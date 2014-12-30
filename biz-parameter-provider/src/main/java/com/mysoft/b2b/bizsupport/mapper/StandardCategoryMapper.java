/**
 * 
 */
package com.mysoft.b2b.bizsupport.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mysoft.b2b.bizsupport.provider.StandardCategoryDO;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;

/**
 * @author liucz
 *
 */

@Component("standardCategoryMapper")
public interface StandardCategoryMapper {
	List<StandardCategoryDO> getStandardCategoryList();
	
	void saveStandardCategory(StandardCategoryDO standardCategoryDO);
	
	List<StandardCategoryDO> getChildrenStandardCategoryList(String categoryCode);
	
	void updateStandardCategory(StandardCategoryDO standardCategoryDO);
	
	void updateStandardCategoryStatus(Map map);
	
	List<StandardCategoryDO> getStandardCategoryByCategoryUiCode(String categoryUiCode);

	List<StandardCategoryVO> getForestRelationshipByCode(String categoryCode);
	
	StandardCategoryDO getStandardCategoryByCode(String categoryCode);
	
	int getChildrenCount(String categoryCode);
	
}
