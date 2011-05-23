import com.changpeng.common.context.Globals;

/**
 * 
 */

/**
 * <pre>
 *
 * </pre> 
 * @author 华锋
 * 2009-7-30-下午10:33:41
 */
public class Test {

	public static void main(String[] args)throws Exception{
		
	String[] s="af#124124#124".split("#");
	System.out.println(s.length);
	
	
	System.out.println(com.changpeng.common.util.MD5.md5("liukang"));
		
//		java.io.PrintWriter  logprovince=new java.io.PrintWriter(new java.io.FileOutputStream("c:\\province.sql",true),true);
//		
//		
//		
//		for(int i=100;i<=599;i++){
//			String ii="991"+i;
//			logprovince.println("insert into sys_user(groupid,password,passkey,status,createuser,createtime,roleid,userid,username,comments,loginname,lawerno) " +
//					"values(99999,'E10ADC3949BA59ABBE56E057F20F883E','123456',0,0,now(),1,"+ii+",'"+ii+"','参加调查:"+ii+"','"+ii+"','"+ii+"');");
//			
//
//		}
//		logprovince.println("insert into sys_user_roles(userid,roleid) select userid,1 from sys_user where userid like '991%';");
//		logprovince.flush();
//		logprovince.close();
	}
}
