package com.upbos.upm.cfg;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.upbos.upm.entity.Cfg;
import com.upbos.framework.data.mybatis.MybatisTplDao;

@Repository("cfgDao")
public class CfgDao {
	
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public List<Cfg> queryCfgList() {
		return dao.queryForList("upm.cfg.queryCfgList");
	}
	
	public List<Cfg> queryCfgList(String ...keys) {
		return dao.queryForList("upm.cfg.queryCfgList", keys);
	}
	
	public Cfg queryCfg(String key) {
		return dao.queryForOne("upm.cfg.queryCfg", key);
	}
}
