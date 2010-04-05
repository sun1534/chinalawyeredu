/**
 * PaginationSupport.java
 */

package com.changpeng.common;

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
	
	private int[] indexes = new int[0];
	
	/**
	 * 总共的页数
	 */
	private int count=0;
	/**
	 * 游标的起始位置
	 */
	private int startIndex = 0;
	public PaginationSupport(List items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
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
    private String pageView;
    public void setPageView(String view){
    	this.pageView=view;
    }
	public String getPageView() {
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
}