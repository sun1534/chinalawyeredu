package com.changpeng.diaocha.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.*;

import java.util.*;

import org.hibernate.criterion.*;
public class ReplyListAction extends AbstractListAction{
	private int diaochaid;
	
	private PaginationSupport pageTime;
	
	private PaginationSupport pageIp;
	
	//private List replylist;
	public void setDiaochaid(int diaochaid){
		this.diaochaid=diaochaid;
	}
	public int getDiaochaid() {
		return diaochaid;
	}
	public ReplyListAction(){
//		this.rightCode="diaocha";
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochareply.class);
		detachedCriteria.setProjection(Projections.groupProperty("replyuser"));
		//detachedCriteria.setProjection(Projections.groupProperty("replytime"));
		//detachedCriteria.setProjection(Projections.distinct(Projections.property("replyuser")));
		detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		//这里获取的仅仅是 replyuser的列表而已
		
		this.page=service.findPageByCriteriaDistinct(detachedCriteria, pageSize, pageNo);
		
		pageTime=
		service.findPageByCriteriaDistinct(DetachedCriteria.forClass(Diaochareply.class)			
				.setProjection( Projections.alias( Projections.groupProperty("replytime"), "replytime" ) )
				, pageSize, pageNo);
		pageIp=
			service.findPageByCriteriaDistinct(DetachedCriteria.forClass(Diaochareply.class)			
					.setProjection( Projections.alias( Projections.groupProperty("ip"), "ip" ) )
					, pageSize, pageNo);
		
		return SUCCESS;
	}
	public PaginationSupport getPageTime() {
		return pageTime;
	}
	/**
	 * @return the pageIp
	 */
	public PaginationSupport getPageIp() {
		return pageIp;
	}

	/*public List getReplylist() {
		return replylist;
	}*/

}
