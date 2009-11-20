/**
 * 
 */
package com.sxit.useraction.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.netquality.models.ContinueMobiles;
import com.sxit.useraction.models.ErrorCodeAllStat;
import com.sxit.useraction.models.ErrorCodeStat;
import com.sxit.useraction.models.ExceptionUsers;
import com.sxit.useraction.models.MobilesTop;
import com.sxit.useraction.models.StatApnErrors;
import com.sxit.useraction.models.UserPdpErrorTop;

/**
 * @author 华锋 Oct 23, 2009-2:34:40 PM
 * 
 */
public class UseractionService {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00:00");

	public static int getDfSec(String date) {
		try {
			Date d = dfsec.parse(date);
			return (int) (d.getTime() / 1000);
		} catch (Exception e) {
			return (int) (System.currentTimeMillis() / 1000);
		}
	}

	/**
	 * 得到这个小时的起始时间值
	 * 
	 * @return
	 */
	public static int getDateHourTime(Date date) {
		String datestr = dfhour.format(date);
		// String hstart = datestr + " 00:00";
		// String hend=datestr+" 59:59";
		try {
			Date d = dfsec.parse(datestr);
			return (int) (d.getTime() / 1000);
		} catch (Exception e) {
			return (int) (System.currentTimeMillis() / 1000);
		}
	}

	public static int getHourAfterTime(int start) {
		return start + 60 * 60;
	}

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 得到起始终止时间段内的错误统计情况
	 * 
	 * @param timestart
	 * @param timeend
	 * @return
	 */
	public ErrorCodeAllStat getPdpErrorStatics(int timestart, int timeend) {
		String sql = "select errcode,count(errcode) as errcount,count(distinct(imsi)) usercount from cdr_mistake where opentime between "
				+ timestart + " and " + timeend + " group by errcode";

		System.out.println(sql);

		final ErrorCodeAllStat ecas = new ErrorCodeAllStat();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					int errcode = rs.getInt("errcode");
					int errcount = rs.getInt("errcount");
					int usercount = rs.getInt("usercount");

					if (errcode == 33) {
						ecas.setErrorcount33(ecas.getErrorcount33() + errcount);
						ecas.setUsercount33(ecas.getUsercount33() + usercount);
					} else if (errcode == 27) {
						ecas.setErrorcount27(ecas.getErrorcount27() + errcount);
						ecas.setUsercount27(ecas.getUsercount27() + usercount);
					} else if (errcode == 29) {
						ecas.setErrorcount29(ecas.getErrorcount29() + errcount);
						ecas.setUsercount29(ecas.getUsercount29() + usercount);
					} else if (errcode == 38) {
						ecas.setErrorcount38(ecas.getErrorcount38() + errcount);
						ecas.setUsercount38(ecas.getUsercount38() + usercount);
					} else {
						ecas.setErrorcountothers(ecas.getErrorcountothers() + errcount);
						ecas.setUsercountothers(ecas.getUsercountothers() + usercount);
					}

					ecas.setErrorcountall(ecas.getErrorcountall() + errcount);
					ecas.setUsercountall(ecas.getUsercountall() + usercount);

				}
				return null;
			}
		});
		// List list = (List) object;
		return ecas;
	}

	/**
	 * 表3、PDP异常用户排名（按失败次数取前1000个用户），其实主要是33号错误的
	 * 
	 * 这里要按天和按时
	 * 
	 * @return
	 */
	public List getPdpErrorTopList(Date date, String orderby) {
		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		return getPdpErrorTopList((int) (start / 1000), (int) (end / 1000), orderby);
		// String sql = "select * from (select imsi,reqapnni,count(*) as cnt
		// from cdr_mistake where opentime between "
		// + start / 1000 + " and " + end / 1000 + " group by imsi,reqapnni
		// order by cnt desc) where rownum<=1000 order by cnt desc";
		// System.out.println(sql);
		// Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
		//
		// public Object extractData(ResultSet rs) throws SQLException,
		// DataAccessException {
		// List list = new ArrayList();
		// while (rs.next()) {
		// UserPdpErrorTop eusers = new UserPdpErrorTop();
		// eusers.setApn(rs.getString("reqapnni"));
		// eusers.setErrorcount(rs.getInt("cnt"));
		// eusers.setImsi(rs.getString("imsi"));
		// list.add(eusers);
		// }
		// return list;
		// }
		// });
		// List list = (List) object;
		// return list;

	}

	public List getPdpErrorTopList(int start, int end, String orderby) {
		// long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		// long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		String sql = "select * from (select imsi,reqapnni,count(*) as errcount from cdr_mistake where opentime between "
				+ start + " and " + end + " group by imsi,reqapnni order by errcount desc) where rownum<=1000 "+orderby;
		System.out.println("sql:::" + sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					UserPdpErrorTop eusers = new UserPdpErrorTop();
					eusers.setApn(rs.getString("reqapnni"));
					eusers.setErrorcount(rs.getInt("errcount"));
					eusers.setImsi(rs.getString("imsi"));
					list.add(eusers);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;

	}
	/**
	 * 从统计表里拿数据
	 * @param stattime
	 * @param orderby
	 * @return
	 */
	public List getPdpErrorTopList(String stattime, String orderby) {
		// long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		// long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		String sql = "select * from(select imsi,reqapnni, errcount from stat_imsi_apn_error where stattime="+stattime+" order by errcount desc) where rownum<=1000 "+orderby ;
	long now=System.currentTimeMillis();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					UserPdpErrorTop eusers = new UserPdpErrorTop();
					eusers.setApn(rs.getString("reqapnni"));
					eusers.setErrorcount(rs.getInt("errcount"));
					eusers.setImsi(rs.getString("imsi"));
					list.add(eusers);
				}
				return list;
			}
		});
		System.out.println((System.currentTimeMillis()-now)+":::::::" + sql);
		List list = (List) object;
		return list;

	}
	/**
	 * 得到异常行业用户 （实际上把错误码为33的，APN为*.cq的异常行业用户号码单列）
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getExceptionUsers(String apnni, Date date, String orderby, int pageNo, int pageSize) {
		final long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		final long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);
	final	String _date = dfyyyyMMdd.format(date);
		String where = "";
		if (apnni != null && !apnni.equals(""))
			where = "  reqapnni='" + apnni + "'";
		else
			where = "  reqapnni like '%.cq'";

		// String cntsql = "select count(*) as cnt from (select count(reqapnni)
		// from cdr_mistake where reqapnni like '%.cq' and errcode=33 and
		// opentime between "
		// + start / 1000 + " and " + end / 1000 + " ${where} group by reqapnni
		// )";

		String cntsql = "select count(*) from stat_apn_error where "+where+" and errcode=33 and stattime="
				+ _date;

		
		long now = System.currentTimeMillis();
		int totalCount = jdbcTemplate.queryForInt(cntsql);
		System.out.println((System.currentTimeMillis() - now) + "::" + cntsql);
		int startIndex = (pageNo - 1) * pageSize;
		 now = System.currentTimeMillis();
//		String sql = "select * from(select a.*,rownum rn from(select reqapnni ,count(distinct(imsi)) as usercount from cdr_mistake where reqapnni like '%.cq' and errcode=33 and opentime between "
//				+ start
//				/ 1000
//				+ " and "
//				+ end
//				/ 1000
//				+ " ${where} group by reqapnni "
//				+ orderby
//				+ ") a where rownum<="
//				+ (startIndex + pageSize) + ") where rn>" + startIndex;
		
		String sql = "select * from(select a.*,rownum rn from(select reqapnni ,usercount from stat_apn_error where "+where+" and errcode=33 and stattime= "+_date
			+ orderby
			+ ") a where rownum<="
			+ (startIndex + pageSize) + ") where rn>" + startIndex;
		

		// String sql="select reqapnni ,count(distinct(imsi)) from cdr_mistake
		// where reqapnni like '%.cq' and errcode=33 and opentime between
		// "+start/1000+" and "+end/1000+" group by reqapnni ";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ExceptionUsers eusers = new ExceptionUsers();
					eusers.setApnni(rs.getString("reqapnni"));
					eusers.setUsercount(rs.getInt("usercount"));

//					eusers.setImsilist(getExcepionUserDetailByApn(eusers, eusers.getApnni(), start, end));
					eusers.setImsilist(getExcepionUserDetailByApn(eusers, eusers.getApnni(), _date));
					list.add(eusers);
				}
				return list;
			}
		});
		
		System.out.println((System.currentTimeMillis() - now) + "::" +sql);
		
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	
	public List getExcepionUserDetailByApn(final ExceptionUsers eusers, String apn, String stattime) {
		String sql = "select imsi, errcount from stat_imsi_apn_error where reqapnni='" + apn
				+ "' and stattime="+stattime;
		
		long now=System.currentTimeMillis();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					ExceptionUsers.DetailImsi detailimsi = eusers.new DetailImsi();
					detailimsi.setErrcount(rs.getInt("errcount"));
					detailimsi.setImsi(rs.getString("imsi"));
					list.add(detailimsi);
				}
				return list;
			}
		});
		System.out.println((System.currentTimeMillis()-now)+"::"+sql);
		List list = (List) object;
		return list;
	}
	
	public List getExcepionUserDetailByApn(final ExceptionUsers eusers, String apn, long start, long end) {
		String sql = "select imsi,count(imsi) as errorcount from cdr_mistake where reqapnni='" + apn
				+ "' and opentime between " + start / 1000 + " and " + end / 1000 + "group by imsi";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();

				while (rs.next()) {
					ExceptionUsers.DetailImsi detailimsi = eusers.new DetailImsi();
					detailimsi.setErrcount(rs.getInt("errorcount"));
					detailimsi.setImsi(rs.getString("imsi"));
					list.add(detailimsi);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 统计高流量的用户排名,根据时间和所在的apn进行排名 stat_apn_mobile
	 * 
	 * @param date
	 * @param apn
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getHightStreamDayUser(final Date date, String standard, String condition) {

		String sql = "";
		String table = com.sxit.stat.util.StatUtil.getMobileApnTable(date);
		if (standard.equals("1")) {
			// sql="select cellid,sum(allvolume) as allv from stat_cellid where
			// dayflag=1 and (stattime="+_date+") group by cellid having
			// sum(allvolume)>="+condition+") order by allv desc";
			sql = "select mobile,apnni,upvolume,downvolume,allvolume,periodlen from " + table
					+ " where dayflag=1 and allvolume>=" + condition + " order by allvolume desc";

		} else {
			// sql = "select
			// mobile,apnni,upvolume,downvolume,allvolume,periodlen from " +
			// table
			// + " where dayflag=1 and rownum<=" + condition + " order by
			// allvolume desc";
			//			
			sql = "select * from(select mobile,apnni,upvolume,downvolume,allvolume,periodlen from  " + table
					+ "  where dayflag=1 order by allvolume desc) where rownum<=" + condition
					+ " order by allvolume desc";
		}
		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					MobilesTop model = new MobilesTop();
					model.setMobile(rs.getString("mobile"));
					model.setApnni(rs.getString("apnni"));
					model.setUpvolume(rs.getFloat("upvolume"));
					model.setDownvolume(rs.getFloat("downvolume"));
					model.setPeriodlen(rs.getInt("periodlen"));
					model.setAllvolume(rs.getFloat("allvolume"));
					model.setDate(df.format(date));
					model.setDatehour(dfsec.format(date));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	public List getHightStreamHourUser(String standard, String condition, Date date, final String hour) {
		final String _date = df.format(date);
		String start = _date + " " + hour + ":00:00";
		String end = _date + " " + hour + ":59:59";

		int _start = getDfSec(start);
		int _end = getDfSec(end);
		String table = com.sxit.stat.util.StatUtil.getMobileApnTable(date);

		String sql = "";
		if (standard.equals("1")) {
			sql = "select mobile,apnni,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(allvolume) as allvolume,sum(periodlen) as periodlen from "
					+ table
					+ " where dayflag=0 and (stattime>="
					+ _start
					+ " and stattime<="
					+ _end
					+ ") group by mobile,apnni having sum(allvolume)>=" + condition + ") order by allvolume desc";
		} else {
			sql = "select mobile,apnni,sum(upvolume) as upvolume,sum(downvolume) as downvolume,sum(allvolume) as allvolume,sum(periodlen) as periodlen from "
					+ table
					+ " where dayflag=0 and (stattime>="
					+ _start
					+ " and stattime<="
					+ _end
					+ ") group by mobile,apnni ) where rownum<=" + condition + " order by allvolume desc";
		}

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					MobilesTop model = new MobilesTop();
					model.setMobile(rs.getString("mobile"));
					model.setApnni(rs.getString("apnni"));
					model.setUpvolume(rs.getFloat("upvolume"));
					model.setDownvolume(rs.getFloat("downvolume"));
					model.setPeriodlen(rs.getInt("periodlen"));
					model.setAllvolume(rs.getFloat("allvolume"));
					model.setDate(_date);

					model.setDatehour(_date + " " + hour + ":00");
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 得到连续n天都有某个错误的号码
	 * 
	 * @param firstdate
	 *            第一天
	 * @param count
	 *            总计多少天
	 * @return
	 */
	public List getConinueErrorMobileList(Date firstdate, int count, int errcode) {
		String first = dfyyyyMMdd.format(firstdate);
		Date lastdate = com.sxit.stat.util.StatUtil.getPrevCountDate(firstdate, count - 1);
		String last = dfyyyyMMdd.format(lastdate);
		// String sql="select imsi,msisdn,count(*) from STAT_IMSI_APN_ERROR
		// where errcode="+errcode+" stattime between "+first+" and "+last+"
		// group by imsi,msisdn having count(*)>= "+ count;

		String sql = "select imsi,count(*) from STAT_IMSI_APN_ERROR where errcode=" + errcode
				+ " and stattime between " + last + " and " + first + " group by imsi having count(*)>= " + count;
		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				int i = 0;
				ContinueMobiles mobiles = null;
				while (rs.next()) {
					if (i % 4 == 0) {
						mobiles = new ContinueMobiles();
						mobiles.setImsi1(rs.getString("imsi"));
						list.add(mobiles);
						i = i + 1;
					}
					if (rs.next()) {
						mobiles.setImsi2(rs.getString("imsi"));
						i = i + 1;
					}
					if (rs.next()) {
						mobiles.setImsi3(rs.getString("imsi"));
						i = i + 1;
					}
					if (rs.next()) {
						mobiles.setImsi4(rs.getString("imsi"));
						i = i + 1;
					}

				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 得到某天的错误情况统计
	 * 
	 * @param date
	 * @return
	 */
	public ErrorCodeStat getErrcodeList(Date date, String errorcode) {
		// 直接到cdr_mistake表中取数据就是了

		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);

		String table = "cdr_mistake";
		if (!errorcode.equals("33")) {
			table = "cdr_mistake_no33";
		}
		String countsql = "select count(distinct(imsi)) as usercount,count(*) as errorcount from  " + table
				+ " where errcode=" + errorcode + " and opentime between " + start / 1000 + " and " + end / 1000;

		System.out.println(countsql);

		final ErrorCodeStat stat = new ErrorCodeStat();
		stat.setErrorcode(errorcode);
		jdbcTemplate.query(countsql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					stat.setErrorcount(rs.getInt("errorcount"));
					stat.setUsercount(rs.getInt("usercount"));

				}
				return null;
			}
		});

		String sql = "select reqapnni,imsi,count(*) as errorcount from  " + table + " where errcode='" + errorcode
				+ "' and opentime between " + start / 1000 + " and " + end / 1000 + " group by imsi,reqapnni";

		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ErrorCodeStat.Details details = stat.new Details();

					details.setApn(rs.getString("reqapnni"));
					details.setImsi(rs.getString("imsi"));
					details.setPdperrorcnt(rs.getInt("errorcount"));

					list.add(details);
				}
				return list;
			}
		});
		List list = (List) object;
		stat.setDetailist(list);

		return stat;

	}

	/**
	 * 得到pdp失败情况总表，主要是当天累加值以及本时间段增加值以及上个时间段增加值
	 * 
	 * 按天和小时统计来
	 * 
	 * @param date
	 *            统计时间
	 * @param dayflag
	 *            统计某天的还是统计某个时间点的
	 * @return
	 */
	public List getApnErrors(String date, String hour, String dayflag, String reqapnni, String errcode, String orderby) {

		String sql = "";
		// if (dayflag.equals("1")) {
		String stattime = date.replace("-", "");
		sql = "select * from stat_apn_error where 1=1";
		if (reqapnni != null && !reqapnni.equals(""))
			sql += " and apnni='" + reqapnni + "'";
		if (errcode != null && !errcode.equals(""))
			sql += " and errcode='" + errcode + "'";
		sql += " and dayflag=1 and stattime=" + stattime + orderby;
		// }
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					StatApnErrors model = new StatApnErrors();
					int usercount = rs.getInt("USERCOUNT");
					int errorcount = rs.getInt("ERRORCOUNT");
					long stattime = rs.getLong("stattime");
					String apnni = rs.getString("apnni");

					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setApnni(apnni);
					model.setDayflag(rs.getString("dayflag"));
					model.setErrorcount(errorcount);
					model.setStattime(stattime);
					model.setUsercount(usercount);
					model.setErrcode(rs.getInt("errcode"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	public PaginationSupport getApnErrors(String date, String hour, String dayflag, String reqapnni, String errcode,
			String orderby, int pageNo, int pageSize) {

		String stattime = date.replace("-", "");

		String where = "";
		String cntsql = "select count(*) from stat_apn_error where 1=1 ${where} and dayflag=1 and stattime=" + stattime
				+ orderby;
		if (reqapnni != null && !reqapnni.equals(""))
			where += " and reqapnni='" + reqapnni + "'";
		if (errcode != null && !errcode.equals(""))
			where += " and errcode='" + errcode + "'";
		cntsql = cntsql.replace("${where}", where);

		System.out.println(cntsql);

		int totalCount = jdbcTemplate.queryForInt(cntsql);

		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select * from stat_apn_error where 1=1 ${where} and dayflag=1 and stattime="
				+ stattime + orderby + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;
		sql = sql.replace("${where}", where);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					StatApnErrors model = new StatApnErrors();
					int usercount = rs.getInt("USERCOUNT");
					int errorcount = rs.getInt("ERRORCOUNT");
					long stattime = rs.getLong("stattime");
					String apnni = rs.getString("reqapnni");

					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setApnni(apnni);
					model.setDayflag(rs.getString("dayflag"));
					model.setErrorcount(errorcount);
					model.setStattime(stattime);
					model.setUsercount(usercount);
					model.setErrcode(rs.getInt("errcode"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

}