/**
 * 
 */
package com.sxit.stat.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.stat.models.ApnCellStatModel;
import com.sxit.stat.models.ApnStatModel;
import com.sxit.stat.models.BsnRncStatModel;
import com.sxit.stat.models.CellStatModel;
import com.sxit.stat.models.NsvcStatModel;
import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.models.TotalStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * @author 华锋 Oct 16, 2009-4:54:14 PM
 * 
 */
public class StatService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("HH:00");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static List<String> ALL_TIME_LIST = new ArrayList<String>();
	private static Map<String, String> ALL_HOUR_LIST = new LinkedHashMap<String, String>();

	static {
		ALL_TIME_LIST.add("00:00");
		ALL_TIME_LIST.add("01:00");
		ALL_TIME_LIST.add("02:00");
		ALL_TIME_LIST.add("03:00");
		ALL_TIME_LIST.add("04:00");
		ALL_TIME_LIST.add("05:00");
		ALL_TIME_LIST.add("06:00");
		ALL_TIME_LIST.add("07:00");
		ALL_TIME_LIST.add("08:00");
		ALL_TIME_LIST.add("09:00");
		ALL_TIME_LIST.add("10:00");
		ALL_TIME_LIST.add("11:00");
		ALL_TIME_LIST.add("12:00");
		ALL_TIME_LIST.add("13:00");
		ALL_TIME_LIST.add("14:00");
		ALL_TIME_LIST.add("15:00");
		ALL_TIME_LIST.add("16:00");
		ALL_TIME_LIST.add("17:00");
		ALL_TIME_LIST.add("18:00");
		ALL_TIME_LIST.add("19:00");
		ALL_TIME_LIST.add("20:00");
		ALL_TIME_LIST.add("21:00");
		ALL_TIME_LIST.add("22:00");
		ALL_TIME_LIST.add("23:00");

		ALL_HOUR_LIST.put("00", "00,00");
		ALL_HOUR_LIST.put("01", "01,00");
		ALL_HOUR_LIST.put("02", "02,00");
		ALL_HOUR_LIST.put("03", "03,00");
		ALL_HOUR_LIST.put("04", "04,00");
		ALL_HOUR_LIST.put("05", "05,00");
		ALL_HOUR_LIST.put("06", "06,00");
		ALL_HOUR_LIST.put("07", "07,00");
		ALL_HOUR_LIST.put("08", "08,00");
		ALL_HOUR_LIST.put("09", "09,00");
		ALL_HOUR_LIST.put("10", "10,00");
		ALL_HOUR_LIST.put("11", "11,00");
		ALL_HOUR_LIST.put("12", "12,00");
		ALL_HOUR_LIST.put("13", "13,00");
		ALL_HOUR_LIST.put("14", "14,00");
		ALL_HOUR_LIST.put("15", "15,00");
		ALL_HOUR_LIST.put("16", "16,00");
		ALL_HOUR_LIST.put("17", "17,00");
		ALL_HOUR_LIST.put("18", "18,00");
		ALL_HOUR_LIST.put("19", "19,00");
		ALL_HOUR_LIST.put("20", "20,00");
		ALL_HOUR_LIST.put("21", "21,00");
		ALL_HOUR_LIST.put("22", "22,00");
		ALL_HOUR_LIST.put("23", "23,00");

	}

	private long getDayStartTime(Date date) {
		try {
			String datestr = df.format(date);
			String startstr = datestr + " 00:00:00";
			Date start = dfyyyyMmddHHmmss.parse(startstr);
			return start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
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
	 * <pre>
	 * 得到一段时间之内的总流量、总用户数、以及平均流量、按天计算
	 * 这里要保证1天的记录都是唯一的,也就是stattime以及systemtype
	 * 总流量分析
	 * </pre>
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List getDaysTotalStream(Date start, Date end) {
		int _from = Integer.parseInt(dfyyyyMMdd.format(start));
		int _to = Integer.parseInt(dfyyyyMMdd.format(end));
		String sql = "select sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME,STATTIME  from  STAT_SGSN where STATTIME between "
				+ _from + " and " + _to + " and dayflag=1 group by STATTIME";
		// int _from = (int) (start.getTime() / 1000);
		// int _to = (int) end.getTime() / 1000;

		System.out.println(sql);

		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalStatModel model = new TotalStatModel();
					int usercount = rs.getInt("USERCOUNT");

					// System.out.println("rs.getDouble(\"USERCOUNT\"):::"+rs.getDouble("USERCOUNT"));

					double all = rs.getDouble("ALLVOLUME");

					// System.out.println("all:::"+all);

					int stattime = rs.getInt("STATTIME");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser((int) (usercount*0.3 ));
					// System.out.println("model.getTotalUser()::"+model.getTotalUser());
					// model.setDate(df.format(date));
					model.setDate(stattime + ""); // 总数的按天统计，时间都是20091011的形式
					list.add(model);
				}
				return list;
			}
		});

		return (List) object;
	}

	/**
	 * 同上,只是将数据拆分成2，3g 两种形式 23g的流量分析
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public List getDaysTotalStream23g(Date start, Date end) {
		String sql = "select sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) ALLVOLUME,NETTYPE,stattime  from  STAT_SGSN where dayflag=1 and STATTIME between ? and ? group by stattime,NETTYPE";
		// int _from = (int) (start.getTime() / 1000);
		// int _to = (int) end.getTime() / 1000;
		int _from = Integer.parseInt(dfyyyyMMdd.format(start));
		int _to = Integer.parseInt(dfyyyyMMdd.format(end));
		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalStatModel model = new TotalStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String type = rs.getString("NETTYPE");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					// model.setTotalUser(usercount);
					model.setTotalUser((int) (usercount*0.3));
					// model.setDate(df.format(date));
					model.setDate(stattime + ""); // 总数的按天统计，时间都是20091011的形式
					model.setNettype(type);
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,其实可以分时的方式,得到每个小时的流量
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStream(Date date) {
		String sql = "select SGSNID,sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME  from  STAT_SGSN where dayflag=1 and STATTIME=? group by sgsnid ";
		// int _date = (int) (date.getTime() / 1000);
		// int _from=Integer.parseInt(dfyyyyMmdd.format(start));
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					// model.setTotalUser(usercount);
					model.setTotalUser((int) (usercount *0.5));
					// model.setDate(df.format(date));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					list.add(model);
				}
				return list;
			}
		});

		return (List) object;
	}

	/**
	 * 
	 * 得到某一天的sgsn流量,其实可以分时的方式,得到每个小时的流量
	 * 
	 * @param date
	 * @return
	 */
	public List getDaySgsnStream23g(Date date) {
		String sql = "select SGSNID,USERCOUNT,ALLVOLUME,STATTIME,NETTYPE from  STAT_SGSN where dayflag=1 and STATTIME=? order by sgsnid,NETTYPE";
		// int _date = (int) (date.getTime() / 1000);
		System.out.println(sql);

		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					// model.setTotalUser(usercount);
					model.setTotalUser((int) (usercount*0.5));
					// model.setDate(df.format(date));
					model.setDate(_date + "");
					model.setSgsnid(sgsnid);
					model.setNettype(rs.getString("NETTYPE"));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 统计某天的bsc/rnc流量
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getBscRncStat(Date date, String sgsnid, int pageNo, int pageSize) {
		// int _date = (int) (date.getTime() / 1000);
		final String _date = dfyyyyMMdd.format(date);

		String where = " where 1=1";
		if (sgsnid != null && !sgsnid.equals(""))
			where += " and sgsnid='" + sgsnid + "'";

		where += " and dayflag=1 and stattime=" + _date;

		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) as cnt from  STAT_BSC {0}";
			countsql = countsql.replace("{0}", where);
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select BSCID,SGSNID,STATTIME,USERCOUNT,ALLVOLUME from  STAT_BSC {0} order by sgsnid) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		sql = sql.replace("{0}", where);

		System.out.println(sql);

		// Object[] args = new Object[] { _date };
		// int[] argTypes = new int[] { Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					BsnRncStatModel model = new BsnRncStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					// model.setDate(df.format(date));
					model.setDate(_date);
					model.setSgsnid(sgsnid);
					// model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	private List findNsvcsByBscid(String bscid) {
		String sql = "select nsvc from set_nsvc where bscid='" + bscid + "'";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					list.add(rs.getString("nsvc"));
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * 统计某天的gblink流量
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getNsvcStat(Date date, String bscid, int pageNo, int pageSize) {

		long start = StatUtil.getDateTime(date);
		long end = StatUtil.getOneDayAfter(start);
		final String _date=df.format(date);
	
		String where = " where 1=1";
		if (bscid != null && !bscid.equals("")) {
			// where+=" and bscid='"+bscid+"'";
			List list = findNsvcsByBscid(bscid);
			if (list.size() == 0) {
				List resultlist = new ArrayList();
				PaginationSupport ps = new PaginationSupport(resultlist, 0, pageSize, 0);
				return ps;
			}
			where += " and nsvc in(" + StatUtil.list2str(list) + ")";
		}
		where += " and stattime between " + start / 1000 + " and " + end / 1000;

		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) as cnt from (select nsvc from stat_nsvc {0} group by nsvc) ";
			countsql = countsql.replace("{0}", where);
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;

		String sql = "select * from(select a.*,rownum rn from(select nsvc,sum(difference) as difference from  stat_nsvc {0} group by nsvc order by difference desc ) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		sql = sql.replace("{0}", where);

		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					NsvcStatModel model = new NsvcStatModel();
					model.setNsvcid(rs.getString("nsvc"));
					model.setDifference(rs.getFloat("difference"));
					model.setDate(_date);
					
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 按天统计各cellid的流量情况,估计要分页(暂不处理分页)
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getCellDayStat(Date date, int pageNo, int pageSize) {
		// int _date = (int) (date.getTime() / 1000);
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) from STAT_CELLID where dayflag=1 and STATTIME=" + _date;

			// System.out.println(countsql);

			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select CELLID,BSCID,NETTYPE,STATTIME,USERCOUNT,ALLVOLUME from  STAT_CELLID where dayflag=1 and STATTIME=? order by CELLID,NETTYPE) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;

		// System.out.println(sql);

		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					CellStatModel model = new CellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					// model.setDate(df.format(date));
					// model.setSgsnid(sgsnid);
					model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 按时间段统计某cellid的流量情况
	 * 
	 * @param date
	 * @return
	 */
	public List getCellDayTimeStat(Date date, String cellid) {

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		int _datestart = (int) (start / 1000);
		int _dateend = (int) (end / 1000);

		String sql = "select CELLID,BSCID,NETTYPE,STATTIME,NETTYPE,USERCOUNT,ALLVOLUME from  STAT_CELLID where dayflag=0 and CELLID='"
				+ cellid + "' and STATTIME between " + _datestart + " and " + _dateend + " order by stattime";
		System.out.println(sql);
		// Object[] args = new Object[] { cellid,_datestart,_dateend };
		// int[] argTypes = new int[] { Types.VARCHAR,Types.INTEGER,
		// Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		final Map<String, CellStatModel> timelist = new HashMap<String, CellStatModel>();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					int stattime = rs.getInt("STATTIME");
					Date date = new Date();
					date.setTime(stattime * 1000);
					String datetime = dftime.format(date);
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					if (timelist.containsKey(datetime)) {
						CellStatModel model = timelist.get(datetime);
						model.setTotalStream(model.getTotalStream() + all);
						model.setTotalUser(model.getTotalUser() + usercount);
						timelist.remove(datetime);
						timelist.put(datetime, model);

					} else {

						CellStatModel model = new CellStatModel();

						// String sgsnid=rs.getString("SGSNID");

						model.setTotalStream(all);
						model.setTotalUser(usercount);
						model.setDate(df.format(date));
						// model.setSgsnid(sgsnid);
						model.setNettype(rs.getString("NETTYPE"));
						model.setBscrncid(rs.getString("BSCID"));
						model.setCellid(rs.getString("CELLID"));
						model.setDatetime(datetime);

						timelist.put(model.getDatetime(), model);
					}
					// System.out.println(timelist);
				}
				return null;
			}
		});
		// System.out.println(timelist);
		List result = new ArrayList();
		for (int i = 0; i < ALL_TIME_LIST.size(); i++) {
			String time = ALL_TIME_LIST.get(i);
			if (timelist.containsKey(time)) {
				result.add(timelist.get(time));
			} else {
				CellStatModel model = new CellStatModel();
				model.setDatetime(time);
				model.setCellid(cellid);
				model.setDate(df.format(date));

				result.add(model);
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * apn业务分时段统计
	 * 
	 * SELECT * FROM 
	 * (
	 * SELECT A.*, ROWNUM RN 
	 * FROM (SELECT * FROM TABLE_NAME) A 
	 * WHERE ROWNUM &lt;= 40
	 * )
	 * WHERE RN &gt;= 21
	 * </pre>
	 * 
	 * @param date
	 * @param cellid
	 * @return
	 */
	public PaginationSupport getApnDayStat(Date date, final int pageNo, final int pageSize) {
		// int _date = (int) (date.getTime() / 1000);
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String cntsql = "select count(*) as cnt from STAT_APN where dayflag=1 and apnni not in('cmwap','cmnet') and STATTIME="
					+ _date + " ";
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}
		int startIndex = (pageNo - 1) * pageSize;
		// String sql = "select * from(select APNNI,STATTIME,USERCOUNT,ALLVOLUME
		// from STAT_APN where STATTIME="+_date+" and dayflag=1)";

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select APNNI,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN where  dayflag=1 and apnni not in('cmwap','cmnet') and  STATTIME="
				+ _date + ") A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN > " + startIndex;

		// Object[] args = new Object[] { _date };
		// int[] argTypes = new int[] { Types.INTEGER };
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnStatModel model = new ApnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					// model.setDate(df.format(date));
					model.setDate(_date + "");
					// model.setSgsnid(sgsnid);
					// model.setDatetime(dftime.format(date));
					model.setDate(_date + "");
					model.setApnid(rs.getString("APNNI"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 按时间段统计某ellid的流量情况
	 * 
	 * @param date
	 * @return
	 */
	public List getApnDayTimeStat(final Date date, String apnid) {
		String sql = "select APNNI,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN where  APNNI=? and dayflag=0 and STATTIME between ? and ? order by STATTIME";
		// int _date = (int) (date.getTime() / 1000);

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		int _datestart = (int) (start / 1000);
		int _dateend = (int) (end / 1000);
		Object[] args = new Object[] { apnid, _datestart, _dateend };
		final Map<String, ApnStatModel> timelist = new HashMap<String, ApnStatModel>();

		// Object[] args = new Object[] { _date, apnid };
		int[] argTypes = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					ApnStatModel model = null;
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					Date _date = new Date();
					_date.setTime(stattime * 1000);
					String datetime = dftime.format(_date);

					if (timelist.containsKey(datetime)) {
						model = timelist.get(datetime);
						model.setTotalStream(model.getTotalStream() + all);
						model.setTotalUser(model.getTotalUser() + usercount);
						timelist.remove(datetime);
					} else {
						model = new ApnStatModel();
						model.setTotalStream(all);
						model.setTotalUser(usercount);
						model.setDate(df.format(date));
						model.setDatetime(datetime);
						model.setApnid(rs.getString("APNNI"));
					}

					timelist.put(datetime, model);
				}
				// return list;
				return null;
			}
		});
		// return (List) object;
		List result = new ArrayList();
		for (int i = 0; i < ALL_TIME_LIST.size(); i++) {
			String time = ALL_TIME_LIST.get(i);
			if (timelist.containsKey(time)) {
				result.add(timelist.get(time));
			} else {
				ApnStatModel model = new ApnStatModel();
				model.setDatetime(time);
				model.setApnid(apnid);
				model.setDate(df.format(date));

				result.add(model);
			}
		}

		return result;
	}

	/**
	 * apn的分cell统计
	 * 
	 * @param date
	 * @param apnid
	 * @return
	 */
	public List getApnCellDayStat(Date date, String apnid) {
		// int _date = (int) (date.getTime() / 1000);
		final int _date = Integer.parseInt(dfyyyyMMdd.format(date));
		String sql = "select APNNI,CELLID,STATTIME,USERCOUNT,ALLVOLUME from  STAT_CELLID_APN  where dayflag=1 AND APNNI='"
				+ apnid + "' and STATTIME=" + _date;
		System.out.println(sql);
		// Object[] args = new Object[] { apnid,_date };
		// int[] argTypes = new int[] { Types.VARCHAR ,Types.INTEGER};
		// Object object = jdbcTemplate.query(sql, args, argTypes, new
		// ResultSetExtractor() {
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnCellStatModel model = new ApnCellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					double all = rs.getDouble("ALLVOLUME");
					// int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					// Date date = new Date();
					// date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(_date + "");
					model.setCellid(rs.getString("CELLID"));
					model.setApnid(rs.getString("APNNI"));
					// model.setSgsnid(sgsnid);
					model.setDatetime(_date + "");
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

}
