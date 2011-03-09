package com.uu800.admin.base.entity;

public class Dict 
{
	private String code;
	private String name;
	
	public Dict(String code,String name)
	{
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
