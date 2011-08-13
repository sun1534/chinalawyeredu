<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 
<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link href="css.css" rel="stylesheet" type="text/css">
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
                        <td width="76%" class="newtit">最新公告</td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CDDDD8">
                      <tr>
                        <td bgcolor="#EEF2F0"><table width="90%"  border="0" align="center" cellpadding="2" cellspacing="1">
                            <tr>
                              <td height="38" align="left">                                文件编号:${bulletin.filenumber}</td>
                            </tr>
                            <tr>
                              <td height="38" align="center" valign="bottom"> <strong class="newtit">${bulletin.bulletinname} </strong> </td>
                            </tr>
                            <tr>
                              <td ><div align="center">${bulletin.createtime} </div></td>
                            </tr>
                            <tr>
                              <td class="text1"><img src="images2/line1.gif" width="610" height="2"></td>
                            </tr>
                            <tr>
                              <td class="text1">&nbsp;</td>
                            </tr>
                            <tr>
                              <td class="text1">${contents}</td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td><div align="right">发布部门:${bulletin.department.departmentname}</div></td>
                            </tr>
                            <tr>
                              <td align="left" bgcolor="#E0E7E3">附件:</td>
                            </tr>
                            <tr>
                              <td align="left" bgcolor="#FFFFFF"><table width="215" border="0" cellspacing="1" cellpadding="0">
                                  <s:iterator value="tinfBuluploadList" status="status">
                                  <tr align="center">
                                    <td><img src="images2/doc.gif" width="60" height="60"></td>
                                  </tr>
                                  <tr align="center">
                                    <td><s:a href="download.action?buluploadid=${buluploadid}">${filename}</s:a></td>
                                  </tr>
                                  </s:iterator>
                              </table></td>
                            </tr>
                            <tr>
                              <td height="50" align="center"><input name="back" type=button class="mask" onClick="javascript:history.back(-1)" value="返回"></td>
                            </tr>
                        </table></td>
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
