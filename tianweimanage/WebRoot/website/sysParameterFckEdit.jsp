<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}></title>
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
  <!-- <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">广告管理&gt;&gt;广告新增修改</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>-->
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="99%"  border=0 align=center cellPadding=1 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="sysParameterFckEdit" validate="true" method="post" enctype="multipart/form-data">
                <TR bgcolor="#CCCCCC">
                      <TD colspan="2" align="left">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
        			 	<TR>
					<s:hidden name="paramname"/>
					  <TD width="100%" class="listline" colspan="3">
					   <FCK:editor id="paramvalue" height="500" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									imageBrowserURL="/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
				imageUploadURL="/editor/filemanager/upload/simpleuploader?Type=Image"
						>
									${paramvalue}
						</FCK:editor>
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
