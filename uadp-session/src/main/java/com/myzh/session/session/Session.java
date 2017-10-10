package com.myzh.session.session;

import com.myzh.session.SessionUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>ClassName: Session</p>
 * <p>Description: </p>
 * <p>Company： myzh.com</p>
 * @author wangjz
 * @date 2016年11月18日 下午9:52:04
 * @version v2.5.0
 */
@SuppressWarnings("serial")
public class Session implements Serializable {

	/* 系统名称  */
	private String app;

	/* 主键 ID */
	private String id;

	private String uid;

	/* 登录 IP */
	private String ip;

	/* 创建 session 当前系统时间 */
	private long time = System.currentTimeMillis();
	
	private Map<String, Object> data = new HashMap<String, Object>();
	
	private SessionUser user;
	
	public String getApp() {
		return app;
	}
	
	public void setApp( String app ) {
		this.app = app;
	}
	
	
	public String getId() {
		return id;
	}

	
	public void setId( String id ) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid( String uid ) {
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime( long time ) {
		this.time = time;
	}
	
	public void putAttr(String key, Object value) {
		this.data.put(key, value);
	}
	
	public void putAtrr(Map<String, Object> attrs) {
		this.data.putAll(attrs);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttr(String key) {
		return (T)this.data.get(key);
	}
	
	public void removeAtrr(String key) {
		this.data.remove(key);
	}

	public SessionUser getUser() {
		return user;
	}

	public void setUser(SessionUser user) {
		this.user = user;
	}
}
