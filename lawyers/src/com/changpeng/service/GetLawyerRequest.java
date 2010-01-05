/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysGroup;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetLawyerRequest extends ElearningRequests {
	private static final Log LOG = LogFactory.getLog(GetLawyerRequest.class);

	public String requestService(int groupid,org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder lawyertemp = new StringBuilder();
		result.append("<response>");
		try {
			BasicService userservice = (BasicService) Globals.getBean("basicService");
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyers.class);
			detachedCriteria.add(Restrictions.eq("directunion", groupid));
			
			List list = userservice.findAllByCriteria(detachedCriteria);
			int lawyersize = list == null ? 0 : list.size();
			temp.append("<respcode>").append(lawyersize).append("</respcode>");
			temp.append("<respmsg>").append("有" + lawyersize + "个律师").append("</respmsg>");

			lawyertemp.append("<lawyers>");
			for (int i = 0; i < lawyersize; i++) {
				Lawyers lawyer = (Lawyers) list.get(i);
				//有事务所并且不是离职的律师了
				String cardno=lawyer.getCardno();
				if (lawyer.getDirectunion()!=-1&&cardno!=null&&!cardno.trim().equals("")) {
					lawyertemp.append("<lawyer>");
					lawyertemp.append("<userid>").append(lawyer.getLawyerid()).append("</userid>");
					lawyertemp.append("<cardno>").append(lawyer.getCardno() == null ? "" : lawyer.getCardno()).append("</cardno>");
					lawyertemp.append("<username>").append(lawyer.getLawyername() == null ? "" : lawyer.getLawyername()).append("</username>");

					int theoffice=lawyer.getTheoffice();
					SysGroup group=(SysGroup)userservice.get(SysGroup.class, theoffice);
					
					lawyertemp.append("<officename>").append(group.getGroupname()).append("</officename>");

					lawyertemp.append("<mobile>").append(lawyer.getSystemno() == null ? "" : lawyer.getSystemno()).append("</mobile>");
//					lawyertemp.append("<mobile>").append(lawyer.getMobile() == null ? "" : lawyer.getMobile()).append("</mobile>");
					lawyertemp.append("<lawyerno>").append(lawyer.getLawyerno()).append("</lawyerno>");
					lawyertemp.append("<photo>").append(lawyer.getPhoto() == null ? "" : lawyer.getPhoto()).append("</photo>");
					lawyertemp.append("</lawyer>\r\n");
				}
				else {
					LOG.info(lawyer.getLawyername() + "所在的事务所为空,忽略");
				}
			}
			lawyertemp.append("</lawyers>");

		}
		catch (Exception e) {
			LOG.error(e);
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取律师信息异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append(temp);
		result.append(lawyertemp);
		result.append("</response>");
		return result.toString();
	}
}
