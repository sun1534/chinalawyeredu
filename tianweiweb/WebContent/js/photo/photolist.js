var albumid;
var photoid;

$(document).ready(function(){

	fitphoto();
});

/**
 * 对相片的大小按照相框的大小进行调整
 */
function fitphoto(){
	
	var _width=86;
	var _height=86;
	var album_img = $(".album_img");
	for(var i=0;i<album_img.length;i++){
		var img_n = album_img.get(i);
	
		var width = img_n.width;
		var height = img_n.height;
		if(height>_height||width>_width){
			if(height/_height>width/_width){
				var per = _height/height;
			}
			else{
				var per = _width/width;
			}

			img_n.height = height*per ;
			img_n.width = width*per ;
			
		}
			
	}
	
}

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