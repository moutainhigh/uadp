package com.myzh.framework.data.mybatis.rowbounds;

import org.apache.ibatis.session.RowBounds;

import com.myzh.framework.data.page.Pagination;

/**
 * <p>Title: PaginationRowBounds.java</p>
 * <p>Description: pagination row bound</p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: myzh.com</p>
 * @author wangjz
 * @date 2013年10月13日
 * @version 2.0.0
 */
public class PaginationRowBounds extends RowBounds {
	private Pagination pagination;
	private boolean hasTotal = true;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public boolean isHasTotal() {
		return hasTotal;
	}

	public void setHasTotal(boolean hasTotal) {
		this.hasTotal = hasTotal;
	}

	public PaginationRowBounds(int offset, int limit) {
		super(offset, limit);
	}
}
