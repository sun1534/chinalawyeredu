package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractAction;

/**
 * 对信用卡号码重复的记录做新增或覆盖操作
 * 
 * @author Administrator Oct 11, 2009
 */
public class CardreoperAction extends AbstractAction {

	private int opflag;
	private String creditcard;

	private List<String> cardList;

	public String getCreditcard() {
		return creditcard;
	}

	public int getOpflag() {
		return opflag;
	}

	public void setOpflag(int opflag) {
		this.opflag = opflag;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public CardreoperAction() {

	}

	public String go() throws HibernateException {
		List<ToprCreditcard> list = (List<ToprCreditcard>) get("recards");

		ToprCreditcard card = null;

		if (opflag == 3 || opflag == 4) { // 全部新增 或 覆盖
			for (ToprCreditcard temp : list) {
				if (opflag == 3) {// 新增的话,就要加到那个service里面去
					getSession().save(temp);

					TusrCustomerNew customer = NewCustomerUtil.getCustomer(getSession(), temp.getUsername(), temp
							.getIdcard());
					if (customer != null) {
						TusrCustomerService service = new TusrCustomerService();
						service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
						service.setCreateuser(curuser.getUsername());
						service.setCreateuserid((int) curuser.getUserid());
						service.setServiceid((int) temp.getCreditcardid());
						service.setServicetype(1);

						service.setTusrCustomerNew(customer);
						service.setRemarks("信用卡卡号重复新增加入");
						getSession().save(service);
					} else {
						System.out.println(temp.getUsername() + ",," + temp.getIdcard() + "没找到用户");
					}
					
				} else
					getSession().update(temp);
			}
			if(opflag==3){
				message="全部新增处理成功";
			}else{
				message="全部覆盖处理成功";
			}
		} else {
			for (ToprCreditcard temp : list) {
				if (temp.getCreditcard().equals(creditcard)) {
					card = temp;
					break;
				}
			}
			// System.out.println(opflag);
			if (opflag == 0 && card != null) {
				getSession().save(card);

				TusrCustomerNew customer = NewCustomerUtil.getCustomer(getSession(), card.getUsername(), card
						.getIdcard());
				if (customer != null) {
					TusrCustomerService service = new TusrCustomerService();
					service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					service.setCreateuser(curuser.getUsername());
					service.setCreateuserid((int) curuser.getUserid());
					service.setServiceid((int) card.getCreditcardid());
					service.setServicetype(1);
					service.setTusrCustomerNew(customer);
					service.setRemarks("信用卡卡号重复新增加入");
					getSession().save(service);
				} else {
					System.out.println(card.getUsername() + ",," + card.getIdcard() + "没找到用户");
				}

				message = "记录新增成功";
			}

			else if (opflag == 1 && card != null) {
				getSession().update(card);
				message = "记录更新成功";
			}

			else if (card == null) {
				message = "未找到对应缴费卡";
			}
		}

		return SUCCESS;
	}

}
