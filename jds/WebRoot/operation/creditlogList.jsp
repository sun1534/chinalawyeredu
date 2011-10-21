<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<jscalendar:head/>
<%
/**
 * <p>功能： 查看creditlog列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
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
     document.pageForm.action = "searchUser.action";
     document.pageForm.submit();
}
function noChecked() {
     var i;
     if(document.pageForm.check!=null){
       if(document.pageForm.check.length!=null){
            for(i=0;i<document.pageForm.check.length;i++){
                 if(document.pageForm.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.pageForm.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.pageForm.check!=null){
          if(document.pageForm.check.length!=null){
               for(i=0;i<document.pageForm.check.length;i++){
                    document.pageForm.check[i].checked=document.pageForm.selectAll.checked;
               }
          }else{
               document.pageForm.check.checked=document.pageForm.selectAll.checked;
          }
     }
}
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.pageForm.action="creditlogDeletes.action";
          document.pageForm.submit();
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
document.pageForm.action="creditlogList.action";
 	document.pageForm.submit();
 	return true;
}
function ccbexport(){
document.pageForm.action="creditlogList.action?ccbexport=ccbexport";
 	document.pageForm.submit();
 	return true;
}
function ccbtxtexport(){
document.pageForm.action="creditlogList.action?ccbexport=txt";
 	document.pageForm.submit();
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;催收日志列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="pageForm" action="creditlogList.action" method="POST">
<s:hidden name="credittaskid"/>
<s:hidden name="pagenumber"/>
<s:hidden name="ismine"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=13 >
			           委托银行:<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					  
					  客户帐号：<s:textfield name="creditcard" size="15"/>
					 催收日期:<jscalendar:jscalendar	name="start" format="%Y-%m-%d"/>至<jscalendar:jscalendar	name="end" format="%Y-%m-%d"/>
					  <input type="button" value="查询" class="botton" onclick="query()"/>		
					<input type="button" value="导出成excel" class="botton" onclick="ccbexport()"/>	
					<input type="button" value="导出成文本txt" class="botton" onclick="ccbtxtexport()"/>
			           	
			           	
			           </TD>
			          </TR>
			          <tr>
			          <td colSpan=13 class=listline><span id="sum_desc" style="color:red"></span></td>
			          </tr>
                  
                      <TR class="listheadline">
                        <TD>选择</TD>
                         <TD>客户帐号</TD>         
                         <TD>客户姓名</TD>                  
                      <TD>催收日期</TD>
                      <TD>催收情况</TD>
                      <TD>记录时间</TD>
                       <TD>详细信息</TD>
                      </TR>
<s:iterator value="creditloglist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${logid}' name="check">
                        </TD>
                          <TD >${toprCredittask.toprCreditcard.creditcard}</TD>
                      <TD >${toprCredittask.toprCreditcard.username}</TD>
              
                      <TD >${logtime}</TD>
                      <TD >${comments}</TD>
                      <TD ><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></TD>
                        <TD><a href="creditlogView.action?creditlogid=${logid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="creditloglist!=null">                    
<TR bgcolor="#ECECFF" class="pt9-18">
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

					<input type="button" value="导出成excel" class="botton" onclick="ccbexport()"/>	&nbsp;
					
					<input type="button" value="导出成文本txt" class="botton" onclick="ccbtxtexport()"/>
<!-- 
<input  class="botton" type=button onclick="return getDelete()" value="删除">&nbsp;

-->
<input  class="botton" type=button onclick="history.go(-1)" value="返回">
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
<s:form name="pageForm1" action="creditlogList.action" method="POST">
	<s:hidden name="credittaskid"/>
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="creditlogCreate!input.action" method="POST">
	<s:hidden name="credittaskid"/>
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
