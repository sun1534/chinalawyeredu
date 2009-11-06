/**
 * 
 */
package com.changpeng.core.regist.action.ajax;

import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.LocationTree;

/**
 * 查询地区
 * 
 * @author 肖云亮 2009-5-16 下午05:22:20
 */
public class LocationListAction extends AbstractAction {
	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(LocationListAction.class);
	private int locationid;
	private List locationList;
	private int optype;// 0获取省份下城市列表；1，获取城市下区域列表;3,获取某省份第一个城市的区域列表

	public LocationListAction() {
		this.rightCode = "PUBLIC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		log.debug("locationid==" + locationid);
		locationList = LocationTree.getDirectionChildrenList(locationid);
		log.debug("locationList==" + locationList.size());
		if (optype == 0) {
			return "province";
		} else if (optype == 1) {
			return "province";

		} else if (optype == 2) {
			return "areas";
		} else {
			return ERROR;
		}
	}

	public List getLocationList() {
		return locationList;
	}

	public void setLocationList(List locationList) {
		this.locationList = locationList;
	}

	public void setOptype(int optype) {
		this.optype = optype;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

}
