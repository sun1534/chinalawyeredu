<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-待认证用户列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
function getAdd(){
  window.location.href="usersCreate!input.action?userRole=${userRole}";
}
function deleteit(uid){
  if(confirm("您确实要删除这个用户吗?"))
    window.location.href="usersDelete.action?userid="+uid;
  else
    return;
  
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
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;商户信息列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="waitUsersList" method="POST">	
				<s:hidden name="userRole"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="center" colspan="16">
              
                 	<s:hidden name="pageNo"/>
                  	名称:<s:textfield name="userName" size="10"/>
                     注册时间从:<jscalendar:jscalendar name="start"/> &nbsp;
                  	到:<jscalendar:jscalendar name="end"/> &nbsp;
                  	  <input type="submit" value="查询" class="botton">
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                      <TD>名称</TD>
                      <TD>登录名</TD>
                      <TD>类型</TD>
                      <TD>注册时间</TD>
                      <TD>状态</TD>
                      <TD>联系手机</TD>
                    <TD>申请认证方式</TD>
                      <TD>处理</TD>               
                      
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                       <TD>${userName}</TD>
                        <TD>${loginName}</TD>
                       <TD>
                       <s:if test="userRole==1">家庭</s:if>
                       <s:else>企业</s:else>
                       </TD>    
                        <TD><s:date name="regTime" format="yyyy-MM-dd HH:mm"/></TD>                                                                        
                        <TD>
                        <s:property value="@com.sxit.users.util.CommonDatas@STATUS[status]"/>
                     </TD>
                        <TD>${mobile}</TD>
                         <TD>
                         <s:property value="@com.sxit.users.util.CommonDatas@APPROVETYPE[approveType]"/>
                         </TD>
                        <TD><a href="usersConfirmDoView.action?userId=${id}">【处理】</a></TD>         
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>
<!--
                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
              -->
                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value="新增">&nbsp;&nbsp;

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