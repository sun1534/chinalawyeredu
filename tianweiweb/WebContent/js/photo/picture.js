
function getpicture(albumid){
	
//	$.ajax({
//        type: "POST",
//        url:"picturelist.action",
//        data:"albumid="+albumid,
//        beforeSend:function(){
//		 },
//        success:function(date){
//        	
//        	
//     }});
	window.location.href = "picturelist.action?albumid="+albumid;
	
}

function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}

function picturecut(){
	 var count=0;	
//     var len= document.form1.photoid.length;  
   
      for(var i=0;i< document.getElementsByName("photoid").length;i++)   
  {   
     if(document.getElementsByName("photoid")[i].checked==true)   
  {   count=1;
    }
  }
  
  if(count==1){ 
     $("#form1").submit();
   }else{
  	alert("请选择一张照片");
   }
	
   
}