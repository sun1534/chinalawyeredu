/**    
 * @Title: WebPara.java  
 * @Package com.changpeng.common  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author 刘哈哈    
 * @date Mar 30, 2012 9:26:51 PM 
*/ 
package com.changpeng.common;

/**  
 * @author 刘哈哈  
 *    
 */

public class WebPara {
	private String indexpic=Constants.DEFAULT_INDEX_PIC;
	private String logopath=Constants.DEFAULT_LOGO_PATH;
	private String sysname=Constants.DEFAULT_SYS_NAME;
	private String topbarpic=Constants.DEFAULT_TOP_BAR_PIC;
	private boolean havelocal=Constants.DEFAULT_HAVELOCAL;
	private String currentdomain="";
	/**  
	 * @return the currentdomain  
	 */
	
	public String getCurrentdomain() {
		return currentdomain;
	}
	/**  
	 * @param currentdomain the currentdomain to set  
	 */
	
	public void setCurrentdomain(String currentdomain) {
		this.currentdomain = currentdomain;
	}
	/**  
	 * @return the indexpic  
	 */
	
	public String getIndexpic() {
		return indexpic;
	}
	/**  
	 * @param indexpic the indexpic to set  
	 */
	
	public void setIndexpic(String indexpic) {
		this.indexpic = indexpic;
	}
	/**  
	 * @return the logopath  
	 */
	
	public String getLogopath() {
		return logopath;
	}
	/**  
	 * @param logopath the logopath to set  
	 */
	
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	/**  
	 * @return the sysname  
	 */
	
	public String getSysname() {
		return sysname;
	}
	/**  
	 * @param sysname the sysname to set  
	 */
	
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	/**  
	 * @return the topbarpic  
	 */
	
	public String getTopbarpic() {
		return topbarpic;
	}
	/**  
	 * @param topbarpic the topbarpic to set  
	 */
	
	public void setTopbarpic(String topbarpic) {
		this.topbarpic = topbarpic;
	}
	/**  
	 * @return the havelocal  
	 */
	
	public boolean getHavelocal() {
		return havelocal;
	}
	/**  
	 * @param havelocal the havelocal to set  
	 */
	
	public void setHavelocal(boolean havelocal) {
		this.havelocal = havelocal;
	}
	
}
