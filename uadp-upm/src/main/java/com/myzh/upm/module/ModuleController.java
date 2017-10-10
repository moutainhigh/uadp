package com.myzh.upm.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myzh.upm.entity.Module;

@RestController
@RequestMapping("upm/module")
public class ModuleController {
	
	@Resource
	private ModuleService srv;
	
	@RequestMapping("queryModule")
	public List<Module> queryModule(Integer id, Boolean cascade) {
		return srv.queryModule(id, cascade);
	}
	
	@RequestMapping("queryModuleByName")
	public List<Module> queryModuleByName(String name) {
		return srv.queryModuleByName(name);
	}
	
	@RequestMapping("insertModule")
	public void insertModule(Module m) {
		srv.insertModule(m);
	}
	
	@RequestMapping("updateModule")
	public void updateModule(Module m) {
		srv.updateModule(m);
	}
	
	@RequestMapping("delModule")
	public void delModule(Integer id) {
		srv.delModule(id);
	}
	
	@RequestMapping("queryModuleTree")
	public List<Map<String, Object>> queryModuleTree() {
		return srv.queryModuleTree();
	}
	
	/**
	 * 拖拽节点，重新排序
	 * @param dragKey 被拖拽的节点
	 * @param dropKey 目标节点
	 * @param isContain 是否拖拽到目标节点下, true-是，false-否
	 * @param isBeforeDropNode 是否在目标节点之前释放
	 */
	@RequestMapping("sort")
	public void sort(String dragKey, String dropKey, Boolean isContain, Boolean isBeforeDropNode) {
		srv.sort(dragKey, dropKey, isContain, isBeforeDropNode);
	}
}
