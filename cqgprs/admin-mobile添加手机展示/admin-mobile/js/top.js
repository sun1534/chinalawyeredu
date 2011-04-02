$(function(){
		clearFocus();
		current("#quickmenu","a","current",this);
		//current("#skinlist","a","current",this);
		//ScrollImgLeft("scroll_begin","scroll_end","scroll_div","40")

	});

	function clearFocus(){
		$('a,input[type="button"]').bind('focus',function(){
		if(this.blur){ this.blur();}});
	}

	function current(em,o,c){
		$(em+" "+o).each(function(){
			$(em+" "+o).click(function()
			{
				$(this).parent().addClass(c).siblings().removeClass(c);
				//loadSkinUrl("#Skin",this,"rel");
			});
		});
	}

	/* 	加载皮肤 */
	function loadSkinUrl(em,o,p){
		$(em).attr("href","skin/"+$(o).attr(p)+"/css/style.css");
	}

	/* 滚动 */
	function ScrollImgLeft(dStart,dEnd,DContainer,speed){
	var speed;
	var scroll_begin = document.getElementById(dStart);
	var scroll_end = document.getElementById(dEnd);
	var scroll_div = document.getElementById(DContainer);
	scroll_end.innerHTML=scroll_begin.innerHTML
	  function Marquee_(){
		if(scroll_end.offsetWidth-scroll_div.scrollLeft<=0)
		  scroll_div.scrollLeft-=scroll_begin.offsetWidth
		else
		  scroll_div.scrollLeft++
	  }
	var MyMar=setInterval(Marquee_,speed)
	  scroll_div.onmouseover=function() {clearInterval(MyMar)}
	  scroll_div.onmouseout=function() {MyMar=setInterval(Marquee_,speed)}
	}
