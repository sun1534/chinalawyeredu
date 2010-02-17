/**
 * 
 */
package main.nsvcalarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import main.util.DBUtils;

import org.apache.commons.logging.Log;

/**
 * 
 * @author 华锋 Nov 15, 2009-9:01:53 PM
 * 
 */
public class NsvcAlarmFileHandle {
	private static final String SEPARATOR = System.getProperty("file.separator");

	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(NsvcAlarmFileHandle.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMdd");
	private static String BASE_STR = "======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST =========";

	private NsvcAlarmFile moveFile(File srcfile, String todayDestDirName, String sgsnid, String timestamp) {
		String newFileName = srcfile.getName() + "_" + sgsnid + "_" + timestamp;

		File destfile = new File(todayDestDirName + SEPARATOR + newFileName);
		srcfile.renameTo(destfile);

		NsvcAlarmFile errorfile = new NsvcAlarmFile();
		errorfile.setSgsnid(sgsnid);
		errorfile.setDestdir(todayDestDirName);
		errorfile.setDestfile(destfile);
		errorfile.setSrcfilename(srcfile.getName());

		return errorfile;
	}

	/**
	 * 这里的规则是srcdir下有n多个SGSN*的目录,主要是copy这个目录下的数据 这里备份的话，每天1个目录
	 * 
	 * 
	 * 感觉这个是应该要一直循环的,比如每30秒钟轮询一次,而不是每10分钟
	 * 
	 * @param srcdir
	 * @param destdir
	 */
	public Map<String, NsvcAlarmFile> copyFile(String srcdir, String destdir) {
		java.util.Date today = new java.util.Date();
		String timestamp = dftime.format(today);
		File srcrootdir = new File(srcdir);
		String[] srcchilddirs = srcrootdir.list();
		String todayDestDirName = destdir + SEPARATOR + dfdate.format(today);
		File todayDestDir = new File(todayDestDirName);

		Map<String, NsvcAlarmFile> errorfiles = new HashMap<String, NsvcAlarmFile>();
		List<File> shoulds = new ArrayList<File>();
		if (!todayDestDir.exists()) {
			boolean b = todayDestDir.mkdirs();
			int k = 0;
			while (!b && (k++) < 10) {
				b = todayDestDir.mkdirs();
				LOG.info("创建存储目录失败,重新来过!" + k);
			}
			if (!b) {
				// LOG.warn("创建存储目录失败,退出系统");
				// System.exit(0);
				throw new RuntimeException("创建存储目录失败,退出系统");
			}
			LOG.info("创建存储目录成功:" + todayDestDirName);
		}

		for (String srchilddir : srcchilddirs) {

			if (srchilddir.indexOf("SGSN") != -1) {
				File file = new File(srcdir + SEPARATOR + srchilddir);
				File[] srcfiles = file.listFiles();
				for (File srcfile : srcfiles) {
					// String srcfilename = srcfile.getName();
					long lastmodified = srcfile.lastModified();
					try {
						Thread.sleep(100L);
					} catch (Exception e) {
						LOG.error("休眠异常:", e);
					}
					long nowmodified = srcfile.lastModified();
					if (nowmodified == lastmodified) {
						// 将这个文件copy移动到另外一个地方
						String sgsnid = srchilddir;
						NsvcAlarmFile errorfile = this.moveFile(srcfile, todayDestDirName, sgsnid, timestamp);
						errorfile.setModified(new java.sql.Timestamp(today.getTime()));

						errorfiles.put(sgsnid + srcfile.getName(), errorfile);
					} else {
						shoulds.add(srcfile);
						// 存起来,然后再处理,轮询2次吧
					
					}
				}
			}
		}
		for (File srcfile : shoulds) {
			LOG.warn("这里需要再处理:" + srcfile);
		}
		return errorfiles;
	}
	/**
	 * 
	 * @param file
	 * @return
	 */
	public List<NsvcAlarmCdr> parseFile(NsvcAlarmFile file) {
		long now = System.currentTimeMillis();
		java.io.BufferedReader br = null;
		int handledLines = file.getHandleLines();
		LOG.info(file.getDestfile() + "已经处理过" + handledLines + "行数据");
		List<NsvcAlarmCdr> cdrs = new ArrayList<NsvcAlarmCdr>();
		int lastCount = 0; // 实际处理的个数
		try {
			br = new BufferedReader(new java.io.InputStreamReader(new FileInputStream(file.getDestfile())));

			String line = null;
			
			int i = 0;
			while ((line = br.readLine()) != null) {

//				alarm;	pmSupThresholdCrossedWar;	qos;	warning;	2009-12-29 11:06:50;	The value of measurement type frWanWanFlows has reached its threshold.;	{2132543023};	'measTypeIndex 2.3.6.1';

				if(line.startsWith("alarm;")&&line.indexOf("warning;")!=-1){
					lastCount++;
					StringTokenizer st=new StringTokenizer(line,";");
					String type=st.nextToken();
					String pcm=st.nextToken();
					String communication=st.nextToken();
					String _warnning=st.nextToken();
					String date=st.nextToken();
					String reason=st.nextToken();
					String timestamp=st.nextToken();
					String gbindex=st.nextToken();
					
					NsvcAlarmCdr cdr=new NsvcAlarmCdr();
					cdr.setCommunication(communication);
					cdr.setAlarmdate((int)(df.parse(date).getTime()/1000));
					cdr.setGbindex(gbindex);
					cdr.setPcm(pcm);
					cdr.setQos(communication);
					cdr.setReason(reason);
					cdr.setTimestamp(timestamp);
					cdr.setSgsnid(file.getSgsnid());
					cdrs.add(cdr);
				}
			}
			file.setHandleLines(handledLines + lastCount);
			LOG.info(file.getDestfile() + "解析时间为:" + (System.currentTimeMillis() - now) + "总共:" + i + "区块,实际处理"
					+ lastCount + "块," + cdrs.size());
		} catch (Exception e) {
			LOG.error("解析文件有误", e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Exception ee) {
					LOG.error(file + "关闭异常", ee);
				}
		}

		return cdrs;
	}

	/**
	 * 新增cdr到数据库中
	 * 
	 * @param cdr
	 */
	public void save(List<NsvcAlarmCdr> cdrs) {
		Connection con = null;
		PreparedStatement stmt = null;
		int now = (int) (System.currentTimeMillis() / 1000);
		try {
			con = DBUtils.getOracleCon();

			String sql = "insert into alarm_nsvc_logs(PCM,COMMUNICATION,ALARMDATE,REASON,TIMESTAMP,GBINDEX,RECORDTIME,sgsnid) values(?,?,?,?,?,?,?,?)";

			stmt = con.prepareStatement(sql);
			int len = cdrs.size();
			long begin = System.currentTimeMillis();
		
			for (int i = 0; i < len; i++) {
				NsvcAlarmCdr cdr = cdrs.get(i);
			
				stmt.setString(1, cdr.getPcm());
				stmt.setString(2, cdr.getCommunication());
				stmt.setInt(3, cdr.getAlarmdate());
				stmt.setString(4, cdr.getReason());
				stmt.setString(5, cdr.getTimestamp());
				stmt.setString(6, cdr.getGbindex().replaceAll("'", "").trim());
				stmt.setTimestamp(7,new java.sql.Timestamp(System.currentTimeMillis()));
				stmt.setString(8, cdr.getSgsnid());
				stmt.addBatch();
				if (((i + 1) % 300) == 0) {
					stmt.executeBatch();
					stmt.clearBatch();
					long time = System.currentTimeMillis();
					LOG.info("插入300条NSVC警告记录的时间：" + (time - begin));
					begin = time;
				}
			}
			int[] s = stmt.executeBatch();
			stmt.clearBatch();
			// 33的错误同时入到另外一张表
		
		
			LOG.info("最后入库记录数：" + (s != null ? s.length : 0) + ",时间:" + (System.currentTimeMillis() - begin));
		} catch (Exception e) {
			LOG.error("插入数据库错误:", e);
			// e.printStackTrace();
			// ReadErrorApns.errorlog("插入数据库错误:" + e);
		} finally {
			DBUtils.closeResource(null, stmt, null);
		}
	}

	public static void main(String[] args) throws Exception {
		File file = new File("d:\\outerrr.txt");
		NsvcAlarmFile ef = new NsvcAlarmFile();
		ef.setDestdir("d:\\");
		ef.setDestfile(file);
		ef.setHandleLines(0);
		ef.setSgsnid("SGSN01");
		ef.setSrcfilename("d:\\outer.txt");
		NsvcAlarmFileHandle fh = new NsvcAlarmFileHandle();
		fh.parseFile(ef);

	}
}
