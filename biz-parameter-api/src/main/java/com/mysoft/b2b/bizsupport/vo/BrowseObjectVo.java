package com.mysoft.b2b.bizsupport.vo;


import java.io.Serializable;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value="browseRecord",noClassnameStored=true)
public class BrowseObjectVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	//用户名
	private String userid;
	
	private String clientId;
	
	//浏览时间
	private Date date;
	
	//浏览目标
	private String targetName;
	
	//浏览目标id
	private String targetId;
	
	//浏览类型
	private BrowseObjType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
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

	public BrowseObjType getType() {
		return type;
	}
	public void setType(BrowseObjType type) {
		this.type = type;
	}
}
