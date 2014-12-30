package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.bizsupport.api.StandardCategory;
import com.mysoft.b2b.bizsupport.api.StandardCategoryNode;
import com.mysoft.b2b.bizsupport.api.StandardCategoryService;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;
import com.mysoft.b2b.bizsupport.util.BizSupportUtil;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
public class StandardCategoryServiceTest extends BaseTestCase {
	private static final Logger logger = Logger.getLogger(StandardCategoryServiceTest.class);

	@Autowired
	private StandardCategoryService standardCategoryService;
	@Autowired
	private CacheManager cacheManager;

	public void printTree(List<StandardCategoryNode> tree) {
		if (tree == null) {
			return;
		}
		StandardCategoryNode rn;
		for (int i = 0; i < tree.size(); i++) {
			rn = tree.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryShortname() + "-"
					+ rn.getDisplayOrder());
			printTree(rn.getChildStandardCategoryNodes());
		}
	}

	@Test
	public void testGetCategoryRootHierarchy()  {
		List<StandardCategoryNode> rs = standardCategoryService.getCategoryRootHierarchy();
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
		StandardCategoryNode prn = standardCategoryService.getCategorySuperHierarchy("52aa71ee24b7414c9e09ada6");
		logger.info("*****testGetCategorySuperHierarchy begin*****");
		if (prn != null) {
			List<StandardCategoryNode> rs = new ArrayList<StandardCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategorySuperHierarchy end*****");
		prn = standardCategoryService.getCategorySuperHierarchy("**@#$%^&*(){}[]\\//';`.?~--==++");
		logger.info("*****testGetCategorySuperHierarchy begin*****");
		if (prn != null) {
			List<StandardCategoryNode> rs = new ArrayList<StandardCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategorySuperHierarchy end*****");

	}

	@Test
	public void testGetCategoryHierarchyByCode()  {
		StandardCategoryNode prn = standardCategoryService.getCategoryHierarchyByCode("52aa71ee24b7414c9e09ada6");
		logger.info("*****testGetCategoryHierarchyByCode begin*****");
		if (prn != null) {
			List<StandardCategoryNode> rs = new ArrayList<StandardCategoryNode>();
			rs.add(prn);
			printTree(rs);
		}
		logger.info("*****testGetCategoryHierarchyByCode end*****");
	}

	@Test
	public void testGetChildrenCategories()  {
		List<StandardCategory> Categorys = standardCategoryService.getChildrenCategories("0");

		logger.info("*****testGetChildrenCategories begin*****");
		logger.info(Categorys.size());
		StandardCategory category;
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetChildrenCategories end*****");

		Categorys = standardCategoryService.getChildrenCategories("52aa71ee24b7414c9e09ada6");

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
		StandardCategory Category = standardCategoryService.getCategoryByCode("52aa71ee24b7414c9e09ada6");

		logger.info("*****testGetCategoryByCode begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode end*****");
		
		Category = standardCategoryService.getCategoryByCode("12aa71ee24b7414c9e09b166");

		logger.info("*****testGetCategoryByCode begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode end*****");
		
		

		Category = standardCategoryService.getCategoryByCode("52aa71ee24b7414c9e09ada8");

		logger.info("*****testGetCategoryByCode Dead begin*****");
		if (Category != null) {
			logger.info(Category.getCategoryCode() + "-" + Category.getCategoryName() + "-"
					+ Category.getDisplayOrder());
		}
		logger.info("*****testGetCategoryByCode Dead end*****");
	}

	@Test
	public void testGetStandardCategoryVOList()  {
		List<StandardCategoryVO> CategoryVOs = standardCategoryService.getStandardCategoryVOList();

		logger.info("*****testGetStandardCategoryVOList begin*****");
		logger.info(CategoryVOs.size());
		StandardCategory Category;
		for (int i = 0; i < CategoryVOs.size(); i++) {
			Category = CategoryVOs.get(i);
			logger.info(Category.getParentCode() + "-" + Category.getCategoryCode() + "-"
					+ Category.getCategoryShortname() + "-" + Category.getDisplayOrder());
		}
		logger.info("*****testGetStandardCategoryVOList end*****");
	}
	
	@Test
	public void testCheckIsValid()  {
		StandardCategoryVO bcv = new StandardCategoryVO();
		bcv.setParentCode("12aa71ee24b7414c9e09b166");
		bcv.setDisplayOrder("11");

		logger.info("*****testCheckIsValid begin*****");
		logger.info(standardCategoryService.checkIsValid(bcv));
		logger.info("*****testCheckIsValid end*****");
	}
	
	@Test
	public void testSaveStandardCategory()  {
		/*StandardCategoryVO bcv = new StandardCategoryVO();
		bcv.setParentCode("0");
		bcv.setCategoryCode("52b1464b24b77b8ca8890f0c");
		bcv.setCategoryName("111");
		bcv.setCategoryUiCode("111");
		bcv.setCategoryShortname("1111x");
		bcv.setOperator("admin");
		bcv.setDisplayOrder("111");

		logger.info("*****testSaveStandardCategory begin*****");
		logger.info(standardCategoryService.saveStandardCategory(bcv));
		logger.info("*****testSaveStandardCategory end*****");*/
	}
	
	@Test
	public void testUpdateStandardCategory()  {
		/*StandardCategoryVO bcv = new StandardCategoryVO();
		bcv.setParentCode(""); //52b7a43324b7130e3a5e48af
		bcv.setCategoryCode("52b7a2e824b76b083e66eb9d");
		bcv.setCategoryName("11");
		bcv.setCategoryUiCode("11");
		bcv.setCategoryShortname("11");
		bcv.setOperator("admin");
		bcv.setDisplayOrder("11");


		logger.info("*****testUpdateStandardCategory begin*****");
		logger.info(standardCategoryService.updateStandardCategory(bcv));
		logger.info("*****testUpdateStandardCategory end*****");*/
	}
	
	@Test
	public void testUpdateStandardCategoryStatus()  {
		
		List<StandardCategoryVO> list = new ArrayList<StandardCategoryVO>();
		StandardCategoryVO bcv = new StandardCategoryVO();
		bcv.setCategoryCode("52b163ec24b737e5055a6f61");
		bcv.setCategoryStatus(BizSupportUtil.STATUS_LIVENESS);
		list.add(bcv);
		bcv = new StandardCategoryVO();
		bcv.setCategoryCode("52b15aa824b7168c6be6360f");
		bcv.setCategoryStatus(BizSupportUtil.STATUS_LIVENESS);
		list.add(bcv);
		logger.info("*****testUpdateStandardCategoryStatus begin*****");
		standardCategoryService.updateStandardCategoryStatus(list);
		logger.info("*****testUpdateStandardCategoryStatus end*****");
	}
	
	@Test
	public void testGetAllChildrenCategories()  {
		List<StandardCategory> Categorys = standardCategoryService.getAllChildrenCategories("0");

		logger.info("*****testGetAllChildrenCategories begin*****");
		logger.info(Categorys.size());
		StandardCategory category;
		for (int i = 0; i < Categorys.size(); i++) {
			category = Categorys.get(i);
			logger.info(category.getCategoryCode() + "-" + category.getCategoryName() + "-"
					+ category.getDisplayOrder());
		}
		logger.info("*****testGetAllChildrenCategories end*****");

		Categorys = standardCategoryService.getAllChildrenCategories("52b3ae5324b7dbe52c4e9a89");

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
