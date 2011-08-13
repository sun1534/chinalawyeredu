<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看node列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-16</p>
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
          document.form1.action="nodesDelete.action";
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
-->
</script>
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
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;${process.processname}</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="nodesDelete.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD align="center">节点名</TD>
                      <TD align="center">节点类型</TD>
                      <TD align="center">节点处理方式 </TD>
                      <TD align="center">处理人职务</TD>
                      <TD align="center">邮件通知</TD>
                      <TD align="center">短信通知</TD>
                      <TD align="center">详细信息</TD>
                      </TR>
<s:iterator value="process.twflNodes" status="status">
                      <TR class=listline>
                        <TD align="center">
                        <INPUT type="checkbox" value='${nodeid}' name="check">
                        </TD>
                        <TD>${nodename}</TD>
                        <TD>
${nodetype==1?"开始节点":""}
${nodetype==2?"普通节点":""}
${nodetype==3?"分支节点":""}
${nodetype==4?"汇聚节点":""}
${nodetype==5?"结束节点":""}
						</TD>
                        <TD>
${nodedotype==1?"单人":""}
${nodedotype==2?"循环":""}
${nodedotype==3?"多人串行":""}
${nodedotype==4?"多人并行":""}
                        </TD>
                        <TD>${tsysPosition.positionname}</TD>
                        <TD>${sendemail?"是":"否"}</TD>
                        <TD>${sendsms?"是":"否"}</TD>
                        <TD><a href="nodeView.action?nodeid=${nodeid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="nodelist!=null">
                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;&nbsp;
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
<s:form name="pageForm" action="nodeList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="nodeCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
    <s:hidden name="processid" value="${process.processid}"/>
</s:form>
<s:form name="searchForm" action="nodeSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
