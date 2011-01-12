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
    	当前位置：论坛 &gt;&gt;查看帖子
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >

	  <tr>
    <td valign="top">	
    	
    	
    	
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">   
      <tr>
       	<TD width="82%" height="23"  align="left" background="../imagesa/top-bg1.gif" >发布时间:${themainforum.createtime}，发帖人:${themainforum.createuser}</TD>
        <TD width="18%"  align="right" background="../imagesa/top-bg1.gif" >
        <input type="button" value="回 帖" class="button" onclick="javascript:window.location.href='forumCreate!input.pl?mainforumid=${themainforum.forumid}'"/>&nbsp;&nbsp;
      
        </TD>
      </tr>

      <TR>
        <TD colspan="2" align="left" class="tab_content">${themainforum.forumcontent}</TD>
        </TR>
   
      <tr background-color="#F1F1ED">
        <td  colspan="2" align="center">&nbsp;       </td>
      </tr>
        
    </table>
       	<s:if test="forumlist!=null">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">   
        <s:iterator value="forumlist" status="stat">
      <tr>
       	<TD width="4%" height="23"  align="left" background="../imagesa/top-bg1.gif" ></TD>
        <TD width="78%"  align="left" background="../imagesa/top-bg1.gif" >回复时间:${createtime}，回帖人:${createuser}</TD>
        <TD width="18%"  align="right" background="../imagesa/top-bg1.gif" >
        <input type="button" value="回 帖" class="button" onclick="javascript:window.location.href='forumCreate!input.pl?mainforumid=${themainforum.forumid}'"/>&nbsp;&nbsp;
        
        </TD>
      </tr>

      <TR>
        <TD align="left" class="tab_content"></TD>
        <TD colspan="2" align="left" class="tab_content">${forumcontent}</TD>
        </TR>
   
      <tr style="background-color=#F1F1ED">
        <td  colspan="3" align="center">&nbsp;       </td>
      </tr>
      </s:iterator>
    </table>
        </s:if>
        
    </td>
  </tr>





</table>
</body>
</html>