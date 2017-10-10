package com.upbos.session;

import java.io.Serializable;

public class SessionUser implements Serializable {
	private static final long serialVersionUID = -6957920501545113819L;
	private String uid;
	private String name;
	private String loginName;
	private Integer toOrgId;
	private String toOrgName;
	private String type;
	private String typeName;
	private String extCode;
	private boolean isTempUser;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public Integer getToOrgId() {
		return toOrgId;
	}
	public void setToOrgId(Integer toOrgId) {
		this.toOrgId = toOrgId;
	}
	public String getToOrgName() {
		return toOrgName;
	}
	public void setToOrgName(String toOrgName) {
		this.toOrgName = toOrgName;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getExtCode() {
		return extCode;
	}
	
	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public boolean isTempUser() {
		return isTempUser;
	}

	public void setTempUser(boolean tempUser) {
		isTempUser = tempUser;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public static final String STATUS_NORMAL = "1";
	public static final String STATUS_LOGOFF = "2";
	public static final String STATUS_FREEZE = "3";

}
