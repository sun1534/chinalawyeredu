/**
 * GroupTree.java
 */

package com.changpeng.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.common.context.Globals;
import com.changpeng.common.util.TreeNode;
import com.changpeng.models.system.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * @author 华锋 2008-3-9 下午08:41:18
 * 
 * 权限树的设置,treenode只设置一次
 * 
 */
public class GroupTree {
	private static Log LOG = LogFactory.getLog(GroupTree.class);
	/**
	 * 权限树节点
	 */
	private TreeNode node;

	private List allGroups;

	private int maxLevel;
	/**
	 * 存储groupid和权限对象
	 */
	public Map<Integer, SysGroup> groupMap = new HashMap<Integer, SysGroup>();
	
	public Map<Integer, SysGroup> getGroupMap(){
		return this.groupMap;
	}
	
	/**
	 * 存储groupid和groupname
	 */
	public Map<Integer, String> groupNameMap = new HashMap<Integer, String>();
	public Map<Integer, String> getGroupNameMap(){
		return this.groupNameMap;
	}
	
	/**
	 * 存储权限id和上级权限id
	 */
	private Map<Object, Object> groupIdMap = new HashMap<Object, Object>();

	/**
	 * 先根据得到的所有权限数据,初始化权限树
	 * 
	 * @param groups
	 */
	public GroupTree() {
		try {
			Globals globals = new Globals();
			SysGroupService service = (SysGroupService) globals.getBean("sysGroupService");
			allGroups = service.find();
			// 此处仅仅获得最大的maxgrade;
			for (int i = 0; i < allGroups.size(); i++) {
				SysGroup group = (SysGroup) allGroups.get(i);
				if (group.getGrouplevel() > maxLevel)
					maxLevel = group.getGrouplevel();
				
				//剔除已离职律师库
				if(group.getGroupid()!=-1){
					// 上下级关系
					groupIdMap.put(group.getGroupid(), group.getParentid());
					// 根据groupcode能得到menu
					groupMap.put(group.getGroupid(), group);
				}
				

				groupNameMap.put(group.getGroupid(), group.getGroupname());
			}
			node = new TreeNode(groupIdMap);
		}
		catch (Exception e) {
			LOG.error("部门树初始化失败:"+e);
		}
	}

	/**
	 * 得到已经排好序了的数据
	 * 
	 * @return
	 */
	public List getGroupList() {
		return allGroups;
	}

	/**
	 * 根据groupid得到其所有的上级,共享节点,包括了他自己
	 * 
	 * @param groupCode
	 * @return
	 */
	public List<SysGroup> getParentGroups(int groupid) {
		List parentcodes = node.getParentList(groupid);
		List<SysGroup> groups = new ArrayList<SysGroup>();
		for (int i = parentcodes.size() - 1; i >= 0; i--) {
			int menuid = Integer.parseInt(parentcodes.get(i).toString());
			if (menuid != Constants.ROOT_GROUP) {// 忽略掉最顶级的parentid=-1
				SysGroup sysGroup = groupMap.get(Integer.parseInt(parentcodes.get(i).toString()));
				groups.add(sysGroup);
			}
		}
		groups.add(groupMap.get(groupid));
		return groups;
	}

	/**
	 * 根据groupcode得到所有的下级,共享节点
	 * 
	 * @param groupCode
	 * @return
	 */
	public List<SysGroup> getChildGroups(int groupid) {
		List childcodes = node.getChildrenList(groupid);
		List<SysGroup> groups = new ArrayList<SysGroup>();
		for (int i = 0; i < childcodes.size(); i++) {
			int menuid = Integer.parseInt(childcodes.get(i).toString());
			SysGroup sysMenu = groupMap.get(menuid);
			groups.add(sysMenu);
		}
		if (groupid != Constants.ROOT_GROUP)
			groups.add(groupMap.get(groupid));// 将自己也加入进去
		return groups;
	}
	/**
	 * 获取groupid的所有下级部门id，包括自己
	 * @param groupid
	 * @return
	 */
	public List getChildGroupIds(int groupid){
		List children = node.getChildrenList(groupid);
        if(groupid!=Constants.ROOT_GROUP)
        	children.add(groupid);
		return children;
	}
}