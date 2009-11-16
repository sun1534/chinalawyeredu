/**
 * 
 */
package main.readerrors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * @author 华锋 Nov 15, 2009-8:31:54 PM
 * 
 */
public class ReadHandleHistory {
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(ReadHandleHistory.class);

	/**
	 * 超过这个，即使相同也不换行
	 */
	private static final long INTERVAL = 60 * 60 * 1000;
	private Connection con;
	private static final String selectSql = "select sgsnid,srcfile,destfile,modifytime,lastlines from apn_error_handle";

	public ReadHandleHistory(Connection con) {
		this.con = con;
	}

	/**
	 * 这里实际就是更新那个最后的处理行数
	 * 
	 * @param files
	 */
	public void getFromDB(Map<String, ErrorFile> files) {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSql);
			List<String> sqls = new ArrayList<String>();
			while (rs.next()) {
				String sgsnid = rs.getString("sgsnid");
				String srcfile = rs.getString("srcfile");
				long modified = rs.getTimestamp("modifytime").getTime();
				String key = sgsnid + srcfile;
				if (files.containsKey(key)) {
					ErrorFile file = files.get(key);
					long thefiletime = file.getModified().getTime();
					if (thefiletime - modified < INTERVAL)
						file.setHandleLines(rs.getInt("lastlines"));
					//
					sqls
							.add("delete from apn_error_handle where sgsnid='" + sgsnid + "' and srcfile='" + srcfile
									+ "'");
				}else{
					LOG.debug("此文件上次没有处理过:"+srcfile);
				}
			}
			main.util.MainStatUtil.executeSql(con, sqls);

		} catch (Exception e) {
			LOG.error("getFromDB:", e);
		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (stmt != null)
//					stmt.close();
//			} catch (Exception e) {
//				LOG.error("getFromDB:", e);
//			}
			main.util.DBUtils.closeResource(rs, stmt, null);
		}
	}
/**
 * 
 * @param mapfiles
 */
	public void saveLatestErrors(Map<String, ErrorFile> mapfiles) {
		java.util.Iterator<ErrorFile> files = mapfiles.values().iterator();
		List<String> sqls = new ArrayList<String>();
		while (files.hasNext()) {
			ErrorFile file = files.next();
			String sql = "insert into apn_error_handle(sgsnid,srcfile,destfile,modifytime,lastlines) values('"
					+ file.getSgsnid() + "','" + file.getSrcfilename() + "','" + file.getDestfile().getName()
					+ "',sysdate," + file.getHandleLines() + ")";
			sqls.add(sql);

		}
		try {
			main.util.MainStatUtil.executeSql(con, sqls);
		} catch (Exception e) {
			LOG.error("saveLatestErrors::", e);
		}
	}
}
