
$(document).ready(function(){
	$("#saveBtn").click(function(){
	  if(($("#fileUpload")).attr("value").length<5){
		  return false;
	  }else{
		  var options={
		      beforeSend:function(){
		      	$("#leavemessagediv").show();
		      	$("#abc").hide();
		      	  },
		      success:function(data){
			    //alert(data);
			  	$.blockUI({message:data});
			  }
		  	};
		  $("#form1").submit();
		  //$("#form1").ajaxSubmit(options);
	  }
	});
//	$(function(){
//        $("#fileUpload").MultiFile({
//        accept:'gif|jpg'/*可接收的文件类型*/,
//        max:2/*最大接收的上传个数*/, STRING: {
//            remove:"移除",
//            selected:"Selecionado: $file",
//            denied:"不接受的文件类型\n$ext!",
//            duplicate:"文件已经存在\n$file!"
//        }
//        });
//    });
});
