package com.myzh.upm.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myzh.framework.data.mybatis.MybatisTplDao;
import com.myzh.upm.entity.Org;

@Service
public class OrgService {
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	public List<Map<String, Object>> queryOrgTree(Integer orgId) {
		List<Org> lst = dao.queryForList("upm.org.queryAllOrg", orgId);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		for(Org org : lst) {
			if(org.getId().intValue() == orgId.intValue()) {
				root.put("id", org.getId());
				root.put("name", org.getName());
				result.add(root);
				break;
			}
		}
		
		renderTree(root, lst);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void renderTree(Map<String, Object> parentNode, List<Org> tree) {
		Integer parentKey =(Integer)parentNode.get("id");
		for(int i = 0; i < tree.size(); i++) {
			Org m = tree.get(i);
			Integer parentId = m.getParentId();
			if(parentId == null) {
				parentId = 0;
			}
			if(parentId == parentKey.intValue()) {
				List<Map<String, Object>> children = (List<Map<String, Object>>)parentNode.get("children");
				if(children == null) {
					children = new ArrayList<Map<String, Object>>();
					parentNode.put("children", children);
				}
				
				Map<String, Object> child = new HashMap<String, Object>();
				child.put("id", m.getId());
				child.put("name", m.getName());
				child.put("isLeaf", m.getIsLeaf());
				children.add(child);
				renderTree(child, tree);
			}
		}
	}
	
	public List<Org> queryOrg(Integer id, Boolean cascade) {
		if(id == null) id = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("cascade", cascade);
		return dao.queryForList("upm.org.queryOrg", params);
	}
	
	public List<Org> queryOrgByName(String name, Integer currentOrgId) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", "%" + name + "%");
		p.put("currentOrgId", currentOrgId);
		return dao.queryForList("upm.org.queryOrgByName", p);
	}
	
	@Transactional("uadp")
	public void insertOrg(Org m) {
		Org maxNode = dao.queryForOne("upm.org.queryMaxNode", m.getParentId());
		if(maxNode == null) {
			m.setIdx(1);
			if(m.getParentId() != 0) {
				dao.update("upm.org.updateOrgLeaf", m.getParentId());
			}
		}else {
			m.setIdx(maxNode.getIdx().intValue() + 1);
		}
		
		String parentNodeCascade = dao.queryForOne("upm.org.queryNodeCascade", m.getParentId());
		if(parentNodeCascade == null) {
			parentNodeCascade = "0";
		}
		
		dao.insert("upm.org.insertOrg", m);
		m.setCascade(parentNodeCascade + "." + m.getId());
		dao.update("upm.org.updateOrgCascade", m);
	}
	
	public void updateOrg(Org m) {
		dao.update("upm.org.updateOrg", m);
	}
	
	@Transactional("uadp")
	public void delOrg(Integer id) {
		String cascade = dao.queryForOne("upm.org.queryNodeCascade", id);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("length", cascade.length());
		params.put("cascade", cascade);
		dao.delete("upm.org.delOrg", params);
	}

	@Transactional
	public void batchGenerateOrg(Org parentOrg) {
		List<Map<String, Object>> list = dao.queryForList("upm.org.queryAreaChildren", parentOrg.getCode());
		if(list == null || list.size() == 0) {
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			Org org = new Org();
			org.setCode((String)list.get(i).get("area_code"));
			org.setIdx(i + 1);
			org.setIsFixed(false);
			Integer level = Integer.parseInt((String)list.get(i).get("level"));
			System.out.println(level);
			if(level <=3) {
				org.setIsLeaf(false);
			}else {
				org.setIsLeaf(true);
			}
			org.setName((String)list.get(i).get("area_name"));
			org.setParentId(parentOrg.getId());
			org.setStatus("1");
			org.setType((String)list.get(i).get("level"));
			dao.insert("upm.org.insertOrg", org);
			org.setCascade(parentOrg.getCascade() + "." + org.getId());
			dao.update("upm.org.updateOrgCascade", org);
			
			if(Integer.parseInt((String)list.get(i).get("level")) <=3) {
				dao.update("upm.org.updateOrgLeaf", org.getId());
				batchGenerateOrg(org);
			}
		}
	}
}
