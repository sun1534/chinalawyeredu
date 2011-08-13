<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
/**
 * <p>功能： 查看department列表</p>
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
<s:head theme="ajax" debug="true" />
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script>
<!--
            function windowsopen(url) {
 document.getElementById('depleft').src=url;
            }
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=5 bgColor=#FFFFFF>
     </TD>
  </TR>

  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR class=listline>
            <TD height="30">
			 <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr class=listline>
				<td align="left">
				   公司：
				   <s:iterator value="departmentlist" status="status">
				   <a href="javascript:windowsopen('departmentTree.action?departmentid=${departmentid}')">${departmentname}</a>
				   </s:iterator>
				</td>
				<td width="80"  align="center">
                  <a href="departmentList.action">管理部门</a>                </td>
			  </tr>
			</table>
			</TD>
		  </TR>
          <TR>
            <TD height="380" valign="top" bgColor=#F9F9F7>
            <table width="100%"  border=0 align=left cellpadding=3 cellspacing=1 >
              <tbody >
                <tr>
                  <td width="180" valign="top" class="listline">                    
<iframe id="depleft" width="100%" noResize frameborder="0" height="100%" name="depleft" src="departmentTree.action?departmentid=1"  marginwidth="0"  scrolling="auto" marginheight="0" ></iframe>
                  </td>
                  <td height="380" class="listline">
<iframe id="depmain" width="100%" noResize frameborder="0" height="100%" name="depmain" src="departmentTreeView.action?departmentid=1"  marginwidth="0"  scrolling="auto" marginheight="0" ></iframe> 
                 </td>
                </tr>
              </tbody>
            </table></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

