<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-产品列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="productCreateEdit!input.action";
}
function deleteInfo(typeid){
   if(confirm('您确定要删除这个产品吗?'))
     window.location.href="productDelete.action?productid="+typeid;
   return false;
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;产品列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="productList" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="left" colspan="16">
              
                 	
   
                  		<s:hidden name="pageNo"/>
     <s:select name="producttype" list="@com.sxit.service.util.CommonDatas@PRODUCTTYPE" headerKey="-1" headerValue="全部" label="产品类型："/>
      
                  	  <input type="submit" value="查询" class="botton">
                  	
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                      <TD>产品名称</TD>
                      <TD>价格</TD>
                      <TD>折扣率</TD>
                       <TD>计价单位</TD>
                      <TD>面向用户</TD> 
                      <TD>最大文字内容</TD>
                      <TD>最大视频数</TD>
                      <TD>面向音频数</TD>
                      <TD>最大图片数</TD>   
                        <TD>状态</TD>  
                       <TD>修改</TD>          
                        <TD>删除</TD>                    
                      
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                       <TD>${name}</TD>
                       <TD>${price }</TD>  
                       <TD>${feerate }%</TD>  
                       <TD>${unit }</TD>  
                       <TD><s:property value="@com.sxit.service.util.CommonDatas@PRODUCTTYPE[producttype]"/></TD>  
                       <TD>${maxcontent}</TD>
                       <TD>${maxvideo}</TD>
                       <TD>${maxaudio}</TD>
                       <TD>${maxpic}</TD>
                           <TD>
                             <s:if test="status==0">开放
                             </s:if>
                             <s:else>禁用
                             </s:else>
                           </TD>  
                        <TD><a href="productCreateEdit!input.action?productid=${id}">修改</a></TD>
                         <TD><a href="#" onclick="deleteInfo('${id}')">【删除】</a></TD>
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
              -->
                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value="新增">&nbsp;&nbsp;

                       </TD>
                    </TR>
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