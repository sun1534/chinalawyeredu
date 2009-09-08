import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
public class ImportHenanGroups {

	private static PrintWriter logugroup;
	private static PrintWriter logugroupexist;

	public static void main(String[] args) throws Exception {

		importGroups();
	}

	public static void importGroups() throws Exception {

		System.out.println(System.currentTimeMillis() / 1000);
		// loguser = new PrintWriter(new FileOutputStream("c:\\user.sql", true),
// true);
		//
		// logugroupmanager = new PrintWriter(new
		// FileOutputStream("c:\\groupmanager.sql", true), true);
		// loguserrole = new PrintWriter(new
		// FileOutputStream("c:\\uesrrole.sql", true), true);
// loggrouprole = new PrintWriter(new FileOutputStream("c:\\grouprole.sql",
		// true), true);

		// TODO Auto-generated method stub

		// PrintWriter logugroup = new PrintWriter(new
		// FileOutputStream("c:\\group.sql", true), true);
		//

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\groups.xls"));

		int parentid = 8495;
		int directgroup = 18;
		int kk = 0;
		Map list = new HashMap();
		logugroupexist = new PrintWriter(new FileOutputStream("c:\\groupexist.log", true), true);

		for (int i = 0; i < 18; i++) {
			parentid = parentid + i;
			logugroup = new PrintWriter(new FileOutputStream("c:\\group" + parentid + ".sql", true), true);

			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(i);

			//	
			// short s = sheet.getTopRow();
			//	
			//
			Iterator _ite = sheet.rowIterator();

			// java.io.BufferedReader br = new java.io.BufferedReader(new
			// java.io.InputStreamReader(new FileInputStream("c:\\cq.csv")));
			String line = null;

			// while ((line=br.readLine())!=null) {
			while (_ite.hasNext()) {
				HSSFRow row = (HSSFRow) _ite.next();
				String groupname = HSSFCellToString.toString(row.getCell(0));// ID

				if (groupname == null || groupname.equals(""))
					break;
				if (groupname.trim().equals("事务所名称"))
					continue;
				String groupaddress = HSSFCellToString.toString(row.getCell(1));
				String contacter = HSSFCellToString.toString(row.getCell(2));
				String groupphone = HSSFCellToString.toString(row.getCell(3));
				String groupenname = HSSFCellToString.toString(row.getCell(4));
				String district = HSSFCellToString.toString(row.getCell(5));

				String systemno = groupenname;

				if (list.containsKey(groupenname)) {
					logugroupexist.println(groupenname +">"+ list.get(groupenname)+ "已存在,现在:"+ groupname+">"+parentid);
					continue;
				}
				list.put(groupenname, groupname + "," + parentid);

				String groupfax = "";
				//			
				//			
				String postcode = "";
				String createuser = "090822导入";
				//		
				String comments = "090822导入";
				//			

				//
				//				
				//				
				logugroup
						.println("insert into sys_group(parentid,grouplevel,groupname,groupenname,contacter,phone,fax,address,delflag,grouptype,directgroup,postcode,createuser,createtime,systemno,comments,district)values('"
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
								+ createuser
								+ "',now(),'"
								+ systemno
								+ "','"
								+ comments
								+ "','"
								+ district + "');");

				System.out.println("i=>kk====>" + i + "=>" + (kk++));

			}

			//		
			logugroup
					.println("insert into sys_user(groupid,roleid,loginname,password,username,status,delflag,provinceid,cityid,officeid,createuserid,createtime,comments,systemno) "
							+ " select groupid,1,groupenname,'E10ADC3949BA59ABBE56E057F20F883E',groupname,0,0,directgroup,parentid,groupid,0,now(),comments,systemno from sys_group where parentid="
							+ parentid + " and grouptype=1;");
			//		
		}
	}
}