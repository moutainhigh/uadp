package com.myzh.framework.test;

import org.springframework.stereotype.Repository;

@Repository
public class DemoDao {

	public String queryDaoName(String serviceId) {
		return serviceId + ".DaoName";
	}
}
