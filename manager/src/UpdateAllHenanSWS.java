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
public class UpdateAllHenanSWS {

	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8";
	static String user = "root";
	static String pass = "password";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = java.sql.DriverManager.getConnection(url, user, pass);

		importGroups();

		con.close();
	}

	public static void importGroups() throws Exception {
		// TODO Auto-generated method stub

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\group.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		int numsheets = wb.getNumberOfSheets();
		int directgroup = 18;
		Statement stmt = con.createStatement();
		Map allgroups = new HashMap();
		for (int i = 0; i < numsheets; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String sheetname = wb.getSheetName(i);
		
			// if (sheetname.equals("省直") || sheetname.equals("郑州")) {
			// continue;
			// }
			if (sheetname.toLowerCase().indexOf("sheet") != -1)
				continue;

			short s = sheet.getTopRow();
			Iterator _ite = sheet.rowIterator();
			System.out.println(sheetname);
			while (_ite.hasNext()) {

				HSSFRow row = (HSSFRow) _ite.next();
				// 事务所名称 地址 行政联络人 电话 执业证号 会员编号 所属律协

				String groupname = HSSFCellToString.toString(row.getCell(0)).trim();

				String groupenname = HSSFCellToString.toString(row.getCell(1)).trim();

				if (groupname.equals("律师事务所") ) {
					continue;
				}
				if (groupname == null || groupname.equals(""))
					break;

				String groupsql = "update sys_group set groupenname='" + groupenname + "' where groupname='"
						+ groupname + "' and directgroup=18";
//System.out.println(groupsql);
				stmt.addBatch(groupsql);

				// System.out.println(groupsql);

			}
			// String usersql = "update sys_user a inner join sys_group b on
			// a.officeid=b.groupid set a.loginname=b.groupenname where
			// b.grouptype=1 and a.provinceid=18";
			// System.out.println(usersql);
			try{
//			stmt.addBatch(usersql);
			int ss[]=stmt.executeBatch();
			for(int ii=0;ii<ss.length;ii++){
				System.out.print(ss[ii]+" ");
			}
			System.out.println("");
			stmt.clearBatch();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		stmt.close();

	}
}