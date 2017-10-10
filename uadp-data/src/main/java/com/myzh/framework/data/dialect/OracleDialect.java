package com.myzh.framework.data.dialect;

/**
 * <p>Title: OracleDialect.java</p>
 * <p>Description: 用来实现oracle数据库物理分页</p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: myzh.com</p>
 * @author wangjz
 * @date 2013年10月13日
 * @version 2.0.0
 */
public class OracleDialect extends Dialect {
	
	/**
	 * <p>Title: getLimitString</p>
	 * <p>Description: 获取oracle物理分页后的sql语句</p>
	 * @see com.myzh.framework.data.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 * @param sql 需要分页的sql语句
	 * @param offset 偏移量
	 * @param limit 条数
	 * @return 分页后的sql语句
	 * @author wangjz
	 * @since 2013年10月13日
	 */
	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ > " + offset
				+ " and rownum_ <= " + (offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}
}
