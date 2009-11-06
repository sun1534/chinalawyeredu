package com.changpeng.global.model;

/**
 * GlobalPartner entity. @author MyEclipse Persistence Tools
 */

public class GlobalPartner implements java.io.Serializable {

	// Fields

	private Integer id;
	private Short type;
	private Integer provinceId;
	private Integer cityId;
	private String partnerName;
	private String fullName;
	private String partnerAddress;
	private String person;
	private String personPhone;
	private String personEmail;
	private String contact;
	private String contactPhone;
	private String contactEmail;

	// Constructors

	/** default constructor */
	public GlobalPartner() {
	}

	/** minimal constructor */
	public GlobalPartner(Short type, Integer provinceId, Integer cityId,
			String partnerName) {
		this.type = type;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.partnerName = partnerName;
	}

	/** full constructor */
	public GlobalPartner(Short type, Integer provinceId, Integer cityId,
			String partnerName, String fullName, String partnerAddress,
			String person, String personPhone, String personEmail,
			String contact, String contactPhone, String contactEmail) {
		this.type = type;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.partnerName = partnerName;
		this.fullName = fullName;
		this.partnerAddress = partnerAddress;
		this.person = person;
		this.personPhone = personPhone;
		this.personEmail = personEmail;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPartnerName() {
		return this.partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPartnerAddress() {
		return this.partnerAddress;
	}

	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPersonPhone() {
		return this.personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonEmail() {
		return this.personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}