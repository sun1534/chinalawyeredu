package com.changpeng.operation.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;

/**
 * 更新记录状态，如转诉讼 或者变更为全清或备注清零等
 * @author sinhoo
 * Jul 15, 2009
 */

public class CreditcardUpdateAction extends AbstractAction {
	private int cflag;
	private int repaystatus;
	private long[] check;

	public CreditcardUpdateAction() {
		rights = "opr2,8";
		nextpage = "creditcardList.action?pagenumber=" + pagenumber;
	}

	protected String go() throws HibernateException {
		if (cflag == 1) {
			for (long creditcardid : check) {
				ToprCreditcard card = (ToprCreditcard) getSession().get(
						ToprCreditcard.class, creditcardid);
				card.setLawflag(1);
				getSession().update(card);
			}
			this.message = "任务转诉讼成功。";
		}else if (cflag == 2) {
			for (long creditcardid : check) {
				ToprCreditcard card = (ToprCreditcard) getSession().get(
						ToprCreditcard.class, creditcardid);
				card.setLawflag(2);
				getSession().update(card);
			}
			this.message = "任务结案成功。";
		}else if (cflag == 3) {
			for (long creditcardid : check) {
				ToprCreditcard card = (ToprCreditcard) getSession().get(
						ToprCreditcard.class, creditcardid);
				card.setRepaystatus(repaystatus);
				getSession().update(card);
			}
			this.message = "任务还款状态更新成功。";
		}
		return SUCCESS;
	}

	public long[] getCheck() {
		return check;
	}

	public void setCheck(long[] check) {
		this.check = check;
	}

	public int getCflag() {
		return cflag;
	}

	public void setCflag(int cflag) {
		this.cflag = cflag;
	}

	public int getRepaystatus() {
		return repaystatus;
	}

	public void setRepaystatus(int repaystatus) {
		this.repaystatus = repaystatus;
	}

}
