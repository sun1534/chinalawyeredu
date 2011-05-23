<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-卡号管理</title>
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
	function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

	function changeCardNo(userid){
	 var _url="../systemajax/lawerChangeCardNo.pl";
        var pars = 'userid='+userid+'&cardno=' + document.getElementById("cardno"+userid).value + '&carddate=' + document.getElementById("carddate"+userid).value;
     
            var myAjax = new Ajax.Request(
                    _url,
                    {method: 'post', parameters: pars, onComplete: showResponse}
                    );
      
	}
	function showResponse(original){
		  var resp = eval('(' + original.responseText + ')');
     //返回的是json的对象，修改document.getElementById("grade2")
     if(resp.changeok=="true"){
         alert("卡号信息修改成功");
      } else{
         alert("卡号信息修失败,请联系管理员");
      }
	}
	</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置： 卡号管理
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="lawerCardNoList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
           <s:hidden name="pageNo"/>
            	姓名:<s:textfield name="username" size="10"/>
            	执业证号:<s:textfield name="lawerno" size="15"/> 
            		卡号:<s:textfield name="cardno" size="10"/>
            			事务所: <s:textfield name="groupname" size="15"/>
        	   
<s:submit value=" 查 询 "/>

          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >所属事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">执业证号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">卡号</TD>
     
        <TD align="center" background="../imagesa/top-bg1.gif">发卡日期</TD>
    
        <TD align="center" background="../imagesa/top-bg1.gif">修改卡号信息</TD>
      
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center">${sysGroup.groupname}</TD>
        <TD class="tab_content" align="center">${username}</TD>
        <TD class="tab_content" align="center">${lawerno}</TD>
        <TD class="tab_content" align="center"><input value='${cardno}' id="cardno${userid}"/></TD>
        <TD class="tab_content" align="center"><input value='${carddate}' id="carddate${userid}"/></TD>
        <TD class="tab_content" align="center"><a href="#" onclick="changeCardNo('${userid}')">修改</a></TD>
      </TR>
     </s:iterator> 
     
      
      <tr background-color"#F1F1ED>
        <td  colspan="7" align="center">
    &nbsp;
  
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
          &nbsp;    	
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>