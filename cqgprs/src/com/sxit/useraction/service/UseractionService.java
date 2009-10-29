/**
 * 
 */
package com.sxit.useraction.service;

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
import com.sxit.stat.models.BsnRncStatModel;

/**
 * @author 华锋
 * Oct 23, 2009-2:34:40 PM
 *
 */
public class UseractionService {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
/**
 * 统计高流量的用户排名,根据时间和所在的apn进行排名
 * @param date
 * @param apn
 * @param pageNo
 * @param pageSize
 * @return
 */
	public PaginationSupport getHightStreamUser(Date date,String apn,int pageNo,int pageSize) {
		int _date = (int) (date.getTime() / 1000);
		String countsql="select count(*) as cnt from  STAT_BSC where STATTIME="+_date+" and dayflag=1 ";
		int totalCount=jdbcTemplate.queryForInt(countsql);
		int startIndex=(pageNo-1)*pageSize;
		
		String sql = "select * from(select a.*,rownum rn from(select BSCID,SGSNID,STATTIME,NETTYPE,USERCOUNT,ALLVOLUME from  STAT_BSC where STATTIME="+_date+" and dayflag=1 order by sgsnid,NETTYPE) a where rownum<="+(startIndex+pageSize)+") where rn>="+startIndex;

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
	 * 统计pdp的失败用户排名
	 * @param date
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getTopPdpErrors(Date date,int pageNo,int pageSize) {
		
//		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
//		return ps;
		
		return null;
	}
 
	/**
	 * 得到pdp失败情况总表，主要是当天累加值以及本时间段增加值以及上个时间段增加值
	 * 
	 * 按天和小时统计来
	 * @param date
	 * @param type
	 * @return
	 */
	public List getPdpErrorsAll(Date date,String type){
		
		return null;
	}
}