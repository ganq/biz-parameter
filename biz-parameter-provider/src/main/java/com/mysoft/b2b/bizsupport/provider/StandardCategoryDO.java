/**
 * 
 */
package com.mysoft.b2b.bizsupport.provider;

import java.io.Serializable;
import java.util.Date;

import com.mysoft.b2b.bizsupport.api.StandardCategory;

/**
 * @author liucz
 * 
 */
public class StandardCategoryDO implements Serializable {
	private static final long serialVersionUID = -5385568303311946269L;
	// 行业标准分类BO
	private StandardCategory standardCategory = new StandardCategory();
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

	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(int hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public StandardCategory getStandardCategory() {
		return standardCategory;
	}

	public void setStandardCategory(StandardCategory standardCategory) {
		this.standardCategory = standardCategory;
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

}
