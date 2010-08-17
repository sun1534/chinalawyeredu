/**
 * CDRAlarm.java
 */
package main.cdralarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * 执行告警
 * 
 * @author 华锋 Jul 15, 20102:57:33 PM
 * 
 */
public class CDRAlarm {
	private static String COMMONAD = "/export/home/jf/GPRS/script/cdralarm.sh";
	private static String COMMONAD_GGSN = "/export/home/jf/GPRS/script/ggsnalarm.sh";
	private static String COMMONAD_GGSN_FTP = "/export/home/jf/GPRS/script/ggsnalarm_ftp.sh";
	public static String MOBILES = "";
	private static DateFormat dfday = new java.text.SimpleDateFormat("yyyyMMdd");
	private static DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat dfmin = new java.text.SimpleDateFormat("yyyyMMddHHmm");
	private static DateFormat dfsec1 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	private static String GGSN_LAST_FILE = "";
	private static String CG03_WORKING_LAST_FILE = "";
	private static String CG03_GGSN_LAST_FILE = "";
	private static String CG03_LAST_FILE = "";

	private static void init() {
		try {
			Properties p = new Properties();
			p.load(CDRAlarm.class.getResourceAsStream("mobile.properties"));
			MOBILES = p.getProperty("mobiles");
		} catch (IOException e) {
			MOBILES = "13808304084,13983240200,13808304521,13608365966,13983242210,13983699454,13647619953";
			System.out.println("初始化失败!" + e);
		}
	}

	/**
	 * ggsn的ftp方式获取话单，看是否有
	 * 
	 * @return
	 */
	private static boolean ggsnalarmftp() {
		// 先删除那个日志文件
		String logfile = "/export/home1/smlog/GGSNALARM/log/ggsnalarm.log";
		File file = new File(logfile);
		if (file.exists()) {
			boolean s = file.delete();
			System.out.println("成功删除文件:" + logfile + "==>" + s);
		}

		int i = 1;// 连续4次，每次间隔20秒，要是一直没有，那就告警
		boolean isok = false;

		while (!isok && i <= 10) {
			try {
				Runtime runtime = Runtime.getRuntime();
				Process p = runtime.exec(COMMONAD_GGSN_FTP);
				System.out.println("第" + i + "次执行命令:" + COMMONAD_GGSN_FTP);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
				}
				Thread.sleep(1500L);
				br.close();

				java.io.BufferedReader logbr = new java.io.BufferedReader(new InputStreamReader(new FileInputStream(
						new File(logfile))));
				while ((line = logbr.readLine()) != null) {
					if (line.indexOf("GGSNCQ02_") != -1) {
						isok = true; // 找到了
						GGSN_LAST_FILE = line;
					}
				}
				logbr.close();
				if (!isok)
					Thread.sleep(20 * 1000);
				// 休息20秒
				i++;
			} catch (Exception e) {
				System.out.println(COMMONAD_GGSN_FTP + "执行失败!" + e);
			}
		}
		return isok;
	}

	public static boolean ggsnalarm() {
		System.out.println("GGSN02的登录和命令验证第一次执行::::::::");
		String ggsn = ggsnalarmssh();
		String[] s = ggsn.split(",");
		boolean canlogin = Boolean.parseBoolean(s[0]);
		boolean showstorage = Boolean.parseBoolean(s[1]);
		int i = 0;
		while ((!canlogin || !showstorage) && (i++ < 3)) { //
			System.out.println("GGSN02的登录和命令验证再次执行:::" + i);
			ggsn = ggsnalarmssh();
			s = ggsn.split(",");
			if (!canlogin)
				canlogin = Boolean.parseBoolean(s[0]);
			if (!showstorage)
				showstorage = Boolean.parseBoolean(s[1]);
		}

		if (!canlogin) {
			// 不能登录,下面的不再跑了
			SmsSend.send("", "GGSN02在" + dfsec.format(new Date()) + "无法登陆");
			System.out.println("GGSN02在" + dfsec.format(new Date()) + "无法登陆");
			return false;
		} else if (!showstorage) {
			SmsSend.send("", "GGSN02在" + dfsec.format(new Date()) + "时候的show system storage指令无响应");
			System.out.println("GGSN02在" + dfsec.format(new Date()) + "时候的show system storage指令无响应");
			return false;
		} else if (!ggsnalarmftp()) {
			SmsSend.send("", "GGSN02在" + dfsec.format(new Date()) + "时候时候无法获取话单文件");
			System.out.println("GGSN02在" + dfsec.format(new Date()) + "时候时候无法获取话单文件");
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 看能否登录以及那个show system storage是否有异常
	 * 
	 * @param ggsn
	 */
	public static String ggsnalarmssh() {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(COMMONAD_GGSN);
			System.out.println("执行命令:" + COMMONAD_GGSN);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			boolean canlogin = true;
			boolean showstorage = false;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("Login incorrect") != -1) {
					canlogin = false;
				}
				if (line.indexOf("Filesystem") != -1 || line.indexOf("/dev/") != -1) {
					System.out.println("LINE===>" + line);
					showstorage = true;
				}
			}
			br.close();
			return canlogin + "," + showstorage;
		} catch (Exception e) {
			System.out.println(COMMONAD_GGSN + "执行失败!" + e);
			return "false,false";
		}
	}

	/**
	 * 检测cg03设备上的数据
	 * 
	 * @return
	 */
	private static boolean cg03alarm() {
		Date now = new Date();
		long nowtime = now.getTime();
		String today = dfday.format(now);
		String exedate = today;
		String yestarday = dfday.format(nowtime - 24 * 60 * 60 * 1000);
		boolean canlogin = false;

		// Timestamp workingtimestamp = new java.sql.Timestamp(nowtime - 10 * 60
		// * 1000);// working目录就10分钟内
		Timestamp ggsntimestamp = new java.sql.Timestamp(nowtime - 25 * 60 * 1000);// 日期目录就25分钟内
		String ggsntime = dfmin.format(ggsntimestamp);
		// String workingtime = dfmin.format(workingtimestamp);
		if (!ggsntime.startsWith(today)) {
			exedate = yestarday;
		}
		System.out.println("当天：" + today + ",昨天：" + yestarday + ",执行时间：" + exedate);

		// String command = COMMONAD + " " + exedate + " " + workingtime + " " +
		// ggsntime;
		String command = COMMONAD + " " + exedate;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(command);
			System.out.println("执行命令:" + command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int workingline = 0;
			int ggsnline = Integer.MAX_VALUE;
			int i = 0;
			while ((line = br.readLine()) != null) {

				if (line.indexOf("Last login") != -1) {
					canlogin = true;
				}
				if (line.indexOf("/STS/working") != -1) {
					System.out.println("workingline====" + i);
					workingline = i;
				}
				if (line.indexOf("-GGSNCQ02.J20R4") != -1) {
					System.out.println("ggsnline====" + i);
					ggsnline = i;
				}
				if (line.indexOf("NO-LABEL") != -1) {
					System.out.println("line::::::" + line);
					if (i >= workingline && i < ggsnline)
						CG03_WORKING_LAST_FILE = line;
					if (i >= ggsnline)
						CG03_GGSN_LAST_FILE = line;
				}
				i++;
			}

			System.out.println("CG03_WORKING_LAST_FILE=" + CG03_WORKING_LAST_FILE);
			System.out.println("CG03_GGSN_LAST_FILE=" + CG03_GGSN_LAST_FILE);

			if (CG03_WORKING_LAST_FILE != null && !CG03_WORKING_LAST_FILE.equals(""))
				CG03_LAST_FILE = CG03_WORKING_LAST_FILE;
			else if (CG03_GGSN_LAST_FILE != null && !CG03_GGSN_LAST_FILE.equals(""))
				CG03_LAST_FILE = CG03_GGSN_LAST_FILE;

			br.close();
			System.out.println("CG03设备的第" + i + "次判断!!!!!!");
			if (!canlogin) {
				// 不能登录,下面的不再跑了
				SmsSend.send("", "CG03在" + dfsec.format(new Date()) + "无法登陆");
				System.out.println("CG03在" + dfsec.format(new Date()) + "无法登陆");
				return false;
			}

			// 这里对CG03_LAST_FILE进行判断
			if (CG03_LAST_FILE == null || CG03_LAST_FILE.equals("")) {// 两个地方都没文件的话,当然不去查了啊
				SmsSend.send("", "CG03在" + dfsec.format(new Date()) + "无话单产生");
				System.out.println("CG03在" + dfsec.format(new Date()) + "无话单产生");
				return false;
			}

			int idx = CG03_LAST_FILE.lastIndexOf(today);
			if (idx == -1)
				idx = CG03_LAST_FILE.lastIndexOf(yestarday);
			if (idx != -1) {

				// GGSNCQ02.J20R4:GGSNCQ02_20100715232545_37350:20100715:232529:031:NO-LABEL

				String date = CG03_LAST_FILE.substring(idx, idx + 8);
				String time = CG03_LAST_FILE.substring(idx + 9, idx + 9 + 6);

				boolean isok = false;
				long filetime = dfsec1.parse(date + time).getTime(); // 文件的时间
				if (CG03_WORKING_LAST_FILE != null && !CG03_WORKING_LAST_FILE.equals("")) {// 看是否10分钟内
					if (nowtime - filetime < 10 * 60 * 1000)
						isok = true;

					System.out.println("working中的时间差距为::" + ((nowtime - filetime) / 1000) + "秒");
				} else if (CG03_GGSN_LAST_FILE != null && !CG03_GGSN_LAST_FILE.equals("")) {// 看是否25分钟内
					if (nowtime - filetime < 25 * 60 * 1000)
						isok = true;

					System.out.println("ggsn中的时间差距为::" + ((nowtime - filetime) / 1000) + "秒");
				}
				if (!isok) {
					SmsSend.send("", "CG03在" + dfsec.format(new Date()) + "无话单产生,最近产生时间为:" + (date + time));
					System.out.println("CG03在" + dfsec.format(new Date()) + "无话单产生,最近为:" + CG03_LAST_FILE);
				}
				return isok;

			} else {
				SmsSend.send("", "CG03在" + dfsec.format(new Date()) + "话单文件有异常");
				System.out.println("CG03在" + dfsec.format(new Date()) + "话单文件有异常：" + CG03_LAST_FILE);
				return false;
			}

		} catch (Exception e) {
			System.out.println(command + "执行失败!" + e);
		}

		// if (!shouldnext) {
		// System.out.println(dfsec.format(new Date()) + "检查CG03的话单正常");
		// return true;
		// }
		// 这里

		// if (shouldnext) {
		// // 找到上一次的时间
		// // *点*分CG03上无GGSN02的话单产生，最后一个话单为“话单名
		// SmsSend.send("", "CG03在" + dfsec.format(new Date()) + "无话单产生");
		// System.out.println("CG03在" + dfsec.format(new Date()) + "无话单产生");
		// return false;
		// }
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===================================================");
		init();
		System.out.println("获取到需要通知的手机号码");
		boolean ggsnok = ggsnalarm();
		System.out.println("本次GGSN设备状况检测完毕");
		boolean cg03ok = cg03alarm();
		System.out.println("本次CG03的设备检测完毕");

		String filepath = CDRAlarm.class.getResource("timeset.txt").getPath();

		java.io.BufferedReader timesettxt = new java.io.BufferedReader(new InputStreamReader(new FileInputStream(
				filepath)));

		String ggsnline = timesettxt.readLine();
		String cg03line = timesettxt.readLine();
		timesettxt.close();

		// 存储当前时间,总次数,总成功数,总失败数,上次发短信到现在的成功数
		String[] cg03s = cg03line.split(",");
		String[] ggsns = ggsnline.split(",");
		int cgtotalcnt = Integer.parseInt(cg03s[1]);
		int cgtotalsucccnt = Integer.parseInt(cg03s[2]);
		int cgtotalerrorcnt = Integer.parseInt(cg03s[3]);
		int cgsmscnt = Integer.parseInt(cg03s[4]);

		int ggtotalcnt = Integer.parseInt(ggsns[1]);
		int ggtotalsucccnt = Integer.parseInt(ggsns[2]);
		int ggtotalerrorcnt = Integer.parseInt(ggsns[3]);
		int ggsmscnt = Integer.parseInt(ggsns[4]);

		String content = "";
		if (cgsmscnt == 6) {
			cgsmscnt = 0;
			// 发送平安短信
			content += "CG03运行正常";
		} else {
			cgsmscnt++;
		}

		if (ggsmscnt == 6) {
			ggsmscnt = 0;
			// 发送平安短信
			if (!content.equals("")) {
				int _idx = GGSN_LAST_FILE.indexOf("_");
				content = "GGSNCQ02/CG03运行正常.最新话单时间为:" + GGSN_LAST_FILE.substring(_idx + 1, _idx + 1 + 14);
			}
		} else {
			ggsmscnt++;
		}

		if (content != null && !content.equals("")) {
			SmsSend.send("", content); // 发送短信
		}

		long nowtime = System.currentTimeMillis();

		java.io.PrintWriter log = new java.io.PrintWriter(new FileOutputStream(filepath, false), true);

		log.println(nowtime + "," + (ggtotalcnt + 1) + "," + (ggsnok ? ggtotalsucccnt + 1 : ggtotalsucccnt) + ","
				+ (ggsnok ? ggtotalerrorcnt : ggtotalerrorcnt + 1) + "," + ggsmscnt);
		log.println(nowtime + "," + (cgtotalcnt + 1) + "," + (cg03ok ? cgtotalsucccnt + 1 : cgtotalsucccnt) + ","
				+ (cg03ok ? cgtotalerrorcnt : cgtotalerrorcnt + 1) + "," + cgsmscnt);
		log.close();

	}

	// private static void updateGgsnConfig(String key, String value) {
	// try {
	// System.out.println("开始更新timeset.txt文件中的时间...........");
	// Properties pro = new Properties();
	// String filePath = CDRAlarm.class.getResource("ggsn.txt").getPath();
	// pro.setProperty(key, value);
	// FileOutputStream os = new FileOutputStream(filePath);
	// pro.store(os, "");
	// os.close();
	// System.out.println("更新timeset.txt文件中的时间完毕.");
	// } catch (Exception e) {
	// System.out.println("更新失败:" + e);
	// e.printStackTrace();
	// }
	// }
	//
	// private static void updateCdrConfig(String key, String value) {
	// try {
	// System.out.println("开始更新timeset.txt文件中的时间...........");
	// Properties pro = new Properties();
	// String filePath = CDRAlarm.class.getResource("cdr.txt").getPath();
	// pro.setProperty(key, value);
	// FileOutputStream os = new FileOutputStream(filePath);
	// pro.store(os, "");
	// os.close();
	// System.out.println("更新timeset.txt文件中的时间完毕.");
	// } catch (Exception e) {
	// System.out.println("更新失败:" + e);
	// e.printStackTrace();
	// }
	// }

	public static void main22(String[] args) {

	}
}
