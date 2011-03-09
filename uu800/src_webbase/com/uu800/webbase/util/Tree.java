package com.uu800.webbase.util;

public class Tree 
{
    private String id;
    private String text;
    private boolean leaf;
    private String cls;
    
    
    public Tree(String id,String text)
    {
    	this.id = id;
    	this.text = text;
    }
    
    public Tree(String id,String text,boolean leaf,String cls)
    {
    	this.id = id;
    	this.text = text;
    	this.leaf = leaf;
    	this.cls = cls;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
}

