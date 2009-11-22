<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-SGSN列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 $("#tableOrder").tablesorter();
 });
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
						<span>您所在是位置:</span><a>网络质量</a>＞<em>资源列表</em>＞<em>SGSN信息列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sgsnList" method="POST">	
			     <s:hidden name="resultType"/>
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
                                
								 <td>APN编号：<s:textfield name="apnid" cssClass="txt" size="10"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
								<td><input type="button" class="btnSubmit" value="设置为重点APN" onclick="setit()"/></td>
							
								</tr>
							</tbody>
						</table>
				  </div> -->
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                       <th>SGSN编号</th>
                       <th>所在IP</th>
                       <th>BSC个数</th>
                       <th>时隙数</th>
                       <th>带宽（K）</th>
                       <th>华为/爱立信</th>
                       <th>最后更新时间</th>
                       </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="resultList" status="status">
                        <tr>
                        
                         <td><a title="点击查看归属BSC/RNC列表" href="bscList.action?sgsnid=${sgsnid }">${sgsnid}</a></td>
                          <td>${sgsnip}</td>
                             <td>${bsccount}</td>
                                <td>${slotcount}</td>
                                  <td>${capacity}</td>
                                
                          <td>${sgsntype }</td>
                          <td><s:date name="lastupdate" format="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="7" class="fright">
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

