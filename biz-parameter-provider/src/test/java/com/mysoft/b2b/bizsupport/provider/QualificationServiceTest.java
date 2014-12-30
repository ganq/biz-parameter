package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.mysoft.b2b.bizsupport.api.BasicCategoryQulificationVO;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.api.Qualification;
import com.mysoft.b2b.bizsupport.api.QualificationBasicRelation;
import com.mysoft.b2b.bizsupport.api.QualificationService;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;




@RunWith(SpringJUnit4ClassRunner.class)
public class QualificationServiceTest extends BaseTestCase {
	private static final Logger logger = Logger.getLogger(QualificationServiceTest.class);
	
	@Autowired
	private  QualificationService qualificationService;
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Test
	public void testGetQualifications()
	{
		logger.info("开始查询所有资质对象……");
		Map<String, Object> param =new HashMap<String, Object>();
		param.put("startIndex", 0);
		param.put("pageSize", 2);
		List<Qualification> qList = qualificationService.getQualifications(param);
		for (Qualification q : qList) {
			logger.info("用户对象：" + q);
		}	
		logger.info("完成查询所有资质对象……");
	}
	/*
	@Test
	public void testGetQualificationsByCategoryCode()
	{
		logger.info("开始根据基础服务码查询资质");
		String categoryCode = "188";
		List<Qualification> qList = qualificationService.getQualificationsByCategoryCode(categoryCode);
		for (Qualification q : qList) {
			logger.info("用户对象：" + q);
		}	
		logger.info("完成根据基础服务码查询资质……");
	}
	
	@Test
	public void testAddQualification(){
		logger.info("开始新增资质");
		Qualification q = new Qualification();
		q.setId(idGenerationService.getNextId("bsp_qualification")+"");
		q.setQualificationCode(new ObjectId().toString());
		q.setQualificationName("测试资质2");
		qualificationService.addQualification(q);
		logger.info("完成新增资质……");
	}
	
	@Test
	public void testGetQualificationById()
	{
		logger.info("开始根据资质id获取资质");
		String id = "1";
		Qualification qualification= qualificationService.getQualificationById(id);
		logger.info(qualification);
		logger.info("完成资质查询……");
	}
	
	@Test
	public void testgetQualificationsByParam()
	{
		logger.info("----testgetQualificationsByParam");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", true);
		param.put("startIndex", 0);
		param.put("pageSize", 10);
		List<Qualification> items = qualificationService.getQualifications(param);
		for(Qualification item: items)
			logger.info(item);
		int count = qualificationService.getQualificationsCount(param);
		logger.info("---Count="+ count);
		logger.info("---testgetQualificationsByParam");
	}
	@Test
	public void testGetQualificationBasicRelationByQCode(){
		String code ="534e4fdbd9d879f52c8677a7";
		logger.info("----开始调用 ---testGetQualificationBasicRelationByQCode()");
		List<QualificationBasicRelation> qbrs = qualificationService.getQualificationBasicRelationByQCode(code);
		for(QualificationBasicRelation qbr : qbrs){
			logger.info(qbr.toString());
		}
		logger.info("----结束调用 ---testGetQualificationBasicRelationByQCode()");
	}
	
	@Test
	public void testGetQualificationsByCategoryCodes()
	{
		logger.info("***start****testGetQualificationsByCategoryCodes()***");
		List<String> codes = new ArrayList<String>();
		codes.add("188");
		codes.add("77");
		List<BasicCategoryQulificationVO> qList = qualificationService.getQualificationsByCategoryCode(codes);
		for (BasicCategoryQulificationVO q : qList) {
			logger.info("用户对象：" + q);
		}	
		logger.info("***end****testGetQualificationsByCategoryCodes()***");
	}
	
	@Test
	public void testUpdateQualificationByCategoryCode(){
		logger.info("***start****testUpdateQualificationByCategoryCode()***");
		String code ="534f3bc0d9d8d6d15d19096a";
		Qualification qualification = qualificationService.getQualificationByCode(code);
		qualification.setRelationBasic(true);
		qualificationService.updateQualification(qualification);
		logger.info("用户对象：" + qualification);
		logger.info("***end****testUpdateQualificationByCategoryCode()***");
	}*/
}
