package com.sxit.info.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.info.model.TinfSet;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;


/**
 *
 * <p>功能： 草稿列表信息</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoApproveListAction extends AbstractListAction  {
        private List infolist;
        private List powerlist;

	public InfoApproveListAction() {
		rights = "inf2,1";
	}

	public String go() throws HibernateException {
		infolist = getQuery().setMaxResults(maxperpage).setFirstResult(
				maxperpage * pagenumber).setCacheable(true).list();
		return SUCCESS;
	}

	private Query getQuery() throws HibernateException {
		String queryName;
		TsysUser curuser = (TsysUser) get("curuser");
		powerlist = Power.whichPower(getSession(), curuser, "approve");
		Long[] typelist = new Long[powerlist.size()+1] ;
		typelist[0]=0l;
		for(int i=0;i<powerlist.size();i++){
			typelist[i+1] = ((TinfSet)powerlist.get(i)).getTinfType().getTypeid();
		}
		
		queryName = "from TinfInfo as info where info.statusid = 2 and info.tinfType.typeid in (:typelist)  order by info.infoid desc";
		Query query = getSession().createQuery(queryName).setParameterList("typelist",typelist );
		recordsize = query.list().size();
		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
		return query;
	}

	public List getInfolist() {
		return infolist;
	}
}
