package com.mysoft.b2b.bizsupport.api;

import java.util.List;

import com.mysoft.b2b.bizsupport.vo.BasicCategoryVO;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 * （云平台）基础分类接口,提供基础分类相关服务
 * 
 * @author liucz
 * 
 */
public interface BasicCategoryService {
	
	/**
	 * 查询基础分类树接口(返回启用状态的整个基础分类树)。
	 * 
	 * @return List<BasicCategoryNode>
	 * 
	 */
	List<BasicCategoryNode> getCategoryRootHierarchy();

	/**
	 * 查询上级分类树接口(返回启用状态的当前节点至根节点树)
	 * 
	 * @return BasicCategoryNode
	 * 
	 */
	BasicCategoryNode getCategorySuperHierarchy(String categoryCode);

	/**
	 * 查询基础分类树接口(返回启用状态的当前节点至末级节点的树)
	 * 
	 * @return BasicCategoryNode
	 * 
	 */
	BasicCategoryNode getCategoryHierarchyByCode(String categoryCode);
	
	/**
	 * 查询单个基础分类记录接口(返回当前基础分类记录[不区分状态])。
	 * 
	 * @return BasicCategory
	 * 
	 */
	BasicCategory getCategoryByCode(String categoryCode);
	
	/**
	 * 根据分类代码数组查询多个基础分类记录接口(返回基础分类记录[不区分状态])。
	 * 
	 * @return List<BasicCategory>
	 * 
	 */
	List<BasicCategory> getCategoriesByCodes(List<String> categoryCodes);
		
	/**
	 * 查询子分类列表接口(返回启用状态的所有直接子级列表)。
	 * 
	 * @return List<BasicCategory>
	 * 
	 */
	List<BasicCategory> getChildrenCategories(String categoryCode);
	
	/**
	 * 获取指定级次的分类列表（不含下级）。
	 * 
	 * @return List<BasicCategoryNode>
	 * 
	 */
	//List<BasicCategory> getChildrenBasicCategoriesWithLevel(int level);
	
	/**
	 * 获取全部基础分类VO列表(含停用状态)。
	 * 
	 * @return List<BasicCategoryVO>
	 * 
	 */
	List<BasicCategoryVO> getBasicCategoryVOList();
	
	/**
	 * 获取子分类分类VO列表(含停用状态)。
	 * 
	 * @return List<BasicCategoryVO>
	 * 
	 */
	//List<BasicCategoryVO> getChildrenBasicCategoryVOList(String categoryCode);
	
	/**
	 * 检查基础分类录入合法性。
	 * @param basicCategoryVO
	 * @return String
	 */
	String checkIsValid(BasicCategoryVO basicCategoryVO);	
	
	/**
	 * 保存基础分类。
	 * @param basicCategoryVO
	 * @return BasicCategoryVO
	 */
	BasicCategoryVO saveBasicCategory(BasicCategoryVO basicCategoryVO) throws PlatformCheckedException;
		
	/**
	 * 修改基础分类。
	 * @param basicCategoryVO
	 * @return BasicCategoryVO
	 */
	BasicCategoryVO updateBasicCategory(BasicCategoryVO basicCategoryVO) throws PlatformCheckedException;
	
	/**
	 * 更新基础分类状态。
	 * @param basicCategoryVOs
	 */
	void updateBasicCategoryStatus(List<BasicCategoryVO> basicCategoryVOs);
	
	/**
	 * 查询子分类列表接口(返回停用和启用状态的所有直接子级列表)。
	 * 
	 * @return List<BasicCategory>
	 * 
	 */
	List<BasicCategory> getAllChildrenCategories(String categoryCode);
	
	/**
	 * 检查是否有关联行业标准分类。
	 * 
	 * @param basicCategoryVOs
	 * @return boolean
	 * 
	 */
	boolean checkHasForestRelationship(List<BasicCategoryVO> basicCategoryVOs);
	
	/**
	 * 获取关联行业标准分类。
	 * 
	 * @param categoryCode
	 * @return List<StandardCategoryVO>
	 * 
	 */
	List<StandardCategoryVO> getForestRelationshipByCode(String categoryCode);
	
	/**
	 * 保存关联行业标准分类。
	 * 
	 * @param basicCategoryVOs
	 * @return boolean
	 * 
	 */
	String saveForestRelationship(BasicCategoryVO basicCategoryVO,
			List<StandardCategoryVO> standardCategoryVOs);
	
	/**
	 * 直接从数据库查询单个基础分类记录接口（仅用于维护界面）。
	 * 
	 * @return BasicCategory
	 * 
	 */
	BasicCategory getCategoryFromDbByCode(String categoryCode);
}