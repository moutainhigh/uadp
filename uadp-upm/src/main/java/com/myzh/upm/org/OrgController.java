package com.myzh.upm.org;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myzh.session.SessionManager;
import com.myzh.session.SessionUser;
import com.myzh.upm.dict.DictUtil;
import com.myzh.upm.entity.Org;

@RestController
@RequestMapping("upm/org")
public class OrgController {
	
	@Resource
	private OrgService srv;
	
	@Resource
	private SessionManager sessionMgr;
	
	@RequestMapping("queryOrgTree")
	public List<Map<String, Object>> queryOrgTree() {
		SessionUser user = sessionMgr.getSession().getUser();
		Integer orgId = user.getToOrgId();
		return srv.queryOrgTree(orgId);
	}
	
	@RequestMapping("queryOrg")
	public List<Org> queryOrg(Integer id, Boolean cascade) throws Exception {
		List<Org> list = srv.queryOrg(id, cascade);
		DictUtil.tranlate(list, "org_type:type->typeName");
		return list;
	}
	
	@RequestMapping("queryOrgByName")
	public List<Org> queryOrgByName(String name) throws Exception {
		SessionUser user = sessionMgr.getSession().getUser();
		Integer currentOrgId = user.getToOrgId();
		List<Org> list = srv.queryOrgByName(name, currentOrgId); 
		DictUtil.tranlate(list, "org_type:type->typeName");
		
		return list;
	}
	
	@RequestMapping("insertOrg")
	public void insertOrg(Org m) {
		if(m.getParentId() == null) m.setParentId(0);
		srv.insertOrg(m);
	}
	
	@RequestMapping("updateOrg")
	public void updateOrg(Org m) {
		srv.updateOrg(m);
	}
	
	@RequestMapping("delOrg")
	public void delOrg(Integer id) {
		srv.delOrg(id);
	}
	
	@RequestMapping("batchGenerateOrg")
	public void batchGenerateOrg() {
		Org org = new Org();
		org.setCascade("0.1");
		org.setCode("410000000000");
		org.setId(1);
		srv.batchGenerateOrg(org);
	}
}
