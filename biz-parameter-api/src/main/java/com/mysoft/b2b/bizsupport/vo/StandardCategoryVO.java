/**
 * 
 */
package com.mysoft.b2b.bizsupport.vo;

import java.io.Serializable;
import java.util.Date;

import com.mysoft.b2b.bizsupport.api.StandardCategory;

/**
 * @author liucz
 * 
 */
public class StandardCategoryVO extends StandardCategory implements Serializable {
	private static final long serialVersionUID = 3844797384327023714L;
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
