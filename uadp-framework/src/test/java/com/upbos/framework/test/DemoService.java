package com.upbos.framework.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

	@Resource
	private DemoDao dao;
	
	public String queryServiceName(String serviceId) {
		return serviceId + ".serviceName->" + dao.queryDaoName(serviceId);
	}
}
