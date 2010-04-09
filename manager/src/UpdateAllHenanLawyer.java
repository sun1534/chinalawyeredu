import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 
 */

/**
 * 导入河南的除郑州和省直所外的其他事务所数据
 * 
 * @author 华锋 Aug 22, 2009-1:11:33 PM
 * 
 */
public class UpdateAllHenanLawyer {
	private static List grouplist = new ArrayList();
	private static Map groupmap = new HashMap();

	private static PrintWriter loguser;
	private static PrintWriter logugroup;
	private static PrintWriter logugroupmanager;
	private static PrintWriter loggrouprole;
	private static PrintWriter loguserrole;

	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8";
	static String user = "root";
	static String pass = "password";

	private static int getGroupid(Connection con, String name) throws Exception {
		String sql = "select groupid from sys_group where groupname like '%" + name
				+ "%' and grouptype=2 and parentid=18";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getInt("groupid");
			return 0;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	private static int getOfficeid(Connection con, String name) throws Exception {
		String sql = "select groupid from sys_group where groupname like '%" + name
				+ "%' and grouptype=1 and directgroup=18";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getInt("groupid");
			return 0;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = java.sql.DriverManager.getConnection(url, user, pass);

		importLawyers();

		con.close();
	}

	public static void importLawyers() throws Exception {
		// TODO Auto-generated method stub
		// 律师姓名 执业机构 性别 身份证号 执业证号 会员编号 隶属机构 首次执业时间

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\lawyers.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		int numsheets = wb.getNumberOfSheets();
		int provinceunion = 18;
		Statement stmt = con.createStatement();
		Map alllawyernos = new HashMap();
		Map allgroups = new HashMap();
		int all = 0;
		for (int i = 0; i < numsheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String sheetname = wb.getSheetName(i);
			// if (sheetname.equals("省直") || sheetname.equals("郑州")) {
			// continue;
			// }
			if (sheetname.toLowerCase().indexOf("sheet") != -1)
				continue;

//			if (!(sheetname.equals("周") || sheetname.equals("南") || sheetname.equals("许") || sheetname.equals("省直") || sheetname
//					.equals("郑州")))
				if (!(sheetname.equals("郑州") ))
				continue;

			int directunion = getGroupid(con, sheetname);
			short s = sheet.getTopRow();
			Iterator _ite = sheet.rowIterator();
int j=0;
			while (_ite.hasNext()) {

				HSSFRow row = (HSSFRow) _ite.next();
				// 律师姓名 执业机构 性别 身份证号 执业证号 会员编号 隶属机构 首次执业时间

				String lawyername = HSSFCellToString.toString(row.getCell(0)).trim();
				// String lawyerenname =
				// com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyername);
				String groupname = HSSFCellToString.toString(row.getCell(2)).trim();
				// String _gender =
				// HSSFCellToString.toString(row.getCell(2)).trim();
				String certno = HSSFCellToString.toString(row.getCell(1)).trim();
				String lawyerno = HSSFCellToString.toString(row.getCell(3)).trim();
				// String systemno =
				// HSSFCellToString.toString(row.getCell(5)).trim();
				// String district =
				// HSSFCellToString.toString(row.getCell(6)).trim();
				// String zhiyedate =
				// HSSFCellToString.toString(row.getCell(7)).trim();
				// String gender = "M";
				// if (_gender == null || _gender.equals("") ||
				// _gender.equals("男"))
				// gender = "M";
				// else
				// gender = "F";
				// int theoffice = 0;
				// int status = 0;
				// int regsrc = 0;
				// String createusername = "100331导入";
				// String createuser = "0";
				// String remarks = "100331导入";
				// String dabiaofen = "35";
				if (lawyername.indexOf("姓名") != -1) {
					continue;
				}
				if (lawyername == null || lawyername.equals(""))
					break;

				if (alllawyernos.containsKey(lawyerno)) {
					System.out.println(sheetname + "律协的执业证号" + lawyerno + "，2个律师雷同：" + alllawyernos.get(lawyerno) + "和"
							+ lawyername + ",导入" + alllawyernos.get(lawyerno) + "," + lawyername + "修改为：" + lawyerno
							+ "_1");
					lawyerno = lawyerno + "_1";
				}

				alllawyernos.put(lawyerno, lawyername);
				String sql = "";

				if (!(lawyerno.equals("") || lawyerno.equals("_1")) || lawyerno.equals("无信息")
						|| lawyerno.equals("无信息_1"))
				{
				sql = "update lawyers set lawyerno='" + lawyerno + "',loginname='" + lawyerno
						+ "' where provinceunion=18 and certno='" + certno + "'";
System.out.println(sql);
				stmt.addBatch(sql);
				j++;
				}
			}
			int ss[] = new int[0];
			try {
				System.out.println("jjjjjjjjjj:"+j);
				ss = stmt.executeBatch();
				for ( j = 0; j < ss.length; j++) {
					System.out.print(ss[j] + " ");
				}
				System.out.println(sheetname + ":" + ss.length);
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			stmt.clearBatch();
			all += ss.length;
		}
		System.out.println("总数:" + all);
		stmt.close();

	}
}