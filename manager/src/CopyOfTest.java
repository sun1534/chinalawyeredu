import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

public class CopyOfTest {
	public static void main(String args[]) throws Exception {
		// System.out.println(MD5.md5("yc@6788"));
		// //
		// System.out.println(new
		// String(com.changpeng.common.util.Base64.decode("MTgxODR8za/P/sP3".getBytes("gb2312")),"gb2312"));
		//		
		// System.out.println(new
		// String(com.changpeng.common.util.Base64.encode("18184|童晓明".getBytes("gb2312")),"gb2312"));
		 System.out.println(com.changpeng.common.util.MD5.md5("123456"));
		// getAllLessons();
		// saveteachers();
		// updatecardno();
//		setalllxs();
	}

	private static Connection getCon() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = java.sql.DriverManager.getConnection(
				"jdbc:mysql://211.154.157.174:3306/pxxt_shenzhen?characterEncoding=utf-8", "root", "password");
		return con;
	}

	public static void updatecardno() throws Exception {

		java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(new java.io.FileInputStream(
				"e:\\a.csv")));

		Connection con = getCon();

		String line = null;

		while ((line = br.readLine()) != null) {
			// 1,郑智丽,14403201011315565,14353727
			String[] s = line.split(",");
			String name = s[1];
			String lawyerno = s[2];

			if (s.length == 4) {
				String sql = "update sys_user set cardno=lpad('" + s[3]
						+ "',10,0),carddate='2010-07-31' where lawerno='" + lawyerno + "'";
				System.out.println(sql);
				boolean ss = executesql(con, sql);
				if (!ss) {
					System.out.println(name + "=" + s[2] + "没有更新");
				}
			}
		}
		br.close();
		con.close();

	}

	public static void setalllxs() throws Exception {
		Map groups = new HashMap();
		groups.put("河北省律协", "5");
		groups.put("山西省律协", "6");
		groups.put("内蒙古自治区律协", "7");
		groups.put("辽宁省律协", "8");
		groups.put("吉林省律协", "9");
		groups.put("黑龙江省律协", "10");
		groups.put("江苏省律协", "12");
		groups.put("浙江省律协", "13");
		groups.put("安徽省律协", "14");
		groups.put("福建省律协", "15");
		groups.put("江西省律协", "16");
		groups.put("山东省律协", "17");
		groups.put("河南省律协", "18");
		groups.put("湖北省律协", "19");
		groups.put("湖南省律协", "20");
		groups.put("广东省律协", "21");
		groups.put("广西自治区律协", "22");
		groups.put("海南省律协", "23");
		groups.put("四川省律协", "25");
		groups.put("贵州省律协", "26");
		groups.put("云南省律协", "27");
		groups.put("西藏自治区律协", "28");
		groups.put("陕西省律协", "29");
		groups.put("甘肃省律协", "30");
		groups.put("青海省律协", "31");
		groups.put("宁夏自治区律协", "32");
		groups.put("新疆自治区律协", "33");
		groups.put("北京律协", "41");
		groups.put("重庆律协", "42");
		groups.put("天津律师", "43");
		groups.put("上海律协", "44");

		java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(new java.io.FileInputStream(
				"e:\\a.csv")));

		Connection con = getCon();

		String line = null;
		String temp = "";
		int groupid = 0;
		int id=0;
		while ((line = br.readLine()) != null) {
			// 1,郑智丽,14403201011315565,14353727
			String[] s = line.split(",");
			String province = "";

			if (s[0].equals("")) {
				province = temp;
			} else {
				province = s[0];
				Object obj = groups.get(province);
				if (obj != null)
					groupid = Integer.parseInt(obj.toString());
				province = s[0];
				temp = province;
			}

			String city = s[1];

			if (groupid == 18 || groupid == 22 || groupid == 19 || city.indexOf("深圳") != -1 || city.indexOf("东莞") != -1
					|| city.indexOf("杭州") != -1 || city.indexOf("温州") != -1 || city.indexOf("长春") != -1
					|| city.indexOf("南京") != -1 || city.indexOf("河源") != -1) {
				System.out.println("已经存在::" + province + "==>" + city + "==>" + groupid);
			} else {

				// 新增

				BasicService bservice = (BasicService) Globals.getMainBean("basicService");

				SysGroup sysGroup = new SysGroup();
				sysGroup.setAddress(city);
				sysGroup.setComments("批量");
				sysGroup.setContacter(city);
				sysGroup.setDirectgroup(groupid);
				sysGroup.setParentid(groupid);
				sysGroup.setDistrict(city);
				sysGroup.setGroupenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(city));
				sysGroup.setGrouptype(2);
				sysGroup.setGroupname(city);
				sysGroup.setCreateuser("刘华锋");
				sysGroup.setCreatetype(1);
				sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				sysGroup.setGrouplevel(2);
				sysGroup.setDelflag(false);
				sysGroup.setSystemno(System.currentTimeMillis() / 1000 + ""+(id++));

				bservice.save(sysGroup);
				SysUnionparams params = new SysUnionparams();
				params.setDabiaofen(40);
				params.setGroupid(sysGroup.getGroupid());
				params.setNianshen("12-31");
				params.setSysGroup(sysGroup);
				bservice.save(params);
				
				System.out.println("插入OK");

			}

		}
		br.close();
		con.close();

	}

	private static boolean executesql(Connection con, String sql) throws Exception {
		Statement stmt = con.createStatement();
		int s = stmt.executeUpdate(sql);
		stmt.close();
		return s >= 1 ? true : false;

	}

	public static void saveteachers() throws Exception {

		String sql = "select distinct teachers,teachertype from lessons order by teachers";
		Connection con = getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String s = "skls";

		while (rs.next()) {
			String teachers = rs.getString("teachers");
			if (teachers != null && !teachers.equals("") && teachers.indexOf("、") == -1) {
				int type = rs.getInt("teachertype");
				String loginname = s + com.changpeng.common.util.Chinese2Pinyin.to2pinyin(teachers);
				String password = "123456";
				String inssql = "insert into teachers(loginname,password,username,teacher_type,status,gender,email,officephone,mobile,provinceid,cityid,officeid,createuserid,createuser,createtime,comments)"
						+ "values('"
						+ loginname
						+ "','"
						+ password
						+ "','"
						+ teachers
						+ "','"
						+ type
						+ "',0,0,'','','',0,0,0,0,'刘华锋',now(),'批量导入的');";
				System.out.println(inssql);

			}
		}
		rs.close();
		stmt.close();

	}

	public static void getAllLessons() throws Exception {
		String sql = "select title,onlinefile,teachers,lessontype,groupname,deleteflag,video_quality,audio_quality from lessons inner join sys_group on lessons.groupid=sys_group.groupid  where onlinefile!=''";
		Connection con = getCon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		java.io.PrintWriter log = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\henan.csv", true), true);
		log.println("标题,视频文件地址,老师,课件类型,课件来源,状态,音频质量,视频质量");
		while (rs.next()) {
			int audio = rs.getInt("audio_quality");
			int video = rs.getInt("video_quality");
			String _audio = com.changpeng.lessons.util.CommonDatas.QUALITIES.get(audio);
			String _video = com.changpeng.lessons.util.CommonDatas.QUALITIES.get(video);
			log.println(rs.getString("title") + "," + rs.getString("onlinefile") + "," + rs.getString("teachers") + ","
					+ com.changpeng.lessons.util.CommonDatas.LessonType.get(rs.getInt("lessontype")) + ","
					+ rs.getString("groupname") + "," + (rs.getInt("deleteflag") == 1 ? "禁用" : "正常") + "," + _audio
					+ "," + _video);
		}
		log.flush();
		log.close();
		con.close();

	}

	public static void main1(String args[]) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = java.sql.DriverManager.getConnection(
				"jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8", "root", "password");

		java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(new java.io.FileInputStream(
				"d:\\henan.csv")));

		java.io.PrintWriter log = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\henan.sql", true), true);
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");

			String name = s[0].trim();
			String no = s[1].trim();
			int groupid = update(con, name);
			if (groupid == 0) {
				System.out.println(name + "," + no);
			} else {
				log.println("update sys_user set loginname='" + no + "' where groupid=" + groupid + " and roleid=1;");
				log.println("update sys_group set groupenname='" + no + "' where groupid=" + groupid + ";");
			}

		}
		log.flush();
		con.close();
		log.close();

		br.close();

	}

	public static int update(Connection con, String name) throws Exception {
		String sql = "select groupid from sys_group where groupname='" + name + "'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int s = 0;
		if (rs.next())
			s = rs.getInt("groupid");
		rs.close();
		stmt.close();
		return s;
	}

}
