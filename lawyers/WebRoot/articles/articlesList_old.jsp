<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}</title>
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
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

</script>

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：
    	<s:if test="type==1">
    		重要通知
    	</s:if>
    	<s:else> 
    		系统帮助
    	</s:else>
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="articlesList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<!--
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
           	<div align="center"> 律师的培训积分</div>
           	<div align="left">
     
           </div>
          </td>
        </tr>
      </table>
      -->
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
    ${page.pageView}
          </td>
        </tr>
      </table>
   
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23" align="center" background="../imagesa/top-bg1.gif" >内容标题</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建人</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建时间</TD>       
        <TD align="center" background="../imagesa/top-bg1.gif">查看</TD>
       
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="left">&nbsp;
        	<a href="articlesView.pl?articleid=${articleid}">${title}</a>
        </TD>
        <TD class="tab_content" align="center">${createuser}</TD>
        <TD class="tab_content" align="center">${createtime}</TD>
   
     <TD align="center" class="tab_content">
  		<a href="articlesView.pl?articleid=${articleid}">查看</a>
     </TD>

      </TR>
     </s:iterator> 
     
      
      <tr background-color="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
     
    </table>
 
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>