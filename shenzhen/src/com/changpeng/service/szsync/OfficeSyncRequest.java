/**
 * GetLessonRequest.java
 */

package com.changpeng.service.szsync;

import java.text.DateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.changpeng.common.BasicService;
import com.changpeng.models.system.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class OfficeSyncRequest extends ElearningSyncRequests {

	private static final Log LOG = LogFactory.getLog(OfficeSyncRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String requestSyncService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");

		try {
			// 事务所的同步
			BasicService basicService = (BasicService) globals.getBean("basicService");
			SysGroupService groupService = (SysGroupService) globals.getBean("sysGroupService");
			// <officeid>111111</officeid> <!--事务所的ID，在系统中的唯一主键，必填-->
			// <officeno>190223</officeno> <!--事务所执业资格证号,必填-->
			// <officename>长鹏律师事务所</officename> <!--事务所名称,必填-->
			// <linkman></linkman> <!--事务所联系人，如果没有，可以不填-->
			Iterator iterator = rootElement.elementIterator("response");
			if (iterator != null) {
				while (iterator.hasNext()) {
					Element office = (Element) iterator.next();
					String synctype = office.attributeValue("synctype");
					int officeid = Integer.parseInt(office.elementText("officeid"));
					String officename = office.elementText("officename");
					String officeenname = office.elementText("officeno");
					String linkman = office.elementText("linkman");
					String address = office.elementText("address");
					result.append("<office officeid=\"" + officeid + "\">");
					try {
						if (synctype.equals("1")) {

							LOG.info("新增事务所:" + officeid);
							SysGroup sysGroup = new SysGroup();
							sysGroup.setComments("接口同步");
							sysGroup.setContacter(linkman);
							sysGroup.setCreateuser("接口同步");
							sysGroup.setDelflag(false);
							sysGroup.setGroupenname(officeenname);
							sysGroup.setGroupid(officeid);
							sysGroup.setGrouplevel((short) 2);
							sysGroup.setGroupname(officename);
							sysGroup.setParentid(1);
							sysGroup.setComments(address);

							basicService.save(sysGroup);
							result.append("<respcode>0</respcode>");
							result.append("<respmsg>新增成功</respmsg>");
						}
						else if (synctype.equals("2")) {
							LOG.info("更新事务所:" + officeid);
							SysGroup group = (SysGroup) basicService.get(SysGroup.class, officeid);

							group.setGroupname(officename);
							group.setGroupenname(officeenname);
							group.setContacter(linkman);
							group.setComments(address);
							basicService.update(group);

							result.append("<respcode>0</respcode>");
							result.append("<respmsg>更新成功</respmsg>");
						}
						else if (synctype.equals("3")) {
							LOG.info("删除事务所:" + officeid);
							int i = groupService.deleteGroup(officeid);

							result.append("<respcode>").append(i).append("</respcode>");
							if (i == 0)
								result.append("<respmsg>删除成功</respmsg>");
							else if (i == -1) {
								result.append("<respmsg>部门下面还有人员,不能删除</respmsg>");
							}
							else if (i == -2) {
								result.append("<respmsg>部门下面还有下级部门,不能删除</respmsg>");
							}
						}
						else {
							result.append("<respcode>").append(-1).append("</respcode>");
							result.append("<respmsg>同步方式不是1，2，3，忽略</respmsg>");
						}
					}
					catch (Exception e) {
						result.append("<respcode>").append(-1).append("</respcode>");
						result.append("<respmsg>事务所资料同步异常:").append(e.getMessage()).append("</respmsg>");
					}
					result.append("</office>");
				}
			}
		}
		catch (Exception e) {
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>事务所同步失败:").append(e.getMessage()).append("</respmsg>");
		}
		// result.append(temp);
		// result.append(lawyertemp);
		result.append(result);
		result.append("</response>");
		return result.toString();
	}
}
