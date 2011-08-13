<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%//@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 编辑bank</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<!--
<jscalendar:head/>
<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown
":""}${curuser.style==3?"green":""}.css" type="text/css"/>
-->
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;编辑委托银行</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="bankEdit" validate="true" method="post">
	 			 	<!--
	 			 	<TR>
						<TD width="15%" class="listheadline">银行id:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.bankid"/></TD>
					</TR>
	 			 	-->
	 			 	<TR>
						<TD width="15%" class="listheadline">银行名称:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.bankname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.contactpeople"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.contactphone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.contactmobile"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="bank.address"/></TD>
					</TR>
                    <!--
                    <TR>
                      <TD width="15%" class="listheadline">时间:</TD>
                      <TD width="35%" class="listline"> <jscalendar:jscalendar name="bank.begindate" format="%Y-%m-%d" showstime="false"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="bank.statusid" class="input1">
<s:if test="bank.statusid==1">
                         <OPTION VALUE="1" selected>启用</OPTION>
                         <OPTION VALUE="0">冻结</OPTION>
</s:if>
<s:if test="bank.statusid==0">
                         <OPTION VALUE="1">启用</OPTION>
                         <OPTION VALUE="0" selected>冻结</OPTION>
</s:if>
                      </SELECT>
                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline">
                        <s:textarea  name="bank.description" cols="38" rows="6"/>
                      </TD>
                    </TR>
	 			 	-->
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
		      </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
