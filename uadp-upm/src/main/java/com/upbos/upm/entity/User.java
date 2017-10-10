package com.upbos.upm.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = -6957920501545113819L;
	private String uid;
	private String name;
	private String loginName;
	private String password;
	private String tel;
	private String mobile;
	private String fax;
	private String email;
	private Integer toOrgId;
	private String toOrgName;
	private String idNo;
	private String addr;
	private Date createDate;
	private String createUserId;
	private String type;
	private String typeName;
	private String status;
	private String pinyin;
	private String extCode;
	private String remark;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
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
	
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}



	public static final String STATUS_NORMAL = "1";
	public static final String STATUS_LOGOFF = "2";
	public static final String STATUS_FREEZE = "3";
}
