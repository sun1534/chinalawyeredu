<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-申请转所</title>
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

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置：律师申请转所列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="officeChangeApplyList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo"/>
        	
               <s:textfield name="lawyername" size="12" label="律师姓名"/> 
              <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省律协" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
              </s:if>
            <s:else>
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" label="市律协" headerKey="0" headerValue="请选择"  onchange="getOffices(this.value)"/>
              </s:if>
           <s:else>
              <s:hidden name="datavisible.cityid"/>
            </s:else>
                 <s:if test="datavisible.officeview">
             <s:select name="datavisible.officeid" id="office" list="datavisible.officelist" listKey="groupid" listValue="groupname" label="事务所" headerKey="0" headerValue="请选择"/>
              </s:if>
            <s:else>
              <s:hidden name="datavisible.officeid"/>
            </s:else>
             <s:submit value=" 查 询 " cssClass="button"/>
          </td>
        </tr>
      </table>
    	
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
      <TD height="23" align="center" background="../imagesa/top-bg1.gif">律师</TD> 
       <TD height="23" align="center" background="../imagesa/top-bg1.gif">申请人</TD> 
            <TD height="23" align="center" background="../imagesa/top-bg1.gif">原所在事务所</TD> 
     
        <TD align="center" background="../imagesa/top-bg1.gif">申请转所事务所</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">申请时间</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">当前状态</TD>
          <TD align="center" background="../imagesa/top-bg1.gif">处理时间</TD>
             <TD align="center" background="../imagesa/top-bg1.gif">处理人</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center"><a href="lawyerView.pl?lawyerid=${lawyerid }">${lawyername }</a></TD>
         <TD class="tab_content" align="center">${applyname }</TD>
       <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[oldoffice]"/></TD>
         <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[newoffice]"/></TD>
       <TD class="tab_content" align="center"><s:date name="applyTime" format="yyyy-MM-dd HH:mm:ss"/></TD>
       <TD class="tab_content" align="center">${statusStr}
       <s:if test="status==0">
      &nbsp;<s:if test="canhandle"><a href="officeChangeHandle!input.pl?id=${id }">【处理】</a></s:if>
       </s:if>
       </TD>
       <TD class="tab_content" align="center">
       <s:if test="status==0">
      &nbsp;
       </s:if>
       <s:else>
       <s:date name="confirmTime" format="yyyy-MM-dd HH:mm:ss"/>
       </s:else>
       </TD>
        <TD class="tab_content" align="center">${confirmusername}</TD>
   
      </TR>
     </s:iterator> 
     
      
      <tr style="background-color=#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
          	
       </td>
      </tr>
     
    </table>
       <!--  	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       <input type="button" name="addforum" value="申请转所" onclick="getAdd()"/>
          </td>
        </tr>
      </table>    -->
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>