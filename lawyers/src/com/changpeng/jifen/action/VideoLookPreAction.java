/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.jifen.util.Ping;
import com.changpeng.lessons.service.TeacherService;
import com.changpeng.models.BasicLawyerlessonxf;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.LogVideoLook;
import com.changpeng.models.Lxnetrecs;
import com.changpeng.models.ShopCart;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.Teacher;
import com.mysql.jdbc.PingTarget;

/**
 * <pre>
 *  
 * 看视频,获取课程名称并获取这个人现在看的分数
 * 如果这个人对这个课程已经获得了满分了，则能看，但是不计入分数
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午12:46:19
 * 
 */
public class VideoLookPreAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(VideoLookPreAction.class);
	private int lessonid;
	private float videotimeout = 0;
	private int visitid;
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	//评论回复
	List replylist=null;
	public List getReplylist(){
		return this.replylist;
	}

	public int getVisitid() {
		return this.visitid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	private int userid;

	public int getUserid() {
		return this.userid;
	}

	private boolean islastyear;

	public boolean getIslastyear() {
		return this.islastyear;
	}

	private float lookedminutes;
	/**
	 * 积分年度
	 */
	private int jifenyear;

	public int getJifenyear() {
		return this.jifenyear;
	}

	// 当前年度
	private int nowyear;
	// 去年年度
	private int lastyear;

	public int getNowyear() {
		return this.nowyear;
	}

	public int getLastyear() {
		return this.lastyear;
	}

	private boolean shouldselect;

	/**
	 * 看去年的积分是否ok
	 * 
	 * @return
	 */
	public boolean getShouldselect() {
		return shouldselect;
	}

	private float nowfen;

	public float getNowfen() {
		return this.nowfen;
	}

	public int mingnian;

	
	public int filetype;
	
	public int getFiletype() {
		return filetype;
	}

	public void setFiletype(int filetype) {
		this.filetype = filetype;
	}
	
	private String playfile;

	public String getPlayfile() {
		return playfile;
	}

	public void setPlayfile(String playfile) {
		this.playfile = playfile;
	}

	private int loglook(int lawyerid, int provinceid, int cityid, int officeid) {
		try {

			LogVideoLook log = new LogVideoLook();

			log.setLawyerid(lawyerid);
			log.setCityid(cityid);
			log.setProvinceid(provinceid);
			log.setOfficeid(officeid);
			log.setLessonid(lessonid);
			log.setIntime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicService.save(log);
			return log.getId();

		} catch (Exception e) {

			LOG.error("记录访问有误:::" + e);
			return -1;
		}
	}

	@Override
	public String go() throws Exception {
		this.videotimeout = Float.parseFloat(com.changpeng.common.CommonDatas.SysParameter.get("videotimeout")
				.toString());

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");

		this.userid = this.getLoginUser().getLawyerid();

		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, this.getLoginUser()
				.getDirectunion());
		totalfen = params.getDabiaofen();
		JifenTime jifentime = CommonDatas.getJifenTime(0, params.getNianshen());
		nowyear = jifentime.getNianshenyear();
		this.lastyear = nowyear - 1;
		boolean isloglast = params.getIsloglast();// 去年的分数没满，听的课是否记录到去年
		yearfen = xfservice.getLawyerZongjifen(userid, lastyear);
		nowfen = xfservice.getLawyerZongjifen(userid, nowyear);

		// LxnetrecsService lxnetrecsService = (LxnetrecsService)
		// getBean("lxnetrecsService");
		// BasicService basicService = (BasicService) getBean("basicService");
		this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
//		前面的路径格式是：mms://uc3.lawyeredu.com/uc3.lawyeredu.com，后面的不变。
//			全部路径的格式为：
//			mms://uc3.lawyeredu.com/uc3.lawyeredu.com/henan/shenglvxie/0371_1_20090516_01_a.wmv



		
		if (this.lessons == null) {
			this.message = "系统有误,请在在线课程里,选择课程点击观看";
			this.nextPage = "javascript:window.close()";
			return "message";
		} else {
			if(filetype==1 && (this.lessons.getOnlinefile() == null || this.lessons.getOnlinefile().equals("")) ){
				this.message = "该课程视频文件为空,请返回";
				this.nextPage = "javascript:window.close()";
				return "message";
			}else if(filetype==2 && (this.lessons.getSoundfile() == null || this.lessons.getSoundfile().equals(""))){
				this.message = "该课程音频文件为空,请返回";
				this.nextPage = "javascript:window.close()";
				return "message";
			}			
		}
		
			
		/***
		 * 视频服务器列表 2012-01-06 修改
		 * 长鹏公司服务器 4号：mms://videos.lawyeredu.com/uc1.lawyeredu.com/ 深圳市、东莞市  律师公用
		 * 长鹏公司服务器 5号：mms://uc2.lawyeredu.com/uc2_lawyeredu/    河南省(直属和郑州除外)
		 * 长鹏公司服务器 6号：mms://uc3.lawyeredu.com/uc3.lawyeredu.com/ 海南省、南京市、温州市、长春市
		 * 广西自己服务器 7号：mms://uc4.lawyeredu.net/uc4_lawyeredu/   广西全区、杭州市律师使用
		 * 长鹏公司服务器 8号：mms://uc5.lawyeredu.net/uc5_lawyeredu/   河南省直、郑州
		 * Provinceunion 律师所在省编号  Directunion  律师所在市编号
		 * 广西区   Provinceunion：22
		 * 河南省   Provinceunion：18  
		 * 海南省   Provinceunion：23  
		 * 东莞市   Provinceunion：21  Directunion：37
		 * 杭州市   Provinceunion：13  Directunion：8078
		 * 温州市   Provinceunion：21  Directunion：11002221
		 * 长春市   Provinceunion：9  Directunion：8079  
		 * 南京市   Provinceunion：12  Directunion：10325
		 */
		
		
		
		String url=lessons.getOnlinefile();
		if(filetype==2){
			url=lessons.getSoundfile();
		}
		System.out.println("00000："+this.getLoginUser().getProvinceunion());
		
		//东莞市、律师 观看时 都去 7号服务器 mms://videos.lawyeredu.com/uc1.lawyeredu.com/ 观看
		if(this.getLoginUser().getDirectunion()==37){			
			url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");			
			url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
			url=url.replace("mms://uc3.lawyeredu.com/uc3.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
			url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");
			LOG.debug("东莞市、杭州市 的地址:"+url);
		}
		//河南省直属和郑州去8号 服务器 mms://uc5.lawyeredu.net/uc5_lawyeredu/  观看，其他去5号	mms://uc2.lawyeredu.com/uc2_lawyeredu/ 
		else if(this.getLoginUser().getProvinceunion()==18 ){
			//河南省直和郑州律协、洛阳市、开封都用8号服务器
			if(this.getLoginUser().getDirectunion()==8495 || this.getLoginUser().getDirectunion()==8496){
				url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc5.lawyeredu.net/uc5_lawyeredu/");	
				url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc5.lawyeredu.net/uc5_lawyeredu/");	
				url=url.replace("mms://uc3.lawyeredu.com/uc3.lawyeredu.com/", "mms://uc5.lawyeredu.net/uc5_lawyeredu/");
				url=url.replace("mms://uc4.lawyeredu.net/uc4_lawyeredu/", "mms://uc5.lawyeredu.net/uc5_lawyeredu/");
				
			}//洛阳市、开封、平顶山、安阳、新乡 都用7号服务器
			else if(this.getLoginUser().getDirectunion()==8497 || this.getLoginUser().getDirectunion()==8498 || this.getLoginUser().getDirectunion()==8499 || this.getLoginUser().getDirectunion()==8500 || this.getLoginUser().getDirectunion()==8501){
				url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");			
				url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
				url=url.replace("mms://uc3.lawyeredu.com/uc3.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
				url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");
				
			}else{//河南其他市去5号
				url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc2.lawyeredu.com/uc2_lawyeredu/");	
				url=url.replace("mms://uc3.lawyeredu.com/uc3.lawyeredu.com/", "mms://uc2.lawyeredu.com/uc2_lawyeredu/");
				url=url.replace("mms://uc4.lawyeredu.net/uc4_lawyeredu/", "mms://uc2.lawyeredu.com/uc2_lawyeredu/");
				url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc2.lawyeredu.com/uc2_lawyeredu/");
				LOG.debug("河南省、长春市 的地址:"+url);	
			}	
		}		
		//海南省、南京市、温州市、长春市 律师 观看时 都去 6号服务器 mms://uc3.lawyeredu.com/uc3.lawyeredu.com/ 观看	
		else if(this.getLoginUser().getProvinceunion()==23 || this.getLoginUser().getDirectunion()==10325 || this.getLoginUser().getDirectunion()==11002221 || this.getLoginUser().getDirectunion()==8079){
			url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");
			url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");		
			url=url.replace("mms://uc4.lawyeredu.net/uc4_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");	
			url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");	
		}//广西全区、杭州 律师 观看时 都去 7 号服务器 mms://uc4.lawyeredu.net/uc4_lawyeredu/ 观看	
		else if(this.getLoginUser().getProvinceunion()==22 || this.getLoginUser().getDirectunion()==8078){
				
			url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");			
			url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
			url=url.replace("mms://uc3.lawyeredu.com/uc3.lawyeredu.com/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
			url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc4.lawyeredu.net/uc4_lawyeredu/");	
			LOG.debug("广西律协的地址:"+url);
		}else{//其他未指定的城市
			url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");
			url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");		
			url=url.replace("mms://uc4.lawyeredu.net/uc4_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");	
			url=url.replace("mms://uc5.lawyeredu.net/uc5_lawyeredu/", "mms://uc3.lawyeredu.com/uc3.lawyeredu.com/");	
			
		}
		//将播放的文件
		playfile=url;
		//lessons.setOnlinefile(url);
		
//		else{
//			//取出2个服务器返回是值
//			Ping ping=new Ping();
//			int u4=ping.get1(); //4号服务器 videos.lawyeredu.com 
//			int u5=ping.get2(); //5号服务器 uc2.lawyeredu.com  
//			String url=lessons.getOnlinefile();
//			if(u4<u5){//4号较好  返回值较少的，代表较流畅				
//				//如果是 4号 服务器较流畅，就将该视频地址改为4号服务器的地址
//				url=url.replace("mms://uc2.lawyeredu.com/uc2_lawyeredu/","mms://videos.lawyeredu.com/uc1.lawyeredu.com/");
//				LOG.info("其他律协的地址:"+url);				
//			}else{//5号较好  返回值较少的，代表较流畅
//				//如果是 5号 服务器较流畅，就将该视频地址改为5号服务器的地址
//				url=url.replace("mms://videos.lawyeredu.com/uc1.lawyeredu.com/","mms://uc2.lawyeredu.com/uc2_lawyeredu/");
//				LOG.info("其他律协的地址:"+url);
//				
//			}
//			lessons.setOnlinefile(url);
//		}

		// this.lxnetrecs = (Lxnetrecs) lxnetrecsService.getLxnetrecs(lessonid,
		// userid);
		// debug("this.lxnetrecs==" + this.lxnetrecs);
		
		
		BasicLawyerlessonxf xuefen = null;
		if ( this.getLoginUser().getLawyertype() == -1)
			 xuefen = xfservice.getXuefenShixi(lessonid, this.userid, 0);
		else if ( this.getLoginUser().getLawyertype() == -2)
			 xuefen = xfservice.getXuefenGongzheng(lessonid, this.userid, 0);
		else
			xuefen = xfservice.getXuefen(lessonid, this.userid, 0);
		// 满分了，不管是通过什么方式的
		if (xuefen != null) {
			this.lookedminutes = xuefen.getPxminutes();
			_LOG.debug("xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()"
					+ (xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()));
			_LOG.debug(xuefen.getPxxf() + "===" + lessons.getXuefen());
		}
		if (xuefen != null && xuefen.getIsfull()) {
			settime = false;
		}

		// 如果参加了现场培训，就不
		if (xuefen != null && xuefen.getLearnmode() == 1) {
			settime = false;
			localelesson = true;
		}

		System.out.println("xuefen::::"+xuefen);
		System.out.println("yearfen::::"+yearfen);
		if (xuefen != null) {
			jifenyear = xuefen.getTheyear();
			islastyear = xuefen.getIslastyear() == 1 ? true : false;
			System.out.println("xuefen !=null");
		} else if (!isloglast) {
			shouldselect = false;
			jifenyear = nowyear;
			System.out.println("!isloglast");
		} else {
			// 如果所获得的分数小于达标分，则提示是否需要设置学分到去年
			if (yearfen < totalfen) {
				shouldselect = true;
				System.out.println("yearfen < totalfen");
			} else {
				shouldselect = false;
				jifenyear = nowyear;
				System.out.println("yearfen >= totalfen");
			}
		}

		Lawyers lawyers = this.getLoginUser();
		//记录这个窗口的打开记录,怎么判断这个用户当前正在听课呢???
		visitid = loglook(lawyers.getLawyerid(), lawyers.getProvinceunion(), lawyers.getDirectunion(), lawyers
				.getTheoffice());
		
		System.out.println("LLLL:"+lessons.getTeacherid());
		if(lessons.getTeacherid()!=0){
			TeacherService service = (TeacherService) this.getBean("teacherService");
			Teacher teacher=(Teacher)service.get(Teacher.class, lessons.getTeacherid());
			TeacherContent=teacher.getComments();
			
		}
		
		//判断是否购买
		
		ShopCart shopcart=basicService.getShopCart(lawyers.getLawyerid(), lessonid);
		if(shopcart!=null && shopcart.getState()==2){
			isbuy=1;//已经付款购买。
		}
		System.err.println("isbuy LLLL:"+isbuy);
		
		///课件评价
		DetachedCriteria dc=DetachedCriteria.forClass(com.changpeng.models.Lessonreply.class);
		dc.add(Restrictions.eq("lessonid",lessonid));
		dc.addOrder(Order.desc("replytime"));
		PaginationSupport __page=basicService.findPageByCriteria(dc, 3, 1);
		this.replylist=__page.getItems();
		return SUCCESS;

	}

	//授课老师简介
	private String TeacherContent;
	public String getTeacherContent() {
		return TeacherContent;
	}
	
	private boolean localelesson;

	public boolean getLocalelesson() {
		return this.localelesson;
	}

	// 是否还需要计时
	private boolean settime = true;

	public boolean getSettime() {
		return this.settime;
	}

	// private Lxnetrecs lxnetrecs;

	// public Lxnetrecs getLxnetrecs() {
	// return this.lxnetrecs;
	// }

	private float totalfen;
	private float yearfen;

	public float getTotalfen() {
		return this.totalfen;
	}

	public float getYearfen() {
		return this.yearfen;
	}

	private Lessons lessons;

	public Lessons getLessons() {
		return this.lessons;
	}

	public float getVideotimeout() {
		return videotimeout;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the lookedminutes
	 */
	public float getLookedminutes() {
		return lookedminutes;
	}
	
	
	private int isbuy=0;
	public int getIsbuy() {
		return isbuy;
	}

	public void setIsbuy(int isbuy) {
		this.isbuy = isbuy;
	}



}