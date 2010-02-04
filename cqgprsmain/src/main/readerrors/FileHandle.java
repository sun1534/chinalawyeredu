/**
 * 
 */
package main.readerrors;

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

import main.util.DBUtils;

import org.apache.commons.logging.Log;

/**
 * 
 * @author 华锋 Nov 15, 2009-9:01:53 PM
 * 
 */
public class FileHandle {
	private static final String SEPARATOR = System.getProperty("file.separator");

	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(FileHandle.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMdd");
	private static String BASE_STR = "======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST =========";

	private ErrorFile moveFile(File srcfile, String todayDestDirName, String sgsnid, String timestamp) {
		String newFileName = srcfile.getName() + "_" + sgsnid + "_" + timestamp;

		File destfile = new File(todayDestDirName + SEPARATOR + newFileName);
		srcfile.renameTo(destfile);

		ErrorFile errorfile = new ErrorFile();
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
	public Map<String, ErrorFile> copyFile(String srcdir, String destdir) {
		java.util.Date today = new java.util.Date();
		String timestamp = dftime.format(today);
		File srcrootdir = new File(srcdir);
		String[] srcchilddirs = srcrootdir.list();
		String todayDestDirName = destdir + SEPARATOR + dfdate.format(today);
		File todayDestDir = new File(todayDestDirName);

		Map<String, ErrorFile> errorfiles = new HashMap<String, ErrorFile>();
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
						ErrorFile errorfile = this.moveFile(srcfile, todayDestDirName, sgsnid, timestamp);
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

	// public List<ErrorApnsCdr> parseFile(ErrorFile file) {
	// long now = System.currentTimeMillis();
	// java.io.BufferedReader br = null;
	// int handledLines = file.getHandleLines();
	// List<ErrorApnsCdr> cdrs=new ArrayList<ErrorApnsCdr>();
	// try {
	// br = new BufferedReader(new java.io.InputStreamReader(new
	// FileInputStream(file.getDestfile())));
	//
	// String line = null;
	//
	// int i = 0;
	// while ((line = br.readLine()) != null) {
	//
	// // ======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST
	// // =========
	// // Time : 2009-11-04 12:17:23
	// // Node : e_Erlang__Global_pm1_16_2_1@eqm01s10p2
	// // IMSI : 460007611131758
	// // SM Cause : Service Option not Subscribed (#33)
	// // Details : Service option not subscribed
	// // APN Req. : cmwap
	// // APN Sub. : cmmm
	//
	// try {
	// if (line.equals(BASE_STR)) {
	// i++;
	// ErrorApnsCdr cdr = new ErrorApnsCdr();
	// String timeline = br.readLine();
	//
	// int timelineidx = timeline.indexOf(":");
	// while (timelineidx == -1) {
	//
	// timeline = br.readLine();
	// timelineidx = timeline.indexOf(":");
	// }
	//
	// String nodeline = br.readLine();
	// int nodelineidx = nodeline.indexOf(":");
	// while (nodelineidx == -1) {
	//
	// timeline = timeline + nodeline;
	// nodeline = br.readLine();
	// nodelineidx = nodeline.indexOf(":");
	// }
	// String imsiline = br.readLine();
	// int imsilineidx = imsiline.indexOf(":");
	// while (imsilineidx == -1) {
	//
	// nodeline = nodeline + imsiline;
	// imsiline = br.readLine();
	// imsilineidx = imsiline.indexOf(":");
	// }
	// String smcauseline = br.readLine();
	// int smcauseidx = smcauseline.indexOf(":");
	// while (smcauseidx == -1) {
	//
	// imsiline = imsiline + smcauseline;
	// smcauseline = br.readLine();
	// smcauseidx = smcauseline.indexOf(":");
	// }
	// String detailsline = br.readLine();
	// int detailsidx = detailsline.indexOf(":");
	// while (detailsidx == -1) {
	// smcauseline = smcauseline + detailsline;
	// detailsline = br.readLine();
	// detailsidx = detailsline.indexOf(":");
	// }
	// String apnreqline = br.readLine();
	// int apnreqidx = apnreqline.indexOf(":");
	// while (apnreqidx == -1) {
	// detailsline = detailsline + apnreqline;
	// apnreqline = br.readLine();
	// apnreqidx = apnreqline.indexOf(":");
	// }
	//
	// String apnsubline = br.readLine();
	// int apnsubidx = apnsubline.indexOf(":");
	// while (apnsubidx == -1) {
	// // System.out.println("这里执行了啊："+apnsub+"=="+apnsubidx);
	// apnreqline = apnreqline + apnsubline;
	// apnsubline = br.readLine();
	// apnsubidx = timeline.indexOf(":");
	// }
	//
	// String smcause = smcauseline.substring(smcauseidx + 2).trim();
	// int idx1 = smcause.indexOf("(");
	// int idx2 = smcause.indexOf(")");
	// System.out.println(smcause+",,"+idx1+",,,,"+idx2);
	// String errorcode = smcause.substring(idx1 + 2, idx2).trim();
	// if (errorcode.indexOf("27") != -1 || errorcode.indexOf("38") != -1) {
	// // 27的话多一行APN Used : yiheng01.gd.mnc000.mcc460.gprs
	// // 38的话有些多一行,有些多二行
	// // 妈的,33和27号错误还有个msisdn
	// String temp = br.readLine(); // 读了这一行,其实可以不管哦
	// LOG.warn(errorcode + "," + temp);
	// }
	//
	// if (i >= handledLines) {
	// String time = timeline.substring(timelineidx + 2).trim();
	// String node = nodeline.substring(nodelineidx + 2).trim();
	// String imsi = imsiline.substring(imsilineidx + 2).trim();
	//
	// String details = detailsline.substring(detailsidx + 2).trim();
	// String apnreq = apnreqline.substring(apnreqidx + 2).trim();
	// String apnsub = apnsubline.substring(apnsubidx + 2).trim();
	//
	// // LOG.debug("timeline:" + time);
	// // LOG.debug("nodeline:" + node);
	// // LOG.debug("imsiline:" + imsi);
	// // LOG.debug("smcause:" + smcause);
	// // LOG.debug("details:" + details);
	// // LOG.debug("apnreq:" + apnreq);
	// // LOG.debug("apnsub:" + apnsub);
	//
	// cdr.setCellid(null);
	//
	// cdr.setErrorcode(errorcode);
	//
	// // System.out.println(cdr.getErrorcode());
	//
	// cdr.setImsi(imsi);
	// cdr.setLac(0);
	// cdr.setNettype(2);
	// cdr.setOpentime((int) (df.parse(time).getTime() / 1000));
	// cdr.setRecordtime(0);// 入库的时候统一计了
	// cdr.setReqapnni(apnreq);
	// cdr.setSgsnid(null);
	// cdr.setSubapnni(apnsub);
	//
	// cdrs.add(cdr);
	// }
	// }
	// } catch (Exception e) {
	// LOG.error(file.getDestfile() + ":第" + i + "个区块解析有误" , e);
	// }
	//				
	// }
	// } catch (Exception e) {
	// LOG.error("解析文件有误" , e);
	// } finally {
	// if (br != null)
	// try {
	// br.close();
	// } catch (Exception ee) {
	// LOG.error(file + "关闭异常" , ee);
	// }
	// }
	// LOG.info(file + "解析时间为:" + (System.currentTimeMillis() - now));
	// return cdrs;
	// }

	public List<ErrorApnsCdr> parseFile(ErrorFile file) {
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
					if (line.equals(BASE_STR)) {

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

	/**
	 * 新增cdr到数据库中
	 * 
	 * @param cdr
	 */
	public void save(List<ErrorApnsCdr> cdrs) {
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
				// 非33的错误同时入另外一张表
				if (!cdr.getErrorcode().equals("33")) {
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
							+ now + ",'" + cdr.getMsisdn() + "')";
					no33sqls.add(sqlno33);

				}
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
			int[] s = stmt.executeBatch();
			stmt.clearBatch();
			// 33的错误同时入到另外一张表
			for (Object obj : no33sqls) {
				LOG.debug(obj.toString());
			}
			main.util.MainStatUtil.executeSql(con, no33sqls);
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
		ErrorFile ef = new ErrorFile();
		ef.setDestdir("d:\\");
		ef.setDestfile(file);
		ef.setHandleLines(0);
		ef.setSgsnid("SGSN09");
		ef.setSrcfilename("d:\\outer.txt");
		main.readerrors.FileHandle fh = new main.readerrors.FileHandle();
		fh.parseFile(ef);

	}
}
