<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-BSC列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["bscid","sgsnid","nsvccount"];
 var field="${orderfield}";
var ascdesc="${ascdesc}";
  Array.prototype.clear=function(){  
    this.length=0;  
}
  $(document).ready(function() {
			$("#checkAll").click(selectAll);
			$("[name=check]").click(selectOne);
		});
	 var arrayall=new Array();
  var arrayadd=new Array();
	<s:iterator value="page.items">
       arrayall.push("${apnid }");
	</s:iterator>
  function selectAll() {
	var checked=$(this).attr('checked');
	var checkboxa = $("#checkForm :checkbox");
	$.each(checkboxa,function(){
	   if(!checked){
	    	$(this).attr('checked','');
		   	$(this).parent().parent().children().addClass("nomal");
			$(this).parent().parent().children().removeClass("current");
	   }else{
		    $(this).attr('checked','checked');
		    $(this).parent().parent().children().addClass("current");
		    $(this).parent().parent().children().removeClass("nomal");
	   }
	});
  }

		/*单选取值*/
		function selectOne(){
				if($(this).attr('checked')){
					$(this).parent().parent().children().addClass("current");
					$(this).parent().parent().children().removeClass("nomal");
				}else{
					$(this).parent().parent().children().addClass("nomal");
					$(this).parent().parent().children().removeClass("current");
					$("#checkAll").attr('checked','');
				}
      }
      
      
            function getChecked(){
         var checkbox = $("#checkForm :checkbox");
         arrayadd.clear();
	     $.each(checkbox,function(){
	      if($(this).attr('checked')){
		    arrayadd.push( $(this).attr("value"));
	       }
	    });
      }

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
  document.form1.resultType.value="list";
  document.form1.submit();
}
function setit(){
  getChecked();
  $.getJSON('../netqualityajax/setApnFocus.action?all='+arrayall.join()+'&selected='+arrayadd.join(),function(data){
        alert(data.msg);
  });
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>网络质量</a>＞<em>资源列表</em>＞<em>BSC/RNC信息列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="bscList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								 <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                 <s:hidden name="pageNo"/>
                                     <s:hidden name="resultType"/>
								 <td>BSC/RNC编号：<s:textfield name="bscid" cssClass="txt" size="10"/>&nbsp;</td>
								  <td>SGSN编号：<s:select name="sgsnid" list="@com.sxit.netquality.service.BasicSetService@ALL_SGSNS" headerKey="" headerValue="全部" listKey="value.sgsnid" listValue="value.sgsnid"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                        
                       <th><a onclick="orderByThis(document.form1,this)" id="bscid" title="点击排序">BSC/RNC编号</a></th>
                       <th>BSC/RNC名称</th>
                       <th><a onclick="orderByThis(document.form1,this)" id="nsvccount" title="点击排序">GB链路数量</a></th>  
                       <th><a onclick="orderByThis(document.form1,this)" id="sgsnid" title="点击排序">所属SGSN</a></th>
                       <th>最后更新时间</th>
                       </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="resultList" status="status">
                        <tr>
                        
                         <td><a title="点击查看归属小区列表" href="cellList.action?bscid=${bscrncid }">${bscrncid}</a> </td>
                          <td>${name}</td>
                          <td>${nsvcount }</td>
                           <td>${sgsnid }</td>
                           
                          <td><s:date name="lastupdate" format="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  
                    </table>
			  </div>

			 <div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

