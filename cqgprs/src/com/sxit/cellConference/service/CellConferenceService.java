/**
 * 
 */
package com.sxit.cellConference.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	private JdbcTemplate jdbcTemplate;
	public static Map<Integer, String> TIMELIST;

	static {
		TIMELIST=new LinkedHashMap<Integer,String>();
		for (int i = 0; i <= 23; i++) {
			TIMELIST.put(i, i + ":00-" + (i + 1) + ":00");
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
	public PaginationSupport getDayConferenceList(String date, int pageNo, int pageSize) {

		long start = com.sxit.stat.util.StatUtil.getDateTime(date);
		long end = com.sxit.stat.util.StatUtil.getOneDayAfter(start);

		String countsql = "select count(*) from alarm_cellid where stattime between " + start / 1000 + " and " + end
				/ 1000;
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;

		sql = "select * from(select a.*,rownum rn from(select * from alarm_cellid where stattime between " + start
				/ 1000 + " and " + end / 1000 + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>"
				+ startIndex;

		System.out.println(countsql);
		System.out.println(sql);
		int totalCount = jdbcTemplate.queryForInt(countsql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					AlarmCell cell = new AlarmCell();
					cell.setCellid(rs.getString("cellid"));
					cell.setLac(rs.getString("lac"));
					cell.setStattime(rs.getLong("stattime"));
					cell.setOldpreallvolume(rs.getDouble("oldpreallvolume"));
					cell.setOldpreusercount(rs.getInt("oldpreusercount"));
					cell.setPreallvolume(rs.getDouble("preallvolume"));
					cell.setPreusercount(rs.getInt("preusercount"));
					cell.setUsercount(rs.getInt("usercount"));
					cell.setAllvolume(rs.getDouble("allvolume"));
					list.add(cell);
				}
				return list;
			}
		});
		List list = (List) object;
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
	public void saveConferenceCell(String cellid, String lac, int usercount, int flow, int timespam, SysUser loginuser) {

		long now = System.currentTimeMillis() / 1000;

		String sql = "insert into SET_CELL_CONFERENCE(CELLID,lac,UPDATETIME,ISACTIVE,createuserid,createusername,timeview,flowalarmvalue,useralarmvalue) values('"
				+ cellid
				+ "','"
				+ lac
				+ "',"
				+ now
				+ ",1,"
				+ loginuser.getUserid()
				+ ",'"
				+ loginuser.getUsername()
				+ "'," + timespam + "," + flow + "," + usercount + ")";

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
		int totalCount = jdbcTemplate.queryForInt(countsql);
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
					cell.setTimeview(rs.getInt("timeview"));
					cell.setFlowalarmvalue(rs.getInt("flowalarmvalue"));
					cell.setUseralarmvalue(rs.getInt("useralarmvalue"));
					list.add(cell);
				}
				return list;
			}
		});
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
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

	/**
	 * 更新
	 * 
	 * @param cellid
	 * @param lac
	 * @param userview
	 * @param flowview
	 * @param timeview
	 */
	public void updateConferenceCell(String cellid, String lac, int userview, int flowview, int timeview) {
		String sql = "update SET_CELL_CONFERENCE set flowalarmvalue=" + flowview + ",useralarmvalue=" + userview
				+ ",timeview=" + timeview + ", where CELLID ='" + cellid + "' and lac='" + lac + "'";
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

		int nowhour = (int) (StatUtil.getDateHourTime(date + " " + hour + ":00:00") / 1000);
		int nowhourend = com.sxit.stat.util.StatUtil.getHourAfterTime(nowhour);

		final List list = new ArrayList();
		final String stattime = date + " " + hour + ":00:00";
		for (int i = days; i >= 0; i--) {

			int start = nowhour - i * 24 * 60 * 60;
			int end = nowhourend - i * 24 * 60 * 60;

			// String sql = "select cellid,lac,sum(allvolume) as
			// allvolume,sum(upvolume) as upvolume,sum(downvolume) as
			// downvolume,sum(usercount) as usercount from stat_cellid where
			// stattime between "
			// + start + " and " + end+" group by cellid,lac";

			String sql = "select cellid,lac,allvolume,upvolume,downvolume,usercount from stat_cellid where stattime between "
					+ start + " and " + end;
			// System.out.println(sql);
			jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
					// List list = new ArrayList();
					if (rs.next()) {
						CellStatModel model = new CellStatModel();
						model.setTotalStream(rs.getDouble("ALLVOLUME"));
						model.setTotalUser(rs.getInt("USERCOUNT"));
						model.setCellid(rs.getString("cellid"));
						model.setLac(rs.getString("lac"));
						model.setDate(date);
						model.setDatetime(stattime);
						model.setDownvolume(rs.getDouble("downvolume"));
						model.setUpvolume(rs.getDouble("upvolume"));
						list.add(model);
					}
					return null;
				}
			});
		}
		return list;
	}
}