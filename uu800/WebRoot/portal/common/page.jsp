<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@page import="com.sxit.common.PageSupport"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%
//分页的模板,后续可以使用这个进行分页的设置,对象都为Page
//
//通过 <%@include file="/common/page.jsp?page=pageobj"的方式调用
//其中pageobj为action中的一个对象，具有getPageobj()的方法
//
ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
String pageobj=(String)request.getAttribute("page");
if(pageobj!=null&&!pageobj.equals("")){
PageSupport sxitpage=null;
if(vs!=null)
 sxitpage=(PageSupport)vs.findValue(pageobj);
 else
 sxitpage= (PageSupport)request.getAttribute("page");
 if(sxitpage!=null){
 //设置当前分方式的起始页
 int currentPage=sxitpage.getPageNo()-2;
 if(currentPage<0)
 currentPage=1;
 //设置当前分方式的结束页
 int endPage=currentPage+4;
  if(endPage>sxitpage.getPageCount())
 endPage=sxitpage.getPageCount();
 int[] pageSizes=new int[]{5,10,20,50,100};
 
 %>
				<div class="page">
								<span class="page-total">共<%=sxitpage.getRecordCount() %>条 当前<%=sxitpage.getPageNo() %>/<%=sxitpage.getPageCount() %></span>
							
								<%if(sxitpage.getPageCount()>1){ %>
								<span class="page-first">首页</span>
								<%if(sxitpage.getPageNo()>1){ %>
								<span class="page-pre">上一页</span>
								<%} %>
								<%for(int i=currentPage;i<=endPage;i++){ %>
								<%if(i==sxitpage.getPageNo()) {%>
								<span class='page-current'><%=i %></span>
								<%}else{ %>
								<a title='跳到第${i}页 href='javascript:gotoPage(<%=i %>);'><%=i %></a>
								<%} %>
								<%} %>
								<%if(sxitpage.getPageNo()<sxitpage.getPageCount()){ %>						
								<a href="javascript:;" class="page-next">下一页</a>
								<%} %>
								<a href="javascript:;" class="page-last">末页</a>
								<span class="selPage">每页显示 
									<select name="pageSize">
									<%for(int pagesize:pageSizes) {%>
									<option value="<%=pagesize %>" <%if(pagesize==sxitpage.getPageSize()){ %>selected<%} %>><%=pagesize %></option>
									<%} %>
									</select>条
								</span>
								<span class="pageOp">
									<label for="pageNo">&nbsp;跳转至
									<select name="pageNo">
										<%for(int i=1;i<=sxitpage.getPageCount();i++){ %>
										<option value="<%=i %>" <%if(i==sxitpage.getPageNo()){ %>selected<%} %>><%=i %></option>
										<%} %>
									</select>页
									</label>
								</span>
								<%} %>
							</div>
							<%}
							}%>