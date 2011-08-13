//$Id: PositionCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * @author zrb
 */
public class PositionViewAction extends AbstractAction {
	private TsysPosition position;
        private int positionid;
	public PositionViewAction() {
	   position = new TsysPosition();
	}

	public String go() throws HibernateException {
           nextpage="positionList.action?pagenumber="+pagenumber;
           position=(TsysPosition)getSession().get(TsysPosition.class,Integer.valueOf(positionid));
           if(position==null){
             message="未找到此职务";
             return "message";
           }
           set("position", position);
           return SUCCESS;
	}
	public TsysPosition getPosition() {
		return position;
	}
        public void setPositionid(int positionid) {

          this.positionid = positionid;
        }
        public int getPositionid() {
          return this.positionid;
        }

}
