package com.changpeng.lessons.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.LessonsUtil;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonshared;
import com.changpeng.models.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 
 * 给课程设置共享
 * 
 * @author 华锋
 * 
 */
public class LessonsSharedAction extends AbstractAction {
	


	public LessonsSharedAction() {

	}

	@Override
	protected String go() throws Exception {

		SysGroup mygroup = this.getLoginUser().getSysGroup();
		
		Lessons lessons=(Lessons)basicService.get(Lessons.class, lessonid);
		if (sharedgroupids == null || sharedgroupids.size() == 0) {
			sharedgroupids = new ArrayList();
		}
		if (mygroup != null)
			sharedgroupids.add(mygroup.getGroupid());
		
		
		LessonsService service = (LessonsService) this.getBean("lessonsService");
		
		service.updateLessonShared(lessons, sharedgroupids, this.getLoginUser());
		
		this.message="课程共享设置完成";
		this.nextPage="lessonsOnlineList.pl?lessonstyle=2&pageNo="+pageNo;
		
		
		this.opResult+="课程共享,名称:"+lessons.getTitle()+",同步到:"+sharedgroupids;
		
		return "message";
	}

	@Override
	public String input() throws Exception{


		SysGroupService groupservice = (SysGroupService) this.getBean("sysGroupService");
		SysGroup mygroup = this.getLoginUser().getSysGroup();
		shouldsharedgroupids = groupservice.getAllsharedunion();
		if (shouldsharedgroupids != null && shouldsharedgroupids.size() != 0) {
			// 课程共享的时候，去掉自己
			if (mygroup != null) {
				for (Object obj : shouldsharedgroupids) {
					SysGroup group = (SysGroup) obj;
					if (group.getGroupid() == mygroup.getGroupid()) {
						shouldsharedgroupids.remove(group);
						break;
					}
				}
			}
		}
		sharedgroupids=new ArrayList();
		DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
		dc.createAlias("lessons", "lessons");
		dc.add(Restrictions.eq("lessons.lessonid", lessonid));
		
		sharedgroups = basicService.findAllByCriteria(dc);
		for (int i = 0; sharedgroups != null && i < sharedgroups.size(); i++) {
			Lessonshared shared = (Lessonshared) sharedgroups.get(i);
//			System.out.println("==============="+shared.getGroupid());
			if (mygroup == null || (mygroup != null && shared.getGroupid() != mygroup.getGroupid()))
				sharedgroupids.add(shared.getGroupid());
		}
		
		return INPUT;
	}
	
	private List sharedgroups;
	

	private int lessonid;
	
	private List sharedgroupids;

	/**
	 * @param sharedgroupids
	 *            the sharedgroupids to set
	 */
	public void setSharedgroupids(List sharedgroupids) {
		this.sharedgroupids = sharedgroupids;
	}

	public List getSharedgroupids() {
		return this.sharedgroupids;
	}

	/**
	 * 课程的来源,其实也应该是省律协或者市律协，共享的时候，设置共享给所有的省律协以及购买了系统的
	 */
	private List shouldsharedgroupids;

	/**
	 * @return the sharedgroupids
	 */
	public List getShouldsharedgroupids() {
		
		
		
		
		return shouldsharedgroupids;
	}

	/**
	 * @return the lessonid
	 */
	public int getLessonid() {
		return lessonid;
	}

	/**
	 * @param lessonid the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	/**
	 * @return the sharedgroups
	 */
	public List getSharedgroups() {
		return sharedgroups;
	}




}
