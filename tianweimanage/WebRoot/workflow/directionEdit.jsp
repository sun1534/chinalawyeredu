<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 编辑direction</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
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
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;编辑转向</span></td>
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
               <s:form name="form1" action="directionEdit" validate="true" method="post">
	 			 	<TR>
						<TD width="15%" class="listheadline">流向id:</TD>
						<TD width="35%" class="listline"><s:textfield name="direction.directionid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">流向名:</TD>
						<TD width="35%" class="listline"><s:textfield name="direction.directionname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">描述:</TD>
						<TD width="35%" class="listline"><s:textfield name="direction.description"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">起始节点:</TD>
						<TD width="35%" class="listline"><s:textfield name="direction.fromnodeid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">到达节点:</TD>
						<TD width="35%" class="listline"><s:textfield name="direction.tonodeid"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="direction.statusid" class="input1">
<s:if test="direction.statusid==1">
                         <OPTION VALUE="1" selected>启用</OPTION>
                         <OPTION VALUE="0">冻结</OPTION>
</s:if>
<s:if test="direction.statusid==0">
                         <OPTION VALUE="1">启用</OPTION>
                         <OPTION VALUE="0" selected>冻结</OPTION>
</s:if>
                      </SELECT>
                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline">
                        <s:textarea  name="direction.description" cols="30" rows="5"/>
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
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
