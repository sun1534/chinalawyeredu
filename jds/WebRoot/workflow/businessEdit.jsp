<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 编辑business</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-11-13</p>
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
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;编辑业务</span></td>
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
                    <s:form name="form1" action="businessEdit" validate="true" method="post"> <s:hidden name="pagenumber" value="${pagenumber}"/>
                    <TR>
                      <TD width="15%" class="listheadline">业务名:</TD>
                      <TD width="35%" class="listline"><s:textfield name="business.businessname"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">类别 :</TD>
                      <TD width="35%" class="listline">

                        <SELECT name="business.usetype" class="input1">
<s:if test="business.usetype==1">
                          <OPTION VALUE="1" selected>所有人适用</OPTION>
                          <OPTION VALUE="0">成员适用</OPTION>
</s:if>
<s:if test="business.usetype==0">
                          <OPTION VALUE="1" >所有人适用</OPTION>
                          <OPTION VALUE="0" selected>成员适用</OPTION>
</s:if>
                        </SELECT>

                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">模块:</TD>
                      <TD width="35%" class="listline">
 <s:select 
	name="business.module.moduleid" 
	listKey="moduleid" 
	listValue="modulename" 
	list="modulelist" 
/> </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                        <SELECT name="business.statusid" class="input1">
                          <s:if test="business.statusid==1">
                          <OPTION VALUE="1" selected>启用</OPTION>
                          <OPTION VALUE="0">冻结</OPTION>
                          </s:if> <s:if test="business.statusid==0">
                          <OPTION VALUE="1">启用</OPTION>
                          <OPTION VALUE="0" selected>冻结</OPTION>
                          </s:if>
                        </SELECT>

                      </TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">
                &nbsp;
                        <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
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
