
package com.changpeng.models;

// Generated 2008-5-4 12:08:05 by Hibernate Tools 3.2.0.CR1

import java.sql.Timestamp;

/**
 * 归属于哪个事务所，根据createuser来
 * 
 * Arrangesignup generated by hbm2java
 */
public class Arrangesignup implements java.io.Serializable {

	private int signupid;
	private Arrange arrange;
	private String arrangeperson;
	private String gender;
	private String birthday;
	private String minzu;
	private String policy;
	private String granduatedate;
	private String shixizhenghao;
	private String shixidate;
	private String zigezhenghao;
	private String zigedate;
	private String phone;
	private String address;
	private String career;
	private String createuser;
	private Timestamp createtime;
	private int groupid;
	private String groupname;
	private String lawerno;
	private String remarks;

	public Arrangesignup() {
	}



	

	public int getSignupid() {
		return this.signupid;
	}

	public void setSignupid(int signupid) {
		this.signupid = signupid;
	}

	public Arrange getArrange() {
		return this.arrange;
	}

	public void setArrange(Arrange arrange) {
		this.arrange = arrange;
	}

	public String getArrangeperson() {
		return this.arrangeperson;
	}

	public void setArrangeperson(String arrangeperson) {
		this.arrangeperson = arrangeperson;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMinzu() {
		return this.minzu;
	}

	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}

	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getGranduatedate() {
		return this.granduatedate;
	}

	public void setGranduatedate(String granduatedate) {
		this.granduatedate = granduatedate;
	}

	public String getShixizhenghao() {
		return this.shixizhenghao;
	}

	public void setShixizhenghao(String shixizhenghao) {
		this.shixizhenghao = shixizhenghao;
	}

	public String getShixidate() {
		return this.shixidate;
	}

	public void setShixidate(String shixidate) {
		this.shixidate = shixidate;
	}

	public String getZigezhenghao() {
		return this.zigezhenghao;
	}

	public void setZigezhenghao(String zigezhenghao) {
		this.zigezhenghao = zigezhenghao;
	}

	public String getZigedate() {
		return this.zigedate;
	}

	public void setZigedate(String zigedate) {
		this.zigedate = zigedate;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}





	/**
	 * @return the lawerno
	 */
	public String getLawerno() {
		return lawerno;
	}





	/**
	 * @param lawerno the lawerno to set
	 */
	public void setLawerno(String lawerno) {
		this.lawerno = lawerno;
	}





	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}





	/**
	 * @param groupname the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
