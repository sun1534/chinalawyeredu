/**
 * 
 */
package com.changpeng.lessons.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * <pre>
 *
 * </pre> 
 * @author 华锋
 * 2009-2-24-下午01:55:46
 */
public class CommonDatas {
	
	
	
	public static HashMap LessonType =new LinkedHashMap();
	public static HashMap LessonState =new LinkedHashMap();
	public static HashMap SharedName =new LinkedHashMap();
	
	static{
		LessonType.put((byte)0, "律师实务");
		LessonType.put((byte)1, "新法讲坛");
		LessonType.put((byte)2, "涉外法务");
		LessonType.put((byte)3, "行业专题");
		LessonType.put((byte)4, "岗前培训");
		LessonType.put((byte)5, "专题论坛");
		LessonType.put((byte)6, "其他");

		LessonState.put((byte)0, "内容编辑");
		LessonState.put((byte)1, "培训课程");
		LessonState.put((byte)2, "往期课程");
		LessonState.put((byte)3, "课程结束");
		//0: 1: 2:取消了共享
		SharedName.put((short)0, "不共享");
		SharedName.put((short)1, "共享");
		SharedName.put((short)2, "被取消");
	}
}
