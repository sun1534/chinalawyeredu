/**
 * 
 */
package main.readerrors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
	private static DateFormat df=new java.text.SimpleDateFormat("yyyyMMddHHmm");

	/**
	 * 超过这个，即使相同也不换行
	 */
	private static final long INTERVAL = 60 * 60 * 1000;
	private Connection con;
	private static final String selectSql = "select sgsnid,srcfile,destfile,modifytime,lastlines from apn_error_handle";
	private static final String selecthwSql = "select sgsnid,srcfile,destfile,modifytime,lastlines from apn_error_handle where sgsnid in('SGSN7','SGSN8','SGSN9')";

	public ReadHandleHistory(Connection con) {
		this.con = con;
	}
/**
 * chr的处理方式,应该是看那个SGSN2010000000_201000000.chr这个的前面这块是否有一致的，也就是存的srcfile要是这样子的，就不考虑时间了
 * @param files
 */
	public void getFromCHRDB(Map<String, ErrorFile> files) {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selecthwSql);
			List<String> sqls = new ArrayList<String>();
			while (rs.next()) {
				String sgsnid = rs.getString("sgsnid");
				String srcfile = rs.getString("srcfile");

				String[] splits=srcfile.split("_");
				String srcfileprefix=splits[0];
				
				String key = sgsnid + srcfileprefix;
				if (files.containsKey(key)) {
					ErrorFile file = files.get(key);
					System.out.println(sgsnid+"_"+srcfile+"上次处理行数:"+rs.getInt("lastlines")+",上次文件:"+srcfile+",本次文件:"+file.getSrcfilename());
					file.setHandleLines(rs.getInt("lastlines"));
					sqls
							.add("delete from apn_error_handle where sgsnid='" + sgsnid + "' and srcfile='" + srcfile
									+ "'");
				}else{
					System.out.println("这些文件此次不用处理:"+sgsnid+"_"+srcfile+"_"+df.format(rs.getTimestamp("modifytime")));
				}
			}
			main.util.MainStatUtil.executeSql(con, sqls);

		} catch (Exception e) {
			LOG.error("getFromDB:", e);
		} finally {
			main.util.DBUtils.closeResource(rs, stmt, null);
		}
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
					LOG.info(sgsnid+"_"+srcfile+"上次处理行数:"+rs.getInt("lastlines")+",间隔:"+(thefiletime - modified));
					if (thefiletime - modified < INTERVAL){
						file.setHandleLines(rs.getInt("lastlines"));
					}
					sqls
							.add("delete from apn_error_handle where sgsnid='" + sgsnid + "' and srcfile='" + srcfile
									+ "'");
				}else{
					LOG.debug("这些文件此次不用处理:"+sgsnid+"_"+srcfile+"_"+df.format(rs.getTimestamp("modifytime")));
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
