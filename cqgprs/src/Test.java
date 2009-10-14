import java.sql.Connection;

/**
 * 
 */

/**
 * @author 华锋
 * Oct 10, 2009 2:41:59 PM
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		System.out.println(com.sxit.common.util.MD5.md5(("123456")));
		// TODO Auto-generated method stub
//		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream(new File("c:\\c.txt")),"utf-8"));
//		String line = null;
//
//	Map<String,Integer> map=new HashMap<String,Integer>();
//		while((line=br.readLine())!=null){
//			String date=line.substring(12);
//			System.out.println(date);
//			if(map.containsKey(date)){
//				int now=map.get(date);
//				map.remove(date);
//				map.put(date, now+1);
//			}else{
//				map.put(date, 1);
//			}
//			
//		}
//		
//		System.out.println(map);
//		br.close();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=	java.sql.DriverManager.getConnection("jdbc:oracle:thin:@218.201.8.150:1521:ora92","jf_gprs","jf_gprs");
		System.out.println(con);
		
		
	}

}
