/**
 * SqlServerDataUtil.java
 */

package com.changpeng.common.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.context.Globals;
import com.changpeng.common.util.MD5;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-6-16 下午11:32:19
 * 
 */
public class SqlServerDataUtil {

	private static Log LOG = LogFactory.getLog(SqlServerDataUtil.class);
//	private static String url = "jdbc:sqlserver://www.cpsoft.cn:1433;DatabaseName=lx_shenzhen";
//	private static String username = "lxshenzhen";
//	private static String password = "lxshenzhen";
	
//	 private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=szlxcs";
//	 private static String username = "szlxcs";
//	 private static String password = "123456";
	 private static String url = "jdbc:sqlserver://211.154.139.95:1433;DatabaseName=szlawyers";
	 private static String username = "szlawyers";
	 private static String password = "123456";
	 private static String userupdsql = "select * from userupdlog";
	 private static String lawerupdsql = "select * from lawerupdlog";
	 private static String userupddelsql = "delete from userupdlog";
	 private static String lawerupddelsql = "delete from lawerupdlog";

	static {
		try {
	
			url=CommonDatas.SysParameter.get("mssqlurl")==null?"jdbc:sqlserver://localhost:1433;DatabaseName=szlawyers":CommonDatas.SysParameter.get("mssqlurl").toString();
			username=CommonDatas.SysParameter.get("mssqlusername")==null?"szlxcs":CommonDatas.SysParameter.get("mssqlusername").toString();
			password=CommonDatas.SysParameter.get("mssqlpassword")==null?"123456":CommonDatas.SysParameter.get("mssqlpassword").toString();
			LOG.info("url:::"+url);
			LOG.info("url:::"+username);
			LOG.info("url:::"+password);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			LOG.info("加载MSSQL数据库驱动成功...");
		}
		catch (Exception e) {
			LOG.error("没有找到SQLSERVER数据库驱动:" + e);
		}
	}
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final DateFormat dfff = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat ddf = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public static void skrecs() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;

		java.io.PrintWriter log = new java.io.PrintWriter("c:\\skrecs.sql");
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_skrecs");
			rs = stmt.executeQuery();
			while (rs.next()) {

				String kahao = rs.getString("kh");
				String lessonid = rs.getString("lessonid");
				java.sql.Timestamp uploadtime = rs.getTimestamp("skdt");
				String skmode = rs.getString("skmode");

				String skdate = df.format(uploadtime);
				String ischeck = "Y";
				int timelong = 0;

				String sql = "insert into lxskrecs(kahao,lessonid,skdate,skmode,ischeck,uploadtime,timelong,remarks) values('" + kahao + "',"
						+ lessonid + ",'" + skdate + "','" + skmode + "','" + ischeck + "','" + uploadtime + "'," + timelong + ",'从老培训系统导入');";

				log.println(sql);
				System.out.println(sql);

			}
			log.flush();
			log.close();
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}

	}

	public static void scores() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		java.io.PrintWriter log = new java.io.PrintWriter("c:\\scores.sql");
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_doclass where dotype='P'");
			rs = stmt.executeQuery();
			while (rs.next()) {

				String lawyerid = rs.getString("lawyerid");
				String lessonid = rs.getString("lessonid");
				String doitem = rs.getString("doitem").trim();
				int score = 1;
				if (doitem.equals("一般")) {
					score = 1;
				}
				else if (doitem.equals("较好")) {
					score = 2;
				}
				else if (doitem.equals("很好")) {
					score = 3;
				}

				java.sql.Timestamp dodate = rs.getTimestamp("dodate");

				int userid = UserLawerMap.getLawid(lawyerid);

				String sql = "insert into lessonscore(lessonid,score,userid,scoretime) values(" + lessonid + "," + score + "," + userid + ",'"
						+ dodate + "');";

				if (userid == 0) {
					sql = "--" + sql;
				}
				log.println(sql);
				System.out.println(sql);

			}
			log.flush();
			log.close();
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
	}

	public static void netrecs() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;

		java.io.PrintWriter log = new java.io.PrintWriter("c:\\netrecs.sql");
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_netrecs");
			rs = stmt.executeQuery();
			while (rs.next()) {

				String lawyerid = rs.getString("lawyerid");
				String lessonid = rs.getString("lessonid");
				java.sql.Timestamp lastdate = rs.getTimestamp("lastdate");

				float allminutes = rs.getFloat("allminutes");
				float lookedminutes = rs.getFloat("lookedminutes");

				int userid = UserLawerMap.getLawid(lawyerid);

				String sql = "insert into lxnetrecs(lessonid,userid,allminutes,lookedminutes,remarks,lasttime) values(" + lessonid + "," + userid
						+ "," + allminutes + "," + lookedminutes + ",'老培训系统导入','" + lastdate + "');";
				if (userid == 0) {
					sql = "--" + sql;
				}
				log.println(sql);
				System.out.println(sql);

			}
			log.flush();
			log.close();
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}

	}

	public static void lessons() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_splesson where lessonname  not like '%深圳律师执业与生存现状大调查%'");
			rs = stmt.executeQuery();
			stmt.setFetchSize(100);
			int i = 0;
			java.io.PrintWriter log = new java.io.PrintWriter("c:\\lessons.sql");
			while (rs.next()) {

				String lessonid = rs.getString("lessonid");
				String name = rs.getString("lessonname");
				java.sql.Timestamp lessondt = rs.getTimestamp("lessondt");
				rs.getInt("skreqtime");
				int xuefen = rs.getInt("lessonxf");
				String lessonstate = rs.getString("lessonstate");
				String bmalert = rs.getString("bmalert");
				rs.getFloat("bmkf");
				String classfile = rs.getString("netclassfile");
				String fenshuoff = rs.getFloat("netclassxs") * 100 + "";

				Lessons lessons = new Lessons();
				lessons.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				lessons.setCreateuser(0);
				lessons.setFenshuoff(fenshuoff);
				lessons.setIsalert(bmalert.equals("不提醒") ? false : true);
				lessons.setIsbixiu(false);
				lessons.setKaoqinshichang(0f);
				lessons.setLessondate(df.format(lessondt));
				lessons.setLessonid(Integer.parseInt(lessonid));
				// 0:'内容编辑',1:'培训课程',2:'往期课程',3:'课程结束'
				if (lessonstate.equals("内容编辑")) {
					lessons.setLessonstate((byte) 0);
				}
				else if (lessonstate.equals("培训课程")) {
					lessons.setLessonstate((byte) 1);
				}
				else if (lessonstate.equals("往期课程")) {
					lessons.setLessonstate((byte) 2);
				}
				else if (lessonstate.equals("课程结束")) {
					lessons.setLessonstate((byte) 3);
				}
				lessons.setOnlinefile(classfile == null ? "" : classfile);
				lessons.setTitle(name);
				lessons.setXuefen((float) xuefen);

				String sql = "insert into lessons(lessonid,title,lessonstate,xuefen,lessondate,onlinefile,fenshuoff) values(" + lessonid + ",'"
						+ name + "','" + lessons.getLessonstate() + "','" + lessons.getXuefen() + "','" + lessons.getLessondate() + "','"
						+ lessons.getOnlinefile() + "','" + lessons.getFenshuoff() + "');";
				log.println(sql);
				System.out.println(sql);
			}
			log.flush();
			log.close();

		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
	}

	public static List getxf() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_lawyerlessonxf where learnmode!='其他计分'");
			rs = stmt.executeQuery();
			stmt.setFetchSize(100);
			java.io.PrintWriter log = new java.io.PrintWriter("c:\\xuefen.sql");
			java.io.PrintWriter uesrid0 = new java.io.PrintWriter("c:\\useridis0.sql");
			int i = 1;
			while (rs.next()) {

				String lawyerid = rs.getString("lawyerid");
				String lessonid = rs.getString("lessonid");
				java.sql.Timestamp pxdate = rs.getTimestamp("pxdate");
				String learnmode = rs.getString("learnmode").trim();
				float reqminutes = rs.getFloat("pxreqminutes");
				float pxminutes = rs.getFloat("pxminutes");
				float pxxf = rs.getFloat("pxxf");
				// String xftype = rs.getString("xftype");
				// String isfull = rs.getString("isfull");

				Lawyerlessonxf lessons = new Lawyerlessonxf();
				lessons.setLastupdate(pxdate);

				// 现场培训，在线视频，文本课件，积分补登
				if (learnmode.equals("网上培训")) {
					lessons.setLearnmode("在线视频");
				}
				else if (learnmode.equals("其他计分")) {
					lessons.setLearnmode("补登积分");
				}
				else if (learnmode.equals("现场培训")) {
					lessons.setLearnmode("现场培训");
				}
				else if (learnmode.equals("现场,网上")) {
					lessons.setLearnmode("在线视频");
				}

				lessons.setLessonid(Integer.parseInt(lessonid));
				lessons.setPxdate(dfff.format(pxdate));
				lessons.setPxminutes(pxminutes);
				lessons.setPxreqminutes(reqminutes);
				lessons.setPxxf(pxxf);
				lessons.setTitle(LessonIdNameMap.LessonIdName.get(lessons.getLessonid()));

				lessons.setRemarks("从老系统同步");
				int userid = UserLawerMap.getLawid(lawyerid);

				if (userid == 0)
					uesrid0.println("在系统中的userid=0:::" + lawyerid);

				if ((i++) % 10000 == 0) {
					log.flush();
					log.close();

					log = new java.io.PrintWriter("c:\\xuefen" + i + ".sql");
				}

				String sql = "insert into lawyerlessonxf(userid,learnmode,pxdate,pxxf,lessonid,title,pxreqminutes,pxminutes,lastupdate) values(";
				sql += userid + ",'" + lessons.getLearnmode() + "','" + lessons.getPxdate() + "'," + pxxf + "," + lessonid + ",'"
						+ lessons.getTitle() + "'," + reqminutes + "," + pxminutes + ",'" + dfff.format(pxdate) + "'";
				sql += ");";
				if (userid != 0)
					log.println(sql);

				System.out.println(i);

			}
			log.flush();
			log.close();
			uesrid0.flush();
			uesrid0.close();

		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
		return list;
	}

	public static void getLawerNoId() throws Exception {
		Globals g = new Globals();
		BasicService bs = (BasicService) g.getBean("basicService");

		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1));
		List list = bs.findAllByCriteria(dc);
		java.io.PrintWriter log = new java.io.PrintWriter("c:\\lawyerid.txt");

		int k = 1;
		for (int i = 0; i < list.size(); i++) {

			SysUser user = (SysUser) list.get(i);
			String s = "(\"" + user.getLawerno() + "\"," + user.getUserid() + ")";
			log.println(s);

			if ((k++) % 1500 == 0) {
				log.flush();
				log.close();

				log = new java.io.PrintWriter("c:\\lawyerid" + i + ".txt");
			}
			System.out.println(s);

		}
		log.flush();
		log.close();
		System.out.println("=======================================over");
	}

	public static void main(String args[]) throws Exception {
		// getbudeng();
		// getxf();
//		scores();
//		skrecs();
//		netrecs();
		// getSysuser();
		// getLawerNoId();
String comments="数据同步:/UpFilesAttach/2006/11/24/151035.JPG";
		String lsxp="/UpFiles/Attach/2006/11/24/151035.JPG";
		
	}

	public static List getbudeng() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		java.io.PrintWriter log = new java.io.PrintWriter("c:\\budeng.sql");
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from lx_lawyerlessonxf where learnmode like '%其他计分%'");
			rs = stmt.executeQuery();
			while (rs.next()) {

				String lawyerid = rs.getString("lawyerid");
				// String lessonid = rs.getString("lessonid");
				java.sql.Timestamp pxdate = rs.getTimestamp("pxdate");

				// float reqminutes = rs.getFloat("pxreqminutes");
				// float pxminutes = rs.getFloat("pxminutes");
				float pxxf = rs.getFloat("pxxf");

				Jifenbudeng budeng = new Jifenbudeng();
				budeng.setBudengdate(dfff.format(pxdate));
				budeng.setCreatetime(pxdate);
				budeng.setCreateuser("老培训系统同步");

				int userid = UserLawerMap.getLawid(lawyerid);

				String sql = "insert into jifenbudeng(budengdate,userid,lawerno,xuefen,createtime)values('" + ddf.format(pxdate) + "'," + userid
						+ ",'" + lawyerid + "'," + pxxf + ",'" + dfff.format(pxdate) + "');";

				if (userid != 0) {
					log.println(sql);
				}

				System.out.println(sql);
			}
			log.flush();
			log.close();
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
			e.printStackTrace();
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
		return list;
	}

	public static List getSysuser() throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		java.io.PrintWriter log = new java.io.PrintWriter("c:\\lawyer.sql");
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			stmt = mssqlcon.prepareStatement("select * from [lawer] where lawno is not null and  lawno != ''");
			rs = stmt.executeQuery();
			while (rs.next()) {

				LawyerUpdLog updlog = new LawyerUpdLog();
//				updlog.carddate = rs.getString("carddate");
				String enterprisid = rs.getString("enterpriseid");
				// 如果这个律师的enterpriseid为空的话，设置到离职律师库
				updlog.enterpriseid = enterprisid != null && !enterprisid.equals("") ? Integer.parseInt(enterprisid) : -1;
				updlog.lawername = rs.getString("lawername");
				updlog.lawid = rs.getInt("lawid");
				updlog.lawno = rs.getString("lawno");
//				updlog.licenceno = rs.getString("licenceno");
				updlog.lsxp = rs.getString("lsxp");
				updlog.memo = rs.getString("memo");
				updlog.password = rs.getString("psd1");
				updlog.telephone = rs.getString("telephone");
//				updlog.trainkh = rs.getString("trainkh");
				// updlog.updlogid = rs.getInt("updlogid");

				String password = MD5.md5(updlog.password);
				String sql = "insert into sys_user(userid,groupid,loginname,username,password,lawerno,photo,roleid) " + "values("
						+ updlog.lawid + "," + updlog.enterpriseid + ",'" + updlog.lawno + "','" + updlog.lawername + "','" + password + "','"
						+ updlog.lawno + "','" + updlog.lsxp + "',1);";

				log.println(sql);
				System.out.println(sql);
			}
			log.flush();
			log.close();
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
		return list;
	}

	public static void getSqlServerDatas(List<UserUpdLog> userUpdLogs, List<LawyerUpdLog> lawyerUpdLogs) throws Exception {
		Connection mssqlcon = null;
		java.sql.PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			mssqlcon = DriverManager.getConnection(url, username, password);
			LOG.info("连接到MSSQL数据库成功...");
			stmt = mssqlcon.prepareStatement(userupdsql);
			rs = stmt.executeQuery();
			stmt.setFetchSize(200);
			while (rs.next()) {
				UserUpdLog updlog = new UserUpdLog();
				updlog.dotype = rs.getString("dotype");
				updlog.id = rs.getInt("id");
				updlog.licenceno = rs.getString("licenceno");
				updlog.mailaddr = rs.getString("mailaddr");
				updlog.mailno = rs.getString("mailno");
				updlog.mastername = rs.getString("mastername");
				updlog.mobile = rs.getString("mobile");
				updlog.password = rs.getString("netpwd");
				updlog.power = rs.getInt("power");
				updlog.shortname = rs.getString("shortname");
				updlog.telcall = rs.getString("telcall");
				updlog.telephone = rs.getString("telephone");
				updlog.telmail = rs.getString("telmail");
				updlog.updlogid = rs.getInt("updlogid");
				updlog.username = rs.getString("username");

				userUpdLogs.add(updlog);
				LOG.info("新增一个事务所的信息变更记录:" + updlog.dotype+",,,"+updlog.licenceno+",,,"+updlog.power);
			}
			stmt = mssqlcon.prepareStatement(lawerupdsql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				LawyerUpdLog updlog = new LawyerUpdLog();
//				updlog.carddate = rs.getString("carddate");
				updlog.dotype = rs.getString("dotype");

				String enterprisid = rs.getString("enterpriseid");
				updlog.enterpriseid = enterprisid != null && !enterprisid.equals("") ? Integer.parseInt(enterprisid) : -1;
				updlog.lawername = rs.getString("lawername");
				updlog.lawid = rs.getInt("lawid");
				updlog.lawno = rs.getString("lawno");
//				updlog.licenceno = rs.getString("licenceno");
				updlog.lsxp = rs.getString("lsxp");
				updlog.memo = rs.getString("memo");
				updlog.password = rs.getString("psd1");
				updlog.telephone = rs.getString("telephone");
//				updlog.trainkh = rs.getString("trainkh");
				updlog.updlogid = rs.getInt("updlogid");
				lawyerUpdLogs.add(updlog);
				LOG.info("新增一个律师的信息变更记录:" + updlog.dotype+",,"+updlog.lawid+",,"+updlog.lawername+",,"+updlog.lawno);
			}
			if (userUpdLogs.size() != 0) {
				stmt = mssqlcon.prepareStatement(userupddelsql);
				int i = stmt.executeUpdate();
				LOG.info("清除事务所信息变更表个数:" + i);
			}
			if (lawyerUpdLogs.size() != 0) {
				stmt = mssqlcon.prepareStatement(lawerupddelsql);
				int i = stmt.executeUpdate();
				LOG.info("清除律师信息变更表个数:" + i);
			}
		}
		catch (Exception e) {
			LOG.error("获取SqlServer的同步数据失败:" + e);
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (mssqlcon != null)
				mssqlcon.close();
		}
	}
}