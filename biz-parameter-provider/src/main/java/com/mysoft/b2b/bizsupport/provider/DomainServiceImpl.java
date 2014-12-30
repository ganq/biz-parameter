package com.mysoft.b2b.bizsupport.provider;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.b2b.bizsupport.api.Domain;
import com.mysoft.b2b.bizsupport.api.DomainService;
import com.mysoft.b2b.bizsupport.api.DomainVO;
import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.bizsupport.mapper.DomainMapper;
/**
 * 
 * @author pengym
 *
 */
@Service("domainService")
public class DomainServiceImpl implements DomainService{
	private static final Logger log = Logger.getLogger(DomainServiceImpl.class);
	@Autowired
	private IdGenerationService idGenerationService;
	@Autowired
	private DomainMapper domainMapper;
	private static final String TABLE_NAME ="b2b_business.bsp_domain";
	
	public String getCompanyIdByPrefix(String prefix) {
		// TODO Auto-generated method stub
		Domain domain = new Domain();
		domain.setPrefix(prefix);
		Domain resultDomain = domainMapper.getDomain(domain);
		if(resultDomain !=null)
			return resultDomain.getCompanyId();
		return null;
	}

	public boolean checkDomainPrefix(String prefix) {
		// TODO Auto-generated method stub
		Domain domain = new Domain();
		domain.setPrefix(prefix);
		Domain resultDomain = domainMapper.getDomain(domain);
		if(resultDomain !=null)
			return true;
		return false;
	}

	public Domain saveAndUpdateDomain(Domain domain) {
		// TODO Auto-generated method stub
		if(domain.getUid() !=null && !"".equals(domain.getUid()))  //修改
		{
			domainMapper.updateDomain(domain);
		}
		else {	//新增
			String uid = idGenerationService.getNextId(TABLE_NAME)+"";
			domain.setUid(uid);
			domainMapper.saveDomain(domain);
		}
		return domain;
	}

	public Domain deleteDomain(Domain domain) {
		// TODO Auto-generated method stub
		 domainMapper.deleteDomain(domain);
		 return domain;
	}
	
	public Domain getDomain(String uid) {
		// TODO Auto-generated method stub
		Domain domain = new Domain();
		domain.setUid(uid);
		return domainMapper.getDomain(domain);
		
	}

	public Domain getDomain(Domain domain) {
		// TODO Auto-generated method stub
		return domainMapper.getDomain(domain);
	}

	public List<Domain> getDomainList(DomainVO domainVO) {
		// TODO Auto-generated method stub
		//return domainMapper.getDomainList(domainVO);
		return domainMapper.getDomainList(domainVO);
	}

	public long getDomainCount(DomainVO domainVO) {
		return domainMapper.getDomainCount(domainVO);
		//return 0;
	}

	@Override
	public String getDomainByCompanyId(String companyId) {
		Domain domain = new Domain();
		domain.setCompanyId(companyId);
		Domain resultDomain = domainMapper.getDomain(domain);
		if(resultDomain !=null)
			return resultDomain.getPrefix();
		return companyId.toLowerCase();
		//SupplierToPortalService
	}
	
}
