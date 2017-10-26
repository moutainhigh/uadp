package com.upbos.framework.data.mybatis.impl;

import com.upbos.framework.data.mybatis.MybatisTplDao;
import com.upbos.framework.data.mybatis.rowbounds.PaginationRowBounds;
import com.upbos.framework.data.page.Pagination;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: MybatisTplDaoImpl.java</p>
 * <p>Description: mybatis数据持久层实现</p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: upbos.com</p>
 * @author wangjz
 * @since 2013年10月13日
 * @version 2.0.0
 */
public class MybatisTplDaoImpl implements MybatisTplDao {

	/**
	 * mybatis session manager template
	 */
	private SqlSessionTemplate sqlTpl;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlTpl) {
		this.sqlTpl = sqlTpl;
	}

	@Override
	public int insert(String sqlId) {
		return sqlTpl.insert(sqlId);
	}

	@Override
	public int insert(String sqlId, Object parameter) {
		return sqlTpl.insert(sqlId, parameter);
	}

	@Override
	public int batchInsert(String sqlId, List parameter, int batchSize) {
		if (0 == batchSize) {
			batchSize = 500;
		}
		int returnValue = 0, total = parameter.size();
		if (total <= batchSize){
			returnValue = sqlTpl.insert(sqlId, parameter);
		}else {
			int loop = (int) Math.ceil(total / (double) batchSize);
			List tempParameter = new ArrayList<>();
			int start, stop;
			for (int i = 0; i < loop; i++) {
				tempParameter.clear();
				start = i * batchSize;
				stop = Math.min(i * batchSize + batchSize - 1, total - 1);
				for (int j = start; j <= stop; j++) {
					tempParameter.add(parameter.get(j));
				}
				returnValue +=sqlTpl.insert(sqlId, tempParameter);
			}
		}
		return returnValue;
	}
	@Override
	public int update(String sqlId) {
		return sqlTpl.update(sqlId);
	}
	@Override
	public int update(String sqlId, Object parameter) {
		return sqlTpl.update(sqlId, parameter);
	}
	@Override
	public int delete(String sqlId) {
		return sqlTpl.delete(sqlId);
	}
	@Override
	public int delete(String sqlId, Object parameter) {
		return sqlTpl.delete(sqlId, parameter);
	}
	@Override
	public <T> List<T> queryForList(String sqlId) {
		return sqlTpl.selectList(sqlId);
	}
	@Override
	public <T> List<T> queryForList(String sqlId, Object parameter) {
		return sqlTpl.selectList(sqlId, parameter);
	}
	@Override
	public Pagination queryForPagination(int pageNo, int pageSize, String sqlId) {
		return queryForPagination(pageNo, pageSize, sqlId, null);
	}
	@Override
	public <T> List<T> queryForPaginationWithoutTotal(int pageNo, int pageSize, String sqlId) {
		Pagination p = queryForPaginationExt(pageNo, pageSize, sqlId, null, false);
		return p.getRows();
	}
	@Override
	public <T> List<T> queryForPaginationWithoutTotal(int pageNo, int pageSize, String sqlId, Object parameter) {
		Pagination p = queryForPaginationExt(pageNo, pageSize, sqlId, parameter, false);
		return p.getRows();
	}

	@Override
	public Pagination queryForPagination(int pageNo, int pageSize, String sqlId, Object parameter) {
		return queryForPaginationExt(pageNo, pageSize, sqlId, parameter, true);
	}

	@Override
	public <K, V> Map<K, V> queryForMap(String sqlId) {
		return sqlTpl.selectOne(sqlId);
	}
	@Override
	public <K, V> Map<K, V> queryForMap(String sqlId, Object parameter) {
		return sqlTpl.selectOne(sqlId, parameter);
	}
	@Override
	public <T> T queryForOne(String sqlId) {
		return sqlTpl.selectOne(sqlId);
	}
	@Override
	public <T> T queryForOne(String sqlId, Object parameter) {
		return sqlTpl.selectOne(sqlId, parameter);
	}

	private Pagination queryForPaginationExt(int pageNo, int pageSize, String sqlId, Object parameter, boolean hasTotal) {
		Pagination p = new Pagination();
		p.setPageNo(pageNo);
		p.setPageSize(pageSize);
		if(pageNo > 0) {
			pageNo = pageNo - 1;
		}
		PaginationRowBounds prd = new PaginationRowBounds(pageNo*pageSize, pageSize);
		prd.setPagination(p);
		prd.setHasTotal(hasTotal);

		List<Object> rtn = sqlTpl.selectList(sqlId, parameter, prd);
		p.setRows(rtn);

		return p;
	}
}
