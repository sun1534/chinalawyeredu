<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-商户订购列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
function queryit(){
document.getElementById("totype").value="list";
document.form1.submit();
}
function exportit(){
document.getElementById("totype").value="excel";
document.form1.submit();
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;商户订购产品列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="tvConfirmList" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="left" colspan="16">
              	<s:hidden name="userRole"/>
                 	<s:hidden name="pageNo"/>
                  	订购人名称:<s:textfield name="userName" size="10"/>
                    订购产品: <s:select name="productid" list="@com.sxit.service.util.CommonDatas@ALLPRODUCTS" headerKey="-1" headerValue="全部"/>
                <s:if test="from!=null&&from.equals(\"publish\")">
                            <s:hidden name="statusid"/>
                     </s:if>
                    <s:else>
        
                      当前状态:<s:select name="statusid" list="@com.sxit.content.util.CommonDatas@PUBLISHSTATUS"headerKey="-1" headerValue="全部"/>
                 
                    </s:else>
                    <br/>
                    订购时间从:<jscalendar:jscalendar name="start"/> 
                  	到:<jscalendar:jscalendar name="end"/> &nbsp;
                  	排序:<s:select name="orderfield" list="#{'productid':'产品','channelid':'频道','userid':'用户','statusid':'状态','createtime':'订购时间'}"/>
                  	<s:select name="order" list="#{'asx':'顺序','desc':'逆序'}"/>
                  	<s:hidden name="totype" id="totype"/>
                  	  <input type="button" value="查询" class="botton" onclick="queryit()"/>
                  	  <input type="button" value="导出" class="botton" onclick="exportit()"/>
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                      <TD>产品名</TD>
                      <TD>发布频道</TD>
                      <TD>订购人</TD>
                     
                      <TD>当前状态</TD>
                      <TD>订购时间</TD>
                      <TD>费用</TD>
                        <s:if test="from!=null&&from.equals(\"publish\")">
                     <TD><a href="orderConentList.action?publishid=${id }">内容审核</a></TD>
                     </s:if>
                      <TD>处理</TD>               
                      
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                       <TD><s:property value="@com.sxit.service.util.CommonDatas@ALLPRODUCTS[productid]"/></TD>
                        <TD><s:property value="@com.sxit.service.util.CommonDatas@ALLCHANNELS[channelid]"/></TD>
                       <TD>
                              <s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[userid]"/>
                       </TD>    
                        <TD>  <s:property value="@com.sxit.content.util.CommonDatas@PUBLISHSTATUS[statusid]"/></TD>                                                                        
                        <TD>
                       <s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
                     </TD>
                       <TD>${fee }</TD>
                           <s:if test="from!=null&&from.equals(\"publish\")">
                     <TD><a href="tvContentDoView.action?actionId=view&id=${id }">内容审核</a></TD>
                     </s:if>
                        <TD>
                        	<%/*	PUBLISHSTATUS.put((short)0, "审批通过");
		PUBLISHSTATUS.put((short)1, "初订购");
		PUBLISHSTATUS.put((short)2, "待付费");
		PUBLISHSTATUS.put((short)3, "待审核");
		PUBLISHSTATUS.put((short)4, "审核未通过");
		PUBLISHSTATUS.put((short)5, "审核通过");
		PUBLISHSTATUS.put((short)99, "发布中");
		PUBLISHSTATUS.put((short)100, "业务到期");*/%>
                        
                        <s:if test="statusid==5">
                          <a href="tvContentDoView.action?actionId=publish&id=${id}">【发布】</a>
                        </s:if>
                        <s:elseif test="statusid==3||statusid==4">
                          <a href="tvContentDoView.action?actionId=confirm&id=${id}">【审核】</a>
                        </s:elseif>
                         <s:elseif test="statusid==99">
                          <a href="tvContentDoView.action?actionId=stop&id=${id}">【停止发布】</a>
                        </s:elseif>
                        <s:else>
                          <a href="tvContentDoView.action?actionId=view&id=${id}">【查看】</a>
                        </s:else>
                        
                        
                        </TD>         
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>
<!--
                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
           
                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value="新增">&nbsp;&nbsp;

                       </TD>
                    </TR>   -->
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>