<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 创建set</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
<script type="text/javascript">
function getDepartUser(){
	document.all.userForm.departmentid.value=document.all.department.value;
	document.userForm.submit();
}
function setUserName(name){
	var username =document.getElementById("setCreate_set_username");
	username.value = name;
}
</script>
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="userselect.js"></script>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;新增权限</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <TABLE width="480"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="setCreate" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
               <s:hidden name="set.username" />
	 			 	<TR>
						<TD width="15%" class="listheadline">权限类别:</TD>
						<TD width="35%" class="listline">
						<s:select 
						 name="set.tinfType.typeid"
						 listKey="typeid" 
						 listValue="name" 
						 list="typeList"
						 ></s:select>
						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">用户名:</TD>
						<TD width="35%" class="listline">
						<s:select 
						name="set.userid" 
						listKey="userid" 
						listValue="username" 
						list="userlist" 
						id="userselsect"
						/>
						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">权限:</TD>
						<TD width="35%" class="listline">
						<s:checkboxlist name="powerlist"
						list="@com.sxit.info.util.CommonDatas@Power"
						value="" />
						</TD>
					</TR>

                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <s:if test="userlist!=null">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        </s:if>
			            <input name="back" type=button class="botton" onClick="document.backForm.submit()" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<s:form name="userForm" action="setCreate!input.action" method="POST">
   <s:hidden name="departmentid" value=""/>
</s:form>
<s:form name="backForm" action="setList.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
</BODY>
</HTML>
