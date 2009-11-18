<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName }-文字类内容审核</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script language="javascript">
function approved(typeid){
  var msg=document.getElementById("themsg").value;
  if(typeid==2){
     if(msg=="请输入审核意见"||msg==""){
        alert("请输入您的审核意见");
        return false;
     }else{
         document.getElementById("domessageid").value=msg;
         document.getElementById("simpleapproveid").value=2;
        document.form1.submit();
     }
  }else{
        if(msg=="请输入审核意见"||msg==""){
           msg="审核通过";
        }
        document.getElementById("domessageid").value=msg;
        document.getElementById("simpleapproveid").value=1;
        document.form1.submit();
  }
  return true;
}
function focusit(obj){
 if(obj.value=="请输入审核意见")
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
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;文字类内容审核</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD  bgColor=#F9F9F7>
                 <TABLE  border=0 align=center width="580" cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7"  class="listline">
                       <TR class="listline">
                     
                      <TD >
                      <INPUT name="back"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
                    <s:if test="diary.statusid!=0">
                        <INPUT name="approved"  type=button   class="botton" onClick="approved(1)" value="审核通过">
                        <INPUT name="notapprove"  type=button   class="botton" onClick="approved(2)" value="审核不通过">
                         <s:textfield name="themsg" id="themsg" size="35" value="请输入审核意见" onfocus="focusit(this)"/>
                  </s:if>
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
                  
                   文章标题：${diary.title}
              </div></TD>
					</TR>
                    <TR>
					  <TD width="20%" class="listheadline">上传者名字:</TD>
					  <TD width="35%" class="listline" colspan="3">
                     <s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[diary.userid]"/>
                      </TD>
					
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">所属分组:</TD>
					  <TD width="35%" class="listline">
                        <s:property value="@com.sxit.content.util.CommonDatas@DIARYTYPE[diary.diarytypeId]"/>
                      </TD>
					  <TD width="20%" class="listheadline">
                     当前状态：
                      </TD>
					  <TD width="35%" class="listline">

 <s:property value="@com.sxit.content.util.CommonDatas@STATUS[diary.statusid]"/>		
                      </TD>
					</TR>
	 			 
	 			 	<TR>
	 			 	  <TD class="listheadline">文章新增时IP:</TD>
	 			 	  <TD class="listline">
                     ${diary.createIp}</TD>
					  <TD class="listheadline">新增时间:</TD>
					  <TD class="listline">
                       <s:date name="diary.createTime" format="yyyy-MM-dd HH:mm"/>	
                      </TD>
	 			 	</TR>
                    	<TR>
					  <TD width="20%" class="listheadline" colspan="4" align="center">内容:</TD></TR>
                      <TR>
					  <TD width="20%" class="listline" colspan="4">
                      <div>
                      ${diary.content}
                      </div>
                      </TD></TR>
            </TABLE>   
            <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					
					</TR>
                
	 			 
                  
            </TABLE>         
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
<s:form name="form1" method="post" action="contentApproveSubmit" >
<s:hidden name="businessId"/>
<s:hidden name="serviceId"/>
<s:hidden name="history.domessage" id="domessageid"/>
<s:hidden name="history.simpleapprove" id="simpleapproveid"/>
</s:form>
