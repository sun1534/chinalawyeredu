/**
 * JSONExample.java
 */
package com.changpeng.system.action.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysRight;
import com.changpeng.system.util.RightTree;

/**
 * 
 * ajax形式的数据,不必进行权限处理等等
 * <br/>
 * 根据rightcode得到下一级的数据
 * 
 * @author 华锋
 * 2008-3-11 下午11:10:44
 *
 */
public class GetRightChildAction extends AbstractListAction {

	private Map  rightMap=new HashMap();
	
	public Map getRightMap(){
		return this.rightMap;
	}
	
	@Override
	public String go()throws Exception{
		
		List<SysRight> rights=RightTree.getChildRights(rightCode);
		for(int i=0;i<rights.size();i++){
			SysRight right=rights.get(i);
			if(right.getGrade()==level)
    			rightMap.put(right.getRightcode(),right.getRightname());
		}
		return SUCCESS;
	}
	
	private String rightCode;
	public void setRightCode(String code){
		this.rightCode=code;
	}
	private int level;
	public void setLevel(int level){
	   this.level=level;	
	}
}