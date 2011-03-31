/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.List;

/**
 * 
 * @author agu
 *
 */
public class PageSupport {

	private int pageSize = 10; //每页显示记录�?,默认�?10
	private int pageNo=1;        //当前的页�?
	private int pageCount = 0; //总页�?
	private int recordCount;   //总记录数
	private List items;        //当前页的存储对象
	
	public PageSupport() {
		this.pageNo=1;

    }
	/**
	 * 这个方法由张如兵添加  为何增加这个方法�?  这样调用参数�?�?,
	 * 使用更方�?  �?要的请求分页的信息已经在page里面�? 
	 * @param page
	 * @param items
	 * @param recordCount
	 */
    public PageSupport(PageSupport page,List items,int recordCount) {
    	this.items=items;
    	this.pageSize=page.getPageSize();
    	this.pageNo=page.getPageNo();
        this.setRecordCount(recordCount);
    }    

    /**
     * 根据记录数来计算页数 前提是相关数据已经在对象�?.
     * @param recordCount
     */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		pageCount = (recordCount - 1) / pageSize + 1;
		pageNo = pageNo > pageCount ? pageCount: pageNo;
	}
	/**
	 * 取开始记�?
	 * @return
	 */
	public int getStartRecord() {
		return pageSize * (pageNo-1);
	}
	/**
	 * 
	 * @param items
	 *            存储的对�?
	 * @param recordCount
	 *            总记录数
	 * @param pageSize
	 *            当前页显示的个数
	 * @param pageNo
	 *            当前页码
	 */
	public PageSupport(List items, int recordCount, int pageSize, int pageNo) {
		this.pageNo = pageNo;
		this.items = items;
		this.pageSize = pageSize;
		if (recordCount > 0) {
			this.recordCount = recordCount;
			this.pageCount = (recordCount - 1) / pageSize + 1;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List getItems() {
		return items;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 
	 * @return
	 */
	public int getRecordCount() {
		return recordCount;
	}
	
	/**
	 * 不推荐使用该方法
	 * @return
	 */
	public String getPageHtml()
	{
		StringBuffer html = new StringBuffer(); 
		if(recordCount>0 && pageCount >1 )
		{
			html.append(String.format("<input type=\"hidden\" name=\"pagenumber\" value=\"%d\"/>",pageNo));
 			html.append("<div id=\"page\">"); 
			html.append("<div class=\"page\">");			
			html.append("<span class=\"page-cur\">�?<em>"+(pageNo+1)+"</em>�?/�?<em>"+pageCount+"</em>�?<em>"+recordCount+"</em>记录</span>");
			html.append(" </span>");
			html.append("");
			
			if (pageNo+1==1)
			{
				html.append("<span class=\"page-first\">首页</span>");
				html.append("<span class=\"page-pre\">上一�?</span>");

			}
			else
			{
				html.append("<a class=\"page-first\" href=\"javascript:void(0);\" onclick=\"page(0);return false;\">首页</a>");
				html.append("<a class=\"page-pre\" href=\"javascript:void(0);\" onclick=\"page("+(pageNo-1)+");return false;\">上一�?</a>");
			}					
					
			if (pageNo+1==pageCount)
			{
				html.append("<span class=\"page-next\">下一�?</span>");
				html.append("<span class=\"page-last\">末页</span>");
			}
			else
			{
				html.append("<a class=\"page-next\" href=\"javascript:void(0);\" onclick=\"page("+(pageNo+1)+");return false;\">下一�?</a>");
				html.append("<a class=\"page-last\" href=\"javascript:void(0);\" onclick=\"page("+(pageCount-1)+");return false;\">末页</a>");
			}
			html.append("</div>");
			html.append("</div>");
		}		  
		return html.toString();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setItems(List items) {
		this.items = items;
	}

}