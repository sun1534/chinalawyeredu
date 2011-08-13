<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 编辑position</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-12</p>
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
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">职务管理&gt;&gt;编辑职务</span></td>
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
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="positionEdit" validate="true" method="post">
               	 	<TR>
					  <TD width="15%" class="listheadline">职务名称:</TD>
					  <TD width="35%" class="listline"><s:textfield name="position.positionname"/></TD>
					</TR>
               	 	<TR>
					  <TD width="15%" class="listheadline">备注:</TD>
					  <TD width="35%" class="listline"><s:textfield name="position.mark"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务类别:</TD>
						<TD width="35%" class="listline">
						<SELECT name="position.type" class="input1">
<s:if test="position.type==1">
                         <OPTION VALUE="1" selected>公司级别</OPTION>
                         <OPTION VALUE="0">部门级别</OPTION>
</s:if>
<s:if test="position.type==0">
                         <OPTION VALUE="1">公司级别</OPTION>
                         <OPTION VALUE="0" selected>部门级别</OPTION>
</s:if>
                      </SELECT>
					  </TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
		      </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
                <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
