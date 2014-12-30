package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
/**
 * 资质与基础分类服务关联模型类
 * @author pengym
 *
 */
public class QualificationBasicRelation implements Serializable{

	private static final long serialVersionUID = 2406775613593500998L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 资质码
	 */
	private String qualificationCode;
	/**
	 * 基础分类服务码
	 */
	private String categoryCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQualificationCode() {
		return qualificationCode;
	}
	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "QualificationBasicRelation{id="+id+",qualificationCode="+qualificationCode+
				",categoryCode="+categoryCode+"}";
	}

}
