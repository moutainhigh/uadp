package com.upbos.upm.dict;

import java.util.List;

import javax.annotation.Resource;

import com.upbos.framework.data.page.Pagination;
import com.upbos.framework.web.ret.RetData;
import com.upbos.upm.entity.Cfg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upbos.upm.entity.Dict;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Application name：</b> DictController.java <br>
 * <b>Application describing: 配置数据字典</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2015-2017 upbos.com 版权所有。<br>
 * <b>Company：</b> upbos.com <br>
 * <b>Date：</b> 2017年10月18日 17:09 <br>
 * <b>author：</b> <a href="mailto:wjzchina2008@126.com"> Jason </a>
 * <b>version：</b>V3.0.0
 */
@RestController
@RequestMapping("upm/dict")
public class DictController {

	@Resource
	private DictService dictService;

	/**
	 * 查询字典列表
	 * @param pageNo   当前页
	 * @param pageSize 每页条数
	 * @param value    查询关键词，可以是参数名称或参数键
	 * @return
	 */
	@RequestMapping("listDict")
	public Pagination listDict(
			@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "20") Integer pageSize,
			String value) {
		Pagination p = dictService.listDict(pageNo, pageSize, value);
		return p;
	}

	@RequestMapping("listDictByKey")
	public List<Dict> listDict(String key) {
		return dictService.listDict(key);
	}

	/**
	 * 新增字典
	 * @param dict 字典对照
	 */
	@RequestMapping("addDict")
	public void addDict(Dict dict) {
		dictService.addDict(dict);
	}

	/**
	 * 修改字典
	 * @param dict 字典内容
	 */
	@RequestMapping("updateDict")
	public void updateDict(Dict dict) {
		dictService.updateDict(dict);
	}

	/**
	 * 删除系统参数
	 *
	 * @param id
	 */
	@RequestMapping("deleteDict")
	public void deleteDict(String id) {
		dictService.deleteDict(id);
	}

}