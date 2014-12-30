/**
 * 
 */
package com.mysoft.b2b.bizsupport.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysoft.b2b.bizsupport.api.BasicCategory;
import com.mysoft.b2b.bizsupport.api.BasicCategoryNode;

/**
 * 云平台基础分类DO。
 * @author liucz
 * 
 */
public class BasicCategoryDO implements Serializable {
	private static final long serialVersionUID = 4104958896088746172L;
	// 基础分类BO
	private BasicCategory basicCategory = new BasicCategory();
	// ID
	private String categoryId;
	// 创建时间
	private Date creationTime;
	// 最后修改时间
	private Date lastModifiedTime;
	// 最后操作人
	private String operator;
	// 是否末级
	private int isLeaf;
	// 分类级别
	private int hierarchyLevel;
	
	public BasicCategory getBasicCategory() {
		return basicCategory;
	}

	public void setBasicCategory(BasicCategory basicCategory) {
		this.basicCategory = basicCategory;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(int hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

}
