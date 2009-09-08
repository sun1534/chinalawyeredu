package com.changpeng.lessons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.web.context.ContextLoader;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Lessonbaoming;
import com.changpeng.models.Lessons;

public class LessonsUtil {
	public static boolean isBaoming(int lessonid, long userid) {
		// System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
		BasicService service = (BasicService) ContextLoader.getCurrentWebApplicationContext().getBean("basicService");
		Lessons lesson;
		try {
			lesson = (Lessons) service.get(Lessons.class, lessonid);
			Set<Lessonbaoming> lessonbaomings = lesson.getLessonbaomings();
			for (Lessonbaoming lessonbaoming : lessonbaomings) {
				if (lessonbaoming.getUserid() == userid)
					return true;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static String list2str(List list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() != 0) {
			for(int i=0;i<list.size();i++)
			sb.append(list.get(i)).append(";");
		} else {
			sb.append("0");
		}

		return sb.toString();
	}

	public static List str2list(String str) {
		List list = new ArrayList();
		if (str != null && !str.equals("")&&!str.equals("0")) {

			StringTokenizer st = new StringTokenizer(str, ";");
			while (st.hasMoreTokens()){
			String sttr=st.nextToken().toString();
			System.out.println(sttr);
				list.add(sttr);
			
			}

		}
		return list;
	}
}
