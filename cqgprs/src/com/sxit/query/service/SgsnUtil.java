package com.sxit.query.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.netquality.models.Sgsn;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.stat.models.SgsnStatModel;

/** 
 * 
 */

/**
 * @author 华锋 Oct 10, 2009 2:41:59 PM
 * 
 */
public class SgsnUtil {
	public static void getsgsns() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@218.201.8.150:1521:ora92", "jf_gprs",
				"jf_gprs");

		String sql = "select a.*,b.loginname,b.loginpwd from  SET_SGSN a,set_sgsn_server b where a.sgsnid=b.sgsnid(+) and a.opttype!=2 ";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		BasicSetService.ALL_SGSNS.clear();
		while (rs.next()) {
			Sgsn model = new Sgsn();
			model.setSgsnid(rs.getString("SGSNID"));
			// model.setSgsnarea(rs.getString("sgsnarea"));
			model.setLastopt(rs.getString("OPTTYPE"));
			Date date = new Date();
			date.setTime(rs.getLong("UPDATETIME") * 1000);
			model.setLastupdate(date);
			model.setSgsntype(rs.getString("provider"));
			model.setBsccount(rs.getInt("bsccount"));
			model.setSgsnip(rs.getString("sgsnip"));
			model.setSlotcount(rs.getInt("slotcount"));
			model.setCapacity(rs.getDouble("capacity"));
			model.setSgsnarea(rs.getString("sgsnarea"));
			model.setLoginname(rs.getString("loginname"));
			model.setLoginpwd(rs.getString("loginpwd"));
			
			BasicSetService.ALL_SGSNS.put(rs.getString("SGSNID"), model);
		}
		rs.close();
		stmt.close();
		con.close();
	}

	
}
