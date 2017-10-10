package com.myzh.upm.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myzh.upm.entity.Sys;

@RestController
@RequestMapping("upm/sys")
public class SysController {

	@Resource
	private SysService srv;
	
	@RequestMapping("querySys")
	public List<Sys> querySys(String name) {
		return srv.querySys(name);
	}
	
	@RequestMapping("addSys")
	public void addSys(Sys sys) {
		srv.addSys(sys);
	}
	
	@RequestMapping("updateSys")
	public void updateSys(Sys sys) {
		srv.updateSys(sys);
	}
	
	@RequestMapping("deleteSys")
	public void deleteSys(String id) {
		srv.deleteSys(id);
	}
}
