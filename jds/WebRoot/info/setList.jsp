<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看set列表</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
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
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="setDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function dis(b){
  var o = document.getElementById(b);
 
  o.style.display = (o.style.display=="block") ? "none" : "block";
  var i;
  var type='typeid';
  var object;
  for(i=1;i<=5;i++){
      type='typeid';
      type=type+i;
      object=document.getElementById(type);
  	  if(b!=type){
  	    object.style.display = "none";
  	  }
  }
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
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;权限设置</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="setDeletes.action" method="POST">
                <br>
                <TABLE width="60%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <TD width="35">选择</TD>
                      <TD width="25%" >类别</TD>
					  <TD >用户</TD>
                      <TD >权限</TD>
                      <TD >查看</TD>
                      </TR>
<s:iterator value="typelist" status="status">
                      <TR class="mask" onClick="dis('typeid${typeid}')">
					    <TD class="listtitle" ></TD>
                        <TD colspan="4"   class="listtitle"> &nbsp;${name}</TD>
                      </TR>

                      <TR >
					    <TD colspan="5">

	<div id="typeid${typeid}" style="display:
	<s:if test="type==0">
	<s:if test="typeid==1">block</s:if>
	<s:else>none</s:else>
	</s:if>
	<s:else>
	<s:if test="type==typeid">block</s:if>
	<s:else>none</s:else>
	</s:else>
	">
	
	<s:iterator value="tinfSets" status="status">
							 <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
							  <TR class=listline>
								<TD width="35" >
								<INPUT type="checkbox" value='${setid}' name="check"></TD>
								<TD width="25%"> </TD>
								<TD width="25%">${username} </TD>
								<TD width="20%">${powerid} </TD>
								<TD ><a href="setView.action?setid=${setid}&pagenumber=${pagenumber}&type=${typeid}">查看</a></TD>
							  </TR>
					    </TABLE>
	</s:iterator> 
	<s:if test="tinfSets==null||tinfSets.size==0"> 
						<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=0 bgcolor="#F9F9F7">
							  <TR class=listline>
								<TD class="errorMessage"><div align="center">这个类别还没记录!</div></TD>
							   </TR>
					    </TABLE>
	</s:if>
	</div>

						</TD>
                      </TR>
 </s:iterator>

<s:if test="setlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#F9F9F7" class="pt9-18">
                    <TD colSpan=12 >
<div align="center">
<input class="mask" type=button onClick="document.createForm.submit()" value="新增">&nbsp;&nbsp;
<input  class="mask" type=button onClick="return getDelete()" value="删除">
</div>
                     </TD>
                    </TR>
                  </TBODY>
              </TABLE>
<br>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="setList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="setCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="setSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
