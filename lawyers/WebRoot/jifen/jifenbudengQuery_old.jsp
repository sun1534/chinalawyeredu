<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}-积分查询</title>
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

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：您的补登积分
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenQuery" name="form1" method="post">
	  <tr>
    <td valign="top">	
    
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" width="35%">补登课程标题</TD>
       	
         <TD align="center" background="../imagesa/top-bg1.gif">是否现场课程</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">补登来源</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >积分时间</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">补登学分</TD>
          <TD align="center" background="../imagesa/top-bg1.gif">补登积分年限</TD>
           <TD align="center" background="../imagesa/top-bg1.gif">处理时间</TD>
           <TD align="center" background="../imagesa/top-bg1.gif">处理人员</TD>
      </tr>
      <s:set name="zongjifen" value="0"/>
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="left">${title}</TD>
            <TD class="tab_content" align="center">${islocalstr}</TD>
      <TD class="tab_content" align="left">
      <s:if test="budengfrom>0">
      自己申请
      </s:if>
      <s:else>
      律协补登
      </s:else>
      </TD>
        <TD class="tab_content" align="center"><s:date name="budengdate" format="yyyy-MM-dd"/></TD>
        <TD class="tab_content" align="center">${xuefen}</TD>
                <TD class="tab_content" align="center">${theyear}</TD>
                    <TD class="tab_content" align="center">${createtime}</TD>
      <TD class="tab_content" align="center">${createuser}</TD>
      </TR>
     </s:iterator> 
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>
</table>
</body>
</html>