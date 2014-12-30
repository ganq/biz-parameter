package com.mysoft.b2b.bizsupport.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mysoft.b2b.bizsupport.provider.BasicCategoryDO;
import com.mysoft.b2b.bizsupport.vo.BasicCategoryVO;

@Component("basicCategoryMapper")
public interface BasicCategoryMapper {
	List<BasicCategoryDO> getBasicCategoryList();
	
	void saveBasicCategory(BasicCategoryDO basicCategoryDO);
	
	List<BasicCategoryDO> getChildrenBasicCategoryList(String categoryCode);
	
	void updateBasicCategory(BasicCategoryDO basicCategoryDO);
	
	void updateBasicCategoryStatus(Map map);
	
	void deleteForestRelationship(String categoryCode);
	
	void saveForestRelationship(Map map);
	
	BasicCategoryDO getBasicCategoryByCode(String categoryCode);
	
	int getChildrenCount(String categoryCode);
}
