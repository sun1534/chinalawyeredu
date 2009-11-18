<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-角色列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysRoleCreate!input.action";
}
function deleteRole(roleid){
   if(confirm('您确定要删除这个角色吗?'))
     window.location.href="sysRoleDelete.action?roleid="+roleid;
   return false;
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
              <td width="97%"><span class="sort-title">系统管理&gt;&gt;角色列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="sysRoleList" method="POST">	
			
				
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  
                  
                  
                  	  <TR class="listheadline">               
                      <td>角色名</td>
        <td>级别</td>
        <td>创建人员</td>
        <td>备注信息</td>
        <td>分配权限</td>
        <td>修改</td>
        <td>删除</td>        
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                           <td>${rolename}</td>
        <td>${gradeid}</td>
        <td>${createuser}</td>
        <td width="40%">${comments}</td>
        <td><a href="sysRoleRightPre.action?roleid=${roleid}">分配权限</a></td>
      
          <td><a href="sysRoleEdit!input.action?roleid=${roleid}">修改</a></td>
            <td>

             <a href="#" onclick="deleteRole('${roleid}')">【删除】</a>

            </td>
                    
                      </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>

                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value=" 新增 ">&nbsp;&nbsp;

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