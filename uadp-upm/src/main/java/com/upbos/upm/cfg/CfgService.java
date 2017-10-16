package com.upbos.upm.cfg;

import com.upbos.framework.data.page.Pagination;
import com.upbos.framework.web.ret.RetCode;
import com.upbos.framework.web.ret.RetData;
import com.upbos.upm.entity.Cfg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgService {

    @Resource
    private CfgDao dao;

    public Pagination listCfg(Integer pageNo, Integer pageSize, String value) {
        Pagination p = dao.listCfg(pageNo, pageSize, value);
        return p;
    }

    public RetData addCfg(Cfg cfg) {
        RetData retData = new RetData(RetCode.SUCCESS);
        if (dao.isCfgUnique(cfg.getKey())) {
            dao.addCfg(cfg);
        } else {
            retData.setCode("0");
            retData.setSuccess(false);
            retData.setMsg("参数键已存在！");
        }
        return retData;
    }

    public void updateCfg(Cfg cfg) {
        dao.updateCfg(cfg);
    }

    public void deleteCfg(String key) {
        dao.deleteCfg(key);
    }
}
