//$Id: PositionCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;




/**
 *
 * @author zrb
 */
public class PositionDeleteAction extends AbstractAction {

	private TsysPosition position;

	public PositionDeleteAction() {
           rights="sys4,8";
	}
	public String go() throws HibernateException {
                TsysPosition position = (TsysPosition) get("position");
                getSession().delete(position);
                message="删除成功！";
                nextpage="positionList.action?pagenumber="+pagenumber;
		return SUCCESS;
	}

	public TsysPosition getPosition() {
         if (position==null)
            position = (TsysPosition) get("position");
          return position;
	}
}
