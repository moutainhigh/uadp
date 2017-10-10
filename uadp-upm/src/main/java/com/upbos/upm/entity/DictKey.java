package com.upbos.upm.entity;

import java.io.Serializable;

public class DictKey implements Serializable{
	private static final long serialVersionUID = -2661062902582494144L;
	
	private String key;
	private String name;
	private String remark;
	private Boolean isFixed;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
