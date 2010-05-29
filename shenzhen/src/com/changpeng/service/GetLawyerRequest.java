/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetLawyerRequest extends ElearningRequests {
	private static final Log LOG = LogFactory.getLog(GetLawyerRequest.class);

	public String requestService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder lawyertemp = new StringBuilder();
		result.append("<response>");
		try {
			BasicService userservice = (BasicService) globals.getBean("basicService");
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1));
			detachedCriteria.setFetchMode("sysGroup", FetchMode.JOIN);
			
			List list = userservice.findAllByCriteria(detachedCriteria);
//			userservice.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			int lawersize = list == null ? 0 : list.size();
			temp.append("<respcode>").append(lawersize).append("</respcode>");
			temp.append("<respmsg>").append("有" + lawersize + "个律师").append("</respmsg>");

			lawyertemp.append("<lawyers>");
			List<String> cardnolist=new ArrayList<String>();
			for (int i = 0; i < lawersize; i++) {
				SysUser lawyer = (SysUser) list.get(i);
				//有事务所并且不是离职的律师了
				String cardno=lawyer.getCardno();
			
				if (lawyer.getSysGroup() != null&&lawyer.getSysGroup().getGroupid()!=-1&&cardno!=null&&!cardno.equals("")) {
					if(!cardnolist.contains(cardno)){
					lawyertemp.append("<lawyer>");
					lawyertemp.append("<userid>").append(lawyer.getUserid()).append("</userid>");
				
//					if(lawyer.getCardno()!=null&&lawyer.getCardno().equals("0000000000"))
//					    lawyertemp.append("<cardno></cardno>");
//					else
						lawyertemp.append("<cardno>").append(lawyer.getCardno() == null ? "" : lawyer.getCardno()).append("</cardno>");
						
					lawyertemp.append("<username>").append(lawyer.getUsername() == null ? "" : lawyer.getUsername()).append("</username>");
					lawyertemp.append("<officename>").append(lawyer.getSysGroup().getGroupname()).append("</officename>");
					lawyertemp.append("<mobile>").append(lawyer.getMobile() == null ? "" : lawyer.getMobile()).append("</mobile>");
					lawyertemp.append("<lawyerno>").append(lawyer.getLawerno()).append("</lawyerno>");
					lawyertemp.append("<photo>").append(lawyer.getPhoto() == null ? "" : lawyer.getPhoto()).append("</photo>");
					lawyertemp.append("</lawyer>\r\n");
					cardnolist.add(cardno);
					}
				}
				else {
					LOG.info(lawyer.getUsername() + "所在的事务所为空,忽略");
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
