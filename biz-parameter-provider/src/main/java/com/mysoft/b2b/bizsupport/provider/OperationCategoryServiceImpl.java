/**
 * 
 */
package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.Datastore;
import com.mysoft.b2b.bizsupport.api.BasicCategory;
import com.mysoft.b2b.bizsupport.api.BidOperationCategory;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.api.MongoDBService;
import com.mysoft.b2b.bizsupport.api.OperationCategoryNode;
import com.mysoft.b2b.bizsupport.api.OperationCategoryService;
import com.mysoft.b2b.bizsupport.api.SupplierOperationCategory;

/**
 * @author liucz
 * 
 */
@Service("operationCategoryService")
public class OperationCategoryServiceImpl implements OperationCategoryService {
	private static final Logger log = Logger.getLogger(OperationCategoryServiceImpl.class);

	@Resource(name = "mongoDBService")
	private MongoDBService mongoDBService;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private IdGenerationService idGenerationService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List createTree(DataType dataType) {
		List list = new ArrayList();
		List<? extends BasicCategory> bcs = null;
		Datastore ds = mongoDBService.getDatastore();
		if (dataType == DataType.BID) {
			bcs = ds.createQuery(BidOperationCategory.class).filter("categoryStatus", 1).order("displayOrder").asList();
		} else if (dataType == DataType.SUPPLIER) {
			bcs = ds.createQuery(SupplierOperationCategory.class).filter("categoryStatus", 1).order("displayOrder")
					.asList();
		}

		// 一级有效节点
		List<OperationCategoryNode> nodes = new ArrayList<OperationCategoryNode>();
		// 所有启用的节点(含父节点是停用的)
		Map<String, String> allParentCodes = new HashMap<String, String>();
		Map<String, List<OperationCategoryNode>> mapParentNode = new HashMap<String, List<OperationCategoryNode>>();
		Map<String, OperationCategoryNode> mapAllNode = new HashMap<String, OperationCategoryNode>();
		BasicCategory bc;
		OperationCategoryNode node;
		List<OperationCategoryNode> children;
		// 找出有效一级节点，各节点的子节点，父节点列表
		if (bcs != null & bcs.size() > 0) {
			for (int i = 0; i < bcs.size(); i++) {
				bc = bcs.get(i);
				node = OperationCategoryNode.categoryToNode(bc);
				mapAllNode.put(node.getCategoryCode(), node);
				// 一级节点处理
				if (bc.getParentCode() == null || "".equals(bc.getParentCode()) || "0".equals(bc.getParentCode())) {
					node.setEffective(true);
					nodes.add(node);
				} else {
					// 非一级节点处理
					children = mapParentNode.get(node.getParentCode());
					if (children == null) {
						children = new ArrayList<OperationCategoryNode>();
						mapParentNode.put(node.getParentCode(), children);
					}
					children.add(node);
					allParentCodes.put(node.getParentCode(), node.getParentCode());
				}
			}
		}

		// 根据父节点列表挂子节点
		/*
		 * String pc; for (int i = 0; i < allParentCodes.size(); i++) { pc =
		 * allParentCodes.get(i); if (mapAllNode.get(pc) != null) {
		 * mapAllNode.get(pc).setChildBasicCategoryNodes(mapParentNode.get(pc));
		 * } }
		 */

		for (String key : allParentCodes.keySet()) {
			if (mapAllNode.get(key) != null) {
				mapAllNode.get(key).setChildBasicCategoryNodes(mapParentNode.get(key));
			}
		}

		// 根据nodes构造有效的Map
		Map<String, OperationCategoryNode> mapNode = new HashMap<String, OperationCategoryNode>();
		getMapNodes(nodes, mapNode);

		list.add(nodes);
		list.add(mapNode);
		return list;
	}

	/**
	 * 遍历子节点构造节点Map
	 * 
	 * @param nodes
	 * @param mapNode
	 */
	private void getMapNodes(List<OperationCategoryNode> nodes, Map<String, OperationCategoryNode> mapNode) {
		if (nodes == null || nodes.size() == 0) {
			return;
		}
		OperationCategoryNode node;
		List<OperationCategoryNode> children;
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			mapNode.put(node.getCategoryCode(), node);
			children = node.getChildrenCategoryNodes();
			getMapNodes(children, mapNode);
		}
		return;
	}

	/**
	 * 查询运营分类树接口(返回启用状态的整个运营分类树)。 无效的节点不需要返回。 无效的节点：当前节点或父节点为停用状态
	 * 
	 * @return List<OperationCategoryNode>
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<OperationCategoryNode> getCategoryRootHierarchy(DataType dataType) {
		List list = createTree(dataType);
		return (List<OperationCategoryNode>) list.get(0);
	}

	/**
	 * 查询上级分类节点信息接口，包含无效节点(含当前节点)
	 * 
	 * 
	 * @return list contains bidOperationCategory or SupplierOperationCategory
	 * 
	 */
	public List<BasicCategory> getCategorySuperNodes(DataType dataType, String categoryCode) {
		List<BasicCategory> list = new ArrayList<BasicCategory>();
		BasicCategory bc = getCategoryByCode(dataType, categoryCode);
		if (bc != null) {
			list.add(bc);
			String parentCode = bc.getParentCode();
			while (parentCode != null && !"".equals(parentCode) && !"0".equals(parentCode)) {
				bc = getCategoryByCode(dataType, parentCode);
				if (bc != null) {
					list.add(0, bc);
					parentCode = bc.getParentCode();
				} else {
					break;
				}
			}
		}
		return list;
	}

	/**
	 * 查询运营分类树接口(返回启用状态的当前节点至末级节点的树) 无效的节点不需要返回。 (父级或当前节点无效时无返回值)
	 * 
	 * @return OperationCategoryNode
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OperationCategoryNode getCategoryHierarchyByCode(DataType dataType, String categoryCode) {
		List list = createTree(dataType);
		Map<String, OperationCategoryNode> mapNode = (Map<String, OperationCategoryNode>) list.get(1);
		return mapNode.get(categoryCode);
	}

	/**
	 * 查询单个运营分类记录接口(返回当前运营分类记录[不区分状态])。
	 * 
	 * @return BidOperationCategory or SupplierOperationCategory
	 * 
	 */
	public BasicCategory getCategoryByCode(DataType dataType, String categoryCode) {
		Datastore ds = mongoDBService.getDatastore();
		if (dataType == DataType.BID) {
			List<BidOperationCategory> list = ds.createQuery(BidOperationCategory.class)
					.filter("categoryCode", categoryCode).asList();
			if (null != list && list.size() == 1) {
				return list.get(0);
			}
		} else if (dataType == DataType.SUPPLIER) {
			List<SupplierOperationCategory> list = ds.createQuery(SupplierOperationCategory.class)
					.filter("categoryCode", categoryCode).asList();
			if (null != list && list.size() == 1) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * 根据分类代码数组查询多个运营分类记录接口(返回运营分类记录[不区分状态])。 列表对象为 BidOperationCategory or
	 * SupplierOperationCategory
	 * 
	 * @return List<BidOperationCategory or SupplierOperationCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getCategoriesByCodes(DataType dataType, List<String> categoryCodes) {
		if(categoryCodes ==null || categoryCodes.size()<1){
			return new ArrayList<BasicCategory>();
		}
		Datastore ds = mongoDBService.getDatastore();
		if (dataType == DataType.BID) {
			List<? extends BasicCategory> list = ds.createQuery(BidOperationCategory.class)
					.filter("categoryCode in ", categoryCodes).asList();
			return (List<BasicCategory>) list;

		} else if (dataType == DataType.SUPPLIER) {
			List<? extends BasicCategory> list = ds.createQuery(SupplierOperationCategory.class)
					.filter("categoryCode in", categoryCodes).asList();
			return (List<BasicCategory>) list;
		}
		return new ArrayList<BasicCategory>();
	}

	/**
	 * 查询子分类列表接口(返回启用状态的所有直接子级列表)。 列表对象为 BidOperationCategory or
	 * SupplierOperationCategory
	 * 
	 * @return List<BidOperationCategory or SupplierOperationCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getChildrenCategories(DataType dataType, String categoryCode) {
		Datastore ds = mongoDBService.getDatastore();
		List<? extends BasicCategory> nodes = null;
		if (dataType == DataType.BID) {
			nodes = ds.createQuery(BidOperationCategory.class).filter("parentCode", categoryCode)
					.filter("categoryStatus", 1).order("displayOrder").asList();
		} else if (dataType == DataType.SUPPLIER) {
			nodes = ds.createQuery(SupplierOperationCategory.class).filter("parentCode", categoryCode)
					.filter("categoryStatus", 1).order("displayOrder").asList();
		}

		return (List<BasicCategory>) nodes;
	}

	/**
	 * 查询子运营分类列表接口(分类状态为分类本身的状态)。 列表对象为 BidOperationCategory or
	 * SupplierOperationCategory
	 * 
	 * @return List<BidOperationCategory or SupplierOperationCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getAllChildrenCategories(DataType dataType, String categoryCode) {
		Datastore ds = mongoDBService.getDatastore();
		List<? extends BasicCategory> nodes = null;
		if (dataType == DataType.BID) {
			nodes = ds.createQuery(BidOperationCategory.class).filter("parentCode", categoryCode).order("displayOrder")
					.asList();
		} else if (dataType == DataType.SUPPLIER) {
			nodes = ds.createQuery(SupplierOperationCategory.class).filter("parentCode", categoryCode)
					.order("displayOrder").asList();
		}

		return (List<BasicCategory>) nodes;
	}

	/**
	 * 查询所有分类列表 （返回停用和启用状态的所有下级级列表，所有子孙分类，无论是否启用均需返回)。
	 * 
	 * @return List<BidOperationCategory or SupplierOperationCategory>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<BasicCategory> getRootSubCategories(DataType dataType) {
		log.info("getRootSubCategories dataType=" + dataType);
		Datastore ds = mongoDBService.getDatastore();
		if (dataType == DataType.BID) {
			List<? extends BasicCategory> list = ds.createQuery(BidOperationCategory.class).order("displayOrder")
					.asList();
			return (List<BasicCategory>) list;
		} else if (dataType == DataType.SUPPLIER) {
			List<? extends BasicCategory> list = ds.createQuery(SupplierOperationCategory.class).order("displayOrder")
					.asList();
			return (List<BasicCategory>) list;
		}
		return null;
	}

	private List<BasicCategory> getAllCategoriesByParentCode(DataType dataType, String parentCode) {
		Datastore ds = mongoDBService.getDatastore();
		List<BasicCategory> result = new ArrayList<BasicCategory>();
		if (dataType == DataType.BID) {
			List<BidOperationCategory> list = ds.createQuery(BidOperationCategory.class)
					.filter("parentCode", parentCode).order("displayOrder").asList();
			if (null != list && list.size() > 0) {
				BidOperationCategory boc;
				for (int i = 0; i < list.size(); i++) {
					boc = list.get(i);
					if (boc != null) {
						result.add(boc);
						result.addAll(getAllCategoriesByParentCode(dataType, boc.getCategoryCode()));
					}
				}
			}
		} else if (dataType == DataType.SUPPLIER) {
			List<SupplierOperationCategory> list = ds.createQuery(SupplierOperationCategory.class)
					.filter("parentCode", parentCode).order("displayOrder").asList();
			if (null != list && list.size() > 0) {
				SupplierOperationCategory soc;
				for (int i = 0; i < list.size(); i++) {
					soc = list.get(i);
					if (soc != null) {
						result.add(soc);
						result.addAll(getAllCategoriesByParentCode(dataType, soc.getCategoryCode()));
					}
				}
			}
		}

		return result;
	}

	/**
	 * 查询分类及其下级分类列表，包含本身 （返回启用状态的所有下级级列表，所有子孙分类，无论是否启用均需返回)。
	 * 
	 * @return List<BidOperationCategory or SupplierOperationCategory>
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BasicCategory> getAllSubCategories(DataType dataType, String categoryCode) {
		List<BasicCategory> list = new ArrayList<BasicCategory>();
		List treeList = createTree(dataType);
		Map<String, OperationCategoryNode> mapNode = (Map<String, OperationCategoryNode>) treeList.get(1);
		OperationCategoryNode ocn = mapNode.get(categoryCode);
		if (ocn == null) {
			return list;
		}

		BasicCategory bc = getCategoryByCode(dataType, categoryCode);
		if (bc != null) {
			list.add(bc);
			getChildrendCategories(dataType, ocn.getChildrenCategoryNodes(), list);
		}
		return list;
	}

	/**
	 * 遍历子节点LIST构造BasicCategory
	 * 
	 * @param nodes
	 * @param mapNode
	 */
	private void getChildrendCategories(DataType dataType, List<OperationCategoryNode> nodes, List<BasicCategory> list) {
		if (nodes == null || nodes.size() == 0) {
			return;
		}
		OperationCategoryNode node;
		BasicCategory bc;
		List<OperationCategoryNode> children;
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			bc = getCategoryByCode(dataType, node.getCategoryCode());
			if (bc != null) {
				list.add(bc);
			}
			children = node.getChildrenCategoryNodes();
			if (nodes != null && nodes.size() > 0) {
				getChildrendCategories(dataType, children, list);
			}
		}
		return;
	}

	/**
	 * 获取招标预告运营分类列表(含停用状态)。 用于维护界面
	 * 
	 * @return List<BidOperationCategory>
	 * 
	 */
	public List<BidOperationCategory> getBidOperationCategoryList() {
		Datastore ds = mongoDBService.getDatastore();
		return ds.createQuery(BidOperationCategory.class).order("displayOrder").asList();
	}

	/**
	 * 检查标预告运营分类录入合法性。
	 * 
	 * @param boc
	 * @return String
	 */
	public String checkBidOperationCategoryIsValid(BidOperationCategory boc) {
		return null;
	}

	/**
	 * 保存标预告运营分类。
	 * 
	 * @param boc
	 * @return Map
	 */
	public Map<String, Object> saveBidOperationCategory(BidOperationCategory boc) {
		Datastore ds = mongoDBService.getDatastore();
		boc.setCategoryCode(String.valueOf(this.idGenerationService.getNextId("infr_bid_operation_category")));
		if (boc.getParentCode() == null || "".equals(boc.getParentCode())) {
			boc.setParentCode("0");
		}
		ds.save(boc);

		return null;
	}

	/**
	 * 修改招标预告运营分类。
	 * 
	 * @param boc
	 * @return BidOperationCategory
	 */
	public Map<String, Object> updateBidOperationCategory(BidOperationCategory boc) {
		// try {
		Datastore ds = mongoDBService.getDatastore();
		List<BidOperationCategory> list = ds.createQuery(BidOperationCategory.class)
				.filter("categoryCode", boc.getCategoryCode()).asList();
		if (null != list && list.size() == 1) {
			BidOperationCategory b = list.get(0);
			b.setBindBasicCategoryIds(boc.getBindBasicCategoryIds());
			b.setBindUrl(boc.getBindUrl());
			// b.setCategoryCode(boc.getCategoryCode());
			b.setCategoryName(boc.getCategoryName());
			b.setCategoryShortname(boc.getCategoryShortname());
			b.setDisplayOrder(boc.getDisplayOrder());
			b.setLastLevel(boc.isLastLevel());
			b.setParentCode(boc.getParentCode());
			b.setCategoryStatus(boc.getCategoryStatus());
			//SEO上设置信息
			b.setSeoModel(boc.getSeoModel());
			ds.save(b);
		} else {
			log.error("update mongo BidOperationCategory error: 招标预告运营分类存在重复的CategoryCode=" + boc.getCategoryCode());
		}

		return null;
	}

	/**
	 * 删除招标预告运营分类。
	 * 
	 * @param id
	 * @return Map
	 */
	public Map<String, Object> deleteBidOperationCategory(String categoryCode) {
		Datastore ds = mongoDBService.getDatastore();
		List<BidOperationCategory> children = ds.createQuery(BidOperationCategory.class)
				.filter("parentCode", categoryCode).asList();
		if (null != children && children.size() != 0) {
			return null;
		}

		List<BidOperationCategory> list = ds.createQuery(BidOperationCategory.class)
				.filter("categoryCode", categoryCode).asList();
		if (null != list && list.size() == 1) {
			BidOperationCategory b = list.get(0);
			ds.delete(b);
		} else {
			log.error("delete mongo BidOperationCategory error: 招标预告运营分类不存在CategoryCode=" + categoryCode);
		}
		return null;
	}

	/**
	 * 获取供应商运营分类列表(含停用状态)。 用于维护界面
	 * 
	 * @return List<BidOperationCategory>
	 * 
	 */
	public List<SupplierOperationCategory> getSupplierOperationCategoryList() {
		Datastore ds = mongoDBService.getDatastore();
		return ds.createQuery(SupplierOperationCategory.class).order("displayOrder").asList();
	}

	/**
	 * 检查供应商运营分类录入合法性。
	 * 
	 * @param soc
	 * @return String
	 */
	public String checkSupplierOperationCategoryIsValid(SupplierOperationCategory soc) {
		return null;
	}

	/**
	 * 保存供应商运营分类。
	 * 
	 * @param soc
	 * @return BidOperationCategory
	 */
	public Map<String, Object> saveSupplierOperationCategory(SupplierOperationCategory soc) {
		Datastore ds = mongoDBService.getDatastore();
		soc.setCategoryCode(String.valueOf(this.idGenerationService.getNextId("infr_supplier_operation_category")));
		if (soc.getParentCode() == null || "".equals(soc.getParentCode())) {
			soc.setParentCode("0");
		}
		ds.save(soc);
		return null;

	}

	/**
	 * 修改供应商运营分类。
	 * 
	 * @param soc
	 * @return Map
	 */
	public Map<String, Object> updateSupplierOperationCategory(SupplierOperationCategory soc) {
		Datastore ds = mongoDBService.getDatastore();
		List<SupplierOperationCategory> list = ds.createQuery(SupplierOperationCategory.class)
				.filter("categoryCode", soc.getCategoryCode()).asList();
		if (null != list && list.size() == 1) {
			SupplierOperationCategory s = list.get(0);
			s.setBindBasicCategoryIds(soc.getBindBasicCategoryIds());
			s.setBindUrl(soc.getBindUrl());
			// b.setCategoryCode(boc.getCategoryCode());
			s.setCategoryName(soc.getCategoryName());
			s.setCategoryShortname(soc.getCategoryShortname());
			s.setDisplayOrder(soc.getDisplayOrder());
			s.setLastLevel(soc.isLastLevel());
			s.setParentCode(soc.getParentCode());
			s.setCategoryStatus(soc.getCategoryStatus());
			//SEO上设置信息
			s.setSeoModel(soc.getSeoModel());
			ds.save(s);
		} else {
			log.error("update mongo SupplierOperationCategory error: 供应商运营分类存在重复的CategoryCode=" + soc.getCategoryCode());
		}
		return null;
	}

	/**
	 * 删除供应商运营分类。
	 * 
	 * @param id
	 * @return Map
	 */
	public Map<String, Object> deleteSupplierOperationCategory(String categoryCode) {

		Datastore ds = mongoDBService.getDatastore();
		List<SupplierOperationCategory> children = ds.createQuery(SupplierOperationCategory.class)
				.filter("parentCode", categoryCode).asList();
		if (null != children && children.size() != 0) {
			return null;
		}
		List<SupplierOperationCategory> list = ds.createQuery(SupplierOperationCategory.class)
				.filter("categoryCode", categoryCode).asList();
		if (null != list && list.size() == 1) {
			SupplierOperationCategory s = list.get(0);
			ds.delete(s);
		} else {
			log.error("delete mongo SupplierOperationCategory error: 供应商运营分类不存在CategoryCode=" + categoryCode);
		}
		return null;
	}

	@Override
	public BasicCategory getCategoryBydirectoryName(DataType dataType,
			String directoryName) {
		Datastore ds = mongoDBService.getDatastore();
		if (dataType == DataType.BID) {
			List<BidOperationCategory> list = ds.createQuery(BidOperationCategory.class)
					.filter("seoModel.directoryName", directoryName).asList();
			if (null != list && list.size() == 1) {
				return list.get(0);
			}
		} else if (dataType == DataType.SUPPLIER) {
			List<SupplierOperationCategory> list = ds.createQuery(SupplierOperationCategory.class)
					.filter("seoModel.directoryName", directoryName).asList();
			if (null != list && list.size() == 1) {
				return list.get(0);
			}
		}
		return null;
	}
	
}
