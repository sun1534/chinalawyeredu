/**
 * 
 */
package com.sxit.netquality.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

import com.sxit.common.PaginationSupport;
import com.sxit.netquality.models.Apn;
import com.sxit.netquality.models.BscRnc;
import com.sxit.netquality.models.Cell;
import com.sxit.netquality.models.Sgsn;

/**
 * 
 * 系统基本参数设置等
 * 
 * @author 华锋 Oct 21, 2009-10:29:32 PM
 * 
 */
public class BasicSetService {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Map<String, Cell> ALL_CELLS = new LinkedHashMap<String, Cell>();

	public static List<Cell> ALL_CELL_LIST = new ArrayList<Cell>();
	public static List<Apn> ALL_APN_LIST = new ArrayList<Apn>();

	public static Map<String, Apn> ALL_APNS = new LinkedHashMap<String, Apn>();
	public static Map<String, BscRnc> ALL_BSCS = new LinkedHashMap<String, BscRnc>();
	public static Map<String, Sgsn> ALL_SGSNS = new LinkedHashMap<String, Sgsn>();

	public static Map<String, String> CELL_BSC = new LinkedHashMap<String, String>();
	public static Map<String, String> BSC_SGSN = new LinkedHashMap<String, String>();

	private static long updatetime = 0L;

	private JdbcTemplate jdbcTemplate;

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

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 得到前1天新增的cell个数
	 * 
	 * @return
	 */
	public int getTotalCells() {

		String sql = "select count(*) from set_cell where opttype!=2";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的apn个数
	 * 
	 * @return
	 */
	public int getTotalApns() {
		String sql = "select count(*) from set_apn where opttype!=2";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的bsc个数
	 * 
	 * @return
	 */
	public int getTotalBscs() {
		String sql = "select count(*) from set_bsc where opttype!=2";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的link个数
	 * 
	 * @return
	 */
	public int getTotalLinks() {
		String sql = "select count(*) from set_bsc where opttype!=2";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的cell个数
	 * 
	 * @return
	 */
	public int getTodayAddCell() {
		Date date = this.getPrevDate();

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;

		String sql = "select count(*) from set_cell where updatetime between " + start / 1000 + " and " + end / 1000
				+ " and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的apn个数
	 * 
	 * @return
	 */
	public int getTodayAddApn() {
		Date date = this.getPrevDate();

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		String sql = "select count(*) from set_apn where updatetime between " + start / 1000 + " and " + end / 1000
				+ " and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的bsc个数
	 * 
	 * @return
	 */
	public int getTodayAddBsc() {
		Date date = this.getPrevDate();

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		String sql = "select count(*) from set_bsc where updatetime between " + start / 1000 + " and " + end / 1000
				+ " and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的link个数
	 * 
	 * @return
	 */
	public int getTodayAddLink() {
		Date date = this.getPrevDate();

		long start = getDayStartTime(date);
		long end = start + 24 * 60 * 60 * 1000L;
		String sql = "select count(*) from set_bsc where updatetime between " + start / 1000 + " and " + end / 1000
				+ " and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevDate() {
		return getPrevCountDate(1);
	}

	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0 - days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}

	public PaginationSupport getCells(String cellid, String bscid,int pageNo, int pageSize) {
		String countsql = "";
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;
		countsql = "select count(*) from set_cell where opttype!=2 ";
		sql = "select * from(select a.*,rownum rn from(select * from set_cell where opttype!=2 ${where} order by CELLID) a where rownum<=" + (startIndex + pageSize) + ") where rn>="
			+ startIndex;
		String where="";
		if (cellid != null && !cellid.equals("")) {
			where+=" and cellid='" + cellid + "'";
		} 
		if (bscid != null && !bscid.equals("")) {
			where+=" and bscid='" + bscid + "'";
		}
		countsql=countsql+where;
		sql=sql.replace("${where}", where);
		
		System.out.println(countsql);
		System.out.println(sql);
		int totalCount = jdbcTemplate.queryForInt(countsql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					Cell model = new Cell();
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					model.setCellname(rs.getString("CELLNAME"));
					model.setLastopt(rs.getString("OPTTYPE"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		// getAllCells();
		// int totalCount = ALL_CELL_LIST.size();
		// int startIndex = (pageNo - 1) * pageSize;
		// List<Cell> list = new ArrayList<Cell>();
		// for (int i = startIndex; i < totalCount && i < startIndex + pageSize;
		// i++) {
		// list.add(ALL_CELL_LIST.get(i));
		// }
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	private void getAllCells() {
		String sql = "select * from  set_cell where opttype!=2 order by CELLID";
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				ALL_CELLS.clear();
				CELL_BSC.clear();
				ALL_CELL_LIST.clear();
				while (rs.next()) {
					Cell model = new Cell();
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					model.setCellname(rs.getString("CELLNAME"));
					model.setLastopt(rs.getString("OPTTYPE"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);
					// list.add(model);
					ALL_CELL_LIST.add(model);
					ALL_CELLS.put(rs.getString("CELLID"), model);
					CELL_BSC.put(rs.getString("CELLID"), rs.getString("BSCID"));
				}
				return null;
			}
		});

	}

	private void getAllApns() {
		String sql = "select * from  set_apn where opttype!=2 order by APNNI";
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				// List list = new ArrayList();
				ALL_APNS.clear();
				ALL_APN_LIST.clear();
				while (rs.next()) {
					Apn model = new Apn();
					model.setApnid(rs.getString("APNNI"));
					model.setApnname(rs.getString("APNNAME"));
					model.setUsercorp(rs.getString("apnconector"));
					model.setUserphone(rs.getString("apnconector"));

					model.setLastopt(rs.getString("opttype"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);
					ALL_APN_LIST.add(model);
					ALL_APNS.put(rs.getString("APNNI"), model);
				}
				return null;
			}
		});

	}

	public List getBsces(String bscid, String sgsnid) {

		String sql = "select * from set_bsc where opttype!=2";
		if (bscid != null && !bscid.equals("")) {
			sql += " and bscid='" + bscid + "'";
		}
		if (sgsnid != null && !sgsnid.equals("")) {
			sql += " and sgsnid='" + sgsnid + "'";
		}
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					BscRnc model = new BscRnc();
					model.setBscrncid(rs.getString("bscid"));
					model.setLastopt(rs.getString("opttype"));

					model.setName(rs.getString("bscname"));
					// model.setNettype(rs.getString("nettype"));
					model.setSgsnid(rs.getString("sgsnid"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);

					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;

	}

	public PaginationSupport getApns(String apnid, int pageNo, int pageSize) {

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from set_apn where opttype!=2";
		String sql = "select * from(select a.*,rownum rn from(select * from	 set_apn where opttype!=2 order by APNNI) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>=" + startIndex;
		if (!(apnid == null || apnid.equals(""))) {

			countsql = "select count(*) from set_apn where opttype!=2 and	 apnni='" + apnid + "'";
			sql = "select * from(select a.*,rownum rn from(select * from set_apn where opttype!=2 and apnni='" + apnid
					+ "' order by APNNI) a where rownum<=" + (startIndex + pageSize) + ")	 where rn>=" + startIndex;
		}
		int totalCount = jdbcTemplate.queryForInt(countsql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					Apn model = new Apn();
					model.setApnid(rs.getString("APNNI"));
					model.setApnname(rs.getString("APNNAME"));
					model.setUsercorp(rs.getString("apnconector"));
					model.setUserphone(rs.getString("apnconector"));
					model.setLastopt(rs.getString("opttype"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);

					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
		// getAllApns();
		// int totalCount = ALL_APN_LIST.size();
		// int startIndex = (pageNo - 1) * pageSize;
		// List<Apn> list = new ArrayList<Apn>();
		// for (int i = startIndex; i < totalCount && i < startIndex + pageSize;
		// i++) {
		// list.add(ALL_APN_LIST.get(i));
		// }
		// // PaginationSupport ps=new PaginationSupport();
		// PaginationSupport ps = new PaginationSupport(list, totalCount,
		// pageSize, startIndex);
		// return ps;
	}

	/**
	 * 
	 * @return
	 */
	private void getBsces() {

		String sql = "select * from  SET_BSC  where opttype!=2  order by BSCID";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				ALL_BSCS.clear();
				BSC_SGSN.clear();
				while (rs.next()) {
					BscRnc model = new BscRnc();
					model.setBscrncid(rs.getString("BSCID"));
					model.setName(rs.getString("BSCNAME"));
					// model.setNettype(rs.getString("NETTYPE"));
					model.setSgsnid(rs.getString("SGSNID"));
					model.setLastopt(rs.getString("OPTTYPE"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);

					ALL_BSCS.put(rs.getString("BSCID"), model);
					BSC_SGSN.put(rs.getString("BSCID"), rs.getString("SGSNID"));
				}
				return null;
			}
		});

	}

	/**
	 * 
	 * @return
	 */
	public List getSgsns() {

		String sql = "select * from  SET_SGSN where opttype!=2 order by sgsnid";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
List list=new ArrayList();
				ALL_SGSNS.clear();
				while (rs.next()) {
					Sgsn model = new Sgsn();
					model.setSgsnid(rs.getString("SGSNID"));
					// model.setSgsnarea(rs.getString("sgsnarea"));
					model.setLastopt(rs.getString("OPTTYPE"));
					Date date = new Date();
					date.setTime(rs.getLong("UPDATETIME") * 1000);
					model.setLastupdate(date);
					list.add(model);
					ALL_SGSNS.put(rs.getString("SGSNID"), model);
				}
				return list;
			}
		});
		return (List)object;
	}

	/**
	 * 得到重点小区
	 * 
	 * @return
	 */
	public List getFocusCellids() {
		String sql = "select CELLID from  SET_CELL_FOCUS ";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					list.add(rs.getString("CELLID"));
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	/**
	 * 设置重点小区
	 * 
	 * @param isset
	 */
	@Transactional
	public void setFocusCell(String all, String selected) {
		StringTokenizer st = new StringTokenizer(all, ",");
		String sql = "";
		while (st.hasMoreTokens()) {
			String cellid = st.nextToken();
			sql = "delete from SET_CELL_FOCUS where CELLID ='" + cellid + "'";
			jdbcTemplate.execute(sql);
		}

		st = new StringTokenizer(selected, ",");
		long now = System.currentTimeMillis() / 1000;
		while (st.hasMoreTokens()) {
			String cellid = st.nextToken();
			sql = "insert into SET_CELL_FOCUS(CELLID,CELLNAME,UPDATETIME,ISACTIVE) values('" + cellid + "',null," + now
					+ ",1)";

			jdbcTemplate.execute(sql);
		}
	}

	/**
	 * 设置重点apn
	 * 
	 * @param isset
	 */
	public void setFocusApn(String all, String selected) {
		StringTokenizer st = new StringTokenizer(all, ",");
		String sql = "";
		while (st.hasMoreTokens()) {
			String apnni = st.nextToken();
			sql = "delete from SET_APN_FOCUS where APNNI ='" + apnni + "'";
			jdbcTemplate.execute(sql);
		}

		st = new StringTokenizer(selected, ",");
		long now = System.currentTimeMillis() / 1000;
		while (st.hasMoreTokens()) {
			String apnni = st.nextToken();
			sql = "insert into SET_APN_FOCUS(APNNI,APNNAME,UPDATETIME,ISACTIVE) values('" + apnni + "',null," + now
					+ ",1)";

			jdbcTemplate.execute(sql);
		}

	}

	/**
	 * 设置需要跟踪的号码
	 * 
	 * @param mobile
	 * @param isset
	 */
	public void setFocusMobile(String mobile, boolean isset) {
		String sql = "";
		if (!isset) {
			sql = "delete from SET_MOBILE_FOCUS where mobile='" + mobile + "'";
		} else {
			sql = "insert into SET_MOBILE_FOCUS(mobile) values('" + mobile + "')";
		}
		jdbcTemplate.execute(sql);

	}

	/**
	 * 得到重点apn编号,和那个普通的进行匹配，如果是重点小区的话，就打勾
	 * 
	 * @return
	 */
	public List getFocusApns() {
		String sql = "select APNNI from  SET_APN_FOCUS ";
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					list.add(rs.getString("APNNI"));
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}

	public void getAllSets() {
		long now = System.currentTimeMillis();

		if (now - updatetime > 12 * 60 * 60 * 1000) {
			_LOG.info("从数据库里面取的数据");
			getSgsns();
			getBsces();
			getAllCells();
			getAllApns();

			updatetime = now;
		} else {
			_LOG.info("从缓存里面取的数据");
		}
	}

	private static Log _LOG = LogFactory.getLog(BasicSetService.class);

}