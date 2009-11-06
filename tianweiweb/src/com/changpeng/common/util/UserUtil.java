package com.changpeng.common.util;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;


public class UserUtil {

	private static Logger LOG = Logger.getLogger(UserUtil.class);
	private UserService service;
	private static UserUtil instance;

	private UserUtil() {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		service = (UserService) wac.getBean("userService");
	}

	/**
	 * 单实例,通过此方法获取对象的实例
	 * 
	 * @return
	 */
	public static UserUtil getInstance() {
		if (instance == null) {
			instance = new UserUtil();
		}
		return instance;
	}

	 public Userinfo getUserinfo(int userid){
		 Userinfo user=service.getUserinfoById(userid);
		 if(user==null){
		 user=new Userinfo();
		 user.setId(userid);
		 }
		 return user;
	 }

	 public CoreUserDetail getUserdetail(int userid){
		 CoreUserDetail user=service.getUserDetailById(userid);
		 if(user==null){
			 user=new CoreUserDetail();
			 user.setUserid(userid);
		 }
		 return user;
	 }
	/**
	 * 简单的对用户的缓存（时间来检验下这个看看，不行的话，就用上面那个了）
	 * （这里hashmap里存储只name,pic,mobile,role等4个东东
	 * 
	 * 貌似可以，加大tomcat的虚拟堆大小
	 * 
	 * @param classid
	 * @return
	 */
//	public Userinfo getUserinfo(int userid) {
//		// 这里只放1w个,考虑,能放多少,还真不知道.貌似系统启动就要全跑数据库咯,先不管了
//		// System.out.println("==========" + userid);
//		LOG.info("USERINFO个数为:" + USERES.size() + ",userid:" + userid);
//		if (USERES.containsKey(userid)) {
////			int time=24 * 60 * 60 * 1000;
//			int time=60*1000;// 超过一秒取数据库，测试用
//			if (System.currentTimeMillis() - USERES_TIME.get(userid) > time) {
//				Userinfo user = service.getUserinfoById(userid);
//				if (user == null) {
//					user = new Userinfo();
//					user.setId(userid);
//				}
//				USERES.remove(userid);
//				USERES.put(userid, user);
//				USERES_TIME.remove(userid);
//				USERES_TIME.put(userid, System.currentTimeMillis());
//			}
//
//			return USERES.get(userid);
//		} else {
//			Userinfo user = service.getUserinfoById(userid);
//			if (user == null) {
//				user = new Userinfo();
//				user.setId(userid);
//			}
//			USERES.put(userid, user);
//			USERES_TIME.put(userid, System.currentTimeMillis());
//			return user;
//		}
//	}


//	private static Hashtable<Integer, CoreClass> CLASSES = new Hashtable<Integer, CoreClass>(1000);
//	private static Hashtable<Integer, Long> CLASSES_TIME = new Hashtable<Integer, Long>(1000);

	private static Hashtable<Integer, Long> SCHOOLES_TIME = new Hashtable<Integer, Long>(1000);

	public String getTitle(int publishid){
		CoreProduct product=null;
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		if(publish!=null&&publish.getProductid()!=null){
			product=(CoreProduct)service.get(CoreProduct.class, publish.getProductid());
		}
		if(product!=null&&product.getName()!=null)
			return product.getName();
		else
			return "";
		
	}

}