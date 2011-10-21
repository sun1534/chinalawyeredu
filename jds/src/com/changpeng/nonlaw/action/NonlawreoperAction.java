package com.changpeng.nonlaw.action;

import java.text.DateFormat;
import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.nonlaw.model.*;
import com.changpeng.operation.model.ToprCreditcard;

/**
 * 对非诉任务身份证重复的记录做新增或覆盖操作
 * 
 * @author Administrator Oct 11, 2009
 */
public class NonlawreoperAction extends AbstractAction {

	private int opflag;
	private String idcard;

	public String getIdcard() {
		return idcard;
	}

	public int getOpflag() {
		return opflag;
	}

	public void setOpflag(int opflag) {
		this.opflag = opflag;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public NonlawreoperAction() {

	}
	private static final DateFormat dfminute = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	public String go() throws HibernateException {
		List<TnlwNonlaw> list = (List<TnlwNonlaw>) get("renonlaws");

		TnlwNonlaw nonlaw = null;

		if (opflag == 3 || opflag == 4) { // 全部新增 或 覆盖
			for (TnlwNonlaw temp : list) {
				if (opflag == 3) {// 新增的话,就要加到那个service里面去
					
					String comments=temp.getComments();
					if(comments==null||comments.trim().equals(""))
						comments="相同数据选择新增:"+dfminute.format(new Date());
					else
						comments=comments+"(相同数据选择新增:"+dfminute.format(new Date())+")";
					temp.setComments(comments);
					
					getSession().save(temp);
					TusrCustomerNew customer = NewCustomerUtil.getCustomer(getSession(), temp.getUsername(), temp
							.getIdcard());
					if (customer != null) {
						TusrCustomerService service = new TusrCustomerService();
						service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
						service.setCreateuser(curuser.getUsername());
						service.setCreateuserid((int) curuser.getUserid());
						service.setServiceid((int) temp.getNonlawid());
						service.setServicetype(2);
						service.setTusrCustomerNew(customer);
						service.setRemarks("非诉卡号重复新增加入");
						getSession().save(service);
					} else {
						System.out.println(temp.getUsername()+",,,,"+temp.getIdcard()+"居然不存在,感觉很不对呢");
					}
				} else{
					String comments=temp.getComments();
					if(comments==null||comments.trim().equals(""))
						comments="相同数据选择更新:"+dfminute.format(new Date());
					else
						comments=comments+"(相同数据选择更新:"+dfminute.format(new Date())+")";
					
					temp.setComments(comments);
					getSession().update(temp);
					
				}
					
			}
		} else {

			for (TnlwNonlaw temp : list) {
				if (temp.getIdcard().trim().equals(idcard)) {
					nonlaw = temp;
					break;
				}
			}

			if (opflag == 0 && nonlaw != null) {
				getSession().save(nonlaw);
				TusrCustomerNew customer = NewCustomerUtil.getCustomer(getSession(), nonlaw.getUsername(), nonlaw
						.getIdcard());
				if (customer != null) {
					TusrCustomerService service = new TusrCustomerService();
					service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					service.setCreateuser(curuser.getUsername());
					service.setCreateuserid((int) curuser.getUserid());
					service.setServiceid((int) nonlaw.getNonlawid());
					service.setServicetype(2);
					service.setTusrCustomerNew(customer);
					service.setRemarks("非诉卡卡号重复新增加入");
					getSession().save(service);
					message = "记录新增成功";
				} else {
					System.out.println(nonlaw.getUsername() + ",," + nonlaw.getIdcard() + "没找到用户");
				}
			} else if (opflag == 1 && nonlaw != null) {
				getSession().update(nonlaw);
				message = "记录更新成功";
			} else if (nonlaw == null) {
				message = "未找到对应催收记录";
			}
		}
		return SUCCESS;
	}

}
