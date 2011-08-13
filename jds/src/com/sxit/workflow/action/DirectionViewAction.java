package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看转向</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class DirectionViewAction extends AbstractAction {
	private TwflDirection direction;
        private int directionid;
	public DirectionViewAction() {
          rights="wfl1,1";
	   direction = new TwflDirection();
	}

	public String go() throws HibernateException {
           nextpage="directionList.action?pagenumber="+pagenumber;
           direction=(TwflDirection)getSession().get(TwflDirection.class,Integer.valueOf(directionid));
           if(direction==null){
             message="未找到此用户";
             return "message";
           }
           set("direction", direction);
           return SUCCESS;
	}
	public TwflDirection getDirection() {
		return direction;
	}
        public void setDirectionid(int directionid) {

          this.directionid = directionid;
        }
        public int getDirectionid() {
          return this.directionid;
        }

}
