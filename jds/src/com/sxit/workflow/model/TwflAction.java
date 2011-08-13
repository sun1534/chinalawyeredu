/**
 * TaskAction.java
 */
package com.sxit.workflow.model;

/**
 * @author 华锋
 * 2008-4-9 下午01:56:22
 *
 */
public class TwflAction {

	private String actionname;
	private String actionurl;
	private int actionid;
	private TwflNode twflnode;
	private String nodeid;
	private String isbixu;
	private String tonext;
	private String stagetime;
	/**
	 * 判断这个action在某个case处是否已经做过处理
	 */
	private boolean ishandled;
	public boolean getIshandled(){
		return this.ishandled;
	}
	
	public void setIshandled(boolean actionIsOver){
		this.ishandled=actionIsOver;
	}
	/**
	 * @return the tonext
	 */
	public String getTonext() {
		return tonext;
	}
	/**
	 * @param tonext the tonext to set
	 */
	public void setTonext(String tonext) {
		this.tonext = tonext;
	}
	/**
	 * @return the actionid
	 */
	public int getActionid() {
		return actionid;
	}
	/**
	 * @param actionid the actionid to set
	 */
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}
	/**
	 * @return the twflnode
	 */
	public TwflNode getTwflnode() {
		return twflnode;
	}
	/**
	 * @param twflnode the twflnode to set
	 */
	public void setTwflnode(TwflNode twflnode) {
		this.twflnode = twflnode;
	}
	/**
	 * @return the isbixu
	 */
	public String getIsbixu() {
		return isbixu;
	}
	/**
	 * @param isbixu the isbixu to set
	 */
	public void setIsbixu(String isbixu) {
		this.isbixu = isbixu;
	}
	/**
	 * @return the actionname
	 */
	public String getActionname() {
		return actionname;
	}
	/**
	 * @param actionname the actionname to set
	 */
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	/**
	 * @return the actionurl
	 */
	public String getActionurl() {
		return actionurl;
	}
	/**
	 * @param actionurl the actionurl to set
	 */
	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	/**
	 * @return the nodeid
	 */
	public String getNodeid() {
		return nodeid;
	}
	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	/**
	 * @return the stagetime
	 */
	public String getStagetime() {
		return stagetime;
	}
	/**
	 * @param stagetime the stagetime to set
	 */
	public void setStagetime(String stagetime) {
		this.stagetime = stagetime;
	}
}