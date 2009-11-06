package com.changpeng.core.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TinfInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfInfo implements java.io.Serializable {

	// Fields

	private int infoid;
	private int typeid;
	private String infoname;
	private String filenumber;
	private String content;
	private int statusid;	//1:草稿，2:待批，3:通过审批，4:未通过审批，0:发布，-1:冻结
	private int groupid;
	private int createuser;
	private Timestamp createtime;
	private int modifyuser;
	private Timestamp modifytime;
	private String waitid;
	private boolean isweb;
	

//加入起始和终止时间
	private Date start;
	private Date end;
	// Constructors
private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
public String getStartstr(){
		if(start==null||start.equals(""))
			return "";
		
		return df.format(start);
	}
	public String getEndstr(){
		if(end==null||end.equals(""))
			return "";
		
		return df.format(end);
	}
	/** default constructor */
	public TinfInfo() {
	}

	public int getInfoid() {
		return infoid;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getInfoname() {
		return infoname;
	}

	public void setInfoname(String infoname) {
		this.infoname = infoname;
	}

	public String getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(String filenumber) {
		this.filenumber = filenumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getCreateuser() {
		return createuser;
	}

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(int modifyuser) {
		this.modifyuser = modifyuser;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	public String getWaitid() {
		return waitid;
	}

	public void setWaitid(String waitid) {
		this.waitid = waitid;
	}

	public boolean getIsweb() {
		return isweb;
	}

	public void setIsweb(boolean isweb) {
		this.isweb = isweb;
	}


	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}