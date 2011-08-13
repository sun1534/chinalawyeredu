<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
          document.form1.action="nonlawlogDeletes.action";
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
<s:form name="form1" action="nonlawlogDeletes.action" method="POST">
<s:hidden name="nonlawtaskid"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>选择</TD>
	                    <TD >客户姓名</TD>                  
                      <TD >催收日期</TD>
                      <TD >催收类型</TD>
                      <TD >催收情况</TD>
                      <TD >记录时间</TD>
                       <TD >详细信息</TD>
                      </TR>
<s:iterator value="nonlawloglist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${logid}' name="check">
                        </TD>
                      <TD >${tnlwNonlawtask.tnlwNonlaw.username}</TD>
              
                      <TD >${logtime}</TD>
                      <td>
                      <s:if test="logtype==1">电话催收</s:if>
                      <s:else>上门催收</s:else>
                      </td>
                      <TD >${comments}</TD>
                      <TD ><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></TD>
                        <TD><a href="nonlawlogView.action?nonlawid=${logid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="nonlawloglist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
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
<input  class="botton" type=button onclick="return getDelete()" value="删除">&nbsp;
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
<s:form name="pageForm" action="nonlawlogList.action" method="POST">
	<s:hidden name="nonlawtaskid"/>
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="nonlawlogCreate!input.action" method="POST">
	<s:hidden name="nonlawtaskid"/>
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
