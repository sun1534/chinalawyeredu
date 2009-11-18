<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>商家订购产品之内容审核</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script language="javascript">

function fanye(str){
  document.viewForm.pageNo.value=str;
  document.viewForm.submit()
  return true;
}
function approved(typeid){
  var msg=document.getElementById("themsg").value;
 if(typeid==2){
     if(msg=="请输入审核意见"||msg==""){
        alert("请输入您的审核意见");
        return false;
     }else{
         document.getElementById("domessageid").value=msg;
         document.getElementById("simpleapproveid").value=typeid;
        document.form1.submit();
     }
  }else{
        if(msg=="请输入审核意见"||msg==""){
           msg="审核通过";
        }
        document.getElementById("domessageid").value=msg;
        document.getElementById("simpleapproveid").value=typeid;
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
		  <img src="../images/arr.gif" width="13" height="13">		 </div>	      </td>
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;商户订购具体内容审核</span></td>
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
                    <s:if test="publishcontent.statusid!=1">
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
					<TD colspan="4" class="listline">
					<div align="center" class="sort-title">
                
                    <div align="center">订购产品：<s:property value="@com.sxit.service.util.CommonDatas@ALLPRODUCTS[publish.productid]"/></div></TD>
					</TR>
                    
                
               	<TR>
               	 <TD width="20%" class="listheadline">
                       当前内容状态
                       </TD>
					  <TD width="35%" class="listline" colspan="3">
                  <s:if test="publishcontent.statusid==0">
                  待审核
                  </s:if>
                  <s:elseif test="publishcontent.statusid==1">
                  审核通过
                  </s:elseif>
                  <s:else>
                  审核未通过
                  </s:else>
                      </TD>
					</TR>
					     
	 			 	<TR>
					  <TD width="20%" class="listheadline">内容说明:</TD>
					  <TD width="35%" class="listline" colspan="3">
                  ${publishcontent.remarks }
                      </TD>
				
					</TR>
	 				<TR>
            </TABLE>   
            
         
            <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					

                    <TR>
					<TD colspan="4" class="listline">
                    <div align="left" class="sort-title">
             <s:if test="video!=null">
             视(音频)链接：${video.url}<br/>
             内容描述：${video.description}<br/>
             </s:if>
             <s:if test="photo!=null">
             内容描述：${photo.description}<br/>
             <img src="${resourcepath}${photo.pic}" width="300"/></s:if>
             <s:if test="diary!=null">
             ${diary.content}
             </s:if>
                  </div>                  </TD>
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
<s:form name="form1" method="post" action="orderContentApproveSubmit" >
<s:hidden name="contentId"/>
<s:hidden name="pageNo"/>
<s:hidden name="actionId"/>
<s:hidden name="history.domessage" id="domessageid"/>
<s:hidden name="history.simpleapprove" id="simpleapproveid"/>
</s:form>