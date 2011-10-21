package com.changpeng.operation.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.changpeng.operation.model.ToprCreditlog;
import com.changpeng.operation.model.ToprCredittask;
import com.changpeng.operation.util.ExcelExport;
import com.sxit.common.action.AbstractAction;

/**
 * 导出催收记录(批量导出)
 * 
 * @author sinhoo Aug 13, 2009
 */

public class ExportLogsAction extends AbstractAction {
	private List tasklist;
	private long bankid;
	private String consigntype;
	private String consignflag;
	private String username;
	private String creditcard;
	private String idcard;
	private String consigndate;
	private String paydate;
	private String canlink;
	private String curdate;
	private String selected;
	private int[] check;
	private String start;
	private String end;

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @param check
	 *            the check to set
	 */
	public void setCheck(int[] check) {
		this.check = check;
	}

	public String getCurdate() {
		return curdate;
	}

	public void setCurdate(String curdate) {
		this.curdate = curdate;
	}

	public String getCanlink() {
		return canlink;
	}

	public void setCanlink(String canlink) {
		this.canlink = canlink;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public String getIdcard() {
		return idcard;
	}

	public String getConsigndate() {
		return consigndate;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	private Query getQuery() throws HibernateException {
		String queryName = "select a from ToprCredittask a,ToprCreditcard b where a.userid=" + curuser.getUserid()
				+ " and a.toprCreditcard.creditcardid=b.creditcardid  and a.taskstat=0";
		if (bankid != 0)
			queryName += " and b.bankid=" + bankid;
		if (consigntype != null && !"".equals(consigntype))
			queryName += " and b.consigntype='" + consigntype + "'";
		if (consignflag != null && !"".equals(consignflag))
			queryName += " and b.consignflag='" + consignflag + "'";
		if (username != null && !"".equals(username))
			queryName += " and b.username like '%" + username + "%'";
		if (creditcard != null && !"".equals(creditcard))
			queryName += " and b.creditcard like '%" + creditcard + "%'";
		if (idcard != null && !"".equals(idcard))
			queryName += " and b.idcard like '%" + idcard + "%'";
		if (consigndate != null && !"".equals(consigndate))
			queryName += " and b.consigndate='" + consigndate + "'";

		if (paydate != null && !"".equals(paydate)) {
			// criteria.add(Expression.eq("paydate", paydate));
			String now = new java.text.SimpleDateFormat("yyyy-MM-dd 00:00").format(new java.util.Date());
			// criteria.add(Expression.between("paydate", now, paydate));
			queryName += " and a.paydate>='" + now + "' and a.paydate<='" + paydate + " 23:59'";
		}
		if (curdate != null && !"".equals(curdate)) {
			queryName += " and a.paydate>='" + curdate + " 00:00' and a.paydate<='" + curdate + " 23:59'";
		}

		if (canlink != null && !"".equals(canlink)) {
			if (!"y".equals(canlink))
				queryName += " and a.canlink='" + canlink + "'";
			else
				// 能联系上
				queryName += " and (a.canlink='y' or a.canlink is null)";

		}
		queryName += " and b.repaystatus<>2";

		queryName += " order by to_number(b.curcnfee) desc";
		Query query = getSession().createQuery(queryName);
		return query;
	}

	public String go() throws HibernateException {

		if (selected != null && selected.equals("selected")) {
			if (check == null || check.length == 0) {
				this.message = "您没有选择任何需要导出的记录,请选择";
				this.nextpage = "javascript:history.go(-1)";

				return ERROR;

			} else {
				String s = "";
				for (int ss : check) {
					s += ss + ",";
				}
				s += "0";

				String queryName = "select a from ToprCredittask a,ToprCreditcard b where a.toprCreditcard.creditcardid=b.creditcardid  and b.creditcardid in("
						+ s + ")";
				tasklist = getSession().createQuery(queryName).list();
			}

		} else {
			tasklist = getQuery().list();
		}

		if (ccbexport != null && !ccbexport.equals("")) {

			int len = tasklist == null ? 0 : tasklist.size();
			try {
				ExcelExport xls = new ExcelExport(true); // 为2007的文件

				xls.createRow(0);
				xls.setCell(0, "所属分行号");
				xls.setCell(1, "案件编号");
				xls.setCell(2, "委外公司");
				xls.setCell(3, "催收日期");
				xls.setCell(4, "催收时间");
				xls.setCell(5, "联络类型");
				xls.setCell(6, "联络方式");
				xls.setCell(7, "联络对象");
				xls.setCell(8, "联络结果");
				xls.setCell(9, "催收记录");
				xls.setCell(10, "催收措施");
				for (int i = 0; i < len; i++) {
					// ToprCreditlog rpd = (ToprCreditlog) tasklist.get(i);
					ToprCredittask task = (ToprCredittask) tasklist.get(i);

					Iterator iterator = task.getToprCreditlogs().iterator();

					while (iterator.hasNext()) {

						ToprCreditlog rpd = (ToprCreditlog) iterator.next();

						xls.createRow(i + 1);

						// 所属分行号 案件编号 委外公司 催收日期 催收时间 联络类型 联络方式 联络对象 联络结果 催收记录
						// 催收措施
						// 442000100 1692767450003050644 金融联（叶玲玲） 20110406
						// 141329
						// 其他地址 广东省深圳市专家公寓B栋东401 持卡人 无法确认 申请寄信:广东省深圳市专家公寓B栋东401
						// 信函催收

						String logtime = rpd.getLogtime();
						String date = "";
						String time = "";
						if (logtime != null && !logtime.equals("")) {
							try {
								Date sdate = dfminute.parse(logtime);
								date = dfdate.format(sdate);
								time = dfmin.format(sdate);
							} catch (Exception e) {
								System.out.println("logtime的时间不对=" + logtime);
								String[] ss = logtime.split(" ");
								date = ss[0].replace(".", "").replace(" ", "").replace("　", "").replace("、", "");
								time = ss[1].replace(".", "").replace(" ", "").replace("　", "").replace("、", "")+"00";
							}

						}
						xls.setCell(0, rpd.getBankno());
						xls.setCell(1, rpd.getToprCreditcard().getCreditcard());
						xls.setCell(2, rpd.getUsername());
						xls.setCell(3, date);
						if (time == null || time.equals("") || time.indexOf(":") == -1) {
							xls.setCell(4, "000000");
						} else
							xls.setCell(4, time);
						xls.setCell(5, rpd.getContacttype());
						xls.setCell(6, rpd.getContactmanner());
						xls.setCell(7, rpd.getContactobj());
						xls.setCell(8, rpd.getContactresult());
						xls.setCell(9, rpd.getComments());
						xls.setCell(10, rpd.getCuishoucuoshi());
					}
				}
				java.io.ByteArrayOutputStream baos = new ByteArrayOutputStream();
				xls.exportXLS(baos);
				byte[] buffer = baos.toByteArray();

				this.inputstream = new ByteArrayInputStream(buffer);
				baos.close();
				return "excelsimple";
			} catch (Exception e) {
				LOG.error("建行格式的催收记录导出失败", e);
				this.message = "建行格式的催收记录导出失败:" + e;
				return "sysmsg";
			}
		}

		return SUCCESS;
	}

	private static final DateFormat dfminute = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfmin = new java.text.SimpleDateFormat(" HH:mm:ss");

	public List getTasklist() {
		return tasklist;
	}

	// 建行的格式导出
	private String ccbexport;
	private InputStream inputstream;

	/**
	 * @return the ccbexport
	 */
	public String getCcbexport() {
		return ccbexport;
	}

	/**
	 * @param ccbexport
	 *            the ccbexport to set
	 */
	public void setCcbexport(String ccbexport) {
		this.ccbexport = ccbexport;
	}

	/**
	 * @return the inpustream
	 */
	public InputStream getInputstream() {
		return inputstream;
	}

	/**
	 * @return the bankid
	 */
	public long getBankid() {
		return bankid;
	}

	/**
	 * @param bankid
	 *            the bankid to set
	 */
	public void setBankid(long bankid) {
		this.bankid = bankid;
	}

	/**
	 * @return the consigntype
	 */
	public String getConsigntype() {
		return consigntype;
	}

	/**
	 * @param consigntype
	 *            the consigntype to set
	 */
	public void setConsigntype(String consigntype) {
		this.consigntype = consigntype;
	}

	/**
	 * @return the consignflag
	 */
	public String getConsignflag() {
		return consignflag;
	}

	/**
	 * @param consignflag
	 *            the consignflag to set
	 */
	public void setConsignflag(String consignflag) {
		this.consignflag = consignflag;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the check
	 */
	public int[] getCheck() {
		return check;
	}

}
