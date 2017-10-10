package com.upbos.framework.data.dialect;

/**
 * <p>Title: MySql5Dialect.java</p>
 * <p>Description: 用来实现mysql数据库物理分页</p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: upbos.com</p>
 * @author wangjz
 * @date 2013年10月13日
 * @version 2.0.0
 */
public class MySql5Dialect extends Dialect {

	/**
	 * <p>Title: getLimitString</p>
	 * <p>Description: 利用mysql物理分页limit特性进行分页</p>
	 * @see com.upbos.framework.data.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 * @param sql 需要分页的原始sql
	 * @param offset 偏移量
	 * @param limit 条数
	 * @return
	 * @author wangjz
	 * @since 2013年10月13日
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return MySql5PageHepler.getLimitString(sql, offset, limit);
	}
}
