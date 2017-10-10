package com.upbos.framework.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.support.ResourcePropertySource;


/**
 * <p>Title: ConfigLoaderListener.java</p>
 * <p>Description: 系统启动时加载config.perperties</p>
 * <p>Copyright: Copyright (c) 2015-2016</p>
 * <p>Company: 明医众禾科技（北京）有限责任公司</p>
 * @author wangjz
 * @date 2016年1月30日
 * @version 2.0.0
 */
public class ConfigLoaderListener implements ServletContextListener {

	private static Logger log = LogManager.getLogger(ConfigLoaderListener.class); 
	
	public static String dbType = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent evt) {

	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		String limitKey[] = {"context.component.scan", "db.type"};
		String envConfigLocation = (String)evt.getServletContext().getInitParameter("envConfigLocation");
		if(envConfigLocation == null) {
			envConfigLocation = "classpath:config/config.properties";
			log.debug("系统默认使用config/config.properties配置.");
		}
		try {
			ResourcePropertySource rps = new ResourcePropertySource("resource", envConfigLocation);
			String[] propertyNames = rps.getPropertyNames();
			if(propertyNames != null && propertyNames.length > 0) {
				for(String property : propertyNames) {
					for(int i = 0; i < limitKey.length; i++) {
						if(limitKey[i].equals(property)) {
							String value = (String)rps.getProperty(property);
							evt.getServletContext().setInitParameter(property, value);
							continue;
						}
					}
				}
			}
			//加载数据库配置文件
			dbType = (String)evt.getServletContext().getInitParameter("db.type"); 
			if(dbType != null) {
				evt.getServletContext().setInitParameter("db.location", "classpath:config/config-" + dbType + ".properties");
			}
		} catch (Exception e) {
			log.error("加载配置文件classpath:config/config-{}.properties时发生错误，错误信息如下：", dbType, e);
		}
	}

}
