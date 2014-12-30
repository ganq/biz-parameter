package com.mysoft.b2b.bizsupport.api;

import java.util.List;
/***
 * 二级域名服务接口
 * @author pengym
 *
 */
public interface DomainService {
	
	/**
	 * 通过域前缀查询公司id
	 * @param prefix
	 * @return 公司id
	 */
	String getCompanyIdByPrefix(String prefix);
	
	/**
	 * 检查域前缀名称是否已存在
	 * @param prefix
	 * @return
	 */
	boolean checkDomainPrefix(String prefix);
	
	/**
	 * 查询二级域名，通过公司ID
	 * @param companyId
	 * @return
	 */
	String getDomainByCompanyId(String companyId);
	
	/**
	 * 更新或者保存对象信息
	 * @param domain
	 * @return
	 */
	Domain saveAndUpdateDomain(Domain domain);
	
	/**
	 * 删除对象
	 * @param domain
	 * @return
	 */
	Domain deleteDomain(Domain domain);
	
	/**
	 * 根据id 查询对象
	 * @param uid
	 * @return
	 */
	Domain getDomain(String uid);
	
	/**
	 * 根据条件对象 查询某个域对象
	 * @param domain
	 * @return
	 */
	Domain getDomain(Domain domain);
	
	/**
	 * 查询 所有域名信息列表
	 * @param domain
	 * @return
	 */
	List<Domain> getDomainList(DomainVO domainVO);
	
	/**
	 * 查询域名记录总数
	 * @param domain
	 * @return
	 */
	long getDomainCount(DomainVO domainVO);
	
}
