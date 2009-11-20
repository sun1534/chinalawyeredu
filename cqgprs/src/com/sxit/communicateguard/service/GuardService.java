/**
 * 
 */
package com.sxit.communicateguard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.communicateguard.models.FocusCell;
import com.sxit.communicateguard.models.FocusMobile;
import com.sxit.netquality.models.Cell;

/**
 * @author 华锋 Nov 18, 2009-2:18:40 AM
 * 
 */
public class GuardService {
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	public boolean isExistCell(String cellid){
	 String sql="select count(*) as cnt from set_cell_focus where cellid='"+cellid+"'";
	 
	 return jdbcTemplate.queryForInt(sql)>0?true:false;
	}
	public boolean isExistMobile(String mobile){
		 String sql="select count(*) as cnt from set_mobile_focus where mobile='"+mobile+"'";
		 return jdbcTemplate.queryForInt(sql)>0?true:false;
		}
	/**
	 * 得到重点小区
	 * 
	 * @param cellid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PaginationSupport getFocusCellids(String cellid, int pageNo, int pageSize) {
		String countsql = "";
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;
		countsql = "select count(*) from SET_CELL_FOCUS  ";
		sql = "select * from(select a.*,rownum rn from(select * from SET_CELL_FOCUS ${where} order by CELLID) a where rownum<="
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
					FocusCell cell = new FocusCell();

					cell.setCellid(rs.getString("cellid"));
					cell.setCreateuserid(rs.getInt("createuserid"));
					cell.setCreateusername(rs.getString("createusername"));
					cell.setUpdatetime(rs.getLong("updatetime"));

					list.add(cell);
				}
				return list;
			}
		});
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

	
	public PaginationSupport getFocusMobiles(String mobile, int pageNo, int pageSize) {
		String countsql = "";
		String sql = "";

		int startIndex = (pageNo - 1) * pageSize;
		countsql = "select count(*) from SET_MOBILE_FOCUS  ";
		sql = "select * from(select a.*,rownum rn from(select * from SET_MOBILE_FOCUS ${where}) a where rownum<="
				+ (startIndex + pageSize) + ") where rn>" + startIndex;
		String where = "";
		if (mobile != null && !mobile.equals("")) {
			where += " where mobile='" + mobile + "'";
//			countsql += " where mobile='" + mobile + "'";
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
					FocusMobile mobile = new FocusMobile();

					mobile.setMobile(rs.getString("mobile"));
					mobile.setCreateuserid(rs.getInt("createuserid"));
					mobile.setCreateusername(rs.getString("createusername"));
					mobile.setUpdatetime(rs.getLong("updatetime"));

					list.add(mobile);
				}
				return list;
			}
		});
		List list = (List) object;
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}
	
	
}
