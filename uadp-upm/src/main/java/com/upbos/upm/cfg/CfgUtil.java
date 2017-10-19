package com.upbos.upm.cfg;

import java.util.List;

import com.upbos.framework.context.SpringContext;
import com.upbos.upm.entity.Cfg;

public class CfgUtil {
	
	public static List<Cfg> listCfg(String ...keys) {
		CfgDao cfgDao = SpringContext.getBean("cfgDao");
		return cfgDao.listCfgByKey(keys);
	}
	
	public static Cfg getCfg(String key) {
		CfgDao cfgDao = SpringContext.getBean("cfgDao");
		return cfgDao.getCfg(key);
	}
}
