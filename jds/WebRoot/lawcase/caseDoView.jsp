<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../imgs/tab.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.tab.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
.maskhandled {
	BORDER: #58656b 1px solid;BACKGROUND-COLOR: #eeeeee;color:blue;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">

</script>
</HEAD>
<BODY >
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
             <td width="97%"><span class="sort-title">诉讼案件&gt;&gt;案件信息查看</span></td>
            </tr>
          </table>
        </div>
    </TD>
  </TR>
  <TR>
    <TD valign="top" bgColor=#FFFFFF>
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD bgColor=#F9F9F7>
				<TABLE width="100%"  border=0 align=left cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                   <TR>
                     <TD width="5" >&nbsp;</TD>
                     <TD align="left" >
                       <INPUT name="back2"  type="button" class="botton" onClick="javascript:history.back(-1)" value="返回">
                       	<s:if test="issubmit"> 
                       		<input name="submit" type=submit class="mask" onClick="document.submitForm.submit()" value="提交">
                      	</s:if>   
                      	<s:if test="isedit">
                        	<input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="修改案件">
                      	</s:if>
                      	<s:iterator value="otherdoes" status="stat">
                     
                      	<s:if test="ishandled">
                          <input name="does" type="button" class="maskhandled" onClick="doesFormSubmit('${actionurl}',${actionid})" value="${actionname}"/>
                      	</s:if>
                      	<s:else>
                      	  <input name="does" type="button" class="mask" onClick="doesFormSubmit('${actionurl}',${actionid})" value="${actionname}"/>
                      	</s:else>
                      	</s:iterator>
                     <TD>&nbsp;</TD>
                     <TD align="right" > 
                     <s:if test="isdelete">
                     	<input name="edit2" type=submit class="mask" onClick="deleteit()" value="删除">
                     </s:if>
                     </TD>
                     <TD width="5" >&nbsp;</TD>
                    </TR>
                </TABLE>
                </TD>
            </TR>
            <TR>
				<TD bgColor=#F9F9F7>
			<div id="tab_list" style="text-align:left"></div>
                </TD>
            </TR>
            <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
            
              <TABLE width="90%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TR>
                    <TD colspan="4" class="listheadline">处理意见:</TD>
                  </TR>
                  <TR>
                    <TD width="5" class="listheadline">&nbsp;</TD>
                    <TD colspan="3" class="listline"> -----------------------------------------------<br>
                    <s:iterator value="dohistories" status="stat"> 
                
                   		${dousername} 于<s:date name="dotime" format="yyyy-MM-dd HH:mm:ss"/><br>>${domessage}<s:if test="remarks!=null&&!remarks.equals(\"\")">>${remarks}</s:if><br>
      -----------------------------------------------<br>
                 
                    </s:iterator> 
                    </TD>
                  </TR>
              </TABLE>
             </TD>
          </TR>
        </TABLE>
    </div>
    </TD>
  </TR>
</TABLE>
</BODY>
<s:form name="editForm" action="caselawCreateEdit!input.action" method="POST">
<s:hidden name="caseid"/>
</s:form>
<s:if test="issubmit">
<s:form name="submitForm" action="caseDoSubmit.action" method="POST">
<s:hidden name="caseid"/>
<s:hidden name="nodeid"/>
<s:hidden name="userid"/>
</s:form>
</s:if>
<s:form name="deleteForm" action="caseDelete.action" method="POST">
<s:hidden name="caseid"/>
</s:form> 
<s:form name="doesForm" action="" method="POST">
<s:hidden name="caseid"/>
<s:hidden name="userid"/>
<s:hidden name="nodeid"/>
<s:hidden name="actionid"/>
</s:form> 
 
<script language="JavaScript" type="text/JavaScript">
function deleteit(){
if(confirm("您确定要删除么?"))
  document.deleteForm.submit()
}
function doesFormSubmit(actionurl,actionid){
  document.doesForm.action=actionurl;
  document.doesForm.actionid.value=actionid;
  document.doesForm.submit();
}

</script>
<script>

var width=document.body.scrollWidth-40;

$(function(){
	$("#tab_list").Tab({items:[
	 {title:'基本信息',closed:false,icon:'',html:'',load:'caseView.action?caseid=${caseid}',callback:function(){}},
	 {title:'立案信息',closed:false,icon:'',html:'',load:'lianinfoView.action?caseid=${caseid }',callback:function(){}},
	 {title:'诉状材料',closed:false,icon:'',html:'',load:'susongView.action?caseid=${caseid }',callback:function(){}},
	 {title:'代理日志',closed:false,icon:'',html:'',load:'caselogList.action?caseid=${caseid }',callback:function(){}},
	 {title:'开庭信息',closed:false,icon:'',html:'',load:'kaitingInfoView.action?caseid=${caseid }',callback:function(){}},
	 {title:'执行材料',closed:false,icon:'',html:'',load:'zhixingView.action?caseid=${caseid }',callback:function(){}},
	 {title:'案件绩效信息',closed:false,icon:'',html:'',load:'caseJixiaoList.action?caseid=${caseid }',callback:function(){}},
	  {title:'案件执行阶段信息',closed:false,icon:'',html:'',load:'zhixingInfoAllView.action?caseid=${caseid }',callback:function(){}}
	],
	width:width,
	height:450,
	tabcontentWidth:width,
	tabScroll:false,
	tabWidth:66,
	tabHeight:25,
	tabScrollWidth:10,
	tabClassDiv:'benma_ui_tab',
	tabClass:'benma_ui_tab',
	tabClassClose:'tab_close',
	tabClassOn:'tab_item',
	tabClassInner:'tab_item',
	tabClassInnerLeft:'tab_item1',
	tabClassInnerMiddle:'tab_item2',
	tabClassInnerRight:'tab_item3',
	tabClassText:'text',
	tabClassScrollLeft:'scroll-left',
	tabClassScrollRight:'scroll-right'
	});
});
</script>
</HTML>
