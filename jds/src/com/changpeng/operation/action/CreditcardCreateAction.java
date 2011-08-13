package com.changpeng.operation.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.operation.model.*;


/**
 *
 * <p>功能： 创建催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditcardCreateAction extends AbstractAction {

	private ToprCreditcard creditcard;
	
	public CreditcardCreateAction() {
           rights="opr2,2";
		creditcard = new ToprCreditcard();
	}

	public String go() throws HibernateException {
		creditcard.setCreatetime(new java.util.Date(System.currentTimeMillis()));
		
		creditcard.setCurcnfee(creditcard.getCnfee());
		creditcard.setCurusafee(creditcard.getUsafee());
		creditcard.setCurhkfee(creditcard.getHkfee());
		creditcard.setCureurfee(creditcard.getEurfee());
		
		creditcard.setTdflag(0);
		getSession().save(creditcard);
		
		
		int cardid=(int)creditcard.getCreditcardid();
		
		TusrCustomerNew customer=NewCustomerUtil.getCustomer(getSession(), creditcard.getUsername(), creditcard.getIdcard());
		int customerid=0;
		boolean exist=false;
		if(customer!=null){
			customerid=customer.getCustomerid();
			exist=true;
		}else{//新增一个用户,同时将这个要加到那个service里面去
			 customer=new TusrCustomerNew();
//			 insert into tusr_customer_new(customerid,
//			 username,idcard,mobile1,homephone,compphone,company,compaddr,homeaddr,idcardaddr,compemail,personalemail,createsrc,createsrcid,createtime)select tusrcustomerid.nextval,
//			 username,idcard,mobileold,homephoneold,workphoneold,company,compaddr,homeaddr,idcardaddr,email,email,1 as createsrc,creditcardid,createtime from topr_creditcard;
			 customer.setUsername(creditcard.getUsername());
			 customer.setIdcard(creditcard.getIdcard());
			 customer.setMobile1(creditcard.getMobileold());
			 customer.setHomephone(creditcard.getHomephoneold());
			 customer.setCompphone(creditcard.getWorkphoneold());
			 customer.setCompany(creditcard.getCompany());
			 customer.setCompaddr(creditcard.getCompaddr());
			 customer.setHomeaddr(creditcard.getHomeaddr());
			 customer.setIdcardaddr(creditcard.getIdcardaddr());
			 customer.setCompemail(creditcard.getEmail());
			 customer.setPersonalemail(creditcard.getEmail());
			 customer.setCreatesrc(1);
			 customer.setCreatesrcid(cardid);
			 customer.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			 customer.setCreateuser((int)curuser.getUserid());
			 customer.setCustomerflag(2);//1:VIP 2:一般
			 customer.setCustomertype(3);//1:机构客户 2:个人客户 3当事人客户
			
			 getSession().save(customer);
			 customerid=customer.getCustomerid();
		}
		
		TusrCustomerService service=new TusrCustomerService();
		service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		service.setCreateuser(curuser.getUsername());
		service.setCreateuserid((int)curuser.getUserid());
		service.setServiceid((int)cardid);
		service.setServicetype(1);
		service.setTusrCustomerNew(customer);
		service.setRemarks("");
		getSession().save(service);
		
		set("creditcard", creditcard);
                nextpage="creditcardList.action";
                message="保存成功！";
                if(exist){
                	
                }
		return SUCCESS;
	}

	public ToprCreditcard getCreditcard() {
		return creditcard;
	}
        public String input() throws Exception {
        	//listBank=com.changpeng.operation.util.OperationUtil.listBank;
            return "input";
    }
}
