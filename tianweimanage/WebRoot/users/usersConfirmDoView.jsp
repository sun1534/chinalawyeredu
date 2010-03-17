<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<s:head /> 
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script language="javascript">
function approved(typeid){
  var msg=document.getElementById("themsg").value;
  if(typeid==2){
     if(msg=="请输入认证意见"||msg==""){
        alert("请输入您的认证意见");
        return false;
     }else{
         document.getElementById("domessageid").value=msg;
         document.getElementById("simpleapproveid").value=2;
        document.form1.submit();
     }
  }else{
        if(msg=="请输入认证意见"||msg==""){
           msg="认证通过";
        }
        document.getElementById("domessageid").value=msg;
        document.getElementById("simpleapproveid").value=1;
        document.form1.submit();
  }
  return true;
}
function focusit(obj){
 if(obj.value=="请输入认证意见")
    obj.value="";
}
</script>
</HEAD>
<BODY>
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;用户认证</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD  bgColor=#F9F9F7>
                 <TABLE width="580" align=center  border="0" cellPadding="3" cellSpacing="1" bgcolor="#F9F9F7" >
                    <TR class="listline">
                     
                      <TD >
                     
                       最终认证方式:<s:select name="approveType" list="@com.sxit.users.util.CommonDatas@APPROVETYPE"/><br/>
                           认证意见:<s:textfield name="themsg" id="themsg" size="35" value="请输入认证意见" onfocus="focusit(this)"/>
                       
				      </TD>
                      <TD >
                      
                       <INPUT name="back"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
                      <INPUT name="approved"  type=button   class="botton" onClick="approved(1)" value="认证通过">
                      <INPUT name="notapprove"  type=button   class="botton" onClick="approved(2)" value="认证不通过">
                   
                      </TD>
                    </TR>
                </TABLE>
			
                  </TD>
          </TR>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
              
              <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					<TD colspan="4" class="listline"><div align="center" class="sort-title">
                    <s:if test="user.userRole==2">
                    商户名称：                    </s:if>
                    <s:else>
                    用户姓名                    </s:else>
                    ${user.userName} </div></TD>
					</TR>
                    
                    <TR>
					  <TD width="20%" class="listheadline">申请认证方式:</TD>
					  <TD width="35%" class="listline">
					
                      <s:property value="@com.sxit.users.util.CommonDatas@APPROVETYPE[user.approveType]"/>
                     </TD>
					  <TD width="20%" class="listline" colspan="2">
                      <s:if test="user.approveType==1"></s:if>                      </TD>
					</TR>
                     <TR>
					  <TD width="20%" class="listheadline">企业名称:</TD>
					  <TD width="35%" class="listline">
				${user.sign }
                     </TD>
					  <TD width="20%" class="listline" colspan="2">
                      <s:if test="user.approveType==1"></s:if>                      </TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">登录账号:</TD>
					  <TD width="35%" class="listline">${user.loginName}</TD>
					  <TD width="20%" class="listheadline">
                         <s:if test="user.userRole==1">
                      身份证号码:                      </s:if>
                      <s:else>营业执照编号                      </s:else>                      </TD>
					  <TD width="35%" class="listline">
 <s:if test="user.userRole==1">
                      ${user.cardno}
                      </s:if>
                      <s:else>  ${user.entno}
                      </s:else>                      </TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">联系手机:</TD>
					  <TD width="35%" class="listline" >
					   ${user.mobile}
					  </TD>
					 <TD width="20%" class="listheadline">当前状态:</TD>
					  <TD width="35%" class="listline">
					    <s:property value="@com.sxit.users.util.CommonDatas@STATUS[user.status]"/>
					  </TD>
					</TR>
					
	 			 	<TR>
	 			 	  <TD class="listheadline">注册IP:</TD>
	 			 	  <TD class="listline">
                     ${user.regIp}</TD>
					  <TD class="listheadline">注册时间:</TD>
					  <TD class="listline">
					  <s:date name="user.regTime" format="yyyy-MM-dd HH:mm"/>
					
				 <br></TD>
	 			 	</TR>
            </TABLE>   
            
            <s:if test="user.userRole==1">
            <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					<TD colspan="4" class="listline">
                    <div align="center" class="sort-title">
                  用户详细信息 
                  </div>
                  </TD>
					</TR>
                
	 			 	<TR>
					  <TD width="20%" class="listheadline">QQ:</TD>
					  <TD width="35%" class="listline"> ${detail.qq}</TD>
					  <TD width="20%" class="listheadline">
                       MSN:</TD>
					  <TD width="35%" class="listline">
 ${detail.msn}
                      </TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">所用邮箱:</TD>
					  <TD width="35%" class="listline">${detail.email}</TD>
					 <TD width="20%" class="listheadline">家庭电话:</TD>
					  <TD width="35%" class="listline">${detail.phone}</TD>
					</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">邮编:</TD>
	 			 	  <TD class="listline">
                     ${detail.postcode}</TD>
					  <TD class="listheadline">地址:</TD>
					  <TD class="listline">
                      ${detail.userAddress}
                      </TD>
	 			 	</TR>
                  <!--   	<TR>
	 			 	  <TD class="listheadline">兴趣爱好:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.interest}</TD>
	 			 	</TR>
                    <TR>
	 			 	  <TD class="listheadline">喜欢的书籍:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.books}</TD>
	 			 	</TR>
                    <TR>
	 			 	  <TD class="listheadline">喜欢的音乐:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.musics}</TD>
	 			 	</TR>
                    <TR>
	 			 	  <TD class="listheadline">喜欢的电影:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.movies}</TD>
	 			 	</TR>
                    <TR>
	 			 	  <TD class="listheadline">喜欢的游戏:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.games}</TD>
	 			 	</TR>
                    <TR>
	 			 	  <TD class="listheadline">喜欢的运动:</TD>
	 			 	  <TD class="listline" colspan="3">
                     ${detail.sports}</TD>
	 			 	</TR>
                  -->
            </TABLE>       
            </s:if>  
              <br>
                <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
               
                  <TR>
                    <TD colspan="4" class="listheadline">处理意见:</TD>
                  </TR>
                  <TR>
                    <TD width="5" class="listheadline">&nbsp;</TD>
                    <TD colspan="3" class="listline"> -----------------------------------------------<br>
                        <s:iterator value="dolist" status="status"> 
                        <s:if test="domessage!=null"> ${dousername} 于 ${dotime}<br>
>>${domessage}<br>
      -----------------------------------------------<br>
                      </s:if> </s:iterator> </TD>
                  </TR>
                
                </TABLE>
                <p>&nbsp;</p></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="form1" method="post" action="usersApproveSubmit" >
<s:hidden name="businessid"/>
<s:hidden name="userId"/>
<s:hidden name="history.domessage" id="domessageid"/>
<s:hidden name="history.simpleapprove" id="simpleapproveid"/>
</s:form>
