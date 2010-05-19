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

<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

function getCities(vallll){

  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：积分补登申请列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenbudengApplyList" name="form1" method="post">
<s:hidden name="pageNo" />
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
            	姓名:<s:textfield name="lawyername" size="6"/>
            	执业证号:<s:textfield name="lawyerno" size="17"/> 
            	申请状态:<s:select name="status" list="#{'-1':'全部','0':'暂未处理','1':'申请通过','2':'申请未通过','3':'申请已取消'}"/>
           <s:if test="datavisible.provinceview">
               <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省级律协" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
          </s:if>
            <s:else>
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" label="市级律协" headerKey="0" headerValue="请选择"  onchange="getOffices(this.value)"/>
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
        <TD align="center" background="../imagesa/top-bg1.gif">所在事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">律师姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">申请时间</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">是否现场课程</TD>        
        <TD align="center" background="../imagesa/top-bg1.gif">补登学分</TD>            
        <TD align="center" background="../imagesa/top-bg1.gif">积分年度</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">积分日期</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">当前状态</TD> 
         <TD align="center" background="../imagesa/top-bg1.gif">处理时间</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">处理人</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
	
      <TR>
        <TD class="tab_content" align="left">&nbsp;&nbsp;${title}</TD>
        <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[officeid]"/></TD>
        <TD class="tab_content" align="center">${lawyername}</TD>
        <TD class="tab_content" align="center"><s:date name="applytime" format="yyyy-MM-dd HH:mm:ss"/></TD> 
        <TD class="tab_content" align="center">${islocalstr}</TD>
        <TD class="tab_content" align="center">${xuefen}</TD>
        <TD class="tab_content" align="center">${theyear}</TD>
         <TD class="tab_content" align="center">${budengdate}</TD>
          <TD class="tab_content" align="center">${statusStr}
       <s:if test="status==0">
      &nbsp;<a href="jifenbudengApplyHandle!input.pl?budengid=${budengid }">【处理】</a>
       </s:if>
       </TD>
       <TD class="tab_content" align="center">
       <s:if test="status==0">
      &nbsp;
       </s:if>
       <s:else>
       <s:date name="confirmtime" format="yyyy-MM-dd HH:mm:ss"/>
       </s:else>
       </TD>
        <TD class="tab_content" align="center">${confirmuser}</TD>
      </TR> 
     </s:iterator> 
     
      
      <tr bgcolor="#F1F1ED">
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