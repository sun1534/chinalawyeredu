<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
<head>
<title>   <s:if test="type==1">
      	重要通知
      </s:if>
       <s:else> 
        	系统帮助
        </s:else></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：
     <s:if test="type==1">
      	重要通知新增/修改
      </s:if>
       <s:else> 
        	系统帮助新增/修改
        </s:else>
    </td>
  </tr>
</table>
<s:form action="articlesCreateEdit" method="post" name="form1">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"><div align="left">　</div>
        </td>
        </tr>
      <tr>
        <td width="20%" class="tab_content" align="right">内容标题：
        </td>
        <td width="80%" colspan="2" class="tab_content1" align="left">
        	<s:hidden name="articles.type"/>
        		<s:hidden name="type"/>
        	<s:textfield name="articles.title" size="40"/></td>
        </tr>
         <tr>
        <td width="20%" class="tab_content" align="right">是否发布：
        
        </td>
        <td width="80%" colspan="2" class="tab_content1" align="left">
      <s:select name="articles.status" list="#{'0':'发布','1':'禁用'}"/>
        </td>
       
  </tr>
       <s:if test="type==1">
        <tr>
        <td width="20%" class="tab_content" align="right">是否设置到首页：
        
        </td>
        <td width="80%" colspan="2" class="tab_content1" align="left">
        <s:checkbox name="articles.toshouye"/>
        </td>
       
  </tr>
       </s:if>
       <s:else>
        <tr>
               <td width="20%" class="tab_content" align="right">在首页显示顺序：
        
        </td>
        <td width="80%" colspan="2" class="tab_content1" align="left">
     <s:textfield name="articles.listorder" size="5"/><font color="red">只能输入数字</font>
        </td>
          </tr>
       </s:else>
      
       <tr>
        <td class="tab_content" align="right">正文：
        </td>
        <td colspan="2" class="tab_content1" align="left">
           <FCK:editor id="articles.content" height="300" width="99%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									imageBrowserURL="../editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
									linkBrowserURL="../editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
									flashBrowserURL="../editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
									imageUploadURL="../editor/filemanager/upload/simpleuploader?Type=Image"
									linkUploadURL="../editor/filemanager/upload/simpleuploader?Type=File"
									flashUploadURL="../editor/filemanager/upload/simpleuploader?Type=Flash">
									${articles.content}
						</FCK:editor>
       	</td>
       </tr>
   <tr>
        <td class="tab_content1"></td>
        <td colspan="2" class="tab_content1">　
         
          <input type="submit" name="Submit" value=" 保存 "/>
          　 
          <input type="reset" name="Submit2" value=" 重置 "/>
          　
          <input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)">
          </td>
        </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>