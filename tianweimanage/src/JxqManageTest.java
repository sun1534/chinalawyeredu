/**
 * Globals.java
 */


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxit.common.util.MD5;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

/**
 * @author 华锋 2008-2-22 下午02:16:54
 * 
 */
public class JxqManageTest {
	// private static String configName = "/WEB-INF/applicationContext.xml";
	private static String configName = "classpath:spring/applicationContext*.xml";
	private static boolean init;
	private static ApplicationContext context;
	
		static {
	

//			context = new ClassPathXmlApplicationContext(configName);
	}
	private static StringBuilder sbb = new StringBuilder("");



	/**
	 * Convenience method to bind objects in Actions
	 * 
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
	
		 return context.getBean(name);
	}

	public Object getMainBean(String name) {
//		return context.getBean(name);
		return null;
	}

	StringBuilder sb = new StringBuilder("<ul id='menu'>");

	public static void main(String args[]) throws Exception {

		String s="12||12345||123";
		int idx = s.indexOf("||");
		int lastidx = s.lastIndexOf("||");
		String loginName = s.substring(0, idx);
		String password = s.substring(idx + 2,lastidx);
		int loginid = Integer.parseInt(s.substring(lastidx +2));
		
		System.out.println(loginName);
		System.out.println(password);
		System.out.println(loginid);
		
//		System.out.println(MD5.md5("123456"));
//
//		JxqManageTest g = new JxqManageTest();
//		
//		SysUser user=new SysUser();
//		user.setDelflag(false);
////		user.setDepartment("深讯");
//		user.setEmail("liuhuafeng@sxit.com.cn");
//		user.setEmployeeno("12345678");
//		user.setGender("F");
//		user.setGroupid(0);
//		user.setLocationid(1);
//		user.setLoginname("wangbadan");
//		user.setMobile("13510073023");
//		user.setOfficephone("1245");
//		user.setPassword("123456");
//		
//		SysUserService service=(SysUserService)g.getBean("sysUserService");
//		service.addUserAnnotation(user, 1);
		

	
	}

}
