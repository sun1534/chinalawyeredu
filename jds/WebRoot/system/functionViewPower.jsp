<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--
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
          document.form1.action="functionDeleteUsers.action";
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
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=20 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60"><div align="center"><img src="../images/arr.gif" width="13" height="13"></div></td>
              <td width="97%"><span class="title-blank-b">功能管理&gt;&gt;权限</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <br>
                <table width="460"  border=0 align=center cellpadding=3 cellspacing=1>
                  <tbody>
                    <tr>
                      <td width="15%"  class="listheadline"><div align="center">功能名:</div></td>
                      <td width="35%"  class="listline"><s:property value="function.functionname"/></td>
                      <td width="15%"  class="listheadline"><div align="center">编号:</div></td>
                      <td width="35%"  class="listline">${function.functionid} </td>
                    </tr>
                  </tbody>
                </table>
                <br>
<s:form name="form1" action="functionDeleteUsers.action" enctype="'whith=100%'"  method="POST">
<s:hidden name="functionid"/>
                <TABLE width="460"  border=0 align=center cellPadding=0 cellSpacing=0>
				    <TBODY >
                    
                    <TR>
                      <TD width="50%" valign="top"><table width="100%"  border=0 align=center cellpadding=3 cellspacing=1>
                          <tbody>
                            <tr class="listheadline">
                              <td width="25%">用户</td>
                              <td width="15%">权限</td>
							  <td width="15%">修改</td>
                            </tr>
<s:iterator value="function.tsysFunctionUsers" status="status">
                            <tr>
                              <td width="25%"  class="listline"><s:property value="comp_id.tsysUser.username"/></td>
                              <td width="15%"  class="listline">${power}</td>
                       		  <td width="15%"  class="listline">
<a href="functionChangeUserPower.action?userid=${comp_id.tsysUser.userid}&functionid=${function.functionid}">修改</a> 
                              </td>
                            </tr>
</s:iterator>
                          </tbody>
                        </table>
                        </TD>
                      <TD width="50%" valign="top">
					    <table width="100%"  border=0 align=center cellpadding=3 cellspacing=1>
                          <tbody>
                            <tr class="listheadline">
                              <td width="25%">角色</td>
                              <td width="15%">权限</td>
                       		  <td width="15%">修改</td>
                            </tr>
<s:iterator value="function.tsysFunctionRoles" status="status">
                            <tr>
                              <td width="25%"  class="listline"><s:property value="comp_id.tsysRole.rolename"/></td>
                              <td width="15%"  class="listline">${power}</td>
                       		  <td width="15%"  class="listline">
<a href="functionChangeRolePower.action?roleid=${comp_id.tsysRole.roleid}&functionid=${function.functionid}">修改</a> 
							</td>
                            </tr>
</s:iterator>
                          </tbody>
                      </table>				      </TD>
                    </TR>
                    <TR >
                      <TD colspan="2" align="center"><s:if test="functionusers.size!=0">
</s:if>
<input name="back2" type=button class="botton" onClick="document.backForm.submit()" value="返回"></TD>
                    </TR>
                  </TBODY>
                </TABLE> 
</s:form>
<p>&nbsp;</p>
<p>&nbsp;</p>
				               <p>&nbsp;</p>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

<s:form name="createForm" action="functionAddUsers.action" method="POST">
  <s:hidden name="functionid"/>
</s:form>
<s:form name="backForm" action="functionList.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>