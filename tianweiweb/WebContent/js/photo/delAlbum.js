function delalbum(albumid){
//	alert("delalbum("+albumid+")");
		$.ajax({
        type: "POST",
        url:"delalbum.action",
        data:"albumid="+albumid,
        beforeSend:function(){
        },
        success:function(data){
//        	alert("return message = "+data);
        	$.blockUI({ message:data});
     }});
}