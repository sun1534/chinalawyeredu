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
public class ImportAllHenanLawyer {
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
//		String sql = "select groupid from sys_group where groupname like '%" + name
//				+ "%' and grouptype=1 and directgroup=18";
		
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

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\lvshi.xls"));
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
			if (sheetname.equals("省直") || sheetname.equals("郑州")) {
				continue;
			}
			if (sheetname.toLowerCase().indexOf("sheet") != -1)
				continue;
			int directunion = getGroupid(con, sheetname);
			short s = sheet.getTopRow();
			Iterator _ite = sheet.rowIterator();

			while (_ite.hasNext()) {

				HSSFRow row = (HSSFRow) _ite.next();
				// 律师姓名 执业机构 性别 身份证号 执业证号 会员编号 隶属机构 首次执业时间

				String lawyername = HSSFCellToString.toString(row.getCell(0)).trim();
				String lawyerenname = com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyername);
				String groupname = HSSFCellToString.toString(row.getCell(1)).trim();
				String _gender = HSSFCellToString.toString(row.getCell(2)).trim();
				String certno = HSSFCellToString.toString(row.getCell(3)).trim();
				String lawyerno = HSSFCellToString.toString(row.getCell(4)).trim();
				String systemno = HSSFCellToString.toString(row.getCell(5)).trim();
				String district = HSSFCellToString.toString(row.getCell(6)).trim();
				String zhiyedate = HSSFCellToString.toString(row.getCell(7)).trim();
				String gender = "M";
				if (_gender == null || _gender.equals("") || _gender.equals("男"))
					gender = "M";
				else
					gender = "F";
				int theoffice = 0;
				int status = 0;
				int regsrc = 0;
				String createusername = "100331导入";
				String createuser = "0";
				String remarks = "100331导入";
				String dabiaofen = "35";
				if (lawyername.indexOf("律师姓名") != -1) {
					continue;
				}
				if (groupname == null || groupname.equals(""))
					break;

				if (alllawyernos.containsKey(lawyerno)) {
					System.out.println(sheetname + "律协的执业证号" + lawyerno + "，2个律师雷同：" + alllawyernos.get(lawyerno) + "和"
							+ lawyername + ",导入" + alllawyernos.get(lawyerno) + "," + lawyername + "修改为：" + lawyerno
							+ "_1");
					lawyerno = lawyerno + "_1";
				}
				

				if (allgroups.containsKey(groupname)) {
					theoffice = Integer.parseInt(allgroups.get(groupname).toString());
				} else {
					theoffice = getOfficeid(con, groupname);
					allgroups.put(groupname, theoffice);
				}
				if (theoffice == 0) {
					System.out.println(sheetname + "律协执业证号" + lawyerno + "的事务所" + groupname + "不存在,暂未导入!");
					continue;
				}

				alllawyernos.put(lawyerno, lawyername);
				String sql = "";
				if (!(zhiyedate == null || zhiyedate.equals("")))
					sql = "insert into lawyers(certno,gender,zhiyedate,lawyerno,lawyername,lawyerenname,systemno,loginname,passwd,"
							+ "theoffice,directunion,provinceunion,status,"
							+ "regsrc,createtime,createuser,createusername,remarks,dabiaofen)" + "values('"
							+ certno
							+ "','"
							+ gender
							+ "','"
							+ zhiyedate
							+ "','"
							+ lawyerno
							+ "','"
							+ lawyername
							+ "','"
							+ lawyerenname
							+ "','"
							+ systemno
							+ "','"
							+ lawyerno
							+ "','"
							+ certno
							+ "',"
							+ "'"
							+ theoffice
							+ "','"
							+ directunion
							+ "','"
							+ provinceunion
							+ "','"
							+ status
							+ "','"
							+ regsrc
							+ "',now(),'"
							+ createuser
							+ "','"
							+ createusername
							+ "','"
							+ remarks
							+ "','"
							+ dabiaofen
							+ "');";
				else
					sql = "insert into lawyers(certno,gender,lawyerno,lawyername,lawyerenname,systemno,loginname,passwd,"
							+ "theoffice,directunion,provinceunion,status,"
							+ "regsrc,createtime,createuser,createusername,remarks,dabiaofen)" + "values('"
							+ certno
							+ "','"
							+ gender
							+ "','"
							+ lawyerno
							+ "','"
							+ lawyername
							+ "','"
							+ lawyerenname
							+ "','"
							+ systemno
							+ "','"
							+ lawyerno
							+ "','"
							+ certno
							+ "',"
							+ "'"
							+ theoffice
							+ "','"
							+ directunion
							+ "','"
							+ provinceunion
							+ "','"
							+ status
							+ "','"
							+ regsrc
							+ "',now(),'"
							+ createuser + "','" + createusername + "','" + remarks + "','" + dabiaofen + "');";
if(!lawyerno.equals("16052001A0427")&&!lawyerno.equals("16052005A0535")&&sheetname.equals("南阳"))
				stmt.addBatch(sql);

			}
			int ss[]=new int[0];
			try {
				 ss = stmt.executeBatch();
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