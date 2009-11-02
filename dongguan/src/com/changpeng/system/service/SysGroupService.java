/**
 * SysGroupService.java
 */

package com.changpeng.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.TreeNode;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.dao.SysGroupDAO;

/**
 * 
 * @author 华锋 2008-2-29 上午10:21:33
 * 
 */
public class SysGroupService {

	private SysGroupDAO sysGroupDAO;

	public void setSysGroupDAO(SysGroupDAO sysGroupDAO) {
		this.sysGroupDAO = sysGroupDAO;

	}

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public SysGroup get(int groupid) throws ServiceException {
		try {
			return (SysGroup) sysGroupDAO.get(SysGroup.class, groupid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List find() throws ServiceException {
		try {
			return sysGroupDAO.findAll(SysGroup.class);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 0删除成功 -1下面有人员 -2有下属部门,不能删除
	 * 
	 * @param groupid
	 * @return
	 * @throws ServiceException
	 */
	public int deleteGroup(final int groupid) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					SysGroup group = (SysGroup) sysGroupDAO.get(SysGroup.class, new Integer(groupid));
					Set<SysUser> users = group.getSysUsers();
					if (users != null && users.size() != 0) {
						return -1;// 下面有人员呢,不能删除
					}
					List list = sysGroupDAO.getChildGroup(groupid);
					if (list != null && list.size() != 0)
						return -2;// 下面要是有下级部门的话,也是不能删除的
					sysGroupDAO.delete(group);
					return 0;

					
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 逻辑删除,将delflag设置为true
	 * 
	 * @param groupid
	 * @return
	 * @throws ServiceException
	 */
	public int deleteGroupLogic(int groupid) throws ServiceException {

		try {
			SysGroup group = (SysGroup) sysGroupDAO.get(SysGroup.class, new Integer(groupid));
			Set<SysUser> users = group.getSysUsers();
			if (users != null && users.size() != 0) {
				return -1;// 下面有人员呢,不能删除
			}
			List list = getChildGroup(groupid);
			if (list != null && list.size() != 0)
				return -2;// 下面要是有下级部门的话,也是不能删除的
			sysGroupDAO.deleteLogic(groupid);
			return 0;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 得到组和下面所有组，树形结构显示 设置所在公司为顶层group，groupid设置为0
	 * 
	 * @param groupid
	 * @return
	 * @throws ServiceException
	 */
	public List getChildGroupTree(int groupid) throws ServiceException {
		try {
			// 先得到所有的groups，之后利用treenode来找出所需的数据
			List allgroups = sysGroupDAO.findAll(SysGroup.class);
			Map groupids = new HashMap();
			Map<Object, Object> trees = new HashMap<Object, Object>();
			for (int i = 0; i < allgroups.size(); i++) {
				SysGroup group = (SysGroup) allgroups.get(i);
				trees.put(group.getGroupid(), group.getParentid());
				groupids.put(group.getGroupid(), group);
			}
			TreeNode node = new TreeNode(trees);
			List childList = new ArrayList();
			// 得到所有下面的组的groupid
			List list = node.getChildrenList(groupid);
			for (int i = 0; i < list.size(); i++) {
				childList.add(groupids.get(list.get(i)));
			}
			// 根据groupid找到所有的对象
			return childList;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	//
	// /**
	// * 得到多个部门的树列表
	// *
	// * @param groupid
	// * @return
	// * @throws ServiceException
	// */
	// public List[] getChildGroupTree(int[] groupid) throws ServiceException {
	// try {
	// // 先得到所有的groups，之后利用treenode来找出所需的数据
	// List allgroups = sysGroupDAO.findAll(SysGroup.class);
	// Map groupids = new HashMap();
	// Map<Object, Object> trees = new HashMap<Object, Object>();
	// for (int i = 0; i < allgroups.size(); i++) {
	// SysGroup group = (SysGroup) allgroups.get(i);
	// trees.put(group.getGroupid(), group.getParentid());
	// groupids.put(group.getGroupid(), group);
	// }
	// TreeNode node = new TreeNode(trees);
	// List[] childList = new ArrayList[groupid.length];
	// // 得到所有下面的组的groupid
	// for (int k = 0; k < groupid.length; k++) {
	// childList[k] = new ArrayList();
	// List list = node.getChildrenList(groupid);
	// for (int i = 0; i < list.size(); i++) {
	// childList[k].add(groupids.get(list.get(i)));
	// }
	// }
	// // 根据groupid找到所有的对象
	// return childList;
	// }
	// catch (Exception e) {
	// throw new ServiceException(e);
	// }
	// }

	/**
	 * 
	 * 
	 * @param groupid
	 * @return
	 * @throws ServiceException
	 */
	public List getChildGroup(int parentid) throws ServiceException {
		try {
//			List list = sysGroupDAO.find("from com.changpeng.models.system.SysGroup group where group.parentid=?", parentid);
//			return list;
			return sysGroupDAO.getChildGroup(parentid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public int addGroup(SysGroup group) throws ServiceException {
		try {
			group.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			if(group.getGroupid()==0)
				group.setGroupid((int)(System.currentTimeMillis() / 10000));
			sysGroupDAO.save(group);
			return group.getGroupid();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// public int addGroup(int parentid, String groupname, String groupenname, short grouplevel, String phone, String fax, String createuser,
	// String contacter, String comments) throws ServiceException {
	// try {
	// SysGroup group = new SysGroup();
	// group.setComments(comments);
	// group.setContacter(contacter);
	// group.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	// group.setCreateuser(createuser);
	// group.setDelflag(false);
	// group.setFax(fax);
	// group.setGroupenname(groupenname);
	// group.setGrouplevel(grouplevel);
	// group.setGroupname(groupname);
	// group.setParentid(parentid);
	// group.setPhone(phone);
	// sysGroupDAO.save(group);
	// return group.getGroupid();
	// }
	// catch (Exception e) {
	// throw new ServiceException(e);
	// }
	// }

	public void updateGroup(SysGroup group) throws ServiceException {
		try {
			sysGroupDAO.update(group);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// /**
	// * 得到userid和loginname的对应关系表
	// * @return
	// * @throws ServiceException
	// */
	// public Map getAllGroupsMap()throws ServiceException{
	// Map map=new HashMap();
	// List list=sysGroupDAO.findAll(SysGroup.class);
	// for(int i=0;list!=null&&i<list.size();i++){
	// SysGroup group=(SysGroup)list.get(i);
	// map.put(group.getGroupid(),group.getGroupname());
	// }
	// return map;
	// }
}