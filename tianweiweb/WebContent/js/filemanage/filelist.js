var albumid;
var photoid;

function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}

function showpopbox1(){
	$('.overshadow').attr("style","display:block");
	$('#popbox1').attr("style","display:block");
}

function hidepopbox1(){
	$('#popbox1').attr("style","display:none");
	$('.overshadow').attr("style","display:none");
}

function setfront(){
	var photoid = $("li.active>img").attr("id");
	//alert(photoid);
	
	$.ajax({
        type: "POST",
        url:"setfront.action",
        data:"photoid="+photoid,
        beforeSend:function(){
		//	$('.popboxbody').empty();
        },
        success:function(data){
//        		alert(date);
        	$.blockUI({ message:data});
     }});
}

function rsetfront(photoid){
	$.ajax({
        type: "POST",
        url:"setfront.action",
        data:"photoid="+photoid,
        beforeSend:function(){
        },
        success:function(data){
        	$.blockUI({ message:data});
        }
     });
}

//function delphoto(albumid){
//	var photoid = $("li.active>img").attr("id");
//	//alert(photoid)
//	if(photoid==undefined){
//		//alert("photoid--undefined");
//		photoid = $("li.active>span").attr("id");
//	}
//	if(photoid==undefined){
//		return false;
//	}
//	$.ajax({
//        type: "POST",
//        url:"delphoto.action",
//        data:"photoid="+photoid+"&albumid="+albumid,
//        beforeSend:function(){
//		//	$('.popboxbody').empty();
//        },
//        success:function(data){
////        		alert(date);
//        	$.blockUI({ message:data});
//     }});
//	
//}

function rdelphoto(albumid,photoid){
//	alert("albumid=="+albumid+"--photoid=="+photoid);
	$.ajax({
        type: "POST",
        url:"delphoto.action",
        data:"photoid="+photoid+"&albumid="+albumid,
        beforeSend:function(){
        },
        success:function(data){
        	$.blockUI({ message:data});
     }});
}

function redtphoto(photoname){
//	alert(photoname);
	if(photoname==""){
		alert("请输入照片名");
		return false;
	}
	$.ajax({
        type: "POST",
        url:"editphoto.action",
        data:"photoid="+photoid+"&albumid="+albumid+"&photoname="+photoname,
        beforeSend:function(){
        	hidepopbox1();
        },
        success:function(data){
        	$.blockUI({ message:data});
     }});

}

function delfile(id){
	if(confirm("确认要删除文件")){
		$.ajax({ 
	    type: "POST",
	    url:"filedel.action",
	    data:"id="+id,
	    success:function(data){
			if(data=="0"){
				location=location;
			}else{
				alert("删除失败");
			}
	    	//$.blockUI({ message:data});
		}});
	}
}

function deldir(id){
	if(confirm("确认要删除该目录？")){
		$.ajax({ 
	        type: "POST",
	        url:"dirdel.action",
	        data:"dirid="+id,
	        success:function(data){
				if(data=="0"){
					location=location;
				}else{
					alert("删除失败");
				}
	     }});
	}else{
	}
}