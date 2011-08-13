package com.changpeng.callcenter.action;
import java.sql.*;

import com.changpeng.common.Globals;
public class Page {
	private int pageSize; //每页显示多少条记录
	private int pageNow; //希望显示第几页
	private int pageCount; //一共有多少页
	private int rowCount; //一共有多少条记录
	public int getPageSize() {
		return pageSize;
	}
	public int getPageNow() {
		return pageNow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public void query() throws Exception{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			con=Globals.getInstance().callCenter();
			stmt=con.createStatement();
			rs=stmt.executeQuery("select count(*) from BASERECORD");
			if(rs.next()){
				rowCount=rs.getInt(1);
			}
			//计算pageCount
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			rs=stmt.executeQuery("select top "+pageSize+" * from BASERECORD where id not in(select top "+pageSize*(pageCount-1)+" id from BASERECORD) ");
		}finally{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}
	}
}
