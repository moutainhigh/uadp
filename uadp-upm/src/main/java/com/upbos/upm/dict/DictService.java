package com.upbos.upm.dict;

import java.util.List;

import javax.annotation.Resource;

import com.upbos.framework.data.page.Pagination;
import com.upbos.framework.web.ret.RetCode;
import com.upbos.framework.web.ret.RetData;
import com.upbos.upm.entity.Cfg;
import org.springframework.stereotype.Service;

import com.upbos.upm.entity.Dict;
import com.upbos.framework.data.mybatis.MybatisTplDao;

@Service
public class DictService {
	
	@Resource
	private DictDao dao;

	public Pagination listDict(Integer pageNo, Integer pageSize, String value) {
		Pagination p = dao.listDict(pageNo, pageSize, value);
		return p;
	}

	public List<Dict> listDict(String key) {
		return dao.listDict(key);
	}

	public void addDict(Dict dict) {
		dao.addDict(dict);
	}

	public void updateDict(Dict dict) {
		dao.updateDict(dict);
	}

	public void deleteDict(String id) {
		dao.deleteDict(id);
	}

}
