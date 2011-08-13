package com.changpeng.service.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看物业管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class OwnerViewAction extends AbstractAction {
	private TserOwner owner;
        private long ownerid;
	public OwnerViewAction() {
          rights="ser2,1";
	   owner = new TserOwner();
	}

	public String go() throws HibernateException {
           nextpage="ownerList.action?pagenumber="+pagenumber;
           owner=(TserOwner)getSession().get(TserOwner.class,ownerid);
           if(owner==null){
             message="未找到此物业管理";
             return "message";
           }
           set("owner", owner);
           return SUCCESS;
	}
	public TserOwner getOwner() {
		return owner;
	}
        public void setOwnerid(long ownerid) {

          this.ownerid = ownerid;
        }
        public long getOwnerid() {
          return this.ownerid;
        }

}
