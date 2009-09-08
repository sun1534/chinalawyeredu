<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="articlesCreateEdit!input.pl?type=${type}";
}
function deleteit(articleid){
  var type=${type};
  var ss="重要通知";
  
  if(type==1){
    type="系统帮助";
  }else{
      ss="重要通知";
  }
  if(confirm("您确实要删除掉这个"+ss+"吗？")){
     window.location.href="articlesDelete.pl?articleid="+articleid+"&type=${type}";
  }
} 

	function setshouye(articlesid,toshouye){

	 var _url="../articlesajax/setshouye.pl";
	   $.getJSON(_url, { "articlesid": articlesid,"toshouye":toshouye,"now":new Date().getTime()}, function(json){
        alert(json.msg);
	    if(json.changeok=="ok"){
	     	window.location.href="articlesList.pl?type=1";
	    } 
      
	});
	}

	function submitForm(articleid,listorder){
	  if(event.keyCode==13){
  	     changeorder(articleid,listorder) ;
    }
}
	function changeorder(articlesid,listorder){
	 var _url="../articlesajax/changeorder.pl";

     $.getJSON(_url, { "articlesid": articlesid,"listorder":listorder,"now":new Date().getTime()}, function(json){
        alert(json.msg);
      
	});
	}

</script>

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：
    	<s:if test="type==1">
    		重要通知
    	</s:if>
    	<s:else> 
    		系统帮助
    	</s:else>
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="articlesList" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<!--
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
           	<div align="center"> 律师的培训积分</div>
           	<div align="left">
     
           </div>
          </td>
        </tr>
      </table>
      -->
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
    ${page.pageView}
          </td>
        </tr>
      </table>
   
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23" align="center" background="../imagesa/top-bg1.gif" >内容标题</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建人</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建时间</TD>
        <s:if test="type==1">
         <TD align="center" background="../imagesa/top-bg1.gif">发布部门</TD>
        </s:if>
        <TD align="center" background="../imagesa/top-bg1.gif">状态</TD>
        <s:if test="type==1">
         <TD align="center" background="../imagesa/top-bg1.gif">首页</TD>
            </s:if>
            <s:else>
             <TD align="center" background="../imagesa/top-bg1.gif">显示顺序</TD>
            </s:else>
        
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
        	
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="left">&nbsp;
       
        	<a href="articlesView.pl?articleid=${articleid}">${title}</a>
        </TD>
        <TD class="tab_content" align="center">${createuser}</TD>
        <TD class="tab_content" align="center">${createtime}</TD>
           <s:if test="type==1">
         <TD class="tab_content">
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[thegroup]"/>
         </TD>
        </s:if>
        <TD class="tab_content" align="center">${status==0?"发布":"禁用"}</TD>
          <TD align="center" class="tab_content">
 <s:if test="type==1">
          <s:if test="mygroup==null||(mygroup!=null&&mygroup.groupid==thegroup)">
         <s:if test="toshouye">
         <a href="#" onClick="setshouye(${articleid},'false')"><span id="shouye${articleid}">取消首页</span></a>
         </s:if>
         <s:else>
             <a href="#" onClick="setshouye(${articleid},'true')"><span id="shouye${articleid}">设置到首页</span></a>
         </s:else>
        
        </s:if>
        <s:else>&nbsp;
        </s:else>
            </s:if>
            <s:else>
         
             	<input type="text" value="${listorder}" size="2" onblur="changeorder(${articleid},this.value)">
      
            </s:else>
            
              </TD>
     <TD align="center" class="tab_content">
     <s:if test="mygroup==null||(mygroup!=null&&mygroup.groupid==thegroup)">
     <a href="articlesCreateEdit!input.pl?articleid=${articleid}&type=${type}">修改</a>
     </s:if>
      <s:else>
     &nbsp;
     </s:else>
     </TD>
     <TD align="center" class="tab_content">

      <s:if test="mygroup==null||(mygroup!=null&&mygroup.groupid==thegroup)">
     <a href="#" onClick="deleteit(${articleid})">【删除】</a>
     </s:if>
     <s:else>
     &nbsp;
     </s:else>
     </TD>
      </TR>
     </s:iterator> 
     
      
      <tr background-color="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       <input type="button" name="addit" value=" 新增 " class="button" onClick="getAdd()" />
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>