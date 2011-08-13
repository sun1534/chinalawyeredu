<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 
<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link href="css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
</head>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
      <div align="left">
        <table width="80%" border="0" cellspacing="0" cellpadding="4">
          <TR>
            <td width="60"><div align="center"><img src="../images/arr.gif" width="13" height="13"></div></TD>
            <td width="97%"><span class="sort-title">信息管理>>查看信息</span></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor="#F9F9F7">
              <table width="690" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="70" background="images2/newtitle.jpg"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
                      <tr>
                        <td width="24%">&nbsp;</td>
                        <td width="76%" class="newtit"><span class="sort-title">${info.tinfType.name}</span></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CDDDD8">
                      <tr>
                        <td bgcolor="#FFFFFF"><TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1>
                          <TR >
                            <TD colSpan=12 align=center>&nbsp;</TD>
                          </TR>
                          <TR>
                            <TD class="pt9-18">文件编号：${info.filenumber}</TD>
                            <TD class="pt9-18" align="right">&nbsp;部门:${info.department.departmentname}</TD>
                           </TR>
                        </TABLE>
                          <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 >
                            <TBODY>
                              <TR >
                                <TD align="center" class="posttitle">${info.infoname}</TD>
                              </TR>
                              <TR >
                                <TD align="center">
                                  <div class="postinfo">作者:${info.createuser.username}&nbsp;&nbsp; 时间:<s:date name="info.createtime" format="yyyy-MM-dd"/> &nbsp;&nbsp; </div></TD>
                              </TR>
                              <TR valign="top">
                                <TD height="60" class="post"><br>
                                  ${info.content}</TD>
                              </TR>
                            </TBODY>
                          </TABLE>
                          <br>
                          <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                            <s:if test="infoAttachList.size>0">
                            <TR>
                              <TD colspan="4">
                                <div class="postinfo">附件:</div></TD>
                            </TR>
                            <TR>
                              <TD colspan="3" class="post" align="left"> <s:iterator value="infoAttachList" status="status">
                                <table style="display:inline" border="0" cellspacing="0" cellpadding="0">
                                  <tr >
                                    <td align="center"  class="pt9-18" onMouseOver="style.backgroundColor='#EEEEEE'"
                                  onmouseout="style.backgroundColor='#FFFFFF'"> <a href="download.action?attachid=${attachid }" title="点击下载附件"><img src="images2/doc.gif" width="40" height="40" border="0"><br>
                                    ${filename}</a></td>
                                  </tr>
                                </table>&nbsp;
                              </s:iterator> </TD>
                            </TR>
                            </s:if>
                          </TABLE>
                          <div align="center">
                            <input name="back" type=button class="mask" onClick="javascript:history.back(-1)" value="返回">
                            <br>
                          </div></td>
                      </tr>
                  </table></td>
                </tr>
              </table></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<s:form name="backForm" action="${nextpage}" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
</BODY>
</HTML>
