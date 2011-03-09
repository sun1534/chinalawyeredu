/**
 * Globals.java
 */

package com.uu800.webbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 华锋 2008-2-22 下午02:16:54
 * 
 */
public class WebContextWrapper {

	private static String configName = "classpath:spring/applicationContext*.xml";
	private static ApplicationContext context;

/**
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
	 * @param name
	 * @return
	 */
	public static Object getBeanWithoutWeb(String name) {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(configName);
		}
		return context.getBean(name);
	}

	public static void main(String args[]) throws Exception {

	}
}
