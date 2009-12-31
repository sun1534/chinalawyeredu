/**
 * RightTree.java
 */

package com.sxit.system.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.Constants;
import com.sxit.common.util.TreeNode;
import com.sxit.models.system.SysRight;

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

	private static List<String> ALL_PARENTCODE=new ArrayList<String>();
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
			SysRight right = (SysRight) rights.get(i);
			if (right.getGrade() > maxGrade)
				maxGrade = right.getGrade();
			// LOG.debug("code==" + menu.getRightcode() + ",==" +
			// menu.getRightname());
			// 上下级关系
			menuIdMap.put(right.getRightcode(), right.getParentcode());
			// 根据rightcode能得到menu
			rightMap.put(right.getRightcode(), right);

			rightNameMap.put(right.getRightcode(), right.getRightname());

		}
		node = new TreeNode(menuIdMap);

		for (int i = 0; i < rights.size(); i++) {
			SysRight right = (SysRight) rights.get(i);
			// 得到的数据已经包括了他自己了
			List<SysRight> parent = RightTree.getParentRights(right.getRightcode());
			// LOG.debug("===="+right.getRightcode());
			long compareid = 0L;

			for (int ii = 0; ii < parent.size(); ii++) {
				SysRight _right = parent.get(ii);
				// LOG.debug("========"+_right.getRightcode());
				compareid += _right.getOrderid() * tenn((maxGrade - _right.getGrade()) * 2);
			}

			right.setCompareid(compareid);
			// LOG.info(right.getRightcode() + "之compareid=" + compareid);
		}
		
		//这里最来判断是否有子节点,根据ALL_PARENTCODE里的数据来
		for (int i = 0; i < rights.size(); i++) {
			SysRight right = (SysRight) rights.get(i);
		    if(ALL_PARENTCODE.contains(right.getRightcode()))
		    	right.setHasChild(true);
		}
		
		allRights = rights;
		Collections.sort(allRights, new RightComparator());
		
		
		for (int i = 0; i < rights.size(); i++) {
			SysRight right = (SysRight) rights.get(i);
		   LOG.debug(right.getRightcode()+"=>"+right.getRightname()+"=>"+right.getCompareid()+"=>"+right.getHasChild());
		}
		
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
		// if (allRights.contains(rightCode)) {
		List parentcodes = node.getParentList(rightCode);
		
		List<SysRight> rights = new ArrayList<SysRight>();
		for (int i = parentcodes.size() - 1; i >= 0; i--) {
			
			String menuid = parentcodes.get(i).toString();
			//将所有找到的父级节点都放到一个队列里面去
			if(!ALL_PARENTCODE.contains(menuid))
			   ALL_PARENTCODE.add(menuid);
			if (!menuid.equals(Constants.ROOT_RIGHT)) {// 忽略掉最顶级的parentid=-1
				SysRight sysMenu = rightMap.get(parentcodes.get(i));
				rights.add(sysMenu);
			}
		}
		if (rightMap.get(rightCode) != null)
			rights.add(rightMap.get(rightCode));
		return rights;
		// }
		// return emptylist;
	}

	// private static List<SysRight> emptylist=new ArrayList<SysRight>();
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
	 * 得到rightcode的直接下级
	 * @param rightCode
	 * @return
	 */
	public static List<SysRight> getDirectChildRights(String rightCode,Set<String> myRights) {
		List childcodes = node.getDirectChildrenList(rightCode);
		List<SysRight> rights = new ArrayList<SysRight>();
		for (int i = 0; i < childcodes.size(); i++) {
			
			String menuid = childcodes.get(i).toString();
			if(myRights==null||(myRights!=null&&myRights.contains(menuid))){
			SysRight sysMenu = rightMap.get(menuid);
			rights.add(sysMenu);
			}
		}
	
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
		for (long i = 0; i < x; i++) {
			ten = ten * 10;
		}
		return ten;
	}
}