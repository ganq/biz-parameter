package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
/**
 * 域对象 辅助类
 * @author pengym
 *
 */
public class DomainVO extends Domain implements Serializable{

	private static final long serialVersionUID = -4747058300446479380L;
	
	private Integer pageSize = 20;
	
	//当前页码
	private Integer pageIndex = 0;
	
	//sql起始下标
	private Integer offset;
	
	//排序字段
	private String sortField;
	
	//排序类型
	private String sortType;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		if(this.pageIndex !=null && this.pageSize !=null)
			this.offset = this.pageIndex * this.pageSize;
	}

	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
}
