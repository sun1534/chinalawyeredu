/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetClassRegRequest extends ElearningRequests {

	private static final Log LOG = LogFactory.getLog(GetClassRegRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int groupid;
	private String cpuid;

	public GetClassRegRequest(String cpuid, int groupid) {
		this.groupid = groupid;
		this.cpuid = cpuid;
	}

	public String requestService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		StringBuilder temp = new StringBuilder();
		StringBuilder lawyertemp = new StringBuilder();

		try {
			BasicService basicService = (BasicService) Globals.getBean("basicService");
			// 这里每次都只取最新的
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);

			// 这里得到这个groupid,cpuid最近的一次数据
			String sql = "select reqtime from Log_Client_Request where method='getClassRegInfo' and groupid=" + groupid + " and cpuid='" + cpuid
					+ "' order by reqtime desc limit 1";
			List reqlist = basicService.findBySqlQuery(sql);
			String lasttime = null;
			LOG.debug("reqlist::::"+reqlist);
			if (reqlist != null && reqlist.size() > 0) {
				Timestamp ts = (Timestamp) (reqlist.get(0));
				detachedCriteria.add(Restrictions.ge("lastupdate", ts));
				lasttime = df.format(ts);
				
			}
			detachedCriteria.add(Restrictions.eq("learnmode", 1));
			detachedCriteria.add(Restrictions.eq("cityid", groupid));

			List list = basicService.findAllByCriteria(detachedCriteria);
			int xflen = list == null ? 0 : list.size();

			temp.append("<respcode>").append(xflen).append("</respcode>");
			if (lasttime == null || lasttime.equals(""))
				temp.append("<respmsg>").append(xflen + "已参加了培训").append("</respmsg>");
			else
				temp.append("<respmsg>").append(lasttime + "到现在，总计有" + xflen + "人参加了培训").append("</respmsg>");
			lawyertemp.append("<classregs>");
			for (int i = 0; i < xflen; i++) {
				Lawyerlessonxf xf = (Lawyerlessonxf) list.get(i);
				Lawyers lawyer=(Lawyers)basicService.get(Lawyers.class, xf.getLawyerid());
				if(lawyer==null)
					continue;
				lawyertemp.append("<classreg>");
				
				lawyertemp.append("<dotime>").append(df.format(xf.getLastupdate())).append("</dotime>");
				lawyertemp.append("<lawyerno>").append(lawyer==null?"":lawyer.getLawyerno()).append("</lawyerno>");
				lawyertemp.append("<lessonid>").append(xf.getLessonid()).append("</lessonid>");
				lawyertemp.append("<regtype>").append(xf.getLearnmode()).append("</regtype>");
				lawyertemp.append("</classreg>");
			}
			lawyertemp.append("</classregs>");

		} catch (Exception e) {
			LOG.error("获取异常", e);
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取该课程已培训信息异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append(temp);
		result.append(lawyertemp);
		result.append("</response>");
		return result.toString();
	}
}
