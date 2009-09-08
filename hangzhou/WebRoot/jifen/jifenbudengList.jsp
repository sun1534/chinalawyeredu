<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-积分补登</title>
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
		window.location.href="jifenbudeng!input.pl";
	}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function deleteit(budengid){
  if(confirm('您确定要删除这个补登的积分吗')){
 
  	 window.location.href="jifenbudengDelete.pl?budengid="+budengid;
  }
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置：积分补登
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenbudengList" name="form1" method="post">
<s:hidden name="pageNo" />
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
            	姓名:<s:textfield name="username" size="10"/>
            	执业证号:<s:textfield name="lawerno" size="15"/> 
            	事务所: <s:textfield name="officename" size="15"/>
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
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >补登内容标题</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">律师姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">执业资格证号</TD>
     
        <TD align="center" background="../imagesa/top-bg1.gif">学分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">积分日期</TD>
          <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
	<s:if test="lawer!=null">
      <TR>
        <TD class="tab_content" align="left" title="点击修改"><a href="jifenbudeng!input.pl?budengid=${budengid}">&nbsp;&nbsp;${title}</a></TD>
        <TD class="tab_content" align="center">${lawer.sysGroup.groupname}</TD>
        <TD class="tab_content" align="center">${lawer.username}</TD>
        <TD class="tab_content" align="center">
        	${lawerno}</TD>
        <TD class="tab_content" align="center">${xuefen}</TD>
         <TD class="tab_content" align="center">${createtime}</TD>
         <TD class="tab_content" align="center"><a href="#" onclick="deleteit(${budengid})">【删除】</a></TD>
      </TR> 
      </s:if>
     </s:iterator> 
     
      
      <tr bgcolor="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       <input type="button" name="budeng" value="补登积分" onclick="getAdd()"/>
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>