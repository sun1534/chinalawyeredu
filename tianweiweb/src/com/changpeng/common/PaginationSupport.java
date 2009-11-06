/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.changpeng.common;

import java.util.List;

/**
 *
 * @author mfq 2008-11-26 13:08:32
 */
public class PaginationSupport {
	 
    //每页记录数
    public final static int PAGESIZE = 10;
    private int pageSize = PAGESIZE;

    //内容
    private List items;

    //总数
    private int totalCount;
    private int[] indexes = new int[0];

    //开始记录数
    private int startIndex = 0;

    //总页数
    private int count=0;

    public PaginationSupport(List items, int totalCount) {
        setPageSize(PAGESIZE);
        setTotalCount(totalCount);
        setItems(items);
        setStartIndex(0);
    }
    public PaginationSupport() {

    }
    public PaginationSupport(List items, int totalCount, int startIndex) {
        setPageSize(PAGESIZE);
        setTotalCount(totalCount);
        setItems(items);
        setStartIndex(startIndex);
    }

    public PaginationSupport(List items, int totalCount, int pageSize, int startIndex) {
        setPageSize(pageSize);
        setTotalCount(totalCount);
        setItems(items);
        setStartIndex(startIndex);
       // log.debug("pageSize:"+pageSize+",totalCount:"+totalCount+",startIndex:"+startIndex);
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
    
    public int getCount() {
		return count;
	}

	public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            count = totalCount / pageSize;
            if (totalCount % pageSize > 0) {
                count++;
            }
            indexes = new int[count];
            for (int i = 0; i < count; i++) {
                indexes[i] = pageSize * i;
            }
        } else {
            this.totalCount = 0;
        }
    }

    public int[] getIndexes() {
        return indexes;
    }

    public void setIndexes(int[] indexes) {
        this.indexes = indexes;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        if (totalCount <= 0) {
            this.startIndex = 0;
        } else if (startIndex >= totalCount) {
            this.startIndex = indexes[indexes.length - 1];
        } else if (startIndex < 0) {
            this.startIndex = 0;
        } else {
            this.startIndex = indexes[startIndex / pageSize];
        }
    }

    public int getNextIndex() {
        int nextIndex = getStartIndex() + pageSize;
        if (nextIndex >= totalCount) {
            return getStartIndex();
        } else {
            return nextIndex;
        }
    }

    public int getPreviousIndex() {
        int previousIndex = getStartIndex() - pageSize;
        if (previousIndex < 0) {
            return 0;
        } else {
            return previousIndex;
        }
    }
    public String getPageView() {
        //log.debug("count:"+count);
		StringBuffer sb = new StringBuffer();
		int pageNo=(this.startIndex/this.pageSize)+1;

		//int pageNum = (this.totalCount + pageSize - 1) / pageSize;
		int pageNum=this.count;

		if (pageNum > 0) {
			sb.append("总计").append(totalCount).append("条记录,第").append(pageNo).append("页,共").append(pageNum).append("页");
//			if (pageNum == 1) {
//				sb.append("首页 前页 后页 末页");
//			}
//			else {
//			总计 100 条记录 第 1 页 共 6 页 <a href="javascript:void(0)"> |&lt; </a>  <a href="javascript:void(0)"> &lt; </a><a href="javascript:void(0)"> &gt; </a>  <a href="javascript:void(0)"> &gt;| </a>  到第<a href="javascript:void(0)"> XX </a> 页

			if(pageNum>1){
				if (pageNo == 1) {
					sb.append("|&lt; &lt;");
				}
				else {
					sb.append("<a href=\"javascript:void(0)\" onclick=fanye(").append(1).append(")>|&lt;</a> <a href=\"javascript:void(0)\" onclick=fanye(")
							.append(pageNo - 1).append(")>&lt;</a>");
				}
				if (pageNo == pageNum) {
					sb.append(" &gt; &gt;|");
				}
				else {
					sb.append("<a href=\"javascript:void(0)\" onclick=fanye(").append(pageNo + 1).append(
							")> &gt;</a> <a href=\"javascript:void(0)\" onclick=fanye(").append(pageNum).append(")>&gt;|</a>");
				}
			}
		}
		return sb.toString();
	}
    
    public String getJxqPage() {
    	StringBuffer sb = new StringBuffer();
		int pageNo=(this.startIndex/this.pageSize)+1;

		//int pageNum = (this.totalCount + pageSize - 1) / pageSize;
		int pageNum=this.count;

		if (pageNum > 0) {
			sb.append("总计").append(totalCount).append("条记录,第").append(pageNo).append("页,共").append(pageNum).append("页");
//			if (pageNum == 1) {
//				sb.append("首页 前页 后页 末页");
//			}
//			else {
//			总计 100 条记录 第 1 页 共 6 页 <a href="javascript:void(0)"> |&lt; </a>  <a href="javascript:void(0)"> &lt; </a><a href="javascript:void(0)"> &gt; </a>  <a href="javascript:void(0)"> &gt;| </a>  到第<a href="javascript:void(0)"> XX </a> 页

			if(pageNum>1){
				if (pageNo == 1) {
					sb.append("首页 前一页");
				}
				else {
					sb.append("<a href=\"javascript:fanye(").append(1).append(")\" onclick=fanye(").append(1).append(")>首页</a> <a href=\"javascript:fanye(")
							.append(pageNo - 1).append(")\" onclick=fanye(")
							.append(pageNo - 1).append(")>前一页</a>");
				}
				if (pageNo == pageNum) {
					sb.append(" 下一页 末页");
				}
				else {
					sb.append("<a href=\"javascript:fanye(").append(pageNo + 1).append(
							")\" onclick=fanye(").append(pageNo + 1).append(
							")> 下一页</a> <a href=\"javascript:fanye(").append(pageNum).append(")\" onclick=fanye(").append(pageNum).append(")>末页</a>");
				}
			}
		}
		return sb.toString();
	}
}
