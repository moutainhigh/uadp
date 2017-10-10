package com.upbos.upm.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.upbos.upm.entity.Dict;
import com.upbos.framework.data.mybatis.MybatisTplDao;

@Service
public class DictService {
	
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public List<Dict> queryDictByKey(String key) {
		return dao.queryForList("upm.dict.queryDictByKey", key);
	}
	
	public List<Dict> queryDictByKey(String[] keys) {
		if(keys == null || keys.length == 0) {
			return null;
		}
		String p = "";
		for(String key:keys) {
			p += "," + key;
		}
		return dao.queryForList("upm.dict.queryDictByKeys", p.substring(1));
	}
}
