function setfront(photoid,albumid,pageNo){
	$.ajax({
        type: "POST",
        url:"setfront.action",
        data:"photoid="+photoid+"&albumid="+albumid+"&pageNo="+pageNo,
        beforeSend:function(){
        },
        success:function(data){
        	$.blockUI({ message:data});
        }
     });
}