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
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript"> 
	function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

var _lawyerid=0;
	function changeCardNo(lawyerid,theact){
	_lawyerid=lawyerid;
	 var _url="../lawyersajax/lawyersChangeCardNo.pl";
	 var _cardno=document.getElementById("cardno"+lawyerid).value;
	 var _carddate=document.getElementById("carddate"+lawyerid).value;
	 var _systemno=document.getElementById("systemno"+lawyerid).value;
	 if(_systemno==""||_systemno.length==0){
	  alert("请输入会员编号,不能为空");
	  return ;
	  }
	  $.getJSON(_url, { "theact": theact,"lawyerid":lawyerid,"cardno":_cardno,"carddate":_carddate,"systemno":_systemno,"now":new Date().getTime()}, function(resp){
  
       //返回的是json的对象，修改document.getElementById("grade2")
     if(resp.changeok=="true"){
         alert("会员编号信息修改成功");
      
         document.getElementById("cardno"+_lawyerid).value=resp.cardno;
            document.getElementById("systemno"+_lawyerid).value=resp.systemno;
             document.getElementById("carddate"+_lawyerid).value=resp.carddate;
         
      }else if(resp.changeok=="repeat"){
       alert("这个会员编号已经被使用,使用人员为:"+resp.lawyername);
      } else if(resp.changeok=="systemnoexist"){
       alert("这个会员编号没有和卡号的对应信息,请联系技术人员");
      }
      else{
         alert("会员编号修失败,请联系管理员");
      }
  
   });
	  
      
	}

	
	function queryit(str){
  document.form1.resultType.value="cardnolist";
  document.form1.submit();
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
	
	</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置： 会员管理&gt;&gt;律师卡号管理
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="lawyersList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
            <s:hidden name="pageNo"/>
               <s:textfield name="lawyername" size="10" label="姓名"/> 
                <s:textfield name="lawyerno" size="15" label="执业证号"/>
               <s:textfield name="cardno" size="12" label="卡号"/> 
               <s:textfield name="systemno" size="15" label="会员编号"/> 
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
        <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[theoffice]"/></TD>
        <TD class="tab_content" align="center">${lawyername}</TD>
        <TD class="tab_content" align="center">${lawyerno}</TD>
        <TD class="tab_content" align="center" title="不需要直接修改,随着会员编号的变动而变动"><input value='${cardno}' id="cardno${lawyerid}" size="10"/></TD>
        <TD class="tab_content" align="center"><input value='${systemno}' id="systemno${lawyerid}" size="10"/></TD>
       
        <TD class="tab_content" align="center"><input value='${carddate}' id="carddate${lawyerid}" size="10"/></TD>
       
        <TD class="tab_content" align="center"><a href="#" onclick="changeCardNo('${lawyerid}','change')">修改</a>
        <a href="#" onclick="changeCardNo('${lawyerid}','clear')" title="清除这个人的卡号信息">清除</a>
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