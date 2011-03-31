package com.xjdzjc.model;

public class City_Tpl_content {
	public int id;
	public String cityname;
	public String city;
	public String template_con_details;
	public String template_con_simple;
	public String template_con_null;
	public String gateway_id = "SMS3";
	public String sql_content;
	public int sendday;
	public int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTemplate_con_details() {
		return template_con_details;
	}

	public void setTemplate_con_details(String templateConDetails) {
		template_con_details = templateConDetails;
	}

	public String getTemplate_con_simple() {
		return template_con_simple;
	}

	public void setTemplate_con_simple(String templateConSimple) {
		template_con_simple = templateConSimple;
	}

	public String getSql_content() {
		return sql_content;
	}

	public void setSql_content(String sqlContent) {
		sql_content = sqlContent;
	}

	public int getSendday() {
		return sendday;
	}

	public void setSendday(int sendday) {
		this.sendday = sendday;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTemplate_con_null() {
		return template_con_null;
	}

	public void setTemplate_con_null(String templateConNull) {
		template_con_null = templateConNull;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gatewayId) {
		gateway_id = gatewayId;
	}

}
