package com.changpeng.nonlaw.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.nonlaw.model.*;

/**
 *
 * <p>功能： 删除多个还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogDeletesAction extends AbstractAction {
  private Long[] check;
  private long nonlawid;
  public long getNonlawid() {
	return nonlawid;
}

public void setNonlawid(long nonlawid) {
	this.nonlawid = nonlawid;
}

public RepaylogDeletesAction() {
    rights="nlw5,8";
    nextpage="repaylogList.action?pagenumber="+pagenumber;
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个还款记录成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个还款记录失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
	  nextpage+="&nonlawid="+nonlawid;
    Session session = getSession();
    
    /*Object o = session.createSQLQuery("select sum(fee) from Tnlw_Repaylog where repaylogid in (:repaylogids)").setParameterList("repaylogids", check).list().get(0);
    session.createSQLQuery("update tnlw_nonlaw set refee=refee-'"+o+"' where nonlawid='"+nonlawid+"'").executeUpdate();
  	*/

    String hqlDelete = "delete from TnlwRepaylog where repaylogid in (:repaylogids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("repaylogids", check)
        .executeUpdate();
    
    getSession().flush();
	
	//更新还款值
	Object o = getSession().createSQLQuery("select sum(fee) from Tnlw_Repaylog where substr(repaytime,0,7)=to_char(sysdate,'yyyy-mm') and nonlawid="+nonlawid).list().get(0);
	if(o==null) o="0";
	getSession().createSQLQuery("update tnlw_nonlaw set refee='"+o+"' where nonlawid='"+nonlawid+"'").executeUpdate();
	
    return deletedEntities;
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }

}
