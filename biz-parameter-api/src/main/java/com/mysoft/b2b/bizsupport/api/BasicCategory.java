package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

/**
 * （云平台）基础分类--数据模型
 * 
 * @author liucz
 * 
 */
public class BasicCategory implements Serializable {
	private static final long serialVersionUID = 3438877180095629773L;
	// 分类简称
	private String categoryShortname;
	// 分类名称
    private String categoryName;
    // 分类代码

	@Id
	@Indexed(value = IndexDirection.ASC, name = "code", unique = true)
    /**
     * 分类编号
     */
    private String categoryCode;
    // 父级分类代码
    private String parentCode;
	// 排序编号
	private String displayOrder;
	// 状态: 0-停用, 1-启用
	private int categoryStatus;

    //是否末级分类，此分类放方可绑定数据
    private boolean isLastLevel;
	
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

    public boolean isLastLevel() {
        return isLastLevel;
    }

    public void setLastLevel(boolean lastLevel) {
        isLastLevel = lastLevel;
    }

    public String toString() {
		return categoryCode + "-" + categoryName;
	}
}
