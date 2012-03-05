package com.changpeng.lessons.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	public static String lastWeek(){
		Timestamp s=new Timestamp(System.currentTimeMillis());
		   int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(s));
		   int month=Integer.parseInt(new SimpleDateFormat("MM").format(s));
		   int day=Integer.parseInt(new SimpleDateFormat("dd").format(s))-6;
		  
		   if(day<1){
		    month-=1;
		    if(month==0){
		     year-=1;month=12;
		    }
		    if(month==4||month==6||month==9||month==11){
		     day=30+day;
		    }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		    {
		     day=31+day;
		    }else if(month==2){
		     if(year%400==0||(year %4==0&&year%100!=0))day=29+day;
		     else day=28+day;
		    }     
		   }
		   String y = year+"";String m ="";String d ="";
		   if(month<10) m = "0"+month;
		   else m=month+"";
		   if(day<10) d = "0"+day;
		   else d = day+"";
		  
		   return y+"-"+m+"-"+d;
		}
	
	public static void main(String[] args) {
		String aa=lastWeek();
		System.out.println(aa);
	}

}
