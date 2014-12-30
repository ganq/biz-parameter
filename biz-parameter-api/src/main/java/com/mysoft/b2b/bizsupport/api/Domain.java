package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Domain implements Serializable {
	/**
	 * 主键id
	 */
	private String uid;
	/**
	 * 公司id
	 */
	private String companyId;
	/**
	 * 域名前缀
	 */
	private String prefix;
	/**
	 * 域名全名
	 */
	private String domain;
	/**
	 * 状态--> 1：预留   2：使用中等等   3：无特殊意义
	 */
	private Integer status;
	/**
	 * 域名类型-->1：开发商  2:供应商 3：平台域名
	 */
	private Integer type;
	/**
	 * 来源类型--> 1:平台创建  2:会员申请 
	 */
	private Integer origin;
	/**
	 *备注信息
	 */
	private String remark;
	
	
	
	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



	public String getPrefix() {
		return prefix;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}



	public String getDomain() {
		return domain;
	}



	public void setDomain(String domain) {
		this.domain = domain;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getOrigin() {
		return origin;
	}



	public void setOrigin(Integer origin) {
		this.origin = origin;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public String toString() {
		return "Domain [uid=" + uid + ", companyId=" + companyId + ", prefix="
				+ prefix + ", domain=" + domain + ", status=" + status
				+ ", type=" + type + ", origin=" + origin + ", remark="
				+ remark + "]";
	}
	
	
}
