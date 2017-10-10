package com.myzh.framework.data.jdbc;

import java.util.List;
import java.util.Map;

import com.myzh.framework.data.page.Pagination;

public interface JdbcDao {
	public int insert(String sql);

	public int insert(String sql, Object param);
	
	public int update(String sql);
	
	public int update(String sql, Object param);
	
	public int delete(String sql);
	
	public int delete(String sql, Object param);
	
	public List<Map<String, Object>> queryForList(String sql);
	
	public List<Map<String, Object>> queryForList(String sql, Object param);

	public <T> List<T> queryForList(String sql, Class<T> elementType);

	public <T> List<T> queryForList(String sql, Object param, Class<T> elementType);
	
	public Pagination queryForPagination(int pageNo, int pageSize, String sql, Class<?> elementType); 
	
	public <T> List<T> queryForPagination(int pageNo, int pageSize, String sql, boolean hasTotal, Class<T> elementType) ;
	
	public Pagination queryForPagination(int pageNo, int pageSize, String sql, Object parameter, Class<?> elementType);
	
	public <T> List<T> queryForPagination(int pageNo, int pageSize, String sql, Object parameter, boolean hasTotal, Class<T> elementType);
	
	public <T>T queryForOne(String sql, Class<T> elementType);
	
	public <T>T queryForOne(String sql, Object param, Class<T> elementType) ;
	
	public Map<String, Object> queryForMap(String sql, Object param);
}
