/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.system.util.GroupTree;

/**
 * 
 * 显示部门树的列表
 * 暂时为上级部门能看到所有下级部门的,如果没有所属部门,则能看到整个公司的<br/>
 * 之后通过设置可见性表,来显示可见性情况
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysGroupTreeAction extends AbstractAction {
	private String queryname; //部门查询
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	/**
	 * 看是否通过弹出窗口打开,是弹出窗口打开的话,显示js，否则显示链接
	 */
	private String openwin;
	
	public void setOpenwin(String win){
		this.openwin=win;
	}
	public String getOpenwin(){
		return this.openwin;
	}
	
	private int groupid=Constants.ROOT_GROUP;
	public int getGroupid(){
		return this.groupid;
	}
	private String groupname;
	public String getGroupname(){
		return this.groupname;
	}
	
	//不设置权限
	public SysGroupTreeAction(){
//		this.rightCode="sysGroupList";
	}
	

	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		GroupTree tree=new GroupTree();
		SysGroup mygroup=getLoginUser().getSysGroup();
		

		if(mygroup!=null){
			groupid=mygroup.getGroupid();
			groupname=mygroup.getGroupname();
		}else{
			groupid=Constants.ROOT_GROUP;
			groupname=Constants.COMPANY_NAME;
		}
		if(!setupgroupid){
			this.upgroupid=groupid;
		}
		groupList=tree.getChildGroups(groupid);

		if(queryname!=null&&!"".equals(queryname.trim())){
			List<SysGroup> templist=new ArrayList<SysGroup>();
		
			for(SysGroup obj:groupList){
				if(obj.getGroupid()==1)
					templist.add(obj);
				if(obj.getGroupname().trim().indexOf(queryname.trim())>-1&&obj.getGroupid()!=1){
					templist.add(obj);
//					groupList.remove(obj);
				}
					
			}
			groupList=templist;
		}

		return SUCCESS;
	}

	
	private List<SysGroup> groupList;
	public List<SysGroup> getGroupList(){
		return this.groupList;
	}
	
	private int upgroupid;

	private boolean setupgroupid=false;
	
	/**
	 * @return the upgroupid
	 */
	public int getUpgroupid() {
		return upgroupid;
	}
	/**
	 * @param upgroupid the upgroupid to set
	 */
	public void setUpgroupid(int upgroupid) {
		setupgroupid=true;
		this.upgroupid = upgroupid;
	}
	

}