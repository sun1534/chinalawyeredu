package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;





/**
 *
 * <p>功能： 删除转向</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class DirectionDeleteAction extends AbstractAction {

	private TwflDirection direction;
        private int directionid;
        private int nodeid;
	public DirectionDeleteAction() {
           rights="wfl1,8";
	}
	public String go() throws HibernateException {
                TwflDirection direction = (TwflDirection)getSession().get(TwflDirection.class,Integer.valueOf(directionid));
                if(direction==null){
                  message="未找到此流向";
                  return "message";
                }
                getSession().delete(direction);
                message="删除成功！";
                nextpage="nodeView.action?nodeid="+nodeid;
		return SUCCESS;
	}
        public void setDirectionid(int directionid) {
          this.directionid = directionid;
        }
        public void setNodeid(int nodeid) {
          this.nodeid = nodeid;
        }
}
