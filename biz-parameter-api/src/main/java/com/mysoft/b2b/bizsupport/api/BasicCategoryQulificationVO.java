package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
import java.util.List;
/**
 * 资质与基础分类关联的辅助类
 * @author pengym
 *
 */
public class BasicCategoryQulificationVO implements Serializable{

	private static final long serialVersionUID = 6958252597793981902L;
	/**
	 * 基础服务分类码
	 */
	private String categoryCode;
	/**
	 * 基础服务分类名称
	 */
	private String categoryName;
	/**
	 * 基础服务分类下对应的所有资质对象的集合
	 */
	private List<Qualification> qualifications;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<Qualification> getQualifications() {
		return qualifications;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String resultString="VO{categoryCode="+categoryCode+
				",categoryName="+categoryName+
				",qualifications=[";
		for (Qualification qualification :qualifications) {
			 resultString=resultString+"{"+qualification.toString()+"},";
		}
		resultString+="]}";
		return resultString;
	}
}
