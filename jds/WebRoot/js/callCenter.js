
//函数名：isTEL
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
function isTEL(TEL)
{	
	if(TEL==null||TEL.length<7)
		return false;
	var i,j,strTemp;
	strTemp="0123456789-()# ";
	for (i=0;i<TEL.length;i++)
	{
		j=strTemp.indexOf(TEL.charAt(i));
		if (j==-1)
		{
		//说明有字符不合法
			return false;
		}
	}
	//说明合法
	return true;
}

/**
 * 每个座席想打电话，必须是“暂停”状态下才可以打出电话的,
 * 暂停的url是“data:"cmdType=Wrap&&Extid="+Extid+"&Agentid="+Agentid
 * ”座席的状态要是“就绪”才要有电话打到座席的电话上,
 * 就绪的url是“data:"cmdType=Ready&&Extid="+Extid+"&Agentid="+Agentid”
 */
	
	var call_url="http://192.168.1.9/Webcomonitor/CallPage/CommandProcessing.aspx"
    var local_url="../callcenter/callCenter.action";
    //拨号 ，其中Extid 是分机号，Agentid是座席号，data是拨打的电话号码。这三个都不能为空的
    function Dail(data,Extid,Agentid){
         if(data=="")
        {
            alert("请选择或填写正确呼叫号码");
            return;
        }
        if(Extid=="")
        {
            alert("请联系管理员完善您的分机号");
            return;
        }
         if(Agentid=="")
        {
            alert("请联系管理员完善您的工号");
            return;
        }
        $.ajax({
           type:"GET",
           url:local_url,
           dataType:"json",
           data:"call_url="+call_url+"&cmdType=Dail&Extid="+Extid+"&Agentid="+Agentid+"&data="+data,
           cache:false,
           success:function(xmldata){
		      if(!xmldata.result){
		      	$("#showmsg").show();
		      	$("#showmsg").html("呼叫中心异常，请稍候再试。");
		      }
           },         
		  error: function(XMLHttpRequest){	
		  		alert(XMLHttpRequest.status);
		  }
        });  
        
    }
    //会议,其中Extid 是分机号，Agentid是座席号，data是拨打的电话号码。这三个都不能为空的
    function Confer(data,Extid,Agentid){
        if(data=="")
        {
            alert("请选择或填写正确呼叫号码");
            return;
        }
        if(Extid=="")
        {
            alert("请联系管理员完善您的分机号");
            return;
        }
         if(Agentid=="")
        {
            alert("请联系管理员完善您的工号");
            return;
        }
        $.ajax({
           type:"GET",
           url:local_url,
           dataType:"json",
           data:"call_url="+call_url+"&cmdType=Confer&Extid="+Extid+"&Agentid="+Agentid+"&data="+data,
           cache:false,
           success:function(xmldata){
		      ;
           }
        });
    }
    //应答，其中Extid 是分机号，Agentid是座席号。这两个都不能为空的
    function Answer(Extid,Agentid){
      //  var data=tbPhoneNumber.value;
        $.ajax({
           type:"GET",
           url:local_url,
           dataType:"json",
           data:"call_url="+call_url+"&cmdType=Answer&Extid="+Extid+"&Agentid="+Agentid,
           cache:false,
           success:function(xmldata){
		      ;
           }
        });
    }
    
    //暂停，其中Extid 是分机号，Agentid是座席号。这两个都不能为空的
    function Wrap(Extid,Agentid){
        $.ajax({
           type:"GET",
           url:local_url,
           dataType:"json",
           data:"call_url="+call_url+"&cmdType=Wrap&Extid="+Extid+"&Agentid="+Agentid,
           cache:false,
           success:function(xmldata)
           {
		      ;
           }
        });
    }
    
    //挂机，其中Extid 是分机号，Agentid是座席号。这两个都不能为空的
    function Release(Extid,Agentid){
        $.ajax({
           type:"GET",
           url:local_url,
           dataType:"json",
           data:"call_url="+call_url+"&cmdType=Release&Extid="+Extid+"&Agentid="+Agentid,
           cache:false,
           success:function(xmldata)
           {
		      ;
           }
        });
    }
    //播放，其中strExt是播放分机，strAgent播放座席，data是播放的序列号,三个都不为空
    function ajaxPlay(data,strExt,strAgent){
        if(strExt=="-1"){
            alert("请填写播放分机");
            return;
         }
        // alert(strExt);
        $.ajax({
            type:"GET",
            url:local_url,
            dataType:"json",
            data:"call_url="+call_url+"&cmdType=Play&Extid="+strExt+"&Agentid="+strAgent+"&data="+data,
            cache:false,
            success:function(xmldata){
				;
            }
        });
    } 
   	//停止播放，其中strExt是停止播放分机，strAgent停止播放座席，data是停止播放的序列号,三个都不为空
    function ajaxStopPlay(data,StrExt,strAgent){
        if(strExt=="-1"){
            alert("请填写播放分机");
            return;
         }
        $.ajax({
            type:"GET",
            url:local_url,
            dataType:"json",
            data:"call_url="+call_url+"&cmdType=Stop&Extid="+strExt+"&Agentid="+strAgent+"&data="+data,
            cache:false,
            success:function(xmldata){
			    ;
            }
        });
    } 
    
    //获取录音播放URL
    var Resouraddress="\\\\192.168.1.9\\RunRecord";
    function strpath(play){
    	var str=play.substring(0, 2) + play.substring(2, 4) + "\\" + play.substring(4, 6) + "\\" + play.substring(12, 15) + "\\" + play.substring(0, 15);
    	return Resouraddress +"\\"+ str + ".wav";
    }