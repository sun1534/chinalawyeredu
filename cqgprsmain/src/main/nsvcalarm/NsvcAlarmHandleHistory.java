/**
 * 
 */
package main.nsvcalarm;

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
public class NsvcAlarmHandleHistory {
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(NsvcAlarmHandleHistory.class);
	private static DateFormat df=new java.text.SimpleDateFormat("yyyyMMddHHmm");

	/**
	 * 超过这个，即使相同也不换行
	 */
	private static final long INTERVAL =2 * 60 * 60 * 1000;
	private Connection con;
	private static final String selectSql = "select sgsnid,srcfile,destfile,modifytime,lastlines from nsvc_alarm_handle";

	public NsvcAlarmHandleHistory(Connection con) {
		this.con = con;
	}

	/**
	 * 这里实际就是更新那个最后的处理行数
	 * 
	 * @param files
	 */
	public void getFromDB(Map<String, NsvcAlarmFile> files) {

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
					NsvcAlarmFile file = files.get(key);
					long thefiletime = file.getModified().getTime();
					LOG.info(sgsnid+"_"+srcfile+"上次处理行数:"+rs.getInt("lastlines")+",间隔:"+(thefiletime - modified));
					if (thefiletime - modified < INTERVAL){
						file.setHandleLines(rs.getInt("lastlines"));
					}
					sqls
							.add("delete from nsvc_alarm_handle where sgsnid='" + sgsnid + "' and srcfile='" + srcfile
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
	public void saveLatestErrors(Map<String, NsvcAlarmFile> mapfiles) {
		java.util.Iterator<NsvcAlarmFile> files = mapfiles.values().iterator();
		List<String> sqls = new ArrayList<String>();
		while (files.hasNext()) {
			NsvcAlarmFile file = files.next();
			String sql = "insert into nsvc_alarm_handle(sgsnid,srcfile,destfile,modifytime,lastlines) values('"
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
