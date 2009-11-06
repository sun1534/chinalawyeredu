function deldir(id){
	if(confirm("确认要删除该目录？")){
		$.ajax({ 
	        type: "POST",
	        url:"dirdel.action",
	        data:"dirid="+id,
	        success:function(data){
	        	document.location.href=document.location.href;
	     }});
	}else{
	}
//		$.ajax({ 
//        type: "POST",
//        url:"dirdel.action",
//        data:"dirid="+id,
//        success:function(data){
//        	//$.blockUI({ message:data});
//			$.Jxq.hideTips('#m1_');
//     }});
}