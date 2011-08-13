<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<%
/**
* 
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<style type="text/css">
<!--
div.tabs {
	font-size: 9pt;
    background: White;
    color: #799C38;
    margin: 0;
    padding: 0;
	background: transparent;
	border-collapse: collapse;
	border-bottom-color: #999999;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	padding: 0.5em 0em 0em 2em;
	white-space: nowrap;

}
div.personalBar {
    /* Bar with personalized menu (user preferences, favorites etc) */
    background: #EFEFEF;
    border-bottom-color: #EFEFEF;
    border-bottom-style: solid;
    border-bottom-width: 5px;
    color: #799C38;
    padding-top: 0px;
    padding-right: 3px;
	padding-bottom: 0px;
    /*text-transform: lowercase;*/
}
/* Rule 3 of ../css/plone.css */
A:link {	
	COLOR: #000000;	
	BACKGROUND-COLOR: transparent;
	TEXT-DECORATION: none
} 
div.tabs a {
    /* The normal, unselected tabs. They are all links */
    background: transparent;
    border-color: #999999;
    border-width: 1px; 
    border-style: solid solid none solid;
    color: #999999; 
    margin-right: 0.5em;
    padding: 0em 1.8em;
    padding-top: 0.1em;
    text-transform: lowercase;
}

div.tabs a.selected {
    /* The selected tab. There's only one of this */
    background: #EFEFEF;
    border: 1px solid #999999;
    border-bottom: #EFEFEF 1px solid;
    color: #000000;
    font-weight: normal;
    padding-top: 0.1em;
}
div.tabs a.plain {
    /* The selected tab. There's only one of this */
	background: #ffffff;
    color: #999999;
    font-weight: normal;
    padding-top: 0.1em;
}
DIV.tabs A:hover {
	BORDER-LEFT-COLOR: #999999; BACKGROUND: #EFEFEF; BORDER-BOTTOM-COLOR: #EFEFEF; COLOR: #000000; BORDER-TOP-COLOR: #999999; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #999999
}
-->
</style>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="userselect.js"></script>
<script type="text/javascript">
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function assignTask(){
	if(noChecked()){
          alert("请选择需要分配的催收记录！");
          return false;
     }
     if (confirm("您确定要对该用户分配所选催收任务?")) {
          document.form1.action="nonlawtaskAssign.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}

function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
function query(){
 document.form1.action="caseAssignList.action";
 document.form1.submit();
}
function assign(){
 document.form1.action="caseAssign.action";
 document.form1.submit();
}
</script>
</HEAD>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;我的案件分配记录</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <tr>
  	<td>

  	</td>
  </tr>
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
        <s:form name="form1" action="caseAssignList" method="POST">
    
    <div align="center">
    <DIV class="tabs" style="text-align:left">
     <A class="plain" href="caseAssignList.action">待分配案件列表</A>
     <A class="selected" href="caseAssignHistoryList.action">已分配案件列表</A>
     </DIV>
    <DIV class="personalBar"></DIV>
    
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        
      <!--  <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left" colspan="3">
                 <s:hidden name="statusid"/>
                 借款人：<s:textfield name="jiekuanren" size="10"/>
                 受理时间：<jscalendar:jscalendar name="thedate" format="%Y-%m-%d" showstime="false"/>
                 受理案号：<s:textfield name="contractno" size="10"/>
                 银行：<s:select name="bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="0" headerValue="全部"/>
                身份证号码：<s:textfield name="theidcard" size="10"/>
                <input name="submit11" type="button" class="mask"  value="查询" onClick="query()">
                      </TD>
                    </TR>
                      <TR>
                 <TD class="listline" align="right">
         请选择案件承办人：</TD>
            <TD class="listline" align="left" width="30"><s:select name="userid" listKey="comp_id.tsysUser.userid" listValue="comp_id.tsysUser.username" list="roleusers" id="userselsect"/>
               </TD>  
               <td class="listline" align="left"><input name="submitassign" type="button" class="mask"  value="分配案件" onClick="assign()">
                      </TD>
                    </TR>
                  </TBODY>
              </TABLE> </TD>
          </TR>-->
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>

     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      
                      <TD align=center>承办人</TD>
                      <TD align=center>分配时间</TD>
                      <TD align=center>委托银行</TD>
                      <TD align=center>支行</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>详情</TD>
                      </TR>
                      <s:iterator value="historylist" status="status">
                      <tr class=listline>
                    <!--     <TD align=center><img src="../images/arr_2.gif"></TD>-->
                    <td align="center">
                    
                    </td>
                     <TD><s:property value="@com.changpeng.lawcase.util.CommonDatas@USER_ID_NAME[assignedUserid]"/></TD>
                        <TD><s:date name="assigntime" format="yyyy-MM-dd HH:mm"/></TD>
                        <TD>${bank }</TD>
                        <TD>${bankbranch }</TD>
                        <TD>${ thedate}</TD>
                        <TD>${ jiekuanren.jiekuanren}</TD>
                        <TD>${ contractno}</TD>
                        <TD>${ jiekuanren.howmuch}</TD>
                        <TD align=center><a href="caseView.action?caseid=${caseid }">查看</a></TD>
                       </tr>
                      </s:iterator>
                   </TBODY>
               </TABLE>
             </TD>
           </TR>
    	${pagestring }
			
				</TABLE>
   </div>
   </s:form>
   </TD></TR>
   
  </TABLE>
</body>


</HTML>
