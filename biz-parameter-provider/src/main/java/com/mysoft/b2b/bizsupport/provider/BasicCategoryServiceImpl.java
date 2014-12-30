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

import com.mysoft.b2b.bizsupport.api.BasicCategory;
import com.mysoft.b2b.bizsupport.api.BasicCategoryNode;
import com.mysoft.b2b.bizsupport.api.BasicCategoryService;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.mapper.BasicCategoryMapper;
import com.mysoft.b2b.bizsupport.mapper.StandardCategoryMapper;
import com.mysoft.b2b.bizsupport.util.BizSupportUtil;
import com.mysoft.b2b.bizsupport.vo.BasicCategoryVO;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 * BasicCategoryService接口的实现类,提供基础分类相关服务
 * 
 * @author liucz
 * 
 */
@Service("basicCategoryService")
public class BasicCategoryServiceImpl implements BasicCategoryService {
	private static final Logger log = Logger.getLogger(BasicCategoryServiceImpl.class);
	@Autowired
	private BasicCategoryMapper basicCategoryMapper;
	@Autowired
	private StandardCategoryMapper standardCategoryMapper;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IdGenerationService idGenerationService;

	/**
	 * 获取Cache。
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param cachekey
	 *            缓存键
	 * @return
	 */
	private Object getEhCache(String cacheName, String cachekey) {
		Cache ehCache = cacheManager.getCache(cacheName);
		if (ehCache == null) {
			log.warn("Cache is null: " + cacheName);
			return null;
		}
		Element element = ehCache.get(cachekey);
		if (element == null) {
			// if (cacheName.equals(BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY)) {
			loadBasicCategoryNodeCache();
			// }
		}
		element = ehCache.get(cachekey);
		if (element == null) {
			log.warn("Element is null: " + cachekey);
			return null;
		}
		return element.getObjectValue();
	}

	/**
	 * 加载缓存。
	 */
	private void loadBasicCategoryNodeCache() {
		List<BasicCategoryNode> listBasicCategoryNode = new ArrayList<BasicCategoryNode>();
		Map<String, BasicCategoryNode> mapLiveBasicCategoryNode = new HashMap<String, BasicCategoryNode>();
		Map<String, BasicCategoryNode> mapAllBasicCategoryNode = new HashMap<String, BasicCategoryNode>();

		BasicCategoryNode scn, parentScn, allScn;
		List<BasicCategoryDO> bclist = basicCategoryMapper.getBasicCategoryList();
		BasicCategoryDO bcd;
		log.info(bclist.size());
		for (int i = 0; i < bclist.size(); i++) {
			bcd = bclist.get(i);
			scn = BizSupportUtil.convertBasicCategoryDO2Node(bcd);
			allScn = BizSupportUtil.convertBasicCategoryDO2Node(bcd);
			if (bcd.getBasicCategory().getCategoryStatus() == BizSupportUtil.STATUS_LIVENESS) {
				mapLiveBasicCategoryNode.put(scn.getCategoryCode(), scn);
			}
			listBasicCategoryNode.add(scn);
			mapAllBasicCategoryNode.put(allScn.getCategoryCode(), allScn);
		}

		String parentCode;
		for (int i = 0; i < listBasicCategoryNode.size(); i++) {
			scn = listBasicCategoryNode.get(i);
			parentCode = scn.getParentCode();
			if (parentCode != null && !"".equals(parentCode) && !"0".equals(parentCode)) {
				parentScn = mapLiveBasicCategoryNode.get(parentCode);
				if (mapLiveBasicCategoryNode.get(scn.getCategoryCode()) != null && parentScn != null) {
					parentScn.getChildBasicCategoryNodes().add(scn);
				}
				parentScn = mapAllBasicCategoryNode.get(parentCode);
				if (parentScn != null) {
					parentScn.getChildBasicCategoryNodes().add(mapAllBasicCategoryNode.get(scn.getCategoryCode()));
				}
			}
		}
		
		Cache ehCache = cacheManager.getCache(BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY);
		Element element = new Element(BizSupportUtil.CACHE_KEY_MAP_BASIC_CATEGORY, mapLiveBasicCategoryNode);
		ehCache.put(element);
		
		ehCache = cacheManager.getCache(BizSupportUtil.CACHE_ALL_BASIC_CATEGORY);
		element = new Element(BizSupportUtil.CACHE_KEY_MAP_ALL_BASIC_CATEGORY, mapAllBasicCategoryNode);
		ehCache.put(element);
		log.info("加载云平台基础分类缓存数据 loadBasicCategoryNodeCache()！");
	}

	/**
	 * 清空缓存。
	 */
	public void clearEhCache() {
		Cache ehCache = cacheManager.getCache(BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY);
		if (ehCache != null) {
			ehCache.removeAll();
		}

		ehCache = cacheManager.getCache(BizSupportUtil.CACHE_ALL_BASIC_CATEGORY);
		if (ehCache != null) {
			ehCache.removeAll();
		}
	}

	/**
	 * 获取完整分类树。
	 * 
	 * @return List<BasicCategoryNode>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategoryNode> getCategoryRootHierarchy() {
		List<BasicCategoryNode> result = new ArrayList<BasicCategoryNode>();
		Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_BASIC_CATEGORY);
		if (mapBasicCategoryNode == null) {
			return result;
		}
		for (BasicCategoryNode basicCategoryNode : mapBasicCategoryNode.values()) {
			if (basicCategoryNode.getParentCode() == null || "0".equals(basicCategoryNode.getParentCode())
					|| "".equals(basicCategoryNode.getParentCode())) {
				result.add(basicCategoryNode);
			}
		}
		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 获取父分类树，含当前分类
	 * 
	 * @return BasicCategoryNode
	 * 
	 */
	@SuppressWarnings("unchecked")
	public BasicCategoryNode getCategorySuperHierarchy(String categoryCode) {
		Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_BASIC_CATEGORY);
		if (mapBasicCategoryNode == null) {
			return null;
		}

		BasicCategoryNode basicCategoryNode = mapBasicCategoryNode.get(categoryCode);
		BasicCategoryNode parentBasicCategoryNode;
		if (basicCategoryNode == null) {
			return null;
		}
		basicCategoryNode = BizSupportUtil.cloneBasicCategoryNodeWithoutChildren(basicCategoryNode);
		String parentCode = basicCategoryNode.getParentCode();
		while (parentCode != null && !"".equals(parentCode)) {
			parentBasicCategoryNode = mapBasicCategoryNode.get(parentCode);
			if (parentBasicCategoryNode == null) {
				return basicCategoryNode;
			}
			parentBasicCategoryNode = BizSupportUtil.cloneBasicCategoryNodeWithoutChildren(parentBasicCategoryNode);
			parentBasicCategoryNode.getChildBasicCategoryNodes().add(basicCategoryNode);
			parentCode = parentBasicCategoryNode.getParentCode();
			basicCategoryNode = parentBasicCategoryNode;
		}

		return basicCategoryNode;
	}

	/**
	 * 根据分类代码获取子分类树，含当前分类
	 * 
	 * @return BasicCategoryNode
	 * 
	 */
	@SuppressWarnings("unchecked")
	public BasicCategoryNode getCategoryHierarchyByCode(String categoryCode) {
		Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_BASIC_CATEGORY);
		if (mapBasicCategoryNode == null) {
			return null;
		}

		BasicCategoryNode basicCategoryNode = mapBasicCategoryNode.get(categoryCode);
		if (basicCategoryNode == null) {
			return null;
		}
		return basicCategoryNode;
	}

	/**
	 * 根据分类代码查找相应分类。
	 * 
	 * @return BasicCategory
	 * 
	 */
	@SuppressWarnings("unchecked")
	public BasicCategory getCategoryByCode(String categoryCode) {
		BasicCategoryNode bcn = null;
		Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_ALL_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_ALL_BASIC_CATEGORY);
		if (mapBasicCategoryNode == null) {
			return null;
		}
		bcn = mapBasicCategoryNode.get(categoryCode);
		if (bcn == null) {
			return null;
		}
		return BizSupportUtil.cloneBasicCategoryNodeWithoutChildren(bcn);
	}

	/**
	 * 根据分类代码数组查询多个基础分类记录接口(返回基础分类记录[不区分状态])。
	 * 
	 * @return List<BasicCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getCategoriesByCodes(List<String> categoryCodes) {
		List<BasicCategory> result = new ArrayList<BasicCategory>();
		if (BizSupportUtil.checkIsNullOrEmpty(categoryCodes)) {
			return result;
		}
		Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
				BizSupportUtil.CACHE_ALL_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_ALL_BASIC_CATEGORY);
		if (mapBasicCategoryNode == null) {
			return result;
		}
		BasicCategoryNode bcn = null;
		for (int i = 0; i < categoryCodes.size(); i++) {
			bcn = mapBasicCategoryNode.get(categoryCodes.get(i));
			if (bcn == null) {
				continue;
			}
			result.add(BizSupportUtil.cloneBasicCategoryNodeWithoutChildren(bcn));
		}
		return result;
	}

	/**
	 * 根据分类代码获取直接下级分类列表（不含下级）。
	 * 
	 * @return List<BasicCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getChildrenCategories(String categoryCode) {
		List<BasicCategory> result = new ArrayList<BasicCategory>();
		List<BasicCategoryNode> children = null;
		if (categoryCode == null || "0".equals(categoryCode) || "".equals(categoryCode)) {
			children = getCategoryRootHierarchy();
		} else {
			Map<String, BasicCategoryNode> mapBasicCategoryNode = (Map<String, BasicCategoryNode>) getEhCache(
					BizSupportUtil.CACHE_LIVE_BASIC_CATEGORY, BizSupportUtil.CACHE_KEY_MAP_BASIC_CATEGORY);
			if (mapBasicCategoryNode == null) {
				return result;
			}
			BasicCategoryNode basicCategoryNode = mapBasicCategoryNode.get(categoryCode);
			if (basicCategoryNode == null) {
				return result;
			}
			children = basicCategoryNode.getChildBasicCategoryNodes();
		}
		if (!BizSupportUtil.checkIsNullOrEmpty(children)) {
			BasicCategoryNode sBcn;
			BasicCategoryNode tBcn;
			for (int i = 0; i < children.size(); i++) {
				sBcn = children.get(i);
				tBcn = BizSupportUtil.cloneBasicCategoryNodeWithoutChildren(sBcn);
				if (BizSupportUtil.checkIsNullOrEmpty(sBcn.getChildBasicCategoryNodes())) {
					tBcn.setIsLeaf(1);
				} else {
					tBcn.setExpanded(false);
				}
				result.add(tBcn);
			}
		}
		return result;
	}

	/**
	 * 获取全部基础分类VO列表(含停用状态)。
	 * 
	 * @return List<BasicCategoryVO>
	 * 
	 */
	public List<BasicCategoryVO> getBasicCategoryVOList() {
		List<BasicCategoryVO> result = new ArrayList<BasicCategoryVO>();
		List<BasicCategoryDO> bclist = basicCategoryMapper.getBasicCategoryList();
		for (int i = 0; i < bclist.size(); i++) {
			result.add(BizSupportUtil.convertBasicCategoryDO2VO(bclist.get(i)));
		}

		BizSupportUtil.sortListByPropertyInAsc(result, "displayOrder", BizSupportUtil.SORT_VALUE_TYPE_STRING);
		return result;
	}

	/**
	 * 检查基础分类录入合法性。
	 * 
	 * @param basicCategoryVO
	 *            云平台基础分类VO
	 * @return String
	 * 
	 */
	public String checkIsValid(BasicCategoryVO basicCategoryVO) {
		if (basicCategoryVO == null) {
			return "云平台基础分类不能为空！";
		}
		String parentCode = basicCategoryVO.getParentCode();
		if (BizSupportUtil.isNullOrEmpty(parentCode)) {
			parentCode = "0";
			basicCategoryVO.setParentCode("0");
		}

		List<BasicCategoryDO> list = basicCategoryMapper.getChildrenBasicCategoryList(parentCode);
		BasicCategoryDO bc;
		for (int i = 0; i < list.size(); i++) {
			bc = list.get(i);
			log.info(bc.getBasicCategory().getCategoryCode() + "," + bc.getBasicCategory().getDisplayOrder());
			log.info(basicCategoryVO.getCategoryCode() + "," + basicCategoryVO.getDisplayOrder());
			if (!bc.getBasicCategory().getCategoryCode().equals(basicCategoryVO.getCategoryCode())
					&& bc.getBasicCategory().getDisplayOrder().equals(basicCategoryVO.getDisplayOrder())) {
				return "排序编号在当前级别下不能重复！";
			}
		}
		return "";

	}

	/**
	 * 保存基础分类。
	 * 
	 * @param basicCategoryVO
	 *            云平台基础分类VO
	 * @return BasicCategoryVO
	 * @throws PlatformCheckedException
	 * 
	 */
	public BasicCategoryVO saveBasicCategory(BasicCategoryVO basicCategoryVO) throws PlatformCheckedException {
		String check = checkIsValid(basicCategoryVO);
		if (!BizSupportUtil.isNullOrEmpty(check)) {
			throw new PlatformCheckedException(check, null);
		}

		BasicCategoryDO bcd = new BasicCategoryDO();
		bcd.setCategoryId(String.valueOf(idGenerationService.getNextId("bsp_standard_forest")));
		bcd.getBasicCategory().setCategoryCode(bcd.getCategoryId());
		if (BizSupportUtil.isNullOrEmpty(basicCategoryVO.getParentCode())) {
			bcd.getBasicCategory().setParentCode("0");
		} else {
			bcd.getBasicCategory().setParentCode(basicCategoryVO.getParentCode());
		}

		bcd.getBasicCategory().setCategoryShortname("");
		bcd.getBasicCategory().setCategoryName(basicCategoryVO.getCategoryName());
		bcd.getBasicCategory().setDisplayOrder(basicCategoryVO.getDisplayOrder());
		bcd.setCreationTime(new Date());
		bcd.setLastModifiedTime(bcd.getCreationTime());
		bcd.getBasicCategory().setCategoryStatus(BizSupportUtil.STATUS_DEATH);
		bcd.setOperator(basicCategoryVO.getOperator());
		basicCategoryMapper.saveBasicCategory(bcd);
		this.clearEhCache();
		basicCategoryVO.setCategoryCode(bcd.getBasicCategory().getCategoryCode());
		return basicCategoryVO;
	}

	/**
	 * 修改基础分类。
	 * 
	 * @param basicCategoryVO
	 *            云平台基础分类VO
	 * @return BasicCategoryVO
	 * @throws PlatformCheckedException
	 * 
	 */
	public BasicCategoryVO updateBasicCategory(BasicCategoryVO basicCategoryVO) throws PlatformCheckedException {
		if (basicCategoryVO == null) {
			throw new PlatformCheckedException("云平台基础分类不能为空！", null);
		}
		BasicCategoryDO bcd = basicCategoryMapper.getBasicCategoryByCode(basicCategoryVO.getCategoryCode());
		if (bcd == null) {
			throw new PlatformCheckedException("分类已删除，不能修改！", null);
		} else if (bcd.getBasicCategory().getCategoryStatus() == BizSupportUtil.STATUS_LIVENESS) {
			if (BizSupportUtil.isNullOrEmpty(basicCategoryVO.getParentCode())) {
				basicCategoryVO.setParentCode("0");
			}
			if (!"0".equals(basicCategoryVO.getParentCode())) {
				bcd = basicCategoryMapper.getBasicCategoryByCode(basicCategoryVO.getParentCode());
				if (bcd == null) {
					throw new PlatformCheckedException("父级分类已删除，不能修改！", null);
				} else if (bcd.getBasicCategory().getCategoryStatus() == BizSupportUtil.STATUS_DEATH) {
					throw new PlatformCheckedException("启用分类的父级分类不能为停用的分类！", null);
				}
			}
		}

		String check = checkIsValid(basicCategoryVO);
		if (!BizSupportUtil.isNullOrEmpty(check)) {
			throw new PlatformCheckedException(check, null);
		}
		bcd = new BasicCategoryDO();
		bcd.getBasicCategory().setParentCode(basicCategoryVO.getParentCode());
		bcd.getBasicCategory().setCategoryCode(basicCategoryVO.getCategoryCode());
		// bcd.getBasicCategory().setCategoryShortname(basicCategoryVO.getCategoryShortname());
		bcd.getBasicCategory().setCategoryName(basicCategoryVO.getCategoryName());
		bcd.getBasicCategory().setDisplayOrder(basicCategoryVO.getDisplayOrder());
		bcd.setLastModifiedTime(new Date());
		bcd.setOperator(basicCategoryVO.getOperator());
		basicCategoryMapper.updateBasicCategory(bcd);
		this.clearEhCache();
		return basicCategoryVO;
	}

	/**
	 * 更新基础分类状态。
	 * 
	 * @param basicCategoryVOs
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateBasicCategoryStatus(List<BasicCategoryVO> basicCategoryVOs) {
		if (BizSupportUtil.checkIsNullOrEmpty(basicCategoryVOs)) {
			return;
		}
		/*
		 * if (!checkHasForestRelationship(basicCategoryVOs)) { return; }
		 */
		Date lastModifiedTime = new Date();
		Map map = new HashMap();
		List list = new ArrayList();
		BasicCategoryVO bcv;
		int j = 0;
		for (int i = 0; i < basicCategoryVOs.size(); i++) {
			bcv = basicCategoryVOs.get(i);
			list.add(bcv.getCategoryCode());
			map.put("categoryStatus", bcv.getCategoryStatus());
			if (j == 900) {
				map.put("categoryCodes", list);
				map.put("lastModifiedTime", lastModifiedTime);
				basicCategoryMapper.updateBasicCategoryStatus(map);
				map.clear();
				list.clear();
				j = 0;
			}
			j++;
		}
		if (!BizSupportUtil.checkIsNullOrEmpty(list)) {
			map.put("categoryCodes", list);
			map.put("lastModifiedTime", lastModifiedTime);
			basicCategoryMapper.updateBasicCategoryStatus(map);
		}
		this.clearEhCache();
	}

	/**
	 * 检查是否有关联行业标准分类。
	 * 
	 * @param basicCategoryVOs
	 * @return boolean
	 * 
	 */
	public boolean checkHasForestRelationship(List<BasicCategoryVO> basicCategoryVOs) {
		if (!BizSupportUtil.checkIsNullOrEmpty(basicCategoryVOs)) {
			for (int i = 0; i < basicCategoryVOs.size(); i++) {
				if (!BizSupportUtil.checkIsNullOrEmpty(getForestRelationshipByCode(basicCategoryVOs.get(i)
						.getCategoryCode()))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取关联行业标准分类。
	 * 
	 * @param categoryCode
	 * @return List<StandardCategoryVO>
	 * 
	 */
	public List<StandardCategoryVO> getForestRelationshipByCode(String categoryCode) {
		if (BizSupportUtil.isNullOrEmpty(categoryCode)) {
			return null;
		}
		return standardCategoryMapper.getForestRelationshipByCode(categoryCode);
	}

	/**
	 * 保存关联行业标准分类。
	 * 
	 * @param basicCategoryVOs
	 * @return boolean
	 * 
	 */
	public String saveForestRelationship(BasicCategoryVO basicCategoryVO, List<StandardCategoryVO> standardCategoryVOs) {
		if (basicCategoryVO == null) {
			log.error("saveForestRelationship 基础分类不能为空!");
			return "基础分类不能为空!";
		}
		// 删除
		basicCategoryMapper.deleteForestRelationship(basicCategoryVO.getCategoryCode());
		// Insert
		if (!BizSupportUtil.checkIsNullOrEmpty(standardCategoryVOs)) {
			StandardCategoryVO scv;
			Map<String, String> row = new HashMap<String, String>();
			for (int i = 0; i < standardCategoryVOs.size(); i++) {
				scv = standardCategoryVOs.get(i);
				row.put("relationshipId", String.valueOf(idGenerationService.getNextId("bsp_forest_relationship")));
				row.put("basicForestCode", basicCategoryVO.getCategoryCode());
				row.put("standardForestCode", scv.getCategoryCode());
				basicCategoryMapper.saveForestRelationship(row);
			}
		}
		return "";
	}

	/**
	 * 查询子分类列表接口(返回停用和启用状态的所有直接子级列表)。
	 * 
	 * @param categoryCode
	 *            分类代码
	 * @return List<BasicCategory>
	 * 
	 */
	public List<BasicCategory> getAllChildrenCategories(String categoryCode) {
		List<BasicCategory> result = new ArrayList<BasicCategory>();
		if (BizSupportUtil.isNullOrEmpty(categoryCode)) {
			categoryCode = "0";
		}
		List<BasicCategoryDO> list = basicCategoryMapper.getChildrenBasicCategoryList(categoryCode);
		for (int i = 0; i < list.size(); i++) {
			BasicCategoryNode bcn = BizSupportUtil.convertBasicCategoryDO2Node(list.get(i));
			if (basicCategoryMapper.getChildrenCount(bcn.getCategoryCode()) < 1) {
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
	 * 直接从数据库查询单个基础分类记录接口（仅用于维护界面）。
	 * 
	 * @return BasicCategory
	 * 
	 */
	public BasicCategory getCategoryFromDbByCode(String categoryCode) {
		if (BizSupportUtil.isNullOrEmpty(categoryCode) || categoryCode.equals("0")) {
			return null;
		}
		log.info(categoryCode);
		BasicCategoryDO bcd = basicCategoryMapper.getBasicCategoryByCode(categoryCode);
		if (bcd == null) {
			return null;
		}
		log.info(bcd.getBasicCategory().getCategoryName());
		BasicCategoryNode bcn = BizSupportUtil.convertBasicCategoryDO2Node(bcd);
		log.info(bcn.getCategoryName());
		return bcn;
	}
}