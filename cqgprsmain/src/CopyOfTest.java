import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.readerrors.HWChrFileHandle;

/** 
 * 
 */

/**
 * @author 华锋 Oct 10, 2009 2:41:59 PM
 * 
 * 
 * 
 */
public class CopyOfTest {
	private static final DateFormat dfsec = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static byte[] long2bytes(long i) {
		byte mybytes[] = new byte[8];
		mybytes[7] = (byte) (int) ((long) 255 & i);
		mybytes[6] = (byte) (int) (((long) 65280 & i) >> 8);
		mybytes[5] = (byte) (int) (((long) 0xff0000 & i) >> 16);
		mybytes[4] = (byte) (int) (((long) 0xff000000 & i) >> 24);
		int high = (int) (i >> 32);
		mybytes[3] = (byte) (0xff & high);
		mybytes[2] = (byte) ((0xff00 & high) >> 8);
		mybytes[1] = (byte) ((0xff0000 & high) >> 16);
		mybytes[0] = (byte) ((0xff000000 & high) >> 24);
		return mybytes;
	}
	private static Connection con = null;
	public static void convert(String nowdate) {
		String cmd = "/export/home1/GPRS/sgsnbill/sgsnbill";
		String srcFileName="/export/home1/GPRS/sgsnbill/SGSN20100128131746_20100128132253.chr";
		String destFileName="/export/home1/GPRS/sgsnbill/"+nowdate+".txt";
		BufferedReader br = null;
		try {
			String command=cmd + " " + srcFileName + " " + destFileName;
		
			System.out.println(command);
			long now=System.currentTimeMillis();
//			Process process = java.lang.Runtime.getRuntime().exec(command);
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
			
				System.out.println("解析华为:" + srcFileName + ":" + line);
			}
			System.out.println("解析时间:"+(System.currentTimeMillis()-now));
			
			br.close();
		
				
		} catch (Exception e) {
			System.out.println("解析异常:"+e);
		}
		
	}
	
	
	private static int getNowHour(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 返回当前是星期几,星期日的话为0
	 * @return
	 */
		private static int getWeekOfDay(){
			Calendar c=Calendar.getInstance();
			return c.get(Calendar.DAY_OF_WEEK)-1;
		}
		/**
		 * 
		 * @return
		 */
		private static int getYestardayWeekOfDay(){
			Calendar c=Calendar.getInstance();
			System.out.println(new java.sql.Timestamp(c.getTimeInMillis()));
		
			System.out.println(new java.sql.Timestamp(c.getTimeInMillis()));
			System.out.println(c.get(Calendar.DATE));
			return c.get(Calendar.DAY_OF_WEEK)-1;
		}
		
		private static int getNowDate(){
			Calendar c=Calendar.getInstance();
			return c.get(Calendar.DATE);
		}
	public static void main(String[] args)throws Exception{

//		HWChrFileHandle c=new HWChrFileHandle();
//		c.truncateTable("hwchr_log_4");
		
		String ss="111|33|||||45";
		String sss[]=ss.split("\\|");
		for(int i=0;i<sss.length;i++){
			System.out.println(sss[i]);
		}
		System.out.println("ddd");
//		System.out.println(getNowDate());
//	System.out.println(getWeekOfDay());
//	System.out.println(getYestardayWeekOfDay());
		
		
//		File destdir=new File("C:\\Documents and Settings\\华锋\\My Documents\\Downloads");
//		File[] files=destdir.listFiles(new FileFilter(){
//			 public boolean accept(File pathname){
//				 if(pathname.getName().endsWith(".jpg"))
//					 return true;
//				 return false;
//			 }
//		});
//		for(File file:files){
//			System.out.println(file.getName());
//		}
//		
//		String name="201007282210_SGSN7_SGSN20100727182857_20100727183805.chr.txt"; //生成的文件的格式
//
//		String[] splits=name.split("_");
//		String theflag=splits[0];
//		String sgsnid=splits[1];
//		String srcfilename=splits[2]+"_"+splits[3].replace(".txt", "");
//		
//		for(String ss:splits){
//			System.out.println(ss);
//		}
//		System.out.println(srcfilename);
	}
	
	private static DateFormat df=new java.text.SimpleDateFormat("yyyyMMddHHmm");
	public static void main333(String[] args){
		System.out.println("=======================================================");
		String nowdate=df.format(new Date());
		try{
			String cmd = "/export/home1/GPRS/sgsnbill/sgsnbill";
			String srcFileName="/export/home1/GPRS/sgsnbill/SGSN20100128131746_20100128132253.chr";
			String destFileName="/export/home1/GPRS/sgsnbill/"+nowdate+".txt";
			BufferedReader br = null;
			try {
				String command=cmd + " " + srcFileName + " " + destFileName;
			
				System.out.println(command);
				long now=System.currentTimeMillis();
//				Process process = java.lang.Runtime.getRuntime().exec(command);
				Runtime runtime = Runtime.getRuntime();
				
				Process process = runtime.exec(command);
				Thread.sleep(7000L);
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				Thread.sleep(10000L);
				while ((line = br.readLine()) != null) {
				
					System.out.println("解析华为:" + srcFileName + ":" + line);
				}
				System.out.println("解析时间:"+(System.currentTimeMillis()-now));
				
				br.close();
			
					
			} catch (Exception e) {
				System.out.println("解析异常:"+e);
			}
		}catch(Exception e){
			System.out.println("失败！！！"+e);
			e.printStackTrace();
		}
	}
	
	public static void main3333(String[] args) throws Exception {
		
		boolean a=false;
		boolean b=true;
		System.out.println(a+","+b+",,,"+Boolean.parseBoolean("false"));
		
		String today="20100715";
		String yestarday="20100714";
		String CG03_LAST_FILE="GGSNCQ02.J20R4:GGSNCQ02_20100715232545_37350:20100715:232529:031:NO-LABEL";
		int idx=CG03_LAST_FILE.lastIndexOf(today);
		if(idx==-1)
			idx=CG03_LAST_FILE.lastIndexOf(yestarday);
		if(idx!=-1){
			
//			
			
			String date=CG03_LAST_FILE.substring(idx,idx+8);
			String time=CG03_LAST_FILE.substring(idx+9,idx+9+6);
		
		System.out.println(date);
		System.out.println(time);
		}
		
//		String s="#############3##3234234###################12312kkkkjkadf#3234234#";
//		String[] ss=s.split("(#)+");
//		for(String sss:ss){
//			System.out.println(sss);
//		}
//		System.exit(1);
//		Date daysagodate = main.util.MainStatUtil.getPrevCountDate(3);
//		System.out.println(daysagodate);
//		long s=3510073023L;
//byte[] b=long2bytes(9999999999L);
//for(byte bb:b){
//	System.out.print(bb+" ");
//}
	
//		System.out.println(dfsec.parse("2009-12-01 00:00:00").getTime()/1000);
//		System.out.println(dfsec.parse("2009-12-02 00:00:00").getTime()/1000);
		
//		long no1w =1259852400L * 1000;
//		System.out.println(new java.sql.Timestamp(no1w));
		
//		 no1w =1259673240L * 1000;
//			System.out.println(new java.sql.Timestamp(no1w));
//		
//		Date daysagodate = main.util.MainStatUtil.getPrevCountDate(8);
//		long daystart = daysagodate.getTime() / 1000;
//		System.out.println("=========" + daystart);
		//		
		// // 更新16，15，14，13，12，11，10这几天的stat_**的dayflag=1的数据
		// DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		//	
		// int now=20091115;
		// for(int i=20091115;i<=20091119;i++){
		// Date date=df.parse(i+"");
		// long start = main.util.MainStatUtil.getDateTime(date)/1000;
		// long end = start + 24 * 60 * 60;
		// String apnimsisql="insert into
		// stat_imsi_apn_error(imsi,apnni,errcount,dayflag,stattime) select
		// imsi,reqapnni,count(*),1,"+i+" from cdr_mistake where opentime
		// between "+start+" and "+end+" group by reqapnni,imsi;";
		// System.out.println(apnimsisql);
		//			
		// }
		//		

		// Date date = new Date();
		//
		// Date yydate = main.util.MainStatUtil.getPrevCountHour(date, 1);
		// System.out.println(dfsec.format(yydate));
//Connection con=null;
//		try {
//			con=DBUtils.getOracleCon();
//			int opentime = 0;
////			String sql = "select min(opentime) as opentime from cdr_mistake";
//			String sql = "select min(stattime) as opentime from stat_cellid";
//			Statement stmt = con.createStatement();
//			int deletetime = 1266948122;
////			 int deletetime = Integer.parseInt(hourstattime);
//			while (opentime <= deletetime) {
//				ResultSet rs = stmt.executeQuery(sql);
//				rs.next();
//				opentime = rs.getInt("opentime");
//				rs.close();
//				long now=System.currentTimeMillis();
////			int ii=	main.util.MainStatUtil.executeSql(con, "delete from cdr_mistake where opentime<=" + (opentime + 3600));
//			int ii=	main.util.MainStatUtil.executeSql(con, "delete from stat_cellid where stattime<=" + (opentime));
//System.out.println("删除了"+ii+"条..."+(System.currentTimeMillis()-now));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		con.close();
//		System.out.println("妈的，我终于删完了。我尻。。。。。。。。");

	}

	/**
	 * @param args
	 */
	public static void main22(String[] args) throws Exception {
		con = main.util.DBUtils.getOracleCon();

		String sql = "select cellid,lac,sum(allvolume) as allvolume,sum(downvolume) as downvolume,sum(upvolume) as upvolume from stat_cellid where dayflag=1 group by cellid,lac";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List list = new ArrayList();
		long now = System.currentTimeMillis();
		int i = 1;
		while (rs.next()) {
			String sql1 = "update allvolume_cellid set upvolume=" + rs.getString("upvolume") + ",downvolume="
					+ rs.getString("downvolume") + ",allvolume=" + rs.getString("allvolume") + " where cellid='"
					+ rs.getString("cellid") + "' and lac='" + rs.getString("lac") + "'";
			// System.out.println(sql1);
			if (i++ % 300 == 0) {
				System.out.println(i + "::::::::::" + (System.currentTimeMillis() - now));
				now = System.currentTimeMillis();
			}

			list.add(sql1);
		}
		System.out.println((System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		main.util.MainStatUtil.executeSql(con, list);

		System.out.println((System.currentTimeMillis() - now));

	}

	/**
	 * @param args
	 */
	public static void main1(String[] args) throws Exception {
		con = main.util.DBUtils.getOracleCon();

		// 更新16，15，14，13，12，11，10这几天的stat_**的dayflag=1的数据
		DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		for (int i = 2; i <= 15; i++) {

			Date date = main.util.MainStatUtil.getPrevCountDate(i);
			long start = main.util.MainStatUtil.getDateTime(date);
			long end = start + 24 * 60 * 60;
			String stattime = df.format(date);
			// sgsn(start, end, stattime);
			// bsc(start, end, stattime);
			// cell(start, end, stattime);

			// apn(start, end, stattime);
			// cellapn(start, end, stattime);
		}

	}

	private static void apn(int start, int end, String stattime) throws Exception {
		String sql = "select apnni,sum(usercount) as usercount from stat_apn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by apnni";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("apnni");
			String usercount = rs.getString("usercount");

			String usql = "update stat_apn set usercount=" + usercount + " where apnni='" + apnni + "' and stattime="
					+ stattime;
			System.out.println(usql + ";");
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));

		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}

		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void cellapn(int start, int end, String stattime) throws Exception {
		String sql = "select apnni,cellid,sum(usercount) as usercount from stat_cellid_apn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by apnni,cellid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String cellid = rs.getString("cellid");
			String apnni = rs.getString("apnni");
			String usercount = rs.getString("usercount");

			String usql = "update stat_cellid_apn set usercount=" + usercount + " where cellid='" + apnni + "',apnni='"
					+ apnni + "' and stattime=" + stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void cell(int start, int end, String stattime) throws Exception {
		String sql = "select cellid,sum(usercount) as usercount from stat_cellid where dayflag=0 and stattime between "
				+ start + " and " + end + " group by cellid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("cellid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_cellid set usercount=" + usercount + " where cellid='" + apnni
					+ "' and stattime=" + stattime;
			// System.out.println(usql);
			list.add(usql);
		}
		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void bsc(int start, int end, String stattime) throws Exception {
		String sql = "select bscid,sum(usercount) as usercount from stat_bsc where dayflag=0 and stattime between "
				+ start + " and " + end + " group by bscid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("bscid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_bsc set usercount=" + usercount + " where bscid='" + apnni + "' and stattime="
					+ stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

	private static void sgsn(int start, int end, String stattime) throws Exception {
		String sql = "select sgsnid,sum(usercount) as usercount from stat_sgsn where dayflag=0 and stattime between "
				+ start + " and " + end + " group by sgsnid";
		System.out.println(stattime + ",,,," + sql);
		List list = new ArrayList();
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		long now = System.currentTimeMillis();
		while (rs.next()) {
			String apnni = rs.getString("sgsnid");
			String usercount = rs.getString("usercount");

			String usql = "update stat_sgsn set usercount=" + usercount + " where sgsnid='" + apnni + "' and stattime="
					+ stattime;
			// System.out.println(usql);
			list.add(usql);
		}

		System.out.println("开始执行:" + (System.currentTimeMillis() - now));
		now = System.currentTimeMillis();
		try {

			main.util.MainStatUtil.executeSql(con, list);

		} catch (Exception e) {
			System.out.println("ERROR::::" + e);
			e.printStackTrace();
		}
		System.out.println("执行完毕:" + (System.currentTimeMillis() - now));
		rs.close();
		stmt.close();
	}

}
