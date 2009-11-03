package com.changpeng.common.mssql;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.context.Globals;
import com.changpeng.common.util.MD5;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 定时连接到sqlserver2005，获取变动的律师和事务所信息。如果没有对应信息，则执行到mysql数据库的连接
 * 
 * @author 华锋 2008-6-17 上午10:18:18
 * 
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);

	public void run() {
		Globals globals = new Globals();
		BasicService service = (BasicService) globals.getBean("basicService");
		SysUserService userservice = (SysUserService) globals.getBean("sysUserService");
		SysGroupService groupservice = (SysGroupService) globals.getBean("sysGroupService");
		List<UserUpdLog> userUpdLogs = new ArrayList<UserUpdLog>();
		List<LawyerUpdLog> lawyerUpdLogs = new ArrayList<LawyerUpdLog>();

		LOG.debug(service + "," + userservice + "," + groupservice);

		try {
			SqlServerDataUtil.getSqlServerDatas(userUpdLogs, lawyerUpdLogs);
			LOG.info("律师资料变动:" + lawyerUpdLogs.size() + ",事务所资料变动:" + userUpdLogs.size());
		} catch (Exception e) {
			LOG.error("从SqlServer2005获取数据失败:" + e);
		}

		// 先对事务所表进行处理
		int usersize = userUpdLogs.size();
		int lawersize = lawyerUpdLogs.size();

		for (int i = 0; i < usersize; i++) {
			UserUpdLog updlog = userUpdLogs.get(i);
			LOG.info("updlog===" + updlog.username + "==" + updlog.dotype + ",power=" + updlog.power);
			try {

				SysGroup sysGroup = (SysGroup) service.get(SysGroup.class, updlog.id);
//				boolean saveuser = false;
//				if (sysGroup == null)
//					updlog.dotype = "inserted";
//				else
//					updlog.dotype = "updated";

				SysUser sysUser = (SysUser) service.get(SysUser.class, updlog.id);
//				if (sysUser == null)
//					saveuser = true;
//				else
//					saveuser = false;

				// if (updlog.dotype.equals("inserted")) {
				if (updlog.power == 1) {// 律协管理员，只新增到sysuser中,
					// SysGroup sysGroup = updlog.getSysGroup();
					// groupservice.addGroup(sysGroup);
					// 这里设置所有的系统管理员都属于深圳律协
					
					userservice.addSysManager(updlog.getSysUser());
					LOG.info("新增系统管理员成功:" + updlog.username + "," + updlog.licenceno);
				} else if (updlog.power == 3) {// 事务所管理员
					if (sysGroup == null) {
						sysGroup = updlog.getSysGroup();
						groupservice.addGroup(sysGroup);

					} else {
						sysGroup.setGroupname(updlog.username);
						sysGroup.setGroupenname(updlog.licenceno);
						sysGroup.setComments(updlog.mailaddr);
						sysGroup.setContacter(updlog.telcall);
						sysGroup.setPhone(updlog.telephone);
						sysGroup.setGrouplevel((short) 2);
						sysGroup.setParentid(1);
						service.update(sysGroup);

					}
					if (sysUser == null) {
						sysUser = updlog.getSysUser();
						sysUser.setSysGroup(sysGroup);
						userservice.addGroupManager(sysUser);
						LOG.info("新增事务所管理员成功:" + updlog.username + "," + updlog.licenceno);
					} else {
						sysUser.setUsername(updlog.username);
						sysUser.setLoginname(updlog.licenceno);
						sysUser.setPassword(MD5.md5(updlog.password));
						sysUser.setComments((sysUser.getComments() == null ? "" : sysUser.getComments()) + ",同步修改");
						sysUser.setOfficephone(updlog.telephone);
						service.update(sysUser);
						LOG.info("更新事务所和管理员OK：" + updlog.username);

					}
				} else {
					// LOG.warn("其他类型的权限,不予处理::" + updlog.power + ",updlog.id="
					// + updlog.id);
					LOG.warn("其他类型的权限,不对Group进行处理::" + updlog.power + ",updlog.id=" + updlog.id);
				}

				if (updlog.dotype.equals("deleted")) {
					LOG.info("事务所删除请求,不予理睬先:::" + updlog.id + ",名称:" + updlog.username);
				}
			} catch (Exception e) {
				LOG.error("事务所同步异常:" + e);
				// e.printStackTrace();
			}
		}
		// 再对用户表进行新增修改和删除
		for (int i = 0; i < lawersize; i++) {
			LawyerUpdLog updlog = lawyerUpdLogs.get(i);
			
			if(updlog.lawername==null||updlog.lawername.indexOf("script")!=-1){
				LOG.warn("姓名被注入脚本:"+updlog.lawno+","+updlog.lawername);
				continue;
			}
			
			LOG.info("updlog===" + updlog.lawno + "==" + updlog.dotype + ",enterpriseid=" + updlog.enterpriseid);
			try {

				LOG.debug("图片地址::" + updlog.lsxp);

				if (updlog.lsxp != null && !updlog.lsxp.equals("")) {
					if (updlog.lsxp.startsWith("/"))
					updlog.lsxp = "/photo/" + updlog.lsxp;
				}

				String photo = "";

				SysUser sysUser = (SysUser) service.get(SysUser.class, updlog.lawid);
				if (sysUser == null)
					updlog.dotype = "inserted";
				else
					updlog.dotype = "updated";
				LOG.info("律师修改DOTYPE:" + updlog.dotype+","+updlog.lawid);

				if (updlog.dotype.equals("inserted")) {

					if (updlog.lsxp != null && !updlog.lsxp.equals("")) {
						updlog.lsxp = CommonDatas.SysParameter.get("PicWebsite").toString() + updlog.lsxp;
						String indexDir = CommonDatas.SysParameter.get("PicPath").toString();
						photo = DownloadFile.downloadPic(updlog.lsxp, indexDir);

					}
				
					if (updlog.lawno != null && !"".equals(updlog.lawno)) {
						SysGroup sysGroup = (SysGroup) service.get(SysGroup.class, updlog.enterpriseid);
						sysUser = updlog.getLawer();
						sysUser.setPassword(sysUser.getLoginname());
						sysUser.setPhoto(photo);
						sysUser.setSysGroup(sysGroup);
						sysUser.setDelflag(false);
						userservice.addLawyer(sysUser);
						LOG.info("新增律师OK:" + updlog.lawno);
					} else {
						LOG.warn("EnterpriseID：" + updlog.enterpriseid + ",LAWNO:" + updlog.lawno);
					}
				} else if (updlog.dotype.equals("updated")) {
					// /UpFiles/Attach/2006/11/24/151035.JPG
					// SysUser sysUser = (SysUser) service.get(SysUser.class,
					// updlog.lawid);
					LOG.debug("updated的图片判断:::"
							+ (updlog.lsxp != null && !updlog.lsxp.equals("") && sysUser.getComments().indexOf(updlog.lsxp) == -1));

					if (updlog.lsxp != null && !updlog.lsxp.equals("") && sysUser.getComments().indexOf(updlog.lsxp) == -1) {
						// String indexDir =
						// ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
						String indexDir = CommonDatas.SysParameter.get("PicPath").toString();
						sysUser.setComments(new java.sql.Timestamp(System.currentTimeMillis()) + "同步修改:" + updlog.lsxp);
						updlog.lsxp = CommonDatas.SysParameter.get("PicWebsite").toString() + updlog.lsxp;
						photo = DownloadFile.downloadPic(updlog.lsxp, indexDir);
						LOG.debug("updated indexDir::" + indexDir + ",photo::" + photo);
						sysUser.setPhoto(photo);

					}

					sysUser.setPassword(MD5.md5(updlog.password));
					sysUser.setLoginname(updlog.lawno);
					sysUser.setLawerno(updlog.lawno);
					sysUser.setUsername(updlog.lawername);

					SysGroup sysGroup = (SysGroup) service.get(SysGroup.class, updlog.enterpriseid);
					sysUser.setSysGroup(sysGroup);

					// 更新所属的律师事务所信息
					service.update(sysUser);
					LOG.info("更新律师OK:" + updlog.lawno);
				} else if (updlog.dotype.equals("deleted")) {
					// 删除这个律师
					userservice.deleteUser(updlog.lawid);

					LOG.info("删除律师OK:" + updlog.lawno);
				}
			} catch (Exception e) {
				LOG.error("律师同步异常:" + updlog.lawno + ":" + e);
				e.printStackTrace();

			}
		}
		if (userUpdLogs.size() == 0 && lawyerUpdLogs.size() == 0) {
			try {
				service.findBySqlQuery("select userid from sys_user where 1=2");
				LOG.info("执行定时连接到数据库");
			} catch (Exception e) {
				LOG.error("执行定时连接到数据库异常:" + e);
			}
		}
	}
}