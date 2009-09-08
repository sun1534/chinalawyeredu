package com.changpeng.lessons.action;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.EducationLocation;
import com.changpeng.common.LessonSync;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessons;

public class LessonsListAction extends AbstractListAction {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String onlineorlocal = "online";

	private byte lessontype = (byte) -1;
	private String teachers = "";
	private String fromAddr = "-1";

	public LessonsListAction() {
		this.rightCode = "kcguanli";
	}

	@Override
	protected String go() throws Exception {
		if(com.changpeng.common.CommonDatas.AllLawyers.isEmpty()){
			
			LessonSync sync=new LessonSync();
			EducationLocation.getLocationEdu();
			
		}
		BasicService service = (BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		// 不显示删除的
		detachedCriteria.add(Restrictions.eq("deleteflag", "N"));

		if (title != null && !"".equals(title))
			detachedCriteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		if (teachers != null && !"".equals(teachers))
			detachedCriteria.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
		if (lessontype != -1)
			detachedCriteria.add(Restrictions.eq("lessontype", lessontype));

		// client端的处理
		if (!com.changpeng.common.Constants.FROMADDR.equals("server")) {
			// 我自己的加上其他地方过来的
			if (this.fromAddr.equals("-1")) {
				Criterion owner = Restrictions.eq("fromAddr", com.changpeng.common.Constants.FROMADDR);

				Criterion notowner = Restrictions.not(owner);
			
				//显示共享给我的
				//Criterion shared = (Restrictions.eq("shared", (short) 1));
				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));

			
				
				Criterion notownerbutshared = Restrictions.and(notowner, shared);

				Criterion thecriterion = Restrictions.or(owner, notownerbutshared);
				detachedCriteria.add(thecriterion);

				// 显示自己这个地方所有的并且显示从其他地方来的所有的

			} 
//				else if (this.fromAddr.equals((com.changpeng.common.Constants.FROMADDR))) { // 显示自己的并所有的
//				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//			} 
			else {
				
			//	Criterion from=Restrictions.eq("fromAddr", fromAddr);
				
//				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));
				Criterion owner= Restrictions.eq("fromAddr", fromAddr);
//				detachedCriteria.add(Restrictions.or(shared, owner));
				detachedCriteria.add(owner);
//				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
//			//	detachedCriteria.add(Restrictions.eq("shared", (short) 1));
//			//	Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR));
//				Criterion shared = (Restrictions.like("shared", com.changpeng.common.Constants.FROMADDR,MatchMode.ANYWHERE));
//				detachedCriteria.add(shared);
			}
		}
		// server端的处理，不考虑是否共享，全部列出来
		else {

			if (!this.fromAddr.equals("-1")) {
				detachedCriteria.add(Restrictions.eq("fromAddr", fromAddr));
				// 显示自己这个地方所有的并且显示从其他地方来的所有的

			}
		}

		Criterion r = Restrictions.eq("onlineorlocal", onlineorlocal);
		Criterion l = Restrictions.eq("onlineorlocal", "all");
		detachedCriteria.add(Restrictions.or(r, l));
		String success="local";

//		detachedCriteria.addOrder(Order.desc("lessonid"));
		detachedCriteria.addOrder(Order.desc("lessondate"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		if (this.onlineorlocal.equals("local")) {
			
			success="local";
		} else {
		
			success="online";
		}
		return success;
	}

	public String getOnlineorlocal() {
		return onlineorlocal;
	}

	public void setOnlineorlocal(String onlineorlocal) {
		this.onlineorlocal = onlineorlocal;
	}

	public byte getLessontype() {
		return lessontype;
	}

	public void setLessontype(byte lessontype) {
		this.lessontype = lessontype;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public boolean getHavelocal(){
		return com.changpeng.common.Constants.HAVELOCAL;
	}
}
