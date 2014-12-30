package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
import java.util.Date;
/**
 * 资质 模型类
 * @author pengym
 *
 */
public class Qualification implements Serializable{
	private static final long serialVersionUID = -5445645886747820099L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 资质码
	 */
	private String qualificationCode;
	/**
	 * 资质名称
	 */
	private String qualificationName;
	/**
	 * 状态
	 */
	private Boolean status;
	/**
	 * 是否关联基础服务
	 */
	private Boolean isRelationBasic;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
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
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getRelationBasic() {
		return isRelationBasic;
	}
	public void setRelationBasic(boolean isRelationBasic) {
		this.isRelationBasic = isRelationBasic;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Qualification{id="+id+",qualificationCode="+ qualificationCode+",qualificationName="+qualificationCode+
				",status="+status+",createTime="+createTime+
				"}";
	}

}
