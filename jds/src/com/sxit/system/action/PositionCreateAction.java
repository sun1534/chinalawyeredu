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
public class PositionCreateAction extends AbstractAction {

	private TsysPosition position;


	public PositionCreateAction() {
           rights="sys4,2";
		position = new TsysPosition();
	}

	public String go() throws HibernateException {
		getSession().save(position);
		set("position", position);
                nextpage="positionList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TsysPosition getPosition() {
		return position;
	}
        public String input() throws Exception {
            return "input";
    }
}
