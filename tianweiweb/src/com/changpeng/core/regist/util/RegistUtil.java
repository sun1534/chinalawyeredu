/**
 * @author 肖云亮
 * 2009-5-20 下午04:36:36
 */
package com.changpeng.core.regist.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 肖云亮
 * 2009-5-20 下午04:36:36
 */
public  class RegistUtil {

	public static Map<Integer,String> GradeNameMap=new LinkedHashMap<Integer,String>();
	static{
		
		GradeNameMap.put(1, "一年级");
		GradeNameMap.put(2, "二年级");
		GradeNameMap.put(3, "三年级");
		GradeNameMap.put(4, "四年级");
		GradeNameMap.put(5, "五年级");
		GradeNameMap.put(6, "六年级");
		GradeNameMap.put(7, "七年级");
		GradeNameMap.put(8, "八年级");
		GradeNameMap.put(9, "九年级");
		GradeNameMap.put(10, "初一");
		GradeNameMap.put(11, "初二");
		GradeNameMap.put(12, "初三");
		
	}
	
	/**
	 * 获取年级名字
	 * 
	 * @return
	 */
	public static String getGradename(int gradeid) {
		if( GradeNameMap.containsKey(gradeid))
			return GradeNameMap.get(gradeid);
		else
			return "NULL";
//		String gradeName = "一年级";
//		switch (gradeid) {
//		case 1:
//			gradeName = "一年级";
//			break;
//		case 2:
//			gradeName = "二年级";
//			break;
//		case 3:
//			gradeName = "三年级";
//			break;
//		case 4:
//			gradeName = "四年级";
//			break;
//		case 5:
//			gradeName = "五年级";
//			break;
//		case 6:
//			gradeName = "六年级";
//			break;
//		case 7:
//			gradeName = "七年级";
//			break;
//		case 8:
//			gradeName = "八年级";
//			break;
//		case 9:
//			gradeName = "九年级";
//			break;
//		case 10:
//			gradeName = "初一";
//			break;
//		case 11:
//			gradeName = "初二";
//			break;
//		case 12:
//			gradeName = "初三";
//			break;
//		default:
//			gradeName = "一年级";
//			break;
//		}
//		return gradeName;
	}
	
	
	public static String getRamCode() {
		StringBuffer sRand = new StringBuffer();
		// 生成随机类
		Random random = new Random();
		// 取随机产生的认证码(4位数字)
		for (int i = 0; i < 4; i++) {
			int rand =random.nextInt(10);
			sRand.append(rand);
		}
		return sRand.toString();
	}
	
	/**
	 * 获取班级名字
	 * 
	 * @return
	 */
	public static String getClassname(int gradeid, String classNameIncludeGrade) {

		String gradeName=GradeNameMap.get(gradeid);
		if(gradeName==null)
			return "";
		int j = gradeName.length();
		int index=classNameIncludeGrade.indexOf(gradeName);
		String onlyClassName=classNameIncludeGrade.substring(index+j);
		System.out.println("onlyClassName==="+onlyClassName);
		return onlyClassName;
		
//		String gradeName = "一年级";
//		String classname = "一班";
//		int i = 0;
//		int j = 0;
//		switch (gradeid) {
//		case 1:
//			gradeName = "一年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 2:
//			gradeName = "二年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 3:
//			gradeName = "三年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 4:
//			gradeName = "四年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 5:
//			gradeName = "五年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 6:
//			gradeName = "六年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 7:
//			gradeName = "七年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 8:
//			gradeName = "八年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 9:
//			gradeName = "九年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 10:
//			gradeName = "初一";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 11:
//			gradeName = "初二";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		case 12:
//			gradeName = "初三";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		default:
//			gradeName = "一年级";
//			j = gradeName.length();
//			i = className.indexOf(gradeName);
//			classname = className.substring((i + j));
//			break;
//		}
//		return classname;
	}
}
