/**
 * 
 */
package com.sxit.netquality.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.netquality.models.HWChrFlowidErrorStat;
import com.sxit.netquality.models.HWChrFlowidStat;
import com.sxit.netquality.models.HWChrLog;

/**
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class ChrQueryService {
	private static Log _LOG = LogFactory.getLog(ChrQueryService.class);
	// 所有的流程id和流程解释
	public static Map<String, String> ALL_FLOWID = new HashMap<String, String>();
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 根据当前日期，得到是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getChrTable(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String table = "hwchr_log_" + weekday;
		return table;
	}
/**
 * 得到所有的流程id的数据字典
 */
	public void getFlowids() {
		String sql = "select * from hwchr_flowid";
		ALL_FLOWID.clear();
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					ALL_FLOWID.put(rs.getString("idname"), rs.getString("iddescription"));
				}
				return null;
			}
		});
		_LOG.info("所有流程ID数据初始化完毕");
	}
	
	
	
	
/**
 * 查询chr的日志详情
 * @param date
 * @param msisdn
 * @param imei
 * @param flowid
 * @param _cellid
 * @param _lac
 * @param pageNo
 * @param pageSize
 * @return
 */
	public PaginationSupport queryChr(Date date, String msisdn, String imei, String flowid, String _cellid,
			String _lac, int pageNo, int pageSize) {

		String table = getChrTable(date);
		int totalCount = 0;

		String cellid = Integer.toHexString(Integer.parseInt(_cellid)).toUpperCase();
		String lac = Integer.toHexString(Integer.parseInt(_lac)).toUpperCase();

		int startIndex = (pageNo - 1) * pageSize;

		String cntsql = "select count(*) as cnt from " + table + " where 1=1 ";
		String search = " where 1=1";
		if (msisdn != null && !msisdn.equals("")) {
			cntsql += " and msisdn = '" + msisdn + "'";
			search += " and msisdn = '" + msisdn + "'";
		}
		if (imei != null && !imei.equals("")) {
			cntsql += " and imei='" + cellid + "'";
			search += " and imei='" + cellid + "'";
		}
		if (flowid != null && !flowid.equals("")) {
			cntsql += " and flowid='" + flowid + "'";
			search += " and flowid='" + flowid + "'";
		}

		if (lac != null && !lac.equals("") && !lac.equals("0")) {
			cntsql += " and cellid like '%" + lac + ":%'";
			search += " and cellid like '%" + lac + ":%'";
		}
		if (cellid != null && !cellid.equals("") && !cellid.equals("0")) {
			cntsql += " and cellid like '%:" + cellid + "%'";
			search += " and cellid like '%:" + cellid + "%'";
		}
		System.out.println("查询SQL:" + cntsql);
		if (pageSize != Integer.MAX_VALUE) {
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from  " + table + search
				+ " ) A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN > " + startIndex;

		System.out.println("查询SQL:" + sql);

		// String sql = "select * from(select APNNI,STATTIME,USERCOUNT,ALLVOLUME
		// from STAT_APN where STATTIME="+_date+" and dayflag=1)";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					HWChrLog model = new HWChrLog();
					model.setTime(rs.getLong("ttime"));
					model.setAllowapn(rs.getString("allowapn"));
					model.setApn(rs.getString("apn"));
					model.setCao(rs.getString("cao"));
					model.setCellid(rs.getString("cellid"));
					model.setFilesgsn(rs.getString("filesgsn"));
					model.setFlowid(rs.getString("flowid"));
					model.setGgsn(rs.getString("ggsn"));
					model.setImei(rs.getString("imei"));
					model.setImsi(rs.getString("imsi"));
					model.setInnerreason(rs.getString("innerreason"));
					model.setKuang(rs.getString("kuang"));
					model.setLazytime(rs.getString("lazytime"));
					// model.setMobiletype(rs.getString("mobiletype");
					model.setMsisdn(rs.getString("msisdn"));
					model.setOldcellid(rs.getString("oldcellid"));
					model.setOldsgsnid(rs.getString("oldsgsnid"));
					model.setOutreason(rs.getString("outreason"));
					model.setSgsnid(rs.getString("sgsnid"));
					model.setTrigreason(rs.getString("trigreason"));
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
	 * 得到flowid的错误统计情况
	 * @param statflag 1按天0按小时
	 * @param statdate 20100731的形式
	 * @param stattime 1,2,3,3,5的形式
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	
	public PaginationSupport getFlowidErrorStat(int statflag,String statdate,String stattime,String flowid, int pageNo, int pageSize) {

	
		int totalCount = 0;
		int startIndex = (pageNo - 1) * pageSize;

		String cntsql = "select count(*) as cnt from hwchr_flowid_error_stat  where 1=1 ";
		String search = " where 1=1";
		
		
		cntsql += " and statflag=" + statflag ;
		search += " and statflag=" + statflag ;
		
		cntsql += " and statdate='" + statdate + "'";
		search += " and statdate='" + statdate + "'";
		
		if(statflag==0){
			cntsql += " and stattime = '" + stattime + "'";
			search += " and stattime = '" + stattime + "'";
		}
		
		if (flowid != null && !flowid.equals("")) {
			cntsql += " and flowid = '" + flowid + "'";
			search += " and flowid = '" + flowid + "'";
		}
//		if (flowid != null && !flowid.equals("")) {
//			cntsql += " and flowid='" + flowid + "'";
//			search += " and flowid='" + flowid + "'";
//		}
	
		System.out.println("查询SQL:" + cntsql);
		if (pageSize != Integer.MAX_VALUE) {
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from  hwchr_flowid_error_stat "  + search
				+ " ) A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN > " + startIndex;

		System.out.println("查询SQL:" + sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					HWChrFlowidErrorStat model = new HWChrFlowidErrorStat();
					model.setErrorcount(rs.getInt("errorcount"));
					model.setFlowid(rs.getString("flowid"));
					model.setInnerreason(rs.getString("innerreason"));
					model.setOutreason(rs.getString("outreason"));
					model.setStatdate(rs.getString("statdate"));
					model.setStatflag(rs.getString("statflag"));
					model.setStattime(rs.getString("stattime"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}
	
	public List getFlowidStatByCellid(final int statflag,final Date statdate,final String stattime,final String cellid,final String lac){
		String table = getChrTable(statdate);
		String sql="select flowid,count(flowid) from "+table;
		final List list=new ArrayList();
		String search = " where 1=1";
		search += " and statdate='" + statdate + "'";	
		if(statflag==0)
			search += " and stattime=" + stattime ;	
		
		if (cellid != null && !cellid.equals("")) {
		
			search += " and cellid = '" + cellid + "'";
		}
		jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			
				while (rs.next()) {
					HWChrFlowidStat model = new HWChrFlowidStat();
					model.setFlowcount(rs.getInt("flowcount"));
					model.setFlowid(rs.getString("flowid"));
					model.setStatflag(statflag+"");
					model.setStattime(stattime);
					list.add(model);
				}
				return null;
			}
		});
		
		
		return list;
	}
	/**
      * 得到flowid的统计情况
	 * @param statflag 1按天0按小时
	 * @param statdate 20100731的形式
	 * @param stattime 1,2,3,3,5的形式
	 * @param sgsnid
	 * @param kuang
	 * @param cao
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getFlowidStat(final int statflag,final String statdate,final String stattime,final String sgsnid,final String kuang,final String cao, int pageNo, int pageSize) {

		
		int totalCount = 0;
		int startIndex = (pageNo - 1) * pageSize;
		
		String search = " where 1=1";
		search += " and statflag=" + statflag ;	
		search += " and statdate='" + statdate + "'";		
		if(statflag==0){
			search += " and stattime = '" + stattime + "'";
		}
		if (sgsnid != null && !sgsnid.equals("")) {		
			search += " and sgsn = '" + sgsnid + "'";
		}
		if (kuang != null && !kuang.equals("")) {			
			search += " and kuang='" + kuang + "'";
		}
		if (cao != null && !cao.equals("")) {			
			search += " and cao='" + cao + "'";
		}
	
		String table="select flowid,sum(flowcount) as flowcount from hwchr_flowid_stat "+search+" group by flowid";
//	String table="select flowid,sum(flowcount) as flowcount,sgsn,kuang,cao from hwchr_flowid_stat "+search+" group by flowid,sgsn,kuang,cao";
		String cntsql="select count(a.flowid) from ("+table+") a";
		
		if (pageSize != Integer.MAX_VALUE) {
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from  ("+table+") order by flowid ) A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN > " + startIndex;
		System.out.println("总数SQL:" + cntsql);
		System.out.println("查询SQL:" + sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					HWChrFlowidStat model = new HWChrFlowidStat();
					model.setFlowcount(rs.getInt("flowcount"));
					model.setFlowid(rs.getString("flowid"));
//					model.setCao(cao);
//					model.setKuang(kuang);
//					model.setSgsnid(sgsnid);
//					model.setCao(rs.getString("cao"));
//					model.setKuang(rs.getString("kuang"));
//					model.setSgsnid(rs.getString("sgsn"));
//					model.setStatdate(rs.getString("statdate"));
//					model.setStatflag(rs.getString("statflag"));
//					model.setStattime(rs.getString("stattime"));
					model.setStatdate(statdate);
					model.setStatflag(statflag+"");
					model.setStattime(stattime);
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}
	
}
