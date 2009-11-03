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
 * 系统管理员和事务所管理员的同步
 * 
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class UserSyncRequest extends ElearningSyncRequests {

	private static final Log LOG = LogFactory.getLog(UserSyncRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String requestSyncService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		// StringBuilder temp = new StringBuilder();
		// StringBuilder lawyertemp = new StringBuilder();
		try {
			SysUserService sysUserService = (SysUserService) globals.getBean("sysUserService");
			BasicService basicService = (BasicService) globals.getBean("basicService");
			Iterator iterator = rootElement.elementIterator("user");
			if (iterator != null) {
				while (iterator.hasNext()) {

					Element user = (Element) iterator.next();
					// <userid></userid> <!--用户的ID，在系统中的唯一主键，必填-->
					// <loginname></loginname> <!--用户的登录名,必填-->
					// <password></password> <!--用户登陆系统的密码明文，必填-->
					// <username></username> <!--用户的名字,必填-->
					// <certno></certno> <!--用户的身份证号，可选-->
					// <picurl></picurl> <!--用户对应的相片的路径，可选-->
					// <officeid></officeid> <!--如果是管理员角色，此为对应的事务所编号,否则可以不填-->
					// <roleid></roleid> <!--用户角色，事务所管理员还是系统管理员，事务所管理员的话，officeid必填-->

					String userid = user.elementText("userid");
					String loginname = user.elementText("loginname");
					String password = user.elementText("password");
					String username = user.elementText("username");
					String certno = user.elementText("certno");
					String picurl = user.elementText("picurl");
					String photo = "";
					String officeid = user.elementText("officeid");
					String roleid = user.elementText("roleid");
					// 这里要下载图片
					String synctype = user.attributeValue("synctype");
					result.append("<user userid=\"" + userid + "\">");
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
							sysUser.setLoginname(loginname);
							sysUser.setUsername(username);
							sysUser.setCertno(certno);
							sysUser.setUserid(Integer.parseInt(userid));
							sysUser.setPhoto(photo);

							// 这里要将照片下载下来
							LOG.info("新增用户:" + sysUser.getUserid() + ":" + sysUser.getUsername() + ":" + sysUser.getLoginname());
						
							// 1是事务所管理员,2是系统管理员,系统管理员的话,自动分配到groupid为1的group
							if (roleid.equals("1")) {
								// 这里要分配角色
								SysGroup group = (SysGroup) basicService.get(SysGroup.class, Integer.parseInt(officeid));
								sysUser.setSysGroup(group);
								sysUserService.addGroupManager(sysUser);
							}
							else if (roleid.equals("2")) {
								SysGroup group = (SysGroup) basicService.get(SysGroup.class, 1);
								sysUser.setSysGroup(group);
								sysUserService.addSysManager(sysUser);
							}
							result.append("<respcode>0</respcode>");
							result.append("<respmsg>新增成功</respmsg>");
						}
						else if (synctype.equals("2")) {// 更新
							LOG.info("更新用户:" + userid + ",roleid:" + roleid + ",groupid:" + officeid);
							SysUser sysUser = (SysUser) basicService.get(SysUser.class, Integer.parseInt(userid));
							if (picurl != null && !picurl.equals("") && sysUser.getComments().indexOf(picurl) != -1) {
								String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
								photo = com.changpeng.service.util.DownloadFile.downloadPic(picurl, indexDir);
								sysUser.setPhoto(photo);
							}
							sysUser.setUsername(username);
							sysUser.setLoginname(loginname);
							sysUser.setPassword(com.changpeng.common.util.MD5.md5(password));
							sysUser.setCertno(certno);
							sysUserService.updateUser(sysUser);
							LOG.info("先不修改该用户所属的角色和部门");
							result.append("<respcode>0</respcode>");
							result.append("<respmsg>更新成功</respmsg>");
						}
						else if (synctype.equals("3")) {// 删除
							int useridint = Integer.parseInt(userid);
							LOG.info("删除用户:" + userid);
							sysUserService.deleteUser(useridint);

							String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
							SysUser sysUser = (SysUser) basicService.get(SysUser.class, useridint);
							if (sysUser.getPhoto() != null && !"".equals(sysUser.getPhoto())) { // 删除掉之前的照片
								File oldfile = new File(indexDir + System.getProperty("file.separator") + sysUser.getPhoto());
								LOG.info("照片是否删除成功:" + oldfile.getAbsoluteFile() + ":::" + oldfile.delete());
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
						result.append("<respmsg>用户资料同步异常:").append(e.getMessage()).append("</respmsg>");
					}
					result.append("</user>");
				}
			}
		}
		catch (Exception e) {
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>用户资料同步异常:").append(e.getMessage()).append("</respmsg>");
		}
		// result.append(temp);
		// result.append(lawyertemp);
		result.append(result);
		result.append("</response>");
		return result.toString();
	}
}
