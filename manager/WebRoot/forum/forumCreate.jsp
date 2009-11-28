<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
<head>
<title>新增帖子</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：新建帖子 </td>
  </tr>
</table>
<s:form action="forumCreate" method="post" name="form1">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"><div align="left">　</div>
        </td>
        </tr>
        	<s:if test="mainforumid==0">
        	
        	
        	  <tr>
        <td class="tab_content" align="right">类型：</td>
        <td colspan="2" class="tab_content1" align="left">
        	
         	     	<s:select name="forumadd.thetype" list="@com.changpeng.common.CommonDatas@ForumType"/>
         	   
         	     	</td>
        </tr>	 
      <tr>
        <td class="tab_content" align="right">内容标题：        </td>
        <td colspan="2" class="tab_content1" align="left">
        	
         	     	<s:textfield name="forumadd.title" size="40"/>
         	     	<font color=red>(*不能为空,请输入)</font>
         	     	</td>
        </tr>
      </s:if>
       <tr>
        <td width="15%" class="tab_content" align="right">正文：
        </td>
        <td width="85%" colspan="2" class="tab_content1" align="left">
        
         		           <FCK:editor id="forumadd.forumcontent" height="300" width="95%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
							>
									${forumadd.forumcontent}
						</FCK:editor><br/><font color="red">(*不能为空,请输入)</font>
         	<s:hidden name="mainforumid"/>
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
   
    </td>
  </tr>
</table>
 </s:form>
</body>
</html>