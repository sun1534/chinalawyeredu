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
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.stat.models.ApnCellStatModel;
import com.sxit.stat.models.ApnStatModel;
import com.sxit.stat.models.BsnRncStatModel;
import com.sxit.stat.models.CellDayStat;
import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.models.TotalModel;

/**
 * @author 华锋 Oct 16, 2009-4:54:14 PM
 * 
 */
public class StatService {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("HH:mm");
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
	public List getDaysTotalStream(Date from, Date to) {
		String sql = "select sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME,STATTIME  from  STAT_SGSN where STATTIME between ? and ? and dayflag=1 group by STATTIME";
		int _from = (int) (from.getTime() / 1000);
		int _to = (int) to.getTime() / 1000;
		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalModel model = new TotalModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
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
	public List getDaysTotalStream23g(Date from, Date to) {
		String sql = "select USERCOUNT,ALLVOLUME,NETTYPE,STATTIME  from  STAT_SGSN where STATTIME between ? and ? and dayflag=1 order by STATTIME desc,NETTYPE asc";
		int _from = (int) (from.getTime() / 1000);
		int _to = (int) to.getTime() / 1000;
		Object[] args = new Object[] { _from, _to };
		int[] argTypes = new int[] { Types.INTEGER, Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TotalModel model = new TotalModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String type = rs.getString("NETTYPE");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
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
		String sql = "select SGSNID,sum(USERCOUNT) as USERCOUNT,sum(ALLVOLUME) as ALLVOLUME,STATTIME  from  STAT_SGSN where STATTIME=? and dayflag=1 group by SGSNID,STATTIME";
		int _date = (int) (date.getTime() / 1000);

		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
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
		String sql = "select SGSNID,USERCOUNT,ALLVOLUME,STATTIME,NETTYPE from  STAT_SGSN where STATTIME=? and dayflag=1 order by sgsnid,NETTYPE";
		int _date = (int) (date.getTime() / 1000);
		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SgsnStatModel model = new SgsnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
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
	public PaginationSupport getBscRncStat(Date date,int pageNo,int pageSize) {
		int _date = (int) (date.getTime() / 1000);
		String countsql="select count(*) as cnt from  STAT_BSC where STATTIME="+_date+" and dayflag=1 ";
		int totalCount=jdbcTemplate.queryForInt(countsql);
		int startIndex=(pageNo-1)*pageSize;
		
		String sql = "select * from(select a.*,rownum rn from(select BSCID,SGSNID,STATTIME,NETTYPE,USERCOUNT,ALLVOLUME from  STAT_BSC where STATTIME="+_date+" and dayflag=1 order by sgsnid,NETTYPE) a where rownum<="+(startIndex+pageSize)+") where rn>="+startIndex;
	
//		Object[] args = new Object[] { _date };
//		int[] argTypes = new int[] { Types.INTEGER };
//		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			Object object = jdbcTemplate.query(sql,  new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					BsnRncStatModel model = new BsnRncStatModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					String sgsnid = rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
					model.setSgsnid(sgsnid);
					model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
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
	 * 按天统计各cellid的流量情况,估计要分页(赞不处理分页)
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getCellDayStat(Date date, int pageNo, int pageSize) {
		int _date = (int) (date.getTime() / 1000);
		String countsql = "select count(*) from STAT_CELLID where STATTIME=" + _date + " and dayflag=1";
		int totalCount = jdbcTemplate.queryForInt(countsql);
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select * from(select a.*,rownum rn from(select CELLID,BSCID,NETTYPE,STATTIME,USERCOUNT,ALLVOLUME from  STAT_CELLID where STATTIME=? and dayflag=1 order by CELLID,NETTYPE) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>=" + startIndex;

		Object[] args = new Object[] { _date };
		int[] argTypes = new int[] { Types.INTEGER };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					CellDayStat model = new CellDayStat();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
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
	public List getCellDayTimeStat(Date date, String cellid) {
		String sql = "select CELLID,BSCID,NETTYPE,STATTIME,NETTYPE,USERCOUNT,ALLVOLUME from  STAT_CELLID where STATTIME=? and CELLID=?and dayflag=0 order by stattime";
		int _date = (int) (date.getTime() / 1000);
		Object[] args = new Object[] { _date, cellid };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					CellDayStat model = new CellDayStat();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
					// model.setSgsnid(sgsnid);
					model.setNettype(rs.getString("NETTYPE"));
					model.setBscrncid(rs.getString("BSCID"));
					model.setCellid(rs.getString("CELLID"));
					model.setDatetime(dftime.format(date));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
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
		int _date = (int) (date.getTime() / 1000);
		String cntsql = "select count(*) as cnt from STAT_APN where STATTIME=" + _date + " and dayflag=1";
		int totalCount = jdbcTemplate.queryForInt(cntsql);
		int startIndex = (pageNo - 1) * pageSize;
		// String sql = "select * from(select APNNI,STATTIME,USERCOUNT,ALLVOLUME
		// from STAT_APN where STATTIME="+_date+" and dayflag=1)";

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select APNNI,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN where STATTIME="
				+ _date
				+ " and dayflag=1) A WHERE ROWNUM <= "
				+ (startIndex + pageSize)
				+ " )  WHERE RN >= "
				+ startIndex;

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
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
					// model.setSgsnid(sgsnid);
					model.setDatetime(dftime.format(date));
					model.setApnid(rs.getString("APNNI"));
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
	 * 按时间段统计某ellid的流量情况
	 * 
	 * @param date
	 * @return
	 */
	public List getApnDayTimeStat(Date date, String apnid) {
		String sql = "select APNNI,STATTIME,USERCOUNT,ALLVOLUME from  STAT_APN where STATTIME=? and APNNI=? and dayflag=0 order by STATTIME";
		int _date = (int) (date.getTime() / 1000);
		Object[] args = new Object[] { _date, apnid };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnStatModel model = new ApnStatModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
					// model.setSgsnid(sgsnid);
					model.setDatetime(dftime.format(date));
					model.setApnid(rs.getString("APNNI"));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

	/**
	 * apn的分cell统计
	 * 
	 * @param date
	 * @param apnid
	 * @return
	 */
	public List getApnCellDayStat(Date date, String apnid) {
		String sql = "select APNNI,CELLID,STATTIME,USERCOUNT,ALLVOLUME from  STAT_CELLID_APN  where STATTIME=? and APNNI=? and dayflag=1";
		int _date = (int) (date.getTime() / 1000);
		Object[] args = new Object[] { _date, apnid };
		int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		Object object = jdbcTemplate.query(sql, args, argTypes, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					ApnCellStatModel model = new ApnCellStatModel();
					int usercount = rs.getInt("USERCOUNT");
					long all = rs.getLong("ALLVOLUME");
					int stattime = rs.getInt("STATTIME");
					// String sgsnid=rs.getString("SGSNID");
					Date date = new Date();
					date.setTime(stattime * 1000);
					model.setTotalStream(all);
					model.setTotalUser(usercount);
					model.setDate(df.format(date));
					model.setCellid(rs.getString("CELLID"));
					model.setApnid(rs.getString("APNNI"));
					// model.setSgsnid(sgsnid);
					model.setDatetime(dftime.format(date));
					list.add(model);
				}
				return list;
			}
		});
		return (List) object;
	}

}
