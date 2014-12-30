/**
 * 
 */
package com.mysoft.b2b.bizsupport.vo;

import java.io.Serializable;
import java.util.Date;

import com.mysoft.b2b.bizsupport.api.BasicCategory;

/**
 * 云平台基础分类VO。
 * @author liucz
 * 
 */
public class BasicCategoryVO extends BasicCategory implements Serializable {
	private static final long serialVersionUID = 6165289100697434750L;
	// 创建时间
	private Date creationTime;
	// 最后修改时间
	private Date lastModifiedTime;
	// 最后操作人
	private String operator;

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
