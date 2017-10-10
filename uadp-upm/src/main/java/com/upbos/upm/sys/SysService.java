package com.upbos.upm.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upbos.upm.entity.Module;
import com.upbos.upm.entity.Sys;
import com.upbos.framework.data.mybatis.MybatisTplDao;

@Service
public class SysService {

	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public List<Sys> querySys(String name) {
		Module m = new Module();
		m.setName("%" + name + "%");
		return dao.queryForList("upm.sys.querySys", m);
	}
	
	@Transactional("uadp")
	public void addSys(Sys sys) {
		dao.insert("upm.sys.addSys", sys); 
	}
	
	@Transactional("uadp")
	public void updateSys(Sys sys) {
		if(sys.getIsCheckedLogin() == false) {
			sys.setLoginUrl(null);
			sys.setLoginUsername(null);
			sys.setLoginPassword(null);
		}
		dao.update("upm.sys.updateSys", sys);
	}
	
	@Transactional("uadp")
	public void deleteSys(String id) {
		dao.update("upm.sys.deleteSys", id);
	}
}
