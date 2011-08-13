package com.sxit.info.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;




/**
 *
 * <p>功能： 删除权限</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class SetDeleteAction extends AbstractAction {

	private TinfSet set;

	public SetDeleteAction() {
           rights="inf5,8";
           nextpage="setList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TinfSet set = (TinfSet) get("set");
                getSession().delete(set);
                message="删除成功！";
		return SUCCESS;
	}

	public TinfSet getSet() {
         if (set==null)
            set = (TinfSet) get("set");
          return set;
	}
}
