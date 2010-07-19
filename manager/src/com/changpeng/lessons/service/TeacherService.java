/**
 * LessonsService.java
 */

package com.changpeng.lessons.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.models.Teacher;

/**
 * 授课老师的新增删除等
 * 
 * @author 华锋 2008-5-16 上午10:55:55
 * 
 */
public class TeacherService extends BasicService {
	private static Log _LOG = LogFactory.getLog(TeacherService.class);
	
	private static int TEACHER_ROLE=100;
	private static int TEACHER_GROUP=2;
	
	/**
	 * 新增1个老师信息
	 * @param teachers
	 */
	@Transactional
	public void addTeacher(Teacher teachers){
	
		SysUser sysUser=new SysUser();
		sysUser.setBirthday(teachers.getBirthday());
		sysUser.setUsername(teachers.getUsername());
		sysUser.setEmail(teachers.getEmail());
		sysUser.setGender(teachers.getGender());
		sysUser.setLoginname(teachers.getLoginname());
		sysUser.setPassword(teachers.getPassword());
		sysUser.setMobile(teachers.getMobile());
		sysUser.setOfficephone(teachers.getOfficephone());
		sysUser.setStatus(teachers.getStatus());
		SysRole role=new SysRole();
		SysGroup group=new SysGroup();
		
		role.setRoleid(TEACHER_ROLE);
		group.setGroupid(TEACHER_GROUP);
		sysUser.setSysRole(role);
		sysUser.setSysGroup(group);

		basicDAO.save(sysUser);
		teachers.setUserid(sysUser.getUserid());
		basicDAO.save(teachers);
	}
	/**
	 * 修改一个老师的信息
	 * @param teachers
	 */
	@Transactional
	public void updateTeacher(Teacher teachers){
		basicDAO.update(teachers);
		SysUser sysUser=(SysUser)basicDAO.get(SysUser.class, teachers.getUserid());
		
		System.out.println(teachers.getUserid()+"===="+sysUser);
		sysUser.setBirthday(teachers.getBirthday());
		sysUser.setEmail(teachers.getEmail());
		sysUser.setUsername(teachers.getUsername());
		sysUser.setGender(teachers.getGender());
		sysUser.setLoginname(teachers.getLoginname());
		sysUser.setPassword(teachers.getPassword());
		sysUser.setMobile(teachers.getMobile());
		sysUser.setOfficephone(teachers.getOfficephone());
		sysUser.setStatus(teachers.getStatus());
		basicDAO.update(sysUser);
	}
	/**
	 * 删除一个老师的信息
	 * @param teacherId
	 */
	@Transactional
	public void delteTeacher(int teacherId){
		String sql1="delete from sys_user where userid="+teacherId;
		String sql2="delete from teacher where userid="+teacherId;
		basicDAO.executeSql(sql1);
		basicDAO.executeSql(sql2);
	}
	/**
	 * 修改密码
	 * @param userId
	 * @param password
	 */
	@Transactional
	public void updatePassword(int userId,String password){
		String sql1="update sys_user set password='"+password+"' where userid="+userId;
		String sql2="update teacher set password='"+password+"' where userid="+userId;
		basicDAO.executeSql(sql1);
		basicDAO.executeSql(sql2);
	}


}