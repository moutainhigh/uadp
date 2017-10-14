package com.upbos.upm.cfg;

import com.upbos.framework.data.page.Pagination;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <b>Application name：</b> CfgController.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 upbos.com 版权所有。<br>
 * <b>Company：</b> upbos.com <br>
 * <b>Date：</b> 2017年10月13日 17:09 <br>
 * <b>author：</b> <a href="mailto:wjzchina2008@126.com"> wangjz </a>
 * <b>version：</b>V3.0.0
 */
@RestController
@RequestMapping("upm/cfg")
public class CfgController {

    @Resource
    private CfgService srv;

    @RequestMapping("listCfg")
    public Pagination listCfg(Integer pageNo, Integer pageSize, String value) {
        return srv.listCfg(pageNo, pageSize, value);
    }


}
