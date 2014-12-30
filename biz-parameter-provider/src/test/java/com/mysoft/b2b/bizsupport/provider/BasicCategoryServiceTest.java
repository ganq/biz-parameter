package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.bizsupport.api.BasicCategory;
import com.mysoft.b2b.bizsupport.api.BasicCategoryNode;
import com.mysoft.b2b.bizsupport.api.BasicCategoryService;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;
import com.mysoft.b2b.bizsupport.util.BizSupportUtil;
import com.mysoft.b2b.bizsupport.vo.BasicCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformCheckedException;

@RunWith(SpringJUnit4ClassRunner.class)
public class BasicCategoryServiceTest extends BaseTestCase {
	private static final Logger logger = Logger.getLogger(BasicCategoryServiceTest.class);

	@Autowired
	private BasicCategoryService basicCategoryService;
	@Autowired
	private CacheManager cacheManager;

	public void printTree(List<BasicCategoryNode> tree) {
		if (tree == null) {
			return;
		}
		BasicCategoryNode rn;
		for (int i = 0; i < tree.size(); i++) {
			rn = tree.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryShortname() + "-"
					+ rn.getDisplayOrder());
			printTree(rn.getChildBasicCategoryNodes());
		}
	}

	@Test
	public void testGetCategoryRootHierarchy()  {
		List<BasicCategoryNode> rs = basicCategoryService.getCategoryRootHierarchy();
		logger.info("*****testGetCategoryRootHierarchy begin*****");
		printTree(rs);
		logger.info("*****testGetCategoryRootHierarchy end*****");
		/*
		 * Cache dicCache = cacheManager.getCache("dicCache"); Element element =
		 * new Element("key", rs); dicCache.put(element);
		 */
	}

	@Test
	public void testGetCategorySuperHierarchy()  {
		BasicCategoryNode prn = basicCategoryService.getCategorySuperHierarchy("52aa71ee24b7414c9e09ada6");
		logger.info("*****testGetCategorySuperHierarchy begin*****");
		if (prn != null) {
			List<BasicCategoryNode> rs = new ArrayList<BasicCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategorySuperHierarchy end*****");
		prn = basicCategoryService.getCategorySuperHierarchy("**@#$%^&*(){}[]\\//';`.?~--==++");
		logger.info("*****testGetCategorySuperHierarchy begin*****");
		if (prn != null) {
			List<BasicCategoryNode> rs = new ArrayList<BasicCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategorySuperHierarchy end*****");

	}

	@Test
	public void testGetCategoryHierarchyByCode()  {
		BasicCategoryNode prn = basicCategoryService.getCategoryHierarchyByCode("52aa71ee24b7414c9e09ada6");
		logger.info("*****testGetCategoryHierarchyByCode begin*****");
		if (prn != null) {
			List<BasicCategoryNode> rs = new ArrayList<BasicCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategoryHierarchyByCode end*****");
	}

	@Test
	public void testGetChildrenCategories()  {
		List<BasicCategory> Categorys = basicCategoryService.getChildrenCategories("0");

		logger.info("*****testGetChildrenCategories begin*****");
		logger.info(Categorys.size());
		BasicCategory category;
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetChildrenCategories end*****");

		Categorys = basicCategoryService.getChildrenCategories("52aa71ee24b7414c9e09ada6");

		logger.info("*****testGetChildrenCategories begin*****");
		logger.info(Categorys.size());
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetChildrenCategories end*****");

	}

	@Test
	public void testGetCategoryByCode()  {
		BasicCategory Category = basicCategoryService.getCategoryByCode("52aa71ee24b7414c9e09ada6");

		logger.info("*****testGetCategoryByCode begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode end*****");
		
		Category = basicCategoryService.getCategoryByCode("12aa71ee24b7414c9e09b166");

		logger.info("*****testGetCategoryByCode begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode end*****");
		
		

		Category = basicCategoryService.getCategoryByCode("52aa71ee24b7414c9e09ada8");

		logger.info("*****testGetCategoryByCode Dead begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode Dead end*****");
	}

	@Test
	public void testGetBasicCategoryVOList()  {
		List<BasicCategoryVO> CategoryVOs = basicCategoryService.getBasicCategoryVOList();

		logger.info("*****testGetBasicCategoryVOList begin*****");
		logger.info(CategoryVOs.size());
		BasicCategory Category;
		for (int i = 0; i < CategoryVOs.size(); i++) {
			Category = CategoryVOs.get(i);
			logger.info(Category.getParentCode() + "-" + Category.getCategoryCode() + "-"
					+ Category.getCategoryShortname() + "-" + Category.getDisplayOrder());
		}
		logger.info("*****testGetBasicCategoryVOList end*****");
	}
	
	@Test
	public void testCheckIsValid()  {
		BasicCategoryVO bcv = new BasicCategoryVO();
		bcv.setParentCode("12aa71ee24b7414c9e09b166");
		bcv.setDisplayOrder("11");

		logger.info("*****testCheckIsValid begin*****");
		logger.info(basicCategoryService.checkIsValid(bcv));
		logger.info("*****testCheckIsValid end*****");
	}
	
	@Test
	public void testUpdateBasicCategory() throws PlatformCheckedException  {
		/*BasicCategoryVO bcv = new BasicCategoryVO();
		bcv.setParentCode("52bcf19224b781cc4767f147");
		bcv.setCategoryCode("52bcf19e24b781cc4767f14b");
		bcv.setCategoryName("ddd");
		bcv.setCategoryShortname("ddd");
		bcv.setOperator("admin");
		bcv.setDisplayOrder("ddd");

		logger.info("*****testUpdateBasicCategory begin*****");
		logger.info(basicCategoryService.updateBasicCategory(bcv));
		logger.info("*****testUpdateBasicCategory end*****");
		List<BasicCategory> list = basicCategoryService.getAllChildrenCategories("52bcf19224b781cc4767f147");
		BasicCategory rn;
		for (int i = 0; i < list.size(); i++) {
			rn = list.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryShortname() + "-"
					+ rn.getDisplayOrder());
		}
		
		bcv = new BasicCategoryVO();
		bcv.setParentCode("52bcf19724b781cc4767f149");
		bcv.setCategoryCode("52bcf19e24b781cc4767f14b");
		bcv.setCategoryName("ddd");
		bcv.setCategoryShortname("ddd");
		bcv.setOperator("admin");
		bcv.setDisplayOrder("ddd");

		logger.info("*****testUpdateBasicCategory begin*****");
		logger.info(basicCategoryService.updateBasicCategory(bcv));
		logger.info("*****testUpdateBasicCategory end*****");
		list = basicCategoryService.getAllChildrenCategories("52bcf19224b781cc4767f147");
		for (int i = 0; i < list.size(); i++) {
			rn = list.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryShortname() + "-"
					+ rn.getDisplayOrder());
		}
		
		list = basicCategoryService.getAllChildrenCategories("52bcf19724b781cc4767f149");
		for (int i = 0; i < list.size(); i++) {
			rn = list.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryShortname() + "-"
					+ rn.getDisplayOrder());
		}*/
	}
	
	@Test
	public void testUpdateBasicCategoryStatus()  {
		
		List<BasicCategoryVO> list = new ArrayList<BasicCategoryVO>();
		BasicCategoryVO bcv = new BasicCategoryVO();
		bcv.setCategoryCode("52bbd54224b776223b6ebb9e");
		bcv.setCategoryStatus(BizSupportUtil.STATUS_LIVENESS);
		list.add(bcv);
		bcv = new BasicCategoryVO();
		bcv.setCategoryCode("52bbd53b24b776223b6ebb9c");
		bcv.setCategoryStatus(BizSupportUtil.STATUS_LIVENESS);
		list.add(bcv);
		logger.info("*****testUpdateBasicCategoryStatus begin*****");
		basicCategoryService.updateBasicCategoryStatus(list);
		logger.info("*****testUpdateBasicCategoryStatus end*****");
	}
	
	@Test
	public void testGetAllChildrenCategories()  {
		List<BasicCategory> Categorys = basicCategoryService.getAllChildrenCategories("0");

		logger.info("*****testGetAllChildrenCategories begin*****");
		logger.info(Categorys.size());
		BasicCategory category;
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetAllChildrenCategories end*****");

		Categorys = basicCategoryService.getAllChildrenCategories("52b3ae5324b7dbe52c4e9a89");

		logger.info("*****testGetAllChildrenCategories begin*****");
		logger.info(Categorys.size());
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetAllChildrenCategories end*****");

	}
	
	
	

}
