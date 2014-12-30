package com.mysoft.b2b.bizsupport.api;

import java.util.List;
import java.util.Map;

import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 *       （云平台）运营分类接口,提供运营分类相关服务
 *        目前支持两种运营分类：供应商运营分类和招标运营分类 对象为 BidOperationCategory or SupplierOperationCategory
 *        两种运营分类的数据树结构描述对象都为OperationCategoryNode
 *
 *        无效节点：父节点处于停用状态，那么所有子节点无效，父节点本身无效。
 *        @author liuza
 *
 */
public interface OperationCategoryService {
	
	public static enum DataType {
		SUPPLIER,//供应商 BidOperationCategory
		BID //招标预告  SupplierOperationCategory
	}
	
    /**
     * 查询运营分类树接口(返回启用状态的整个运营分类树)。
     *    无效的节点不需要返回。
     *
     *   @return List<OperationCategoryNode>
     *
     */
    List<OperationCategoryNode> getCategoryRootHierarchy(DataType dataType);

    /**
     * 查询所有上级分类信息接口，包含停用分类
     *
     *
     * @return  list contains BidOperationCategory or SupplierOperationCategory
     *
     */
    List<BasicCategory> getCategorySuperNodes(DataType dataType, String categoryCode);

    /**
     * 查询运营分类树接口(返回启用状态的当前节点至末级节点的树)
     * 无效的节点不需要返回。
     *
     * @return OperationCategoryNode
     *
     */
    OperationCategoryNode getCategoryHierarchyByCode(DataType dataType, String categoryCode);

    /**
     * 查询单个运营分类记录接口(返回当前运营分类记录[不区分状态])。
     *
     * @return BidOperationCategory or SupplierOperationCategory
     *
     */
    BasicCategory getCategoryByCode(DataType dataType, String categoryCode);

    /**
     * 根据分类代码数组查询多个运营分类记录接口(返回运营分类记录[不区分状态])。
     *        列表对象为 BidOperationCategory or SupplierOperationCategory
     * @return List<BidOperationCategory or SupplierOperationCategory>
     *
     */
    List<BasicCategory> getCategoriesByCodes(DataType dataType, List<String> categoryCodes);

    /**
     * 查询子分类列表接口(返回启用状态的所有直接子级列表)。
     * 列表对象为 BidOperationCategory or SupplierOperationCategory
     *
     * @return List<BidOperationCategory or SupplierOperationCategory>
     *
     */
    List<BasicCategory> getChildrenCategories(DataType dataType, String categoryCode);

    /**
     * 查询所有分类列表 （返回停用和启用状态的所有下级级列表，所有子孙分类，无论是否启用均需返回)。
     *
     * @return List<BidOperationCategory or SupplierOperationCategory>
     *
     */
    List<BasicCategory> getRootSubCategories(DataType dataType);

    /**
     * 查询分类及其下级分类列表，包含本身 （返回启用状态的所有下级级列表，所有子孙分类，无论是否启用均需返回)。
     *
     * @return List<BidOperationCategory or SupplierOperationCategory>
     *
     */
    List<BasicCategory> getAllSubCategories(DataType dataType, String categoryCode);
    
    /**
	 * 获取招标预告运营分类列表(含停用状态)。
	 * 用于维护界面
	 * @return List<BidOperationCategory>
	 * 
	 */
	List<BidOperationCategory> getBidOperationCategoryList();
	
	/**
	 * 检查招标预告运营分类录入合法性。
	 * @param boc
	 * @return String
	 */
	String checkBidOperationCategoryIsValid(BidOperationCategory boc);	
	
	/**
	 * 保存招标预告运营分类。
	 * @param boc
	 * @return Map
	 */
	Map saveBidOperationCategory(BidOperationCategory boc);
		
	/**
	 * 修改招标预告运营分类。
	 * @param boc
	 * @return Map
	 */
	Map updateBidOperationCategory(BidOperationCategory boc);
	
	/**
	 * 删除招标预告运营分类。
	 * @param id
	 * @return Map
	 */
	Map deleteBidOperationCategory(String categoryCode);	
	
	/**
	 * 获取供应商运营分类列表(含停用状态)。 用于维护界面
	 * 
	 * @return List<SupplierOperationCategory>
	 * 
	 */
	List<SupplierOperationCategory> getSupplierOperationCategoryList();

	/**
	 * 检查供应商运营分类录入合法性。
	 * 
	 * @param soc
	 * @return String
	 */
	String checkSupplierOperationCategoryIsValid(SupplierOperationCategory soc);

	/**
	 * 保存供应商运营分类。
	 * 
	 * @param soc
	 * @return Map
	 */
	Map saveSupplierOperationCategory(SupplierOperationCategory soc);
	
	/**
	 * 修改供应商运营分类。
	 * 
	 * @param soc
	 * @return Map
	 */
	Map updateSupplierOperationCategory(SupplierOperationCategory soc);
	
	/**
	 * 删除供应商运营分类。
	 * 
	 * @param id
	 * @return Map
	 */
	Map deleteSupplierOperationCategory(String categoryCode);
	
	/**
	 * 根据目录名称返回当前分类信息
	 * @param dataType
	 * @param directoryName
	 * @return
	 */
    BasicCategory getCategoryBydirectoryName(DataType dataType, String directoryName);

}
