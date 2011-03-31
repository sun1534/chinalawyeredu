package com.xjdzjc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.xjdzjc.common.DbHelper;
import com.xjdzjc.model.City_Tpl_content;
import com.xjdzjc.model.City_Prompt;

/**
 * 地州短信模板业务处理类
 * 
 * @author xyl
 * @date 2011.01.09
 */
public class TemplateService {
	public static Logger log = Logger.getLogger(TemplateService.class);

	public List<City_Tpl_content> getcitytemplateList(Connection con,
			int status) {
		List<City_Tpl_content> list = new ArrayList<City_Tpl_content>();
		String sql = "select * from base.city_template_content where status=?";
		log.info("---开始取地州内容模板" + sql + "status=" + status);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, status);
			rs = stmt.executeQuery();
			while (rs.next()) {
				City_Tpl_content ctempc = new City_Tpl_content();
				ctempc.setId(rs.getInt("id"));
				ctempc.setCity(rs.getString("city"));
				ctempc.setCityname(rs.getString("cityname"));
				ctempc.setSendday(rs.getInt("sendday"));
				ctempc.setSql_content(rs.getString("sql_content"));
				ctempc.setTemplate_con_details(rs
						.getString("template_con_details"));
				ctempc.setTemplate_con_simple(rs
						.getString("template_con_simple"));
				ctempc.setTemplate_con_null(rs.getString("template_con_null"));
				ctempc.setGateway_id(rs.getString("gateway_id"));
				ctempc.setStatus(rs.getInt("status"));
				list.add(ctempc);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据模板数据错误！" + e.getMessage());
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return list;
	}

	/**
	 * 获取更新地州小提示
	 * 
	 * @param con
	 * @return
	 */
	public List<City_Prompt> getCityPrompt(Connection con) {
		List<City_Prompt> list = new ArrayList<City_Prompt>();
		/**
		 * 20110318 修改 数据库新增7条数据，分别是星期一 星期二 星期三 星期四....星期天
		 * 每天根据星期几来取数据  
		 * 20110318 修改数据库，增加一个status 状态
		 */
		int week=1;
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == 1) {
			week = 7;
		} else {
			week = week - 1;
		}
		log.info("今天 是星期" + week);
		String sql = "select * from city_prompt_update where week="+week +" and status=0";
		log.info("取更新地州小提示");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				City_Prompt ctempc = new City_Prompt();
				ctempc.setId(rs.getInt("id"));
				ctempc.setCity(rs.getString("city"));
				ctempc.setContent(rs.getString("content"));
				list.add(ctempc);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据模板数据错误！" + e.getMessage());
		} finally {
			DbHelper.closeRsorStmt(rs, stmt);
		}
		return list;
	}

	/**
	 * 获取更新地州小提示
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int UpdateCityPrompt(Connection con, String city, String content)
			throws SQLException {
		String sql = "update base.city_prompt set sql_content=?  where city=? ";
		int i[] = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			if (city == null || city.equals("") || city.indexOf(",") < 0)
				return 0;
			String cityname[] = city.split(",");
			for (String string : cityname) {
				stmt.setString(1, content);
				stmt.setString(2, string);
				stmt.addBatch();
			}
			i = stmt.executeBatch();
		} finally {
			DbHelper.closeRsorStmt(null, stmt);
		}
		return i.length;
	}

}
