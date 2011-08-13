package com.sxit.info.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.Power;
import com.sxit.system.model.TsysUser;

import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看权限</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class SetViewAction extends AbstractAction {
	private TinfSet set;
    private int setid;
    private String[] power;
    private String powerlist ="";
    private long type;


	public SetViewAction() {
        rights="inf5,1";
       power = new String[]{"approve","prom","newinf","edit","del","view"};
	   set = new TinfSet();
	}

	public String go() throws HibernateException {
           nextpage="setList.action?pagenumber="+pagenumber;
           set=(TinfSet)getSession().get(TinfSet.class,Long.valueOf(setid));
           TsysUser user = (TsysUser)getSession().get(TsysUser.class, set.getUserid());
           for(String p:power){
        	   if(Power.hasPower(getSession(), user, p, set.getTinfType().getTypeid())){
        		   powerlist =powerlist + (String)com.sxit.info.util.CommonDatas.Power.get(Power.getKindNum(p)) +"  ";
        	   }
           }
           if(set==null){
             message="未找到此用户";
             return "message";
           }
           set("set", set);
           return SUCCESS;
	}
	
	public TinfSet getSet() {
		return set;
	}

	public void setSetid(int setid) {
		this.setid = setid;
	}

	public int getSetid() {
		return this.setid;
	}
	
	public String getPowerlist(){
		return this.powerlist;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

}
