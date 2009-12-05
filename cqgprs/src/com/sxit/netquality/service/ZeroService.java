/**
 * 
 */
package com.sxit.netquality.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.netquality.models.ZeroApn;
import com.sxit.netquality.models.ZeroCell;

/**
 * @author 华锋 Nov 7, 2009-10:44:26 AM
 * 
 */
public class ZeroService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	// private static final DateFormat dfhour = new
	// java.text.SimpleDateFormat("yyyyMMdd HH:00:00");
	// private static final DateFormat dfsec = new
	// java.text.SimpleDateFormat("yyyy--MM-dd HH:mm:ss");
	//
	// private int getDfSec(String date) {
	// try {
	// Date d = dfsec.parse(date);
	// return (int) (d.getTime() / 1000);
	// } catch (Exception e) {
	// return (int) (System.currentTimeMillis() / 1000);
	// }
	// }

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getZeroCells(Date date) {
		String sql = "select count(*) as cnt from zero_cellid where stattime=" + df.format(date);
		return jdbcTemplate.queryForInt(sql);
	}

	public int getZeroApns(Date date) {
		String sql = "select count(*) as cnt from zero_apn where stattime=" + df.format(date);
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到某一天的流量为0的cell
	 * 
	 * @param apnid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getDayZeroCells(final Date date, String orderby, int pageNo, int pageSize) {

		String _date = df.format(date);

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_cellid where dayflag=1 and stattime=" + _date;
		String sql = "select * from(select a.*,rownum rn from(select * from	 zero_cellid where dayflag=1 and stattime="
				+ _date + orderby + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		int totalCount = jdbcTemplate.queryForInt(countsql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ZeroCell model = new ZeroCell();
					model.setCellid(rs.getString("cellid"));
					model.setLac(rs.getString("lac"));
					model.setAllvolume(rs.getFloat("allvolume"));
					model.setHistoryvolume(rs.getFloat("Historyvolume"));
					model.setPrehistoryvolume(rs.getFloat("Prehistoryvolume"));
					model.setStattime(date.getTime() / 1000);
					model.setDayflag(1);
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;

		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;

	}

	/**
	 * 得到某天流量为0的apn
	 * 
	 * @param date
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getDayZeroApns(final Date date, String orderby, int pageNo, int pageSize) {

		String _date = df.format(date);
		if (orderby == null || orderby.equals(""))
			orderby = " order by orderby";

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_apn where dayflag=1 and stattime=" + _date;
		String sql = "select * from(select a.*,rownum rn from(select zero_apn.* from zero_apn,set_apn where zero_apn.apnni=set_apn.apnni and dayflag=1 and stattime="
				+ _date + orderby + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		int totalCount = jdbcTemplate.queryForInt(countsql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ZeroApn model = new ZeroApn();
					model.setApnni(rs.getString("apnni"));
					model.setAllvolume(rs.getFloat("allvolume"));
					model.setHistoryvolume(rs.getFloat("Historyvolume"));
					model.setPrehistoryvolume(rs.getFloat("Prehistoryvolume"));
					model.setStattime(date.getTime() / 1000);
					model.setDayflag(1);
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;

		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;

	}

	// /**
	// * 得到某一天某个小时的流量为0的cell
	// *
	// * @param apnid
	// * @param pageNo
	// * @param pageSize
	// * @return
	// */
	// public PaginationSupport getHourZeroCells(final String date, String hour,
	// String orderby, int pageNo, int pageSize) {
	//
	// String start = date + " " + hour + ":00:00";
	// String end = date + " " + hour + ":59:59";
	// long _start = com.sxit.stat.util.StatUtil.getDateHourTime(start) / 1000;
	// long _end = com.sxit.stat.util.StatUtil.getDateHourTime(end) / 1000;
	//
	// // int _start = getDfSec(start);
	// // int _end = getDfSec(end);
	//
	// int startIndex = (pageNo - 1) * pageSize;
	// String countsql = "select count(*) from zero_cell where dayflag=0 and
	// stattime>=" + _start + " and stattime<="
	// + _end;
	// String sql = "select * from(select a.*,rownum rn from(select * from
	// zero_cell where dayflag=0 and stattime>="
	// + _start + " and stattime<=" + _end + orderby + ") a where rownum<=" +
	// (startIndex + pageSize)
	// + ") where rn>" + startIndex;
	//
	// int totalCount = jdbcTemplate.queryForInt(countsql);
	//
	// Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
	// public Object extractData(ResultSet rs) throws SQLException,
	// DataAccessException {
	// List list = new ArrayList();
	// while (rs.next()) {
	// ZeroCell model = new ZeroCell();
	// model.setCellid(rs.getString("cellid"));
	// model.setLac(rs.getString("lac"));
	//
	// model.setAllvolume(rs.getFloat("allvolume"));
	// model.setHistoryvolume(rs.getFloat("Historyvolume"));
	// model.setPrehistoryvolume(rs.getFloat("Prehistoryvolume"));
	// model.setStattime(rs.getInt("stattime"));
	// model.setDayflag(1);
	// list.add(model);
	// }
	// return list;
	// }
	// });
	// List list = (List) object;
	//
	// PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize,
	// startIndex);
	//		return ps;
	//	}

	/**
	 * 得到某一天某个小时的流量为0的apn
	 * 
	 * @param apnid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getHourZeroApns(final String date, String orderby, int starthour, int endhour) {

		String start = date + " " + starthour + ":00:00";
		String end = date + " " + endhour + ":00:00";
		if (orderby == null || orderby.equals(""))
			orderby = " order by orderby";
		// int _start = getDfSec(start);
		// int _end = getDfSec(end);

		Date thedate = com.sxit.stat.util.StatUtil.getDate(date);

		String date1 = df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, 1));
		String date2 = df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, 2));

		int _start = (int) (com.sxit.stat.util.StatUtil.getDateHourTime(start) / 1000);
		int _endstart = (int) (com.sxit.stat.util.StatUtil.getDateHourTime(end) / 1000);
		long _end = com.sxit.stat.util.StatUtil.getHourAfterTime(_endstart);

		int count = endhour - starthour + 1;
		// int startIndex = (pageNo - 1) * pageSize;
		// String countsql = "select count(*) from(select count(apnni) from
		// stat_apn_zero where dayflag=0 and stattime>="
		// + _start + " and stattime<=" + _end + " group by apnni having
		// count(apnni)>=" + count + ")";

		// System.out.println(countsql);
		String sql = "select stat_apn_zero.apnni,sum(allvolume) as allvolume from stat_apn_zero ,set_apn where stat_apn_zero.apnni=set_apn.apnni  and dayflag=0 and stattime>="
				+ _start
				+ " and stattime<="
				+ _end
				+ "group by stat_apn_zero.apnni,set_apn.orderby having count(stat_apn_zero.apnni)>=" + count + orderby;
		System.out.println(sql);
		// int totalCount = jdbcTemplate.queryForInt(countsql);

		final Map<String, ZeroApn> zeroapnlist = new HashMap<String, ZeroApn>();
		final List<String> apnlist = new ArrayList<String>();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					ZeroApn model = new ZeroApn();
					model.setApnni(rs.getString("apnni"));
					if (com.sxit.netquality.service.BasicSetService.ALL_APNS.containsKey(model.getApnni()))
						model.setAllvolume((float) com.sxit.netquality.service.BasicSetService.ALL_APNS.get(
								model.getApnni()).getAllvolume());

					if (model.getAllvolume() > 0) { // 总流量大于0的才显示

						// model.set(rs.getFloat("allvolume"));
						// model.setHistoryvolume(rs.getFloat("Historyvolume"));
						// model.setPrehistoryvolume(rs.getFloat("Prehistoryvolume"));
						// model.setStattime(rs.getInt("stattime"));
						model.setDayflag(0);
						// list.add(model);
						zeroapnlist.put(model.getApnni(), model);
						apnlist.add(model.getApnni());
					}
				}
				return null;
			}
		});

		getDateApnVolume(date1, apnlist, zeroapnlist, 1);
		getDateApnVolume(date2, apnlist, zeroapnlist, 2);

		List list = new ArrayList();
		for (String apnni : apnlist) {
			list.add(zeroapnlist.get(apnni));
		}
		 zeroapnlist.clear();
		 apnlist.clear();
		return list;

	}

	/**
	 * 得到apn某个时间段的数据情况
	 * 
	 * @param apnni
	 * @param start
	 * @param end
	 * @param dayflag
	 * @param apnnilist
	 */
	private void getDateApnVolume(String stattime, List apnnilist, final Map zeroapnlist, final int which) {

		String apnnis = com.sxit.stat.util.StatUtil.list2str(apnnilist);
		String sql = "select apnni,sum(allvolume) as allvolume from stat_apn where dayflag=1 and apnni in(" + apnnis
				+ ") and stattime=" + stattime + "  group by apnni";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {

					String apnni = rs.getString("apnni");
					float allvolume = rs.getFloat("allvolume");
					ZeroApn apn = (ZeroApn) zeroapnlist.get(apnni);
					if (apn != null) {
						if (which == 1)
							apn.setHistoryvolume(allvolume);
						else
							apn.setPrehistoryvolume(allvolume);
					}

				}
				return null;
			}
		});
	}

	/**
	 * 得到某一天某个小时的流量为0的apn
	 * 
	 * @param apnid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getHourZeroCells(final String date, String orderby, int starthour, int endhour) {

		String start = date + " " + starthour + ":00:00";
		String end = date + " " + endhour + ":00:00";

		Date thedate = com.sxit.stat.util.StatUtil.getDate(date);

		String date1 = df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, 1));
		String date2 = df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate, 2));

		int _start = (int) (com.sxit.stat.util.StatUtil.getDateHourTime(start) / 1000);
		int _endstart = (int) (com.sxit.stat.util.StatUtil.getDateHourTime(end) / 1000);
		long _end = com.sxit.stat.util.StatUtil.getHourAfterTime(_endstart);

		int count = endhour - starthour + 1;
		// int startIndex = (pageNo - 1) * pageSize;
		// String countsql = "select count(*) from(select count(apnni) from
		// stat_apn_zero where dayflag=0 and stattime>="
		// + _start + " and stattime<=" + _end + " group by apnni having
		// count(apnni)>=" + count + ")";

		// System.out.println(countsql);
		String sql = "select cellid,lac,sum(allvolume) as allvolume from stat_cellid_zero where dayflag=0 and stattime>="
				+ _start + " and stattime<=" + _end + "group by cellid,lac having count(*)>=" + count + orderby;
		System.out.println(sql);
		// int totalCount = jdbcTemplate.queryForInt(countsql);

		final Map<String, ZeroCell> zerocelllist = new HashMap<String, ZeroCell>();
		final List<String> cellidlist = new ArrayList<String>();
		final List<String> cellkeylist = new ArrayList<String>();
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				while (rs.next()) {
					ZeroCell model = new ZeroCell();

					model.setCellid(rs.getString("cellid"));
					model.setLac(rs.getString("lac"));
					if (com.sxit.netquality.service.BasicSetService.ALL_CELLS.containsKey(model.getCellkey()))
						model.setAllvolume((float) com.sxit.netquality.service.BasicSetService.ALL_APNS.get(
								model.getCellkey()).getAllvolume());

					if (model.getAllvolume() > 0) { // 总流量大于0的才显示
						model.setDayflag(0);
						zerocelllist.put(model.getCellkey(), model);
						cellidlist.add(model.getCellid());
						cellkeylist.add(model.getCellkey());
					}
				}
				return null;
			}
		});

		getDateApnVolume(date1, cellidlist, zerocelllist, 1);
		getDateApnVolume(date2, cellidlist, zerocelllist, 2);

		List list = new ArrayList();
		for (String cellkey : cellkeylist) {
			list.add(zerocelllist.get(cellkey));
		}
		zerocelllist.clear();
		 cellidlist.clear();
		 zerocelllist.clear();
		return list;


	}

	/**
	 * 得到apn某个时间段的数据情况
	 * 
	 * @param apnni
	 * @param start
	 * @param end
	 * @param dayflag
	 * @param apnnilist
	 */
	private void getDateCellVolume(String stattime, List cellidlist, final Map zerocelllist, final int which) {

		String cellids = com.sxit.stat.util.StatUtil.list2str(cellidlist);
		String sql = "select cellid,lac,sum(allvolume) as allvolume from stat_cellid_day where dayflag=1 and cellid in("
				+ cellids + ") and stattime=" + stattime + "  group by apnni";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {

					String cellid = rs.getString("cellid");
					String lac = rs.getString("lac");
					float allvolume = rs.getFloat("allvolume");
					String cellkey = null;
					if (lac == null || lac.equals(""))
						cellkey = cellid;
					else
						cellkey = lac + "-" + cellid;

					ZeroCell cell = (ZeroCell) zerocelllist.get(cellkey);
					if (cell != null) {
						if (which == 1)
							cell.setHistoryvolume(allvolume);
						else
							cell.setPrehistoryvolume(allvolume);
					}

				}
				return null;
			}
		});
	}
}
