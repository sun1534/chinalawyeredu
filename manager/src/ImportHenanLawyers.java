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
public class ImportHenanLawyers {

	private static PrintWriter loglawyers;
	private static PrintWriter loglawyersexist;

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

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\lawyers.xls"));

		int directunion = 8495;
		int provinceunion = 18;
		// 李煦燕 北京市大成律师事务所郑州分所 女 410104620424402 16191988A0025 省直 1985-1-1
		int kk = 0;

		loglawyersexist = new PrintWriter(new FileOutputStream("c:\\lawyersexist.log", true), true);
		Map list = new HashMap();
		for (int i = 0; i < 18; i++) {
			directunion = directunion + i;
			loglawyers = new PrintWriter(new FileOutputStream("c:\\lawyers" + directunion + ".sql", true), true);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(i);

			Iterator _ite = sheet.rowIterator();

			// String line = null;

			// while ((line=br.readLine())!=null) {
			while (_ite.hasNext()) {
				HSSFRow row = (HSSFRow) _ite.next();
				String lawyername = HSSFCellToString.toString(row.getCell(0));// ID

				if (lawyername == null || lawyername.equals(""))
					break;
				if (lawyername.equals("律师姓名"))
					continue;

				String remarks = HSSFCellToString.toString(row.getCell(1));
				String sex = HSSFCellToString.toString(row.getCell(2)).equals("男") ? "M" : "F";
				String certno = HSSFCellToString.toString(row.getCell(3));
				String lawyerno = HSSFCellToString.toString(row.getCell(4));
				String district = HSSFCellToString.toString(row.getCell(5));
				String zhiyedate = HSSFCellToString.toString(row.getCell(6));

				if (list.containsKey(lawyerno)) {
					loglawyersexist.println(lawyerno +">"+list.get(lawyerno)+ "已存在,现在:"+directunion+","+remarks+","+lawyername);
					continue;
				}
				list.put(lawyerno,lawyername+","+directunion);

				String lawyerenname = com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyername);
				String theoffice = "-1";

				int status = 0;
				int regsrc = 0;
				int createuser = 0;
				String createusername = "090826导入";

				String systemno = lawyerno;

				String dabiaofen = "0";
				String sql = "insert into lawyers(lawyerno,lawyername,lawyerenname,systemno,loginname,passwd,"
						+ "theoffice,directunion,provinceunion,status,"
						+ "regsrc,createtime,createuser,createusername,remarks,dabiaofen,gender,zhiyedate)"
						+ "values('"
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
						+ "','" + dabiaofen + "','" + sex + "','" + zhiyedate + "');";

				loglawyers.println(sql);

				System.out.println((kk++) + "=>" + sql);
				//		
			}
		}
	}
}