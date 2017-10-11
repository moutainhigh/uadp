package com.upbos.framework.data.mybatis;

import java.util.List;
import java.util.Map;

import com.upbos.framework.data.page.Pagination;

/**
 * <p>Title: MybatisTplDao.java</p>
 * <p>Description: 利用mybatis实现数据持久层的访问数据接口</p>
 * <p>Copyright: Copyright (c) 2010-2017</p>
 * <p>Company: upbos.com</p>
 * @author wangjzd
 * @date 2013年10月13日
 * @version 2.0.0
 */
public interface MybatisTplDao {
	
	/**
	 * <p>Title: insert</p>
	 * <p>Description: 插入数据，例如：insert into table(p1, p2) values('1','2')</p>
	 * @param sqlId sqlId 在mapper文件中定义的SQL Id
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public int insert(String sqlId);
	
	/**
	 * <p>Title: insert</p>
	 * <p>Description: 插入数据,例如： insert into table(p1, p2) values(#{p1},#{p2})</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象  
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public int insert(String sqlId, Object parameter);

	/**
	 * <p>Title: batchInsert</p>
	 * <p>Description: 插入数据,例如： insert into table(p1, p2) values(#{p1},#{p2}),(#{p3},#{p4}),...</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象
	 * @param batchSize 每段的条数
	 * @return 影响的行数
	 * @author zhurs
	 * @since 2016年11月11日
	 */
	public int batchInsert(String sqlId, List parameter, int batchSize);
	
	/**
	 * <p>方法名称：update</p>
	 * <p>方法描述：更新数据，主要用于update的SQL语句，例如：update table set p1='1' where p2='2'</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public int update(String sqlId);
	
	/**
	 * <p>Title：update</p>
	 * <p>Description：更新数据，主要用于update的SQL语句，例如：update table set p1=#{p1} where p2=#{p2}</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象  
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月10日
	 */
	public int update(String sqlId, Object parameter);
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 删除数据，用于delete的SQL语句，例如：delete from table where p1='1'</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public int delete(String sqlId);
	
	/**
	 * <p>Title: delete</p>
	 * <p>Description: 删除数据，用于delete的SQL语句，例如：delete from table where p1=#{p1}</p>
	 * @param sqlId sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象
	 * @return 影响的行数
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public int delete(String sqlId, Object parameter);

	/**
	 * <p>Title: queryForList</p>
	 * <p>Description: 查询数据，例如：select p1,p2 from table</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzdd
	 * @since 2013年10月13日
	 */
	public <T> List<T> queryForList(String sqlId);
	
	/**
	 * <p>Title: queryForList</p>
	 * <p>Description: 查询数据,例如：select p1,p2 from table where p1=#{p1}</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public <T> List<T> queryForList(String sqlId, Object parameter);
	
	/**
	 * <p>Title: queryForPagination</p>
	 * <p>Description: 分页查询数据</p>
	 * @param pageNo 当前页码，从1开始
	 * @param pageSize 每页条数
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public Pagination queryForPagination(int pageNo, int pageSize, String sqlId);
	

	/**
	 * <p>Title: queryForPagination</p>
	 * <p>Description: 分页查询数据</p>
	 * @param pageNo 当前页码，从1开始
	 * @param pageSize 每页条数
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象  
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public Pagination queryForPagination(int pageNo, int pageSize, String sqlId, Object parameter);

	/**
	 * <p>Title: queryForPagination</p>
	 * <p>Description: 分页查询数据</p>
	 * @param pageNo 当前页码，从1开始
	 * @param pageSize 每页条数
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzdd
	 * @since Mar 24, 2014
	 */
	public <T> List<T> queryForPaginationWithoutTotal(int pageNo, int pageSize, String sqlId);

	/**
	 * <p>Title: queryForPagination</p>
	 * <p>Description: 分页查询数据</p>
	 * @param pageNo 当前页码，从1开始
	 * @param pageSize 每页条数
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象  
	 * @return 查询结果， list中的对象类型可以是引用类型对象或pojo对象
	 * @author wangjzdd
	 * @since Mar 24, 2014
	 */
	public <T> List<T> queryForPaginationWithoutTotal(int pageNo, int pageSize, String sqlId, Object parameter);
	
	/**
	 * <p>Title: queryForMap</p>
	 * <p>Description: 查询数据，SQL语句的查询结果中只能包含一条记录</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 返回一个map对象  
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public <K, V> Map<K, V> queryForMap(String sqlId);
	

	/**
	 * <p>Title: queryForMap</p>
	 * <p>Description: 查询数据，SQL语句的查询结果中只能包含一条记录</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型或pojo对象
	 * @return 返回一个Map对象
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public <K, V> Map<K, V> queryForMap(String sqlId, Object parameter);
	
	/**
	 * <p>Title: queryForOne</p>
	 * <p>Description: 查询数据，SQL语句的查询结果中只能包含一条记录</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @return 返回一个对象，可以是引用类型对象或pojo对象  
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public <T> T queryForOne(String sqlId);

	/**
	 * <p>Title: queryForOne</p>
	 * <p>Description: 查询数据，SQL语句的查询结果中只能包含一条记录</p>
	 * @param sqlId 在mapper文件中定义的SQL Id
	 * @param parameter 可以是引用类型对象或pojo对象
	 * @return 返回一个对象，可以是引用类型对象或pojo对象
	 * @author wangjzd
	 * @since 2013年10月13日
	 */
	public <T> T queryForOne(String sqlId, Object parameter);
}
