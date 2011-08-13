<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<jscalendar:head />
		<link href="../css/system.css" rel="stylesheet" type="text/css">
		<link href="../css/system_${curuser.style}.css" rel="stylesheet"
			type="text/css">

		<script type="text/javascript">
function page(str){
  document.form1.pagenumber.value=str;
  document.form1.submit()
  return true;
}
function changePagesize(maxperpage){ 
	var url = location.href; 
	if(url.indexOf("?")>0)
		url=url.substring(0,url.indexOf("?"));
	location.replace(url+"?maxperpage="+maxperpage);
}
function query(){
document.form1.action="newcaseList.action";
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
</script>
	</HEAD>
	<body>
		<TABLE width="100%" border=0 align="center" cellPadding="0" cellSpacing=4>
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
								<td width="97%">
									<span class="sort-title">诉讼业务&gt;&gt;入口案件列表</span>
								</td>
							</tr>
						</table>
					</div>
				</TD>
			</TR>
		

			<TR>
				<TD height="171" valign="top" bgColor="#FFFFFF">

					<s:form name="form1" method="post" action="rukouCases">
						<div align="center">
							<s:hidden name="pagenumber" />
							<s:hidden name="statusid" />
							<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999
								border=0>
								<TR>
									<TD align="center" valign="top" bgColor=#F9F9F7>
										<TABLE cellSpacing=1 cellPadding=3 width="100%" align="center" border=0>
											<TBODY>
												  <TR>
                      <TD align="left" class="listline" colspan="2">
                      委托银行:<s:select name="lawcase.bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="-1" headerValue="全部" />&nbsp;
	                    支行:<s:textfield name="lawcase.bankbranch" size="10"/>&nbsp;
                        被告人姓名:<s:textfield name="lawcase.jiekuanren.jiekuanren" size="8" />&nbsp;
                         委托日期:<s:textfield name="lawcase.thedate" size="10" />&nbsp;
                        </TD>
                      </TR>
                    <TR>
                <TD align="left" class="listline">
                案件承办人:<s:textfield name="lawcase.susongworkname" size="12" />&nbsp;
   是否立案:<s:select name="lawcase.islian" list="#{'1':'是','0':'否'}" headerKey="-1" headerValue="全部"/>&nbsp;
   是否缴费:<s:select name="lawcase.isjiaofei" list="#{'1':'是','0':'否'}" headerKey="-1" headerValue="全部"/>&nbsp;
   是否起诉:<s:select name="lawcase.isqisu" list="#{'1':'是','0':'否'}" headerKey="-1" headerValue="全部"/>&nbsp;
   </TD>
   <td align="left" width="15%" class="listline">
   <input type="submit" class="botton" value=" 查询 "/>
  <a href="#" onClick="document.getElementById('morequerytr').style.display='block';">【更多查询】</a>
   </td>
                </TR>
                <tr id="morequerytr" style="display:none">
                   <TD align="left" class="listline" colspan="2">
              合同编号:<s:textfield name="lawcase.jiekuanren.contractno" size="10" />&nbsp;
                借款人身份证号:<s:textfield name="lawcase.jiekuanren.theidcard" size="10" />&nbsp;
                  借款金额大于:<s:textfield name="lawcase.jiekuanren.howmuch" size="6" />&nbsp;
                    借款余额大于:<s:textfield name="lawcase.jiekuanren.remain" size="6" />&nbsp;
                      贷款类型:<s:textfield name="lawcase.jiekuanren.jiekuantype" size="10" />&nbsp;  
                        银行分管人:<s:textfield name="lawcase.bankadmin" size="10" />&nbsp;  
                         担保人:<s:textfield name="lawcase.jiekuanren.danbaoren" size="10" />&nbsp;    
                          担保类型:<s:textfield name="lawcase.jiekuanren.danbaotype" size="10" />&nbsp;      
                   </TD>
                </tr>
											</TBODY>
										</TABLE>
								  </TD>
								</TR>

								<TR>
									<TD align=center valign="top" bgColor=#F9F9F7>

										<TABLE width="100%" border=0 align=center cellPadding=3
											cellSpacing=1 bgcolor="#F9F9F7">
											<TBODY>
												<TR class="listheadline">
													<TD rowspan="2" align=center>
														委托银行
													</TD>
													<TD rowspan="2" align=center>
														所在支行
													</TD>
													<TD rowspan="2" align=center>
														被告人姓名
													</TD>
													<TD rowspan="2" align=center>
														诉讼承办人
													</TD>
													<TD rowspan="2" align=center>
														是否符合起诉
													</TD>
													<TD rowspan="2" align=center>
														是否立案
													</TD>
													<TD rowspan="2" align=center>
														是否缴费
													</TD>
													<TD rowspan="2" align=center>
														是否进入登记表
													</TD>
													<TD rowspan="2" align=center>
														不符合起诉条件/未立案/未缴费原因
													</TD>
													<TD rowspan="2">
														银行分管人
													</TD>
													<TD colspan="2">
														委托时间
													</TD>
													<TD rowspan="2">
														合同编号
													</TD>
													<TD rowspan="2">
														更多
													</TD>
												</TR>
												<TR class="listheadline">
													<TD >
														委托日期
													</TD>
													<TD>
														重复查询日期
													</TD>
													
												</TR>
												<s:iterator value="lawcaselist" status="status">
													<tr class=listline>
														<TD>
															<s:property
																value="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS[bankid]" />
														</TD>
														<TD>
															${bankbranch }
														</TD>
														<TD>
															${ jiekuanren.jiekuanren}
														</TD>
														<TD >
															<s:if test="susongworkid>0">${susongworkname}(<s:property value="@com.changpeng.lawcase.util.CommanDatas@ALLUSERS[susongworkid].mobile"/>)</s:if><s:else>还未分配</s:else>
														</TD>
														<TD>
															<s:if test="isqisu==1">是</s:if><s:else>否</s:else>
														</TD>

														<TD>
															<s:if test="islian==1">是</s:if><s:else>否</s:else>
														</TD>
														<TD>
															<s:if test="isjiaofei==1">是</s:if><s:else>否</s:else>
														</TD>
														<TD>
																<s:if test="isdengji==1">是</s:if><s:else>否</s:else>
														</TD>
														<TD>
															${ whynot}
														</TD>
														<TD>
															${ bankadmin}
														</TD>
														<TD>
															${ thedate}
														</TD>
														<TD>
															${ requerytime}
														</TD>
														<TD>
															${ contractno}
														</TD>
														<TD>
															<a href="caseDoView.action?caseid=${caseid }">【查看】</a>
														</TD>
													</tr>
												</s:iterator>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							   <TR bgcolor="#FEF7E9" class="pt9-18">
           <TD colSpan=15 align="right">
           ${lawcasePagestring }
           </TD>
           </TR>

							<!-- 	<tr bgcolor="#F9F9F7" class="pt9-18">
									<td colspan=15 align="center">
										<input name="submitaddbatch" type="button" class="mask"
											value="删除" onClick="deleteit()">
										&nbsp;
										<input name="submitaddbatch" type="button" class="mask"
											value="提交到是事业部总经理分配" onClick="commit2manager()">
									</td>
								</tr>
								-->
							</TABLE>



						</div>
					</s:form>

				</TD>
			</TR>

		</TABLE>
	</body>


</HTML>
