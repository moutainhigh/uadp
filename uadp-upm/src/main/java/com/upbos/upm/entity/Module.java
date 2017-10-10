package com.upbos.upm.entity;

import java.util.List;

public class Module implements Comparable<Module>{
	private Integer id;
	private String name;
	private String url;
	private String cascade;
	private String remark;
	private Integer status;
	private Integer parentId;
	private String icon;
	private Integer isSingle;
	private Integer isPopup;
	private Integer isAutorun;
	private Boolean isLeaf;
	private Integer isFixed;
	private Integer idx;
	private List<Module> children;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCascade() {
		return cascade;
	}
	public void setCascade(String cascade) {
		this.cascade = cascade;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getIsSingle() {
		return isSingle;
	}
	public void setIsSingle(Integer isSingle) {
		this.isSingle = isSingle;
	}
	public Integer getIsPopup() {
		return isPopup;
	}
	public void setIsPopup(Integer isPopup) {
		this.isPopup = isPopup;
	}
	public Integer getIsAutorun() {
		return isAutorun;
	}
	public void setIsAutorun(Integer isAutorun) {
		this.isAutorun = isAutorun;
	}
	
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getIsFixed() {
		return isFixed;
	}
	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public List<Module> getChildren() {
		return children;
	}
	public void setChildren(List<Module> children) {
		this.children = children;
	}
	
	@Override
	public int compareTo(Module o) {
		return this.idx.compareTo(o.idx);
	}
}
