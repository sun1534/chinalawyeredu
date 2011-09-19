package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;


/**
 *  退单记录
 * @author sinhoo
 * 2009-11-8
 */
public class ChangeTddateAction extends AbstractListAction  {
       private String newtddate;
       private String consigndate;
        public String go() throws HibernateException {
        
        	String hql="";
        	
        	if(consigndate==null||consigndate.equals("")){
        		nextpage = "javascript:history.go(-1)";
        		this.message="请输入对应退单数据的委托日期,不能为空";
        		return SUCCESS;
        	}
        	nextpage = "credittdList.action?pagenumber=" + pagenumber;
        	if(newtddate==null||newtddate.equals("")){
        		hql="update ToprCreditcard set tdflag=0 where consigndate='"+consigndate+"'";
        		this.message="单号设置为不退单处理成功";
        	}else{
        		this.message="单号设置为不退单处理成功，新的退单日期修改为:"+newtddate;
        		hql="update ToprCreditcard set tdflag=0,tddate='"+newtddate+"' where consigndate='"+consigndate+"'";
        	}
        int i=	getSession().createQuery(hql).executeUpdate();
        	System.out.println("修改的退单个数为:"+i);
                return SUCCESS;
        }
		/**
		 * @return the newtddate
		 */
		public String getNewtddate() {
			return newtddate;
		}
		/**
		 * @param newtddate the newtddate to set
		 */
		public void setNewtddate(String newtddate) {
			this.newtddate = newtddate;
		}
		/**
		 * @return the consigndate
		 */
		public String getConsigndate() {
			return consigndate;
		}
		/**
		 * @param consigndate the consigndate to set
		 */
		public void setConsigndate(String consigndate) {
			this.consigndate = consigndate;
		}
       
}
