package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	// Fields

	private int userid;
	private SysGroup sysGroup;
	private SysRole sysRole;
	private String loginname;
	private String password;
	private String username;
	private String employeeno;
	private int status;
	private boolean delflag;
	private String style;
	private String gender;
	private String email;
	private Date birthday;
	private String officephone;
	private String mobile;
	private String comments;
	
	private int provinceid;
	private int cityid;
	private int officeid;
	
	private String createuser;
	private int createuserid;
	private Timestamp createtime;
	private String systemno;
	private Set<SysRight> sysRights = new HashSet<SysRight>(0);

	public int getFieldvalue(String field){
		if(field.equalsIgnoreCase("provinceid"))
		  return this.provinceid;
		if(field.equalsIgnoreCase("cityid"))
			  return this.cityid;
		if(field.equalsIgnoreCase("officeid"))
			  return this.officeid;
		return 0;
	}
	
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	

	// Property accessors

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysGroup getSysGroup() {
		return this.sysGroup;
	}

	public void setSysGroup(SysGroup sysGroup) {
		this.sysGroup = sysGroup;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmployeeno() {
		return this.employeeno;
	}

	public void setEmployeeno(String employeeno) {
		this.employeeno = employeeno;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getDelflag() {
		return this.delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}



	/**
	 * @return the sysRights
	 */
	public Set<SysRight> getSysRights() {
		return sysRights;
	}



	/**
	 * @param sysRights the sysRights to set
	 */
	public void setSysRights(Set<SysRight> sysRights) {
		this.sysRights = sysRights;
	}


	/**
	 * 绑定这个用户的此次登录的id
	 */
	private int loginId;
    public void setLoginId(int loginId){
    	this.loginId=loginId;
    }
    public int getLoginId(){
    	return this.loginId;
    }
	
	/**
	 * 记录最近一次的登录时间
	 */
	private Timestamp lastLoginTime;
	public void setLastLoginTime(Timestamp lastLoginTime){
		this.lastLoginTime=lastLoginTime;
	}
	public Timestamp getLastLoginTime(){
		return this.lastLoginTime;
	}
	/**
	 * 总计登录次数
	 */
	private int loginCount;
	public void setLoginCount(int loginCount){
		this.loginCount=loginCount;
	}
	public int getLoginCount(){
		return this.loginCount;
	}
	/**
	 * 此人最终的的权限列表
	 */
	private Set<String> rightList;
	public void setRightList(Set<String> rightList){
		this.rightList=rightList;
	}
	public Set<String> getRightList(){
		return this.rightList;
	}
	
	/**
	 * 此人的菜单显示
	 */
	private List<SysRight> userMenus;
	public void setUserMenus(List<SysRight> userMenus){
		this.userMenus=userMenus;
	}
	
	public List<SysRight> getUserMenus(){
		return this.userMenus;
	}
	
	/**
	 * 判断这个人是否有权限
	 * @param rightCode
	 * @return
	 */
	public boolean hasRight(String rightCode){
		if(rightCode==null||rightCode.equals(""))
			return true;
		return rightList.contains(rightCode);
	}



	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}



	/**
	 * @param provinceid the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}



	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}



	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}



	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}



	/**
	 * @param officeid the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}

	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}

	/**
	 * @param systemno the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}
	
}