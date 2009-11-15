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

import com.sxit.netquality.models.TopCell;

/**
 * @author 华锋
 * Nov 7, 2009-3:48:26 PM
 *
 */
public class HighStreamService {
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
	
/**
 * 
 * 得到高流量的小区
 * @param standard 按top排名还是只要大于多少就行了
 * @param condition 条件
 * @param flag 按天还是按小时
 * @param date 日期
 * @param hour 那个小时
 * @return
 */
	
	public List getHightStreamDayCell(String standard,String condition,Date date){
	
		String _date=df.format(date);
		String sql="";
		if(standard.equals("1")){
//			 sql="select cellid,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime="+_date+") group by cellid having sum(allvolume)>="+condition+") order by allv desc";
			 sql="select cellid,allvolume from stat_cellid where dayflag=1 and stattime="+_date+" where allvolume>="+condition+") order by allvolume desc";

		}else{
			 sql="select cellid,allvolume from stat_cellid where dayflag=1 and stattime="+_date+" and rownum<="+condition+" order by allvolume desc";
        }
		
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TopCell model = new TopCell();
					model.setCellid(rs.getString("cellid"));
					model.setCurrentStream(rs.getFloat("allvolume"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
	}
	
	public List getHightStreamHourCell(String standard,String condition,String date,String hour){

		String start = date + " " + hour + ":00:00";
		String end = date + " " + hour + ":59:59";

		int _start = getDfSec(start);
		int _end = getDfSec(end);
		
		
		String sql="";
		if(standard.equals("1")){
			 sql="select cellid,sum(allvolume) as allv from stat_cellid where dayflag=0 and (stattime>="+_start+" and stattime<="+_end+") group by cellid having sum(allvolume)>="+condition+") order by allv desc";
        }else{
			 sql="select cellid,allv from(select cellid,sum(allvolume) as allv from stat_cellid where dayflag=0 and (stattime>="+_start+" and stattime<="+_end+") group by cellid ) where rownum<="+condition+" order by allv desc";
        }
		
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TopCell model = new TopCell();
					model.setCellid(rs.getString("cellid"));
					model.setCurrentStream(rs.getFloat("allv"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
	}
	
	/**
	 * 得到一段时间内总计的流量排名,这里就没有当前流量的概念了
	 * @param flag
	 * @param condition
	 * @param start 20091001的形式
	 * @param end
	 * @return
	 */
	public List getHightStreamCellAll(String standard,String condition,String start,String end){
		String sql="";
		if(standard.equals("1")){
			 sql="select cellid,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime>="+start+" and stattime<="+end+") group by cellid having sum(allvolume)>="+condition+") order by allv desc";
        }else{
			 sql="select cellid,allv from(select cellid,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime>="+start+" and stattime<="+end+") group by cellid ) where rownum<="+condition+" order by allv desc";
        }
		
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TopCell model = new TopCell();
					model.setCellid(rs.getString("cellid"));
					model.setTotalStream(rs.getFloat("allv"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
		
	}
	
}
