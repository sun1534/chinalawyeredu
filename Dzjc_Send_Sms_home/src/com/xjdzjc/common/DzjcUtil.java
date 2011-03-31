package com.xjdzjc.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class DzjcUtil {
	
	public static int   SEND_DETAIL=0;  //发送详细
	public static int   SEND_SIMPLE=1;  //发送统计
	public static int   SEND_PROMPT=2;  //发送提示
	
	
	public static int   MT_FLAG=9;  //9表示人工不干预，0表示人工干预发详细，1表示人工干预发统计
	public static int   DAY = 1000 * 60 * 60 * 24;
	
//	public static	int DAY = 1000 * 60 * 5;
	
	public static Properties SQL_P = new Properties();
	//黑名单 放到内存在
	public static HashMap<String, String> black_mobile=new HashMap<String, String>();
	public static HashMap<String, String> AUTO_MT_FLAG=new HashMap<String, String>();
	static 	
	{
		AUTO_MT_FLAG.put("jwt_yili", "伊犁");
		AUTO_MT_FLAG.put("jwt_wusu", "乌苏");
		AUTO_MT_FLAG.put("jwt_tulufan", "吐鲁番");
		AUTO_MT_FLAG.put("jwt_kuitun", "奎屯");
		AUTO_MT_FLAG.put("jwt_kuerle", "库尔勒");
		AUTO_MT_FLAG.put("jwt_hami", "哈密");
		AUTO_MT_FLAG.put("jwt_bole", "博乐");
		AUTO_MT_FLAG.put("jwt_alt", "阿勒泰");
		AUTO_MT_FLAG.put("jwt_hetian", "和田");
		AUTO_MT_FLAG.put("jwt_atushi", "阿图什");		
	}
	
	public static void readSql() {
		InputStream in;
		try {
			in = DzjcUtil.class.getClassLoader().getResourceAsStream("templates.properties");
			SQL_P.load(in);// 属性文件将该流加入的可被读取的属性中
			in.close();// 读完了关闭
			// Iterator iter = SQL_P.entrySet().iterator();
			// while (iter.hasNext()) {
			// Entry<String, String> object = (Entry<String, String>)
			// iter.next();
			// String fixvalue = object.getValue();
			// String fixname = object.getKey();
			// System.out.println(fixname + "=" + fixvalue);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
