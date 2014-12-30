package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;

/**
 * （行业）标准分类--数据模型
 * 
 * @author liucz
 * 
 */
public class StandardCategory implements Serializable {
	private static final long serialVersionUID = -5358945898071058555L;
	// 分类简称
	private String categoryShortname;
	// 分类名称
	private String categoryName;
	// 分类代码
	private String categoryUiCode;
	// 分类机器码
	private String categoryCode;
	// 父级机器代码
	private String parentCode;
	// 排序编号
	private String displayOrder;
	// 状态: 0-停用, 1-启用
	private int categoryStatus;
	
	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCategoryShortname() {
		return categoryShortname;
	}

	public void setCategoryShortname(String categoryShortname) {
		this.categoryShortname = categoryShortname;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String toString() {
		return categoryCode + "-" + categoryShortname;
	}

	public String getCategoryUiCode() {
		return categoryUiCode;
	}

	public void setCategoryUiCode(String categoryUiCode) {
		this.categoryUiCode = categoryUiCode;
	}
}
