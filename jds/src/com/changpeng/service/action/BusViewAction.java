package com.changpeng.service.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看车辆管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class BusViewAction extends AbstractAction {
	private TserBus bus;
        private long busid;
	public BusViewAction() {
          rights="ser3,1";
	   bus = new TserBus();
	}

	public String go() throws HibernateException {
           nextpage="busList.action?pagenumber="+pagenumber;
           bus=(TserBus)getSession().get(TserBus.class,busid);
           if(bus==null){
             message="未找到此物业管理";
             return "message";
           }
           set("bus", bus);
           return SUCCESS;
	}
	public TserBus getBus() {
		return bus;
	}
        public void setBusid(long busid) {

          this.busid = busid;
        }
        public long getBusid() {
          return this.busid;
        }

}
