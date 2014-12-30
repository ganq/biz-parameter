package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.bizsupport.api.BasicCategory;
import com.mysoft.b2b.bizsupport.api.BasicCategoryNode;
import com.mysoft.b2b.bizsupport.api.BidOperationCategory;
import com.mysoft.b2b.bizsupport.api.OperationCategoryNode;
import com.mysoft.b2b.bizsupport.api.OperationCategoryService;
import com.mysoft.b2b.bizsupport.api.OperationCategoryService.DataType;
import com.mysoft.b2b.bizsupport.api.SEOModel;
import com.mysoft.b2b.bizsupport.api.SupplierOperationCategory;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
public class OperationCategoryServiceTest extends BaseTestCase {
	private static final Logger logger = Logger.getLogger(OperationCategoryServiceTest.class);

	@Autowired
	private OperationCategoryService operationCategoryService;

	// @Autowired
	// private CacheManager cacheManager;

	public void printTree(List<OperationCategoryNode> tree) {
		if (tree == null) {
			return;
		}
		OperationCategoryNode rn;
		for (int i = 0; i < tree.size(); i++) {
			rn = tree.get(i);
			logger.info(rn.getParentCode() + "-" + rn.getCategoryCode() + "-" + rn.getCategoryName() + "-"
					+ rn.getCategoryShortname() + "-" + rn.getDisplayOrder());
			printTree(rn.getChildrenCategoryNodes());
		}
	}

	public void printBc(BasicCategory bc) {
		if (bc == null) {
			logger.info("bc=null");
			return;
		}
		logger.info(bc.getParentCode() + "-" + bc.getCategoryCode() + "-" + bc.getCategoryName() + "-"
				+ bc.getCategoryShortname() + "-" + bc.getDisplayOrder());
	}

	public void printBcList(List<BasicCategory> list) {
		if (list == null) {
			logger.info("list=null");
			return;
		}
		logger.info("size=" + list.size());
		BasicCategory bc;
		for (int i = 0; i < list.size(); i++) {
			bc = list.get(i);
			logger.info(bc.getParentCode() + "-" + bc.getCategoryCode() + "-" + bc.getCategoryName() + "-"
					+ bc.getCategoryShortname() + "-" + bc.getDisplayOrder());
		}
	}

	@Test
	public void testSaveBidOperationCategory() {
	
		BidOperationCategory bo = new BidOperationCategory();
		bo.setBindUrl("http://b2bdev.com");
		bo.setCategoryCode("testXXX");
		bo.setCategoryName("66name test");
		bo.setCategoryShortname("66categoryShortname0 test");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("6004");
		bo.setLastLevel(true);
		bo.setParentCode("0");
	   // operationCategoryService.saveBidOperationCategory(bo);
	}

	@Test
	public void testUpdateBidOperationCategory() {
		BidOperationCategory bo = new BidOperationCategory();
		bo.setBindUrl("http://b2bdev.com---update");
		bo.setCategoryCode("281");
		bo.setCategoryName("66name update");
		bo.setCategoryShortname("66categoryShortname0 update");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("6004");
		bo.setLastLevel(true);
		bo.setParentCode("0");
		List list = new ArrayList();
		list.add("600004");
		list.add("611114");
		//
		SEOModel seoModel = new SEOModel();
		seoModel.setDirectoryName("XXXname");
		seoModel.setTitle("XXtitle");
		seoModel.setKeywords("XXkeywords");
		seoModel.setDescription("XXXdesc");
		bo.setSeoModel(seoModel);
		bo.setBindBasicCategoryIds(list);
		//
	    //operationCategoryService.updateBidOperationCategory(bo);
	}

	@Test
	public void testDeleteBidOperationCategory() {
		// operationCategoryService.deleteBidOperationCategory("7");
	}

	@Test
	public void testGetBidOperationCategoryList() {
		logger.info("*****testGetBidOperationCategoryList begin*****");
		List<? extends BasicCategory> list = operationCategoryService.getBidOperationCategoryList();
		printBcList((List<BasicCategory>)list);
		logger.info("*****testGetBidOperationCategoryList end*****");
	}

	@Test
	public void testSaveSupplierOperationCategory() {
		/*
		 * List<SupplierOperationCategory> list =
		 * OperationCategoryServiceTestHelper
		 * .getNewSupplierOperationCategories(); for (int i = 0; i <
		 * list.size(); i++) {
		 * operationCategoryService.saveSupplierOperationCategory(list.get(i));
		 * }
		 */
	}

	@Test
	public void testUpdateSupplierOperationCategory() {
		SupplierOperationCategory bo = new SupplierOperationCategory();
		bo.setBindUrl("http://b2bdev.com");
		bo.setCategoryCode("6");
		bo.setCategoryName("6name 6");
		bo.setCategoryShortname("6categoryShortname00 6");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("6006");
		bo.setLastLevel(true);
		bo.setParentCode("0");
		List list = new ArrayList();
		list.add("600006");
		list.add("611116");
		bo.setBindBasicCategoryIds(list);
		// operationCategoryService.updateSupplierOperationCategory(bo);
	}

	@Test
	public void testDeleteSupplierOperationCategory() {
		// operationCategoryService.deleteSupplierOperationCategory("7");
	}

	@Test
	public void testGetSupplierOperationCategoryList() {
		logger.info("*****testGetSupplierOperationCategoryList begin*****");
		List<? extends BasicCategory> list = operationCategoryService.getSupplierOperationCategoryList();
		printBcList((List<BasicCategory>)list);
		logger.info("*****testGetSupplierOperationCategoryList end*****");
	}

	@Test
	public void testGetCategoryRootHierarchy() {
		logger.info("*****testGetCategoryRootHierarchy 招标预告运营分类 begin*****");
		List<OperationCategoryNode> list = operationCategoryService.getCategoryRootHierarchy(DataType.BID);
		printTree(list);
		logger.info("*****testGetCategoryRootHierarchy 招标预告运营分类 end*****");
		logger.info("*****testGetCategoryRootHierarchy 供应商运营分类 begin*****");
		list = operationCategoryService.getCategoryRootHierarchy(DataType.SUPPLIER);
		printTree(list);
		logger.info("*****testGetCategoryRootHierarchy 供应商运营分类 end*****");
	}

	@Test
	public void testGetCategorySuperNodes() {
		logger.info("*****testGetCategorySuperNodes 招标预告运营分类 begin*****");
		List<BasicCategory> list = operationCategoryService.getCategorySuperNodes(DataType.BID, "27");
		printBcList(list);
		list = operationCategoryService.getCategorySuperNodes(DataType.BID, "25");
		printBcList(list);
		logger.info("*****testGetCategorySuperNodes 招标预告运营分类 end*****");

		logger.info("*****testGetCategorySuperNodes 供应商运营分类 begin*****");
		list = operationCategoryService.getCategorySuperNodes(DataType.SUPPLIER, "24");
		printBcList(list);
		list = operationCategoryService.getCategorySuperNodes(DataType.SUPPLIER, "22");
		printBcList(list);
		logger.info("*****testGetCategorySuperNodes 供应商运营分类 end*****");
	}

	//@Test
	public void testGetCategoryHierarchyByCode() {
		logger.info("*****testGetCategoryHierarchyByCode 招标预告运营分类 begin*****");
		OperationCategoryNode node = operationCategoryService.getCategoryHierarchyByCode(DataType.BID, "27");
		List<OperationCategoryNode> list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("********");
		node = operationCategoryService.getCategoryHierarchyByCode(DataType.BID, "25");
		list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("********");
		node = operationCategoryService.getCategoryHierarchyByCode(DataType.BID, "24");
		list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("*****testGetCategoryHierarchyByCode 招标预告运营分类 end*****");
		logger.info("*****testGetCategoryHierarchyByCode 供应商运营分类 begin*****");
		node = operationCategoryService.getCategoryHierarchyByCode(DataType.SUPPLIER, "24");
		list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("********");
		node = operationCategoryService.getCategoryHierarchyByCode(DataType.SUPPLIER, "22");
		list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("********");
		node = operationCategoryService.getCategoryHierarchyByCode(DataType.SUPPLIER, "21");
		list = new ArrayList<OperationCategoryNode>();
		list.add(node);
		printTree(list);
		logger.info("*****testGetCategoryHierarchyByCode 供应商运营分类 end*****");
	}

	@Test
	public void testGetCategoryByCode() {
		logger.info("*****testGetCategoryByCode 招标预告运营分类 begin*****");
		BasicCategory node = operationCategoryService.getCategoryByCode(DataType.BID, "27");
		printBc(node);
		node = operationCategoryService.getCategoryByCode(DataType.BID, "25");
		printBc(node);
		node = operationCategoryService.getCategoryByCode(DataType.BID, "29");
		printBc(node);
		logger.info("*****testGetCategoryByCode 招标预告运营分类 end*****");
		logger.info("*****testGetCategoryByCode 供应商运营分类 begin*****");
		node = operationCategoryService.getCategoryByCode(DataType.SUPPLIER, "24");
		printBc(node);
		node = operationCategoryService.getCategoryByCode(DataType.SUPPLIER, "22");
		printBc(node);
		node = operationCategoryService.getCategoryByCode(DataType.SUPPLIER, "26");
		printBc(node);
		logger.info("*****testGetCategoryByCode 供应商运营分类 end*****");
	}

	@Test
	public void testGetCategoriesByCodes() {
		List<String> categoryCodes = new ArrayList<String>();
		logger.info("*****testGetCategoriesByCodes 招标预告运营分类 begin*****");
		categoryCodes.add("27");
		categoryCodes.add("25");
		categoryCodes.add("29");
		List<BasicCategory> list = operationCategoryService.getCategoriesByCodes(DataType.BID, categoryCodes);
		printBcList(list);	
		logger.info("*****testGetCategoriesByCodes 招标预告运营分类 end*****");
		logger.info("*****testGetCategoriesByCodes 供应商运营分类 begin*****");
		categoryCodes = new ArrayList<String>();
		categoryCodes.add("24");
		categoryCodes.add("22");
		categoryCodes.add("26");
		list = operationCategoryService.getCategoriesByCodes(DataType.SUPPLIER, categoryCodes);
		printBcList(list);
		logger.info("*****testGetCategoriesByCodes 供应商运营分类 end*****");
	}
	
	@Test
	public void testGetChildrenCategories(){
		logger.info("*****testGetChildrenCategories 招标预告运营分类 begin*****");
		List<BasicCategory> list = operationCategoryService.getChildrenCategories(DataType.BID, "27");
		printBcList(list);
		list = operationCategoryService.getChildrenCategories(DataType.BID, "25");
		printBcList(list);
		list = operationCategoryService.getChildrenCategories(DataType.BID, "24");
		printBcList(list);
		logger.info("*****testGetChildrenCategories 招标预告运营分类 end*****");

		logger.info("*****testGetChildrenCategories 供应商运营分类 begin*****");
		list = operationCategoryService.getChildrenCategories(DataType.SUPPLIER, "24");
		printBcList(list);
		list = operationCategoryService.getChildrenCategories(DataType.SUPPLIER, "22");
		printBcList(list);
		list = operationCategoryService.getChildrenCategories(DataType.SUPPLIER, "21");
		printBcList(list);
		logger.info("*****testGetChildrenCategories 供应商运营分类 end*****");
	}
	
	@Test
	public void testGetRootSubCategories(){
		logger.info("*****testGetRootSubCategories 招标预告运营分类 begin*****");
		List<BasicCategory> list = operationCategoryService.getRootSubCategories(DataType.BID);
		printBcList(list);
		logger.info("*****testGetRootSubCategories 招标预告运营分类 end*****");

		logger.info("*****testGetRootSubCategories 供应商运营分类 begin*****");
		list = operationCategoryService.getRootSubCategories(DataType.SUPPLIER);
		printBcList(list);
		logger.info("*****testGetRootSubCategories 供应商运营分类 end*****");
	}
	
	@Test
	public void testGetAllSubCategories(){

		logger.info("*****testGetAllSubCategories 招标预告运营分类 begin*****");
		List<BasicCategory> list = operationCategoryService.getAllSubCategories(DataType.BID, "27");
		printBcList(list);
		logger.info("********");
		list = operationCategoryService.getAllSubCategories(DataType.BID, "25");
		printBcList(list);
		logger.info("********");
		list = operationCategoryService.getAllSubCategories(DataType.BID, "24");
		printBcList(list);
		logger.info("*****testGetAllSubCategories 招标预告运营分类 end*****");

		logger.info("*****testGetAllSubCategories 供应商运营分类 begin*****");
		list = operationCategoryService.getAllSubCategories(DataType.SUPPLIER, "24");
		printBcList(list);
		logger.info("********");
		list = operationCategoryService.getAllSubCategories(DataType.SUPPLIER, "22");
		printBcList(list);
		logger.info("********");
		list = operationCategoryService.getAllSubCategories(DataType.SUPPLIER, "21");
		printBcList(list);
		logger.info("*****testGetAllSubCategories 供应商运营分类 end*****");
	}
	
	@Test
	public void testGetCategoryByDirectoryName(){
		logger.info("*****testGetCategoryByDirectoryName start*****");
		BidOperationCategory category = (BidOperationCategory)operationCategoryService.getCategoryBydirectoryName(DataType.BID, "gongchengupdate2");
		logger.info(category);
		logger.info("*****testGetCategoryByDirectoryName end*****");
	}
}
