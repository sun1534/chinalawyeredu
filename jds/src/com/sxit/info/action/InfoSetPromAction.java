/**
 * 
 */
package com.sxit.info.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.TinfInfo;
import com.sxit.info.model.TinfSet;
import com.sxit.info.util.Power;
import com.sxit.wait.util.WaitWork;
import com.sxit.system.model.TsysUser;

/**
 * @author sg
 * @TODO  发布冻结信息
 * @createtime 2008-9-4
 * @version v1.0
 */
public class InfoSetPromAction extends AbstractAction {
	
	private TinfInfo info;
	boolean firstprom = false;
	
	public InfoSetPromAction(){
		rights = "inf4,1";
	}

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		LOG.debug("=======================");
		if (firstprom) {
			LOG.debug("=======================firstprom");
			curuser = (TsysUser) get("curuser");
			int waitid;
			String waitidstring = info.getWaitid();
			if (waitidstring != null && !waitidstring.equals("")) {
				String[] waitids = waitidstring.split(",");
				for (int i = 0; i < waitids.length; i++) {
					waitid = Integer.valueOf(waitids[i]);
					WaitWork.EndWait(getSession(), waitid, curuser);
				}
			}
		}
		info.setWaitid("");
		getSession().update(info);

		nextpage="infoPromList.action?type="+info.getTinfType().getTypeid()+"&pagenumber="+pagenumber;
		message = "提交成功！";
		return SUCCESS;
	}
	
	public TinfInfo getInfo() {
		if (info == null)
			info = (TinfInfo) get("info");
		return this.info;
	}

	public boolean isFirstprom() {
		return firstprom;
	}

	public void setFirstprom(boolean firstprom) {
		this.firstprom = firstprom;
	}
	

	

}
