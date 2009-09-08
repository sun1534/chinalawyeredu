<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-参数设置</title>
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
	window.location.href="sysParameterCreate!input.pl";
}
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
    	当前位置：参数设置
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="sysParameterList" name="form1" method="post">
<s:hidden name="pageNo" />
	  <tr>
    <td valign="top">	
    	<!--
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
          

          </td>
        </tr>
      </table>-->
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >参数名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >参数值</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">参数说明</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
     
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
      
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center">${paramname}</TD>
        <TD class="tab_content" align="center">${paramvalue}</TD>
        <TD class="tab_content" align="center">${comments}</TD>
        <TD class="tab_content" align="center"><a href="sysParameterEdit!input.pl?paramname=${paramname}">修改</a></TD>
        <TD class="tab_content" align="center"><a href="sysParameterDelete.pl?paramname=${paramname}">【删除】</a></TD>
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
          <input type="button" name="submit2" value="新增参数" onClick="getAdd()"/>
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>