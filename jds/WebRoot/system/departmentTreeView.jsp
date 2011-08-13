<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看department</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-24</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">部门管理&gt;&gt;查看部门</span></td>
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
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY >
                    <TR>
                      <TD width="15%" class="listheadline">部门名称:</TD>
                      <TD width="35%" class="listline">${department.departmentname}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">类型:</TD>
                      <TD width="35%" class="listline">
                        ${department.type==0?"公司":""}
                        ${department.type==1?"一级部门":""}
                        ${department.type==2?"二级部门":""}
                        ${department.type==3?"三级部门":""}
					 </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">上级部门:</TD>
                      <TD width="35%" class="listline">${department.parent.departmentname}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">电话:</TD>
                      <TD width="35%" class="listline">${department.phone}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">联系人:</TD>
                      <TD width="35%" class="listline">${department.linkman}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">地址:</TD>
                      <TD width="35%" class="listline">${department.address}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">${department.statusid==1?"启用":"冻结"}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描述:</TD>
                      <TD width="35%" class="listline">${department.description}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">时间:</TD>
                      <TD width="35%" class="listline">${department.createtime}</TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">任职情况:</TD>
                      <TD width="35%" class="listline">

                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">&nbsp;</TD>
                      <TD width="35%" class="listline">&nbsp;</TD>
                    </TR>
                    
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                        <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
   <s:form name="editForm" action="departmentTreeEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   <s:hidden name="departmentid" value="${department.departmentid}"/>
   </s:form>
   <s:form name="deleteForm" action="departmentDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="departmentid" value="${department.departmentid}"/>
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="departmentList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
