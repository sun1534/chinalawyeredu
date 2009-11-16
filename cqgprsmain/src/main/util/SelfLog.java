/**
 * 
 */
package main.util;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author 华锋 Nov 4, 2009-11:27:37 PM
 * 
 */
public class SelfLog {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMddHHmmss");

	private java.io.PrintWriter ERRORLOG = null;
	private java.io.PrintWriter INFOLOG = null;
	private static String SEPARATOR = System.getProperty("file.separator");
	/**
	 * 每几天产生一个info文件,最多5m大小
	 */
	private static int LOGSIZE = 1024 * 1024 * 5;
	public static String LOGDIR;

	private static SelfLog log;
	public static SelfLog getInstance(){
		if(log==null){
			log=new SelfLog();
		}
		return log;
	}
	
	private SelfLog() {
		
		String infofilename = LOGDIR + SEPARATOR + "/info.log";
		String errorfilename = LOGDIR + SEPARATOR + "/error.log";
		try {
			File infofile = new File(infofilename);
			File errorfile = new File(errorfilename);
			if (!infofile.exists()||infofile.length()<LOGSIZE) {
				INFOLOG = new java.io.PrintWriter(new java.io.FileOutputStream(infofilename, true), true);
			}else {
			    infofile.renameTo(new File(infofilename+dfdate.format(new Date())));
				INFOLOG = new java.io.PrintWriter(new java.io.FileOutputStream(infofilename, true), true);
            }
			if (!errorfile.exists()||errorfile.length()<LOGSIZE) {
				ERRORLOG = new java.io.PrintWriter(new java.io.FileOutputStream(errorfilename, true), true);
			}else {
				errorfile.renameTo(new File(errorfilename+dfdate.format(new Date())));
			    ERRORLOG = new java.io.PrintWriter(new java.io.FileOutputStream(infofilename, true), true);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public  void info(String msg) {
		INFOLOG.println(df.format(new Date())+"=>"+msg);
	}

	public  void error(String msg) {
		ERRORLOG.println(df.format(new Date())+"=>"+msg);
	}
	
	public  void error(String msg,Exception e) {
		ERRORLOG.println(df.format(new Date())+"=>"+msg);
		e.printStackTrace(ERRORLOG);
	}

}
