/**
 * LessonStatic.java
 */
package com.changpeng.lessons.action;

/**
 * @author 华锋
 * Jul 15, 201010:07:55 AM
 *
 */
public class LessonStatic {
	private int lessonid;
	private int count;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return com.changpeng.lessons.util.CommonDatas.AllLessonMap.get(lessonid);
	}

	
}
