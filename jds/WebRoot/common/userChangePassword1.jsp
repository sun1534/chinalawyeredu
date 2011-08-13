<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="ww" uri="webwork" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="../imagesa/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 2px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60"><div align="center"><img src="../imagesa/ico_arrow_title.gif" width="13" height="13"></div></td>
              <td width="97%"><span class="title-blank-b">管理员管理&gt;&gt;编辑用户</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<ww:form name="'form1'" action="'userPassword'" validate="true" method="'post'">
<ww:hidden name="'flag'" value="'save'"/>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY class="pt9-18">
                    <TR>
                      <TD width="15%" bgcolor="#FFD275" class="pt9-18" align="center">用户名:</TD>
                      <TD width="35%" bgcolor="#ECECFF">
					  <ww:property  value="user.username"/>
                      </TD>
                    </TR>
					<TR>
                      <TD width="15%" bgcolor="#FFD275" ><div align="center">旧密码:</div></TD>
                      <TD width="35%" bgcolor="#ECECFF">
					  <ww:textfield  name="'oldpassword'" template="'textfield.vm'" required="true"/>
					  </TD>
					</TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">新密码:</div></TD>
                      <TD bgcolor="#ECECFF">
					  <ww:textfield  name="'password1'" template="'textfield.vm'" required="true"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">新密码:</div></TD>
                      <TD bgcolor="#ECECFF">
					  <ww:textfield  name="'password2'" template="'textfield.vm'" required="true"/>
                      </TD>
                    </TR>
                    <TR bgcolor="#ECECFF">
                      <TD colspan="2" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">
                &nbsp;
                      <input name="back" type=button class="mask" onClick="javascript:history.back(-1)" value="返回"></TD>
                    </TR>
                  </TBODY>
                </TABLE>
</ww:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
