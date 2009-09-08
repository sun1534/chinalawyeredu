import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Test.java
 */

/**
 * @author 华锋 2008-6-15 下午10:55:42
 * 
 */
public class CopyOfTest2Fuck {

	private static List grouplist = new ArrayList();
	private static Map groupmap = new HashMap();

	private static PrintWriter loguser;
	private static PrintWriter logugroup;
	private static PrintWriter logugroupmanager;
	private static PrintWriter loggrouprole;
	private static PrintWriter loguserrole;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		loguser = new PrintWriter(new FileOutputStream("c:\\user.sql", true), true);
		logugroup = new PrintWriter(new FileOutputStream("c:\\group.sql", true), true);
		logugroupmanager = new PrintWriter(new FileOutputStream("c:\\groupmanager.sql", true), true);
		loguserrole = new PrintWriter(new FileOutputStream("c:\\uesrrole.sql", true), true);
		loggrouprole = new PrintWriter(new FileOutputStream("c:\\grouprole.sql", true), true);

		// TODO Auto-generated method stub

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\fuck.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		short s = sheet.getTopRow();
		System.out.println(first + "===" + last + "===top:::" + s);

		Iterator _ite = sheet.rowIterator();

		int groupid = 10000000;

		int iiiii = 0;
		// for(int i=first;i<last;i++){
		while (_ite.hasNext()) {
			// HSSFRow row= sheet.getRow(i);
			HSSFRow row = (HSSFRow) _ite.next();
			
//		Iterator ttt=	row.cellIterator();
//		while(ttt.hasNext()){
//			HSSFCell cell=(HSSFCell)ttt.next();
//			System.out.print(HSSFCellToString.toString(cell)+"*");
//		}
//		System.out.println("");
			
			String __userid = HSSFCellToString.toString(row.getCell(0));//ID
			System.out.println(__userid + "=========");
			if (__userid.equals(""))
				break;			
			if(__userid.equals("ID"))
				continue;			
			String userid=__userid;
	
//	培训卡卡号	在杭州执业开始时间	已通过培训	注册情况	事务所总机电话	事务所邮政编码	事务所传真	事务所地址	面积	负责人	事务所执业证号

//			32904.0*
//			王治国*
//			男*
//			杭州体育场路335号*
//			150204197708190630*
//			浙江万向光明律师事务所*
//			1.381917586E10*
//			310003.0*
//			11012007117104      *
//			专职                *
//			2009-03-25*
//			0.0*
//			已注册*
//			28033080  28033081*
//			310004.0*
//			2.8033083E7*
//			杭州市庆春路136号广利大厦809室*
//			98M*
//			屠有先*
//			11012004200586    *
//			2004-07-30*
//			杭州市司法局*
//			已注册*
			String username = HSSFCellToString.toString(row.getCell(1));//律师姓名
		
			String sex = HSSFCellToString.toString(row.getCell(2)).equals("男") ? "M" : "G";//性别
		
			String certno = HSSFCellToString.toString(row.getCell(4));//	身份证号
			
			String groupname = HSSFCellToString.toString(row.getCell(5));//所属律所
			
			String userphone = HSSFCellToString.toString(row.getCell(6));///	电话号码	
			String lawyerno = HSSFCellToString.toString(row.getCell(7));//	执业证号
			String systemno = HSSFCellToString.toString(row.getCell(8));//	会员编号	
//			String cardno = HSSFCellToString.toString(row.getCell(9));//培训卡卡号
			String zhiyedate = HSSFCellToString.toString(row.getCell(10));//在杭州执业开始时间
//			String zhuceqingkuang = HSSFCellToString.toString(row.getCell(10));//	注册情况
			String groupphone = HSSFCellToString.toString(row.getCell(13));//		事务所总机电话	
			String postcode = HSSFCellToString.toString(row.getCell(14));//	事务所邮政编码
			String groupfax = HSSFCellToString.toString(row.getCell(15));//	事务所传真
			String groupaddress = HSSFCellToString.toString(row.getCell(16));//	事务所地址
			
//			System.out.println(row.getCell(15));
			String contacter = HSSFCellToString.toString(row.getCell(18));//负责人
			String groupenname = HSSFCellToString.toString(row.getCell(19));//事务所执业证号
			if (!grouplist.contains(groupenname)) {

				grouplist.add(groupenname);
				groupmap.put(groupenname, groupid);

				groupid = groupid + 1;

				logugroup
						.println("insert into sys_group(groupid,groupname,groupenname,contacter,phone,fax,comments)values('"
								+ groupid
								+ "','"
								+ groupname
								+ "','"
								+ groupenname
								+ "','"
								+ contacter
								+ "','"
								+ groupphone + "','" + groupfax + "','" + groupaddress + "');");
				logugroupmanager
						.println("insert into sys_user(userid,groupid,loginname,username,gender,certno,zhiyedate,systemno,mobile,passkey)values('"
								+ groupid
								+ "','"
								+ groupid
								+ "','"
								+ groupenname
								+ "','"
								+ groupname
								+ "','"
								+ sex
								+ "','','','','" + userphone + "','" + postcode + "');");

				loggrouprole.println("insert into sys_user_roles(roleid,userid)values(2," + groupid + ")");

				System.out.println("事务所个数：：：：" + (groupid - 10000000));
			}

			loguser
					.println("insert into sys_user(userid,groupid,loginname,username,gender,lawerno,certno,zhiyedate,roleid,systemno,mobile,passkey)values('"
							+ userid
							+ "','"
							+ groupmap.get(groupenname)
							+ "','"
							+ lawyerno
							+ "','"
							+ username
							+ "','"
							+ sex
							+ "','"
							+ lawyerno
							+ "','"
							+ certno
							+ "','"
							+ zhiyedate
							+ "','1','"
							+ systemno
							+ "','" + userphone + "','" + postcode + "');");
			loguserrole.println("insert into sys_user_roles(roleid,userid)values(1," + userid + ")");

			System.out.println("律师个数：：：：" + (iiiii++));

		}
	}
}