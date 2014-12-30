package com.mysoft.b2b.bizsupport.api;

import java.util.List;

import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformCheckedException;

/**
 * （行业）标准分类接口,提供标准分类相关服务
 * 
 * @author liucz
 * 
 */
public interface StandardCategoryService {

	/**
	 * 查询行业标准分类树接口(返回启用状态的整个行业标准分类树)
	 * 
	 * @return List<StandardCategoryNode>
	 * 
	 */
	List<StandardCategoryNode> getCategoryRootHierarchy();
	
	/**
	 * 查询上级行业标准分类树接口(返回启用状态的当前节点至根节点树)
	 * 
	 * @return StandardCategoryNode
	 * 
	 */
	StandardCategoryNode getCategorySuperHierarchy(String categoryCode);

	/**
	 * 查询行业标准分类树接口(返回启用状态的当前节点至末级节点的树)
	 * 
	 * @return StandardCategoryNode
	 * 
	 */
	StandardCategoryNode getCategoryHierarchyByCode(String categoryCode);

	
	/**
	 * 查询单个行业标准分类记录接口(返回当前行业标准分类记录[不区分状态])
	 * 
	 * @return StandardCategory
	 * 
	 */
	StandardCategory getCategoryByCode(String categoryCode);
	
	
	/**
	 * 查询子行业标准分类列表接口(返回启用状态的所有直接子级列表)
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	List<StandardCategory> getChildrenCategories(String categoryCode);
	
	/**
	 * 获取指定级次的分类列表（不含下级）。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	//List<StandardCategory> getChildrenCategoriesWithLevel(int level);
	

	/**
	 * 获取全部行业标准分类VO列表(含停用状态)。
	 * 
	 * @return List<StandardCategoryVO>
	 * 
	 */
	List<StandardCategoryVO> getStandardCategoryVOList();
	
	/**
	 * 获取启用状态的全部行业标准分类VO列表。
	 * 
	 * @return List<StandardCategoryVO>
	 * 
	 */
	List<StandardCategoryVO> getStandardCategoryVOListWithUsing();
	
	/**
	 * 检查行业标准分类录入合法性。
	 * @param StandardCategoryVO
	 * @return String
	 */
	String checkIsValid(StandardCategoryVO standardCategoryVO);	
	
	/**
	 * 保存行业标准分类。
	 * @param standardCategoryVO
	 * @return StandardCategoryVO
	 */
	StandardCategoryVO saveStandardCategory(StandardCategoryVO standardCategoryVO) throws PlatformCheckedException;
		
	/**
	 * 修改行业标准分类。
	 * @param standardCategoryVO
	 * @return StandardCategoryVO
	 */
	StandardCategoryVO updateStandardCategory(StandardCategoryVO standardCategoryVO) throws PlatformCheckedException;
	
	/**
	 * 更新行业标准分类状态。
	 * @param standardCategoryVOs
	 */
	void updateStandardCategoryStatus(List<StandardCategoryVO> standardCategoryVOs);
	
	/**
	 * 查询子分类列表接口(返回停用和启用状态的所有直接子级列表)。
	 * 
	 * @return List<StandardCategory>
	 * 
	 */
	List<StandardCategory> getAllChildrenCategories(String categoryCode);

	/**
	 * 直接从数据库查询单个行业标准分类记录接口（仅用于维护界面）。
	 * 
	 * @return BasicCategory
	 * 
	 */
	StandardCategory getCategoryFromDbByCode(String categoryCode);
	
	
	
}