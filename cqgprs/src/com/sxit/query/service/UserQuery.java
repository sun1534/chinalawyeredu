/**
 * UserQuery.java
 */
package com.sxit.query.service;

import com.sxit.query.model.MobileApnState;

/**
 * @author 华锋
 * Jun 16, 20104:49:07 PM
 *
 */
public abstract class UserQuery {

	protected String LINE_SEP="</p>";
	protected String LINE_SEP_PRE="<p>";
	protected String consoleResultLines="";
	/**
	 * @return the consoleResultLines
	 */
	public String getConsoleResultLines() {
		return consoleResultLines;
	}
	/**
	 * @param consoleResultLines the consoleResultLines to set
	 */
	public void setConsoleResultLines(String consoleResultLines) {
		this.consoleResultLines = consoleResultLines;
	}
	public abstract MobileApnState queryUserState(String sgsnid, String msisdn, String random);
}