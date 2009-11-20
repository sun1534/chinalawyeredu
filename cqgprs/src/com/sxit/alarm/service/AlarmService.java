/**
 * 
 */
package com.sxit.alarm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.alarm.models.GbLinkAlarm;
import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractAction;
import com.sxit.stat.models.BsnRncStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * @author 华锋 Oct 16, 2009-4:54:14 PM
 * 
 */
public class AlarmService {
	private static Log _LOG = LogFactory.getLog(AlarmService.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("HH:00");
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

	public void updateAlarmHasRead() {
		String sql = "update alarm_gb set isactive=0 where isactive=1";
		jdbcTemplate.execute(sql);
		_LOG.info("此次的警告信息已读完毕");
		
	}
	
	
	public int  getGbAlarmCounts() {
		// int _date = (int) (date.getTime() / 1000);
		String sql = "select count(*) from  alarm_gb where isactive=1 ";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 查询告警信息历史列表
	 * 
	 * @param date
	 * @return
	 */
	public List getGbAlarms() {
		// int _date = (int) (date.getTime() / 1000);
		String sql = "select * from  alarm_gb where isactive=1 order by alarmtime desc";
		System.out.println(sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					GbLinkAlarm model = new GbLinkAlarm();
					model.setAlarmtime(rs.getLong("alarmtime"));
					model.setAlarmtype(rs.getInt("alarmtype"));
					model.setFlowcount(rs.getDouble("flowcount"));
					model.setNsvc(rs.getString("nsvc"));
					model.setIsactive(rs.getInt("isactive"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
	   return list;
	}
	
	/**
	 * 查询告警信息历史列表
	 * 
	 * @param date
	 * @return
	 */
	public PaginationSupport getAlarmhishory(Date startdate, Date enddate, int pageNo, int pageSize) {
		// int _date = (int) (date.getTime() / 1000);

		long start = 0;
		long end = 0;

		if (startdate != null)

			start = StatUtil.getDateTime(startdate);
		else
			start = 0;
		if (enddate != null)
			end = com.sxit.stat.util.StatUtil.getOneDayAfter(com.sxit.stat.util.StatUtil.getDateTime(enddate));
		else
			end = Long.MAX_VALUE;
		String where = " where isactive=0 and (alarmtime between " + start / 1000 + " and " + end / 1000
				+ ")";
		int totalCount = 0;
		if (pageSize != Integer.MAX_VALUE) {
			String countsql = "select count(*) as cnt from  alarm_gb {0}";
			countsql = countsql.replace("{0}", where);
			totalCount = jdbcTemplate.queryForInt(countsql);
		}
		int startIndex = (pageNo - 1) * pageSize;
//		Hibernate:    select * from ( select row_.*, rownum rownum_ from ( select * from SYS_LOGINLOG this_ order by this_.loginid desc ) row_ ) where rownum_ <= ? and rownum_ > ?

		String sql = "select * from(select a.*,rownum rn from(select * from  alarm_gb {0} order by alarmtime desc) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex ;

		sql = sql.replace("{0}", where);
		System.out.println(sql);

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					GbLinkAlarm model = new GbLinkAlarm();
					model.setAlarmtime(rs.getLong("alarmtime"));
					model.setAlarmtype(rs.getInt("alarmtype"));
					model.setFlowcount(rs.getDouble("flowcount"));
					model.setNsvc(rs.getString("nsvc"));
					model.setIsactive(rs.getInt("isactive"));
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
