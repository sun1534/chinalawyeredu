<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-视窗链接管理</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">


<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
</HEAD>
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;视窗链接新增修改</span></td>
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
                <TABLE width="85%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="shichuangCreateEdit" validate="true" method="post">
             
        			 	<TR>
					  <TD width="15%" class="listheadline">标题:</TD>
					  <TD width="35%" class="listline" colspan="3">
					  <s:hidden name="exist"/>
					  <s:hidden name="type"/>
					  <s:textfield name="shichuang.title" size="25" maxlength="150" cssClass="text1" required="true"/></TD>
					</TR>
                 
	 			 <TR>
					  <TD width="15%" class="listheadk   line">链接指向:</TD>
					  <TD width="35%" class="listline" colspan="3">
					  <s:hidden name="exist"/>
					  <s:textfield name="shichuang.url" size="35" maxlength="150" cssClass="text1" required="true"/></TD>
					</TR>
					
					<s:if test="type.equals(\"dh\")">
						 <TR>
					  <TD width="15%" class="listheadline">显示顺序:</TD>
					  <TD width="35%" class="listline" colspan="3">
					  <s:hidden name="exist"/>
					  <s:textfield name="shichuang.order" size="5" maxlength="150" cssClass="text1" required="true"/>
					  (请输入数字)
					  </TD>
					</TR>
					</s:if>
					
        			 	<TR>
					  <TD width="15%" class="listheadline">备注说明:</TD>
					  <TD width="35%" class="listline" colspan="3">
				
					  <s:textarea name="shichuang.remarks" rows="4" cols="40" cssClass="text1"/></TD>
					</TR>
                  
                    
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
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
