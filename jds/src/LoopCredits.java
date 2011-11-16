import java.util.List;

import com.changpeng.operation.model.ToprCreditcard;

/**
 * LoopCredits.java
 */

/**
 * @author 刘哈哈
 * Nov 13, 201111:18:50 AM
 *
 */
public class LoopCredits {

	private ToprCreditcard creditcard;
	private List logList;
	public int getLoglistsize(){
		int len=(logList==null?0:logList.size());
		return len+2;
	}
	/**
	 * @return the creditcard
	 */
	public ToprCreditcard getCreditcard() {
		return creditcard;
	}
	/**
	 * @param creditcard the creditcard to set
	 */
	public void setCreditcard(ToprCreditcard creditcard) {
		this.creditcard = creditcard;
	}
	/**
	 * @return the logList
	 */
	public List getLogList() {
		return logList;
	}
	/**
	 * @param logList the logList to set
	 */
	public void setLogList(List logList) {
		this.logList = logList;
	}
}
