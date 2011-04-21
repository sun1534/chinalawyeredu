package entity;

import freemarker.template.Template;

/**
 * CityTemplateContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CityTemplateContent implements java.io.Serializable {

	// Fields

	private int id;
	private String cityname;
	private String city;
	private String templateConDetails;
	private String templateConNull;
	private String templateConSimple;
	private String sqlContent;
	private int sendday;
	private int status;
	private String gatewayId;
	
	private Template detailtemplate;
	private Template nulltemplate;
	private Template simpletemplate; 

	// Constructors

	/** default constructor */
	public CityTemplateContent() {
	}

	/** minimal constructor */
	public CityTemplateContent(int id, String city) {
		this.id = id;
		this.city = city;
	}

	/** full constructor */
	public CityTemplateContent(int id, String cityname, String city, String templateConDetails,
			String templateConNull, String templateConSimple, String sqlContent, int sendday, int status,
			String gatewayId) {
		this.id = id;
		this.cityname = cityname;
		this.city = city;
		this.templateConDetails = templateConDetails;
		this.templateConNull = templateConNull;
		this.templateConSimple = templateConSimple;
		this.sqlContent = sqlContent;
		this.sendday = sendday;
		this.status = status;
		this.gatewayId = gatewayId;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTemplateConDetails() {
		return this.templateConDetails;
	}

	public void setTemplateConDetails(String templateConDetails) {
		this.templateConDetails = templateConDetails;
	}

	public String getTemplateConNull() {
		return this.templateConNull;
	}

	public void setTemplateConNull(String templateConNull) {
		this.templateConNull = templateConNull;
	}

	public String getTemplateConSimple() {
		return this.templateConSimple;
	}

	public void setTemplateConSimple(String templateConSimple) {
		this.templateConSimple = templateConSimple;
	}

	public String getSqlContent() {
		return this.sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	public int getSendday() {
		return this.sendday;
	}

	public void setSendday(int sendday) {
		this.sendday = sendday;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	/**
	 * @return the detailtemplate
	 */
	public Template getDetailtemplate() {
		return detailtemplate;
	}

	/**
	 * @param detailtemplate the detailtemplate to set
	 */
	public void setDetailtemplate(Template detailtemplate) {
		this.detailtemplate = detailtemplate;
	}

	/**
	 * @return the nulltemplate
	 */
	public Template getNulltemplate() {
		return nulltemplate;
	}

	/**
	 * @param nulltemplate the nulltemplate to set
	 */
	public void setNulltemplate(Template nulltemplate) {
		this.nulltemplate = nulltemplate;
	}

	/**
	 * @return the simpletemplate
	 */
	public Template getSimpletemplate() {
		return simpletemplate;
	}

	/**
	 * @param simpletemplate the simpletemplate to set
	 */
	public void setSimpletemplate(Template simpletemplate) {
		this.simpletemplate = simpletemplate;
	}

}