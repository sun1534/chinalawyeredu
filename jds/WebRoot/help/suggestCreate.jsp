<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<%
/**
 * <p>功能： 创建suggest</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
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
              <td width="97%"><span class="sort-title">问题与建议&gt;&gt;新增问题与建议</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="suggestCreate" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 		
	 			 	<TR>
						<TD width="15%" class="listheadline">标题:</TD>
						<TD width="35%" class="listline"><s:textfield name="suggest.title"/></TD>
					</TR>
	 			 	<TR>
						<TD class="listheadline" colspan="2">问题建议:</TD>
					</TR>
	 			 	<TR>
						<TD class="listline" colspan="2">
						<FCK:editor id="info.content" height="350" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="Basic"	> 
		  		        </FCK:editor>
						</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="2" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
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
