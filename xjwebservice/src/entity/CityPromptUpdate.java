package entity;

/**
 * CityPromptUpdate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CityPromptUpdate implements java.io.Serializable {

	// Fields

	private int id;
	private String city;
	private String content;
	private int week;
	private int status;

	// Constructors

	/** default constructor */
	public CityPromptUpdate() {
	}

	/** minimal constructor */
	public CityPromptUpdate(int id, String city) {
		this.id = id;
		this.city = city;
	}

	/** full constructor */
	public CityPromptUpdate(int id, String city, String content, int week, int status) {
		this.id = id;
		this.city = city;
		this.content = content;
		this.week = week;
		this.status = status;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWeek() {
		return this.week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}