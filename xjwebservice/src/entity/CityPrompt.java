package entity;

/**
 * CityPrompt entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CityPrompt implements java.io.Serializable {

	// Fields

	private int id;
	private String cityname;
	private String city;
	private String sqlContent;
	private int status;

	// Constructors

	/** default constructor */
	public CityPrompt() {
	}

	/** minimal constructor */
	public CityPrompt(int id, String city) {
		this.id = id;
		this.city = city;
	}

	/** full constructor */
	public CityPrompt(int id, String cityname, String city, String sqlContent, int status) {
		this.id = id;
		this.cityname = cityname;
		this.city = city;
		this.sqlContent = sqlContent;
		this.status = status;
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

	public String getSqlContent() {
		return this.sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}