/**
 * 
 */
package com.sxit.info.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.info.model.TinfSet;
import com.sxit.info.util.Power;

import com.sxit.system.model.TsysUser;

/**
 * @author sg
 * @TODO 显示信息列表 
 * @createtime 2008-8-27
 * @version v1.0
 * 参数: 1,新闻列表 2,公告列表 3,制度列表 4,文件列表
 */
public class InfoPromListAction extends AbstractListAction {

	private long type;
	private List typeList;
	private List inf;
	
	public InfoPromListAction(){
		 rights="inf4,1";
	}

	@Override
	protected String go() throws JDBCException, HibernateException {


		if(infoList() == null)
		{
			message="你没有相应的发布权限,请与管理员联系!";
			return "message";
		}
		inf = infoList()
		.setMaxResults(maxperpage)
	    .setFirstResult(maxperpage * pagenumber)
	    .setCacheable(true)
	    .list();
		
		return SUCCESS;
		
	}
	
	private Query infoList(){
        String queryName;
        curuser = (TsysUser)get("curuser");
        typeList = Power.whichPower(getSession(), curuser, "prom");
        if(typeList.size()==0)
        {
        	return null;
        }
        TinfSet infset = (TinfSet)typeList.get(0);
        if(type ==0)
        {
        	type=infset.getTinfType().getTypeid();
        }
        
        LOG.debug("--------------type="+type);
        
        queryName =
            "from TinfInfo as inf where (inf.statusid=3 or inf.statusid=0 or inf.statusid=-1) and inf.tinfType.typeid="+type+"  order by inf.infoid desc";
        Query query = getSession().createQuery(queryName);
        
        recordsize = query.list().size();
        
        pagesize = (recordsize - 1) / maxperpage + 1;
        pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
        return query;
	}
	
	public void setType(long type){
		this.type = type;
	}
	
	public long getType(){
		return this.type;
	}
	
	public List getInf(){
		return this.inf;
	}
	
	public List getTypeList(){
		return this.typeList;
	}

}
