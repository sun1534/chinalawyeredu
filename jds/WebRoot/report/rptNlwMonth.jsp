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
String bankid=request.getParameter("bankid");
String rptmonth=request.getParameter("rptmonth");
JdbcTemplate jdbc=new JdbcTemplate();
List<HashMap<String,Object>> list=jdbc.query("select bankid,bankname,sum(ausers) ausers,sum(pusers) pusers,sum(eusers) eusers,sum(allfee) allfee,sum(allpfee) allpfee,decode(sum(allfee),0,0,sum(allpfee)/sum(allfee)) rate from trpt_nlwmonth where rptmonth='"+rptmonth+"' and bankid="+bankid+" group by bankid,bankname");
%>
<%!
public int rowsize(JdbcTemplate jdbc,Object bankid,Object bankname,String rptmonth){
	try{
		Object o=jdbc.queryOne("select count(*) from trpt_nlwmonth where rptmonth='"+rptmonth+"' and bankid="+bankid+" and bankname='"+bankname+"'");
		if(o==null) o=0;
		return Integer.parseInt(o+"")+1;
	}catch(Exception e){
		return 1;
	}
}
%>
</style></HEAD>
<BODY onload="document.form1.bankid.value=<%=bankid%>">
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
              <td width="97%"><span class="sort-title">绩效管理&gt;&gt;非诉月度回款统计</span></td>
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
               <s:form name="form1" action="rptNlwMonth.jsp" validate="true" method="post">
               <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="20">
                     委托银行:<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" listKey="bankid" listValue="bankname"/>					 
                     统计月份:<jscalendar:jscalendar name="rptmonth" value="<%=rptmonth%>" format="%Y-%m"/>
                       <input type="submit" value="查询"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                        <TD>支行</TD>
                      	<TD>总户数</TD>
                      	<TD>总还款</TD>
                      	<TD>结案数</TD>
                      	<TD>总任务</TD>
                       <TD>回款总额</TD>
                       <TD>回款率</TD>
                       <TD>催收人员</TD>
                       <TD>催收户数</TD>
                       <TD>还款户数</TD>
                       <TD>结案户数</TD>
                       <TD>本月任务</TD>
                       <TD>回款总额</TD>
                       <TD>回款率</TD>
                      </TR>
                    </TR>
                    
                    <%if(list!=null&&list.size()>0){
                    for(HashMap<String,Object> map:list){
                    	int rowsize=this.rowsize(jdbc,bankid,map.get("bankname"),rptmonth);
                    %>                    	
                    <TR class="listline">
                        <TD rowspan="<%=rowsize%>"><%=map.get("bankname")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("ausers")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("pusers")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("eusers")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("allfee")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("allpfee")%></TD>
                        <TD rowspan="<%=rowsize%>"><%=map.get("rate")%></TD>
                        <TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&nbsp;</TD>
                      </TR>
						<%
						List<HashMap<String,Object>> users=jdbc.query("select a.*,b.username from trpt_nlwmonth a,tsys_user b where a.taskuser=b.userid and rptmonth='"+rptmonth+"' and bankid="+bankid+" and bankname='"+map.get("bankname")+"'");
						if(users!=null&&users.size()>0){
                    for(HashMap<String,Object> u:users){%>
                    <TR class="listline">
                    	<TD><%=u.get("username")%></TD>		
                        <TD><%=u.get("ausers")%></TD>
                        <TD><%=u.get("pusers")%></TD>
                        <TD><%=u.get("eusers")%></TD>
                        <TD><%=u.get("allfee")%></TD>
                        <TD><%=u.get("allpfee")%></TD>
                        <TD><%=u.get("ratefee")%></TD>
                      </TR>		
						 	<%}
						 }	
                    }}%>
                    
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
