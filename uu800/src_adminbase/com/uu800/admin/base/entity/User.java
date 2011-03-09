package com.uu800.admin.base.entity;


/**
 * TsysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User  extends Userinfo implements java.io.Serializable {

	// Fields
	private long userid; //用户id
	private long enterpriseid;//企业id
	private String srcnumber; //源号码
	private String username;
	private String name;
	private String password;
	private int status;
	private String userip;
	private int style;
	private int usertype;
	private int typeId; //TYPE_ID        企业/个人 用户类型(作为短信网关接口表判断1，2，3，4)   
	private long corpid;
	private String corpcode;
	private String corpname;
	private String token;
	private String areacode;
	private String areaname;
	private String logintype;  //登录类型 ： SYS：运营商、ENT：企业用户、USR：个人用户
	
	
	// Constructors
	/** default constructor */
	public User() {
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public long getCorpid() {
		return corpid;
	}
	public void setCorpid(long corpid) {
		this.corpid = corpid;
	}
	public String getCorpcode() {
		return corpcode;
	}
	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getAreacode()
	{
		return areacode;
	}
	public void setAreacode(String areacode)
	{
		this.areacode = areacode;
	}
	public String getLogintype()
	{
		return logintype;
	}
	public void setLogintype(String logintype)
	{
		this.logintype = logintype;
	}
	public String getAreaname()
	{
		return areaname;
	}
	public void setAreaname(String areaname)
	{
		this.areaname = areaname;
	}
	public long getEnterpriseid()
	{
		return enterpriseid;
	}
	public void setEnterpriseid(long enterpriseid)
	{
		this.enterpriseid = enterpriseid;
	}
	public int getTypeId()
	{
		return typeId;
	}
	public void setTypeId(int typeId)
	{
		this.typeId = typeId;
	}
	public String getSrcnumber() {
		return srcnumber;
	}
	public void setSrcnumber(String srcnumber) {
		this.srcnumber = srcnumber;
	}
	
	



	
}