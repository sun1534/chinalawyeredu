<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-保障小区列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />

   <script type="text/javascript" src="../js/dialog.js"></script> 
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
 Array.prototype.clear=function(){  
    this.length=0;  
}
  $(document).ready(function() {
			$("#checkAll").click(selectAll);
			$("[name=check]").click(selectOne);
  });
 
  var arrayadd=new Array();
	
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

function savecell(){
var cellid=$("#cellid").val();
var now=new Date().getTime();
$.getJSON("../communicateguardajax/guardCellSet.action?cellid="+cellid+"&now="+now,function(json){
  if(json.isok=="1"){
    alert("保障小区新增成功");
    closeDialogBox('#dialogBoxAdd');
    window.location.reload();
  }else if(json.isok=="2"){
    alert("该小区编码在系统中不存在");
  }else if(json.isok=="4"){
    alert("该小区编码已经设置为保障小区");
  }else{
    alert("保障小区新增失败");
  }
});
}

function deletecell(){
  getChecked();
    if(arrayadd.length==0){
   alert("请选择要删除的保障小区");
   return;
  }
  if(!confirm("您确定要删除这些保障小区吗?")){
  return;
  }
var now=new Date().getTime();
$.getJSON("../communicateguardajax/guardCellSet.action?isdelete=1&cellid="+arrayadd.join()+"&now="+now,function(json){
  if(json.isok=="1"){
    alert("保障小区删除成功");
    window.location.reload();
  }else{
    alert("保障小区删除失败");
  }
});
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>通信保障</a>＞<em>保障小区列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="guardCells" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
                                    <s:hidden name="resultType"/>
								 <td>小区编号：<s:textfield name="cellid" cssClass="txt" size="10"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							 <td><input type="button" class="btnSubmit" value="新　增" onclick="showDialogBox('#dialogBoxAdd',500);"/> </td>
							 <td><input type="button" class="btnSubmit" value="删  除" onclick="deletecell()"/></td>
							
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate"> 
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="showDialogBox('#dialogBoxAdd',500);"/> 
						<input type="button" class="btnCancel" title="返 回" value="删　除"/> 
					</div> 
				-->
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                         <th title="点中全选">
                          <input type="checkbox" class="checkbox" name="checkAll"  id="checkAll"/>
                          </th>
                       <th>小区编号</th>
                       <th>小区名称</th>
                       <th>所属BSC/RNC</th>
                       <th>归属SGSN</th>
                       <th>所属城区</th>
                       <th>设置时间</th>
                          <th>设置人员</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                
                    <td align="center">
                      <input type="checkbox" class="checkbox" name="check" value="${cellid }"/>
                    </td>
                     <td>${cellid} </td>
                          <td>${cell.cellname}</td>
                          <td>${cell.bscrncid }</td>
                          <td><s:property value="@com.sxit.netquality.service.BasicSetService@BSC_SGSN[cell.bscrncid]"/></td>
                          <td>${cell.subarea}</td>
                          <td>${lastupdate}</td>
                          <td>${createusername}</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                <!--     <tfoot>
							<tr>
							   <td colspan="7" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  -->
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
<div class="Overdialog" id="Overdialog"><iframe></iframe></div> 
			<!-- 新增  --> 
			<div class="dialogBox" id="dialogBoxAdd"> 
				<div class="DialogWrap"> 
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">新增保障小区</div> 
						<table class="operateTabBox"> 
							<tbody> 
								<tr class="fEven"> 
									<td class="w120 fname">小区编码：</td> 
									<td class="fvalue">
									<input type="text" id="cellid" size="20" class="txt"/>
									</td> 
								</tr> 
								
							</tbody> 
						</table> 
					</div> 
					<div class="dialogBtn"><input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="savecell()"/>　<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div> 
				</div> 
			</div> 
</body>

</html>

