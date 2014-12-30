package com.mysoft.b2b.bizsupport.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysoft.b2b.bizsupport.api.Domain;
import com.mysoft.b2b.bizsupport.api.DomainVO;

/**
 * 域名 映射器
 * @author pengym
 *
 */
@Component("domainMapper")
public interface DomainMapper {
	/**
	 * 保存对象信息
	 * @param domain
	 * @return
	 */
	int saveDomain(Domain domain);
	/**
	 * 修改域名对象
	 * @param domain
	 * @return
	 */
	int updateDomain(Domain domain);
	/**
	 * 删除对象
	 * @param domain
	 * @return
	 */
	int deleteDomain(Domain domain);
	
	/**
	 * 根据条件对象 查询某个域对象
	 * @param domain
	 * @return
	 */
	Domain getDomain(Domain domain);
	
	/**
	 * 暂时不实现
	 * @param domain
	 * @return
	 */
	List<Domain> getDomainList(DomainVO domainVO);
	
	/**
	 * 暂时不实现
	 * @param domain
	 * @return
	 */
	long getDomainCount(DomainVO domainVO);
}
