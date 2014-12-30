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
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.api.Qualification;
import com.mysoft.b2b.bizsupport.api.QualificationLevel;
import com.mysoft.b2b.bizsupport.api.QualificationLevelService;
import com.mysoft.b2b.bizsupport.api.QualificationService;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
public class QualificationLevelServiceTest extends BaseTestCase {
	private static final Logger logger = Logger.getLogger(QualificationLevelServiceTest.class);
	
	@Autowired
	private  QualificationLevelService qLevelService;
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Test
	public void testGetQualificationLevels()
	{
		logger.info("开始查询所有资质等级对象……");
		Map<String, Object> param =new HashMap<String, Object>();
		param.put("startIndex", 0);
		param.put("pageSize", 2);
		List<QualificationLevel> qList =  qLevelService.getQualificationLevels(param);
		for (QualificationLevel q : qList) {
			logger.info("用户对象：" + q);
		}	
		logger.info("完成查询所有资质对象……");
	}
	/*
	@Test
	public void testGetQualificationsByQCode()
	{
		logger.info("开始根据资质码查询资质等级");
		String code = "534e4fdbd9d879f52c8677a7";
		List<QualificationLevel> qList = qLevelService.getLevelsByQualificationCode(code);
		for (QualificationLevel q : qList) {
			logger.info("用户对象：" + q);
		}	
		logger.info("完成根据资质码查询资质等级……");
	}
	
	@Test
	public void testAddQualificationLevel(){
		logger.info("开始新增资质等级");
		QualificationLevel q = new QualificationLevel();
		String id = idGenerationService.getNextId("bsp_qualification_level")+"";
		q.setId(id);
		q.setLevelCode(id);
		q.setPriority(1);
		q.setLevelName("测试资质等级test");
		qLevelService.addQualificationLevel(q);
		logger.info("完成新增资质……");
	}
	
	@Test
	public void testGetQualificationLeverByCode()
	{
		logger.info("开始根据资质等级码获取资质等级");
		String code = "534e7833d9d803ae0464aca5";
		QualificationLevel ql = qLevelService.getQualificationLevelByCode(code);
		logger.info(ql);
		logger.info("完成资质等级查询……");
	}
	
	@Test
	public void testGetQualificationLeverParam()
	{
		logger.info("----start-testGetQualificationLeverParam");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("priority", 1);
		String levelName="工程";
		param.put("levelName", "%"+levelName+"%");
		List<QualificationLevel> items = qLevelService.getQualificationLevels(param);
		for(QualificationLevel item: items)
			logger.info(item);
		logger.info("---end…testGetQualificationLeverParam…");
	}*/
}
