<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-积分查询</title>
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
<script language="javascript" type="text/javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="arrange!input.pl?arrangetype=${arrangetype}";
}
function deleteit(arrangeid){
    	<s:if test="arrangetype==1">
		if(confirm("您确实要删除掉这个岗前培训吗，删除后报名信息也将全部删除？")){
		window.location.href="arrangeDelete.pl?arrangetype=${arrangetype}&arrangeid="+arrangeid;
		}
		</s:if>
		<s:else>
		if(confirm("您确实要删除掉这个活动安排吗，删除后报名信息也将全部删除？")){
		window.location.href="arrangeDelete.pl?arrangetype=${arrangetype}&arrangeid="+arrangeid;
		}
		</s:else>
}
</script>

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置：
    	<s:if test="arrangetype==1">
    		岗前培训
    	</s:if>
    	<s:else> 
    		活动安排
    	</s:else>
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="arrangeList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<!--
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
           	<div align="center"> 律师的培训积分</div>
           	<div align="left">
     
           </div>
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
    
          </td>
        </tr>
      </table>
      -->
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >活动通告</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >我要报名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">人数限制</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">活动截止</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">报名人数</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">参与事务所</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">报名状态</TD> 
               <s:if test="sysUser.rightList.contains(\"arrangezong\")">
       <TD align="center" background="../imagesa/top-bg1.gif">报名汇总</TD>
       </s:if>
        
                <s:if test="sysUser.rightList.contains(\"arrangeedit\")">
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
       </s:if>
            <s:if test="sysUser.rightList.contains(\"arrangedelete\")">
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
       </s:if>
      </tr>
      
<s:iterator value="arrangelist" status="stat">
      <TR>
        <TD class="tab_content" align="left" width="35%">
 &nbsp;&nbsp;
        <a href="arrangeView.pl?arrangeid=${arrangeid}">${title}</a>
        </TD>
        <TD class="tab_content" align="center">    
          <s:if test="canbaoming">
           <a href="arrangesignup!input.pl?arrangeid=${arrangeid}">我要报名</a>
          </s:if>
          <s:else> 
           &nbsp;
          </s:else>	
        </TD>
        <TD class="tab_content" align="center">${maxpersons}</TD>
        <TD class="tab_content" align="center">${arrangend}</TD>
        <TD class="tab_content" align="center">
${baomingrenshu}</TD>
         <TD class="tab_content" align="center">${shiwusuo}</TD>
        <TD class="tab_content" align="center">
        <s:if test="canbaoming">
         	正在报名
        </s:if>
        <s:else> 
         	报名结束
        </s:else>	
        </TD>
            <s:if test="sysUser.rightList.contains(\"arrangezong\")">
         <TD class="tab_content" align="center"><a href="arrangesignupList.pl?arrangeid=${arrangeid}">报名汇总</a>
         </TD>
         </s:if>
            <s:if test="sysUser.rightList.contains(\"arrangeedit\")">
         <TD align="center" class="tab_content"><a href="arrange!input.pl?arrangeid=${arrangeid}">修改</a></TD>
         </s:if>
         <s:if test="sysUser.rightList.contains(\"arrangedelete\")">
         <TD align="center" class="tab_content"><a href="#" onClick="deleteit(${arrangeid})">【删除】</a></TD>
         </s:if>
      </TR>
     </s:iterator> 
     
      
      <tr background-color="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
          <s:if test="sysUser.rightList.contains(\"arrangeadd\")">
       <input type="button" name="addit" value=" 新增 " onClick="getAdd()"/>
       </s:if>
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>