import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * Test.java
 */

/**
 * @author 华锋 2008-6-15 下午10:55:42
 * 
 */
public class Test2 {

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
		
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("c:\\workbook11.csv")));
		
		
		String line="";
int iiiii=0;
		int groupid=10000000;
		while ((line=br.readLine())!=null) {
		  if(line.equals(""))
			  break;
		  
		 String[] sss=line.split(",");
		  
			

			String __userid = sss[0];
			System.out.println(__userid + "=========");
			if (__userid.equals(""))
				break;			
			if(__userid.equals("ID"))
				continue;			
			String userid=__userid;
	
		//	ID,律师姓名,性别2,身份证号3,所属律所4,电话号码5,执业证号6,类型7,会员编号8,
			//培训卡卡号9,在杭州执业开始时间10,已通过培训11,注册情况12,事务所总机电话13,
			//事务所邮政编码14,事务所传真,事务所地址,面积17,负责人18,事务所执业证号,成立时间,所属机关,注册情况
			String username =sss[1].trim();//律师姓名
		
			String sex =sss[2].trim().equals("男") ? "M" : "G";//性别
		
			String certno = sss[3].trim();//	身份证号
			
			String groupname = sss[4].trim();//所属律所
			
			String userphone = sss[5].trim();///	电话号码	
			String lawyerno = sss[6].trim();//	执业证号
			String systemno =sss[8].trim();//	会员编号	
//			String cardno = HSSFCellToString.toString(row.getCell(9));//培训卡卡号
			String zhiyedate = sss[10].trim();//在杭州执业开始时间
//			String zhuceqingkuang = HSSFCellToString.toString(row.getCell(10));//	注册情况
			String groupphone = sss[13].trim();//		事务所总机电话	
			String postcode = sss[14].trim();//	事务所邮政编码
			String groupfax =sss[15].trim();//	事务所传真
			String groupaddress =sss[16].trim();//	事务所地址
			
//			System.out.println(row.getCell(15));
			String contacter = sss[18].trim();//负责人
			String groupenname =sss[19].trim();//事务所执业证号
			if (!grouplist.contains(groupenname)) {

				grouplist.add(groupenname);
				groupmap.put(groupenname, groupid);

			

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

				loggrouprole.println("insert into sys_user_roles(roleid,userid)values(2," + groupid + ");");

				System.out.println("事务所个数：：：：" + (groupid - 10000000));
				
				
				groupid = groupid + 1;
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
			loguserrole.println("insert into sys_user_roles(roleid,userid)values(1," + userid + ");");

			System.out.println("律师个数：：：：" + (iiiii++));

		}
	}
}