/**
 * 
 */
package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;

import com.mysoft.b2b.bizsupport.api.BidOperationCategory;
import com.mysoft.b2b.bizsupport.api.SupplierOperationCategory;

/**
 * @author liucz
 * 
 */
public class OperationCategoryServiceTestHelper {

	/**
	 * 
	 */
	public OperationCategoryServiceTestHelper() {
		// TODO Auto-generated constructor stub
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<BidOperationCategory> getNewBidOperationCategories() {
		int i = 32;
		int j = i;
		List<BidOperationCategory> result = new ArrayList<BidOperationCategory>();
		BidOperationCategory bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告服务类");
		bo.setCategoryShortname("101");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("01");
		bo.setLastLevel(false);
		bo.setParentCode("0");
		List list = new ArrayList();
		list.add("101");
		list.add("101");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告勘察测绘");
		bo.setCategoryShortname("10101");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("01");
		bo.setLastLevel(false);
		bo.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10101");
		list.add("10101");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("工程地质勘察");
		bo.setCategoryShortname("1010102");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("02");
		bo.setLastLevel(true);
		bo.setParentCode(String.valueOf(i + 2));
		list = new ArrayList();
		list.add("1010102");
		list.add("1010102");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("勘察外审");
		bo.setCategoryShortname("1010101");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("01");
		bo.setLastLevel(true);
		bo.setParentCode(String.valueOf(i + 2));
		list = new ArrayList();
		list.add("1010101");
		list.add("1010101");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告设计");
		bo.setCategoryShortname("10102");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("02");
		bo.setLastLevel(true);
		bo.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10102");
		list.add("10102");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告停用");
		bo.setCategoryShortname("10103");
		bo.setCategoryStatus(0);
		bo.setDisplayOrder("03");
		bo.setLastLevel(true);
		bo.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10102");
		list.add("10102");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		i = j;
		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告物资类");
		bo.setCategoryShortname("103");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("03");
		bo.setLastLevel(false);
		bo.setParentCode("0");
		list = new ArrayList();
		list.add("103");
		list.add("103");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("承重结构");
		bo.setCategoryShortname("10301");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("01");
		bo.setLastLevel(false);
		bo.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10301");
		list.add("10301");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);
		j++;

		bo = new BidOperationCategory();
		bo.setBindUrl("http://bidoc.b2bdev.com");
		// bo.setCategoryCode("000");
		bo.setCategoryName("预告工程类");
		bo.setCategoryShortname("102");
		bo.setCategoryStatus(1);
		bo.setDisplayOrder("02");
		bo.setLastLevel(true);
		bo.setParentCode("0");
		list = new ArrayList();
		list.add("102");
		list.add("102");
		bo.setBindBasicCategoryIds(list);
		result.add(bo);

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<SupplierOperationCategory> getNewSupplierOperationCategories() {
		int i = 29;
		int j = i;
		List<SupplierOperationCategory> result = new ArrayList<SupplierOperationCategory>();
		SupplierOperationCategory soc = new SupplierOperationCategory();

		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商服务类");
		soc.setCategoryShortname("101");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("01");
		soc.setLastLevel(false);
		soc.setParentCode("0");
		List list = new ArrayList();
		list.add("101");
		list.add("101");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商营销代理");
		soc.setCategoryShortname("10101");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("01");
		soc.setLastLevel(false);
		soc.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10101");
		list.add("10101");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("广告策划");
		soc.setCategoryShortname("1010102");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("02");
		soc.setLastLevel(true);
		soc.setParentCode(String.valueOf(i + 2));
		list = new ArrayList();
		list.add("1010102");
		list.add("1010102");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("广告印刷");
		soc.setCategoryShortname("1010101");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("01");
		soc.setLastLevel(true);
		soc.setParentCode(String.valueOf(i + 2));
		list = new ArrayList();
		list.add("1010101");
		list.add("1010101");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商造价咨询");
		soc.setCategoryShortname("10102");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("02");
		soc.setLastLevel(true);
		soc.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10102");
		list.add("10102");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商停用");
		soc.setCategoryShortname("10103");
		soc.setCategoryStatus(0);
		soc.setDisplayOrder("03");
		soc.setLastLevel(true);
		soc.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10102");
		list.add("10102");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		i = j;
		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商物资类");
		soc.setCategoryShortname("103");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("03");
		soc.setLastLevel(false);
		soc.setParentCode("0");
		list = new ArrayList();
		list.add("103");
		list.add("103");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("建筑门窗");
		soc.setCategoryShortname("10301");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("01");
		soc.setLastLevel(true);
		soc.setParentCode(String.valueOf(i + 1));
		list = new ArrayList();
		list.add("10301");
		list.add("10301");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);
		j++;

		soc = new SupplierOperationCategory();
		soc.setBindUrl("http://supplieroc.b2bdev.com");
		// bo.setCategoryCode("000");
		soc.setCategoryName("供应商工程类");
		soc.setCategoryShortname("102");
		soc.setCategoryStatus(1);
		soc.setDisplayOrder("02");
		soc.setLastLevel(true);
		soc.setParentCode("0");
		list = new ArrayList();
		list.add("102");
		list.add("102");
		soc.setBindBasicCategoryIds(list);
		result.add(soc);

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
