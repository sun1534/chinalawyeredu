package com.changpeng.service.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看电话费管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class PhobillViewAction extends AbstractAction {
	private TserPhobill phobill;
        private long phobillid;
	public PhobillViewAction() {
          rights="ser1,1";
	   phobill = new TserPhobill();
	}

	public String go() throws HibernateException {
           nextpage="phobillList.action?pagenumber="+pagenumber;
           phobill=(TserPhobill)getSession().get(TserPhobill.class,phobillid);
           if(phobill==null){
             message="未找到此电话费管理";
             return "message";
           }
           set("phobill", phobill);
           return SUCCESS;
	}
	public TserPhobill getPhobill() {
		return phobill;
	}
        public void setPhobillid(long phobillid) {

          this.phobillid = phobillid;
        }
        public long getPhobillid() {
          return this.phobillid;
        }

}
