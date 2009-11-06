$(document).ready(function() {
     $('#save').click(function() {
    	 var chk1 = document.getElementsByName("c1");
    	 var arr1 = [];
    	 for(i=0; i < chk1.length; i++){
    		if(chk1[i].checked){
    			arr1[i]="1";
    		}else{
    			arr1[i]="0";
    		}
    	 }
    					
    	 var chk2 = document.getElementsByName("c2");
    	 var arr2 =[];
    	 for(j=0; j < chk2.length; j++){
    		if(chk2[j].checked){
    			arr2[j]="1";
    	 	}else{
    	 		arr2[j]="0";
    		}
    	}
    	$.ajax({
    		type:"post",
    		url:"../userset/treadupdate.action",
    		data:"isrecieves="+arr1.toString()+"&issends="+arr2.toString(),
    		beforeSend:function(){
    		var blockCss = {
        			border: 'none', 
                    padding: '55px', 
                    backgroundColor: '#000', 
                    '-webkit-border-radius': '50px', 
                    '-moz-border-radius': '10px', 
                    opacity: '.5', 
                    color: '#fff' 
                	};
    		//$.blockUI({ message: "正在保存...."}, blockCss);
    		},
	    	error:function(){
	    		$.blockUI({ message: "失败" });
	    	},
	    	success:function(data){
	    			//$.blockUI({ message: $('#question')});
	    		$.blockUI({ message: data });
	    	}    	
    	});
     });     
}); 


/*
 *  2009年4月20日  用ajax提交 并验证 联系资料
 */

/*
$(document).ready(function(){
	$("#form1").validate({
       rules:{ 
       	   //
           areacode: {
               minlength:4,
               maxlength:4,
               number:true
           },
              phone: {
               minlength:7,
               maxlength:11,
               number:true
           },
           extcode: {
               minlength:4,
               maxlength:6,
               number:true
           },
           postcode: {
           	   minlength:6,
               maxlength:6,
               number:true
           }
       },
       messages: {    
           areacode: {
               minlength:"区号必须为4个数字!",
               maxlength:"区号必须为4个数字!",
               number:"区号必须为数字"
           },
            phone: {
               minlength:"手机/小灵通最少为7个数字!",
               maxlength:"手机/小灵通最多为11个数字!",
               number:"分机号必须为数字"
            
           },
              extcode: {
               minlength:"分机号必须为4个数字!",
               maxlength:"分机号最多6个数字",
               number:"分机号必须为数字"
           },
           postcode: {
           	   minlength:"邮政编号必须为6个数字!",
               maxlength:"邮政编号必须为6个数字",
               number:true
           }
       } ,
      errorPlacement: function(error, element) {						   
		 error.appendTo( element.parent() );   
	  },
	   submitHandler: function(form) {
		//ajax提交表单,需要jQuery.Form插件
	   	    var options={success:showResponse};
			$("#form1").ajaxSubmit(options);
			return false;
		}
	});

});
*/

/*
 * 修改个人设置
 */

$(document).ready(function(){
	var options={success:showResponse};
	
	$("#saveBtn").click(function(){
	  $("#form1").ajaxSubmit(options);
	});
 	$("#saveBtn2").click(function(){
		$("#form2").ajaxSubmit(options);
	});
});


function showRequest(){	
	//$.blockUI({ message: "正在保存...." });
}

function showResponse(data){
	$.blockUI({ message: data });
}

function showimg()   
{   
  document.form1.Myimg.src=document.form1.pic.value;   
  document.form1.Myimg.width=120;   
  document.form1.Myimg.height=100;   
}
