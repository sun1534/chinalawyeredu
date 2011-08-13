package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 编辑电话费管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class PhobillEditAction extends AbstractAction {

	private TserPhobill phobill;

	public PhobillEditAction() {
          rights="ser1,4";
	}

	public String go() throws HibernateException {
                getSession().update(phobill);
		set("phobill", phobill);
                nextpage="phobillList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TserPhobill getPhobill() {
         if (phobill==null)
            phobill = (TserPhobill) get("phobill");
          return phobill;
	}

        public String input() throws Exception {
          return "input";
        }


}
