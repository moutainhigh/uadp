package com.upbos.upm.cfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.upbos.framework.data.page.Pagination;
import com.upbos.framework.web.ret.RetData;
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
		Map<String, String> p = new HashMap<String, String>();
		p.put("value", value);
		return dao.queryForPagination(pageNo, pageSize,"upm.cfg.listCfg", p);
	}

	public void addCfg(Cfg cfg) {
		dao.insert("upm.cfg.insertCfg", cfg);
	}

	public void updateCfg(Cfg cfg) {
		dao.update("upm.cfg.updateCfg", cfg);
	}

	public void deleteCfg(String key) {
		dao.delete("upm.cfg.deleteCfg", key);
	}

	public boolean isCfgUnique(String key) {
		int cnt = dao.queryForOne("upm.cfg.isCfgUnique", key);
		if(cnt == 0) {
			return true;
		}else {
			return false;
		}
	}

	public List<Cfg> listCfg(String ...keys) {
		return dao.queryForList("upm.cfg.listCfg", keys);
	}
	
	public Cfg getCfg(String key) {
		return dao.queryForOne("upm.cfg.listCfg", key);
	}
}
