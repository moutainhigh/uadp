package com.upbos.upm.cfg;

import java.util.List;

import javax.annotation.Resource;

import com.upbos.framework.data.page.Pagination;
import org.springframework.stereotype.Repository;

import com.upbos.upm.entity.Cfg;
import com.upbos.framework.data.mybatis.MybatisTplDao;

@Repository("cfgDao")
public class CfgDao {
	
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public Pagination listCfg(Integer pageNo, Integer pageSize, String value) {
		if(value != null && !"".equals(value)) {
			value = "%" + value + "%";
		}
		return dao.queryForPagination(pageNo, pageSize,"upm.cfg.listCfg", value);
	}
	
	public List<Cfg> listCfg(String ...keys) {
		return dao.queryForList("upm.cfg.listCfg", keys);
	}
	
	public Cfg getCfg(String key) {
		return dao.queryForOne("upm.cfg.listCfg", key);
	}
}
