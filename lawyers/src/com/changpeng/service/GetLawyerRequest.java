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
	private int groupid;
	public GetLawyerRequest(int groupid){
		this.groupid=groupid;
	}
	public String requestService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder lawyertemp = new StringBuilder();
		result.append("<response>");
		try {
			BasicService userservice = (BasicService) Globals.getBean("basicService");
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyers.class);
			//市级律协
			detachedCriteria.add(Restrictions.eq("directunion", groupid));
			
			List list = userservice.findAllByCriteria(detachedCriteria);
			int lawyersize = list == null ? 0 : list.size();
			temp.append("<respcode>").append(lawyersize).append("</respcode>");
			temp.append("<respmsg>").append("have" + lawyersize + "lawyers").append("</respmsg>");

			lawyertemp.append("<lawyers>");
			int j=0;
			for (int i = 0; i < lawyersize; i++) {
				Lawyers lawyer = (Lawyers) list.get(i);
				//有事务所并且不是离职的律师了
				String cardno=lawyer.getCardno();
//				if (lawyer.getDirectunion()!=-1&&cardno!=null&&!cardno.trim().equals("")) {
				
				if(cardno==null||cardno.equals("")){
					cardno="0000990000"+(j++);
				}
				int theoffice=lawyer.getTheoffice();
				SysGroup group=(SysGroup)userservice.get(SysGroup.class, theoffice);
				//如果这个律师没卡号，则就不送到下面去了
				if (lawyer.getStatus()==0&&lawyer.getLawyertype()==0&&group!=null) {
					
				
					
					lawyertemp.append("<lawyer>");
					lawyertemp.append("<userid>").append(lawyer.getLawyerid()).append("</userid>");
					lawyertemp.append("<cardno>").append(cardno == null ? "" : cardno.trim()).append("</cardno>");
					lawyertemp.append("<username>").append(lawyer.getLawyername() == null ? "" : lawyer.getLawyername().trim()).append("</username>");
				
					lawyertemp.append("<officename>").append(group.getGroupname().trim()).append("</officename>");
					lawyertemp.append("<systemno>").append(lawyer.getSystemno() == null ? "" : lawyer.getSystemno().trim()).append("</systemno>");
					lawyertemp.append("<lawyerno>").append(lawyer.getLawyerno()).append("</lawyerno>");
					lawyertemp.append("<photo>").append(lawyer.getPhoto() == null ? "" : lawyer.getPhoto().trim()).append("</photo>");
					lawyertemp.append("</lawyer>\r\n");
				}
				else {
					LOG.info(lawyer.getLawyername() + "所在的事务所为空,忽略");
				}
			}
			lawyertemp.append("</lawyers>");

		}
		catch (Exception e) {
			LOG.error("GetLawyerRequest",e);
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取律师信息异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append(temp);
		result.append(lawyertemp);
		result.append("</response>");
		return result.toString();
	}
}
