package com.upbos.upm.cfg;

import com.upbos.framework.data.page.Pagination;
import com.upbos.framework.web.ret.RetData;
import com.upbos.upm.entity.Cfg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <b>Application name：</b> CfgController.java <br>
 * <b>Application describing: 配置系统参数</b> <br>
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

    /**
     * 查询系统参数，支持模糊查询
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @param value    查询关键词，可以是参数名称或参数键
     * @return {"pageNo":1,"pageSize":10,
     * "rows":[{"fixed":false,"key":"app_title","name":"应用系统标题","options":"应用集成平台","value":"应用集成开发平台"},
     * {"fixed":false,"key":"is_top_menu","name":"顶层菜单置顶","options":"true,false","remark":"顶层菜单是否置顶","value":"true"},
     * {"fixed":true,"key":"layout","name":"系统布局","options":"tab,window,mdi","value":"tab"}],
     * "total":3}
     */
    @RequestMapping("listCfg")
    public Pagination listCfg(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            String value) {
        Pagination p = srv.listCfg(pageNo, pageSize, value);
        return p;
    }

    /**
     * 新增系统参数，如果存在相同的参数键，则不能增加
     *
     * @param cfg 参数内容
     * @return
     */
    @RequestMapping("addCfg")
    public RetData addCfg(Cfg cfg) {
        return srv.addCfg(cfg);
    }

    /**
     * 修改系统参数，如果存在相同的参数键，则不能增加
     *
     * @param cfg
     * @return
     */
    @RequestMapping("updateCfg")
    public void updateCfg(Cfg cfg) {
        srv.updateCfg(cfg);
    }

    /**
     * 删除系统参数
     *
     * @param key
     */
    @RequestMapping("deleteCfg")
    public void deleteCfg(String key) {
        srv.deleteCfg(key);
    }
}
