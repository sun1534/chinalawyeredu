/**
 * 
 */
package com.changpeng.common.sync.pxxt;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.sync.lgpt.GuildUserInfo;
import com.changpeng.common.sync.lgpt.LgptDAO;
import com.changpeng.common.sync.lgpt.OrgOfficeInfo;
import com.changpeng.common.sync.lgpt.OrgUserInfo;
import com.changpeng.common.sync.lgpt.PersonInfo;
import com.changpeng.common.util.Chinese2Pinyin;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.service.SysUserService;

/**
 * @author 华锋 Jul 11, 2009-4:37:09 PM
 * 
 */
public class PxxtTransfer {

	private static Log LOG = LogFactory.getLog(PxxtTransfer.class);

	private static LawyersService service = null;
	private static BasicService basicService = null;
	private static SysGroupService groupService = null;
	private static SysUserService sysUserService=null;
	static {
		service = (LawyersService) Globals.getMainBean("lawyersService");
		basicService = (BasicService) Globals.getMainBean("basicService");
		groupService = (SysGroupService) Globals.getMainBean("sysGroupService");
		sysUserService= (SysUserService) Globals.getMainBean("sysUserService");
	}

	public static void syncLawyers(PersonInfo person)throws Exception {
		
		if(person.getRoleCode().equals("7")){
			LOG.warn("实习人员,不进行同步!"+person.getPersonName());
			return;
		}
		
		Lawyers lawyers = null;
		try {
			boolean isnew = false;
			lawyers=service.getLawyerBySystemno(person.getPersonCode());
			if (lawyers == null) {
				lawyers = new Lawyers();
				isnew = true;
			}

			String orgId = person.getOrgId();

			SysGroup group = (SysGroup) groupService.getGroupBySystemno("9" + orgId);
			int directunion = 0;
			int theoffice = 0;
			int provinceunion = 0;
			if (group != null) {
				directunion = group.getParentid();
				theoffice = group.getGroupid();
				provinceunion = group.getDirectgroup();
				LOG.info("律师所在的部门:::name=" + group.getGroupname() + ",type=" + group.getGrouptype());

			} else {
				LOG.warn("(9)+" + orgId + "对应的部门不存在...");
				return;
			}

			lawyers.setBirthday(person.getBirthday());
			lawyers.setCertno(person.getIdentityCard());
			lawyers.setCityid(0);
			lawyers.setCountryid(0);
			lawyers.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			lawyers.setCreateuser(0);
			lawyers.setCreateusername("律管平台同步");
			lawyers.setDegree(0);
			lawyers.setDirectunion(directunion);
			lawyers.setDistrictid(0);
			lawyers.setEmail(person.getEmail());
			lawyers.setFax(person.getHomeTelephone());
			lawyers.setForeignlan(0);
			lawyers.setForeignlevel("");
			lawyers.setGender(person.getSex());
			lawyers.setHomephone(person.getHomeTelephone());
			lawyers.setIsmarrige(true);
			lawyers.setLawyerenname(Chinese2Pinyin.to2pinyin(person.getPersonName()));
			lawyers.setLawyername(person.getPersonName());
			lawyers.setLawyerno(person.getCertificateNo());
			lawyers.setLawyertype(Integer.parseInt(person.getRoleCode()));
			lawyers.setLoginname(person.getCertificateNo());
			lawyers.setMobile1(person.getMobilePhone());
			lawyers.setMobile2(person.getMobilePhone());
			lawyers.setMsn(null);
			lawyers.setOfficephone(person.getOfficeTelephone());
			lawyers.setPasswd(person.getIdentityCard());
			lawyers.setPhoto(null);
			lawyers.setPhotoname(null);
			lawyers.setPolicy(0);
			lawyers.setPostcode(person.getPost());
			lawyers.setProvinceid(0);
			lawyers.setProvinceunion(provinceunion);
			lawyers.setQq(null);
			lawyers.setRegsrc(3);
			lawyers.setRemarks("律管平台同步");
			lawyers.setSchool(person.getGraduateSchool());
			lawyers.setSpecility(person.getSpeciality());
			lawyers.setStatus(0);
			lawyers.setSystemno(person.getPersonCode());
			lawyers.setTheoffice(theoffice);
			lawyers.setWorktime(person.getJoinDate());
			lawyers.setZhiyedate(person.getJoinDate());
			if(person.getPersonStatus()!=null&&person.getPersonStatus().equals("6"))
			lawyers.setStatus(Integer.parseInt(person.getPersonStatus()));
			
			String image="";
			if(person.getFilename()!=null&&!person.getFilename().equals("")&&!person.getFilename().equalsIgnoreCase("null")){
				image=person.getFilename();
			}
			lawyers.setPhoto(image);
			lawyers.setPhotoname(image);
			String flag = person.getDataType();

			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				if (isnew)
					service.addLawyer(lawyers);
				else
					service.updateLawyers(lawyers);
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew)
					basicService.delete(lawyers);
				else
					LOG.warn("删除律师,但是对应的律师不存在:" + lawyers.getSystemno());

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("培训系统律师同步成功:" + lawyers.getSystemno() + "=>" + flag);
		} catch (Exception e) {
			LOG.error("培训系统律师同步异常:" + lawyers.getSystemno() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static void syncOrgs(OrgOfficeInfo org)throws Exception {

		SysGroup group = null;
		try {
			group = (SysGroup) groupService.getGroupBySystemno("9" + org.getOrgId());
			boolean isnew = false;
			if (group == null) {
				group = new SysGroup();
				isnew = true;
			}
			String parentOrg = org.getGuildId();
			SysGroup parentGroup=null;
			if(parentOrg.equals("0408")){
				 parentGroup = (SysGroup) groupService.getGroupBySystemno("7330");
			}else{
				 parentGroup = (SysGroup) groupService.getGroupBySystemno("-1" + parentOrg);
			}
			
			int parentId = 0;
			int directgroup = 0;
			if (parentGroup != null) {
				parentId = parentGroup.getGroupid();
				if (parentGroup.getGrouptype() == 2) {// 市律协
					directgroup = parentGroup.getParentid();
				} else {
					directgroup = parentGroup.getGroupid();
				}
			}

			group.setAddress(org.getAddress());
			group.setComments("律管平台同步");
			group.setContacter(null);
			group.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			group.setCreatetype(3);
			group.setCreateuser("律管平台");
			group.setDelflag(false);
			group.setDirectgroup(directgroup);
			group.setFax(org.getFax());
			group.setGroupenname(org.getCertCode());
			group.setGrouplevel(2);
			group.setGroupname(org.getOrgName());
			group.setGrouptype(1);
			group.setParentid(parentId);
			group.setPhone(org.getOfficePhone());
			group.setSystemno("9" + org.getOrgId());
			group.setUsercnts(0);
			String flag = org.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				if (isnew)
					basicService.save(group);
				else
					basicService.update(group);
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
//					group.setDelflag(true);
//					basicService.update(group);
					basicService.delete(group);
				} else
					LOG.warn("删除部门,但是对应的部门不存在:" + group.getSystemno());

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("培训系统部门同步成功:" + group.getSystemno() + "=>" + flag);
		} catch (Exception e) {
			LOG.error("培训系统部门同步异常:" + group.getSystemno() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 */
	public static void syncLXManagers(GuildUserInfo info)throws Exception {
		SysUser user = null;
		try {
			String systemno="";
			if(info.getDepatId().equals("0408")){
				if(info.getLoginName().equals("gxlxadmin")){
					systemno="-1" + info.getDepatId();
				}else{
					systemno="7330";
				}
			}else{
				systemno="-1" + info.getDepatId();
			}
			
			SysGroup group = (SysGroup) groupService.getGroupBySystemno(systemno);
			if (group == null) {
				LOG.warn(info.getUserName() + "对应的律协(-1+)" + info.getDepatId() + "在系统中不存在");
				return;
			}
			user=(SysUser)sysUserService.getSysUserBySystemno(info.getUserId());
			boolean isnew = false;
			if (user == null) {
				user = new SysUser();
				isnew = true;
			}
			user.setBirthday(null);
		
			user.setComments("律管平台同步");
			user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			user.setCreateuser("律管平台");
			user.setCreateuserid(0);
			user.setDelflag(false);
			user.setEmail(null);
			user.setEmployeeno(info.getUserId());
			user.setGender("F");
			user.setLoginname(info.getLoginName());
			user.setMobile(null);
			
			user.setOfficephone(null);
			user.setPassword(info.getPassword());
			if(group.getGrouptype()==2){ //市律协的
				user.setProvinceid(group.getParentid());
				user.setCityid(group.getGroupid());
				user.setOfficeid(0);
			}else if(group.getGrouptype()==3){//省律协的
				user.setProvinceid(group.getGroupid());
				user.setCityid(0);
				user.setOfficeid(0);
			}
		
			
			user.setStatus((short)0);
			user.setSysGroup(group);
			SysRole role=new SysRole();
		 
			role.setRoleid(group.getGrouptype());
			user.setSysRole(role);
			user.setSystemno(info.getUserId());
			user.setUsername(info.getUserName());
	
			String flag = info.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				if (isnew)
					basicService.save(user);
				else
					basicService.update(user);
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					group.setDelflag(true);
					basicService.update(group);
				} else
					LOG.warn("删除律协管理员,但是对应的管理员不存在:" + info.getUserId());

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("培训系统律协管理员同步成功:" + info.getUserId() + "=>" + flag);
		} catch (Exception e) {
			LOG.error("培训系统律协管理员同步异常:" + info.getUserId() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static void syncOrgManagers(OrgUserInfo info)throws Exception {

		SysUser user = null;
		try {
			SysGroup group = (SysGroup) groupService.getGroupBySystemno("9" + info.getOrgId());
			if (group == null) {
				LOG.warn(info.getUserName() + "对应的事务所(9+)" + info.getOrgId() + "在系统中不存在");
				return;
			}
			user=(SysUser)sysUserService.getSysUserBySystemno(info.getUserId());
			boolean isnew = false;
			if (user == null) {
				user = new SysUser();
				isnew = true;
			}
			user.setBirthday(null);
			
			user.setComments("律管平台同步");
			user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			user.setCreateuser("律管平台");
			user.setCreateuserid(0);
			user.setDelflag(false);
			user.setEmail(null);
			user.setEmployeeno(info.getUserId());
			user.setGender("F");
			user.setLoginname(info.getUserName());
			user.setMobile(null);
			user.setOfficeid(0);
			user.setOfficephone(null);
			user.setPassword(info.getPassword());
			user.setProvinceid(group.getDirectgroup());
			user.setCityid(group.getParentid());
			user.setOfficeid(group.getGroupid());
			user.setStatus((short)0);
			user.setSysGroup(group);
			SysRole role=new SysRole();
			role.setRoleid(1);
			user.setSysRole(role);
			user.setSystemno(info.getUserId());
			user.setUsername(info.getUserName());
	
			String flag = info.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				if (isnew)
					basicService.save(user);
				else
					basicService.update(user);
			} else if (flag != null && flag.equals("deleted")) {
				if (!isnew) {
					group.setDelflag(true);
					basicService.update(group);
				} else
					LOG.warn("删除事务所管理员,但是对应的管理员不存在:" + info.getUserId());

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("培训系统事务所管理员同步成功:" + info.getUserId() + "=>" + flag);
		} catch (Exception e) {
			LOG.error("培训系统事务所管理员同步异常:" + info.getUserId() + "=>" + e);
			e.printStackTrace();
			throw e;
		}
	}
}
