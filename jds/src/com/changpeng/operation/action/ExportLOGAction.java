package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;
import org.hibernate.Query;
import java.util.List;
/**
 * 导出催收记录
 * @author sinhoo
 * Aug 13, 2009
 */

public class ExportLOGAction extends AbstractAction {
	private ToprCreditcard creditcard;
    private long creditcardid;
   
    private List logList;
    
    public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}
    public ToprCreditcard getCreditcard() {
		return creditcard;
	}
	public ExportLOGAction() {
          rights="opr4,1";
         // creditcard = new ToprCreditcard();
	}

	public String go() throws HibernateException {
           creditcard=(ToprCreditcard)getSession().get(ToprCreditcard.class,creditcardid);
       
           logList=getSession().createQuery(" from ToprCreditlog where toprCredittask.toprCreditcard.creditcardid="+creditcardid).list();
           
           if(creditcard==null){
             message="未找到此催收记录";
             return "message";
           }
           set("creditcard", creditcard);
           return SUCCESS;
	}
	
	public List getLogList() {
		return logList;
	}

}
