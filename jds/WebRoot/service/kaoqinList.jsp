<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 查看kaoqin列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
}
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="kaoqinDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function query(){
	document.form1.action="kaoqinList.action";
    document.form1.submit();
}
-->
</script>
<jscalendar:head/>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">考勤管理&gt;&gt;考勤管理列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="kaoqinDeletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="7">
                        用户：<s:textfield name="username" size="10"/>
                   上\下班:<s:select name="kqflag" list="#{0:'全部',1:'上班',2:'下班'}"/>
                 考勤结果:<s:select name="kqresult" list="#{0:'全部',3:'正常',1:'迟到',2:'早退',4:'病假',5:'事假'}"/>
                     考勤日期:<jscalendar:jscalendar name="kqdate" format="%Y-%m-%d"/>
                       <input type="button" value="查询" onclick="query()"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD >员工</TD>
                      <TD >上\下班</TD>
                      <TD >考勤日期</TD>
                      <TD >出\入时间</TD>
                      <TD >考勤结果<!--  3:正常 1:迟到 2:早退 --> </TD>
                       <TD >详细信息</TD>
                      </TR>
<s:iterator value="kaoqinlist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${kaoqinid}' name="check">
                        </TD>
                      <TD >${username}</TD>
                      <TD ><s:if test="kqflag==1">上班</s:if><s:else>下班</s:else></TD>
                      <TD >${kqdate}</TD>
                      <TD >${kqtime}</TD>
                      <TD ><s:if test="kqresult==3">正常</s:if><s:elseif test="kqresult==1">迟到</s:elseif>
                      <s:elseif test="kqresult==4">病假</s:elseif><s:elseif test="kqresult==5">事假</s:elseif>
                      <s:else>早退</s:else></TD>
                      
                        <TD><a href="kaoqinView.action?kaoqinid=${kaoqinid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="kaoqinlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>
${pagestring}
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 >
<div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
<input  class="botton" type=button onclick="return getDelete()" value="删除">
</div>
                       </TD>
                    </TR>
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="kaoqinList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="kaoqinCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
