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
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
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
        var pars = 'articlesid='+articlesid+'&toshouye='+toshouye;
     
            var myAjax = new Ajax.Request(
                    _url,
                    {method: 'post', parameters: pars, onComplete: showResponse}
                    );
      
	}
	function showResponse(original){
		  var resp = eval('(' + original.responseText + ')');
     //返回的是json的对象，修改document.getElementById("grade2")
       alert(resp.msg);
	 if(resp.changeok=="ok"){
	  	window.location.href="articlesList.pl?type=1";
	 //   	alert(document.getElementById("shouye"+resp.articlesid).innerText );
	/*   if(resp.toshouye=="true")
	    {
	    
	     document.getElementById("shouye"+resp.articlesid).innerText="取消首页";
	    }
	   else{
	      document.getElementById("shouye"+resp.articlesid).innerText="设置到首页";
	      }*/
	 } 
      
	}
	function submitForm(articleid,listorder){
	 // e=e||window.event;
	  if(event.keyCode==13){
  	     changeorder(articleid,listorder) ;
    }
}
	function changeorder(articlesid,listorder){
	 var _url="../articlesajax/changeorder.pl";
        var pars = 'articlesid='+articlesid+'&listorder='+listorder;
     
            var myAjax = new Ajax.Request(
                    _url,
                    {method: 'post', parameters: pars, onComplete: showResponse2}
                    );
      
	}
	function showResponse2(original){
		  var resp = eval('(' + original.responseText + ')');
     //返回的是json的对象，修改document.getElementById("grade2")
	
       alert(resp.msg);
	}
</script>

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
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
        <TD class="tab_content" align="center">${status==1?"发布":"禁用"}</TD>
          <TD align="center" class="tab_content">
 <s:if test="type==1">
        
         <s:if test="toshouye">
         <a href="#" onClick="setshouye(${articleid},'false')"><span id="shouye${articleid}">取消首页</span></a>
         </s:if>
         <s:else>
             <a href="#" onClick="setshouye(${articleid},'true')"><span id="shouye${articleid}">设置到首页</span></a>
         </s:else>
        
            </s:if>
            <s:else>
         
             	<input type="text" value="${listorder}" size="2" onblur="changeorder(${articleid},this.value)" onkeydown="submitForm(${articleid},this.value)">
      
            </s:else>
              </TD>
     <TD align="center" class="tab_content"><a href="articlesCreateEdit!input.pl?articleid=${articleid}&type=${type}">修改</a></TD>
     <TD align="center" class="tab_content"><a href="#" onClick="deleteit(${articleid})">【删除】</a></TD>
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