package com.changpeng.help.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看问题与建议</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
 */

public class SuggestViewAction extends AbstractAction {
	private ThlpSuggest suggest;
	private List suglist;
    private long suggestid;
	
    public SuggestViewAction() {
          rights="hlp1,1";
	   suggest = new ThlpSuggest();
	}

	public String go() throws HibernateException {
           nextpage="suggestList.action?pagenumber="+pagenumber;
           suggest=(ThlpSuggest)getSession().get(ThlpSuggest.class,suggestid);
          suglist=getSession().createQuery(" from ThlpSuggest where parentid="+suggestid).list();
           if(suggest==null){
             message="未找到此问题与建议";
             return "message";
           }
        //   set("suggest", suggest);
           return SUCCESS;
	}
	public ThlpSuggest getSuggest() {
		return suggest;
	}
        public void setSuggestid(long suggestid) {

          this.suggestid = suggestid;
        }
        public long getSuggestid() {
          return this.suggestid;
        }

		public List getSuglist() {
			return suglist;
		}

}
