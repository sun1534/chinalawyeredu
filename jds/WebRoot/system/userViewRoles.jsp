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
          document.form1.action="userDeleteRoles.action";
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
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;用户角色</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
                <br>
                <table width="460"  border=0 align=center cellpadding=3 cellspacing=1>
                  <tbody>
                    <tr>
                      <td width="15%"  class="listheadline"><div align="center">用户名:</div></td>
                      <td width="35%"  class="listline"><s:property value="user.loginname"/></td>
                      <td width="15%"  class="listheadline"><div align="center">状 态:</div></td>
                      <td width="35%"  class="listline">${user.statusid==1?"启用":"冻结"} </td>
                    </tr>
                    <tr>
                      <td   class="listheadline"><div align="center">姓 名:</div></td>
                      <td  class="listline"><s:property value="user.username"/></td>
                      <td  class="listheadline"><div align="center">公 司:</div></td>
                      <td  class="listline"><s:property value="user.tsysDepartment.departmentname"/> </td>
                    </tr>
                  </tbody>
                </table>
                <br>
<s:form name="form1" action="userDeleteRoles.action" enctype="'whith=100%'"  method="POST">
<s:hidden name="userid"/>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1>
				    <TBODY >
                    <TR class="listheadline">
					  <TD  width="20%"><div align="center">选择</div></TD>
                      <TD  width="60%"><div align="center">角色</div></TD>
                      <TD  width="20%"><div align="center">操作</div></TD>
                    </TR>
                    <s:iterator value="userroles" status="status">
                    <TR align="middle" class="listline">
                        <TD>
						<INPUT class="inputwhiteboder" type="checkbox" value='<s:property value="comp_id.tsysRole.roleid"/>' name="check">
						</TD>
                      <TD>
					   <s:property value="comp_id.tsysRole.rolename"/>
					   </TD>
                      <TD align="center"><a href="userDeleteRole.action?userid=${user.userid}&roleid=${comp_id.tsysRole.roleid}">删除</a></TD>
                    </TR>
                    </s:iterator>
<s:if test="userroles.size!=0">
                    <TR  class="listline">
                      <TD colSpan=3 align="middle"><div align="left"><input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div></TD>
                    </TR>
</s:if>
                    <TR >
                      <TD colspan="3" align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
<s:if test="userroles.size!=0">
<input  class="botton" type=button onclick="return getDelete()" value="删除">&nbsp;
</s:if>
<input name="back2" type=button class="botton" onClick="document.backForm.submit()" value="返回"></TD>
                    </TR>
                  </TBODY>
                </TABLE> 
</s:form>
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

<s:form name="createForm" action="userAddRoles.action" method="POST">
  <s:hidden name="userid"/>
</s:form>
<s:form name="backForm" action="userList.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>