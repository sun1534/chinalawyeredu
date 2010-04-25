
var MERCHANTID ='';              //商户代码
var BRANCHID='';                 //分行代码
var POSID='';                    //柜台号
var INTER_FLAG='1';						//接口类型，若为'1'则表示新接口，新接口必需有商户公钥的前30位PUB32
var PUB32='';

var ORDERID=Math.round(Math.random()*100000)+1;//'001';
var ORDERDATE=''                  //订单日期
var PAYMENT='';
var CURCODE='01';
var TXCODE='520100';
var TXCODE410404='410404'                 //查询交易
var REMARK1='';
var REMARK2='';
var DOTYPE='1';
var bankURL='https://ibsbjstar.ccb.com.cn/app/ccbMain';
var EPAY_NO='';//E付卡卡号


//以下内容不要轻易改动
var strMD5;
var URL;
var URL0;//网上银行支付
var URL1;//E付卡支付

function setValue()
{
	
	var objMERCHANTID=document.getElementById("MERCHANTID");
	objMERCHANTID.value=MERCHANTID;
	

	var objPOSID=document.getElementById("POSID");
	objPOSID.value=POSID;
	
	
	

	var objBRANCHID=document.getElementById("BRANCHID");
	objBRANCHID.value=BRANCHID;
	
	
	var objORDERID=document.getElementById("ORDERID");
	objORDERID.value=ORDERID;
	

	var objPAYMENT=document.getElementById("PAYMENT");
	objPAYMENT.value=PAYMENT;
	
	
	var objCURCODE=document.getElementById("CURCODE");
	objCURCODE.value=CURCODE;
	
	
	var objTXCODE=document.getElementById("TXCODE");
	objTXCODE.value=TXCODE;
	

	var objREMARK1=document.getElementById("REMARK1");
	objREMARK1.value=REMARK1;
	
	
	var objREMARK2=document.getElementById("REMARK2");
	objREMARK2.value=REMARK2;
	
	
	var objbankURL=document.getElementById("bankURL");
	objbankURL.value=bankURL;
	
   
	var objEPAY_NO=document.getElementById("EPAY_NO");
	objEPAY_NO.value=EPAY_NO;
	
	document.getElementById("PUB32").value=PUB32;;
	document.getElementById("INTER_FLAG").value=INTER_FLAG;;
	
	var objsendB=document.getElementById("sendB");
	objsendB.disabled=true;

}

function make()
{
	var tmp;
	var tmp0;
	var tmp1;
 
  MERCHANTID=document.getElementById("MERCHANTID").value;
  
	
	POSID=document.getElementById("POSID").value;
	
	
	BRANCHID=document.getElementById("BRANCHID").value;
	
	
	ORDERID=document.getElementById("ORDERID").value;
	
	
	PAYMENT=document.getElementById("PAYMENT").value;
	
	
	CURCODE=document.getElementById("CURCODE").value;
	
	
	TXCODE=document.getElementById("TXCODE").value;
	
	
	REMARK1=document.getElementById("REMARK1").value;
	
	
	REMARK2=document.getElementById("REMARK2").value;
	
	
	bankURL=document.getElementById("bankURL").value;
	
	
	EPAY_NO=document.getElementById("EPAY_NO").value;
    
	tmp ='MERCHANTID='+MERCHANTID+'&POSID='+POSID+'&BRANCHID='+BRANCHID+'&ORDERID='+ORDERID+'&PAYMENT='+PAYMENT+'&CURCODE='+CURCODE+'&TXCODE='+TXCODE+'&REMARK1='+REMARK1+'&REMARK2='+REMARK2;
	tmp0='MERCHANTID='+MERCHANTID+'&POSID='+POSID+'&BRANCHID='+BRANCHID+'&ORDERID='+ORDERID+'&PAYMENT='+PAYMENT+'&CURCODE='+CURCODE+'&TXCODE=520100'+'&REMARK1='+REMARK1+'&REMARK2='+REMARK2;
	tmp1='MERCHANTID='+MERCHANTID+'&POSID='+POSID+'&BRANCHID='+BRANCHID+'&ORDERID='+ORDERID+'&PAYMENT='+PAYMENT+'&CURCODE='+CURCODE+'&TXCODE=NE4100'+'&REMARK1='+REMARK1+'&REMARK2='+REMARK2+'&EPAY_NO=' +EPAY_NO;
	index=document.getElementById("INTER_FLAG").value;
	temp_New=tmp;
	
	if(index=="1"){
		temp_New=tmp+'&PUB32='+document.getElementById("PUB32").value;
	}
	strMD5=hex_md5(temp_New);

    URL = bankURL+'?'+tmp+'&MAC='+strMD5;
	URL0 = bankURL+'?'+tmp0+'&MAC='+hex_md5(tmp0);
	URL1 = bankURL+'?'+tmp1+'&MAC='+hex_md5(tmp1);

   
    document.getElementById("result").value=URL;

	
	document.getElementById("MAC").value=strMD5;
	
	
	document.getElementById("URL01").value=URL0+'||'+URL1;

  
	document.getElementById("sendB").disabled=false;

  
    document.getElementById("makeMd5").disabled=true;
}

function go( sendUrl)
{
	var objMD5form=document.getElementById("MD5form");
	
	objMD5form.method="post";
 	objMD5form.action=bankURL;
 	objMD5form.submit();
 	
  

}

function newRest()
{
   window.MD5form.sendB.disabled =true ;
   window.MD5form.makeMd5.disabled = false;

   window.MD5form.MERCHANTID.value=MERCHANTID;
   window.MD5form.POSID.value=POSID;
   window.MD5form.BRANCHID.value=BRANCHID;
   window.MD5form.ORDERID.value=ORDERID;
   window.MD5form.PAYMENT.value=PAYMENT;
   window.MD5form.CURCODE.value=CURCODE;
	window.MD5form.TXCODE.value=TXCODE;
	window.MD5form.REMARK1.value=REMARK1;
	window.MD5form.REMARK2.value=REMARK2;
	window.MD5form.bankURL.value=bankURL;
  
}

function DOTYPE_onclick()
{
var index=window.MD5form.DOTYPE.selectedIndex;
var value=window.MD5form.DOTYPE.options[index].value;
if (value==0)
{
window.document.all.epay.style.display="none";
}else
{
window.document.MD5form.EPAY_NO.value="";
window.document.all.epay.style.display="";
}


}