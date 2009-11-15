/**
 * 
 */
package com.sxit.netquality.service;

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
import com.sxit.netquality.models.ZeroApn;
import com.sxit.netquality.models.ZeroCell;

/**
 * @author 华锋 Nov 7, 2009-10:44:26 AM
 * 
 */
public class ZeroService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyyMM-dd HH:mm:ss");

	private int getDfSec(String date) {
		try {
			Date d = dfsec.parse(date);
			return (int) (d.getTime() / 1000);
		} catch (Exception e) {
			return (int) (System.currentTimeMillis() / 1000);
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

	public int getZeroCells(Date date){
		String sql="select count(*) as cnt from zero_cellid where stattime="+df.format(date);
		return jdbcTemplate.queryForInt(sql);
	}
	public int getZeroApns(Date date){
		String sql="select count(*) as cnt from zero_apn where stattime="+df.format(date);
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
	public PaginationSupport getDayZeroCells(final Date date, int pageNo, int pageSize) {

		String _date = df.format(date);

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_cellid where dayflag=1 and stattime=" + _date;
		String sql = "select * from(select a.*,rownum rn from(select * from	 zero_cellid where dayflag=1 and stattime="
				+ _date + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

		int totalCount = jdbcTemplate.queryForInt(countsql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ZeroCell model = new ZeroCell();
					model.setCellid(rs.getString("cellid"));
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
	public PaginationSupport getDayZeroApns(final Date date, int pageNo, int pageSize) {

		String _date = df.format(date);

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_apn where dayflag=1 and stattime=" + _date;
		String sql = "select * from(select a.*,rownum rn from(select * from	 zero_apn where dayflag=1 and stattime="
				+ _date + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>" + startIndex;

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

	/**
	 * 得到某一天某个小时的流量为0的cell
	 * 
	 * @param apnid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getHourZeroCells(final String date, String hour, int pageNo, int pageSize) {

		String start = date + " " + hour + ":00:00";
		String end = date + " " + hour + ":59:59";

		int _start = getDfSec(start);
		int _end = getDfSec(end);

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_cell where dayflag=0 and stattime>=" + _start + " and stattime<="
				+ _end;
		String sql = "select * from(select a.*,rownum rn from(select * from	 zero_cell where dayflag=0 and stattime>="
				+ _start + " and stattime<=" + _end + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>"
				+ startIndex;

		int totalCount = jdbcTemplate.queryForInt(countsql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ZeroCell model = new ZeroCell();
					model.setCellid(rs.getString("cellid"));
					model.setAllvolume(rs.getFloat("allvolume"));
					model.setHistoryvolume(rs.getFloat("Historyvolume"));
					model.setPrehistoryvolume(rs.getFloat("Prehistoryvolume"));
					model.setStattime(rs.getInt("stattime"));
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
	 * 得到某一天某个小时的流量为0的cell
	 * 
	 * @param apnid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getHourZeroApns(final String date, String hour, int pageNo, int pageSize) {

		String start = date + " " + hour + ":00:00";
		String end = date + " " + hour + ":59:59";

		int _start = getDfSec(start);
		int _end = getDfSec(end);

		int startIndex = (pageNo - 1) * pageSize;
		String countsql = "select count(*) from zero_apn where dayflag=0 and stattime>=" + _start + " and stattime<="
				+ _end;
		String sql = "select * from(select a.*,rownum rn from(select * from	 zero_apn where dayflag=0 and stattime>="
				+ _start + " and stattime<=" + _end + ") a where rownum<=" + (startIndex + pageSize) + ") where rn>"
				+ startIndex;

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
					model.setStattime(rs.getInt("stattime"));
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
}
