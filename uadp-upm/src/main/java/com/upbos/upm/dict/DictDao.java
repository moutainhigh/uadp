package com.upbos.upm.dict;

import com.upbos.framework.data.mybatis.MybatisTplDao;
import com.upbos.framework.data.page.Pagination;
import com.upbos.upm.entity.Cfg;
import com.upbos.upm.entity.Dict;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Application name：</b> DictDao.java <br>
 * <b>Application describing: 数据字典dao</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2015-2017 upbos.com。<br>
 * <b>Company：</b> upbos.com <br>
 * <b>Date：</b> 2017年10月18日 16:05 <br>
 * <b>author：</b> <a href="mailto:wjzchina2008@miyzh.com"> Jason </a>
 * <b>version：</b>V3.0.0
 */
@Repository
public class DictDao {
    @Resource(name = "mybatisTplDao")
    private MybatisTplDao dao;

    public Pagination listDict(Integer pageNo, Integer pageSize, String value) {
        if (value != null && !"".equals(value)) {
            value = "%" + value + "%";
        }
        Map<String, String> p = new HashMap<String, String>();
        p.put("value", value);
        return dao.queryForPagination(pageNo, pageSize, "upm.dict.listDict", p);
    }

    public List<Dict> listDict(String key) {
        return dao.queryForList("upm.dict.listDictByKey", key);
    }

    public void addDict(Dict dict) {
        dao.insert("upm.dict.insertDict", dict);
    }

    public void updateDict(Dict dict) {
        dao.update("upm.dict.updateDict", dict);
    }

    public void deleteDict(String key) {
        dao.delete("upm.dict.deleteDict", key);
    }


}
