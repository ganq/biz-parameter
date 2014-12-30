package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
import java.util.Date;

/**
 * 资质等级模型类
 * @author pengym
 *
 */
public class QualificationLevel implements Serializable {
	private static final long serialVersionUID = 3493275344612459197L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 资质等级名称
	 */
	private String levelName;
	/**
	 * 资质等级码
	 */
	private String levelCode;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 状态
	 */
	private Boolean status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 所属资质码
	 */
	private String qualificationCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getQualificationCode() {
		return qualificationCode;
	}
	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "QualificationLevel{id="+id+",levelName="+levelName+",levelCode="+levelCode+
				",qCode="+qualificationCode+",status="+status+",priority="+priority+
				"}";
	}
	
}
