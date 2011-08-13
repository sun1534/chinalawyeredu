package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 删除考勤管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class KaoqinDeleteAction extends AbstractAction {

	private TserKaoqin kaoqin;

	public KaoqinDeleteAction() {
           rights="ser4,8";
           nextpage="kaoqinList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TserKaoqin kaoqin = (TserKaoqin) get("kaoqin");
                getSession().delete(kaoqin);
                message="删除成功！";
		return SUCCESS;
	}

	public TserKaoqin getKaoqin() {
         if (kaoqin==null)
            kaoqin = (TserKaoqin) get("kaoqin");
          return kaoqin;
	}
}
