package com.sxit.member.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * <p>
 * 功能： 列表简历录入
 * </p>
 * <p>
 * 作者： 雷华
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-05-13
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class ResumeListAction extends AbstractListAction {
	private List resumelist;

	public ResumeListAction() {
		rights = "mem2,1";
	}

	public String go() throws HibernateException {
		
		
		resumelist = getQuery().setMaxResults(maxperpage).setFirstResult(
				maxperpage * pagenumber).setCacheable(true).list();
		
		return SUCCESS;
	}

	private Query getQuery() throws HibernateException {
		String queryName;
		queryName = "from TmemResume as resume order by resume.resumeid desc";
		Query query = getSession().createQuery(queryName);
		recordsize = query.list().size();
		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
		return query;
	}

	public List getResumelist() {
		return resumelist;
	}
}
