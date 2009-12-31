<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-会议小区列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
   <script type="text/javascript" src="../js/dialog.js"></script> 
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.blockUI.js"></script> 
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
var lac=$("#lac").val();
var cellkey=lac+"-"+cellid;
var flowview=$("#flowview").val();
var timeview1=$("#timeview1hour").val()+$("#timeview1min").val();
var timeview2=$("#timeview2hour").val()+$("#timeview2min").val();
var userview=$("#userview").val();
//alert(cellkey+"=="+flow+"=="+timespan+"="+usercount);
var now=new Date().getTime();
$.getJSON("../cellconferenceajax/conferenceCellSet.action?timeview1="+timeview1+"&timeview2="+timeview2+"&userview="+userview+"&flowview="+flowview+"&cellkey="+cellkey+"&now="+now,function(json){
  if(json.isok=="1"){
    alert("监控会议小区新增成功");
    closeDialogBox('#dialogBoxAdd');
    window.location.reload();
  }else if(json.isok=="2"){
    alert("该小区编码在系统中不存在");
  }else if(json.isok=="4"){
    alert("该小区编码已经设置为监控会议小区");
  }else{
    alert("监控会议小区新增失败");
  }
});
		

}

function changetime(objid){
 if(objid=="timeview1hour"||objid=="timeview1min"){
 
    var hour=parseInt($("#timeview1hour").val());
    var min=$("#timeview1min").val();
    $("#amtime").html("上午监控时间段为"+hour+":"+min+"到"+(hour+1)+":"+min);
 }
 else{
     var hour=parseInt($("#timeview2hour").val());
     var min=$("#timeview2min").val();
     $("#pmtime").html("下午监控时间段为"+hour+":"+min+"到"+(hour+1)+":"+min);
  
  }
}

function deletecell(){
  getChecked();
    if(arrayadd.length==0){
   alert("请选择要删除的监控会议小区");
   return;
  }
  if(!confirm("您确定要删除这些监控会议小区吗?")){
  return;
  }
var now=new Date().getTime();
$.getJSON("../cellconferenceajax/conferenceCellSet.action?isdelete=1&cellkey="+arrayadd.join()+"&now="+now,function(json){
  if(json.isok=="1"){
    alert("监控会议小区删除成功");
    window.location.reload();
  }else{
    alert("监控会议小区删除失败");
  }
});
}
function changeit(cellkey){
  $.ajax({
     type: "POST",
     data:"",
     url:"../cellconferenceajax/conferenceCellEdit!input.action?cellkey="+cellkey,
	 success:function(data){
		$.blockUI({message:data});
	
	 }
   });
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>会议小区监控</a>＞<em>会议小区监控设置列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="conferenceCellList" method="POST">	
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
                       <!-- <th>归属SGSN</th>-->
                       <th>上午监控点</th>
                       <th>下午监控点</th>
                       <th>流量增长率阀值</th>
                       <th>用户增长率阀值</th>
                       <th>设置时间</th>
                          <th>参数修改</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                   <!--      <tr title="双击修改配置参数值" onDblClick="changeit('${cellkey}')">-->
                   <tr>
                    <td align="center">
                      <input type="checkbox" class="checkbox" name="check" value="${cellkey }"/>
                    </td>
                     <td>${cellkey} </td>
                          <td>${cell.cellname}</td>
                          <td>${cell.bscrncid }</td>
                      <!--     <td><s:property value="@com.sxit.netquality.service.BasicSetService@BSC_SGSN[cell.bscrncid]"/></td>
                           -->  
                           <td>${timeview1str }</td>
                           <td>${timeview2str }</td>
                            <td>${flowalarmvalue}%</td>
                            <td>${useralarmvalue}%</td>
                       
                          <td>${lastupdate}</td>
                         <!--  <td>${createusername}</td>-->
                          <td><a href="conferenceCellEdit!input.action?cellkey=${cellkey }">修改</a></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                   <tfoot>
						<tr>
							   <td colspan="8" class="fright">
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
<div class="Overdialog" id="Overdialog"><iframe></iframe></div> 
			<!-- 新增  --> 
		<form id="form11" method="post" action="../cellconferenceajax/conferenceCellSet.action">
			<div class="dialogBox" id="dialogBoxAdd"> 
				<div class="DialogWrap"> 
		     	<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">新增监控会议小区</div> 
						<table class="operateTabBox"> 
							<tbody> 
								<tr class="fEven"> 
									<td class="fname" width="170">小区编码：</td> 
									<td class="fvalue">
									<input type="text" id="cellid" size="20" class="txt"/>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="fname">LAC：</td> 
									<td class="fvalue">
									<input type="text" id="lac" size="20" class="txt"/>
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="fname">上午监控时间点：</td> 
									<td class="fvalue">
									<s:select name="timeview1hour" id="timeview1hour" list="@com.sxit.cellConference.service.CellConferenceService@AMTIMELIST" onchange="changetime('timeview1hour')"></s:select>时
									<s:select name="timeview1min" id="timeview1min" list="@com.sxit.cellConference.service.CellConferenceService@MINUTELIST" onchange="changetime('timeview1hour')"></s:select>分
									<span id="amtime"></span>
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="fname">下午监控时间点：</td> 
									<td class="fvalue">
									<s:select name="timeview2hour" id="timeview2hour" list="@com.sxit.cellConference.service.CellConferenceService@PMTIMELIST" onchange="changetime('timeview2hour')"></s:select>时
									<s:select name="timeview2min" id="timeview2min" list="@com.sxit.cellConference.service.CellConferenceService@MINUTELIST" onchange="changetime('timeview2hour')"></s:select>分
									<span id="pmtime"></span>
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="fname">对比前天同时段流量增长率：</td> 
									<td class="fvalue">
									<input type="text" id="flowview" size="10" class="txt"/>%
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="fname">对比前天同时段用户增长率：</td> 
									<td class="fvalue">
									<input type="text" id="userview" size="10" class="txt"/>%
									</td> 
								</tr> 
							</tbody> 
						</table> 
					</div> 
					<div class="dialogBtn"><input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="savecell()"/>　
					<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div> 
				</div> 
				</form>
			</div> 
			
</body>

</html>

