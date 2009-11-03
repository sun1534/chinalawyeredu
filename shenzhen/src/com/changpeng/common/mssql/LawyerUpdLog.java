/**
 * LawyerUpdLog.java
 */
package com.changpeng.common.mssql;

import com.changpeng.models.system.SysUser;

/**
 * @author 华锋
 * 2008-6-16 下午10:55:26
 *
 */
public class LawyerUpdLog {

	private static String theurl="http://www.szlawyers.com/";
	
	public int updlogid;
	public int lawid;
	public int enterpriseid;
	public String lawno;
	public String lawername;
	public String telephone;
	public String memo;
//	public String licenceno;
	public String password;
	public String lsxp;
//	public String trainkh;
//	public String carddate;
	public String dotype;
	
	public SysUser getLawer(){

		SysUser sysUser=new SysUser();
		
		sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysUser.setCreateuser("同步接口");
		sysUser.setComments("同步接口:" + lsxp);
		sysUser.setDelflag(false);
		sysUser.setStatus((byte) 0);
		sysUser.setPassword(password);
		sysUser.setLoginname(lawno);
		sysUser.setLawerno(lawno);
		sysUser.setUsername(lawername);
		sysUser.setCertno("");
		sysUser.setUserid(lawid);

//		sysUser.setCarddate(this.carddate);
//		sysUser.setCardno(this.trainkh!=null&&this.trainkh.equals("")?null:this.trainkh);
		return sysUser;		
	}	
}
