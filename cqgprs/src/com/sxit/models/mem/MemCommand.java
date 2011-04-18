package com.sxit.models.mem;

import java.sql.Timestamp;
import java.util.List;

import com.sxit.common.Globals;
import com.sxit.communicateguard.service.MemService;

/**
 * MemDevicecommand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemCommand implements java.io.Serializable {

	// Fields

	private int commandid;
	private String commananame;
	private String commandscript;
	private int commandtype;
	private String typename;
	private Timestamp createtime;
	private int createuser;
	private String createusername;
	private String plugin;
	public List getDeviceIdList(){
		MemService memservice=(MemService)Globals.getBean("memService");
		return memservice.getDevicesByCommandId(this.commandid);
	}
	/**
	 * @return the commandid
	 */
	public int getCommandid() {
		return commandid;
	}
	/**
	 * @param commandid the commandid to set
	 */
	public void setCommandid(int commandid) {
		this.commandid = commandid;
	}
	/**
	 * @return the commananame
	 */
	public String getCommananame() {
		return commananame;
	}
	/**
	 * @param commananame the commananame to set
	 */
	public void setCommananame(String commananame) {
		this.commananame = commananame;
	}
	/**
	 * @return the commandscript
	 */
	public String getCommandscript() {
		return commandscript;
	}
	/**
	 * @param commandscript the commandscript to set
	 */
	public void setCommandscript(String commandscript) {
		this.commandscript = commandscript;
	}
	/**
	 * @return the commandtype
	 */
	public int getCommandtype() {
		return commandtype;
	}
	/**
	 * @param commandtype the commandtype to set
	 */
	public void setCommandtype(int commandtype) {
		this.commandtype = commandtype;
	}
	/**
	 * @return the typename
	 */
	public String getTypename() {
		return typename;
	}
	/**
	 * @param typename the typename to set
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the createuser
	 */
	public int getCreateuser() {
		return createuser;
	}
	/**
	 * @param createuser the createuser to set
	 */
	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}
	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}
	/**
	 * @param createusername the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	/**
	 * @return the plugin
	 */
	public String getPlugin() {
		return plugin;
	}
	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}
	
}