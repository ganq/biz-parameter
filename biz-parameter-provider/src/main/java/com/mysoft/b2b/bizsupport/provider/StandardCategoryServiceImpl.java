package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.api.StandardCategory;
import com.mysoft.b2b.bizsupport.api.StandardCategoryNode;
import com.mysoft.b2b.bizsupport.api.StandardCategoryService;
import com.mysoft.b2b.bizsupport.mapper.StandardCategoryMapper;
import com.mysoft.b2b.bizsupport.util.BizSupportUtil;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 * StandardCategoryService接口的实现类,提供行业标准分类相关服务
 * 
 * @author liucz
 * 
 */
@Service("standardCategoryService")
public class StandardCategoryServiceImpl implements StandardCategoryService {
	private static final Logger log = Logger.getLogger(StandardCategoryServiceImpl.class);
	@Autowired
	private StandardCategoryMapper standardCategoryMapper;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IdGenerationService idGenerationService;

	private Object getEhCache(String cacheName, String cachekey) {
		Cache ehCache = cacheManager.getCache(cacheName);
		if (ehCache == null) {
			log.warn("Cache is null: " + cacheName);
			return null;
		}
		Element element = ehCache.get(cachekey);
		if (element == null) {
			// if
			// (cacheName.equals(BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY)) {
			loadStandardCategoryNodeCache();
			// }
		}
		element = ehCache.get(cachekey);
		if (element == null) {
			log.warn("Element is null: " + cachekey);
			return null;
		}
		return element.getObjectValue();
	}

	private void loadStandardCategoryNodeCache() {
		List<StandardCategoryNode> listStandardCategoryNode = new ArrayList<StandardCategoryNode>();
		Map<String, StandardCategoryNode> mapLiveStandardCategoryNode = new HashMap<String, StandardCategoryNode>();
		Map<String, StandardCategoryNode> mapAllStandardCategoryNode = new HashMap<String, StandardCategoryNode>();
		StandardCategoryNode scn, parentScn, allScn;
		List<StandardCategoryDO> bclist = this.standardCategoryMapper.getStandardCategoryList();
		StandardCategoryDO bcd;
		// log.info(bclist.size());
		for (int i = 0; i < bclist.size(); i++) {
			bcd = bclist.get(i);
			scn = BizSupportUtil.convertStandardCategoryDO2Node(bcd);
			allScn = BizSupportUtil.convertStandardCategoryDO2Node(bcd);
			if (bcd.getStandardCategory().getCategoryStatus() == BizSupportUtil.STATUS_LIVENESS) {
				mapLiveStandardCategoryNode.put(scn.getCategoryCode(), scn);
			}
			listStandardCategoryNode.add(scn);
			mapAllStandardCategoryNode.put(allScn.getCategoryCode(), allScn);
		}

		String parentCode;
		for (int i = 0; i < listStandardCategoryNode.size(); i++) {
			scn = listStandardCategoryNode.get(i);
			parentCode = scn.getParentCode();
			if (parentCode != null && !"".equals(parentCode) && !"0".equals(parentCode)) {
				parentScn = mapLiveStandardCategoryNode.get(parentCode);
				if (mapLiveStandardCategoryNode.get(scn.getCategoryCode()) != null && parentScn != null) {
					parentScn.getChildStandardCategoryNodes().add(scn);
				}
				parentScn = mapAllStandardCategoryNode.get(parentCode);
				if (parentScn != null) {
					parentScn.getChildStandardCategoryNodes()
							.add(mapAllStandardCategoryNode.get(scn.getCategoryCode()));
				}
			}
		}

		Cache ehCache = cacheManager.getCache(BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY);
		Element element = new Element(BizSupportUtil.CACHE_KEY_MAP_STANDARD_CATEGORY, mapLiveStandardCategoryNode);
		ehCache.put(element);

		ehCache = cacheManager.getCache(BizSupportUtil.CACHE_ALL_STANDARD_CATEGORY);
		element = new Element(BizSupportUtil.CACHE_KEY_MAP_ALL_STANDARD_CATEGORY, mapAllStandardCategoryNode);
		ehCache.put(element);
		log.info("加载行业标准分类缓存数据 loadStandardCategoryNodeCache()！");
	}

	public void clearEhCache() {
		Cache ehCache = cacheManager.getCache(BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY);
		if (ehCache != null) {
			ehCache.removeAll();
		}

		ehCache = cacheManager.getCache(BizSupportUtil.CACHE_ALL_STANDARD_CATEGORY);
		if (ehCache != null) {
			ehCache.removeAll();
		}
	}

	/**
	 * 获取完整分类树。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<StandardCategoryNode> getCategoryRootHierarchy() {
		List<StandardCategoryNode> result = new ArrayList<StandardCategoryNode>();
		Map<String, StandardCategoryNode> mapStandardCategoryNode = (Map<String, StandardCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_STANDARD_CATEGORY);
		if (mapStandardCategoryNode == null) {
			return result;
		}
		for (StandardCategoryNode standardCategoryNode : mapStandardCategoryNode.values()) {
			if (standardCategoryNode.getParentCode() == null || "0".equals(standardCategoryNode.getParentCode())
					|| "".equals(standardCategoryNode.getParentCode())) {
				result.add(standardCategoryNode);
			}
		}
		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 获取父分类树，含当前分类
	 * 
	 * @return StandardCategory
	 * 
	 */
	@SuppressWarnings("unchecked")
	public StandardCategoryNode getCategorySuperHierarchy(String categoryCode) {
		Map<String, StandardCategoryNode> mapStandardCategoryNode = (Map<String, StandardCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_STANDARD_CATEGORY);
		if (mapStandardCategoryNode == null) {
			return null;
		}

		StandardCategoryNode standardCategoryNode = mapStandardCategoryNode.get(categoryCode);
		StandardCategoryNode parentStandardCategoryNode;
		if (standardCategoryNode == null) {
			return null;
		}
		standardCategoryNode = BizSupportUtil.cloneStandardCategoryNodeWithoutChildren(standardCategoryNode);
		String parentCode = standardCategoryNode.getParentCode();
		while (parentCode != null && !"".equals(parentCode)) {
			parentStandardCategoryNode = mapStandardCategoryNode.get(parentCode);
			if (parentStandardCategoryNode == null) {
				return standardCategoryNode;
			}
			parentStandardCategoryNode = BizSupportUtil
					.cloneStandardCategoryNodeWithoutChildren(parentStandardCategoryNode);
			parentStandardCategoryNode.getChildStandardCategoryNodes().add(standardCategoryNode);
			parentCode = parentStandardCategoryNode.getParentCode();
			standardCategoryNode = parentStandardCategoryNode;
		}

		return standardCategoryNode;
	}

	/**
	 * 根据分类代码获取子分类树，含当前分类
	 * 
	 * @return StandardCategory
	 * 
	 */
	@SuppressWarnings("unchecked")
	public StandardCategoryNode getCategoryHierarchyByCode(String categoryCode) {
		Map<String, StandardCategoryNode> mapStandardCategoryNode = (Map<String, StandardCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_STANDARD_CATEGORY);
		if (mapStandardCategoryNode == null) {
			return null;
		}

		StandardCategoryNode standardCategoryNode = mapStandardCategoryNode.get(categoryCode);
		if (standardCategoryNode == null) {
			return null;
		}
		return standardCategoryNode;
	}

	/**
	 * 根据分类代码查找相应分类。
	 * 
	 * @return StandardCategory
	 * 
	 */
	@SuppressWarnings("unchecked")
	public StandardCategory getCategoryByCode(String categoryCode) {
		StandardCategoryNode scn;
		Map<String, StandardCategoryNode> mapStandardCategoryNode = (Map<String, StandardCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_ALL_STANDARD_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_ALL_STANDARD_CATEGORY);
		if (mapStandardCategoryNode == null) {
			return null;
		}
		scn = mapStandardCategoryNode.get(categoryCode);
		if (scn == null) {
			return null;
		}
		return BizSupportUtil.cloneStandardCategoryNodeWithoutChildren(scn);
	}

	/**
	 * 根据分类代码获取直接下级分类列表（不含下级）。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<StandardCategory> getChildrenCategories(String categoryCode) {
		List<StandardCategory> result = new ArrayList<StandardCategory>();
		List<StandardCategoryNode> children = null;
		if (categoryCode == null || "0".equals(categoryCode) || "".equals(categoryCode)) {
			children = getCategoryRootHierarchy();
		} else {
			Map<String, StandardCategoryNode> mapStandardCategoryNode = (Map<String, StandardCategoryNode>) getEhCache(
					BizSupportUtil.CACHE_LIVE_STANDARD_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_STANDARD_CATEGORY);
			if (mapStandardCategoryNode == null) {
				return result;
			}
			StandardCategoryNode standardCategoryNode = mapStandardCategoryNode.get(categoryCode);
			if (standardCategoryNode == null) {
				return result;
			}
			children = standardCategoryNode.getChildStandardCategoryNodes();
		}
		if (!BizSupportUtil.checkIsNullOrEmpty(children)) {
			StandardCategoryNode sScn;
			StandardCategoryNode tScn;
			for (int i = 0; i < children.size(); i++) {
				sScn = children.get(i);
				tScn = BizSupportUtil.cloneStandardCategoryNodeWithoutChildren(sScn);
				if (BizSupportUtil.checkIsNullOrEmpty(sScn.getChildStandardCategoryNodes())) {
					tScn.setIsLeaf(1);
				} else {
					tScn.setExpanded(false);
				}
				result.add(tScn);
			}
		}
		return result;
	}

	/**
	 * 获取全部行业标准分类VO列表(含停用状态)。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	public List<StandardCategoryVO> getStandardCategoryVOList() {
		List<StandardCategoryVO> result = new ArrayList<StandardCategoryVO>();
		StandardCategoryVO bcvo;
		List<StandardCategoryDO> bclist = this.standardCategoryMapper.getStandardCategoryList();
		for (int i = 0; i < bclist.size(); i++) {
			bcvo = BizSupportUtil.convertStandardCategoryDO2VO(bclist.get(i));
			result.add(bcvo);
		}
		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 获取启用状态的全部行业标准分类VO列表。
	 * 
	 * @return List<StandardCategoryVO>
	 * 
	 */
	public List<StandardCategoryVO> getStandardCategoryVOListWithUsing() {
		List<StandardCategoryVO> result = new ArrayList<StandardCategoryVO>();
		List<StandardCategoryDO> bclist = this.standardCategoryMapper.getStandardCategoryList();
		for (int i = 0; i < bclist.size(); i++) {
			if (bclist.get(i).getStandardCategory().getCategoryStatus() == BizSupportUtil.STATUS_LIVENESS) {
				result.add(BizSupportUtil.convertStandardCategoryDO2VO(bclist.get(i)));
			}
		}
		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 检查行业标准分类录入合法性。
	 * 
	 * @param standardCategoryVO
	 */
	public String checkIsValid(StandardCategoryVO standardCategoryVO) {
		if (standardCategoryVO == null) {
			return "行业标准分类不能为空";
		}
		String parentCode = standardCategoryVO.getParentCode();
		if (BizSupportUtil.isNullOrEmpty(parentCode)) {
			parentCode = "0";
			standardCategoryVO.setParentCode("0");
		}

		StandardCategoryDO bc;
		List<StandardCategoryDO> list = standardCategoryMapper.getStandardCategoryByCategoryUiCode(standardCategoryVO
				.getCategoryUiCode());
		for (int i = 0; i < list.size(); i++) {
			bc = list.get(i);
			// log.info(bc.getStandardCategory().getCategoryCode() + "," +
			// bc.getStandardCategory().getCategoryUiCode());
			// log.info(standardCategoryVO.getCategoryCode() + "," +
			// standardCategoryVO.getCategoryUiCode());
			if (!bc.getStandardCategory().getCategoryCode().equals(standardCategoryVO.getCategoryCode())
					&& bc.getStandardCategory().getCategoryUiCode().equals(standardCategoryVO.getCategoryUiCode())) {
				return "分类编码不能重复！";
			}
		}

		list = standardCategoryMapper.getChildrenStandardCategoryList(parentCode);
		for (int i = 0; i < list.size(); i++) {
			bc = list.get(i);
			// log.info(bc.getStandardCategory().getCategoryCode() + "," +
			// bc.getStandardCategory().getDisplayOrder());
			// log.info(standardCategoryVO.getCategoryCode() + "," +
			// standardCategoryVO.getDisplayOrder());
			if (!bc.getStandardCategory().getCategoryCode().equals(standardCategoryVO.getCategoryCode())
					&& bc.getStandardCategory().getDisplayOrder().equals(standardCategoryVO.getDisplayOrder())) {
				return "排序编号在当前级别下不能重复！";
			}
		}

		return "";

	}

	/**
	 * 保存行业标准分类。
	 * 
	 * @param StandardCategoryVO
	 * @throws PlatformCheckedException
	 */
	public StandardCategoryVO saveStandardCategory(StandardCategoryVO StandardCategoryVO)
			throws PlatformCheckedException {
		String check = checkIsValid(StandardCategoryVO);
		if (!BizSupportUtil.isNullOrEmpty(check)) {
			throw new PlatformCheckedException(check, null);
		}
		StandardCategoryDO bcd = new StandardCategoryDO();
		bcd.setCategoryId(String.valueOf(idGenerationService.getNextId("bsp_standard_forest")));
		bcd.getStandardCategory().setCategoryCode(bcd.getCategoryId());
		if (BizSupportUtil.isNullOrEmpty(StandardCategoryVO.getParentCode())) {
			bcd.getStandardCategory().setParentCode("0");
		} else {
			bcd.getStandardCategory().setParentCode(StandardCategoryVO.getParentCode());
		}

		bcd.getStandardCategory().setCategoryShortname(StandardCategoryVO.getCategoryShortname());
		bcd.getStandardCategory().setCategoryName(StandardCategoryVO.getCategoryName());
		bcd.getStandardCategory().setCategoryUiCode(StandardCategoryVO.getCategoryUiCode());
		bcd.getStandardCategory().setDisplayOrder(StandardCategoryVO.getDisplayOrder());
		bcd.setCreationTime(new Date());
		bcd.setLastModifiedTime(bcd.getCreationTime());
		bcd.getStandardCategory().setCategoryStatus(BizSupportUtil.STATUS_LIVENESS);
		bcd.setOperator(StandardCategoryVO.getOperator());
		standardCategoryMapper.saveStandardCategory(bcd);
		this.clearEhCache();
		StandardCategoryVO.setCategoryCode(bcd.getStandardCategory().getCategoryCode());
		return StandardCategoryVO;
	}

	/**
	 * 修改行业标准分类。
	 * 
	 * @param standardCategoryVO
	 * @throws PlatformCheckedException
	 */
	public StandardCategoryVO updateStandardCategory(StandardCategoryVO standardCategoryVO)
			throws PlatformCheckedException {
		if (standardCategoryVO == null) {
			throw new PlatformCheckedException("行业标准分类不能为空", null);
		}
		StandardCategoryDO scdo = standardCategoryMapper
				.getStandardCategoryByCode(standardCategoryVO.getCategoryCode());

		if (scdo == null) {
			throw new PlatformCheckedException("分类已删除，不能修改！", null);
		} else if (scdo.getStandardCategory().getCategoryStatus() == BizSupportUtil.STATUS_LIVENESS) {
			if (BizSupportUtil.isNullOrEmpty(standardCategoryVO.getParentCode())) {
				standardCategoryVO.setParentCode("0");
			}
			if (!"0".equals(standardCategoryVO.getParentCode())) {
				scdo = standardCategoryMapper.getStandardCategoryByCode(standardCategoryVO.getParentCode());
				if (scdo == null) {
					throw new PlatformCheckedException("父级分类已删除，不能修改！", null);
				} else if (scdo.getStandardCategory().getCategoryStatus() == BizSupportUtil.STATUS_DEATH) {
					throw new PlatformCheckedException("启用分类的父级分类不能为停用的分类！", null);
				}
			}
		}

		String check = checkIsValid(standardCategoryVO);
		if (!BizSupportUtil.isNullOrEmpty(check)) {
			throw new PlatformCheckedException(check, null);
		}
		StandardCategoryDO scd = new StandardCategoryDO();
		scd.getStandardCategory().setParentCode(standardCategoryVO.getParentCode());
		scd.getStandardCategory().setCategoryCode(standardCategoryVO.getCategoryCode());
		scd.getStandardCategory().setCategoryUiCode(standardCategoryVO.getCategoryUiCode());
		scd.getStandardCategory().setCategoryShortname(standardCategoryVO.getCategoryShortname());
		scd.getStandardCategory().setCategoryName(standardCategoryVO.getCategoryName());
		scd.getStandardCategory().setDisplayOrder(standardCategoryVO.getDisplayOrder());
		scd.setLastModifiedTime(new Date());
		scd.setOperator(standardCategoryVO.getOperator());
		standardCategoryMapper.updateStandardCategory(scd);
		this.clearEhCache();
		return standardCategoryVO;
	}

	/**
	 * 更新行业标准分类状态。
	 * 
	 * @param StandardCategoryVO
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateStandardCategoryStatus(List<StandardCategoryVO> standardCategoryVOs) {
		if (BizSupportUtil.checkIsNullOrEmpty(standardCategoryVOs)) {
			return;
		}
		Map map = new HashMap();
		List list = new ArrayList();
		StandardCategoryVO bcv;
		Date lastModifiedTime = new Date();
		int j = 0;
		for (int i = 0; i < standardCategoryVOs.size(); i++) {
			bcv = standardCategoryVOs.get(i);
			list.add(bcv.getCategoryCode());
			map.put("categoryStatus", bcv.getCategoryStatus());
			if (j == 900) {
				map.put("categoryCodes", list);
				map.put("lastModifiedTime", lastModifiedTime);
				standardCategoryMapper.updateStandardCategoryStatus(map);
				map.clear();
				list.clear();
				j = 0;
			}
			j++;
		}
		if (!BizSupportUtil.checkIsNullOrEmpty(list)) {
			map.put("categoryCodes", list);
			map.put("lastModifiedTime", lastModifiedTime);
			standardCategoryMapper.updateStandardCategoryStatus(map);
		}
		this.clearEhCache();
	}

	/**
	 * 查询子分类列表接口(返回停用和启用状态的所有直接子级列表)。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	public List<StandardCategory> getAllChildrenCategories(String categoryCode) {
		if (BizSupportUtil.isNullOrEmpty(categoryCode)) {
			categoryCode = "0";
		}
		List<StandardCategory> result = new ArrayList<StandardCategory>();
		List<StandardCategoryDO> list = standardCategoryMapper.getChildrenStandardCategoryList(categoryCode);
		for (int i = 0; i < list.size(); i++) {
			StandardCategoryNode bcn = BizSupportUtil.convertStandardCategoryDO2Node(list.get(i));
			if (standardCategoryMapper.getChildrenCount(bcn.getCategoryCode()) < 1) {
				bcn.setIsLeaf(1);
			} else {
				bcn.setExpanded(false);
			}
			result.add(bcn);
		}

		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 直接从数据库查询单个行业标准分类记录接口（仅用于维护界面）。
	 * 
	 * @return BasicCategory
	 * 
	 */
	public StandardCategory getCategoryFromDbByCode(String categoryCode) {
		if (BizSupportUtil.isNullOrEmpty(categoryCode) || categoryCode.equals("0")) {
			return null;
		}
		// log.info(categoryCode);
		StandardCategoryDO bcd = standardCategoryMapper.getStandardCategoryByCode(categoryCode);
		if (bcd == null) {
			return null;
		}
		// log.info(bcd.getStandardCategory().getCategoryName());
		StandardCategoryNode bcn = BizSupportUtil.convertStandardCategoryDO2Node(bcd);
		// log.info(bcn.getCategoryName());
		return bcn;
	}

}