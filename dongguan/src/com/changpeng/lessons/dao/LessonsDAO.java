/**
 * LessonsDAO.java
 */
package com.changpeng.lessons.dao;

import java.util.List;

import com.changpeng.common.BasicDAO;
import com.changpeng.models.Lessonbaoming;
import com.changpeng.models.Lessonscore;

/**
 * @author 华锋
 * 2008-5-16 上午10:56:13
 *
 */
public class LessonsDAO extends BasicDAO {

	/**
	 * 判断这个人是否已经投票
	 * @param userid
	 * @param lessonid
	 * @return
	 */
	public Lessonscore getScorebyLessonUser(long userid,int lessonid){
		Lessonscore score=null;
		List list=this.find("from com.changpeng.models.Lessonscore score where score.lessons.lessonid="+lessonid+" and score.userid="+userid);
		if(list!=null&&list.size()!=0)
			score=(Lessonscore)list.get(0);
		return score;
	}
	
	public List getBaominglistByUser(long userid){
		List list=this.find("from com.changpeng.models.Lessonbaoming.lessons lessons where baoming.userid="+userid);
		return list;
	}
	
	/**
	 * 判断这个人的这个课程是否已经报名了
	 * @param userid
	 * @param lessonid
	 * @return
	 */
	public Lessonbaoming getBaomingbyLessonUser(long userid,int lessonid){
		Lessonbaoming baoming=null;
		List list=this.find("from com.changpeng.models.Lessonbaoming baoming where baoming.lessons.lessonid="+lessonid+" and baoming.userid="+userid);
		if(list!=null&&list.size()!=0)
			baoming=(Lessonbaoming)list.get(0);
		
		
		return baoming;
	}
}
