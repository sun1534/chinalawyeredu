/**
 * GetLessonRequest.java
 */

package com.changpeng.service.szsync;

import java.io.File;
import java.text.DateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Element;

import com.changpeng.common.BasicService;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 律师资料的同步
 * 
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class LawyerSyncRequest extends ElearningSyncRequests {

	private static final Log LOG = LogFactory.getLog(LawyerSyncRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String requestSyncService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		// StringBuilder temp = new StringBuilder();
		// StringBuilder lawyertemp = new StringBuilder();
		try {
			SysUserService sysUserService = (SysUserService) globals.getBean("sysUserService");
			BasicService basicService = (BasicService) globals.getBean("basicService");
			Iterator iterator = rootElement.elementIterator("lawyer");
			if (iterator != null) {
				while (iterator.hasNext()) {

					Element lawyer = (Element) iterator.next();
					// <lawyerid></lawyerid> <!--律师的ID，在系统中的唯一主键，必填-->
					// <lawyerno></lawyerno> <!--律师执业资格证号,必填-->
					// <password></password> <!--律师登陆系统的密码明文，必填-->
					// <lawyername></lawyername> <!--律师名字,必填-->
					// <certno></certno> <!--律师的身份证号，可选-->
					// <picurl></picurl> <!--律师对应的图片路径，可选-->
					// <officeid></officeid> <!--此律师对应的事务所编号,必填-->

					String lawyerid = lawyer.elementText("lawyerid");
					String lawyerno = lawyer.elementText("lawyerno");
					String password = lawyer.elementText("password");
					String lawyername = lawyer.elementText("lawyername");
					String certno = lawyer.elementText("certno");
					String picurl = lawyer.elementText("picurl");
					String photo = "";
					String officeid = lawyer.elementText("officeid");

					// 这里要下载图片
					String synctype = lawyer.attributeValue("synctype");
					result.append("<lawyer lawyerid=\"" + lawyerid + "\">");
					try {
						if (synctype.equals("1")) {

							if (picurl != null && !picurl.equals("")) {
								String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
								photo = com.changpeng.service.util.DownloadFile.downloadPic(picurl, indexDir);
							}

							SysUser sysUser = new SysUser();
							sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
							sysUser.setCreateuser("同步接口");
							sysUser.setComments("同步接口:" + picurl);
							sysUser.setDelflag(false);
							sysUser.setStatus((byte) 0);
							sysUser.setPassword(password);
							sysUser.setLoginname(lawyerno);
							sysUser.setLawerno(lawyerno);
							sysUser.setUsername(lawyername);
							sysUser.setCertno(certno);
							sysUser.setUserid(Integer.parseInt(lawyerid));
							sysUser.setPhoto(photo);

							// 这里要将照片下载下来
							LOG.info("新增律师:" + sysUser.getUserid() + ":" + sysUser.getUsername() + ":" + sysUser.getLoginname());

							// 这里要分配角色
							SysGroup group = (SysGroup) basicService.get(SysGroup.class, Integer.parseInt(officeid));
							sysUser.setSysGroup(group);
							sysUserService.addLawyer(sysUser);

							result.append("<respcode>0</respcode>");
							result.append("<respmsg>律师新增成功</respmsg>");
						}
						else if (synctype.equals("2")) {// 更新
							LOG.info("更新律师:" + lawyerid + ",groupid:" + officeid);
							SysUser sysUser = (SysUser) basicService.get(SysUser.class, Integer.parseInt(lawyerid));
							if (picurl != null && !picurl.equals("") && sysUser.getComments().indexOf(picurl) != -1) {
								String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
								photo = com.changpeng.service.util.DownloadFile.downloadPic(picurl, indexDir);
								sysUser.setPhoto(photo);
							}
							sysUser.setUsername(lawyername);
							sysUser.setLoginname(lawyerno);
							sysUser.setLawerno(lawyerno);
							sysUser.setPassword(com.changpeng.common.util.MD5.md5(password));
							sysUser.setCertno(certno);
							sysUserService.updateUser(sysUser);
							LOG.info("先不修改该律师所属的部门");
							result.append("<respcode>0</respcode>");
							result.append("<respmsg>律师更新成功</respmsg>");
						}
						else if (synctype.equals("3")) {// 删除
							int lawyeridint = Integer.parseInt(lawyerid);
							LOG.info("删除律师:" + lawyerid);
							sysUserService.deleteUser(lawyeridint);

							String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
							SysUser sysUser = (SysUser) basicService.get(SysUser.class, lawyeridint);
							if (sysUser.getPhoto() != null && !"".equals(sysUser.getPhoto())) { // 删除掉之前的照片
								File oldfile = new File(indexDir + System.getProperty("file.separator") + sysUser.getPhoto());
								LOG.info("律师照片是否删除成功:" + oldfile.getAbsoluteFile() + ":::" + oldfile.delete());
							}
							result.append("<respcode>0</respcode>");
							result.append("<respmsg>删除成功</respmsg>");
						}
						else {
							result.append("<respcode>").append(-1).append("</respcode>");
							result.append("<respmsg>同步方式不是1，2，3，忽略</respmsg>");
						}
					}
					catch (Exception e) {
						result.append("<respcode>").append(-1).append("</respcode>");
						result.append("<respmsg>律师资料同步异常:").append(e.getMessage()).append("</respmsg>");
					}
					result.append("</lawyer>");
				}
			}
		}
		catch (Exception e) {
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>律师资料同步异常:").append(e.getMessage()).append("</respmsg>");
		}
		// result.append(temp);
		// result.append(lawyertemp);
		result.append(result);
		result.append("</response>");
		return result.toString();
	}
}
