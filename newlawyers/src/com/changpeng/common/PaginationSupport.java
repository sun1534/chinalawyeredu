/**
 * PaginationSupport.java
 */

package com.changpeng.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 华锋 2008-2-20 下午04:55:28
 * 
 */
public class PaginationSupport {
	/**
	 * 每页的个数
	 */
	public final static int PAGESIZE = 30;
	private int pageSize = PAGESIZE;
	/**
	 * 放置的内容
	 */
	private List items;
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	private int pageNo;
	
	private int[] indexes = new int[0];
	
	/**
	 * 总共的页数
	 */
	private int count=0;
	/**
	 * 游标的起始位置
	 */
	private int startIndex = 0;
	
	public PaginationSupport() {
		setPageSize(PAGESIZE);
		setTotalCount(0);
		setItems(new ArrayList());
		setStartIndex(0);
		pageNo = 1;
	}
	
	public PaginationSupport(List items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
		pageNo = (this.startIndex / this.pageSize) + 1;
	}
	/**
	 * 游标的起始位置怎么获取呢?
	 * @param items
	 * @param totalCount
	 * @param startIndex
	 */
	public PaginationSupport(List items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
		pageNo = (this.startIndex / this.pageSize) + 1;
	}
	/**
	 * 
	 * @param items
	 * @param totalCount
	 * @param pageSize
	 * @param startIndex
	 */
	public PaginationSupport(List items, int totalCount, int pageSize, int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
		pageNo = (this.startIndex / this.pageSize) + 1;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		    count = totalCount / pageSize; //总共的页数
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;  //每页游标的起始位置
			}
		}
		else {
			this.totalCount = 0;
		}
	}
	public int[] getIndexes() {
		return indexes;
	}
	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}
	/**
	 * 得到现在的游标位置
	 * @return
	 */
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}
	/**
	 * 得到下一个游标的位置
	 * @return
	 */
	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}
	/**
	 * 得到前一个游标的位置
	 * @return
	 */
	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}
	//每页30条,0,30,60,90,120
//    private String pageView;
//    public void setPageView(String view){
//    	this.pageView=view;
//    }
    
	
	public String getPageView(){
		StringBuffer sb = new StringBuffer("<span>");
		pageNo = (this.startIndex / this.pageSize) + 1;

		// int pageNum = (this.totalCount + pageSize - 1) / pageSize;
		int pageNum = this.count;
		
		if (pageNum > 0) {
			
			
			sb.append("<font color='red'>总计").append(totalCount).append("条记录,第").append(pageNo).append("页,共").append(pageNum).append("页</font>");
			
			if(pageNum>1)
			{
//				<span><a href="#">首页</a></span>  <span><a href="#">上一页</a></span>  <span><a href="#">下一页</a></span>  <span><a href="#">尾页</a></span>  <span><a href="#">共6页</a></span>

				sb.append("&nbsp;&nbsp;");
				if (pageNo == 1) {
					sb.append("首页&nbsp;上一页&nbsp;");
				}
				else {
					sb.append("<span><a href=\"#\" onclick=fanye(").append(1).append(")>首页</a></span>&nbsp;<span><a href=\"#\" onclick=fanye(")
							.append(pageNo - 1).append(")>上一页</a></span>&nbsp;");
				}
				if (pageNo == pageNum) {
					sb.append("&nbsp;下一页&nbsp;末页");
				}
				else {
					sb.append("&nbsp;<span><a href=\"#\" onclick=fanye(").append(pageNo + 1).append(
							")>下一页</a></span>&nbsp;<span><a href=\"#\" onclick=fanye(").append(pageNum).append(")>末页</a></span>");
				}
				
			}
			
			
		}
		sb.append("</span>");
		return sb.toString();
	}
	
    public String getPageView1() {
		// log.debug("count:"+count);
		StringBuffer sb = new StringBuffer();
		pageNo = (this.startIndex / this.pageSize) + 1;

		// int pageNum = (this.totalCount + pageSize - 1) / pageSize;
		int pageNum = this.count;
		sb.append("<div class=\"page\">");
		if (pageNum > 0) {
		
			sb.append("<span class='page-total'>共"+pageNum+"页,"+totalCount+"条记录</span>");
			if(pageNo>1){
				sb.append("<a title='跳转到首页' class=\"page-move\" href=\"javascript:fanye('1');\" >&lt;&lt;</a>");
				//sb.append("<span class=\"page-move\" href=\"javascript:fanye('"+(pageNo-1)+"')\">&lt;&lt;</span>");
			}
			int currentPage=pageNo-2;
			if(currentPage<=0)
				currentPage=1;
			
			for(int i=currentPage, j=0;i<=pageNum&&j<5;j++,i++){
				if(i==pageNo)
					sb.append("<span title='第"+i+"页' class='page-current'>"+i+"</span>");
				else
					sb.append("<a title='跳到第"+i+"页' href='javascript:fanye("+i+");' >"+i+"</a>");
			}
			if(pageNo<pageNum){
				sb.append("<a title='跳转到末页' class=\"page-move\" href=\"javascript:fanye('"+(pageNum)+"');\" >&gt;&gt;</a>");
			}
			sb.append("<label>&nbsp;每页<input type='text' name='pageSize' value='"+pageSize+"' class='pageNo'/>&nbsp;条记录</label>");
			sb.append("<label>&nbsp;跳转至<input type='text' id='inputPageNo' value='"+pageNo+"' class='pageNo'/>&nbsp;页</label><input type='button' value='GO' class='goPage' onclick='fanye(document.getElementById(\"inputPageNo\").value)'/>");
			
//			<span class="page-total">共3页</span>
//			<span class="page-move">&lt;&lt;</span>
//			<span title="第1页" class="page-current">1</span>
//			<a title="跳到第2页" href="javascript:;" >2</a>
//			<a title="跳到第3页" href="javascript:;" >3</a>
//			<a title="跳到第4页" href="javascript:;" >4</a>
//			<a title="跳到第5页" href="javascript:;" >5</a>
//			<a title="跳到第4页" class="page-move" href="javascript:;" >&gt;&gt;</a>
//			<label>&nbsp;跳转至<input type="text" name="" value=""  class="pageNo"/>&nbsp;页</label><input type="button" name="" value="GO" class="goPage" />
		}
		sb.append("</div>");
		return sb.toString();
	}
    
    
	public String getOldPageView() {
		StringBuffer sb = new StringBuffer();
		int pageNo=(this.startIndex/this.pageSize)+1;
		
		//int pageNum = (this.totalCount + pageSize - 1) / pageSize;
		int pageNum=this.count;
		
		if (pageNum > 0) {
			sb.append("<font color='red'>总计").append(totalCount).append("条记录,第").append(pageNo).append("页,共").append(pageNum).append("页</font>");
//			if (pageNum == 1) {
//				sb.append("首页 前页 后页 末页");
//			}
//			else {
			
			if(pageNum>1)
			{
				
				sb.append(",");
				if (pageNo == 1) {
					sb.append("首页 前页");
				}
				else {
					sb.append("<a href=\"#\" onclick=fanye(").append(1).append(")>首页</a> <a href=\"#\" onclick=fanye(")
							.append(pageNo - 1).append(")>前页</a>");
				}
				if (pageNo == pageNum) {
					sb.append(" 后页 末页");
				}
				else {
					sb.append(" <a href=\"#\" onclick=fanye(").append(pageNo + 1).append(
							")>后页</a> <a href=\"#\" onclick=fanye(").append(pageNum).append(")>末页</a>");
				}
				
			}
		}
		return sb.toString();
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}