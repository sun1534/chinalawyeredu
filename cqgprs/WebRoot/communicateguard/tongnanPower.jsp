<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>潼南移动基站电量分析</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" /> 
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
 
 <jscalendar:head/>
   <script type="text/javascript" src="../js/dialog.js"></script> 
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript" src="../js/swfobject.js"></script>
 <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
 <script type="text/javascript">
  $(document).ready(function(){
 $("#tableOrder").tablesorter();
 });
 
 //var url="test.txt"
 //swfobject.embedSWF("../open-flash-chart.swf", "barchart", "500", "300", "9.0.0","",{"data-file":"test.txt","loading":"正在载入数据..."} );
 
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
function imageit(){
 if(ishide){
  $("#imageopton").show();
  ishide=false;
  }else{
  $("#imageopton").hide();
      $("#imgreport").hide();
  ishide=true;
  }
}
var ishide=true;
$(document).ready(function(){
  $("#imageopton").hide();
  $("#imgreport").hide();
  ishide=true;
});
function confirmit(){
   var month=$("#month").val();
    $("#imgreport").show();
      var flashby=$("#flashby").val();
 
   var url="tongnanPower.action?flashby="+flashby+"%26month="+month+"%26resultType=flash%26now="+new Date().getTime();
   // alert(url);
   swfobject.embedSWF("../open-flash-chart.swf", "barchart", "2000", "350", "9.0.0","",{"data-file":url,"loading":"正在载入数据..."} );
   //alert(url);

}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist" align="center">
						<span style="font-size:16px"><strong>潼南移动基站电量分析</strong></span></em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="tongnanPower" method="POST">	
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
								 <td>输入月份：<s:textfield name="month" id="month" cssClass="txt" size="10" title="为201011的形式"/>&nbsp;</td>
								 <td>CELLID前4位：<s:textfield name="cellid" id="cellid" size="6" cssClass="txt"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
								 <td>  <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/></td>
								
								<td><input type="button" class="btnSubmit" value="图  形"  onclick="imageit()"/></td>
								 <td id="imageopton">
								   <s:select name="flashby" id="flashby" list="#{'1':'中达日均/载频','2':'中达日均/话务','3':'电力日均/载频','4':'电力日均/话务','5':'电力/中达日均对比'}" label="维度"></s:select>
								   <input type="button" class="btnSubmit" value="确 认"  onclick="confirmit()" id="flashconfirm"/>
								 </td>
								<td><input type="button" class="btnSubmit" value="上传电度文件" onclick="showDialogBox('#dialogBoxAdd',500);"/> </td>
						
							
								</tr>
							</tbody>
						</table>
				  </div> 
				
				  <div  class="tablist" style="text-align:center;overflow:auto" id="imgreport">
                    <div id="barchart"></div>
                    </div>
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                          <th>基站名称</th>
                     
                          <th>中达日均</th>
                           <th>电力公司日均</th>
                            <th>载频数</th>
                          <th>日均话务量</th>
                          <th>中达与电力数据比例</th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="powerlist" status="status">
                        <tr>
                         <td>${cellName}</td>
                          <td>${zhongdanrijun }</td>
                           <td>${powerrijun }</td>
                            <td>${zaipin }</td>
                          <td>${huawuliangrijun}</td>
                          <td>${theratePercent}</td>
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

				<!-- <div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>-->
				</div>
			</div>
		</div>
</s:form>
<div class="Overdialog" id="Overdialog"><iframe></iframe></div> 
			<!-- 新增  --> 
			<div class="dialogBox" id="dialogBoxAdd"> 
				<form name="tongnanPowerSet" action="tongnanPowerSet.action" method="post" enctype="multipart/form-data">
				<div class="DialogWrap"> 
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">上传电度文件</div> 
						<table class="operateTabBox"> 
							<tbody> 
								<tr class="fEven"> 
									<td class="w120 fname">月份：</td> 
									<td class="fvalue">
									<input type="text" id="uploadmonth" name="uploadmonth" size="20" class="txt"/>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">电度文件：</td> 
									<td class="fvalue">
									<input type="file" id="upload" name="upload" size="20" class="txt"/>
									</td> 
								</tr> 
							</tbody> 
						</table> 
					</div> 
					<div class="dialogBtn">
					<input type="submit"  value="上 传" title="上 传" class="btnSubmit"/>　
					<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/>
					</div> 
				</div> 
				</form>
			</div> 
</body>

</html>

