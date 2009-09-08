<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">
	function getAdd(){
		window.location.href="forumCreate!input.pl";
	}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function deleteit(forumid){
if(confirm("确定要删除掉这个信息吗")){
  window.location.href="forumDelete.pl?mainforumid="+forumid;
  return true;
  }
 return false;

}

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置：论坛
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="forumList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
             <s:hidden name="pageNo"/>
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >内容标题</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >创建者</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">回复数</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">最近更新时间</TD>
       <s:if test="hasright">
         <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
        </s:if>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="left">&nbsp;&nbsp;<a href="forumView.pl?mainforumid=${forumid}">${title}</a></TD>
        <TD class="tab_content" align="center">${createuser}</TD>
        <TD class="tab_content" align="center">${replycount}</TD>
        <TD class="tab_content" align="center">
        	${lastupdate}
        </TD>
      <s:if test="hasright">
     	<TD class="tab_content" align="center"><a href="#" onclick="deleteit('${forumid}')">删除</a></TD>
    </s:if>
      </TR>
     </s:iterator> 
     
      
      <tr background-color="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
          	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       <input type="button" name="addforum" value="新增帖子" onclick="getAdd()"/>
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>