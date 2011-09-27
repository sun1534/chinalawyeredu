package com.changpeng.operation.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;
import org.hibernate.Query;
import java.util.List;
/**
 * 导出催收记录(批量导出) 
 * @author sinhoo
 * Aug 13, 2009
 */

public class ExportLogsAction extends AbstractAction {
	private List tasklist;
    private long bankid;
    private String consigntype;
    private String consignflag;
    private String username;
    private String creditcard;
    private String idcard;
    private String consigndate;
    private String paydate;
    private String canlink;
    private String curdate;
    private String selected;
	private int[] check;
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(int[] check) {
		this.check = check;
	}

    public String getCurdate() {
		return curdate;
	}
	public void setCurdate(String curdate) {
		this.curdate = curdate;
	}
    public String getCanlink() {
		return canlink;
	}
	public void setCanlink(String canlink) {
		this.canlink = canlink;
	}
    public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getIdcard() {
		return idcard;
	}
	public String getConsigndate() {
		return consigndate;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}
	 private Query getQuery() throws HibernateException {
         String queryName="select a from ToprCredittask a,ToprCreditcard b where a.userid="+curuser.getUserid()+" and a.toprCreditcard.creditcardid=b.creditcardid  and a.taskstat=0";
         if (bankid != 0)
         	queryName+=" and b.bankid="+bankid;	
 		if (consigntype!= null&&!"".equals(consigntype))
 			queryName+=" and b.consigntype='"+consigntype+"'";
 		if (consignflag!= null&&!"".equals(consignflag))
 			queryName+=" and b.consignflag='"+consignflag+"'";
 		if (username!= null&&!"".equals(username))
 			queryName+=" and b.username like '%"+username+"%'";
 		if (creditcard!= null&&!"".equals(creditcard))
 			queryName+=" and b.creditcard like '%"+creditcard+"%'";
 		if (idcard!= null&&!"".equals(idcard))
 			queryName+=" and b.idcard like '%"+idcard+"%'";
 		if (consigndate!= null&&!"".equals(consigndate))
 			queryName+=" and b.consigndate='"+consigndate+"'";
 		if(paydate!=null&&!"".equals(paydate)){
          	//criteria.add(Expression.eq("paydate", paydate));
         	String now=new java.text.SimpleDateFormat("yyyy-MM-dd 00:00").format(new java.util.Date());
          //	criteria.add(Expression.between("paydate", now, paydate));
          	queryName+=" and a.paydate>='"+now+"' and a.paydate<='"+paydate+" 23:59'";
          }
 		if (curdate!= null&&!"".equals(curdate)){
          	queryName+=" and a.paydate>='"+curdate+" 00:00' and a.paydate<='"+curdate+" 23:59'";
 		}
 		
 		if(canlink!=null&&!"".equals(canlink)){
           	 if(!"y".equals(canlink))
           		queryName+=" and a.canlink='"+canlink+"'";
           	 else //能联系上
           		queryName+=" and (a.canlink='y' or a.canlink is null)";
           	 
            }
 		queryName+=" and b.repaystatus<>2";
 		
 		queryName+=" order by to_number(b.curcnfee) desc";
         Query query = getSession().createQuery(queryName);
         return query;
 }
	 
	public String go() throws HibernateException {
		
		if (selected != null && selected.equals("selected")) {
			if(check==null||check.length==0){
				this.message="您没有选择任何需要导出的记录,请选择";
				this.nextpage="javascript:history.go(-1)";
				
				return ERROR;
				
			}else{
				String s="";
				for(int ss:check){
					s+=ss+",";
				}
				s+="0";	
				
				String queryName = "select a from ToprCredittask a,ToprCreditcard b where a.toprCreditcard.creditcardid=b.creditcardid  and b.creditcardid in("+s+")";
				tasklist =getSession().createQuery(queryName).list();				
			}
			
		} else {
			tasklist = getQuery().list();
		}		
//		tasklist= getQuery().list();
        return SUCCESS;
	}
	public List getTasklist() {
		return tasklist;
	}
	

}
