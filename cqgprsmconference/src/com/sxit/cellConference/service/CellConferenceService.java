/**
 * 
 */
package com.sxit.cellConference.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.cellConference.models.AlarmCell;
import com.sxit.cellConference.models.ConferenceCell;
import com.sxit.common.PaginationSupport;
import com.sxit.models.system.SysUser;
import com.sxit.stat.models.CellStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * @author 华锋 Nov 26, 2009-12:36:18 AM
 * 
 */
public class CellConferenceService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dff = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhhmmss = new java.text.SimpleDateFormat("HHmmss");
	private JdbcTemplate jdbcTemplate;
	public static Map<String, String> AMTIMELIST;
	public static Map<String, String> PMTIMELIST;
	public static Map<String, String> MINUTELIST;
	public static Hashtable<String, ConferenceCell> CCLIST;
	static {
		CCLIST = new Hashtable<String, ConferenceCell>();
		AMTIMELIST = new LinkedHashMap<String, String>();
		PMTIMELIST = new LinkedHashMap<String, String>();
		MINUTELIST = new LinkedHashMap<String, String>();
		for (int i = 8; i <= 12; i++) {
			AMTIMELIST.put(i + "", i + "");
		}
		for (int i = 13; i <= 20; i++) {
			PMTIMELIST.put(i + "", i + "");
		}

		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				MINUTELIST.put("0" + i, i + "");
			else
				MINUTELIST.put("" + i, i + "");
		}
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 得到某天的所有会议小区数据
	 * 
	 * @return
	 */
	public PaginationSupport getDayConferenceList(String date, String cellid, String lac, int pageNo, int pageSize,
			boolean isalarm, String orderby) {

		long start = 0;
		long end = 0;
		if (date != null) {
			start = com.sxit.stat.util.StatUtil.getDateTime(date);
			end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		} else {
			start = 0;
			end = Long.MAX_VALUE;
		}
		String where = "";
		if (isalarm)
			where = " and alarmflag=1";

		if (!(lac == null || lac.equals(""))) {
			where += " and lac="+lac;
		}
		if (!(cellid == null || cellid.equals(""))) {
			where += " and cellid="+cellid;
		}

		String countsql = "select count(*) from alarm_cellid where (stattime between " + start / 1000 + " and " + end
				/ 1000 + ") " + where;
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;

		sql = "select * from(select a.*,rownum rn from(select * from alarm_cellid where (stattime between " + start
				/ 1000 + " and " + end / 1000 + ")" + where + orderby + ") a where rownum<=" + (startIndex + pageSize)
				+ ") where rn>" + startIndex;

		System.out.println(countsql);
		System.out.println(sql);
		int totalCount = 0;

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					AlarmCell cell = new AlarmCell();
					cell.setCellid(rs.getString("cellid"));
					cell.setLac(rs.getString("lac"));
					cell.setStattime(rs.getLong("stattime"));
					cell.setPredayallvolume(rs.getDouble("predayallvolume"));
					cell.setPretimeallvolume(rs.getDouble("pretimeallvolume"));
					cell.setPredayusercount(rs.getInt("predayusercount"));
					cell.setPretimeusercount(rs.getInt("pretimeusercount"));
					cell.setUsercount(rs.getInt("usercount"));
					cell.setAllvolume(rs.getDouble("allvolume"));
					list.add(cell);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {
			totalCount = jdbcTemplate.queryForInt(countsql);
		}

		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	/**
	 * 新增一个会议小区
	 * 
	 * @param cellid
	 * @param lac
	 * @param userid
	 * @param username
	 */
	public void saveConferenceCell(String cellid, String lac, float userview, float flowview, int timeview1,
			int timeview2, SysUser loginuser) {

		long now = System.currentTimeMillis() / 1000;

		String flowviewf = com.sxit.system.util.NumberUtil.toMoney(flowview / 100.0);
		String userviewf = com.sxit.system.util.NumberUtil.toMoney(userview / 100.0);

		String sql = "insert into SET_CELL_CONFERENCE(CELLID,lac,UPDATETIME,ISACTIVE,createuserid,createusername,timeview1,timeview2,flowalarmvalue,useralarmvalue) values('"
				+ cellid
				+ "','"
				+ lac
				+ "',"
				+ now
				+ ",1,"
				+ loginuser.getUserid()
				+ ",'"
				+ loginuser.getUsername()
				+ "'," + timeview1 + "," + timeview2 + "," + flowviewf + "," + userviewf + ")";

		jdbcTemplate.execute(sql);

	}

	public boolean isExistCell(String cellid, String lac) {
		String sql = "select count(*) as cnt from SET_CELL_CONFERENCE where cellid='" + cellid + "' and lac='" + lac
				+ "'";

		return jdbcTemplate.queryForInt(sql) > 0 ? true : false;
	}

	/**
	 * 得到需要监控的会议小区
	 * 
	 * @param cellid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getConferenceCellids(String cellid, int pageNo, int pageSize) {
		String countsql = "";
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;
		countsql = "select count(*) from SET_CELL_CONFERENCE  ";
		sql = "select * from(select a.*,rownum rn from(select * from SET_CELL_CONFERENCE ${where} order by CELLID) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;
		String where = "";
		if (cellid != null && !cellid.equals("")) {
			where += " where cellid='" + cellid + "'";
		}

		countsql = countsql + where;
		sql = sql.replace("${where}", where);

		System.out.println(countsql);
		System.out.println(sql);
		int totalCount = 0;
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ConferenceCell cell = new ConferenceCell();
					cell.setCellid(rs.getString("cellid"));
					cell.setCreateuserid(rs.getInt("createuserid"));
					cell.setCreateusername(rs.getString("createusername"));
					cell.setUpdatetime(rs.getLong("updatetime"));
					cell.setLac(rs.getString("lac"));
					cell.setTimeview1(rs.getInt("timeview1"));
					cell.setTimeview2(rs.getInt("timeview2"));
					cell.setFlowalarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
							.getFloat("flowalarmvalue") * 100))));
					cell.setUseralarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
							.getFloat("useralarmvalue") * 100))));
					list.add(cell);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		} else {
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	private static long updatetime = 0;

	/**
	 * 得到所有的监控小区
	 * 
	 * @return
	 */
	public void getAllConferenceCell(boolean notime) {

		long now = System.currentTimeMillis();
		boolean timepass = (now - updatetime > 60 * 60 * 60 * 1000); // 1小时刷1次

		if (notime || timepass) {

			String sql = "select * from SET_CELL_CONFERENCE";
			jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
					CCLIST.clear();
					while (rs.next()) {
						ConferenceCell cell = new ConferenceCell();
						cell.setCellid(rs.getString("cellid"));
						cell.setCreateuserid(rs.getInt("createuserid"));
						cell.setCreateusername(rs.getString("createusername"));
						cell.setUpdatetime(rs.getLong("updatetime"));
						cell.setLac(rs.getString("lac"));
						cell.setTimeview1(rs.getInt("timeview1"));
						cell.setTimeview2(rs.getInt("timeview2"));
						cell.setFlowalarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
								.getFloat("flowalarmvalue") * 100))));
						cell.setUseralarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
								.getFloat("useralarmvalue") * 100))));

						CCLIST.put(cell.getCellkey(), cell);
					}
					return null;
				}
			});
			updatetime = now;
		}
	}

	/**
	 * 
	 * @param lac
	 * @param cellid
	 * @return
	 */
	public ConferenceCell getConferenceCell(String lac, String cellid) {
		String sql = "select * from SET_CELL_CONFERENCE where lac='" + lac + "' and cellid='" + cellid + "'";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					ConferenceCell cell = new ConferenceCell();
					cell.setCellid(rs.getString("cellid"));
					cell.setCreateuserid(rs.getInt("createuserid"));
					cell.setCreateusername(rs.getString("createusername"));
					cell.setUpdatetime(rs.getLong("updatetime"));
					cell.setLac(rs.getString("lac"));
					cell.setTimeview1(rs.getInt("timeview1"));
					cell.setTimeview2(rs.getInt("timeview2"));
					cell.setFlowalarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
							.getFloat("flowalarmvalue") * 100))));
					cell.setUseralarmvalue(Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney((rs
							.getFloat("useralarmvalue") * 100))));

					return cell;
				}
				return null;
			}
		});
		if (object == null)
			return null;
		return (ConferenceCell) object;
	}

	/**
	 * 删除监控的会议小区
	 * 
	 * @param cellid
	 * @param lac
	 */
	public void deleteConferenceCell(String cellid, String lac) {
		String sql = "delete from SET_CELL_CONFERENCE where CELLID ='" + cellid + "' and lac='" + lac + "'";
		jdbcTemplate.execute(sql);

	}

	private static long times=0;
	/**
	 * today的形式为2009-12-12的形式
	 * 
	 * @param today
	 */
	public void updatePreDayData(final String today) {
//		if(true)
//return;
//		if(System.currentTimeMillis()-times<60*60*1000){
//			return;
//		}
		long now=System.currentTimeMillis();
		times=System.currentTimeMillis();
		Date date = com.sxit.stat.util.StatUtil.getDate(today);
		Date predate = com.sxit.stat.util.StatUtil.getPrevCountDate(date, 1);
		final long prestart =com.sxit.stat.util.StatUtil.getDateTime(predate);
		final long preend = com.sxit.stat.util.StatUtil.getDateTime(date);
		final long nowend =preend + 24 * 60 * 60 * 1000;
		final long fenjiexian = (preend + nowend) / 2 + 3 * 60 * 60 * 1000;
		final long yefenjiexian = (prestart + preend) / 2 + 3 * 60 * 60 * 1000;
		System.out.println("fenjiexian:::::::::::"+fenjiexian);
		String sql = "select cellid,lac,allvolume,usercount,stattime from alarm_cellid where stattime between "
				+ prestart / 1000 + " and " + preend / 1000;
		final List<String> sqls = new ArrayList<String>();
System.out.println(sql);
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					String cellid = rs.getString("cellid");
					String lac = rs.getString("lac");
					String allvolume = rs.getString("allvolume");
					String usercount = rs.getString("usercount");
					long stattime = rs.getLong("stattime");
					if (stattime > prestart / 1000 && stattime < yefenjiexian / 1000) {
						String sql="update alarm_cellid set predayallvolume=" + allvolume
						+ ",predayusercount="+usercount+" where lac='" + lac + "' and cellid='" + cellid
						+ "' and stattime between " + preend / 1000 + " and " + fenjiexian / 1000;
						
						
						sqls.add(sql);
					} else {
						String sql="update alarm_cellid set predayallvolume=" + allvolume
								+ ",predayusercount="+usercount+" where lac='" + lac + "' and cellid='" + cellid
								+ "' and stattime between " + fenjiexian / 1000 + " and " + nowend / 1000;
						
						
						sqls.add(sql);
					}
				}
				return null;
			}
		});

//		System.out.println(sqls);
		String[] sqlsarr = new String[sqls.size()];
		sqls.toArray(sqlsarr);
		if(sqlsarr.length>0)
		jdbcTemplate.batchUpdate(sqlsarr);

	
		System.out.println("花费时间:"+(System.currentTimeMillis()-now));
	}

	/**
	 * 更新
	 * 
	 * @param cellid
	 * @param lac
	 * @param userview
	 * @param flowview
	 * @param timeview
	 */
	public void updateConferenceCell(String cellid, String lac, float userview, float flowview, int timeview1,
			int timeview2) {
		long updatetime = System.currentTimeMillis() / 1000;

		String flowf = com.sxit.system.util.NumberUtil.toMoney(flowview / 100.0);
		String usercountf = com.sxit.system.util.NumberUtil.toMoney(userview / 100.0);
		String sql = "update SET_CELL_CONFERENCE set flowalarmvalue=" + flowf + ",useralarmvalue=" + usercountf
				+ ",timeview1=" + timeview1 + ",timeview2=" + timeview2 + ",updatetime=" + updatetime
				+ " where CELLID ='" + cellid + "' and lac='" + lac + "'";
		jdbcTemplate.execute(sql);

	}

	/**
	 * 得到连续几天这个hour的统计数据情况，这个date可以是当天时间
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public List getConitnueTimeStatCells(String cellid, String lac, final String date, String hour, int days) {
		long datetime = StatUtil.getDateHourTime(date + " 23:59:59") / 1000;
		final Map<String, CellStatModel> list = new HashMap<String, CellStatModel>();
		// 得到前面7天的时间
		Date thedate = com.sxit.stat.util.StatUtil.getDate(date);
		for (int i = days - 1; i >= 0; i--) {
			Date predate = com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, i);
			CellStatModel model = new CellStatModel();
			String _date = dff.format(predate);
			model.setDate(_date);
			list.put(_date, model);
		}
		System.out.println("hour::::" + hour);

		// 得到这个cellid,lac的统计时间,这个是根据那个stattime来的
		final int _hour = Integer.parseInt(hour.replace(":", "")) + 20000;

		String sql = "select * from(select cellid,lac,allvolume,usercount,predayusercount,pretimeusercount,predayallvolume,pretimeallvolume,stattime from alarm_cellid where cellid="
				+ cellid
				+ " and lac="
				+ lac
				+ " and stattime<="
				+ datetime
				+ " order by stattime desc) where rownum<"
				+ (days + 1) * 2;
		System.out.println(sql);
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					long stattime = rs.getLong("stattime");
					java.sql.Timestamp timestamp = new java.sql.Timestamp(stattime * 1000);
					String _date = dff.format(timestamp);
					if (list.containsKey(_date)) {
						int time = Integer.parseInt(dfhhmmss.format(timestamp));
						System.out.println(_date + ",,,time:" + time + ",,,_hour:" + _hour);
						if ((_hour < 125900 && time < 150000) || (_hour > 130000 && time >= 150000)) {
							CellStatModel model = list.get(_date);
							model.setTotalStream(rs.getDouble("ALLVOLUME"));
							model.setTotalUser(rs.getInt("USERCOUNT"));
							model.setCellid(rs.getString("cellid"));
							model.setLac(rs.getString("lac"));
							model.setStattime(rs.getLong("stattime"));
						}
					}
				}
				return null;
			}
		});
		List _list = new ArrayList();
		Iterator iterator = list.values().iterator();
		while (iterator.hasNext()) {
			_list.add(iterator.next());
		}
		java.util.Collections.sort(_list, new CellStatComparator());
		return _list;
	}
}