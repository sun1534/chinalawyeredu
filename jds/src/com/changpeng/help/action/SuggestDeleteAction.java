package com.changpeng.help.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;




/**
 *
 * <p>功能： 删除问题与建议</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
 */

public class SuggestDeleteAction extends AbstractAction {

//	private ThlpSuggest suggest;
	private long suggestid;
	
	
	public void setSuggestid(long suggestid) {
		this.suggestid = suggestid;
	}
	public long getSuggestid() {
		return suggestid;
	}
	public SuggestDeleteAction() {
           rights="hlp1,8";
           nextpage="suggestList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
		ThlpSuggest suggest=(ThlpSuggest)getSession().get(ThlpSuggest.class, suggestid);
		if(suggest!=null&&!suggest.getCreateuser().equals(curuser.getUsername())){
			message="您不能删除他人提交的建议。";
		}else{
			getSession().createSQLQuery("delete from thlp_suggest where suggestid="+suggestid+" or parentid="+suggestid+"").executeUpdate();
               // ThlpSuggest suggest = (ThlpSuggest) get("suggest");
               // getSession().delete(suggest);
                message="删除成功！";
		}
		return SUCCESS;
	}

/*	public ThlpSuggest getSuggest() {
         if (suggest==null)
            suggest = (ThlpSuggest) get("suggest");
          return suggest;
	}*/
}
