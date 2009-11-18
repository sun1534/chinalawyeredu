<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-用户列表</title>
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
	window.location.href="sysUserCreate!input.action";
}
function deleteInfo(paramname){
  if(confirm('您确定要删除这个用户吗?'))
    window.location.href="sysUserDelete.action?check="+userid;
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
              <td width="97%"><span class="sort-title">系统管理&gt;&gt;管理员列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="sysUserList" method="POST">	
			
				
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  
               <tr class=listline >
                  	  <td align="left" colspan="16">
              
                 	<s:hidden name="pageNo"/>
    登录名：<s:textfield name="loginname" size="10"/>
    姓名：<s:textfield name="username" size="10"/>
	  <input type="submit" value="查询" class="botton">
                  	  </td>
                  	  </tr>
                  
                  	  <TR class="listheadline">               
                      <TD>姓名</TD>
                      <TD>帐号</TD>
                      <TD>所在部门</TD>
                         <TD>状态</TD>
        <s:if test="hassetrole">
        <TD>分配角色</TD>
        </s:if>
          <s:if test="hassetrights">
        <TD>分配权限</TD>
        </s:if>
                      <TD>修改</TD>               
                      <TD>删除</TD>        
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                       <TD>${username}</TD>
                       <TD>${loginname }</TD>  
                          <TD>${sysGroup.groupname}</TD>  
                        <td>
          <s:if test="status==0">
        激活
        </s:if>
        <s:else>
    <b>冻结</b>
        </s:else></strong></td>
         <s:if test="hassetrole">
        <td><a href="sysUserRolePre.action?userid=${userid}">角色分配</a></td>
        </s:if>
         <s:if test="hassetrights">
           <td><a href="sysUserRightPre.action?userid=${userid}">权限分配</a></td>
           </s:if>
          <td><a href="sysUserEdit!input.action?userid=${userid}">修改</a></td>
            <td><a href="#" onclick="deleteUser('${userid}')">【删除】</a></td>
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