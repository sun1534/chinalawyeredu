/**
 * 
 */
package com.sxit.system.action.ajax;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.datas.GlobalArea;

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
	private int optype;// 0获取省份下城市列表；1，获取城市下区域列表;3,获取某省份第一个城市的区域列表

	public LocationListAction() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
	
		List<GlobalArea> locationList =null;
		
		if(optype==1){
			locationList=com.sxit.common.util.LocationTree.ProvinceCity.get(locationid);
		}else{
			locationList=com.sxit.common.util.LocationTree.CityDistrict.get(locationid);
		}
		
		for(int i=0;i<locationList.size();i++){
			childrens.put(locationList.get(i).getId(), locationList.get(i).getAreaName());
		}
		
		
		return SUCCESS;
	}

    private Map<Integer,String> childrens=new LinkedHashMap<Integer,String> ();
    public Map<Integer,String> getChildrens(){
    	return this.childrens;
    }


	public void setOptype(int optype) {
		this.optype = optype;
	}

//	public int getLocationid() {
//		return locationid;
//	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	
	private String time;

	public void setTime(String time) {
		this.time = time;
	}

}
