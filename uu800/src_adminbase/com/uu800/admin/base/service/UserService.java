
package com.uu800.admin.base.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.webbase.util.TreeInfo;
import com.uu800.admin.base.dao.UserDAO;
import com.uu800.admin.base.entity.TsysAreaCode;
import com.uu800.admin.base.entity.TsysOrg;
import com.uu800.admin.base.entity.TsysUser;

public class UserService  extends BasicService 
{
	@Resource
    private UserDAO userDAO;
	/**
	 * @param UserDAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		super.basicDao = userDAO;
	}
	
	/**
	 * 取组织结构MAP (orgid,orgname)
	 * @return LinkedHashMap
	 */
	public LinkedHashMap getOrgList() {
		return this.userDAO.getOrgList();
	}
	
	/**
	 * 取区域信息Map
	 * @return
	 */
	public LinkedHashMap getAreaCodeList()
	{
		LinkedHashMap map = new LinkedHashMap();
		List list = userDAO.findAll(TsysAreaCode.class);
		for(Object obj:list)
		{	
			 TsysAreaCode tsysAreaCode=(TsysAreaCode) obj;
			 map.put(tsysAreaCode.getAreacode(),tsysAreaCode.getAreaname());
		}	
		
		return map;
	}
	
	/**
	 * 取组织JS
	 * @return LinkedHashMap
	 */	
	public String getOrgTree() {
		
		List<TreeInfo> list = new ArrayList();
		List<TsysOrg> orglist = this.userDAO.getOrgTreeList();
		TreeInfo  tree = new TreeInfo(0,-1,"Root","","orgmain");		 
		list.add(tree);
		
		for(TsysOrg org:orglist)
		{
			 int orgid =(int) org.getOrgid();			 
			 int parentid =(int) org.getParentOrg().getOrgid();
			 String orgname =org.getOrgname();
			 tree = new TreeInfo(orgid,parentid,orgname,"userList.action?orgid="+orgid,"usermain");
			 list.add(tree);
		}		
	
		return TreeInfo.createTreeInfo(list);
	}
	
	public String getOrgTreeWfl() {
		
		List<TreeInfo> list = new ArrayList();
		List<TsysOrg> orglist = this.userDAO.getOrgTreeList();

		TreeInfo  tree = new TreeInfo(0,-1,"ROOT","","orgmain");		 
		list.add(tree);
		
		for(TsysOrg org:orglist)
		{
			 int orgid =(int) org.getOrgid();			 
			 int parentid =(int) org.getParentOrg().getOrgid();
			 String orgname =org.getOrgname();
			 tree = new TreeInfo(orgid,parentid,"<INPUT type=checkbox value=jobname="+orgname+"&orgid="+orgid+" name=check />"+orgname,"","usermain");
			 list.add(tree);
//			 for(TsysJob job:org.getTsysJobs())
//			 {
//				 int jobid =(int) job.getJobid()*-1;
//				 String jobname = job.getJobname();
//				 TreeInfo  jobtree = new TreeInfo(jobid,orgid,"<INPUT type=checkbox value=jobname="+jobname+"&orgid="+orgid+"&jobid="+job.getJobid()+" name=check />"+"<img src=../img/jobIco.gif border=0 /> <b>"+jobname+"</b>","","usermain");
//				 list.add(jobtree);
//			 }
		}		
	
		return TreeInfo.createTreeInfo(list);
	}
	
	
	/**
	 * 编辑用户角色时的list
	 * @param menu
	 * @return
	 */
	public List getUserRoleList(final TsysUser user)
	{
		return this.userDAO.getUserRoleList(user);
	}
	
	/**
	 * 编辑用户角色
	 */
    @Transactional
    public void updateUserRoles(final TsysUser user,final Long[] check) throws ServiceException {
        try {
        	userDAO.updateUserRoles(user,check);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


	
}
