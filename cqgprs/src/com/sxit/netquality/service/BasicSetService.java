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
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

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

	private JdbcTemplate jdbcTemplate;

	private long getDayStartTime(Date date){
		try{
		String datestr=df.format(date);
		String startstr=datestr+" 00:00:00";
		Date start=dfyyyyMmddHHmmss.parse(startstr);
		return start.getTime();
		}catch(Exception e){
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
		
		long start=getDayStartTime(date);
		long end=start+24*60*60*1000L;
		
		

		String sql = "select count(*) from set_cell where updatetime between "+start/1000+" and "+end/1000+" and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的apn个数
	 * 
	 * @return
	 */
	public int getTodayAddApn() {
		Date date = this.getPrevDate();
		
		long start=getDayStartTime(date);
		long end=start+24*60*60*1000L;
		String sql = "select count(*) from set_apn where updatetime between "+start/1000+" and "+end/1000+" and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的bsc个数
	 * 
	 * @return
	 */
	public int getTodayAddBsc() {
		Date date = this.getPrevDate();
		
		long start=getDayStartTime(date);
		long end=start+24*60*60*1000L;
		String sql = "select count(*) from set_bsc where updatetime between "+start/1000+" and "+end/1000+" and opttype=0";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 得到前1天新增的link个数
	 * 
	 * @return
	 */
	public int getTodayAddLink() {
		Date date = this.getPrevDate();
		
		long start=getDayStartTime(date);
		long end=start+24*60*60*1000L;
		String sql = "select count(*) from set_bsc where updatetime between "+start/1000+" and "+end/1000+" and opttype=0";
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

	public PaginationSupport getCells(int pageNo, int pageSize) {

		String countsql = "select count(*) from set_cell";
		int totalCount = jdbcTemplate.queryForInt(countsql);
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select * from  set_cell where opttype!=2 order by CELLID) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>=" + startIndex;

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					Cell model = new Cell();
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					model.setCellname(rs.getString("CELLNAME"));
					model.setLastopt(rs.getString(""));
					Date date = new Date();
					date.setTime(rs.getInt("UPDATETIME") * 1000);
					model.setLastupdate(date);
					model.setLastopt(rs.getString(""));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	public PaginationSupport getApns(int pageNo, int pageSize) {

		String countsql = "select count(*) from set_apn";
		int totalCount = jdbcTemplate.queryForInt(countsql);
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select * from  set_apn where opttype!=2 order by APNNI) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>=" + startIndex;

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					Apn model = new Apn();
					model.setApnid(rs.getString("APNNI"));
					model.setApnname(rs.getString("APNNAME"));
					model.setUsercorp(rs.getString(""));
					model.setUserphone(rs.getString(""));
					model.setLastopt(rs.getString(""));
					Date date = new Date();
					date.setTime(rs.getInt("UPDATETIME") * 1000);
					model.setLastupdate(date);
					model.setLastopt(rs.getString(""));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}
/**
 * 
 * @return
 */
	public List getBsces() {

		String sql = "select * from  SET_BSC  where opttype!=2  order by BSCID";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					BscRnc model = new BscRnc();
					model.setBscrncid(rs.getString("BSCID"));
					model.setName(rs.getString("BSCNAME"));
					model.setNettype(rs.getString("NETTYPE"));
					model.setSgsnid(rs.getString("SGSNID"));
					model.setLastopt(rs.getString(""));
					Date date = new Date();
					date.setTime(rs.getInt("UPDATETIME") * 1000);
					model.setLastupdate(date);
					model.setLastopt(rs.getString(""));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}
	/**
	 * 
	 * @return
	 */
	public List getSgsns() {

		String sql = "select * from  SET_SGSN order by sgsnid";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					Sgsn model = new Sgsn();
				
					model.setSgsnid(rs.getString("SGSNID"));
					model.setSgsnarea(rs.getString("sgsnarea"));
					model.setLastopt(rs.getString(""));
					Date date = new Date();
					date.setTime(rs.getInt("UPDATETIME") * 1000);
					model.setLastupdate(date);
					model.setLastopt(rs.getString(""));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		return list;
	}
}
