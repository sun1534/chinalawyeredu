/**
 * 
 */
package main.readerrors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.util.DBUtils;

import org.apache.commons.logging.Log;

/**
 * 
 * @author 华锋 Nov 15, 2009-9:01:53 PM
 * 
 */
public class HWChrFileHandle {
	private static final String SEPARATOR = System.getProperty("file.separator");

	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(HWChrFileHandle.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMdd");
	private static String HWCHR_BASE_STR = "========================================================================================";
	private static String HWERROR_BASE_STR = "======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST =========";

	private ErrorFile moveHWFile(File srcfile, String todayDestDirName, String sgsnid, String timestamp) {
		String destFileName = todayDestDirName + SEPARATOR + srcfile.getName() + "_" + sgsnid + "_" + timestamp
				+ ".txt";
		String destFileNameNoChange = todayDestDirName + SEPARATOR + srcfile.getName() + "_" + sgsnid + "_" + timestamp
				+ ".bak";

		String srcFileName = srcfile.getAbsolutePath();
		File destfile = new File(destFileName);
		File destfileNoChange = new File(destFileNameNoChange);

		// 妈妈的，这里convert就不执行,尻，奇怪死了，不知道怎么回事
		HWSessionLogHandle handle = new HWSessionLogHandle(srcFileName, destFileName);
		handle.convert();

		ErrorFile errorfile = new ErrorFile();
		errorfile.setSgsnid(sgsnid);
		errorfile.setDestdir(todayDestDirName);
		errorfile.setDestfile(destfile);
		errorfile.setSrcfilename(srcfile.getName());
		// 移动cdr文件
		srcfile.renameTo(destfileNoChange);
		LOG.info(".chr文件移动到:" + destFileNameNoChange);
		return errorfile;
	}

	/**
	 * flag的作用是取得此批的数据
	 * 
	 * @param destdir
	 * @param flag
	 * @return
	 */
	public Map<String, ErrorFile> getHWFile(String destdirname, final String flag) {

		java.util.Date now = new java.util.Date();
		Map<String, ErrorFile> errorfiles = new HashMap<String, ErrorFile>();
		// List<File> shoulds = new ArrayList<File>();

		File destdir = new File(destdirname);

		File[] files = destdir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith(flag) && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		});

		if (files != null && files.length > 0) {
			for (File file : files) {
				// 201007282210_SGSN7_SGSN20100727182857_20100727183805.chr.txt
				// //生成的文件的格式

				String name = file.getName();
				String[] splits = name.split("_");
				String theflag = splits[0];
				String sgsnid = splits[1];
				String srcfilename = splits[2] + "_" + splits[3] + ".chr";
				String srcfilenameprefix = splits[2]; // 也就是SGSN20100727182857这一截

				ErrorFile errorfile = new ErrorFile();
				errorfile.setSgsnid(sgsnid);
				errorfile.setDestdir(destdirname);
				errorfile.setDestfile(file);
				errorfile.setSrcfilename(srcfilename);
				errorfile.setModified(new java.sql.Timestamp(now.getTime()));

				errorfiles.put(sgsnid + srcfilenameprefix, errorfile);

			}
		}
		return errorfiles;
	}

	/**
	 * 这里的规则是srcdir下有n多个SGSN*的目录,主要是copy这个目录下的数据 这里备份的话，每天1个目录
	 * 
	 * 这里要调用命令,执行转化,转化完毕后才能执行后续的处理
	 * 
	 * 感觉这个是应该要一直循环的,比如每30秒钟轮询一次,而不是每10分钟
	 * 
	 * @param srcdir
	 * @param destdir
	 */
	public Map<String, ErrorFile> copyHWFile(String srcdir, String destdir) {
		java.util.Date today = new java.util.Date();
		String timestamp = dftime.format(today);
		File srcrootdir = new File(srcdir);
		String[] srcchilddirs = srcrootdir.list();
		String todayDestDirName = destdir + SEPARATOR + dfdate.format(today);
		File todayDestDir = new File(todayDestDirName);

		java.util.Date yestarday = main.util.MainStatUtil.getPrevCountDate(1);
		String yestardayDestDirName = destdir + SEPARATOR + dfdate.format(yestarday);
		File yesdir = new File(yestardayDestDirName);
		if (yesdir.exists()) {
			File[] deletefiles = yesdir.listFiles();
			if (deletefiles != null && deletefiles.length > 0) {
				for (File deletefile : deletefiles)
					deletefile.delete();
			}
			boolean b = yesdir.delete();
			LOG.info(yestardayDestDirName + "华为目录删除成功!!!" + b);
		}

		Map<String, ErrorFile> errorfiles = new HashMap<String, ErrorFile>();
		List<File> shoulds = new ArrayList<File>();
		if (!todayDestDir.exists()) {
			boolean b = todayDestDir.mkdirs();
			int k = 0;
			while (!b && (k++) < 10) {
				b = todayDestDir.mkdirs();
				LOG.info("创建华为存储目录失败,重新来过!" + k);
				try {
					Thread.sleep(3000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!b) {
				// LOG.warn("创建存储目录失败,退出系统");
				// System.exit(0);
				throw new RuntimeException("创建华为存储目录失败,退出系统");
			}
			LOG.info("创建华为存储目录成功:" + todayDestDirName);
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
						ErrorFile errorfile = this.moveHWFile(srcfile, todayDestDirName, sgsnid, timestamp);
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
	 * 新增cdr到数据库中
	 * 
	 * @param cdr
	 */
	public void saveHWError(List<ErrorApnsCdr> cdrs) {
		Connection con = null;
		PreparedStatement stmt = null;
		int now = (int) (System.currentTimeMillis() / 1000);
		try {
			con = DBUtils.getOracleCon();

			String sql = "insert into CDR_MISTAKE(lac,cellid,sgsnid,reqapnni,subapnni,imsi,nettype,errcode,opentime,recordtime) values(?,?,?,?,?,?,?,?,?,?)";

			stmt = con.prepareStatement(sql);
			int len = cdrs.size();
			long begin = System.currentTimeMillis();
			List<String> no33sqls = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				ErrorApnsCdr cdr = cdrs.get(i);

				if (cdr.getErrorcode() != null && cdr.getErrorcode().equals("4328")) // 郭建说4328号错误到算到38头上，因此这里统一改下入库的方式
					cdr.setErrorcode("38");
				// 非33的错误同时入另外一张表
				// System.out.println("ErrorCode:"+cdr.getErrorcode());
				if (cdr.getErrorcode() != null && !cdr.getErrorcode().equals("33")
						&& !cdr.getErrorcode().equals("4329") && !cdr.getErrorcode().equals("4328")) {
					String sqlno33 = "insert into CDR_MISTAKE_NO33(lac,cellid,sgsnid,reqapnni,subapnni,imsi,nettype,errcode,opentime,recordtime,msisdn) values("
							+ "0,0,'"
							+ cdr.getSgsnid()
							+ "','"
							+ (cdr.getReqapnni() == null ? "" : cdr.getReqapnni())
							+ "','"
							+ (cdr.getSubapnni() == null ? "" : cdr.getSubapnni())
							+ "','"
							+ cdr.getImsi()
							+ "','"
							+ cdr.getNettype()
							+ "','"
							+ cdr.getErrorcode()
							+ "',"
							+ cdr.getOpentime()
							+ ","
							+ now + ",'" + cdr + "')";
					no33sqls.add(sqlno33);

				}
				if (cdr.getErrorcode() != null) {
					// System.out.println("SGSNID:"+cdr.getSgsnid());

					stmt.setLong(1, cdr.getLac());
					stmt.setString(2, cdr.getCellid());
					stmt.setString(3, cdr.getSgsnid());
					stmt.setString(4, cdr.getReqapnni() == null ? "" : cdr.getReqapnni());
					stmt.setString(5, cdr.getSubapnni() == null ? "" : cdr.getSubapnni());
					stmt.setString(6, cdr.getImsi());
					stmt.setInt(7, cdr.getNettype());
					stmt.setString(8, cdr.getErrorcode());
					stmt.setInt(9, cdr.getOpentime());
					stmt.setInt(10, now);
					stmt.addBatch();
					if (((i + 1) % 300) == 0) {
						stmt.executeBatch();
						stmt.clearBatch();
						long time = System.currentTimeMillis();
						LOG.info("插入300条记录的时间：" + (time - begin));
						begin = time;
					}
				}
			}
			int[] s = stmt.executeBatch();
			stmt.clearBatch();
			// 33的错误同时入到另外一张表
			// for (Object obj : no33sqls) {
			// LOG.debug(obj.toString());
			// }
			main.util.MainStatUtil.executeSql(con, no33sqls);
			LOG.info("最后入库记录数：" + (s != null ? s.length : 0) + ",时间:" + (System.currentTimeMillis() - begin));
		} catch (Exception e) {
			LOG.error("插入数据库错误:", e);
			e.printStackTrace();
			// ReadErrorApns.errorlog("插入数据库错误:" + e);
		} finally {
			DBUtils.closeResource(null, stmt, null);
		}
	}

	public List<ErrorApnsCdr> parseHWErrorFile(ErrorFile file) {
		long now = System.currentTimeMillis();
		java.io.BufferedReader br = null;
		int handledLines = file.getHandleLines();
		LOG.info(file.getDestfile() + "已经处理过" + handledLines + "行数据");
		List<ErrorApnsCdr> cdrs = new ArrayList<ErrorApnsCdr>();
		int lastCount = 0; // 实际处理的个数
		try {
			br = new BufferedReader(new java.io.InputStreamReader(new FileInputStream(file.getDestfile())));

			String line = null;
			ErrorApnsCdr cdr = null;
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
					if (line.equals(HWERROR_BASE_STR)) {

						if (i >= handledLines) {
							// if(cdr!=null)

							lastCount++;
							cdr = new ErrorApnsCdr();
							cdr.setSgsnid(file.getSgsnid());
							cdrs.add(cdr);
						}
						i++;

					}
					int idx = line.indexOf(":");
					if (idx == -1)
						continue;

					if (cdr != null) {
						if (line.startsWith("Time")) {
							String time = line.substring(idx + 2).trim();
							cdr.setOpentime((int) (df.parse(time).getTime() / 1000));

						} else if (line.startsWith("Node")) {

						} else if (line.startsWith("IMSI")) {
							cdr.setImsi(line.substring(idx + 2).trim());
						} else if (line.startsWith("MSISDN")) {
							cdr.setMsisdn(line.substring(idx + 2).trim());
						} else if (line.startsWith("SM Cause")) {
							int idx1 = line.indexOf("(");
							int idx2 = line.indexOf(")");
							String errorcode = line.substring(idx1 + 2, idx2).trim();
							cdr.setErrorcode(errorcode);
						} else if (line.startsWith("Details")) {

						} else if (line.startsWith("PDP Addr")) {
							cdr.setPdpaddr(line.substring(idx + 2).trim());
						} else if (line.startsWith("APN Used")) {
							cdr.setUsedapn(line.substring(idx + 2).trim());
						} else if (line.startsWith("NSEI")) {
							cdr.setNsei(line.substring(idx + 2).trim());
						} else if (line.startsWith("GGSN Addr")) {
							cdr.setGgsnaddr(line.substring(idx + 2).trim());
						} else if (line.startsWith("APN Req")) {
							cdr.setReqapnni(line.substring(idx + 2).trim());
						} else if (line.startsWith("APN Req")) {
							cdr.setReqapnni(line.substring(idx + 2).trim());
						} else if (line.startsWith("APN Sub")) {
							if (line.length() < idx + 2)
								cdr.setSubapnni("");
							else
								cdr.setSubapnni(line.substring(idx + 2).trim());
						} else if (line.startsWith("APN") && !line.startsWith("APN Req") && !line.startsWith("APN Sub")) {
							if (line.length() < idx + 2)
								cdr.setSubapnni("");
							else
								cdr.setReqapnni(line.substring(idx + 2).trim());
						}
					}
				} catch (Exception e) {
					LOG.error(file.getDestfile() + ":第" + i + "个区块解析有误", e);
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

	public List<HWChrLog> parseHWChrFile(ErrorFile file) {
		long now = System.currentTimeMillis();
		java.io.BufferedReader br = null;
		int handledLines = file.getHandleLines();
		LOG.info(file.getDestfile() + "已经处理过" + handledLines + "行数据");
		List<HWChrLog> cdrs = new ArrayList<HWChrLog>();
		int lastCount = 0; // 实际处理的个数
		try {
			br = new BufferedReader(new java.io.InputStreamReader(new FileInputStream(file.getDestfile())));

			String line = null;
			HWChrLog cdr = null;
			int i = 0;
			while ((line = br.readLine()) != null) {

				// ========================================================================================
				//
				// [ UTC 日期/时间 ] 2010-07-27 17:08:03
				// [ MSISDN ] 8615215161033
				// [ IMSI ] 460022151600264
				// [ IMEISV ] 3585920104672887
				// [ 终端型号 ]
				// [ 流程ID ] MS_GPRS_DETACH
				// [ 触发原因 ] MS
				// [ 时延 ] 10
				// [ 外部原因 ] UNDEFINED
				// [ 内部原因 ] UNDEFINED
				// [ APN ]
				// [ 允许APN ]
				// [ 小区编码 ] 331D:01:4E68
				// [ 小区名称 ]
				// [ SGSN ] Test Sgsn 1
				// [ GGSN ]
				// [ 旧小区编码 ] 331D:01:4E68
				// [ 旧小区名称 ]
				// [ 旧侧SGSN地址 ]
				// [ 框号 ] 8
				// [ 槽号 ] 5

				try {
					if (line.equals(HWCHR_BASE_STR)) {
						if (i >= handledLines) {
							// if(cdr!=null)
							lastCount++;
							cdr = new HWChrLog();
							cdr.setFilesgsn(file.getSgsnid());
							cdrs.add(cdr);
						}
						i++;

					}
					int idx = line.indexOf("]");
					if (idx == -1)
						continue;

					if (cdr != null) {
						if (line.indexOf("UTC 日期/时间") != -1) {
							String time = line.substring(idx + 1).trim();
							cdr.setTime((int) (df.parse(time).getTime() / 1000));
						} else if (line.indexOf("MSISDN") != -1) {
							cdr.setMsisdn(line.substring(idx + 1).trim());
						} else if (line.indexOf("IMSI") != -1) {
							cdr.setImsi(line.substring(idx + 1).trim());
						} else if (line.indexOf("IMEISV") != -1) {
							cdr.setImei(line.substring(idx + 1).trim());
						} else if (line.indexOf("流程ID") != -1) {
							cdr.setFlowid(line.substring(idx + 1).trim());
						} else if (line.indexOf("触发原因") != -1) {
							cdr.setTrigreason(line.substring(idx + 1).trim());
						} else if (line.indexOf("时延") != -1) {
							cdr.setLazytime(line.substring(idx + 1).trim());
						} else if (line.indexOf("外部原因") != -1) {
							cdr.setOutreason(line.substring(idx + 1).trim());
						} else if (line.indexOf("内部原因") != -1) {
							cdr.setInnerreason(line.substring(idx + 1).trim());
						} else if (line.indexOf("APN") != -1 && line.indexOf("允许APN") == -1) {

							cdr.setApn(line.substring(idx + 1).trim());
						} else if (line.indexOf("允许APN") != -1) {

							cdr.setAllowapn(line.substring(idx + 1).trim());
						} else if (line.indexOf("小区编码") != -1 && line.indexOf("旧小区编码") == -1) {

							cdr.setCellid(line.substring(idx + 1).trim());
						} else if (line.indexOf("SGSN") != -1 && line.indexOf("旧侧SGSN地址") == -1) {
							// System.out.println(line);
							cdr.setSgsnid(line.substring(idx + 1).trim());
						} else if (line.indexOf("GGSN") != -1) {
							cdr.setGgsn(line.substring(idx + 1).trim());
						} else if (line.indexOf("旧小区编码") != -1) {

							cdr.setOldcellid(line.substring(idx + 1).trim());
						} else if (line.indexOf("旧侧SGSN地址") != -1) {
							cdr.setOldsgsnid(line.substring(idx + 1).trim());
						} else if (line.indexOf("框号") != -1) {

							cdr.setKuang(line.substring(idx + 1).trim());
						} else if (line.indexOf("槽号") != -1) {

							cdr.setCao(line.substring(idx + 1).trim());
						}
					}
				} catch (Exception e) {
					System.out.println(file.getDestfile() + ":第" + i + "个区块解析有误" + e);
				}
			}
			file.setHandleLines(handledLines + lastCount);
			System.out.println(file.getDestfile() + "解析时间为:" + (System.currentTimeMillis() - now) + "总共:" + i
					+ "区块,实际处理" + lastCount + "块," + cdrs.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("解析文件异常:" + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (Exception ee) {
					System.out.println(file + "关闭异常:" + ee);
				}
		}

		return cdrs;
	}

	public void truncateTable(String table) throws SQLException {
		System.out.println("开始truncate table " + table);
		String sql = "truncate table " + table;
		Connection con = null;
		java.sql.PreparedStatement stmt = null;
		try {
			long now = System.currentTimeMillis();
			con = DBUtils.getOracleCon();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);
			System.out.println("结束truncate table " + table + ",时间:" + (System.currentTimeMillis() - now));
		} catch (Exception e) {
			System.out.println("truncate table " + table + "异常::" + e);
		} finally {
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// 每小时的统计数据，key为flowid,sgsnid,kuang,cao(有cellid的话,太大了,如果根据cellid查的话,到日志表里去查)
	// Key以|隔开
	private Map<String, Integer> flowidstat = new HashMap<String, Integer>();

	public Map<String, Integer> getFlowidstat() {
		return this.flowidstat;
	}

	private Map<String, Integer> flowiderrortat = new HashMap<String, Integer>();

	public Map<String, Integer> getFlowiderrorstat() {
		return this.flowiderrortat;
	}

	/**
	 * 插入到hw的chrlogs中
	 * 
	 * 
	 * @param cdrs
	 */
	public void saveHWChr(Connection con, List<HWChrLog> cdrs, int weekday, int thehour, String thedate)
			throws SQLException {

		String table = "hwchr_log_" + weekday;

		String sql = "insert into "
				+ table
				+ " (TTIME,MSISDN,IMSI,IMEI,FLOWID,TRIGREASON,LAZYTIME,"
				+ "OUTREASON,INNERREASON,APN,ALLOWAPN,CELLID,SGSNID,GGSN,OLDCELLID,OLDSGSNID,KUANG,CAO,FILESGSN,thehour,thedate)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		java.sql.PreparedStatement stmt = null;
		try {

			stmt = con.prepareStatement(sql);
			int len = cdrs.size();
			for (int i = 0; i < len; i++) {
				HWChrLog log = cdrs.get(i);
				stmt.setLong(1, log.getTime());
				stmt.setString(2, log.getMsisdn());
				stmt.setString(3, log.getImsi());
				stmt.setString(4, log.getImei());
				stmt.setString(5, log.getFlowid());
				stmt.setString(6, log.getTrigreason());
				stmt.setString(7, log.getLazytime());
				stmt.setString(8, log.getOutreason());
				stmt.setString(9, log.getInnerreason());
				stmt.setString(10, log.getApn());
				stmt.setString(11, log.getAllowapn());
				stmt.setString(12, log.getCellid());
				stmt.setString(13, log.getSgsnid());
				stmt.setString(14, log.getGgsn());
				stmt.setString(15, log.getOldcellid());
				stmt.setString(16, log.getOldsgsnid());
				stmt.setString(17, log.getKuang());
				stmt.setString(18, log.getCao());
				stmt.setString(19, log.getFilesgsn());			
				stmt.setInt(20, thehour);
				stmt.setString(21, thedate);
				
				String flowidkey = log.getFilesgsn() + "|" + log.getKuang() + "|" + log.getCao() + "|"
						+ log.getFlowid() + "|O";
				int cnt = 1;
				if (this.flowidstat.containsKey(flowidkey)) {
					cnt = flowidstat.get(flowidkey) + 1;
					flowidstat.remove(flowidkey);
				}
				flowidstat.put(flowidkey, cnt);
				// 插入flowid的统计表
				String flowiderrorkey = log.getInnerreason() + "|" + log.getOutreason() + "|" + log.getFlowid() + "|O";
				int errorcnt = 1;
				if (this.flowiderrortat.containsKey(flowiderrorkey)) {
					errorcnt = flowiderrortat.get(flowiderrorkey) + 1;
					flowiderrortat.remove(flowiderrorkey);
				}
				flowiderrortat.put(flowiderrorkey, errorcnt);
				// 插入flowid的错误信息统计表
				stmt.addBatch();
				if ((i + 1) % 1000 == 0) {
					try {
						long now = System.currentTimeMillis();
						stmt.executeBatch();
						stmt.clearBatch();
						// System.out.println("插入CHR日志1000条的时间为:" +
						// (System.currentTimeMillis() - now));
					} catch (Exception e) {
						System.out.println("数据插入有误" + e);
					}
				}
			}
			long now = System.currentTimeMillis();
			stmt.executeBatch();
			stmt.clearBatch();
			System.out.println("最后入库时间花费为:" + (System.currentTimeMillis() - now));

		} catch (Exception e) {
			System.out.println("数据插入有误" + e);
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	/**
	 * 按小时统计流程情况
	 * 
	 * @param con
	 * @param thedate
	 * @param hour
	 * @throws SQLException
	 */
	public void saveFlowidHourDatas(Connection con, String thedate, int thehour) throws SQLException {
		String sql = "insert into hwchr_flowid_stat(statdate,stattime,statflag,flowid,sgsn,kuang,cao,flowcount) values(?,?,0,?,?,?,?,?)";

		java.sql.PreparedStatement stmt = null;
		try {
			long now = System.currentTimeMillis();
			stmt = con.prepareStatement(sql);
			java.util.Iterator<String> iterator = flowidstat.keySet().iterator();
			int i = 0;
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				int count = flowidstat.get(key);
				i++;
				String[] s = key.split("\\|");
				String sgsn = s[0];
				String kuang = s[1];
				String cao = s[2];
				String flowid = s[3];

				stmt.setString(1, thedate);
				stmt.setInt(2, thehour);
				stmt.setString(3, flowid);
				stmt.setString(4, sgsn);
				stmt.setString(5, kuang);
				stmt.setString(6, cao);
				stmt.setInt(7, count);
				stmt.addBatch();
			}

			int[] s = stmt.executeBatch();
			System.out
					.println("flowid的小时统计入库OK:" + (System.currentTimeMillis() - now) + ",个数:" + (i) + "=>" + s.length);
		} catch (Exception e) {
			System.out.println("flowid的小时统计错误:" + e);
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}
	
	/**
	 * 统计1天的情况
	 * @param con
	 */
	public void statFlowidDay(Connection con,String thedate) throws SQLException {
		String sql="insert into hwchr_flowid_stat(statdate,stattime,statflag,flowid,sgsn,kuang,cao,flowcount) select '"+thedate+"',0,1,flowid,sgsn,kuang,cao,sum(flowcount) from hwchr_flowid_stat  where statflag=0 and statdate='"+thedate+"' group by flowid,sgsn,kuang,cao";
		String errorsql="insert into hwchr_flowid_error_stat(statdate,stattime,statflag,flowid,innerreason,outreason,errorcount) select '"+thedate+"',0,1,flowid,innerreason,outreason,sum(errorcount) from hwchr_flowid_error_stat  where statflag=0 and statdate='"+thedate+"' group by flowid,innerreason,outreason";
		Statement stmt=null;
		try{
			long now=System.currentTimeMillis();
			stmt=con.createStatement();
			stmt.addBatch(sql);
			stmt.addBatch(errorsql);
			stmt.executeBatch();
			System.out.println("按天统计chr日志完成,花费:"+(System.currentTimeMillis()-now));
		}finally{
			if(stmt!=null)stmt.close();
		}
		
	}

	/**
	 * 按小时统计错误情况
	 * 
	 * @param con
	 * @param thedate
	 * @param hour
	 * @throws SQLException
	 */
	public void saveFlowidErrorHourDatas(Connection con, String thedate, int thehour) throws SQLException {
		String sql = "insert into hwchr_flowid_error_stat(statdate,stattime,statflag,flowid,innerreason,outreason,errorcount) values(?,?,0,?,?,?,?)";

		java.sql.PreparedStatement stmt = null;
		try {
			long now = System.currentTimeMillis();
			stmt = con.prepareStatement(sql);
			java.util.Iterator<String> iterator = flowiderrortat.keySet().iterator();
			int i = 0;
			// String flowiderrorkey = log.getInnerreason() + "|" +
			// log.getOutreason()+ "|"
			// + log.getFlowid() + "|O";
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				int count = flowiderrortat.get(key);
				i++;
				String[] s = key.split("\\|");
				String inner = s[0];
				String out = s[1];

				String flowid = s[2];

				stmt.setString(1, thedate);
				stmt.setInt(2, thehour);
				stmt.setString(3, flowid);
				stmt.setString(4, inner);
				stmt.setString(5, out);
				stmt.setInt(6, count);
				stmt.addBatch();
			}

			int[] s = stmt.executeBatch();
			System.out.println("flowid的错误的小时统计入库OK:" + (System.currentTimeMillis() - now) + ",个数:" + (i) + "=>"
					+ s.length);
		} catch (Exception e) {
			System.out.println("flowid的错误的小时统计错误:" + e);
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public static void main(String[] args) throws Exception {
		File file = new File("e:\\SGSN920100727234152.txt");
		ErrorFile ef = new ErrorFile();
		ef.setDestdir("e:\\");
		ef.setDestfile(file);
		ef.setHandleLines(0);
		ef.setSgsnid("SGSN09");
		ef.setSrcfilename("e:\\SGSN920100727234152.txt");
		HWChrFileHandle fh = new HWChrFileHandle();
		List<HWChrLog> list = fh.parseHWChrFile(ef);
		long now = System.currentTimeMillis();
		for (HWChrLog log : list) {
			// System.out.println(log.getTime()+"===>"+log+"==>"+log.getAllowapn());
		}
		System.out.println(System.currentTimeMillis() - now);

	}
}
