package com.upbos.framework.data.dialect;


/**
 * <p>Title: Dialect.java</p>
 * <p>Description: 数据库方言抽象接口，主要用来定义数据库分页模板，生成数据库分页语句 </p>
 * <p>Copyright: Copyright (c) 2010-2013</p>
 * <p>Company: upbos.com</p>
 * @author wangjz
 * @date 2013年10月13日
 * @version 2.0.0
 */
public abstract class Dialect { 

	/**
	 * 数据类型
	 */
	public static enum Type{
		MARIADB,
		MYSQL,
		ORACLE,
		POSTGRESQL,
		SQLSERVER,
		SYBASE,
		HSQLDB,
		DB2
	}
	
	/**
	 * <p>方法名称：getLimitString</p>
	 * <p>方法描述：获取分页后的sql语句</p>
	 * @param sql 需要分页的sql
	 * @param offset 
	 * @param limit
	 * @return
	 * @author wangjz
	 * @since 2013年10月10日
	 */
	public abstract String getLimitString(String sql, int offset, int limit);

}
