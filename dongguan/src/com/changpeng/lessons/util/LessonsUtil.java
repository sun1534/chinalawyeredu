package com.changpeng.lessons.util;
import java.util.Set;

import org.springframework.web.context.ContextLoader;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;
public class LessonsUtil {
	public static boolean isBaoming(int lessonid,long userid){
		System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
		BasicService service=(BasicService) ContextLoader.getCurrentWebApplicationContext().getBean("basicService");
		Lessons lesson;
		try {
			lesson = (Lessons)service.get(Lessons.class, lessonid);
			Set<Lessonbaoming> lessonbaomings =lesson.getLessonbaomings();
			for(Lessonbaoming lessonbaoming:lessonbaomings){
				if(lessonbaoming.getUserid()==userid)
					return true;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
	}
}
