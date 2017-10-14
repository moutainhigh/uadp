package com.upbos.upm.cfg;

import com.upbos.framework.data.page.Pagination;
import com.upbos.upm.entity.Cfg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgService {

    @Resource
    private CfgDao dao;

    public Pagination listCfg(Integer pageNo, Integer pageSize, String value) {
        return dao.listCfg(pageNo, pageSize, value);
    }
}
