package com.mysoft.b2b.bizsupport.vo;

import java.io.Serializable;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "pageView", noClassnameStored = true)
public class PageViewVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	// 用户名
	private String userid;

	// 客户端id
	private String clientId;

	// 页面对象（招标，供应商等）id
	private String pageId;
	
	// 页面类型：招标 、供应商
	private String pvType;

	// 当前浏览时间
	private Date date;

	// 浏览者ip
	private String ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPvType() {
		return pvType;
	}

	public void setPvType(String pvType) {
		this.pvType = pvType;
	}

}
