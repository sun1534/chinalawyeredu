package com.changpeng.common.sysdata;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CommonData {
	public static String Staticpath="..";
	public static String Resourcepath="..";
	
	
	public static String LOGINCOOKIE="logincookie";
	
	public static int CURPID=240;
	public static String CURPNAME="辽宁";
//	public static String downloadpath;
//	public static String ippath;
//	public static String defaultpath;
//	public static String hebeipath;
//	public static String liaoningpath;
//	public static String shanxipath;
	
	public static List Courses;
	public static List Grades;
	//public static List Publishers;
	public static Properties properties;
	//public static List IpList_temp;
	
	public static Map<Integer,String> Phase;
	
	public static Map<Short,String> Subjects=new LinkedHashMap<Short,String>();
	
	public static Map<Short,String> UserRoles=new LinkedHashMap<Short,String>();
	
	public static String DEFAULT_ENT_LOGO="/upload/logo/3_80_0.gif";
	public static String DEFAULT_ENT_PIC="/upload/logo/2_80_0.gif";
	public static String DEFAULT_FAM_PIC="/upload/logo/1_80_0.gif";
		
	public static String DEFAULT_LOGO_URL="logo";
	public static String DEFAULT_PIC_URL="pic";
	public static String DEFAULT_ALBUM_PIC="/upload/logo/0_80_0.gif";
	static{
		Phase=new HashMap<Integer,String>();
		Phase.put(1, "小学");
		Phase.put(2, "初中");
		
		UserRoles.put(SysUserRole.STUDENT, "同学");
		UserRoles.put(SysUserRole.PARENT, "家长");
		UserRoles.put(SysUserRole.TEACHER, "老师");
		UserRoles.put(SysUserRole.PARENT_TEACHER, "老师");
		
		
		Subjects.put((short)1,"语文");
		Subjects.put((short)2,"数学");
		Subjects.put((short)3,"英语");
		Subjects.put((short)4,"政治");
		Subjects.put((short)5,"历史");
		Subjects.put((short)6,"地理");
		Subjects.put((short)7,"物理");
		Subjects.put((short)8,"化学");
		Subjects.put((short)9,"生物");
		Subjects.put((short)10,"自然科学");
	}
}
