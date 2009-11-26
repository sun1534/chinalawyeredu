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
	
	public List getHightStreamDayCell(Date date,String standard,String condition,String bscid,String orderby){
	
		String _date=df.format(date);
		String sql="";
		String where="";
		if(!(bscid==null||bscid.equals("")))
			where=" and bscid='"+bscid+"'";
		if(standard.equals("1")){
//			 sql="select cellid,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime="+_date+") group by cellid having sum(allvolume)>="+condition+") order by allv desc";
//			 sql="select a.cellid,a.allvolume as currentvolume,b.allvolume as allvolume from stat_cellid a,allvolume_cellid b where  a.cellid=b.cellid(+) and a.dayflag=1 and a.allvolume>="+condition+" and a.stattime="+_date+" "+ orderby;
//			 sql="select a.cellid,a.lac,a.bscid,a.allvolume as currentvolume,b.allvolume as allvolume from stat_cellid a,allvolume_cellid b where a.cellid=b.cellid(+) and a.dayflag=1 "+where+" and a.stattime="+_date+" and currentvolume>="+condition+orderby;
			sql="select cellid,lac,bscid,allvolume from stat_cellid a where dayflag=1 "+where+" and stattime="+_date+" and allvolume>="+condition+orderby;

		}else{
			
//			 sql="select * from(select a.cellid,a.lac,a.bscid,a.allvolume as currentvolume,b.allvolume as allvolume from stat_cellid a,allvolume_cellid b where a.cellid=b.cellid(+) and a.dayflag=1 "+where+" and a.stattime="+_date+" order by a.allvolume desc) where rownum<="+condition+orderby;
			 sql="select * from(select cellid,lac,bscid,allvolume from stat_cellid  where dayflag=1 "+where+" and stattime="+_date+" order by allvolume desc) where rownum<="+condition+orderby;

//				 sql="select cellid,allvolume from stat_cellid where dayflag=1 and stattime="+_date+" and rownum<="+condition+" order by allvolume desc";
        }
		
		System.out.println(sql);
		
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TopCell model = new TopCell();
					model.setCellid(rs.getString("cellid"));
					model.setLac(rs.getString("lac"));
					model.setCurrentStream(rs.getDouble("allvolume"));
//					model.setTotalStream(rs.getDouble("allvolume"));
//					System.out.println(rs.getString("lac")+"-"+rs.getString("cellid"));
					String cellkey=rs.getString("lac")+"-"+rs.getString("cellid");
					if(BasicSetService.ALL_CELLS.containsKey(cellkey)){
						model.setTotalStream(com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey).getAllvolume());
					}					model.setBscid(rs.getString("bscid"));
					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
        return list;
	}
	
	public List getHightStreamHourCell(String date,String hour,String bscid,String standard,String condition,String orderby){

		String start = date + " " + hour + ":00:00";
		String end = date + " " + hour + ":59:59";
		String where="";
		if(!(bscid==null||bscid.equals("")))
			where=" and bscid='"+bscid+"'";
		int _start = getDfSec(start);
		int _end = getDfSec(end);
		
		
		String sql="";
		if(standard.equals("1")){
//			 sql="select * from(select a.cellid,a.bscid,sum(a.allvolume) as currentvolume,b.allvolume as allvolume from stat_cellid a,allvolume_cellid b where a.cellid=b.cellid(+) and a.dayflag=1 "+where+" and a.stattime="+_date+") where currentvolume>="+condition+orderby;

			 sql="select a.cellid,a.lac,a.bscid,a.allvolume from (select cellid,lac,bscid,sum(allvolume) as allvolume from stat_cellid where dayflag=0 "+where+" and (stattime>="+_start+" and stattime<="+_end+") group by cellid,lac,bscid having sum(allvolume)>="+condition+") a"+orderby;;
        }else{
			 sql="select * from(select a.cellid,a.lac,a.bscid,a.allvolume from (select cellid,lac,bscid,sum(allvolume) as allvolume from stat_cellid where dayflag=0 "+where+" and (stattime>="+_start+" and stattime<="+_end+") group by cellid,lac,bscid) a order by allvolume desc) where rownum<="+condition+orderby;
//			 sql="select cellid,allv from(select cellid,sum(allvolume) as allv from stat_cellid where dayflag=0 and (stattime>="+_start+" and stattime<="+_end+") group by cellid ) where rownum<="+condition+" order by allv desc";
        }
		long now=System.currentTimeMillis();
	
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					TopCell model = new TopCell();
					model.setCellid(rs.getString("cellid"));
					model.setLac(rs.getString("lac"));
					model.setCurrentStream(rs.getDouble("allvolume"));
//					model.setTotalStream(rs.getDouble("allvolume"));
			String cellkey=rs.getString("lac")+"-"+rs.getString("cellid");
			if(BasicSetService.ALL_CELLS.containsKey(cellkey)){
				model.setTotalStream(com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey).getAllvolume());
			}
					
					model.setBscid(rs.getString("bscid"));
					list.add(model);
				}
				return list;
			}
		});
		System.out.println((System.currentTimeMillis()-now)+":hour()::"+sql);
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
//	public List getHightStreamCellAll(String standard,String condition,String start,String end){
//		String sql="";
//		if(standard.equals("1")){
//			 sql="select cellid,lac,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime>="+start+" and stattime<="+end+") group by cellid,lac having sum(allvolume)>="+condition+") order by allv desc";
//        }else{
//			 sql="select cellid,lac,allv from(select cellid,lac,sum(allvolume) as allv from stat_cellid where dayflag=1 and (stattime>="+start+" and stattime<="+end+") group by cellid,lac ) where rownum<="+condition+" order by allv desc";
//        }
//		
//		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
//			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
//				List list = new ArrayList();
//				while (rs.next()) {
//					TopCell model = new TopCell();
//					model.setCellid(rs.getString("cellid"));
//					model.setLac(rs.getString("lac"));
//					model.setTotalStream(rs.getFloat("allv"));
//					list.add(model);
//				}
//				return list;
//			}
//		});
//		List list = (List) object;
//        return list;
//		
//	}
	
}
