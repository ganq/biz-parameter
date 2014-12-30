package com.mysoft.b2b.bizsupport.api;

import java.util.List;
import java.util.Map;

/**
 * 资质等级服务接口
 * @author pengym
 *
 */
public interface QualificationLevelService {
	/**
	 * 查询所有的资质等级
	 * @return
	 */
	public List<QualificationLevel> getQualificationLevels(Map<String, Object> param);
	
	/**
	 * 查询所有的资质等级的总数量
	 * @return
	 */
	public int getQualificationLevelsCount(Map<String, Object> param);
	
	/**
	 * 根据id获取资质等级对象
	 * @param id
	 * @return
	 */
	public QualificationLevel getQualificationLevelById(String id);
	
	/**
	 * 根据资质码查询所有的已启用的资质等级对象
	 * @param code
	 * @return
	 */
	public List<QualificationLevel> getLevelsByQualificationCode(String code);
	
	/**
	 * 根据资质码查询所有资质等级（包括启用、停用的两种资质对象）
	 * @param qualificationCode
	 * @return
	 */
	public List<QualificationLevel> getLevelsIncludeUnusedByQCode(String qualificationCode);
	
	/**
	 * 根据资质码查询所有的资质等级对象的数量
	 * @return
	 */
	public int getLevelsByQualificationCodeCount(String code);
	
	/***
	 * 根据资质等级码查询等级对象
	 * @param code
	 * @return
	 */
	public QualificationLevel getQualificationLevelByCode(String code);
	
	/**
	 * 新增资质等级对象
	 * @param qLevel
	 * @return
	 */
	public boolean addQualificationLevel(QualificationLevel qLevel);
	
	/**
	 * 修改资质等级对象
	 * @param qLevel
	 * @return
	 */
	public boolean updateQualificationLevel(QualificationLevel qLevel);
	
	/**
	 * 比较两个资质等级优先级
	 * @param levelCode1
	 * @param levelCode2
	 * @return 1： levelCode1的优先级  > levelCode2的优先级
	 * 		   0: levelCode1的优先级  = levelCode2的优先级
	 * 		  -1：levelCode1的优先级  < levelCode2的优先级
	 * 		   
	 */
	public int compareQualificationLevel(String levelCode1, String levelCode2);
	
}
