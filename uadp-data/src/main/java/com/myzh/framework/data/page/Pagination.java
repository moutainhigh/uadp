package com.myzh.framework.data.page;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: Pagination.java</p>
 * <p>Description: 分页对象，用于数据库分页</p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: myzh.com</p>
 * @author wangjzd
 * @date 2013年10月13日
 * @version 2.0.0
 */
public class Pagination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页码,页码从1开始
	 */
	private int pageNo;
	
	/**
	 * 当前页行数
	 */
	private int pageSize;
	
	/**
	 * 总行数
	 */
	private int total;
	
	/**
	 * 当前页的数据 
	 */
	@SuppressWarnings("rawtypes")
	private List rows;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getRows() {
		return rows;
	}
	
	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
}
