package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;




/**
 *
 * <p>功能： 编辑转向</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class DirectionEditAction extends AbstractAction {

	private TwflDirection direction;

	public DirectionEditAction() {
          rights="wfl1,4";
	}

	public String go() throws HibernateException {
                getSession().update(direction);
		set("direction", direction);
                nextpage="directionList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TwflDirection getDirection() {
         if (direction==null)
            direction = (TwflDirection) get("direction");
          return direction;
	}

        public String input() throws Exception {
          return "input";
        }


}
