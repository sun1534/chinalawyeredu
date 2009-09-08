package com.changpeng.lessons.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LessonsUtil {

	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static String list2str(List list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() != 0) {
			for(int i=0;i<list.size();i++)
			sb.append(list.get(i)).append(";");
		} else {
			sb.append("0");
		}

		return sb.toString();
	}

	public static List str2list(String str) {
		List list = new ArrayList();
		if (str != null && !str.equals("")&&!str.equals("0")) {
			StringTokenizer st = new StringTokenizer(str, ";");
			while (st.hasMoreTokens()){
			String sttr=st.nextToken().toString();
				list.add(sttr);
			}
		}
		return list;
	}
	
	public static Timestamp str2timestamp(String lessondate){
		Timestamp s=new Timestamp(System.currentTimeMillis());
		try{
			s=new Timestamp(df.parse(lessondate).getTime());
		}catch(Exception ee){
			System.out.println(ee);
		}
		
		return s;
	}
	
	public static void timestamp2str(Timestamp timestamp,String date,String time){
		String s=df.format(timestamp);
		
		int index=s.indexOf(" ");
		date=s.substring(0,index);
		time=s.substring(index+1);
		
	}
}
