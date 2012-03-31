/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sun.util.logging.resources.logging;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.context.Globals;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.LessonsUtil;
import com.changpeng.models.Articles;
import com.changpeng.models.Forum;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * userlogin成功后,redirect到这个页面。防止刷新，重复登录
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class IndexPageAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexPageAction.class);
	private BasicService basicService = null;
	private JifenTime jifentime;

	public JifenTime getJifentime() {
		return jifentime;
	}
	
	
	public String getTopbarpic(){
		
		return super.webpara.getTopbarpic();
	}
	
	
	public IndexPageAction(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		basicService = (BasicService) this.getBean("basicService");
		
		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
		this.lawyer = (Lawyers)basicService.get(Lawyers.class, this.getLoginUser().getLawyerid());
		//this.lawyer = this.getLoginUser();
		LOG.info(lawyer.getLawyerenname()+ "进入首页成功......");
		com.changpeng.lessons.util.CommonDatas.getAlllessons();
		com.changpeng.common.CommonDatas.getGroups();


		// 根据查询的年来查,默认为当前时间所在的积分年

		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyer.getDirectunion());
		//需要的总达标分
		needfen = params.getDabiaofen();
		//需要的现场达标分
		needxcfen=params.getLocalfen();
		jifentime = CommonDatas.getJifenTime(0, params.getNianshen());

		debug("起始时间:::" + jifentime.getStartstr() + ",终止时间::::" + jifentime.getEndstr());

		DetachedCriteria detachedCriteria = null;

//		nowxuefen = xfservice.getLawyerZongjifen(this.lawyers.getLawyerid(), jifentime.getStart(), jifentime.getEnd());
		
		//当前总积分
		nowxuefen = xfservice.getLawyerZongjifen(this.lawyer.getLawyerid(), jifentime.getNianshenyear());
		//当前现场分
		nowxcxuefen=xfservice.getLawyerXianchangJifen(this.lawyer.getLawyerid(), jifentime.getNianshenyear());
		
		
		


//		detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true)).add(Restrictions.eq("provinceid",lawyers.getProvinceunion()));
		detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true)).add(Restrictions.eq("cityid",lawyer.getCityid()));
		
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		PaginationSupport page = basicService.findPageByCriteria(detachedCriteria, 6, 1);
		this.forumList = page.getItems();

		// 1是通知2是帮助
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(
				Restrictions.eq("type", 2));
		detachedCriteria.addOrder(Order.asc("listorder"));
		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.helpList = page.getItems();
		// 获取重要通知,我的事务所发布的或者我的市律协发布的或者我的省律协发布的,也包括系统发布滴
		
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 0)).add(
				Restrictions.eq("type", 1)).add(Restrictions.eq("toshouye", true));
		Criterion province = Restrictions.in("thegroup", new Integer[] { this.getLoginUser().getProvinceunion(),
				this.getLoginUser().getDirectunion(), this.getLoginUser().getTheoffice(), 0 });
		Criterion c = Restrictions.eq("provinceid", 0);
	   detachedCriteria.add(Restrictions.or(c, province));
	
		
	
		detachedCriteria.addOrder(Order.desc("createtime"));
		List list = basicService.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() != 0) {
			this.tongzhi = (Articles) list.get(0);
		}
		// 我的所属市律协
		SysGroup group = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getDirectunion());

		
//		LessonsService lessonsService = (LessonsService) this.getBean("lessonsService");
//		PaginationSupport __page = lessonsService.getPages(group, -1, 0, 0, null, null, 6, 1,null,null);
		//这里的课程显示为本省的或者本市的，不分享的
		detachedCriteria=DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag", false));
		detachedCriteria.add(Restrictions.eq("lessonstyle", 2));
		//detachedCriteria.add(Restrictions.in("groupid", new Object[]{this.getLoginUser().getProvinceunion(),this.getLoginUser().getDirectunion()}));
		detachedCriteria.addOrder(Order.desc("lessondate"));
		PaginationSupport __page =basicService.findPageByCriteria(detachedCriteria,4, 1);
		
		this.lessonList = __page.getItems();
		
		//推荐课程
		detachedCriteria=DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag", false));
		detachedCriteria.add(Restrictions.eq("lessonstyle", 2));
		detachedCriteria.add(Restrictions.eq("istuijian", 1));
		//detachedCriteria.add(Restrictions.in("groupid", new Object[]{this.getLoginUser().getProvinceunion(),this.getLoginUser().getDirectunion()}));
		detachedCriteria.addOrder(Order.desc("lessondate"));
		PaginationSupport tuijian__page =basicService.findPageByCriteria(detachedCriteria, 4, 1);
		this.lessonTuijianList=tuijian__page.getItems();
		
		
		//课件类型数量统计
		String sql1="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and lessontype like '1%' ";
		lessonType1= basicService.getCountByQuery(sql1);
		
		String sql2="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and lessontype like '2%' ";
		lessonType2= basicService.getCountByQuery(sql2);
		
		String sql3="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and lessontype like '3%' ";
		lessonType3= basicService.getCountByQuery(sql3);
		
		String sql5="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and lessontype=5 ";
		lessonType5= basicService.getCountByQuery(sql5);
		
		String sql6="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and (lessontype=6  or lessontype=7) ";
		lessonType6= basicService.getCountByQuery(sql6);
		
		String sql8="select count(lessonid) as count from Lessons where (lessonstyle=2  or lessonstyle=3) and deleteflag=0 and  (lessontype=4 or lessontype=8) ";
		lessonType8= basicService.getCountByQuery(sql8);
		
		
		
		//最受欢迎课程
		LessonsService service=(LessonsService)getBean("lessonsService");
		PaginationSupport page1= service.getLessonStatic(0,  null, null, 1, 6);		
		this.lessonPlayNum=page1.getItems();
		
		
		//一周上升课件
		final DateFormat df1=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Timestamp s=new Timestamp(System.currentTimeMillis());
		System.out.println(df1.format(s));
		Timestamp today=Timestamp.valueOf((df1.format(s)));
		Timestamp weekday=Timestamp.valueOf(LessonsUtil.lastWeek()+" 24:00:00");
		PaginationSupport page3= service.getLessonStatic(0,  today, weekday, 1, 7);		
		this.lessonWeekPlayNum=page3.getItems();
		
		// 好评课程，这里也只要显示共享给我的，也就是共享到市律协和省律协的

		String sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 and exists(select lessonid from lessonshared b where a.lessonid=b.lessonid and b.groupid in("
				+ group.getGroupid() + "," + group.getParentid() + "))group by a.lessonid order by count desc limit 10";
		this.goodlessonList = basicService.findBySqlQuery(sql);
		
		
		
		
		
		// this.goodlessonList = page.getItems();
//		System.out.println("其他的");
//		toppic="/syspic/cntop.gif";
//		System.out.println("la: Provinceunion:::"+lawyer.getProvinceunion());
//		System.out.println("la: Districtid:::"+lawyer.getDirectunion());
//		if(lawyer.getProvinceunion()==22){//广西全省
//			toppic="/syspic/gxtop.gif";
//			System.out.println("广西全省");
//		}
//		if(lawyer.getProvinceunion()==18){//河南全省
//			toppic="/syspic/hentop.gif";
//			System.out.println("河南全省");
//		}
//		
//		 if(lawyer.getProvinceunion()==23){//海南全省
//			toppic="/syspic/hntop.gif";
//			System.out.println("海南全省");
//		}
//		 if(lawyer.getProvinceunion()==42 && lawyer.getDirectunion()==24){//重庆省重庆市
//			toppic="/syspic/cqtop.gif";
//			System.out.println("重庆省重庆市");
//		}
//		 if(lawyer.getProvinceunion()==21 && lawyer.getDirectunion()==37){//广东省东莞市
//			toppic="/syspic/dgtop.jpg";
//			System.out.println("广东省东莞市");
//		}
//		 if(lawyer.getProvinceunion()==19){//湖北全省
//			toppic="/syspic/hubtop.gif";
//			System.out.println("湖北全省");
//		}
//		 if(lawyer.getDirectunion()==8078){//浙江省杭州市
//
//			toppic="/syspic/hztop.jpg";
//			System.out.println("浙江省杭州市");
//		}
//		 if(lawyer.getDirectunion()==11002221){//浙江省温州市
//			toppic="/syspic/wztop.gif";
//			System.out.println("浙江省温州市");
//		}
//		
//		 System.out.println(lawyer.getDirectunion());
//		if(lawyer.getDirectunion()==8079){//吉林省长春市
//			
//			toppic="/syspic/cctop.jpg";
//			System.out.println("吉林省长春市");
//		}
//		 
//		if(lawyer.getProvinceunion()==12 && lawyer.getDirectunion()==10325){//江苏省南京市
//			toppic="/syspic/njtop.gif";
//			System.out.println("江苏省南京市");
//		}
//		
//		
//		
//		//toppic=com.changpeng.common.Constants.TOP_BAR_PIC;
//		System.out.println("bbb:"+toppic);
//		LOG.info("toppic::::"+toppic);
//		
//		
//		
//
//		LOG.info("toppic::::"+toppic);
//		LOG.info("参数:" + com.changpeng.common.CommonDatas.SysParameter);
//		LOG.info("URL:" + com.changpeng.common.CommonDatas.SysUnionparams.keySet());
//		if (com.changpeng.common.CommonDatas.SysParameter.containsKey("topbarpic")) {
//			System.out.println(Constants.TOP_BAR_PIC);
//			Constants.TOP_BAR_PIC = com.changpeng.common.CommonDatas.SysParameter.get("topbarpic").toString();
//			System.out.println(Constants.TOP_BAR_PIC);
//			Constants.DEFAULT_TOP_BAR_PIC=Constants.TOP_BAR_PIC;
//			System.out.println(Constants.TOP_BAR_PIC);
//		}

		
		
		
		return SUCCESS;
	}



	private Lawyers lawyer;

	public List getLessonPlayNum() {
		return lessonPlayNum;
	}

	public void setLessonPlayNum(List lessonPlayNum) {
		this.lessonPlayNum = lessonPlayNum;
	}

	public Lawyers getLawyer() {
		return lawyer;
	}
	
	
	private float nowxuefen;

	public float getNowxuefen() {
		return Float.parseFloat(NumberUtil.toMoney(nowxuefen + ""));
	}
	
	//现在的现场学分
	private float nowxcxuefen;
	public float getNowxcxuefen() {
		return nowxcxuefen;
	}
	
	private float needfen;

	public float getNeedfen() {
		return this.needfen;
	}
	
	private float needxcfen;
	public float getNeedxcfen() {
		return needxcfen;
	}
	
	
	private List forumList;
	private List lessonList;
	private List lessonTuijianList;
	private List helpList;
	private List goodlessonList;
	private List lessonPlayNum;
	private List lessonWeekPlayNum;
	
	
	/**
	 * @return the forumList
	 */
	public List getForumList() {
		return forumList;
	}

	/**
	 * @return the lessonList
	 */
	public List getLessonList() {
		return lessonList;
	}

	/**
	 * @return the helpList
	 */
	public List getHelpList() {
		return helpList;
	}

	/**
	 * @return the goodlessonList
	 */
	public List getGoodlessonList() {
		return goodlessonList;
	}

	private Articles tongzhi;

	public Articles getTongzhi() {
		return this.tongzhi;
	}
	
	private int lessonType1;
	private int lessonType2;
	private int lessonType3;
	private int lessonType5;
	private int lessonType6;
	private int lessonType8;

	public int getLessonType1() {
		return lessonType1;
	}

	public void setLessonType1(int lessonType1) {
		this.lessonType1 = lessonType1;
	}

	public int getLessonType2() {
		return lessonType2;
	}

	public void setLessonType2(int lessonType2) {
		this.lessonType2 = lessonType2;
	}

	public int getLessonType3() {
		return lessonType3;
	}

	public void setLessonType3(int lessonType3) {
		this.lessonType3 = lessonType3;
	}

	public int getLessonType5() {
		return lessonType5;
	}

	public void setLessonType5(int lessonType5) {
		this.lessonType5 = lessonType5;
	}

	public int getLessonType6() {
		return lessonType6;
	}

	public void setLessonType6(int lessonType6) {
		this.lessonType6 = lessonType6;
	}

	public int getLessonType8() {
		return lessonType8;
	}

	public void setLessonType8(int lessonType8) {
		this.lessonType8 = lessonType8;
	}

	public List getLessonWeekPlayNum() {
		return lessonWeekPlayNum;
	}

	public void setLessonWeekPlayNum(List lessonWeekPlayNum) {
		this.lessonWeekPlayNum = lessonWeekPlayNum;
	}

	public List getLessonTuijianList() {
		return lessonTuijianList;
	}

	public void setLessonTuijianList(List lessonTuijianList) {
		this.lessonTuijianList = lessonTuijianList;
	}
	
	
	private String toppic;

	public String getToppic() {
		return toppic;
	}
	public void setToppic(String toppic) {
		this.toppic = toppic;
	}
	

	
}
