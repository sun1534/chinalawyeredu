/**
 * Constants.java
 */

package com.changpeng.common;

/**
 * @author 华锋 2008-2-21 下午02:37:48
 * 
 */
public class Constants {

	public static final String LOGIN_USER = "loginUser";
	public static final String VALIDATE_CODE = "validateCode";

	public static final String ACTION_MESSAGE = "message";
	public static final String ACTION_ERROR = "error";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_NORIGHT = "noright";
	public static final String ACTION_SQLEXCEPTION = "sqlexception";
	public static final String ACTION_EXCEPTION = "exception";
	
	public static final String COOKIE_LOGINNAME="cookieloginname";

	public static final String COOKIE_PASSWORD="cookiepassword";
	/**
	 * 最顶层的权限列表,对应sys_right中的最顶端parent
	 */
	public static final String ROOT_RIGHT = "0";
	
	public static final int ROOT_GROUP = 0;
	
	public static final String COMPANY_NAME="深圳市长鹏计算机软件有限公司";
//	public static String SYS_NAME="执业律师继续教育培训考核系统";
	public static String DEFAULT_SYS_NAME="执业律师继续教育培训考核系统";
	public static String FROMADDR="server";
	public static  int START_YER=2006;
//	public static  String LOCATION="东莞";
	
	
//	public static  boolean HAVELOCAL=true;
	public static  boolean DEFAULT_HAVELOCAL=true;
	/**
	 * 显示各地的律师的照片的路径
	 */
//	public static String LOGO_PATH;
	public static String DEFAULT_LOGO_PATH;
	/**
	 *
	 */
	public static String RESOURCE_PATH;
	
//	public static String INDEX_PIC;
//	public static String TOP_BAR_PIC;
	//反问的url是什么
//	public static String CURRENT_DOMAIN;
	
	
	public static String DEFAULT_INDEX_PIC;
	public static String DEFAULT_TOP_BAR_PIC;
	
	public static String PHOTO_SAVE_PATH;
	
	//反问的url是什么
}