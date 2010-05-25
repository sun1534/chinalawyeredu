<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-积分查询</title>
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

function queryit(str){
  document.form1.submit();
}

function exportit(str){
  document.form1.submit();
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：律师积分查询
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenQueryStatic" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
          <s:hidden name="pageNo"/>
		积分年度:<s:select name="year" list="jifentime.years" headerKey="0" headerValue="全部"/> 
	 	姓名:<s:textfield name="lawyername" size="10"/>
         执业证号:<s:textfield name="lawyerno" size="20"/> 
        培训方式:<s:select name="learnmode" list="@com.changpeng.jifen.util.CommonDatas@LEARNMODE" headerKey="0" headerValue="全部"/>
        	   <input type="button" name="query" value=" 查 询 " onclick="queryit()"/>
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
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">课程名称</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">培训方式</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">培训日期</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">获得积分</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">总积分</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">积分年度</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">积分最新时间</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center"  title="查看律师信息">
        <a href="../lawyers/lawyerView.pl?userid=${lawyerid}">${lawyername}
        </TD>
        <TD class="tab_content" align="left">&nbsp;&nbsp;
        <a href="officeJifenStatic.pl?selectoffice=${cityid }"><s:property value="@com.changpeng.system.util.CommonDatas@groups[officeid]"/></a>
      
        </TD>
         <TD class="tab_content" align="center"> ${title}</TD>
       	<TD class="tab_content" align="center"> <s:property value="@com.changpeng.jifen.util.CommonDatas@LEARNMODE[learnmode]"/></TD>
     	<TD class="tab_content" align="center">${pxdate}</TD>
        <TD class="tab_content" align="center">${pxxf}</TD>
        <TD class="tab_content" align="center">${zongjifen}</TD>
       <TD class="tab_content" align="center">${theyear}</TD>
        <TD class="tab_content" align="center">${lastupdate}</TD>
          </TR>
        </s:iterator> 
     
      
       <tr style='background-color="#F1F1ED"'>
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