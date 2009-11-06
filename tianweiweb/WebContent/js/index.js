$(document).ready(function(){
    setContent("../home/notice.action","#noticediv");
    //setContent("../home/myshow.action","#myshowdiv");
    //setContent("../home/entshow.action","#entshowdiv");
    //setContent("../home/newsindex.action","#hotnewsdiv");
    //setContent("../home/redianindex.action","#hotindexdiv");
    //setContent("../home/productindex.action","#info6");
    setContent("../home/huodongindex.action","#info4");
    //setContent("../home/shihuiindex.action","#info5");
    //setContent("../home/adview.action?id=1","#topbannal");
    
    setContent("../home/adview.action?id=2","#right1");
    //setContent("../home/adview.action?id=3","#right2");
    setContent("../home/adview.action?id=4","#left1");
    //setContent("../home/adview.action?id=5","#left2");
    
    setContent("../home/partnerindex.action","#friend");
    
    setContent("http://www.7stk.com/1/6/sina.htm","#tq");
});
function setContent(requrl,condiv){
	if($(condiv).size()!=0){
		$.ajax({
	    type: "POST",
	    data:"",
	    url:requrl,
	    success:function(data){
	    	$(condiv).html(data);
	    }});
	}
}
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}