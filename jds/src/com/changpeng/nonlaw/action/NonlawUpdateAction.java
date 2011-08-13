package com.changpeng.nonlaw.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;
import com.changpeng.operation.model.ToprCreditcard;

/**
 *
 * <p>功能： 删除多个催收记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawUpdateAction extends AbstractAction {

  public NonlawUpdateAction() {
    rights="nlw1,8";
    nextpage="nonlawList.action?pagenumber="+pagenumber;
  }

  private int cflag;
	
	private long[] check;


	protected String go() throws HibernateException {
		if (cflag == 1) {
			for (long nonlawid : check) {
				TnlwNonlaw nonlaw = (TnlwNonlaw) getSession().get(
						TnlwNonlaw.class, nonlawid);
				nonlaw.setLawflag(1);
				getSession().update(nonlaw);
			}
			this.message = "任务转诉讼成功。";
		}else if (cflag == 2) {
			for (long nonlawid : check) {
				TnlwNonlaw nonlaw = (TnlwNonlaw) getSession().get(
						TnlwNonlaw.class, nonlawid);
				nonlaw.setLawflag(2);
				getSession().update(nonlaw);
			}
			this.message = "任务结案成功。";
		} else if (cflag == 3) {
			for (long nonlawid : check) {
				TnlwNonlaw nonlaw = (TnlwNonlaw) getSession().get(
						TnlwNonlaw.class, nonlawid);
				nonlaw.setRepaystatus(2);
				getSession().update(nonlaw);
			}
			this.message = "任务还款状态（全清状态）更新成功。";
		}else if (cflag == 4) {
				//设置所有的为全清状态
				getSession().createSQLQuery("update tnlw_nonlaw set REFEE=0").executeUpdate();
				
				
			this.message = "所有案件全清状态设置成功。";
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


}
