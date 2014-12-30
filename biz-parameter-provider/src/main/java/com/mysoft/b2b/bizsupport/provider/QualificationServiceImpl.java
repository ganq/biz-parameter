package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.b2b.bizsupport.api.BasicCategoryQulificationVO;
import com.mysoft.b2b.bizsupport.api.Qualification;
import com.mysoft.b2b.bizsupport.api.QualificationBasicRelation;
import com.mysoft.b2b.bizsupport.api.QualificationService;
import com.mysoft.b2b.bizsupport.mapper.QualificationMapper;

@Service("qualificationService")
public class QualificationServiceImpl implements QualificationService {
	private static final Logger log = Logger.getLogger(QualificationServiceImpl.class);
	@Autowired
	private QualificationMapper qMapper;

	public List<Qualification> getQualifications(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Qualification> qlList = qMapper.getQualifications(param);
		if(qlList !=null)
			return qlList;
		else 
			return new ArrayList<Qualification>();
	}

	public int getQualificationsCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return qMapper.getQualificationsCount(param);
	}

	public Qualification getQualificationByCode(String code) {
		// TODO Auto-generated method stub
		return qMapper.getQualificationByCode(code);
	}

	public List<Qualification> getQualificationsByCategoryCode(String code) {
		// TODO Auto-generated method stub
		List<Qualification> qlList = qMapper.getQualificationsByCategoryCode(code);
		if(qlList !=null)
			return qlList;
		else 
			return new ArrayList<Qualification>();
	}

	public int getQualificationsByCategoryCodeCount(String code) {
		// TODO Auto-generated method stub
		return qMapper.getQualificationsByCategoryCodeCount(code);
	}

	public boolean addQualification(Qualification qualification) {
		// TODO Auto-generated method stub
		if(qualification ==null)
		{
			log.error("qualification参数不能为null", new RuntimeException("qualification参数不能为null"));
			return false;
		}
		return qMapper.addQualification(qualification) > 0;
	}

	public boolean updateQualification(Qualification qualification) {
		// TODO Auto-generated method stub
		if(qualification ==null)
		{
			log.error("qualification参数不能为null", new RuntimeException("qualification参数不能为null"));
			return false;
		}
		return qMapper.updateQualification(qualification) > 0;
	}

	public boolean addQualificationBasicRelationBatch(
			List<QualificationBasicRelation> qbr) {
		// TODO Auto-generated method stub
		if(qbr ==null)
		{
			log.error("qbr参数不能为null", new RuntimeException("qbr参数不能为null"));
			return false;
		}
		if(qbr.size()==0)
		{
			log.error("qbr参数不能为空集合", new RuntimeException("qbr参数不能为空集合"));
			return false;
		}
		return qMapper.addQualificationBasicRelationBatch(qbr) > 0;
	}

	public boolean addQualificationBasicRelation(QualificationBasicRelation qbr) {
		// TODO Auto-generated method stub
		if(qbr ==null)
		{
			log.error("qbr参数不能为null", new RuntimeException("qbr参数不能为null"));
			return false;
		}
		if(qbr.getCategoryCode()==null || qbr.getQualificationCode() ==null)
		{
			log.error("qbr参数对象除id外的属性不能为null值", new RuntimeException("qbr参数对象除id外的属性不能为null值"));
			return false;
		}
		return qMapper.addQualificationBasicRelation(qbr) > 0;
	}

	public Qualification getQualificationById(String id) {
		// TODO Auto-generated method stub
		return qMapper.getQualificationById(id);
	}
	
	public boolean deleteQualificationBasicRelationById(String id) {
		// TODO Auto-generated method stub
		return  qMapper.deleteQualificationBasicRelationById(id) > 0;
	}

	public boolean deleteQualificationBasicRelationByQCode(
			String qualificationCode) {
		// TODO Auto-generated method stub
		return qMapper.deleteQualificationBasicRelationByQCode(qualificationCode) > 0;
	}

	public List<QualificationBasicRelation> getQualificationBasicRelationByQCode(
			String qualificationCode) {
		// TODO Auto-generated method stub
		List<QualificationBasicRelation> qbrs = qMapper.getQualificationBasicRelationByQCode(qualificationCode);
		if(qbrs !=null)
			return qbrs;
		else 
			return new ArrayList<QualificationBasicRelation>();
	}
	
	public boolean deleteQualificationBasicRelationByCategoryCode(
			String categoryCode) {
		// TODO Auto-generated method stub
		return qMapper.deleteQualificationBasicRelationByCategoryCode(categoryCode) > 0;
	}
	
	public List<BasicCategoryQulificationVO> getQualificationsByCategoryCode(
			List<String> categoryCodes) {
		// TODO Auto-generated method stub
		List<BasicCategoryQulificationVO> qList = qMapper.getQualificationsByCategoryCodes(categoryCodes);
		if(qList !=null)
			return qList;
		return new ArrayList<BasicCategoryQulificationVO>();
	}
	
}
