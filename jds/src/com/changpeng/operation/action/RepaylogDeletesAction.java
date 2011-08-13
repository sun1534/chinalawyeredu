package com.changpeng.operation.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;

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
  private long creditcardid;
  

public long getCreditcardid() {
	return creditcardid;
}

public void setCreditcardid(long creditcardid) {
	this.creditcardid = creditcardid;
}

public RepaylogDeletesAction() {
    rights="opr5,8";
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
	  nextpage+="&creditcardid="+creditcardid;
    Session session = getSession();
   
  
    String hqlDelete = "delete from ToprRepaylog where repaylogid in (:repaylogids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("repaylogids", check)
        .executeUpdate();
    
    session.flush();
    //更新还款值
  	Object o = getSession().createSQLQuery("select sum(fee) from Topr_Repaylog where creditcardid="+creditcardid).list().get(0);
  	if(o==null) o="0";
  	getSession().createSQLQuery("update topr_creditcard set refee='"+o+"' where creditcardid='"+creditcardid+"'").executeUpdate();
  	
  	
    return deletedEntities;
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }

}
