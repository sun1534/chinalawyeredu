/**
 * 
 */
package com.sxit.info.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.info.model.TinfType;;


/**
 * @author sg
 * @TODO 显示信息列表 
 * @createtime 2008-8-27
 * @version v1.0
 * 参数: 1,新闻列表 2,公告列表 3,制度列表 4,文件列表
 */
public class InfoViewListAction extends AbstractListAction {

	private long type ;
	private List inf;
	private List typeList;
	
	public InfoViewListAction(){
		rights = "inf1,1";
		type=0;
	}

	@Override
	protected String go() throws JDBCException, HibernateException {

		Criteria criteria = getSession().createCriteria(TinfType.class);
		 criteria.addOrder(Order.asc("typeid"));
		 typeList=criteria.list();
		if(type==0&&typeList.size()>0){
			type=((TinfType)typeList.get(0)).getTypeid();
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
        queryName =
            "from TinfInfo as inf where inf.statusid=0 and inf.tinfType.typeid="+type+"  order by inf.infoid desc";
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
		return typeList;
	}

}
