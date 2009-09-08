/**
 * Globals.java
 */

package com.changpeng.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 华锋 2008-2-22 下午02:16:54
 * 
 */
public class Globals {
	private static String configName = "classpath:spring/applicationContext*.xml";
//	private static ApplicationContext context;
//	private static volatile boolean isok;
	static {
//		FileSystemXmlApplicationContext;
		
//		 context =new FileSystemXmlApplicationContext(configName);
		 
	}

	/**
	 * Convenience method to bind objects in Actions
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
	}

	/**
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public static Object getMainBean(String name) {
//		if(!isok){
//			 context = new ClassPathXmlApplicationContext(configName);
//			 isok=true;
//		}
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
//		return context.getBean(name);
	}


	public static void main(String args[]) throws Exception {
		
		ApplicationContext	 context = new ClassPathXmlApplicationContext(configName);
		
		System.out.println(context.getBean("basicDAO"));
		
	}
}
