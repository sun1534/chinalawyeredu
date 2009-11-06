/**
 * Globals.java
 */

package com.changpeng.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 华锋 2008-2-22 下午02:16:54
 * 
 */
public class Globals {

	private static String configName = "classpath:spring/applicationContext*.xml";
	private static ApplicationContext context;

	// static {
	// context = new ClassPathXmlApplicationContext(configName);
	// }

	/**
	 * Convenience method to bind objects in Actions
	 * 
	 * @param name
	 * @return
	 */
	public static Object getWebBean(String name) {

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
	}

	public static Object getMainBean(String name) {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(configName);
		}
		return context.getBean(name);
	}

	public static void main(String args[]) throws Exception {

	}
}
