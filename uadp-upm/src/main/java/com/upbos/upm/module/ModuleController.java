package com.upbos.upm.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upbos.upm.entity.Module;

/**
 * @author wangjz
 */
@RestController
@RequestMapping("upm/module")
public class ModuleController {
	
	@Resource
	private ModuleService srv;
	
	@RequestMapping("listModule")
	public List<Module> listModule(Integer id) {
		return srv.listModule(id);
	}
	
	@RequestMapping("listModuleByName")
	public List<Module> listModuleByName(String name) {
		return srv.listModuleByName(name);
	}
	
	@RequestMapping("addModule")
	public void addModule(Module m) {
		srv.addModule(m);
	}
	
	@RequestMapping("updateModule")
	public void updateModule(Module m) {
		srv.updateModule(m);
	}
	
	@RequestMapping("deleteModule")
	public void deleteModule(Integer id) {
		srv.deleteModule(id);
	}
	
	@RequestMapping("listModuleTree")
	public List<Map<String, Object>> listModuleTree() {
		return srv.listModuleTree();
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
