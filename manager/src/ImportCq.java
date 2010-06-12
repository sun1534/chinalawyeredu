import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * 
 */

/**
 * @author 华锋
 * Aug 22, 2009-1:11:33 PM
 *
 */
public class ImportCq {
	private static List grouplist = new ArrayList();
	private static Map groupmap = new HashMap();

	private static PrintWriter loguser;
	private static PrintWriter logugroup;
	private static PrintWriter logugroupmanager;
	private static PrintWriter loggrouprole;
	private static PrintWriter loguserrole;
	
	
	public static void main(String[] args)throws Exception{
		System.out.println("[[[[[[[[[[[[[");
//		importGroups();
		importLawyers();
	}
	
	
	public static void importGroups()throws Exception{
//		loguser = new PrintWriter(new FileOutputStream("c:\\user.sql", true), true);
//		logugroup = new PrintWriter(new FileOutputStream("c:\\group.sql", true), true);
//		logugroupmanager = new PrintWriter(new FileOutputStream("c:\\groupmanager.sql", true), true);
//		loguserrole = new PrintWriter(new FileOutputStream("c:\\uesrrole.sql", true), true);
//		loggrouprole = new PrintWriter(new FileOutputStream("c:\\grouprole.sql", true), true);

		// TODO Auto-generated method stub
		
		PrintWriter	logugroup = new PrintWriter(new FileOutputStream("c:\\group.sql", true), true);

//		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\cq.xls"));
//		HSSFWorkbook wb = new HSSFWorkbook(fs);
//		HSSFSheet sheet = wb.getSheetAt(0);
//	
//		short s = sheet.getTopRow();
//	
//
//		Iterator _ite = sheet.rowIterator();

		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream("c:\\cq.csv")));
		String line=null;
		int parentid = 24;
		int directgroup=42;
		
		while ((line=br.readLine())!=null) {
		
//			HSSFRow row = (HSSFRow) _ite.next();
//			String serialid = HSSFCellToString.toString(row.getCell(0));//ID
//		
//			if (serialid.equals(""))
//				break;			
//			if(serialid.equals("ID"))
//				continue;			
//		
//			String groupname = HSSFCellToString.toString(row.getCell(1));//律师姓名
//		
//			String systemno = HSSFCellToString.toString(row.getCell(2));
			
			String[] s=line.split(",");
			String serialid=s[0];
			String groupname=s[1];
			String systemno=s[2];
		
		
			System.out.println(serialid+">>"+groupname+">>"+systemno);
			
			String contacter="";
			String groupphone="";
			String groupfax="";
			String groupaddress="";
			
			
			String postcode="";
			String createuser="090822导入";
		
			String comments="090822导入";
			
			String groupenname =systemno;
			if (!grouplist.contains(groupenname)) {

				
				
				logugroup
						.println("insert into sys_group(parentid,grouplevel,groupname,groupenname,contacter,phone,fax,address,delflag,grouptype,directgroup,postcode,createuser,createtime,systemno,comments)values('"
								+ parentid
								+ "',3,'"
								+ groupname
								+ "','"
								+ groupenname
								+ "','"
								+ contacter
								+ "','"
								+ groupphone + "','" + groupfax + "','" + groupaddress + "',0,1,"+directgroup+",'"+postcode+"','"+createuser+"',now(),'"+systemno+"','"+comments+"');");
						
			}
		}
		
		logugroup.println("insert into sys_user(groupid,roleid,loginname,password,username,status,delflag,provinceid,cityid,officeid,createuserid,createtime,comments,systemno) " +
				" select groupid,1,groupenname,'123456',groupname,0,0,directgroup,parentid,groupid,0,now(),comments,systemno from sys_group where parentid="+parentid+" and grouptype=1;");
		
	}
	
	public static void importLawyers()throws Exception{
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream("c:\\cq.csv")));
		String line=null;
		int directunion = 24;
		int provinceunion=42;
		PrintWriter	loglawyers = new PrintWriter(new FileOutputStream("c:\\lawyers.sql", true), true);

		
		String tempgroup="";
		while ((line=br.readLine())!=null) {
			
			String[] s=line.split(",");
//			1,重庆伟豪律师事务所,杨家学,0023000001
			String serialid=s[0];
			String group=s[1];
			if(group==null||group.equals(""))
			{
				group=tempgroup;
			}else{
				tempgroup=group;
			}
			String lawyername=s[2];
			String systemno=s[3];
//			System.out.println(line);
			System.out.println(serialid+">"+group+">"+lawyername+">"+systemno);
			
			String lawyerenname=com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyername);
			String theoffice="-1";
		
			int status=0;
			int regsrc=0;
			int createuser=0;
			String createusername="090822导入";
			String remarks=group;
			String dabiaofen="0";
			String sql="insert into lawyers(lawyerno,lawyername,lawyerenname,systemno,loginname,passwd," +
					"theoffice,directunion,provinceunion,status," +
					"regsrc,createtime,createuser,createusername,remarks,dabiaofen)" +
					"values('"+systemno+"','"+lawyername+"','"+lawyerenname+"','"+systemno+"','"+systemno+"','"+systemno+"'," +
							"'"+theoffice+"','"+directunion+"','"+provinceunion+"','"+status+"','"+regsrc+"',now(),'"+createuser+"','"+createusername+"','"+remarks+"','"+dabiaofen+"');";
	
			loglawyers.println(sql);
		
		}
		loglawyers.flush();
		loglawyers.close();
	}
	
	
	
}
