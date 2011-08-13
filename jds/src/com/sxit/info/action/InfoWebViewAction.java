package com.sxit.info.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看信息</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
 */

public class InfoWebViewAction extends AbstractAction {
	private TinfInfo info;
    private int infoid = 0;
    private List infoAttachList;
	public InfoWebViewAction() {
	   rights = "inf1,0";
	   info = new TinfInfo();
	}

	public String go() throws HibernateException {
		if(infoid==0){
			message="没有这个信息";
			return "message";
		}
		nextpage = "infoViewList.action?pagenumber=" + pagenumber;
		info = (TinfInfo) getSession()
				.get(TinfInfo.class, Long.valueOf(infoid));
		if (info == null || info.getStatusid()!=0) {
			message = "未找到此信息";
			return "message";
		}
		
		infoAttachList = getQueryInfoAttachList().list();
		return SUCCESS;
	}

	private Query getQueryInfoAttachList() throws HibernateException {
		String queryName;
		queryName = "from TinfAttach where tinfInfo.infoid=" + infoid + " ";
		Query query = getSession().createQuery(queryName);
		return query;
	}
	
	public TinfInfo getInfo() {
		return info;
	}

	public void setInfoid(int infoid) {

		this.infoid = infoid;
	}

	public int getInfoid() {
		return this.infoid;
	}
	
	public List getInfoAttachList(){
		return this.infoAttachList;
	}

}
