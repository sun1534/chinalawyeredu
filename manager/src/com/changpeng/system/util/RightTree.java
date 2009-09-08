/**
 * RightTree.java
 */

package com.changpeng.system.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.common.util.TreeNode;
import com.changpeng.models.SysRight;

/**
 * @author 华锋 2008-3-9 下午08:41:18
 * 
 * 权限树的设置,treenode只设置一次
 * 
 */
public class RightTree {
	private static Log LOG = LogFactory.getLog(RightTree.class);
	/**
	 * 权限树节点
	 */
	private static TreeNode node;

	private static List allRights;

	private static int maxGrade;
	/**
	 * 存储权限id和权限对象
	 */
	public static Map<String, SysRight> rightMap = new HashMap<String, SysRight>();
	/**
	 * rightcode和名称的对应关系
	 */
	public static Map<String, String> rightNameMap = new HashMap<String, String>();
	/**
	 * 存储权限id和上级权限id
	 */
	private static Map<Object, Object> menuIdMap = new HashMap<Object, Object>();

	/**
	 * 先根据得到的所有权限数据,初始化权限树
	 * 
	 * @param rights
	 */
	public static void setRightList(List rights) {
		// if (node == null || allRights == null) {
		// 此处仅仅获得最大的maxgrade;
		rightMap.clear();
		rightNameMap.clear();
		menuIdMap.clear();

		for (int i = 0; i < rights.size(); i++) {
			SysRight menu = (SysRight) rights.get(i);
			if (menu.getGrade() > maxGrade)
				maxGrade = menu.getGrade();
			// LOG.debug("code==" + menu.getRightcode() + ",==" + menu.getRightname());
			// 上下级关系
			menuIdMap.put(menu.getRightcode(), menu.getParentcode());
			// 根据rightcode能得到menu
			rightMap.put(menu.getRightcode(), menu);

			rightNameMap.put(menu.getRightcode(), menu.getRightname());

		}
		node = new TreeNode(menuIdMap);

		for (int i = 0; i < rights.size(); i++) {
			SysRight right = (SysRight) rights.get(i);
			// 得到的数据已经包括了他自己了
			List<SysRight> parent = RightTree.getParentRights(right.getRightcode());
			// LOG.debug("===="+right.getRightcode());
			long compareid = 0;
			for (int ii = 0; ii < parent.size(); ii++) {
				SysRight _right = parent.get(ii);
				compareid += _right.getOrderid() * tenn((maxGrade - _right.getGrade()) * 2);
			}

			right.setCompareid(compareid);
			// LOG.debug(right.getRightcode() + "之compareid=" + compareid);
		}
		allRights = rights;
		Collections.sort(allRights, new RightComparator<SysRight>());
		// for(int i=0;i<allRights.size();i++){
		//				
		// SysRight right = (SysRight) allRights.get(i);
		// LOG.debug("compareid="+right.getCompareid()+"=="+right.getRightcode());
		//				
		// }
		LOG.info("系统启动初始化权限树成功...");
		// }
	}

	/**
	 * 得到已经排好序了的数据
	 * 
	 * @return
	 */
	public static List getRightList() {
		return allRights;
	}

	public static int getMaxGrade() {
		return maxGrade;
	}

	/**
	 * 根据rightcode得到其所有的上级,共享节点,包括了他自己
	 * 
	 * @param rightCode
	 * @return
	 */
	public static List<SysRight> getParentRights(String rightCode) {
		List parentcodes = node.getParentList(rightCode);
		List<SysRight> rights = new ArrayList<SysRight>();
		for (int i = parentcodes.size() - 1; i >= 0; i--) {
			String menuid = parentcodes.get(i).toString();
			if (!menuid.equals(Constants.ROOT_RIGHT)) {// 忽略掉最顶级的parentid=-1
				SysRight sysMenu = rightMap.get(parentcodes.get(i));
				rights.add(sysMenu);
			}
		}
		rights.add(rightMap.get(rightCode));
		return rights;
	}

	/**
	 * 根据rightcode得到所有的下级,共享节点
	 * 
	 * @param rightCode
	 * @return
	 */
	public static List<SysRight> getChildRights(String rightCode) {
		List childcodes = node.getChildrenList(rightCode);
		List<SysRight> rights = new ArrayList<SysRight>();
		for (int i = 0; i < childcodes.size(); i++) {
			String menuid = childcodes.get(i).toString();
			SysRight sysMenu = rightMap.get(menuid);
			rights.add(sysMenu);
		}
		if (!rightCode.equals(Constants.ROOT_RIGHT))
			rights.add(rightMap.get(rightCode));// 将自己也加入进去
		return rights;
	}

	/**
	 * 计算10的n次方
	 * 
	 * @param x
	 * @return
	 */
	private static long tenn(int x) {
		long ten = 1;
		for (int i = 0; i < x; i++) {
			ten = ten * 10;
		}
		return ten;
	}
}