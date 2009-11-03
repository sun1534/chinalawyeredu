/**
 * test.java
 */

package com.changpeng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * @author 华锋 2008-5-5 下午01:55:04
 * 
 */
public class test {
	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis()/10000);
		System.out.println((int)9999999999L);
		System.out.println((System.currentTimeMillis()+10234)/10000);
		System.out.println(Integer.MAX_VALUE);
		
		System.out.println(java.net.URLEncoder.encode("王小安"));
		
		System.out.println((int)(System.currentTimeMillis() / 10000));
		
//		BufferedReader fr = new BufferedReader(new FileReader("c:\\20080617.CSV"));
//		String line="";
//		while((line=fr.readLine())!=null){
//			StringTokenizer st=new StringTokenizer(line,",");
////			System.out.println(line+","+st.countTokens());
//			String id=st.nextToken();
//			String name=st.nextToken();
//			String no=st.nextToken().trim();
//			String sql="update user set licenceno='"+no+"' where username='"+name+"';";
//			System.out.println(sql);
//		}

	}

}
