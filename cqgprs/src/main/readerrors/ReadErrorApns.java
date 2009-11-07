package main.readerrors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.util.DBUtils;
import main.util.SelfLog;

/**
 * 
 */

/**
 * @author 华锋 Nov 4, 2009-9:21:28 PM
 * 
 */
public class ReadErrorApns {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String BASE_STR = "======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST =========";
//	private static java.io.PrintWriter ERRORLOG = null;

	/**
	 * 处理前放文件的目录
	 */
	private static String SRCDIR = "c:\\download";
	/**
	 * 处理完毕后的备份目录
	 */
	private static String DESTDIR = "c:\\bak";
	
	private static String LOGDIR = "c:\\bak";


	private static SelfLog LOG;
	private static List<ErrorApnsCdr> cdrs;

	private static void parseFile(String file) {
		long now = System.currentTimeMillis();
		java.io.BufferedReader br = null;
		try {
			br = new BufferedReader(new java.io.InputStreamReader(new FileInputStream(file)));

			String line = null;

			int i = 0;
			while ((line = br.readLine()) != null) {

				// ======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST
				// =========
				// Time : 2009-11-04 12:17:23
				// Node : e_Erlang__Global_pm1_16_2_1@eqm01s10p2
				// IMSI : 460007611131758
				// SM Cause : Service Option not Subscribed (#33)
				// Details : Service option not subscribed
				// APN Req. : cmwap
				// APN Sub. : cmmm

				try {
					if (line.equals(BASE_STR)) {
						i++;
						ErrorApnsCdr cdr = new ErrorApnsCdr();
						String timeline = br.readLine();

						int timelineidx = timeline.indexOf(":");
						while (timelineidx == -1) {

							timeline = br.readLine();
							timelineidx = timeline.indexOf(":");
						}

						String nodeline = br.readLine();
						int nodelineidx = nodeline.indexOf(":");
						while (nodelineidx == -1) {

							timeline = timeline + nodeline;
							nodeline = br.readLine();
							nodelineidx = nodeline.indexOf(":");
						}
						String imsiline = br.readLine();

						int imsilineidx = imsiline.indexOf(":");
						while (imsilineidx == -1) {

							nodeline = nodeline + imsiline;
							imsiline = br.readLine();
							imsilineidx = imsiline.indexOf(":");
						}
						String smcauseline = br.readLine();

						int smcauseidx = smcauseline.indexOf(":");
						while (smcauseidx == -1) {

							imsiline = imsiline + smcauseline;
							smcauseline = br.readLine();
							smcauseidx = smcauseline.indexOf(":");
						}
						String detailsline = br.readLine();
						int detailsidx = detailsline.indexOf(":");
						while (detailsidx == -1) {
							smcauseline = smcauseline + detailsline;
							detailsline = br.readLine();
							detailsidx = detailsline.indexOf(":");
						}
						String apnreqline = br.readLine();
						int apnreqidx = apnreqline.indexOf(":");
						while (apnreqidx == -1) {
							detailsline = detailsline + apnreqline;
							apnreqline = br.readLine();
							apnreqidx = apnreqline.indexOf(":");
						}

						String apnsubline = br.readLine();
						int apnsubidx = apnsubline.indexOf(":");
						while (apnsubidx == -1) {
							// System.out.println("这里执行了啊："+apnsub+"=="+apnsubidx);
							apnreqline = apnreqline + apnsubline;
							apnsubline = br.readLine();
							apnsubidx = timeline.indexOf(":");
						}

						String time = timeline.substring(timelineidx + 2);
						String node = nodeline.substring(timelineidx + 2);
						String imsi = imsiline.substring(timelineidx + 2);
						String smcause = smcauseline.substring(timelineidx + 2);
						String details = detailsline.substring(timelineidx + 2);
						String apnreq = apnreqline.substring(timelineidx + 2);
						String apnsub = apnsubline.substring(timelineidx + 2);

//						LOG.debug("timeline:" + time);
//						LOG.debug("nodeline:" + node);
//						LOG.debug("imsiline:" + imsi);
//						LOG.debug("smcause:" + smcause);
//						LOG.debug("details:" + details);
//						LOG.debug("apnreq:" + apnreq);
//						LOG.debug("apnsub:" + apnsub);

						cdr.setCellid(null);
						int idx1 = smcause.indexOf("(");
						int idx2 = smcause.indexOf(")");
						cdr.setErrorcode(smcause.substring(idx1 + 2, idx2));
						
//						System.out.println(cdr.getErrorcode());
						
						cdr.setImsi(imsi);
						cdr.setLac(0);
						cdr.setNettype(2);
						cdr.setOpentime((int) (df.parse(time).getTime() / 1000));
						cdr.setRecordtime(0);// 入库的时候统一计了
						cdr.setReqapnni(apnreq);
						cdr.setSgsnid(null);
						cdr.setSubapnni(apnsub);

						cdrs.add(cdr);
					}
				} catch (Exception e) {
					LOG.error(file + ":第" + i + "个区块解析有误"+e);
//					errorlog(file + ":第" + i + "个区块解析有误" + e);
				}
			}
		} catch (Exception e) {
			LOG.error("解析文件有误"+e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Exception ee) {
					LOG.error(file + "关闭异常"+ee);
//					errorlog(file + "关闭异常" + ee);
				}
		}
		LOG.info(file + "解析时间为:" + (System.currentTimeMillis() - now));
	}

//	public static void errorlog(String msg) {
//		ERRORLOG.println(df.format(new Date()) + "=>" + msg);
//	}

	private static void init() {
		InputStream in = null;
		try {
			in = ReadErrorApns.class.getResourceAsStream("/main/readerrors/read.properties");
			Properties prop = new Properties();
			prop.load(in);
			SRCDIR = prop.getProperty("SRCDIR");
			DESTDIR = prop.getProperty("DESTDIR");
			LOGDIR= prop.getProperty("LOGDIR");
		} catch (Exception e) {
			try {
				if (in != null)
					in.close();
			} catch (Exception ee) {
//				LOG.error("关闭ice.properties异常:" + e);
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		
		SelfLog.LOGDIR=LOGDIR;
		LOG=SelfLog.getInstance();
		
		LOG.info("============================================================");
		
		cdrs = new ArrayList<ErrorApnsCdr>();
//		ERRORLOG = new java.io.PrintWriter(new FileOutputStream("errorread.log", true), true);
		String separator = System.getProperty("file.separator");
		File srcdir = new File(SRCDIR);
		String[] filenames = srcdir.list();
		if (filenames.length > 0) {

			long now = System.currentTimeMillis();
			cdrs = new ArrayList<ErrorApnsCdr>();
			for (String filename : filenames) {
				parseFile(SRCDIR + separator + filename);
			}

			LOG.info("解析完此次获得的文件所花时间为:" + (System.currentTimeMillis() - now));
			now = System.currentTimeMillis();

			save(cdrs);

			// 执行入库的动作

			// 将文件移动到另外一个地方去
			for (String filename : filenames) {

				File srcfile = new File(SRCDIR + separator + filename);
				File destfile = new File(DESTDIR + separator + filename);
				srcfile.renameTo(destfile);
			}

		} else {
			LOG.info("目录下无文件,跳过处理...");
		}

	}
	
	
	/**
	 * 新增cdr到数据库中
	 * 
	 * @param cdr
	 */
	private static void save(List<ErrorApnsCdr> cdrs) {
		Connection con = null;
		PreparedStatement stmt = null;
		int now = (int) (System.currentTimeMillis() / 1000);
		try {
			con = DBUtils.getOracleCon();

			String sql = "insert into CDR_MISTAKE(lac,cellid,sgsnid,reqapnni,subapnni,imsi,nettype,errcode,opentime,recordtime) values(?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			int len = cdrs.size();
			long begin = System.currentTimeMillis();
			for (int i = 0; i < len; i++) {
				ErrorApnsCdr cdr = cdrs.get(i);
				stmt.setLong(1, cdr.getLac());
				stmt.setString(2, cdr.getCellid());
				stmt.setString(3, cdr.getSgsnid());
				stmt.setString(4, cdr.getReqapnni());
				stmt.setString(5, cdr.getSubapnni());
				stmt.setString(6, cdr.getImsi());
				stmt.setInt(7, cdr.getNettype());
				stmt.setString(8, cdr.getErrorcode());
				stmt.setInt(9, cdr.getOpentime());
				stmt.setInt(10, now);
				stmt.addBatch();
				if (((i + 1) % 100) == 0) {
					stmt.executeBatch();
					stmt.clearBatch();
					long time = System.currentTimeMillis();
					LOG.info("插入100条记录的时间是：" + (time - begin));
					begin = time;
				}
			}
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (Exception e) {
			LOG.error("插入数据库错误:"+ e);
			e.printStackTrace();
//			ReadErrorApns.errorlog("插入数据库错误:" + e);
		} finally {
			DBUtils.closeResource(null, stmt, con);
		}
	}
}
