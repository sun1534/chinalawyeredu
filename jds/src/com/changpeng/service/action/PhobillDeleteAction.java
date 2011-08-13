package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 删除电话费管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class PhobillDeleteAction extends AbstractAction {

	private TserPhobill phobill;

	public PhobillDeleteAction() {
           rights="ser1,8";
           nextpage="phobillList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TserPhobill phobill = (TserPhobill) get("phobill");
                getSession().delete(phobill);
                message="删除成功！";
		return SUCCESS;
	}

	public TserPhobill getPhobill() {
         if (phobill==null)
            phobill = (TserPhobill) get("phobill");
          return phobill;
	}
}
