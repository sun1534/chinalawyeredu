<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-信息管理></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script type="text/javascript" src="../js/jquery.js"></script>


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
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;信息新增</span></td>
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
                <TABLE width="95%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="newsCreate" validate="true" method="post" enctype="multipart/form-data">
             
        			 	<TR>
					  <TD width="15%" class="listheadline">标题:</TD>
					  <TD width="35%" class="listline" colspan="3">
					  <s:textfield name="info.infoname" size="35" maxlength="150" cssClass="text1" required="true"/></TD>
					</TR>
                 
	 			 	<TR>
						<TD class="listheadline">信息类别：</TD>
						<TD class="listline" colspan="3">
						
                     <s:select name="info.typeid" list="@com.sxit.info.util.CommonDatas@INFOTYPES"/>
                   
                        </TD>
                       <!--  <TD class="listheadline">发布到外网：</TD>
						<TD class="listline">
   <s:checkbox name="info.isweb"/>
                     
                        </TD>-->
					</TR>
					  <TR>
					  <TD width="15%" class="listheadline">新闻广告图片:</TD>
					  <TD width="35%" class="listline" colspan="3">
	
					  <s:file name="upload" size="15"/>
					  </TD>
					</TR>
                  <TR>
						<TR>
						<TD class="listheadline">起始时间：</TD>
						<TD class="listline">
                    <jscalendar:jscalendar name="info.start"/>
                        </TD>
                        <TD class="listheadline">终止时间：</TD>
						<TD class="listline">
    <jscalendar:jscalendar name="info.end"/>
                     
                        </TD>
					</TR>
                   	<TR>
						<TD class="listline" colspan="4" align="center">
						     <FCK:editor id="info.content" height="300" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									imageBrowserURL="/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
				imageUploadURL="/editor/filemanager/upload/simpleuploader?Type=Image"
						>
									${info.content}
						</FCK:editor>
			<span class="cR">不能为空</span>
						
						</TD>
						
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
