package com.myzh.upm.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myzh.upm.entity.Module;
import com.myzh.framework.data.mybatis.MybatisTplDao;

@Service
public class ModuleService {
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public List<Module> queryModule(Integer id, Boolean cascade) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("cascade", cascade);
		return dao.queryForList("upm.module.queryModule", params);
	}
	
	public List<Module> queryModuleByName(String name) {
		return dao.queryForList("upm.module.queryModuleByName", "%" + name + "%");
	}
	
	@Transactional("uadp")
	public void insertModule(Module m) {
		Module maxNode = dao.queryForOne("upm.module.queryMaxChildNode", m.getParentId());
		if(maxNode == null) {
			m.setIdx(1);
			if(m.getParentId() != 0) {
				dao.update("upm.module.updateModuleLeaf", m.getParentId());
			}
		}else {
			m.setIdx(maxNode.getIdx().intValue() + 1);
		}
		
		String parentNodeCascade = dao.queryForOne("upm.module.queryNodeCascade", m.getParentId());
		if(parentNodeCascade == null) {
			parentNodeCascade = "0";
		}
		
		dao.insert("upm.module.insertModule", m);
		m.setCascade(parentNodeCascade + "." + m.getId());
		dao.update("upm.module.updateModuleCascade", m);

	}
	
	public void updateModule(Module m) {
		dao.update("upm.module.updateModule", m);
	}
	
	@Transactional("uadp")
	public void delModule(Integer id) {
		String cascade = dao.queryForOne("upm.module.queryNodeCascade", id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("length", cascade.length());
		params.put("cascade", cascade);
		dao.delete("upm.module.delModule", params);
	}
	
	public List<Map<String, Object>> queryModuleTree() {
		List<Module> lst = dao.queryForList("upm.module.queryAllModule");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("key", "0");
		root.put("name", "功能模块树");
		result.add(root);
		renderTree(root, lst);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void renderTree(Map<String, Object> parentNode, List<Module> tree) {
		String parentKey =(String)parentNode.get("key");
		for(int i = 0; i < tree.size(); i++) {
			Module m = tree.get(i);
			String parentId = String.valueOf(m.getParentId());
			if(parentId == null) {
				parentId = "0";
			}
			if(parentId.equals(parentKey)) {
				List<Map<String, Object>> children = (List<Map<String, Object>>)parentNode.get("children");
				if(children == null) {
					children = new ArrayList<Map<String, Object>>();
					parentNode.put("children", children);
				}
				
				Map<String, Object> child = new HashMap<String, Object>();
				child.put("key", String.valueOf(m.getId()));
				child.put("name", m.getName());
				child.put("isLeaf", m.getIsLeaf());
				children.add(child);
				renderTree(child, tree);
			}
		}
	}
	
	/**
	 * 拖拽节点，重新排序
	 * @param dragKey 被拖拽的节点
	 * @param dropKey 目标节点
	 * @param isContain 是否拖拽到目标节点下, true-是，false-否
	 * @param isBeforeDropNode 是否在目标节点之前释放
	 */
	@Transactional("uadp")
	public void sort(String dragKey, String dropKey, Boolean isContain, Boolean isBeforeDropNode) {
		if(isContain) {
			//如果拖拽节点被拖到目标节点下，则找到目标节点排序号最大的子节点，将拖拽节点序号加1，插到目标节点下
			Module maxChildNode = dao.queryForOne("upm.module.queryMaxChildNode", dropKey);
			Module updateModule = new Module();
			updateModule.setId(Integer.valueOf(dragKey));
			updateModule.setIdx(maxChildNode == null ? 1 : maxChildNode.getIdx() + 1);
			updateModule.setParentId(Integer.valueOf(dropKey));
			String cascade = dao.queryForOne("upm.module.queryNodeCascade", dropKey);
			if(cascade == null) {
				cascade = "0";
			}
			updateModule.setCascade(cascade + "." + dragKey);
			dao.update("upm.module.updateModuleIdx", updateModule);
			//父节点置为目录
			dao.update("upm.module.updateModuleLeaf", Integer.valueOf(dropKey));
		}else {
			//如果拖拽节点被拖到目标节点的前后位置，则找到目标节点下的所有子节点
			List<Module> brotherModules = dao.queryForList("upm.module.queryBrotherModule", dropKey);
			List<Module> sortedModules = new ArrayList<Module>();
			boolean isFind = false;
			for(int i = 0; i < brotherModules.size(); i++) {
				Module currentModule = brotherModules.get(i);
				//当拖拽节点在子节点列表中，则跳过当次循环
				if(currentModule.getId().intValue() == Integer.valueOf(dragKey)) {
					continue;
				}
				
				if(currentModule.getId().intValue() == Integer.valueOf(dropKey)) {
					Module insertModule = new Module();
					insertModule.setId(Integer.valueOf(dragKey));
					insertModule.setParentId(currentModule.getParentId());
					String cascade = dao.queryForOne("upm.module.queryNodeCascade", currentModule.getParentId());
					if(cascade == null) {
						cascade = "0";
					}
					insertModule.setCascade(cascade + "." + dragKey);
					if(isBeforeDropNode) {
						sortedModules.add(insertModule);
						sortedModules.add(currentModule);
					}else {
						sortedModules.add(currentModule);
						sortedModules.add(insertModule);
					}
				}else {
					sortedModules.add(currentModule);
				}
			}
			
			for(int i = 0 ; i < sortedModules.size(); i++) {
				Module m = sortedModules.get(i);
				m.setIdx(i + 1);
				dao.update("upm.module.updateModuleIdx", m);
			}
		}
	}
}
