/**
 * 
 */
package com.changpeng.lawcase.action;

import java.text.DateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.lawcase.model.TlawCaselog;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.service.LawcaseService;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 显示这个案件的日志列表
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselogListAction extends AbstractListAction {

	private long caseid;
	private TlawLawcase lawcase;
	private int logtype=-1;
	/**
	 * 是否记录案件进度备注栏,在查看案件的代理日志的时候,台帐管理员根据调解日志录入到案件进度备注栏
	 * 
	 * 这个是否可以自动的方式录入进去呢？？？
	 */
	private int jilubeizhu;
	private String planlog;
	public String getPlanlog(){
		return this.planlog;
	}
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
       this.planlog=lawcase.getPlanlogs();
		Criteria criteria = getSession().createCriteria(TlawCaselog.class).add(Restrictions.eq("caseid", caseid));
		if(logtype!=-1){
			criteria.add(Restrictions.eq("logtype", logtype));
		}
		
		// 设定只有台帐管理员随时能修改案件信息,这里也就是录入案件进度备注信息
		List rolelist = LawcaseService.getRolesById(getSession(), this.curuser.getUserid());
		System.out.println("positionlist:::"+rolelist);
		// 621是台帐管理有的职位id
		
		System.out.println(rolelist+"===="+jilubeizhu+"");
				
		if (jilubeizhu==1&&(rolelist.contains(com.changpeng.lawcase.util.CommanDatas.TAIZHANGROLE)||this.curuser.getLoginname().equals("admin"))) {
			jilubeizhu=1;
		}else
			jilubeizhu=0;
		
		// 按入库顺序逆序排列
		criteria.addOrder(Order.desc("createtime"));
		loglist = this.page(criteria);

		return SUCCESS;
	}

	private List loglist;

	public List getLoglist() {
		return this.loglist;
	}

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}

	/**
	 * @return the logtype
	 */
	public int getLogtype() {
		return logtype;
	}

	/**
	 * @param logtype the logtype to set
	 */
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

	/**
	 * @return the jilubeizhu
	 */
	public int getJilubeizhu() {
		return jilubeizhu;
	}

	/**
	 * @param jilubeizhu the jilubeizhu to set
	 */
	public void setJilubeizhu(int jilubeizhu) {
		this.jilubeizhu = jilubeizhu;
	}

}
