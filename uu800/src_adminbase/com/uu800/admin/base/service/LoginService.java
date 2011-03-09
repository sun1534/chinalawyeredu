package com.uu800.admin.base.service;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.entity.TsysUser;
import com.uu800.admin.base.entity.User;
import com.uu800.admin.base.dao.LoginDAO;
@Service
public class LoginService  extends BasicService 
{
	private LoginDAO loginDAO;
	
	/**
	 * @param loginDAO
	 */
	@Resource
	public void setLoginDAO(LoginDAO loginDAO) 
	{
		this.basicDao = loginDAO;
		this.loginDAO = loginDAO;
	}
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @return
	 */
	public TsysUser Login(String username,String password){
		
		String querysql = "from TsysUser where username=? and password=?";
		
		String[] parameter = new String[2];
		parameter[0]=username;
		parameter[1]=com.uu800.webbase.util.Md5.MD5(password);
		
		List list = loginDAO.findByQuery(querysql,parameter);
		
		System.out.println("========="+list+"==>"+parameter[1]+"===>"+username);
		
		if(list==null||list.size()!=1)
			return null;
		else
			return (TsysUser)list.get(0);
	}
	
	
	/**
	 * 功能：取用户权限列表
	 * 
	 * @param user
	 * @return HashMap
	 */
	public HashSet<String> getUserRights(User user) 
	{
		String sql = "select upper(rightcode) from v_userright Where userid="
				+ user.getUserid();
		if(user.getUsertype()==0)
		{//超级管理员取所有权限
			sql = "select  upper(rightcode) from tsys_right where moduletype='SYS' and nodetype=1 ";
		}
				
		List<String> list = loginDAO.findBySqlQuery(sql);
		HashSet<String> set = new HashSet<String>(list);
		return set;
	}
	/**
	 * 功能：取菜单集合函数 *
	 * 
	 * @return String
	 */	
	public List getMenuList(User user) {
	   return	loginDAO.getMenuList(user);
	}
}
