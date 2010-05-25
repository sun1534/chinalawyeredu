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
public class ImportWZCardno {

	private static PrintWriter logsystemcard;
	private static PrintWriter logcardchange;

	public static void main(String[] args) throws Exception {
		logsystemcard = new PrintWriter(new FileOutputStream("c:\\systemcard.sql", true), true);
		logcardchange = new PrintWriter(new FileOutputStream("c:\\cardchange.sql", true), true);
		
		importGroups();
		logsystemcard.flush();
		logsystemcard.close();
		logcardchange.flush();
		logcardchange.close();
	}

	public static void importGroups() throws Exception {
		// TODO Auto-generated method stub

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\a.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
	
		int directgroup = 11002221;

		HSSFSheet sheet = wb.getSheetAt(0);
	

		// System.out.println(sheetname + ",,," + parentid);
		short s = sheet.getTopRow();
		Iterator _ite = sheet.rowIterator();
		
		while (_ite.hasNext()) {
		
		
			HSSFRow row = (HSSFRow) _ite.next();
			// 事务所名称 地址 行政联络人 电话 执业证号 会员编号 所属律协

			String lawyername = HSSFCellToString.toString(row.getCell(1)).trim();
			String office = HSSFCellToString.toString(row.getCell(2)).trim();
			String systemno = HSSFCellToString.toString(row.getCell(3)).trim();
			String cardno = HSSFCellToString.toString(row.getCell(4)).trim();
			if (lawyername == null || lawyername.equals(""))
				break;
			String sql1 = "";
//			if (!cardno.equals("")) {
				sql1 = "insert into sys_systemcardno(systemno,cardno) values('" + systemno + "','" + cardno + "');";
				logsystemcard.println(sql1);
//			}
			String sql2 = "update lawyers set cardno='" + cardno + "',systemno='" + systemno + "' where lawyername='"
					+ lawyername + "' and directunion=" + directgroup + ";";
			logcardchange.println(sql2);
			System.out.println(sql2);

		}

	}
}