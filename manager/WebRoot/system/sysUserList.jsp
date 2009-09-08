<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>管理员列表</title>
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
function getAdd(){
	window.location.href="sysUserCreate!input.pl";
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
    <td height="23" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置： 管理员列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
	<s:form name="form1" action="sysUserList" method="post">
  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo" value="1"/>
        	 <s:textfield name="loginname" size="8" label="登录名"/>
             <s:textfield name="username" size="8" label="姓名"/> 
         
                 <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省(系统)级" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             </s:if>
            <s:else>
                  <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" label="律协" headerKey="0" headerValue="请选择"  onchange="getOffices(this.value)"/>
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
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr  class="list_tdfunc">
          <td height="24" align="right" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
		<tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建人员</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">所属部门</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">状态</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">角色</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD> 
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR class="list_td01">
        <!--<TD class="tab_content" align="center"><input type="checkbox" name="check" value="${userid}"/></TD>
         -->
        <TD align="center">${username}</TD>
        <TD align="center">${loginname}</TD>
        <TD align="center">${createuser}</TD>
        <TD align="center">${sysGroup.groupname}</TD>
        <TD align="center">
        <s:if test="status==0">
        激活        
        </s:if>
        <s:else>
        <span class="statusClass">冻结</span> </s:else>        
        </TD>
     <TD align="center">${sysRole.rolename}</TD>
          
        <TD align="center"><a href="sysUserEditPre.pl?userid=${userid}">修改</a></TD>
        <TD align="center"><a href="sysUserDelete.pl?check=${userid}">【删除】</a></TD>
      </TR>
      </s:iterator>
      <tr class="list_td01">
        <td  colspan="9" align="center">&nbsp;       </td>
      </tr>
       <tr class="list_tdfunc">
          <td height="24" colspan="9"  align="center"  >
         <INPUT type="button" onClick="return getAdd()"  value=" 新增管理员 " name="addbutton" class="button">
&nbsp;          </td>
        </tr>
    </table>
      </td>
  </tr>
    </s:form>
</table>
</body>
</html>