import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 
 */

/**
 * @author 华锋 Aug 22, 2009-1:11:33 PM
 * 
 */
public class ImportHZXuefen {

	private static PrintWriter loglessons;
	private static PrintWriter logerror;
	private static Map<String, String> lawyersCertno = new HashMap<String, String>();
	private static Map<String, String> lawyersName = new HashMap<String, String>();
	private static Map<Integer, String> alllessons = new HashMap<Integer, String>();
	private static Map<Integer, String> alllessonDate = new HashMap<Integer, String>();
	private static Map<String, String> lawyersLawyerno = new HashMap<String, String>();
	private static Connection con = null;
	static String url = "jdbc:mysql://127.0.0.1:3306/pxxt_hangzhou?characterEncoding=utf-8";
	static String user = "root";
	static String pass = "pm5cpsoft";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pass);
		System.out.println(con);

		getLawyers();
		getLessons();
		importGroups();

	}
	static java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-M-dd");
	public static void getLessons() throws Exception {
		String sql = "select lessonid,title,lessondate from lessons";
		java.sql.Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			alllessons.put(rs.getInt("lessonid"), rs.getString("title"));
		
			alllessonDate.put(rs.getInt("lessonid"), rs.getString("lessondate"));
		}
		rs.close();
		stmt.close();
	}

	public static int getUserid(String certno, String lawerno,String username) throws Exception {
		String ssql = "select userid from sys_user where certno='" + certno + "' or lawerno='" + lawerno + "' or username='"+username+"'";
		java.sql.Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ssql);
		if (rs.next()) {
			return rs.getInt("userid");
		}
		rs.close();
		stmt.close();
		return 0;

	}

	public static void getLawyers() throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\0809lawyers.xls"));
//		补登内容标题(必填)	计分日期(非必填,不填默认为当天)	律所	姓名	补登积分年度(必填)	补登学分(必填)	是否计为现场培训(是/否)

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);

		Iterator _ite = sheet.rowIterator();

		while (_ite.hasNext()) {
			HSSFRow row = (HSSFRow) _ite.next();
			String id = HSSFCellToString.toString(row.getCell(0));// ID

			if (id == null || id.equals(""))
				break;
			if (id.equals("ID"))
				continue;

			String MagcardID = HSSFCellToString.toString(row.getCell(2)).trim();
			String certno = HSSFCellToString.toString(row.getCell(4)).trim();
			String lawerno = HSSFCellToString.toString(row.getCell(5)).trim();
			String name = HSSFCellToString.toString(row.getCell(1)).trim();

			lawyersCertno.put(MagcardID, certno);
			lawyersLawyerno.put(MagcardID, lawerno);

			lawyersName.put(MagcardID, name);
			System.out.println(MagcardID + "-->" + certno + "-->" + lawerno);

		}
	}

	public static void importGroups() throws Exception {

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\0809px.xls"));

		loglessons = new PrintWriter(new FileOutputStream("c:\\px.sql", true), true);
		logerror = new PrintWriter(new FileOutputStream("c:\\pxerror.sql", true), true);
		Map list = new HashMap();

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);

		Iterator _ite = sheet.rowIterator();

		// ID CS_Year CS_Name CS_Time Address Teacher Kind Flag

		String sql = "insert into lawyerlessonxf(userid,lessonid,learnmode,pxdate,pxxf,title,remarks,lastupdate)values(";

		int pxxf = 4;

		String learnmode = "现场培训";
		String remarks="系统导入";
		while (_ite.hasNext()) {
			HSSFRow row = (HSSFRow) _ite.next();
			String id = HSSFCellToString.toString(row.getCell(0));// ID

			if (id == null || id.equals(""))
				break;
			if (id.equals("ID"))
				continue;

			String MagcardID = HSSFCellToString.toString(row.getCell(2)).trim();
			String courseid = HSSFCellToString.toString(row.getCell(6)).trim();
String name=lawyersName.get(MagcardID);
			int lessonid = (int) (1000 + Float.parseFloat(courseid));

			String certno = lawyersCertno.get(MagcardID).toString();
			String lawerno = lawyersLawyerno.get(MagcardID).toString();
			int userid=getUserid(certno,lawerno,name);

			if(userid==0){
				logerror.println(certno+"=>"+lawerno+"=>"+name);
				continue;
			}
			
			String pxdate=alllessonDate.get(lessonid);
			String lastupdate=alllessonDate.get(lessonid);
			String title=alllessons.get(lessonid);
		
			
			String tmp = sql + userid + ",'" + lessonid + "','" + learnmode + "','" + pxdate + "','" + pxxf + "',"
					+ "'" + title + "','" + remarks + "','" + lastupdate + "');";
			System.out.println(userid + "->" + lessonid + "->" + learnmode + "->" + pxdate + "->" + pxxf + "->" + title
					+ "->" + remarks + "->" + lastupdate);

			loglessons.println(tmp);

		}
		loglessons.flush();
		loglessons.close();
		logerror.flush();
		logerror.close();
	}

}