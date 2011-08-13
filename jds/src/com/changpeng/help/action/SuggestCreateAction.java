package com.changpeng.help.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;


/**
 *
 * <p>功能： 创建问题与建议</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
 */

public class SuggestCreateAction extends AbstractAction {

	private ThlpSuggest suggest;


	public SuggestCreateAction() {
           rights="hlp1,2";
		suggest = new ThlpSuggest();
	}

	public String go() throws HibernateException {
		suggest.setCreateuser(curuser.getUsername());
		suggest.setCreatetime(new java.util.Date());
		getSession().save(suggest);
		set("suggest", suggest);
		
		if(suggest.getParentid()!=0){
			nextpage="suggestView.action?suggestid="+suggest.getParentid();
            message="发表回复成功！";
		}else{
            nextpage="suggestList.action";
            message="保存成功！";
		}
        return SUCCESS;
	}

	public ThlpSuggest getSuggest() {
		return suggest;
	}
        public String input() throws Exception {
            return "input";
    }
}
