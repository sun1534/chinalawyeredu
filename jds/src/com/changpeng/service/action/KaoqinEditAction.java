package com.changpeng.service.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.service.model.*;




/**
 *
 * <p>功能： 编辑考勤管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class KaoqinEditAction extends AbstractAction {

	private TserKaoqin kaoqin;

	public KaoqinEditAction() {
          rights="ser4,4";
	}

	public String go() throws HibernateException {
                getSession().update(kaoqin);
		set("kaoqin", kaoqin);
                nextpage="kaoqinList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TserKaoqin getKaoqin() {
         if (kaoqin==null)
            kaoqin = (TserKaoqin) get("kaoqin");
          return kaoqin;
	}

        public String input() throws Exception {
          return "input";
        }


}
