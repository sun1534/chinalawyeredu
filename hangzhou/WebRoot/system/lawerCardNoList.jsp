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

var _userid=0;
	function changeCardNo(userid,theact){
	_userid=userid;
	 var _url="../systemajax/lawerChangeCardNo.pl";
	 var _cardno=document.getElementById("cardno"+userid).value;
	 var _carddate=document.getElementById("carddate"+userid).value;
	 var _systemno=document.getElementById("systemno"+userid).value;
	 if(_systemno==""||_systemno.length==0){
	  alert("请输入会员编号,不能为空");
	  return ;
	  }
       var pars = 'theact='+theact+'&userid='+userid+'&cardno=' + _cardno+ '&carddate=' + _carddate+ '&systemno=' + _systemno;
            var myAjax = new Ajax.Request(
                    _url,
                    {method: 'post', parameters: pars, onComplete: showResponse}
                    );
      
	}
	function showResponse(original){
		  var resp = eval('(' + original.responseText + ')');
     //返回的是json的对象，修改document.getElementById("grade2")
     if(resp.changeok=="true"){
         alert("会员编号信息修改成功");
      
         document.getElementById("cardno"+_userid).value=resp.cardno;
            document.getElementById("systemno"+_userid).value=resp.systemno;
               document.getElementById("carddate"+_userid).value=resp.carddate;
         
      }else if(resp.changeok=="repeat"){
       alert("这个会员编号已经被使用,使用人员为:"+resp.username);
      } 
      else{
         alert("会员编号修失败,请联系管理员");
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
            	执业证号:<s:textfield name="lawerno" size="10"/> 
            		卡号:<s:textfield name="cardno" size="10"/>
            			会员编号:<s:textfield name="systemno" size="10"/>
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
        <TD align="center" background="../imagesa/top-bg1.gif">会员编号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">发卡日期</TD>
    
        <TD align="center" background="../imagesa/top-bg1.gif">修改卡号信息</TD>
      
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center">${sysGroup.groupname}</TD>
        <TD class="tab_content" align="center">${username}</TD>
        <TD class="tab_content" align="center">${lawerno}</TD>
        <TD class="tab_content" align="center" title="不需要直接修改,随着会员编号的变动而变动"><input value='${cardno}' id="cardno${userid}" size="10"/></TD>
        <TD class="tab_content" align="center"><input value='${systemno}' id="systemno${userid}" size="10"/></TD>
        <TD class="tab_content" align="center"><input value='${carddate}' id="carddate${userid}" size="10"/></TD>
         
        <TD class="tab_content" align="center"><a href="#" onclick="changeCardNo('${userid}','change')">修改</a>
        <a href="#" onclick="changeCardNo('${userid}','clear')" title="清除这个人的卡号信息">清除</a>
        </TD>
      </TR>
     </s:iterator> 
     
      
      <tr style="background-color=#F1F1ED">
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