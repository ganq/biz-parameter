package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;

/**
 * SEO 实体类
 * @author pengym
 *
 */
public class SEOModel implements Serializable{
	private static final long serialVersionUID = 8605429765850550384L;
	/**
	 * 目录名称
	 */
	private String directoryName;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 关键字
	 */
	private String keywords;
	
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public String toString() {
		return "SEOModel [directoryName=" + directoryName + ", title=" + title
				+ ", description=" + description + ", keywords=" + keywords
				+ "]";
	}
	
	
	
}
