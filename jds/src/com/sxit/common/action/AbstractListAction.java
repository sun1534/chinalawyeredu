package com.sxit.common.action;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sxit.common.BasicService;
import com.sxit.common.PageSupport;

/**
 *
 * <p>功能： 列表类Action的基类</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-25</p>
 * @版本： V1.0
 * @修改：
 */


public abstract class AbstractListAction extends AbstractAction
	{
//分页属性
        protected int maxperpage = 10;
        protected int pagenumber;
        protected int recordsize;
        protected int pagesize;
        
        protected String pagestring;
//分页
        
        protected BasicService basicService;
        
        public AbstractListAction(){
        	WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        	basicService=(BasicService) wac.getBean("basicService");
        	
        }
        
        public int getMaxperpage() {
          return maxperpage;
        }

        public void setMaxperpage(int maxperpage) {
          this.maxperpage = maxperpage;
        }

        public int getPagenumber() {
          return pagenumber;
        }

        public int getRecordsize() {
          return recordsize;
        }

        public int getPagesize() {
          return pagesize;
        }

        public void setPagenumber(int pagenumber) {
          this.pagenumber = pagenumber;
        }
        
        public String getPagestring()
		{	
			initPagestring();
			return pagestring;
		}
    
        public String getLawcasePagestring()
		{	
        	initLawcasePagestring();
			return pagestring;
		}
        
        private void initLawcasePagestring()
		{
        	pagestring="";
			if(recordsize>0){
//				pagestring = "<TR bgcolor=\"#FEF7E9\" class=\"pt9-18\"><TD colSpan=15 align=\"middle\"> ";
				//pagestring+="<div align=\"left\" bgcolor=\"#FEF7E9\" style='float:left'><span class=\"pt9-18\">";
				//pagestring+="每页显示<select name='maxperpage' onchange=\"changePagesize(this.value)\">";
				//pagestring+="<option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option>";
				//pagestring+="</select>条</div>";
				pagestring+="<div align=\"right\" bgcolor=\"#FEF7E9\"><span class=\"pt9-18\">";
				pagestring+="共<font color=red><b>"+recordsize+"</b></font>记录";
				//pagestring+="第<font color=red><b>"+(pagenumber+1)+"</b></font>页/";
				pagestring+="第<input type='text' size='3' id='pages' value='"+(pagenumber+1)+"' onkeypress=\"if(event.keyCode==13) page(this.value-1);\">页<a href=\"javascript:page(document.getElementById('pages').value-1)\"><font color=red><b>GO</b></a>";
				
				pagestring+="共<font color=red>"+pagesize+"</b></font>页</span>";
				if(pagesize==1){
					pagestring+="<font color=\"#bbbbbb\">首页 前页 后页 末页</font>";
				}else{
					if(pagenumber+1==1){
						pagestring+="<font color=\"#bbbbbb\">首页 前页</font>";
					}else{
						pagestring+="<a href=\"javascript:page(0)\">首页</a>";
						pagestring+="<a href=\"javascript:page("+(pagenumber-1)+")\">前页</a>";
					}if(pagenumber+1==pagesize){
						pagestring+="<font color=\"#bbbbbb\"> 后页 末页</font>";
					}else{
						pagestring+="<a href=\"javascript:page("+(pagenumber+1)+")\"> 后页</a>";
						pagestring+="<a href=\"javascript:page("+(pagesize-1)+")\"> 末页</a>";
					}
				}
					
//				pagestring+="</TD></TR>";
			}                                  
		}
        
		private void initPagestring()
		{
			pagestring="";
			if(recordsize>0){
				pagestring = "<TR bgcolor=\"#FEF7E9\" class=\"pt9-18\"><TD colSpan=15 align=\"middle\"> ";
				//pagestring+="<div align=\"left\" bgcolor=\"#FEF7E9\" style='float:left'><span class=\"pt9-18\">";
				//pagestring+="每页显示<select name='maxperpage' onchange=\"changePagesize(this.value)\">";
				//pagestring+="<option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option>";
				//pagestring+="</select>条</div>";
				pagestring+="<div align=\"right\" bgcolor=\"#FEF7E9\"><span class=\"pt9-18\">";
				pagestring+="共<font color=red><b>"+recordsize+"</b></font>记录";
				//pagestring+="第<font color=red><b>"+(pagenumber+1)+"</b></font>页/";
				pagestring+="第<input type='text' size='3' id='pages' value='"+(pagenumber+1)+"' onkeypress=\"if(event.keyCode==13) page(this.value-1);\">页<a href=\"javascript:page(document.getElementById('pages').value-1)\"><font color=red><b>GO</b></a>";
				
				pagestring+="共<font color=red>"+pagesize+"</b></font>页</span>";
				if(pagesize==1){
					pagestring+="<font color=\"#bbbbbb\">首页 前页 后页 末页</font>";
				}else{
					if(pagenumber+1==1){
						pagestring+="<font color=\"#bbbbbb\">首页 前页</font>";
					}else{
						pagestring+="<a href=\"javascript:page(0)\">首页</a>";
						pagestring+="<a href=\"javascript:page("+(pagenumber-1)+")\">前页</a>";
					}if(pagenumber+1==pagesize){
						pagestring+="<font color=\"#bbbbbb\"> 后页 末页</font>";
					}else{
						pagestring+="<a href=\"javascript:page("+(pagenumber+1)+")\"> 后页</a>";
						pagestring+="<a href=\"javascript:page("+(pagesize-1)+")\"> 末页</a>";
					}
				}
					
				pagestring+="</TD></TR>";
			}                                  
		}
		
		public List page(Criteria criteria){
			
			PageSupport ps=basicService.findPageOnPageNo(criteria, maxperpage, pagenumber);
			
			recordsize=ps.getRecordCount();
//			recordsize = criteria.list().size();	

			pagesize=ps.getPageCount();
			  pagenumber= ps.getPageNo();
			return ps.getItems();
//            pagesize = (recordsize - 1) / maxperpage + 1;
          
//            return  criteria.setMaxResults(maxperpage).setFirstResult(maxperpage * pagenumber).setCacheable(true).list();
		}
		
		public  Object getBean(String name) {

			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			return wac.getBean(name);
		}
}
