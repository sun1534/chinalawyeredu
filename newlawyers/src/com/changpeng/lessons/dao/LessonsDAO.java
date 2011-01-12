/**
 * LessonsDAO.java
 */
package com.changpeng.lessons.dao;

import java.util.List;

import com.changpeng.common.BasicDAO;
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
		List list=this.find("from com.changpeng.models.Lessonscore score where score.lessonid="+lessonid+" and score.userid="+userid);
		if(list!=null&&list.size()!=0)
			score=(Lessonscore)list.get(0);
		return score;
	}


}
