package com.sxit.system.util;

import java.util.List;

public class Menu {
	
//    private List<Menu> children;
    private long id;            //ID
    private String text;          //节点显示
    private String cls;           //图标
    private boolean leaf;         //是否叶子
    private String href;          //链接
    private String hrefTarget;    //链接指向
    private boolean singleClickExpand;
	public boolean isSingleClickExpand() {
		return singleClickExpand;
	}
	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
//	public List<Menu> getChildren() {
//		return children;
//	}
//	public void setChildren(List<Menu> children) {
//		this.children = children;
//	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHrefTarget() {
		return hrefTarget;
	}
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
}
