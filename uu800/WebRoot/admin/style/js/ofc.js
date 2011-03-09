OFC = {};   
    
OFC.jquery = {   
    name: "jQuery",   
    version: function(src) { return $('#'+ src)[0].get_version() },   
    rasterize: function (src, dst) { $('#'+ dst).replaceWith(OFC.jquery.image(src)) },   
    image: function(src) { return "<img src='data:image/png;base64," + $('#'+src)[0].get_img_binary() + "' />"},   
    saveimg: function(src) {  
        var img_win = window.open('', 'Image')  
		var  html = "<html><head><title>Charts: Export as Image</title>\n";
		      html = html + "<script type=\"text/javascript\">\n";
			  html = html + "function  formsubmit(){\n";
			  html = html + "	var form1 = document.getElementById(\"form1\");\n";
			  html = html + "	form1.submit();\n";
			  html = html + "}\n";			 
			  html = html + "</script>\n";
			  html = html + "</head><body onload=\"formsubmit()\">\n";
			  
		     html = html + "<form id=\"form1\" action=\"flashToImage.action\" method=\"post\">\n";
		     html = html + "<input type=\"hidden\" name = \"imagename\" value=\"bar\">\n";
			 html = html + "<input type=\"hidden\" name = \"imgBase64Code\" value=\""+ OFC.jquery.image(src) +"\">\n";
			 html = html + "</form>\n";
			 html = html + "</body>\n";
			 html = html + "</html>\n";
            with(img_win.document) {   
             write(html) ;
			}   
        // stop the 'loading...' message   
        img_win.document.close();   
     } ,
	 savepng: function(src) {   	    
		var  html = "";
		     html = html + "<form id=\"pngform\" action=\"flashToImage.action\" method=\"post\">\n";
		     html = html + "<input type=\"hidden\" name = \"imagename\" value=\"bar\">\n";
			 html = html + "<input type=\"hidden\" name = \"imgBase64Code\" value=\""+ $('#'+src)[0].get_img_binary() +"\">\n";
			 html = html + "</form>\n";
		     html = html + "<script type=\"text/javascript\">\n";
			 html = html + "function  formsubmit(){\n";
			 html = html + "	var pngform = document.getElementById(\"pngform\");\n";
			 html = html + "	pngform.submit();\n";
			 html = html + "}\n";			 
			 html = html + "</script>\n";	
			$("#pngdiv").empty();
			$("#pngdiv").append(html);
			formsubmit();
     }  
}   
    
// Using an object as namespaces is JS Best Practice. I like the Control.XXX style.   
//if (!Control) {var Control = {}}   
//if (typeof(Control == "undefined")) {var Control = {}}   
if (typeof(Control == "undefined")) {var Control = {OFC: OFC.jquery}}   
    
// By default, right-clicking on OFC and choosing "save image locally" calls this function.   
// You are free to change the code in OFC and call my wrapper (Control.OFC.your_favorite_save_method)   
// function save_image() { alert(1); Control.OFC.popup('my_chart') }   

