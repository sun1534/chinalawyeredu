
$(document).ready(function(){
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
})

function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}

function showpopbox1(){
	
	$('.overshadow').attr("style","display:block");
	$('#albumname').attr('value',"");
	$('#popbox1').attr("style","display:block");
	
}

function hidepopbox1(){
	$('#popbox1').attr("style","display:none");
	$('.overshadow').attr("style","display:none");
	$('#groupname').attr("value","");

}


function delalbum(albumid){
	$.ajax({
        type: "POST",
        url:"delalbum.action",
        data:"albumid="+albumid,
        beforeSend:function(){
			
        },
        success:function(data){
        	$.blockUI({ message:data});
     }});
}

function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}

function updatephtot(albumname,albumid){
	var pattern=/[~!@#$%^&*()_+]/;
	if($.trim(albumname)==""){

		return false;
    }
    if(pattern.exec(albumname))
    { 
        alert("您输入的相册名称中包含了非法字符,请重新输入");
    }else{
	
		$.ajax({
	        type: "POST",
	        url:"updatephoto.action",
	        data:"albumname="+albumname+"&albumid="+albumid,
	        beforeSend:function(){
				
	        },
	        success:function(data){
	    
	        	$.blockUI({ message:data});
	     }});
	}
}



function backlist(){
	window.location = "../photo/albumlist.action";
}