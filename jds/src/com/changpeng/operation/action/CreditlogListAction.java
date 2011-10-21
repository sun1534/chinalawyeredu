package com.changpeng.operation.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.operation.model.ToprCreditlog;
import com.changpeng.operation.util.ExcelExport;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * <p>
 * 功能： 列表催收日志
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-14
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CreditlogListAction extends AbstractListAction {
	private List creditloglist;

	private long credittaskid;
	private long creditcardid;
	private String creditcard;

	private String ismine;
	private long bankid;
	private String start;
	private String end;
	private String ccbexport;

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

	public long getCreditcardid() {
		return creditcardid;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

	public long getCredittaskid() {
		return credittaskid;
	}

	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

	public CreditlogListAction() {
		rights = "opr4,1";
	}

	public String go() throws HibernateException {

		this.nextpage="javascript:history.go(-1)";
		/*
		 * creditloglist = getQuery() .setMaxResults(maxperpage)
		 * .setFirstResult(maxperpage * pagenumber) .setCacheable(true) .list();
		 */
		Criteria criteria = getSession().createCriteria(ToprCreditlog.class).createAlias("toprCreditcard",
				"toprCreditcard");
		// criteria.add(Expression.eq("userid", curuser.getUserid()));
		// criteria.createCriteria("toprCredittask");

		// if(ismine!=null&&!ismine.equals("")){
		// criteria.createAlias("toprCredittask", "toprCredittask");
		// criteria.add(Expression.eq("toprCredittask.userid",
		// this.curuser.getUserid()));
		// }
		if (ismine != null && !ismine.equals("")) {
			criteria.add(Expression.eq("userid", this.curuser.getUserid()));
		}
		if (credittaskid != 0)
			criteria.add(Expression.eq("toprCredittask.credittaskid", credittaskid));
		if (creditcardid != 0)
			criteria.add(Expression.eq("toprCreditcard.creditcardid", creditcardid));
		if (bankid != 0)
			criteria.add(Restrictions.eq("toprCreditcard.bankid", bankid));
		if (creditcard != null && !creditcard.equals("")) {
			criteria.add(Restrictions.eq("toprCreditcard.creditcard", creditcard));
		}
		boolean b = false;
		if (start != null && !start.equals("")) {
			b = true;
			// criteria.add(Restrictions.isNotEmpty("logtime"));
			criteria.add(Restrictions.isNotNull("logtime"));
			criteria.add(Restrictions.ge("logtime", start + " 00:00"));
		}
		if (end != null && !end.equals("")) {
			if (!b) {
				b = true;
				// criteria.add(Restrictions.isNotEmpty("logtime"));
				criteria.add(Restrictions.isNotNull("logtime"));
			}
			criteria.add(Restrictions.le("logtime", start + " 23:59"));
		}
		criteria.addOrder(Order.desc("logid"));
		if (ccbexport == null || ccbexport.equals("")) {
			creditloglist = page(criteria);

			return SUCCESS;
		} else if (ccbexport.equals("txt")) {

			creditloglist = criteria.list();

			int len = creditloglist == null ? 0 : creditloglist.size();
			if (len > 10000) {
				this.nextpage="javascript:history.go(-1)";
				this.message = "1次最多导出10000条记录,请缩小查询的范围后进行导出";
				return "sysmsg";
			}

			try {
//				java.io.string sw = new java.io.StringWriter();
			StringBuilder sw=new StringBuilder();
			sw.append("所属分行号,案件编号,委外公司,催收日期,催收时间,联络类型,联络方式,联络对象,联络结果,催收记录,催收措施");
				sw.append("\r\n");
				for (int i = 0; i < len; i++) {
					ToprCreditlog rpd = (ToprCreditlog) creditloglist.get(i);

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
							if (logtime != null && !logtime.equals("") && logtime.indexOf(" ") != -1) {
								String[] ss = logtime.split(" ");
								date = ss[0].replace(".", "").replace(" ", "").replace("　", "").replace("、", "");
								time = ss[1].replace(".", "").replace(" ", "").replace("　", "").replace("、", "") + "00";
							} else {
								date = dfdate.format(new Date());
								time = dfmin.format(new Date());
							}
						}
					}
					String time1 = "";
					if (time == null || time.equals("") || time.indexOf(":") == -1) {
						time1 = "000000";
					} else
						time1 = time.replace(":", "") + "00";
					sw.append((rpd.getBankno()==null?"":rpd.getBankno()) + "," 
							+ (rpd.getToprCreditcard().getCreditcard()==null?"":rpd.getToprCreditcard().getCreditcard()) 
							+ ",嘉德信("	+ rpd.getUsername() + ")," 
							+ date.replace("-", "") 
							+ "," + time1 + ","
							+ (rpd.getContacttype()==null?"":rpd.getContacttype()) 
							+ "," 
							+ (rpd.getContactmanner()==null?"":rpd.getContactmanner()) + "," 
							+ (rpd.getContactobj()==null?"":rpd.getContactobj()) + ","
							+ (rpd.getContactresult()==null?"":rpd.getContactresult()) + "," 
							+ (rpd.getComments()==null?"":rpd.getComments()) + "," 
							+ (rpd.getCuishoucuoshi()==null?"":rpd.getCuishoucuoshi()) + "");
					sw.append("\r\n");

				}

				byte[] buffer = sw.toString().getBytes("utf-8");
				System.out.println("buffer.length====>"+buffer.length);
				this.inputstream = new ByteArrayInputStream(buffer);
				return "txt";
			} catch (Exception e) {
				this.nextpage="javascript:history.go(-1)";
				LOG.error("建行格式的催收记录导出失败", e);
				this.message = "建行格式的催收记录导出失败:" + e;
				return "sysmsg";
			}

		} else {
			creditloglist = criteria.list();

			int len = creditloglist == null ? 0 : creditloglist.size();
			if (len > 10000) {
				this.message = "1次最多导出10000条记录,请缩小查询的范围后进行导出";
				return "sysmsg";
			}

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
					ToprCreditlog rpd = (ToprCreditlog) creditloglist.get(i);

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
							if (logtime != null && !logtime.equals("") && logtime.indexOf(" ") != -1) {
								String[] ss = logtime.split(" ");
								date = ss[0].replace(".", "").replace(" ", "").replace("　", "").replace("、", "");
								time = ss[1].replace(".", "").replace(" ", "").replace("　", "").replace("、", "") + "00";
							} else {
								date = dfdate.format(new Date());
								time = dfmin.format(new Date());
							}
						}
					}
					xls.setCell(0, rpd.getBankno());
					xls.setCell(1, rpd.getToprCreditcard().getCreditcard());
					xls.setCell(2, "嘉德信(" + rpd.getUsername() + ")");
					xls.setCell(3, date.replace("-", ""));
					if (time == null || time.equals("") || time.indexOf(":") == -1) {
						xls.setCell(4, "000000");
					} else
						xls.setCell(4, time.replace(":", "") + "00");
					xls.setCell(5, rpd.getContacttype());
					xls.setCell(6, rpd.getContactmanner());
					xls.setCell(7, rpd.getContactobj());
					xls.setCell(8, rpd.getContactresult());
					xls.setCell(9, rpd.getComments());
					xls.setCell(10, rpd.getCuishoucuoshi());

				}
				java.io.ByteArrayOutputStream baos = new ByteArrayOutputStream();
				xls.exportXLS(baos);
				byte[] buffer = baos.toByteArray();

				this.inputstream = new ByteArrayInputStream(buffer);
				baos.close();
				return "excelsimple";
			} catch (Exception e) {
				this.nextpage="javascript:history.go(-1)";
				LOG.error("建行格式的催收记录导出失败", e);
				this.message = "建行格式的催收记录导出失败:" + e;
				return "sysmsg";
			}

		}
	}

	private static final DateFormat dfminute = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfmin = new java.text.SimpleDateFormat(" HH:mm:ss");

	/*
	 * private Query getQuery() throws HibernateException { String queryName ;
	 * queryName="from ToprCreditlog as creditlog order by creditlog.creditlogid
	 * desc"; if(creditcardid!=0){ queryName="from ToprCreditlog as creditlog
	 * where creditlog.toprCredittask.creditcardid="+creditcardid+" order by
	 * creditlog.creditlogid desc"; } Query query =
	 * getSession().createQuery(queryName); recordsize = query.list().size();
	 * pagesize = (recordsize - 1) / maxperpage + 1; pagenumber=
	 * pagenumber>pagesize-1?pagesize-1:pagenumber; return query; }
	 */
	public List getCreditloglist() {
		return creditloglist;
	}

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

	private InputStream inputstream;

	/**
	 * @return the inpustream
	 */
	public InputStream getInputstream() {
		return inputstream;
	}

	/**
	 * @return the creditcard
	 */
	public String getCreditcard() {
		return creditcard;
	}

	/**
	 * @param creditcard
	 *            the creditcard to set
	 */
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	/**
	 * @return the ismine
	 */
	public String getIsmine() {
		return ismine;
	}

	/**
	 * @param ismine
	 *            the ismine to set
	 */
	public void setIsmine(String ismine) {
		this.ismine = ismine;
	}
}
