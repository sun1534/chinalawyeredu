$.fn.Tab = function(opt){
	var cfg={
		items:[{'id':1,'title':'tab','closed':true,'icon':'','html':'',load:'','callback':function(){}}],
		width:500,
		height:500,
		tabcontentWidth:498,
		tabWidth:66,
		tabHeight:25,
		tabScroll:true,
		tabScrollWidth:19,
		tabClass:'tab',
		tabContentClass:'tab-div-content',
		tabClassOn:'on',
		tabClassOff:'off',
		tabClassClose:'close',
		tabClassInner:'inner',
		tabClassInnerLeft:'innerLeft',
		tabClassInnerMiddle:'innerMiddle',
		tabClassInnerRight:'innerRight',
		tabClassText:'text',
		tabClassScrollLeft:'scroll-left',
		tabClassScrollRight:'scroll-right',
		tabClassDiv:'tab-div',
		tabClassHtmlDiv:'tab-div-html',
		tabHtml:''
	};
	//默认显示第一个li
	var displayLINum=0;
	$.extend(cfg,opt);
	//判断是不是有隐藏的tab
	var tW=cfg.tabWidth*cfg.items.length;
	cfg.tabScroll?tW-=cfg.tabScrollWidth*2:null;
	
	//tabDiv,该div是自动增加的
	var tab=$('<div />').attr('id','jquery_tab_div').height(cfg.tabHeight).addClass(cfg.tabClass).append('<ul />');
	//tab target content
	var tabContent=$('<div />').attr('id','jquery_tab_div_content').width(cfg.tabcontentWidth).height(cfg.height-cfg.tabHeight).addClass(cfg.tabContentClass);
	var ccW=(cfg.items.length*cfg.tabWidth)-cfg.width;
	//var tabH=$('<div />').append(cfg.tabHtml).addClass(cfg.tabClassHtmlDiv).height(cfg.tabHeight);
	var tabH='';
	//增加一个tab下的li得模板
	var tabTemplate='';
	tabTemplate = '<table class="'+cfg.tabClassInner+'"  id="{0}" border="0" cellpadding="0" cellspacing="0"><tr>' + '<td class="'+cfg.tabClassInnerLeft+'"></td>'
			+ '<td class="'+cfg.tabClassInnerMiddle+'"><span class="'+cfg.tabClassText+'">{1}</span></td>' + '<td class="'+cfg.tabClassInnerMiddle+'"><div class="'+cfg.tabClassClose+' '+cfg.tabClassClose+'_noselected"></div></td>' + '<td class="'+cfg.tabClassInnerRight+'"></td>'
			+ '</tr></table>';
	var scrollTab=function(o,flag){
		//当前的left
		var displayWidth=Number(tab.css('left').replace('px',''));
		!displayWidth?displayWidth=0:null;
		//显示第几个LI
		var displayNum=0;
		var DW=0;
		var left=0;
		if(flag&&displayWidth==0){
			return;
		}
		//向左边移动一个tab
		if(flag){
			displayLINum--;
			left=displayWidth+tab.find('li').eq(displayLINum).width();
			left>0?left=0:null;
		}
		//向右边移动一个tab
		else{
			//判断当前显示得li得宽度
			if((tab.find('li:last').offset().left+tab.find('li:last').width()-tab.find('li').eq(displayLINum).offset().left)<=cfg.width-2*cfg.tabScrollWidth){
				return;
			}
			left=displayWidth-tab.find('li').eq(displayLINum).width();
			displayLINum++;
		}
		tab.animate({'left':left});
	}
  function addTab(item){
		//兼容以前没有id
		if(!item.id) item.id=+new Date();
		var innerString=tabTemplate.replace("{0}",item.id).replace("{1}",item.title);
		var liObj=$('<li></li>');
		liObj.append(innerString).appendTo(tab.find('ul')).find('table td:eq(1)').click(function(){
			//判断当前是不是处于激活状态
			var _self=liObj;
			if(_self.hasClass(cfg.tabClassOn)) return;
			
			//改变内部得css
			_self.find('td:first').addClass(cfg.tabClassInnerLeft+'_selected');
			_self.find('td:last').addClass(cfg.tabClassInnerRight+'_selected');
			_self.find('td:eq(1)').addClass(cfg.tabClassInnerMiddle+'_selected');
			_self.find('td:eq(2)').addClass(cfg.tabClassInnerMiddle+'_selected');
			
			var activeLi=_self.parent().find('li.'+cfg.tabClassOn);
			activeLi.find('td:first').removeClass().addClass(cfg.tabClassInnerLeft);
			activeLi.find('td:last').removeClass().addClass(cfg.tabClassInnerRight);
			activeLi.find('td:eq(1)').removeClass().addClass(cfg.tabClassInnerMiddle);
			activeLi.find('td:eq(2)').removeClass().addClass(cfg.tabClassInnerMiddle).find('div').addClass(cfg.tabClassClose+'_noselected');
			activeLi.removeClass().addClass(cfg.tabClassOff);
			
			$(this).next().find('div').removeClass().addClass(cfg.tabClassClose);
			_self.removeClass().addClass(cfg.tabClassOn);
			
			//判断是显示html代码还是ajax请求内容
			if(item.html){
				tabContent.html(item.html);
			}
			else{
				if(item.load){
					tabContent.load(item.load);
				}
			}
			//回调函数是什么
			if(item.callback) item.callback(item);
		}).hover(function(){
			var _self=liObj;
			if(_self.hasClass(cfg.tabClassOn)) return;
			_self.find('td:first').addClass(cfg.tabClassInnerLeft+'_mouseover');
			_self.find('td:last').addClass(cfg.tabClassInnerRight+'_mouseover');
			_self.find('td:eq(1)').addClass(cfg.tabClassInnerMiddle+'_mouseover');
			_self.find('td:eq(2)').addClass(cfg.tabClassInnerMiddle+'_mouseover');
		},function(){
			var _self=liObj;
			if(_self.hasClass(cfg.tabClassOn)) return;
			_self.find('td:first').removeClass(cfg.tabClassInnerLeft+'_mouseover');
			_self.find('td:last').removeClass(cfg.tabClassInnerRight+'_mouseover');
			_self.find('td:eq(1)').removeClass(cfg.tabClassInnerMiddle+'_mouseover');
			_self.find('td:eq(2)').removeClass(cfg.tabClassInnerMiddle+'_mouseover');
		});
		if(item.closed){
			liObj.find('td:eq(2)').find('div').click(function(){
				var _self=$(this);
				if(tab.find('li').length<2){
					_self.remove();
				}
				else{
					liObj.remove();
				}
			}).hover(function(){
				if(liObj.hasClass(cfg.tabClassOn)) return;
				var _self=$(this);
				_self.removeClass().addClass(cfg.tabClassClose);
			},function(){
				if(liObj.hasClass(cfg.tabClassOn)) return;
				var _self=$(this);
				_self.addClass(cfg.tabClassClose+'_noselected');
			});
		}
		else{
			liObj.find('td:eq(2)').html('');
		}
	}
	function newTab(item){
		//cfg.items.push(item);
		var liLength=tab.find('li').length;
		var nW=liLength*cfg.tabWidth;
		ccW+=cfg.tabWidth;
		//(ccW>0&&ccW<cfg.tabWidth)?ccW=cfg.tabWidth:null;
		if(nW>cfg.width){
			if(!cfg.tabScroll){
				cfg.tabScroll=true;
				scrollLeft=$('<div class="'+cfg.tabClassScrollLeft+'"></div>').click(function(){
					scrollTab(scrollLeft,true);
				});
				srcollRight=$('<div class="'+cfg.tabClassScrollRight+'"></div>');
				cW-=cfg.tabScrollWidth*2;
				tabContenter.width(cW);
				scrollLeft.insertBefore(tabContenter);
				srcollRight.insertBefore(tabContenter);
			}
			addTab(item);
			//scrollLeft.click();
			scrollTab(srcollRight,false);
		}
		else{
			addTab(item);
		}
	}
	$.each(cfg.items,function(i,o){
		addTab(o);
	});
	var cW=cfg.width;
	var scrollLeft,srcollRight;
	
	if(cfg.tabScroll){
		scrollLeft=$('<div class="'+cfg.tabClassScrollLeft+'"></div>').click(function(){
			scrollTab($(this),true);
		});
		//if(tW>cW){
			srcollRight=$('<div class="'+cfg.tabClassScrollRight+'"></div>').click(function(){
				scrollTab($(this),false);
			});
		//}
		//else{
			//srcollRight=$('<div class="'+cfg.tabClassScrollRight+'"></div>');
		//}
		cW-=cfg.tabScrollWidth*2;
	}
	var container=$('<div />').css({
		'overflow':'hidden',
		'position':'relative',
		'width':cfg.width,
		'height':cfg.tabHeight
	}).append(scrollLeft).append(srcollRight).addClass(cfg.tabClassDiv);
	var tabContenter=$('<div />').css({
		'overflow':'hidden',
		'width':cW,
		'height':cfg.tabHeight,
		'float':'left'
	}).append(tab);
	var obj=$(this).append(tabH).append(container.append(tabContenter)).append(tabContent);
	//点击第一
	tab.find('li:first td:eq(1)').click();
	return obj.extend({'addTab':addTab,'newTab':newTab});
};