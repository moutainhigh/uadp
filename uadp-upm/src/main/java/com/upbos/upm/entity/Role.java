package com.upbos.upm.entity;

import java.io.Serializable;

public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String code;
	private String remark;
	private Integer toOrgId;
	private String toOrgName;
	private String isGlobal;
	private Boolean isFixed;
	private Boolean isLocalRole;
	private String orgType;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public Boolean getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}

	public Boolean getIsLocalRole() {
		return isLocalRole;
	}

	public void setIsLocalRole(Boolean isLocalRole) {
		this.isLocalRole = isLocalRole;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
