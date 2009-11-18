<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<jscalendar:head/>
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
  if(typeid==3){
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
function stoppublish(){
document.stopForm.submit();
}
function publish(){
document.getElementById("start").value=document.getElementById("startDate").value;
document.getElementById("end").value=document.getElementById("endDate").value;
document.publishForm.submit();
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
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;电视发布内容审核</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD  bgColor=#F9F9F7>
                 <TABLE  border=0 align=left cellPadding=4 cellSpacing=1 bgcolor="#F9F9F7">
                       <TR class="listline">
                      <TD >&nbsp;</TD>
                      <TD>
              
                      <INPUT name="back"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
                          <s:if test="actionId.equals(\"confirm\")">
                            <input name="approved"  type=button   class="botton" onClick="approved(1)" value="审核通过">
                              <input name="approved"  type=button   class="botton" onClick="approved(2)" value="审核通过并发布">
                            <INPUT name="notapprove"  type=button   class="botton" onClick="approved(3)" value="审核不通过">
                             
                       <br/>
                       审核意见：<s:textfield name="themsg" id="themsg" size="35" value="请输入审核意见" onfocus="focusit(this)"/>
                       </s:if>
                     <s:elseif test="actionId.equals(\"publish\")">
                        <INPUT name="notapprove"  type=button   class="botton" value="发布" onClick="publish()">
                        起始时间：<jscalendar:jscalendar name="startDate" id="startDate"/>
                        终止时间：<jscalendar:jscalendar name="endDate" id="endDate"/>
                        
                       </s:elseif>
   <s:elseif test="actionId.equals(\"stop\")">
   <INPUT name="notapprove"  type=button   class="botton" value="停止发布" onClick="stoppublish()">
 </s:elseif>				     
  </TD>
                      <TD >&nbsp;</TD>
                    </TR>
                </TABLE>
			
              </TD>
          </TR>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
              
              <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					<TD colspan="4" class="listline"><div align="center" class="sort-title">
                
                    <div align="center">订购产品：<s:property value="@com.sxit.service.util.CommonDatas@ALLPRODUCTS[publish.productid]"/></div></TD>
					</TR>
                    
                    <TR>
					  <TD width="20%" class="listheadline">订购人:</TD>
                     
					  <TD width="35%" class="listline">
					  <s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[publish.userid]"/>
                      </TD>
                       <TD width="20%" class="listheadline">订购时间:</TD>
					  <TD width="20%" class="listline">
                      <s:date format="yyyy-MM-dd HH:mm" name="publish.createtime"/>
                      </TD>
					</TR>
                    
	 			 	<TR>
					  <TD width="20%" class="listheadline">申请发布频道:</TD>
					  <TD width="35%" class="listline">
                      <s:property value="@com.sxit.service.util.CommonDatas@ALLCHANNELS[publish.channelid]"/>
                      </TD>
					  <TD width="20%" class="listheadline">
                       当前状态
                       </TD>
					  <TD width="35%" class="listline">
                      <s:property value="@com.sxit.content.util.CommonDatas@PUBLISHSTATUS[publish.statusid]"/>
                      </TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">付费时间:</TD>
					  <TD width="35%" class="listline" >
                       <s:date format="yyyy-MM-dd HH:mm" name="publish.feetime"/>
                      </TD>
                        <TD width="20%" class="listheadline">审批通过时间:</TD>
					  <TD width="35%" class="listline" >
                       <s:date format="yyyy-MM-dd HH:mm" name="publish.approvcetime"/>
                      </TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">展示开始时间:</TD>
					  <TD width="35%" class="listline" >
                       <s:date format="yyyy-MM-dd" name="publish.starttime"/>
                      </TD>
                        <TD width="20%" class="listheadline">展示结束时间:</TD>
					  <TD width="35%" class="listline" >
                       <s:date format="yyyy-MM-dd" name="publish.endtime"/>
                      </TD>
					</TR>
            </TABLE>   
            
         
            <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					<TD colspan="4" class="listline">
                    <div align="left" class="sort-title" style="display:inline;float:left">
                  具体内容(内容个数:${page.totalCount}) 
                  </div>
                  <div style="display:inline;float:right"class="sort-title">
          
              
             
              <s:if test="publish.statusid==99&&publish.userRole==2">

               【<s:if test="content.statusid==0">待审核</s:if>
               <s:elseif test="content.statusid==2">审核未通过</s:elseif>
               <s:else><a href="orderContentApproveSubmit!input.action?contentId=${content.id}&pageNo=${pageNo }&actionId=${actionId }">已审核</a>
               </s:else>】
               <s:if test="content.statusid!=1">
              <a href="orderContentApproveSubmit!input.action?contentId=${content.id}&pageNo=${pageNo }&actionId=${actionId }">内容审核</a>
             </s:if>
              </s:if>
                   ${page.contentPageView}
                  </div>                            
                  </TD>
					</TR>
                    <TR>
					<TD colspan="4" class="listline">
                    <div align="left" class="sort-title">
             <s:if test="video!=null">
             视(音频)链接：<a href="${resourcepath }${video.url}">${video.url}</a><br/>
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
                    <TD colspan="4" class="listheadline">产品订购处理意见:</TD>
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
<s:form name="form1" method="post" action="tvContentApproveSubmit" >
<input type="hidden" name="serviceId" value="${publish.id}"/>
<s:hidden name="history.domessage" id="domessageid"/>
<s:hidden name="history.simpleapprove" id="simpleapproveid"/>
</s:form>
<s:form name="publishForm" method="post" action="tvContentPublish" >
<input type="hidden" name="serviceId" value="${publish.id}"/>
<s:hidden name="start" id="start"/>
<s:hidden name="end" id="end"/>
</s:form>
<s:form name="stopForm" method="post" action="tvContentStop">
<input type="hidden" name="serviceId" value="${publish.id}"/>
</s:form>
<s:form name="viewForm" method="post" action="tvContentDoView">
<input type="hidden" name="id" value="${publish.id}"/>
<s:hidden name="actionId"/>
<s:hidden name="pageNo"/>
</s:form>