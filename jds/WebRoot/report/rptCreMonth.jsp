<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%@ page import="com.changpeng.report.util.*" %>
<%@ page import="java.util.*" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
<%
String rptmonth=request.getParameter("rptmonth");
JdbcTemplate jdbc=new JdbcTemplate();
List<HashMap<String,Object>> list=jdbc.query("select b.username,a.userid from trpt_cremonth a,tsys_user b where a.rptmonth='"+rptmonth+"' and a.userid=b.userid group by a.userid,b.username");
%>
<%!
public Object query(JdbcTemplate jdbc,Object userid,String rptmonth,int consigntype,String columnname){
	try{
		String temp="";
		if(consigntype!=0)
			temp=" and consigntype="+consigntype;
		return jdbc.queryOne("select "+columnname+" from trpt_cremonth where userid="+userid+" and rptmonth='"+rptmonth+"' "+temp+"");
	}catch(Exception e){
		return 0;
	}
}
%>
</style></HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">&nbsp; 
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">绩效管理&gt;&gt;信用卡月度回款统计</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
               <s:form name="form1" action="rptCreMonth.jsp" validate="true" method="post">
               <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="20">
                     统计月份:<jscalendar:jscalendar name="rptmonth" value="<%=rptmonth%>" format="%Y-%m"/>
                       <input type="submit" value="查询"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                        <TD>经办人</TD>
                      	<TD>委托类型</TD>
                      	<TD>月存量</TD>
                      	<TD>月新增</TD>
                      	<TD>月退回</TD>
                       <TD>月任务</TD>
                       <TD>月回款</TD>
                       <TD>回款率</TD>
                       <TD>次月存量</TD>
                      </TR>
                    </TR>
                    
                    <%if(list!=null&&list.size()>0){
                    for(HashMap<String,Object> map:list){
                    
                    %>                    	
                    <TR class="listline">
                        <TD rowspan=5><%=map.get("username")%></TD>
                        <TD>3000以下</TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,4,"retask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,4,"newtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,4,"backtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,4,"curtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,4,"endtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,4,"ratetask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,4,"nexttask")%></TD>
                      </TR>
					  <TR class="listline">
                      	<TD>3000-5000</TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,1,"retask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,1,"newtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,1,"backtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,1,"curtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,1,"endtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,1,"ratetask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,1,"nexttask")%></TD>
                      </TR>
                    </TR>
					 <TR class="listline">
                      	<TD>5000-10000</TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,3,"retask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,3,"newtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,3,"backtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,3,"curtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,3,"endtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,3,"ratetask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,3,"nexttask")%></TD>
                      </TR>
                    </TR>
					 <TR class="listline">
                      	<TD>10000以上</TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,2,"retask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,2,"newtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,2,"backtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,2,"curtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,2,"endtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,2,"ratetask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,2,"nexttask")%></TD>
                      </TR>
                    	<TR class="listline">
                      	<TD>小计</TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,0,"retask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,0,"newtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,0,"backtask")%></TD>
                      	<TD><%=query(jdbc,map.get("userid"),rptmonth,0,"curtask")%></TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,0,"endtask")%></TD>
                       <TD>&nbsp;</TD>
                       <TD><%=query(jdbc,map.get("userid"),rptmonth,0,"nexttask")%></TD>
                      </TR>
                    <%}}%>
                    
                  </TBODY>
              </TABLE>
                 </s:form>
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
<%
jdbc.safelyClose();
%>
</HTML>
