/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenStaticsQueryAction extends AbstractListAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2006;
private String totype;
	/**
 * @return the totype
 */
public String getTotype() {
	return totype;
}

/**
 * @param totype the totype to set
 */
public void setTotype(String totype) {
	this.totype = totype;
}

	public JifenStaticsQueryAction() {
		this.rightCode = "jifentongji";

		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int nowyear = calendar.get(Calendar.YEAR);

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的
		if (nianshen.equals("12-31")||nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			this.year = nowyear;
		}
		else {
			this.year = nowyear - 1;
		}
		for (int i = START_YEAR; i <= year; i++) {
			years.put(i, i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = getLoginUser();
		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		// 根据查询的年来查,默认为当前时间所在的积分年
		// detachedCriteria.add(Restrictions.eq("lawer.userid", lawer.getUserid()));
		this.dabiaofen = Float.parseFloat(CommonDatas.SysParameter.get("dabiaofen").toString());
		BasicService basicService = (BasicService) this.getBean("basicService");
        //年审时间
		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		// 起始时间是现在的时间基础上加1
		Date temp = null;
		if (!nianshen.equals("12-31")) {
			// 起始时间是现在的时间基础上加1
			temp = dfdate.parse(year + "-" + nianshen);

			// 年审时间的基础上加1天的毫秒值
			long time = temp.getTime() + 24 * 60 * 60 * 1000;
			// 得到这个时间

			temp.setTime(time);

			this.from = dfdate.format(temp);
			this.end = (year + 1) + "-" + nianshen;
		} else {
			temp = dfdate.parse(year + "-01-01");
//			long time = temp.getTime() + 24 * 60 * 60 * 1000;
//			temp.setTime(time);
			this.from = dfdate.format(temp);
			this.end = (year) + "-" + nianshen;
		}
		// Date _from = dftime.parse(from + " 0:0:0");
		java.sql.Timestamp _from = new java.sql.Timestamp(temp.getTime());
		java.sql.Timestamp _end = new java.sql.Timestamp(dftime.parse(end + " 23:59:59").getTime());

		// 只能自己来写视图实现d 
		int groupid = -1;
		Set<SysRole> _roles = this.getLoginUser().getSysRoles();
		if (_roles != null && _roles.size() != 0) {
			Iterator<SysRole> roles = _roles.iterator();
			short temproleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				short roleid = role.getRoleid();
				if (temproleid <= roleid) {
					temproleid = roleid;
				}
			}
			if (temproleid == 2) {// 是事务所管理员，这个人是
				this.officename = this.getLoginUser().getSysGroup().getGroupname();
				this.officenameinput = false;
				groupid = this.getLoginUser().getSysGroup().getGroupid();
			}
		}

		
		// year的培训课程总数,year的视频课程总数
		debug("===frommmmmmm:::" + from + ",===end::::::::" + end);
	
		//年提供的现场课程数
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		detachedCriteria.add(Restrictions.ge("lessondate", from + " 00:00")).add(Restrictions.le("lessondate", end + " 23:59"));
		detachedCriteria.setProjection(org.hibernate.criterion.Projections.count("lessonid"));
		lessoncnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());
	    //年提供的在线课程数
		detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		detachedCriteria.add(Restrictions.ge("lessondate", from + " 00:00")).add(Restrictions.le("lessondate", end + " 23:59")).add(
				Restrictions.isNotNull("onlinefile"));
		detachedCriteria.setProjection(org.hibernate.criterion.Projections.count("lessonid"));
		onlinelessoncnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());
		
		//现场课程培训人数，视频课程培训人数。
//		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("learnmode")).add(Projections.count("learnmode")).add(
//				Projections.groupProperty("learnmode")));

		String sql="select learnmode,count(learnmode) from (select a.learnmode,a.userid from lawyerlessonxf a inner join sys_user b on a.userid=b.userid where b.roleid=1 and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000+" and "+_end.getTime()/1000+") group by learnmode,userid) c group by c.learnmode";
		if(groupid!=-1){
		  	   sql="select learnmode,count(learnmode) from (select a.learnmode,a.userid from lawyerlessonxf a inner join sys_user b on a.userid=b.userid where b.roleid=1 and b.groupid="+groupid+" and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000+" and "+_end.getTime()/1000+")  group by learnmode,userid) c group by c.learnmode";
		}
		tongjilist= basicService.findBySqlQuery(sql);
	
//		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("learnmode")).add(Projections.groupProperty("learnmode")));
//		tongjilist = basicService.findAllByCriteria(detachedCriteria);
		int tongjilength=tongjilist==null?0:tongjilist.size();
		for(int i=0;i<tongjilength;i++){
			Object[] obj=(Object[])tongjilist.get(i);
			if(obj[0].equals("现场培训")){
				this.localecnt=Integer.parseInt(obj[1].toString());
			}else if(obj[0].equals("在线视频")){
				this.video=Integer.parseInt(obj[1].toString());
			}else if(obj[0].equals("文本课件")){
				this.wenbenkejian=Integer.parseInt(obj[1].toString());
			}else if(obj[0].equals("补登积分")){
				this.budeng=Integer.parseInt(obj[1].toString());
			}
		}

		// 得到所有的律师数，所有的已达标数，所有的未达标数
		jifenstatics=new Jifenstatics();
//		detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
//		detachedCriteria.add(Restrictions.between("lastupdate", _from, _end));
//		detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("lawer")).add(Projections.sum("pxxf")).add(
//				Projections.groupProperty("lawer"))).createAlias("lawer", "lawer").add(Restrictions.eq("lawer.roleid", 1));
//		if (groupid != -1) {
//			detachedCriteria.createAlias("lawer.sysGroup", "group").add(Restrictions.eq("group.groupid", groupid));
//		}
//		List list = basicService.findAllByCriteria(detachedCriteria);
		
		String adminsql="select a.userid,sum(a.pxxf) from lawyerlessonxf a,sys_user b where a.userid=b.userid and b.roleid=1 and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000 + " and " + _end.getTime()/1000 + ") group by a.userid";
		if (groupid != -1) {
			adminsql="select a.userid,sum(a.pxxf) from lawyerlessonxf a,sys_user b where a.userid=b.userid and b.groupid="+groupid+" and b.roleid=1 and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000 + " and " + _end.getTime()/1000 + ") group by a.userid";
		}
		List list=basicService.findBySqlQuery(adminsql);
		int length = list == null ? 0 : list.size();
		int dabiaoshu=0;
		int weidabiaoshu=0;
		for (int i = 0; i < length; i++) {
			Object[] obj = (Object[]) list.get(i);
			float xuefen = Float.parseFloat(obj[1].toString());
			debug(obj[0] + "===" + obj[1]);
			if (xuefen >= dabiaofen) {
				dabiaoshu++;
			}
			else  {
				weidabiaoshu++;
			}
		}
		// 得到所有的律师数
		detachedCriteria = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1)).setProjection(Projections.count("userid"));
		if (groupid != -1) {
			detachedCriteria.add(Restrictions.eq("sysGroup.groupid", groupid));
		}
		int lawyercnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());
		jifenstatics.setAllusers(lawyercnt);
		jifenstatics.setWeipeixun(lawyercnt - length);
		jifenstatics.setDabiaoshu(dabiaoshu);
		jifenstatics.setWeidabiao(weidabiaoshu);
		
		//得到统计数据列表
		LawyerlessonxfService lawyerlessonxfService = (LawyerlessonxfService) getBean("lawyerlessonxfService");
		debug("===from:::" + _from + ",===end:::" + _end);
	
		
		if(totype!=null&&totype.equals("excel")){

			 this.page = lawyerlessonxfService.getJifentongji(_from, _end, officename, username, lawerno, 1, Integer.MAX_VALUE, this.isdabiao,jifenstatics);
				
			return "excel";
	    	
		}else{
		 this.page = lawyerlessonxfService.getJifentongji(_from, _end, officename, username, lawerno, pageNo, pageSize, this.isdabiao,jifenstatics);
			
			return "list";
		}
		
		
		// TODO Auto-generated method stub
//		return SUCCESS;
	}
	private int video;
	private int localecnt;
	private int budeng;
	private int wenbenkejian;
	public int getExportright(){
		String login=this.getLoginUser().getLoginname();
		if(login!=null&&login.equals("admin"))
		return 1;
		return 0;
	}
	public int getVideo(){
		return this.video;
	}
	public int getLocalecnt(){
		return this.localecnt;
	}
	public int getBudeng(){
		return this.budeng;
	}
	public int getWenbenkejian(){
		return this.wenbenkejian;
	}
	

	private boolean officenameinput=true;

	public boolean getOfficenameinput() {
		return this.officenameinput;
	}

//	private int dabiaoshu;
//	private int weidabiaoshu;
//	private int weipeishun;

	private List tongjilist;
	private int lessoncnt;
	private int onlinelessoncnt;

	public List getTongjilist() {
		return this.tongjilist;
	}

	public int getLessoncnt() {
		return this.lessoncnt;
	}

	public int getOnlinelessoncnt() {
		return this.onlinelessoncnt;
	}

	private int year;

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	private String from;
	private String end;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	private Map years = new HashMap();

	public Map getYears() {
		return this.years;
	}

	private String officename;
	private String username;
	private String lawerno;

	// 1达标2未达标3未培训，未培训就是总分=0
	private int isdabiao=1;

	/**
	 * @return the isdabiao
	 */
	public int getIsdabiao() {
		return isdabiao;
	}

	/**
	 * @param isdabiao
	 *            the isdabiao to set
	 */
	public void setIsdabiao(int isdabiao) {
		this.isdabiao = isdabiao;
	}

	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}

	/**
	 * @param officename
	 *            the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the lawerno
	 */
	public String getLawerno() {
		return lawerno;
	}

	/**
	 * @param lawerno
	 *            the lawerno to set
	 */
	public void setLawerno(String lawerno) {
		this.lawerno = lawerno;
	}

	private float dabiaofen = 0;

	public float getDabiaofen() {
		return dabiaofen;
	}

	
	private Jifenstatics jifenstatics;
	public Jifenstatics getJifenstatics(){
		return this.jifenstatics;
	}
	
//	/**
//	 * @return the dabiaoshu
//	 */
//	public int getDabiaoshu() {
//		return dabiaoshu;
//	}
//
//	/**
//	 * @return the weidabiaoshu
//	 */
//	public int getWeidabiaoshu() {
//		return weidabiaoshu;
//	}
//
//	/**
//	 * @return the weipeishun
//	 */
//	public int getWeipeishun() {
//		return weipeishun;
//	}

}