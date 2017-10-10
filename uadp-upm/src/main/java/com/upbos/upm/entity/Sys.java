package com.upbos.upm.entity;

import java.io.Serializable;
import java.util.Date;

public class Sys implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String prefixUrl;
	private String remark;
	private Boolean isFixed;
	private Boolean isCheckedLogin;
	private String loginUrl;
	private String loginUsername;
	private String loginPassword;
	private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefixUrl() {
		return prefixUrl;
	}
	public void setPrefixUrl(String prefixUrl) {
		this.prefixUrl = prefixUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsFixed() {
		return isFixed;
	}
	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}
	public Boolean getIsCheckedLogin() {
		return isCheckedLogin;
	}
	public void setIsCheckedLogin(Boolean isCheckedLogin) {
		this.isCheckedLogin = isCheckedLogin;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getLoginUsername() {
		return loginUsername;
	}
	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
