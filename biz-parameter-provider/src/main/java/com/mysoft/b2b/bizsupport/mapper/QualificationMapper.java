package com.mysoft.b2b.bizsupport.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mysoft.b2b.bizsupport.api.BasicCategoryQulificationVO;
import com.mysoft.b2b.bizsupport.api.Qualification;
import com.mysoft.b2b.bizsupport.api.QualificationBasicRelation;

@Component("qualificationMapper")
public interface QualificationMapper {
	/**
	 * 获取所有资质类型对象
	 * @return
	 */
	public List<Qualification> getQualifications(Map<String, Object> param);
	/**
	 *  查询所有的资质记录数目
	 * @return
	 */
	public int getQualificationsCount(Map<String, Object> param);
	/**
	 * 根据资质码查询资质对象
	 * @return
	 */
	public Qualification getQualificationByCode(String code);
	/**
	 * 根据基础分类服务码查询资质对象
	 * @param code
	 * @return
	 */
	public List<Qualification> getQualificationsByCategoryCode(String code);
	/**
	 *根据基础分类服务码查询资质对象的数量
	 * @return
	 */
	public int getQualificationsByCategoryCodeCount(String code);
	
	/**
	 * 
	 * @param qualification
	 * @return
	 */
	public int addQualification(Qualification qualification);
	/**
	 * 更新资质
	 * @param qualification
	 * @return
	 */
	public int updateQualification(Qualification qualification);
	/**
	 * 批量新增资质与基础分类关联记录
	 * @param qbr
	 * @return
	 */
	public int addQualificationBasicRelationBatch(List<QualificationBasicRelation> qbr);
	/**
	 * 新增资质与基础分类关联记录
	 * @param qbr
	 * @return
	 */
	public int addQualificationBasicRelation(QualificationBasicRelation qbr);
	
	public Qualification getQualificationById(String id);
	
	public int deleteQualificationBasicRelationById(String id);
	/**
	 * 根据资质码删除资质与基础分类服务之间的关联关系
	 * @param qualificationCode 资质码
	 * @return
	 */
	public int deleteQualificationBasicRelationByQCode(String qualificationCode);
	
	public int deleteQualificationBasicRelationByCategoryCode(String categoryCode);
	
	/**
	 * 根据资质码查询资质与基础分类关联记录
	 * @param qualificationCode
	 * @return
	 */
	public List<QualificationBasicRelation> getQualificationBasicRelationByQCode(String qualificationCode);
	
	public List<BasicCategoryQulificationVO> getQualificationsByCategoryCodes(List<String> categoryCodes);
	
}
