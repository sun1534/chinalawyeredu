package com.sxit.info.action;

import java.io.File;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.AttachFile;
import com.sxit.system.model.TsysUser;
import com.sxit.wait.util.WaitWork;

/**
 *
 * <p>功能： 删除多个信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoAdminDeletesAction extends AbstractAction {
  private Long[] check;
  private String type;
  public InfoAdminDeletesAction() {
    rights="inf6,8";
    
  }

  protected String go() throws HibernateException {
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个信息成功！";
      nextpage="infoList.action?type="+type+"&pagenumber="+pagenumber;
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个信息失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    
    for (int i=0; i < check.length; i++) {
			TinfInfo info = (TinfInfo) session.get(TinfInfo.class, check[i]);

			curuser = (TsysUser) get("curuser");
			int waitid;
			String waitidstring = info.getWaitid();
			if (waitidstring != null && !waitidstring.equals("")) {
				String[] waitids = waitidstring.split(",");
				for (int j = 0; j < waitids.length; j++) {
					waitid = Integer.valueOf(waitids[j]);
					LOG.debug("waitid============" + waitid);
					WaitWork.DelWait(getSession(), waitid, curuser);
				}
			}
	}

    String hqlDelete = "delete from TinfInfo where infoid in (:infoids)";
    String hqlDeleteAttach = "delete from TinfAttach as attach where attach.tinfInfo.infoid in (:infoids)";
    String hqlDeleteFile = "select filepath from TinfAttach as attach where attach.tinfInfo.infoid in (:infoids)";
    ArrayList list = (ArrayList)session.createQuery(hqlDeleteFile).setParameterList("infoids", check).list();
    for(int i=0;i<list.size();i++){
    	String filepath = (String)list.get(i);
    	AttachFile.delete(filepath);
    	
    }
    int deletedAttach = session.createQuery(hqlDeleteAttach)
    	.setParameterList("infoids", check)
    	.executeUpdate();
    System.out.println("删除了==============="+deletedAttach+"个附件");
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("infoids", check)
        .executeUpdate();
    

	
    return deletedEntities;
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }
  
  public void setType(String type){
	  this.type = type;
  }

}
