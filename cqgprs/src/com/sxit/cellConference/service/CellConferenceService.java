/**
 * 
 */
package com.sxit.cellConference.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 华锋
 * Nov 26, 2009-12:36:18 AM
 *
 */
public class CellConferenceService {

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * 得到某天的所有会议小区数据
	 * @return
	 */
	public List getDayConferenceList(String date){
	
		long start=com.sxit.stat.util.StatUtil.getDateTime(date);
		long end=com.sxit.stat.util.StatUtil.getOneDayAfter(start);
		String sql="select * from ";
		
		return null;
	}
	
}
