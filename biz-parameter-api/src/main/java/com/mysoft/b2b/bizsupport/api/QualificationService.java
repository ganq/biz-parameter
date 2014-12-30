package com.mysoft.b2b.bizsupport.api;

import java.util.List;
import java.util.Map;
/**
 * 资质类型服务接口
 * @author pengym
 *
 */
public interface QualificationService {
	/**
	 * 查询所有的资质信息
	 * @param param Map 条件内容:"startIndex"、"pageSize"、Qualification对象字段属性
	 * 参数可为null
	 * @return
	 */
	public List<Qualification> getQualifications(Map<String, Object> param);
	/**
	 *  查询所有的资质记录数目
	 * @return
	 */
	public int getQualificationsCount(Map<String, Object> param);
	/**
	 * 根据资质码查询资质信息
	 * @param code
	 * @return
	 */
	public Qualification getQualificationByCode(String code);
	
	/**
	 * 根据资质id获取资质对象
	 * @param id
	 * @return
	 */
	public Qualification getQualificationById(String id);
	/**
	 * 根据基础分类服务码查询资质对象
	 * @param code
	 * @return
	 */
	public List<Qualification> getQualificationsByCategoryCode(String code);
	
	/**
	 * 根据基础分类集合码查询资质与基础分类的辅助对象集合
	 * @param categoryCodes
	 * @return
	 */
	public List<BasicCategoryQulificationVO> getQualificationsByCategoryCode(List<String> categoryCodes);
	
	/**
	 *根据基础分类服务码查询资质对象的数量
	 *@param code
	 * @return
	 */
	public int getQualificationsByCategoryCodeCount(String code);
	/**
	 *  新增资质
	 * @param qualification
	 * @return
	 */
	public boolean addQualification(Qualification qualification);
	/**
	 * 更新资质
	 * @param qualification
	 * @return
	 */
	public boolean updateQualification(Qualification qualification);
	/**
	 * 批量新增资质与基础分类关联记录
	 * @param qbr
	 * @return
	 */
	public boolean addQualificationBasicRelationBatch(List<QualificationBasicRelation> qbr);
	/**
	 * 新增资质与基础分类关联记录
	 * @param qbr
	 * @return
	 */
	public boolean addQualificationBasicRelation(QualificationBasicRelation qbr);
	/**
	 * 删除资质与基础分类服务之间的关联
	 * @param id 关联记录主键id
	 * @return
	 */
	public boolean deleteQualificationBasicRelationById(String id);
	/**
	 * 根据资质码删除资质与基础分类服务之间的关联关系
	 * @param qualificationCode 资质码
	 * @return
	 */
	public boolean deleteQualificationBasicRelationByQCode(String qualificationCode);
	/**
	 * 根据基础分类码删除资质与基础分类的关联记录
	 * @param categoryCode
	 * @return
	 */
	public boolean deleteQualificationBasicRelationByCategoryCode(String categoryCode);
	
	/**
	 * 根据资质码查询资质与基础分类关联记录
	 * @param qualificationCode
	 * @return
	 */
	public List<QualificationBasicRelation> getQualificationBasicRelationByQCode(String qualificationCode);
}
