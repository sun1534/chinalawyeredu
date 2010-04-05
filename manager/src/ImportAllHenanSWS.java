import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class ImportAllHenanSWS {
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

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = java.sql.DriverManager.getConnection(url, user, pass);

		importGroups();

		con.close();
	}

	public static void importGroups() throws Exception {
		// TODO Auto-generated method stub

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\sws.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		int numsheets = wb.getNumberOfSheets();
		int directgroup = 18;
		Statement stmt = con.createStatement();
		Map allgroups = new HashMap();
		for (int i = 0; i < numsheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String sheetname = wb.getSheetName(i);
			if (sheetname.equals("省直") || sheetname.equals("郑州")) {
				continue;
			}
			if (sheetname.toLowerCase().indexOf("sheet") != -1)
				continue;
			int parentid = getGroupid(con, sheetname);
//			System.out.println(sheetname + ",,," + parentid);
			short s = sheet.getTopRow();
			Iterator _ite = sheet.rowIterator();

			while (_ite.hasNext()) {

				HSSFRow row = (HSSFRow) _ite.next();
				// 事务所名称 地址 行政联络人 电话 执业证号 会员编号 所属律协

				String groupname = HSSFCellToString.toString(row.getCell(0)).trim();
				String groupaddress = HSSFCellToString.toString(row.getCell(1)).trim();
				String contacter = HSSFCellToString.toString(row.getCell(2)).trim();
				String groupphone = HSSFCellToString.toString(row.getCell(3)).trim();
				String groupenname = HSSFCellToString.toString(row.getCell(4)).trim();
				String systemno = HSSFCellToString.toString(row.getCell(5)).trim();
				String district = HSSFCellToString.toString(row.getCell(6)).trim();
				String groupfax = "";
				String postcode = "";
				String createuser = "100331导入";
				String comments = "100331导入";
				if (groupname.indexOf("事务所名称") != -1) {
					continue;
				}
				if (groupname == null || groupname.equals(""))
					break;

				if (allgroups.containsKey(groupenname)) {
					System.out.println(sheetname+"律协的执业证号" + groupenname + "，2个事务所雷同：" + allgroups.get(groupenname) + "和"
							+ groupname + ",导入" + allgroups.get(groupenname) + "," + groupname + "修改为：" + groupenname
							+ "_1");
					groupenname = groupenname + "_1";
				}
				allgroups.put(groupenname, groupname);
				String groupsql = "insert into sys_group(parentid,grouplevel,groupname,groupenname,contacter,phone,fax,address,delflag,grouptype,directgroup,postcode,createuser,createtime,systemno,comments,district)values('"
						+ parentid
						+ "',3,'"
						+ groupname
						+ "','"
						+ groupenname
						+ "','"
						+ contacter
						+ "','"
						+ groupphone
						+ "','"
						+ groupfax
						+ "','"
						+ groupaddress
						+ "',0,1,"
						+ directgroup
						+ ",'"
						+ postcode
						+ "','"
						+ createuser + "',now(),'" + systemno + "','" + comments + "','" + district + "')";

				stmt.addBatch(groupsql);

				// System.out.println(groupsql);

			}
			String usersql = "insert into sys_user(groupid,roleid,loginname,password,username,status,delflag,provinceid,cityid,officeid,createuserid,createtime,comments,systemno) "
					+ " select groupid,1,groupenname,'123456',groupname,0,0,directgroup,parentid,groupid,0,now(),comments,systemno from sys_group where parentid="
					+ parentid + " and grouptype=1";
			// System.out.println(usersql);
			stmt.addBatch(usersql);
			stmt.executeBatch();
			stmt.clearBatch();

		}

		stmt.close();

	}
}