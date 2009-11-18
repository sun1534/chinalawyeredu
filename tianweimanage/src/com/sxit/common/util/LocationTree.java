/**
 * RightTree.java
 */

package com.sxit.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.models.datas.GlobalArea;

/**
 * @author 华锋 2008-3-9 下午08:41:18
 * 
 * 权限树的设置,treenode只设置一次
 * 
 */
public class LocationTree {
	private static Log LOG = LogFactory.getLog(LocationTree.class);

	private static int ROOT_RIGHT = 0;

	/**
	 * 权限树节点
	 */
//	private static TreeNode node;

	private static List allLocations;
	
	private static List NULL_LIST=new ArrayList();
	/**
	 * 存储地区id和对象
	 */
	public static Map<Integer, GlobalArea> locationMap = new LinkedHashMap<Integer, GlobalArea>();
	/**
	 * id和名称的对应关系
	 */
	public static Map<Integer, String> locationNameMap = new LinkedHashMap<Integer, String>();
	/**
	 * 存储地区id和上级权限id
	 */
	public static Map<Object, Object> locationIdMap = new LinkedHashMap<Object, Object>();

	/**
	 * 所有的省列表
	 */
	public static List<GlobalArea> ProvinceList = new ArrayList<GlobalArea>();
	/**
	 * 
	 * 省和下面的市列表
	 */
	public static Map<Integer, List<GlobalArea>> ProvinceCity = new LinkedHashMap<Integer, List<GlobalArea>>();
	/**
	 * 市下面和对应的区县列表
	 */
	public static Map<Integer, List<GlobalArea>> CityDistrict = new LinkedHashMap<Integer, List<GlobalArea>>();

	/**
	 * 先根据得到的所有权限数据,初始化权限树
	 * 
	 * @param locations
	 */
	public static void setLocationList(List locations) {
		// if (node == null || allLocations == null) {
		// 此处仅仅获得最大的maxgrade;
		locationMap.clear();
		locationNameMap.clear();
		locationIdMap.clear();

		for (int i = 0; i < locations.size(); i++) {
			GlobalArea location = (GlobalArea) locations.get(i);
			// LOG.debug("code==" + menu.getRightcode() + ",==" +
			// menu.getRightname());
			// 上下级关系
			locationIdMap.put(location.getId(), location.getParent());
			// 根据rightcode能得到menu
			locationMap.put(location.getId(), location);

			locationNameMap.put(location.getId(), location.getAreaName());
			if (location.getParent() == 0)
				ProvinceList.add(location);
			else if (location.getAreaLevel() == 1) {// 市信息
				int parentid = location.getParent();
				List<GlobalArea> areas = null;
				if (ProvinceCity.containsKey(parentid)) {
					areas = ProvinceCity.get(parentid);
				} else {
					areas = new ArrayList<GlobalArea>();
					ProvinceCity.put(parentid, areas);
				}
				areas.add(location);
			} else if (location.getAreaLevel() == 2) {// 区县信息

				int parentid = location.getParent();
				List<GlobalArea> areas = null;
				if (CityDistrict.containsKey(parentid)) {
					areas = CityDistrict.get(parentid);
				} else {
					areas = new ArrayList<GlobalArea>();
					CityDistrict.put(parentid, areas);
				}
				areas.add(location);
			}

		}
//		node = new TreeNode(locationIdMap);
		allLocations = locations;
		LOG.info("系统启动获得地区信息成功...");
	}

	/**
	 * 得到已经排好序了的数据
	 * 
	 * @return
	 */
	public static List getAllList() {
		return allLocations;
	}

	/**
	 * 根据locationid得到其所有的上级,共享节点,包括了他自己
	 * 
	 * @param rightCode
	 * @return
	 */
	// public static List<GlobalArea> getParentLocations(int locationid) {
	// List parentcodes = node.getParentList(locationid);
	// List<GlobalArea> locations = new ArrayList<GlobalArea>();
	// for (int i = parentcodes.size() - 1; i >= 0; i--) {
	// String menuid = parentcodes.get(i).toString();
	// if (!menuid.equals(ROOT_RIGHT)) {// 忽略掉最顶级的parentid=-1
	// GlobalArea sysMenu = locationMap.get(parentcodes.get(i));
	// locations.add(sysMenu);
	// }
	// }
	// locations.add(locationMap.get(locationid));
	// return locations;
	// }
	/**
	 * 根据locationid得到其所有的上级,共享节点,包括了他自己
	 * 
	 * @param rightCode
	 * @return
	 */
	// public static List getParentLocationIds(int locationid) {
	// List parentcodes = node.getParentList(locationid);
	//
	// parentcodes.add(locationid);
	// return parentcodes;
	// }
	/**
	 * 根据rightcode得到所有的下级,共享节点
	 * 
	 * @param rightCode
	 * @return
	 */
	// public static List<GlobalArea> getChildLocations(int locationid) {
	// List childcodes = node.getChildrenList(locationid);
	// List<GlobalArea> locations = new ArrayList<GlobalArea>();
	// for (int i = 0; i < childcodes.size(); i++) {
	// String menuid = childcodes.get(i).toString();
	// GlobalArea sysMenu = locationMap.get(Integer.valueOf(menuid));
	// locations.add(sysMenu);
	// }
	// if (locationid != 0)
	// locations.add(locationMap.get(locationid));// 将自己也加入进去
	// return locations;
	// }
	/**
	 * 只返回ID
	 * 
	 * @param locationid
	 * @return
	 */
	// public static List<Integer> getChildLocationIds(int locationid) {
	// List childcodes = node.getChildrenList(locationid);
	// // List<GlobalArea> locations = new ArrayList<GlobalArea>();
	// // for (int i = 0; i < childcodes.size(); i++) {
	// // String menuid = childcodes.get(i).toString();
	// // GlobalArea sysMenu = locationMap.get(menuid);
	// // locations.add(sysMenu);
	// // }
	// if (locationid != 0)
	// childcodes.add(locationid);// 将自己也加入进去
	// return childcodes;
	// }
	/**
	 * 得到直接下级
	 * 
	 * @param locationid
	 * @return
	 */
	// public static Map getDirectionChildren(int locationid) {
	// Map areas = new HashMap();
	// Iterator keys = locationIdMap.keySet().iterator();
	// while (keys.hasNext()) {
	// Object key = keys.next();
	// if (Integer.parseInt(locationIdMap.get(key).toString()) == locationid) {
	// areas.put(key, locationNameMap.get(key));
	// }
	// }
	// return areas;
	// }
	/**
	 * 
	 * @param locationid
	 * @return
	 */
	public static List getDirectionChildrenList(int locationid) {
		List list=null;
		if (locationid == 0) // 省的话
			return ProvinceList;
	
		GlobalArea area = locationMap.get(locationid);
		if(area==null)
			return NULL_LIST;
		if (area.getAreaLevel() == 0) { // 这个是得到省下面的
			List<GlobalArea> cities = ProvinceCity.get(locationid);
			list= cities;
		} else if (area.getAreaLevel() == 1) {// 这个是得到市下面的
			List<GlobalArea> districts = CityDistrict.get(locationid);
			list=districts;
		}
		if(list==null)
			list=NULL_LIST;
		return list;
		// List areas = new ArrayList();
		// Iterator keys = locationIdMap.keySet().iterator();
		// while (keys.hasNext()) {
		// Object key = keys.next();
		// if (Integer.parseInt(locationIdMap.get(key).toString()) ==
		// locationid) {
		// areas.add(locationMap.get(key));
		// // areas.put(key, locationNameMap.get(key));
		// }
		// }
		// return areas;
	}

	public static GlobalArea getAreas(int locationid) {
		return locationMap.get(locationid);
	}

}