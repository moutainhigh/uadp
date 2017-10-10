package com.upbos.upm.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upbos.upm.entity.Dict;

@RequestMapping("upm/dict")
@Controller
public class DictController {

	@Resource
	private DictService srv;
	
	@RequestMapping("queryDict")
	@ResponseBody
	public List<Dict> queryDictBytKey(String key) {
		return srv.queryDictByKey(key);
	}
}
