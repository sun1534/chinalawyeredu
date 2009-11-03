/**
 * UserUpdLog.java
 */

package com.changpeng.common.mssql;

import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-6-16 下午10:55:16
 * 
 */
public class UserUpdLog {

	public int updlogid;
	public int id;
	public String licenceno;
	public String username;
	public String password;
	// 1是律协管理员3是事务所管理员
	public int power;
	public String telephone;
	public String telmail;
	public String mailaddr;
	public String mailno;
	public String mastername;
	public String telcall;
	public String mobile;
	public String shortname;
	public String dotype;

	public SysGroup getSysGroup() {
		SysGroup sysGroup = new SysGroup();
		sysGroup.setGroupid(this.id);
		sysGroup.setGroupname(this.username);
		sysGroup.setGroupenname(this.licenceno);
		sysGroup.setComments(this.mailaddr);
		sysGroup.setContacter(this.telcall);
		sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysGroup.setCreateuser("定时同步");
		sysGroup.setDelflag(false);
		sysGroup.setPhone(this.telephone);

		if (this.power == 1) {// 1是系统管理员
			sysGroup.setGrouplevel((short) 1);
			sysGroup.setParentid(0);
		}
		else {// 3是事务所管理员，只对1进行sysuser的变化,不考虑sysgroup的变化
			sysGroup.setGrouplevel((short) 2);
			sysGroup.setParentid(1);
		}
		return sysGroup;
	}

	public SysUser getSysUser() {
		SysUser sysUser = new SysUser();
		sysUser.setUserid(this.id);
		sysUser.setUsername(this.username);
		sysUser.setLoginname(this.licenceno);
		sysUser.setPassword(this.password);
		sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysUser.setComments("数据同步");
		sysUser.setCreateuser("数据同步");
		sysUser.setDelflag(false);
		sysUser.setOfficephone(this.telephone);

		return sysUser;
	}
}