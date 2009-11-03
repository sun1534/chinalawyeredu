/**
 * Globals.java
 */

package com.changpeng.common.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 华锋 2008-2-22 下午02:16:54
 * 
 */
public class Globals {
	// private static String configName = "/WEB-INF/applicationContext.xml";
	private static String configName = "classpath:spring/applicationContext*.xml";
	private static ApplicationContext context;
	//
	// private static final Map share = new HashMap();
	static {
		 context = new ClassPathXmlApplicationContext(configName);
	}

	/**
	 * Convenience method to bind objects in Actions
	 * 
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		// if (context == null) {
		// context = new ClassPathXmlApplicationContext(configName);
		// }
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
		// return context.getBean(name);
	}

	 public static Object getStaticBean(String name) {
	 if (context == null) {
	 context = new ClassPathXmlApplicationContext(configName);
	 }
	 return context.getBean(name);
	 }

	public static void main(String args[]) throws Exception {

		// System.out.println(MD5.md5("123"));

		// ScheduleTask task = new ScheduleTask();
		// task.run();

		// 获得课程资料

		// Globals g = new Globals();
		// Object o = g.getBean("sysUserService");
		// System.out.println(o.getClass() + "==" + o.getClass().getName());
		// SysUserService service = (SysUserService) o;
		//

		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		// detachedCriteria.setProjection(Projections.projectionList().add(Projections.sum("pxxf")).add(Projections.groupProperty("learnmode"))).add(Restrictions.eq("lawer.userid",
		// 4));
		// BasicService o = (BasicService)g.getBean("basicService");
		// List list=null;
		// list=o.findAllByCriteria(detachedCriteria);
		//		
		// System.out.println(list);
		//		
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i).getClass().getName());
		// Object[] obj=(Object[])list.get(i);
		// for(int k=0;k<obj.length;k++){
		// System.out.print(obj[k]+"=");
		//				
		// }
		// System.out.println("");
		// }
		// DetachedCriteria detachedCriteria=DetachedCriteria.forClass(com.changpeng.models.Lessons.class);
		//		
		// detachedCriteria.setFetchMode("lessonxf",FetchMode.JOIN);
		// detachedCriteria.createAlias("lessonxf", "lessonxf",CriteriaSpecification.LEFT_JOIN);
		// Criterion lhs=Restrictions.eq("lessonxf.lawer.userid", 4);
		// Criterion rhs=Restrictions.eq("lessonxf.lessonid", null);

		// detachedCriteria.add(Restrictions.or(lhs,rhs));
		// detachedCriteria.add(rhs);
		// detachedCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		// String hql=" select lessons,xuefen from com.changpeng.models.Lessons lessons left outer join com.changpeng.models.Lawyerlessonxf xuefen on
		// lessons.lessonid=xuefen.lessonid where (xuefen.lawyer.userid is null or xuefen.lawyer.userid=4";
		// BasicService service=(BasicService)g.getBean("basicService");
		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessonscore.class);
		//		
		// detachedCriteria.setProjection(Projections.projectionList().add(Projections.count("lessons"),"count").add(Projections.groupProperty("lessons")));
		// detachedCriteria.addOrder(Order.desc("count"));
		// detachedCriteria.setFetchMode("lessons",FetchMode.SELECT);
		// List aa=service.findAllByCriteria(detachedCriteria);
		// System.out.println(aa);
		// for(int i=0;i<aa.size();i++){
		// Object[] obj=(Object[])aa.get(i);
		// Lessons lesson=(Lessons)obj[1];
		// int count=Integer.parseInt(obj[0].toString());
		// System.out.println(lesson.getLessonid()+","+count);
		// // for(int j=0;j<obj.length;j++){
		// // Lessons lesson=(Lessons)obj[1];
		// // System.out.print(obj[j].getClass().getName()+"=");
		// // }
		// System.out.println("11====================="+aa.get(i));
		// }
		//		
		// List _list=service.find(hql);
		// List _list=service.findAllByCriteria(detachedCriteria);
		//	
		// System.out.println(_list);
		// Iterator iter = _list.iterator();
		// while ( iter.hasNext() ) {
		// // Map map = (Map) iter.next();
		// // Lessons lesson = (Lessons) map.get(Criteria.ROOT_ALIAS);
		//		
		// Lessons lesson=(Lessons)iter.next();
		// //
		// System.out.println(lesson.getLessonid());
		// System.out.println("lesson.getLessonxf()==="+lesson.getLessonxf());
		// //
		// // Lawyerlessonxf xuefen = (Lawyerlessonxf) map.get("lessonxf");
		// }

		// LawyerlessonxfService lawyerlessonxfService=(LawyerlessonxfService)g.getBean("lawyerlessonxfService");
		// java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
		// Timestamp _from=new java.sql.Timestamp(df.parse("2007-4-2").getTime());
		// Timestamp _end=new java.sql.Timestamp(df.parse("2009-4-2").getTime());
		//		
		// System.out.println(_from);
		//		
		// System.out.println(_end);
		// PaginationSupport page=lawyerlessonxfService.getJifentongji(_from, _end, "", "","", 1, 20,0);
		// list=page.getItems();
		//		
		// System.out.println(list.size());
		//		 
		// for(int i=0;i<list.size();i++){
		// // System.out.println(list.get(i).getClass().getName());
		// Jifentongji tongji=(Jifentongji)list.get(i);
		// if(tongji==null)
		// {
		// System.out.println("tong ji is null........");
		// continue;
		// }
		// JifentongjiId id=tongji.getId();
		//			
		//			
		// System.out.println("id.getGroupname()="+id.getGroupname());
		// System.out.println("id.getBudeng()="+id.getBudeng());
		// System.out.println("id.getDoc()="+id.getDoc());
		// System.out.println("id.getKoufen()="+id.getKoufen());
		// System.out.println("id.getUsername()="+id.getUsername());
		// System.out.println("id.getVideo()="+id.getVideo());
		// System.out.println("id.getXianchang()="+id.getXianchang());
		// System.out.println("id.getZongjifen()="+id.getZongjifen());
		//			

		// Object[] obj=(Object[])list.get(i);
		// for(int k=0;k<obj.length;k++){
		// System.out.print(obj[k]+"=");
		//				
		// }
		// System.out.println("");
	}

	// List list=new ArrayList();
	//		
	// list.add("1");
	// list.add("3");
	// //list.add(3);
	// service.assignRoles(2, list);
	// //service.assignRrights(2, list);
	// System.out.println("=================");
	// Object o = g.getBean("sysRightDAO");
	// SysRightDAO sysRightDAO=(SysRightDAO)o;
	//
	// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRight.class).add(Restrictions.eq("iscommon",true));
	//		
	//
	// // List list = sysRightDAO.find("from com.changpeng.models.system.SysRight right where right.iscommon=1");
	// List list=sysRightDAO.findAllByCriteria(detachedCriteria);
	// for (int i = 0; list != null && i < list.size(); i++) {
	// SysRight right = (SysRight) list.get(i);
	// System.out.println(right.getRightcode());
	// }
	// System.out.println("============");
	// GroupTree tree = new GroupTree();
	// SysGroup group=getLoginUser().getSysGroup();
	// if(group!=null){
	// groupid=group.getGroupid();
	// groupname=group.getGroupenname();
	// }else{
	// groupid=Constants.ROOT_GROUP;
	// groupname=companyName;
	// }
	// List groupList = tree.getChildGroups(0);
	// System.out.println(groupList);
	// }

	// public static void shareObject(String key, Object value) {
	// share.put(key, value);
	// }
	//
	// public static Object getSharedObject(String key) {
	// return share.get(key);
	// }
}
