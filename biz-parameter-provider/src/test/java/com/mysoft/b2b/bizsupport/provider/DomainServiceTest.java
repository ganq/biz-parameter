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
import com.mysoft.b2b.bizsupport.api.Domain;
import com.mysoft.b2b.bizsupport.api.DomainService;
import com.mysoft.b2b.bizsupport.api.DomainVO;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.api.Qualification;
import com.mysoft.b2b.bizsupport.api.QualificationBasicRelation;
import com.mysoft.b2b.bizsupport.api.QualificationService;
import com.mysoft.b2b.bizsupport.test.BaseTestCase;




//@RunWith(SpringJUnit4ClassRunner.class)
public class DomainServiceTest extends BaseTestCase {/*
	private static final Logger logger = Logger.getLogger(DomainServiceTest.class);
	
	@Autowired
	private DomainService domainService;
	
	@Test
	public void testGetDomains()
	{
		logger.info("********start testGetDomain()****");
		
		List<Domain> domains =domainService.getDomainList(null);
		for(Domain domain :domains)
			logger.info("用户对象：" + domain);
		logger.info("********end testGetDomain()****");
	}
	@Test
	public void testGetAllDomains()
	{
		logger.info("********start testGetAllDomains()****");
		DomainVO domainVO = new DomainVO();
		domainVO.setPageIndex(0);
		domainVO.setPageSize(20);
		domainVO.setOffset(domainVO.getPageIndex()* domainVO.getPageSize());
		List<Domain> domains =domainService.getDomainList(domainVO);
		for(Domain domain :domains)
			logger.info("用户对象：" + domain);
		logger.info("********end testGetAllDomains()****");
	}
	
	@Test
	public void testGetDomainCount()
	{
		logger.info("********start testGetDomainCount()****");
		Long count =domainService.getDomainCount(null);
		logger.info("用户对象：" + count);
		logger.info("********end testGetDomainCount()****");
	}
	
	@Test
	public void testSaveDomain()
	{
		logger.info("********start testSaveDomain()****");
		Domain domain = new Domain();
		domain.setCompanyId("XXXXX");
		domain.setDomain("XXX.XXX.com");
		domain.setOrigin(1);
		domain.setPrefix("XXX");
		domain.setRemark("XXremark");
		domain.setStatus(1);
		domain.setType(1);
		domainService.saveAndUpdateDomain(domain);
		logger.info("用户对象：" + domain);
		logger.info("********end testSaveDomain()****");
	}
	
	
	
*/}
