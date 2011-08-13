<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看customer</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 

<script language="javascript">
function phoneDelete(phoneid){
	$.ajax({
           type:"GET",
           url:'../addressAjax/addressDelete.action',
           dataType:"json",
           data:"addressid="+phoneid,
           success:function(data){
		    if(data.result){
		      	alert("联系人号码删除成功。");
		      	location.reload();
		      }
           }
    });  
}
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">客户管理&gt;&gt;查看个人客户管理</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY >
	 			 	<TR>
						<TD width="15%" class="listheadline">姓名:</TD>
						<TD width="35%" class="listline">${customer.username}</TD>
					</TR>
						<TR>
						<TD width="15%" class="listheadline">身份证号:</TD>
						<TD width="35%" class="listline">${customer.idcard}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
						<TD width="35%" class="listline">${customer.company}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司地址:</TD>
						<TD width="35%" class="listline">${customer.compaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司邮编:</TD>
						<TD width="35%" class="listline">${customer.comppostcode}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">公司电话:</TD>
						<TD width="35%" class="listline">${customer.compphone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司传真:</TD>
						<TD width="35%" class="listline">${customer.compfax}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">籍贯:</TD>
						<TD width="35%" class="listline">${customer.nativeplace}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">家庭地址:</TD>
						<TD width="35%" class="listline">${customer.homeaddr}</TD>
					</TR>
					
					<TR>
						<TD width="15%" class="listheadline">家庭电话:</TD>
						<TD width="35%" class="listline">${customer.homephone}</TD>
					</TR>

	 			 	<TR>
						<TD width="15%" class="listheadline">手机1:</TD>
						<TD width="35%" class="listline">${customer.mobile1}</TD>
					</TR>	
					<TR>
						<TD width="15%" class="listheadline">手机2:</TD>
						<TD width="35%" class="listline">${customer.mobile2}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">生日:</TD>
						<TD width="35%" class="listline">${customer.birthday}</TD>
					</TR>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">公司邮箱:</TD>
						<TD width="35%" class="listline">${customer.compemail}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">个人邮箱:</TD>
						<TD width="35%" class="listline">${customer.personalemail}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">QQ:</TD>
						<TD width="35%" class="listline">${customer.qq}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">MSN:</TD>
						<TD width="35%" class="listline">${customer.msn}</TD>
					</TR>
	 			
	 			 
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="修改">
                    <!--   <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      -->
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">
                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div>
      <div align="center">
	<legend>
		<span style="font:bold">联系人列表</span>
	</legend>
	<table align="center" width="80%"  border=0 align=center style="FONT-SIZE:12px;">
		<TR class="listheadline">
			<td>号码</td>
			<td>姓名</td>
			<td>说明</td>
			<td>编辑</td>
			<td>删除</td>
		</tr>
		<s:iterator value="addressList" status="stat">
			<TR class=listline>
				<td><div id="phone${stat.index}">${phone}</div></td>
				<td>${username}</td>
				<td>${comments}</td>
				<td>
					<a href="../address/addressEdit!input.action?addressid=${addressid}&keepThis=true&TB_iframe=true&height=250&width=400" target="_blank" title="号码编辑" class="thickbox" >编辑</a></td>
				<td><a href="#" onclick="phoneDelete(${addressid})">删除</a></td>
			</tr>
		</s:iterator>	
	</table>
	    
    </div>
    </TD>
  </TR>
  <tr>
  
  </tr>
</TABLE>
   <s:form name="editForm" action="customer3Edit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   <s:hidden name="customerid" value="${customer.customerid}"/>
   </s:form>
   <!-- 
   <s:form name="deleteForm" action="customer2Delete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>-->
   <s:form name="backForm" action="customer2List.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
