<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>律师列表</title>
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
	window.location.href="lawyersCreateEditPre.pl";
}

function getAddBatch(){
window.location.href="lawyersCreateBatch!input.pl";
}
function passwdReset(userid){
   var _url="../systemajax/passwordReset.pl";
   $.getJSON(_url, { "userid": userid,"islawyer":"true","now":new Date().getTime()}, function(json){
     alert(json.changeok);
   });
}
function deleteit(userid){
	  if(confirm("您确定要删除这个律师的信息吗？")){
	    window.location.href="lawyersDelete.pl?lawyerid="+userid+"&pageNo=${pageNo}";
	  }
}
function getCities(vallll){

  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
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
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}

function queryit(str){
  document.form1.resultType.value="list";
  document.form1.submit();
}

function exportit(str){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置： 律师列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
	<s:form name="form1" action="lawyersList" method="post">
  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo"/>
               <s:textfield name="lawyername" size="10" label="姓名"/> 
                <s:textfield name="lawyerno" size="15" label="执业证号"/>
               <s:textfield name="cardno" size="12" label="卡号"/> 
               <s:textfield name="certno" size="15" label="身份证号"/> 
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
             <s:hidden name="resultType"/>
        	   <input type="button" name="query" value=" 查 询 " onclick="queryit()"/>
        	   <input type="button" name="export" value=" 导 出 " onclick="exportit()"/>
          </td>
        </tr>
       
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr  class="list_tdfunc">
          <td height="24" align="right" >
       ${page.pageView }
           
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1"bgcolor="#EDEDED">
		<tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">身份证号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">执业证号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">会员编号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">卡号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">执业日期</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">所属事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">密码重置</TD>
          <s:if test="canupd">
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
        </s:if>
        <TD align="center" background="../imagesa/top-bg1.gif">转所</TD>
        <s:if test="candel">
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD> 
        </s:if>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR class="list_td01">
      
        <TD align="center">${lawyername}</TD>
        <TD align="center">${certno}</TD>
        <TD align="center">${lawyerno}</TD>
        <TD align="center">${systemno}</TD>
        <TD align="center">${cardno}</TD>
        <TD align="center">${zhiyedate} </TD>
        <TD align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[theoffice]"/></TD>
        <TD align="center"><a href="#" onClick="passwdReset('${lawyerid}')">密码重置</a></TD>
        <s:if test="canupd">
        <TD align="center"><a href="lawyersCreateEditPre.pl?lawyerid=${lawyerid}">修改</a></TD>
        </s:if>
        <TD align="center"><a href="lawyersChangeOfficePre.pl?lawyerid=${lawyerid}">转所</a></TD>
        <s:if test="candel">
        <TD align="center"><a href="#" onclick="deleteit(${lawyerid})">【删除】</a></TD>
        </s:if>
      </TR>
      </s:iterator>
      <tr class="list_td01">
        <td  colspan="10" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
       <tr class="list_tdfunc">
          <td height="24" colspan="10"  align="center"  >
          <s:if test="canins">
         <INPUT type="button" onClick="return getAdd()"  value=" 新增律师 " name="addbutton" class="button">
          <INPUT type="button" onClick="return getAddBatch()"  value=" 批量新增律师 " name="addbutton" class="button">
         </s:if>         
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