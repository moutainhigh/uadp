package com.myzh.framework.data.jdbc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.myzh.framework.data.jdbc.JdbcDao;
import com.myzh.framework.data.page.Pagination;

public class JdbcDaoImpl implements JdbcDao {
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insert(String sql) {
		return jdbcTemplate.update(sql, new EmptySqlParameterSource());
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insert(String sql, Object param) {
		if(param instanceof Map) {
			return this.jdbcTemplate.update(sql, (Map<String, ?>)param);
		}else {
			return this.jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(param));
		}
	}
	
	@Override
	public int update(String sql) {
		return this.jdbcTemplate.update(sql, new EmptySqlParameterSource());
	}
	
	@Override
	public int update(String sql, Object param) {
		if(param instanceof Map) {
			return this.jdbcTemplate.update(sql, (Map<String, ?>)param);
		}else {
			return this.jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(param));
		}
	}
	
	@Override
	public int delete(String sql) {
		return this.jdbcTemplate.update(sql, new EmptySqlParameterSource());
	}
	
	@Override
	public int delete(String sql, Object param) {
		if(param instanceof Map) {
			return this.jdbcTemplate.update(sql, (Map<String, ?>)param);
		}else {
			return this.jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(param));
		}
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String sql) {
		return jdbcTemplate.queryForList(sql, new EmptySqlParameterSource());
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String sql, Object param) {
		if(param instanceof Map) {
			return jdbcTemplate.queryForList(sql, (Map<String, ?>)param);
		}else {
			return this.jdbcTemplate.queryForList(sql, new BeanPropertySqlParameterSource(param));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryForList(String sql, Class<T> elementType) {
		if (elementType == Map.class) {
			return (List<T>)this.queryForList(sql);
		} else if(isExistClass(elementType)){
			return this.jdbcTemplate.queryForList(sql, new EmptySqlParameterSource(), elementType);
		}else {
			return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(elementType));
		}
	}

	@Override
	public <T> List<T> queryForList(String sql, Object param, Class<T> elementType) {
		if(param instanceof Map) {
			return jdbcTemplate.queryForList(sql, (Map<String, ?>)param, elementType);
		}else {
			return this.jdbcTemplate.queryForList(sql, new BeanPropertySqlParameterSource(param), elementType);
		}
	}
	
	@Override
	public <T>T queryForOne(String sql, Class<T> elementType) {
		
		return this.jdbcTemplate.queryForObject(sql, new EmptySqlParameterSource(), elementType);
	}
	
	@Override
	public <T>T queryForOne(String sql,  Object param, Class<T> elementType) {
		if(param instanceof Map) {
			return jdbcTemplate.queryForObject(sql, (Map<String, ?>)param, elementType);
		}else {
			return this.jdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(param), elementType);
		}
	}
	
	public Map<String, Object> queryForMap(String sql, Object param) {
		if(param instanceof Map) {
			return jdbcTemplate.queryForMap(sql, (Map<String, ?>)param);
		}else {
			return this.jdbcTemplate.queryForMap(sql, new BeanPropertySqlParameterSource(param));
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static boolean isExistClass(Class cs) {
		for (Class item : cl) {
			if (item == cs) {
				return true;
			}
		}
		return false;
	}
	
	private static Class[] cl = { Integer.class, int.class, Long.class,
			long.class, Double.class, double.class, Byte.class, byte.class,
			Short.class, short.class, Float.class, float.class, String.class };

	@Override
	public Pagination queryForPagination(int pageNo, int pageSize, String sql, Class<?> elementType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> queryForPagination(int pageNo, int pageSize, String sql, boolean hasTotal,
			Class<T> elementType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination queryForPagination(int pageNo, int pageSize, String sql, Object parameter, Class<?> elementType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> queryForPagination(int pageNo, int pageSize, String sql, Object parameter, boolean hasTotal,
			Class<T> elementType) {
		// TODO Auto-generated method stub
		return null;
	}

}
