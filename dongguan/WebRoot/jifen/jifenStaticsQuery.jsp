<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-参数设置</title>
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
function getAdd(){
	window.location.href="sysParameterCreate!input.pl";
}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置：积分统计
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenStaticsQuery" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
          <s:hidden name="pageNo"/>
积分年限:<s:select name="year" list="years"/> 
	分类:<s:select name="isdabiao" list="#{'0':'全部','1':'已达标','2':'未达标','3':'未培训'}"/>
	 	姓名:<s:textfield name="username" size="10"/>
            	执业证号:<s:textfield name="lawerno" size="15"/> 
            		
            	事务所: 
            	<s:if test="officenameinput">
            	<s:textfield name="officename" size="15"/>
            	</s:if>
            		<s:else>
 
            			<s:textfield name="officename" size="15" readonly="true"/>
            	</s:else>
        	   <s:submit value=" 查 询 "/>
          </td>
        </tr>
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" align="center">
<b> 当前查询条件下：达标人数【${jifenstatics.dabiaoshu}】 未达标人数【${jifenstatics.weidabiao}】 未培训人数【${jifenstatics.weipeixun}】</b>
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">现场积分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">网上积分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">文本课件</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">补登积分</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">未到扣分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">总积分</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">是否达标</TD>
            <TD align="center" background="../imagesa/top-bg1.gif">明细</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center"  title="查看律师信息"><a href="../system/lawyerView.pl?userid=${id.userid}">${id.username}</TD>
        <TD class="tab_content" align="left">&nbsp;&nbsp;${id.groupname}</TD>
        <TD class="tab_content" align="center">
        	   	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.xianchang)"/>
        </TD>
       <TD class="tab_content" align="center">
     
       	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.video)"/>
       	</TD>
     <TD class="tab_content" align="center">
     
       	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.doc)"/>
       	</TD>
         <TD class="tab_content" align="center">
         		<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.budeng)"/>
         	</TD>
           <TD class="tab_content" align="center">
           	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.koufen)"/>
          </TD>
             <TD class="tab_content" align="center">
             		<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.zongjifen)"/>
             		</TD>
               <TD class="tab_content" align="center">
               	<s:if test="id.zongjifen!=0&&id.zongjifen<dabiaofen">
                		 <font color="blue">未达标</font>
               	</s:if>
               	<s:elseif test="id.zongjifen==0"> 
               		未培训
               	</s:elseif>
                <s:else><font color="red">已达标</font>
                </s:else>
                
               	</TD> 
               	<TD class="tab_content" align="center"><a href="../jifen/jifenQuery.pl?fromwhere=fromwhere&userid=${id.userid}&year=${year}" title="查看积分明细">查看</TD>
      
               	</TR>
        </s:iterator> 
     
      
      <tr background-color="#F1F1ED">
        <td  colspan="7" align="center">&nbsp;
    
  
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="left" background="../imagesa/login_bg1.gif" >
     <ul>
<li>总计培训课程【${lessoncnt}】，其中有【${onlinelessoncnt}】门课程提供了视频等影像资料。</li>
   <li>
       有【${localecnt}】位律师参加了现场培训，
       有【${video}】位律师观看了在线视频，
       有【${wenbenkejian}】位律师观看了文本课件
 </li>
<!--<li>有【0】位律师使用了网上报名，其中有【0】位因未到被扣分；有【3】位律师参与了课程评价。</li>
-->
<li>
       有【${budeng}】提供了外部培训记录，并获得学分
</li>
</ul>
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>