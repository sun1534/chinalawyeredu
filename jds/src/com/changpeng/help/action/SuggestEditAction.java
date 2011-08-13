package com.changpeng.help.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;




/**
 *
 * <p>功能： 编辑问题与建议</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
 */

public class SuggestEditAction extends AbstractAction {

	private ThlpSuggest suggest;

	public SuggestEditAction() {
          rights="hlp1,4";
	}

	public String go() throws HibernateException {
                getSession().update(suggest);
		set("suggest", suggest);
                nextpage="suggestList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public ThlpSuggest getSuggest() {
         if (suggest==null)
            suggest = (ThlpSuggest) get("suggest");
          return suggest;
	}

        public String input() throws Exception {
          return "input";
        }


}
