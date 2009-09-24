/**
 * 
 */
package com.changpeng.common.sync.website;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 华锋
 * Sep 24, 2009-1:26:55 PM
 *
 */
public class Distionary {

	public static Map<String,String> KNOWLEDGE=new HashMap<String,String>();
	public static Map<String,String> NATION=new HashMap<String,String>();
	
	public static Map<String,String> POLITICS=new HashMap<String,String>();
	public static Map<String,String> STATUS=new HashMap<String,String>();
	
	
	static{
		
		STATUS.put("14","暂停");
		STATUS.put("9","吊销");
		STATUS.put("2","调出");
		STATUS.put("12","结束");	
		STATUS.put("13","待转出");
		STATUS.put("5","停业");	
		STATUS.put("6","注销");
		STATUS.put("1","调入");
		STATUS.put("0","正常");	
		STATUS.put("8","待撤回");
		STATUS.put("7","待派驻");
		STATUS.put("10","失效");	
		STATUS.put("11","延期");		
		STATUS.put("3","派驻");

		
		
		KNOWLEDGE.put("0", "无学历");
		KNOWLEDGE.put("1", "大专以下");
		KNOWLEDGE.put("2", "大专");
		KNOWLEDGE.put("3", "本科");
		KNOWLEDGE.put("4", "硕士");
		KNOWLEDGE.put("5", "博士");
		KNOWLEDGE.put("6", "博士后");
		
		POLITICS.put("0", "中共党员");
				POLITICS.put("1", "中共预备党员");		
				POLITICS.put("2", "共青团员");		
				POLITICS.put("3", "民革会员");	
				POLITICS.put("4", "民盟盟员");	
				POLITICS.put("5", "民建会员");	
				POLITICS.put("6", "民进会员");	
				POLITICS.put("7","农工党党员");	
				POLITICS.put("8", "致公党党员");	
				POLITICS.put("9", "九三学社社员");	
				POLITICS.put("10","台盟盟员");	
				POLITICS.put("11","无党派人士");	
				POLITICS.put("12","群众");		
				POLITICS.put("13","民主党派");
		
				NATION.put("1","汉族");
				NATION.put("2","蒙古族");
				NATION.put("3","回族");
				NATION.put("4","藏族");
				NATION.put("5","维吾尔族");
				NATION.put("6","苗族");
				NATION.put("7","彝族");
				NATION.put("8","壮族");
				NATION.put("9","布依族");
				NATION.put("10","朝鲜族");
				NATION.put("11","满族");
				NATION.put("12","侗族");
				NATION.put("13","瑶族");
				NATION.put("14","白族");
				NATION.put("15","土家族");
				NATION.put("16","哈尼族");
				NATION.put("17","哈萨克族");
				NATION.put("18","傣族");
				NATION.put("19","黎族");
				NATION.put("20","傈僳族");
				NATION.put("21","佤族");
				NATION.put("22","畲族");
				NATION.put("23","高山族");
				NATION.put("24","拉祜族");
				NATION.put("25","水族");
				NATION.put("26","东乡族");
				NATION.put("27","纳西族");
				NATION.put("28","景颇族");
				NATION.put("29","柯尔克孜族");
				NATION.put("30","土族");
				NATION.put("31","达斡尔族");
				NATION.put("32","仫佬族");
				NATION.put("33","羌族");
				NATION.put("34","布朗族");
				NATION.put("35","撒拉族");
				NATION.put("36","毛南族");
				NATION.put("37","仡佬族");
				NATION.put("38","锡伯族");
				NATION.put("39","阿昌族");
				NATION.put("40","普米族");
				NATION.put("41","塔吉克族");
				NATION.put("42","怒族");
				NATION.put("43","乌孜别克族");
				NATION.put("44","俄罗斯族");
				NATION.put("45","鄂温克族");
				NATION.put("46","德昂族");
				NATION.put("47","保安族");
				NATION.put("48","裕固族");
				NATION.put("49","京族");
				NATION.put("50","塔塔尔族");
				NATION.put("51","独龙族");
				NATION.put("52","鄂伦春族");
				NATION.put("53","赫哲族");
				NATION.put("54","门巴族");
				NATION.put("55","珞巴族");
				NATION.put("56","基诺族");
				NATION.put("57","少数民族");

	}
	
}
