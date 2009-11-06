 $.Jxq = {  
    version : 'Jxq 2.0',	
	/*弹出层
	引用代码
	<input type="button" onclick="$.Jxq.ShowDialog('#a',500,-250,-50,100);" value="自定义弹出层的大小 500*100"/>
	<input type="button" onclick="$.Jxq.ShowDialog('#a',300,-150,-50);" value="自定义弹出层的大小 300 高度自适应"/>
	<input type="button" onclick="$.Jxq.ShowDialogRB('#a',200,200);" value="自定义弹出层的位置右下角"/>
	<input type="button" onclick="$.Jxq.ShowDialogRT('#a',100,100);" value="自定义弹出层的位置右上角"/>
	<div id="a" style="display:none">
		<div class="">111<br />111<br />111<br />
		</div>
		<a href="#" onclick="$.Jxq.HideDialog('#a')">11</a>
	</div>
	*/
	ShowDialog:function (e,w,ml,mt,h){
		/*显示指定元素
		e		指定显示元素ID
		w   指定显示元素width
		h	指定显示元素height
		ml		指定显示元素margin-left
		mt		指定显示元素margin-top
		用于屏幕居中处理
		*/
		if(!h){
			$.blockUI(
				{
				 message: $(e),
				 css:
				 {
					 top:			'15%',
					 left:			'50%',
					 marginLeft:	ml,
					 marginTop:		'auto',
					 width:			w,
					 height:		'auto'
				 }
				}
			);
		}else{
			$.blockUI(
				{
				 message: $(e),
				 //showOverlay:false,控制是否有遮盖层
				 css:
				 {
					 top:			'40%',
					 left:			'50%',
					 marginLeft:	ml,
					 marginTop:		mt,
					 width:			w,
					 height:		h
				 }
				}
			);
			}
	},
	/*
	隐藏指定元素 可以设置时间
	*/
	HideDialog : function(e,time){
		if(!time){$.unblockUI();
		}else{
		setTimeout(function(){$.unblockUI();},time);
		}
	},

	/*设定时间 显示另外一个div*/
	SetTimeShowDialog: function(e,time){
			setTimeout(function() {$.unblockUI({onUnblock:function(){$.blockUI({message:$(e)})}});},time)
	},

	/*点击层外关闭div*/
	OutHideDialog : function (){
		 $('.blockOverlay').attr('title','点击关闭').click($.unblockUI);
	},
	/*
	指定显示右下角
	*/
	ShowDialogRB :function (e,w,h){
		/*显示指定元素
		**IE6下有点小问题**
		e		指定显示元素ID
		w   指定显示元素width
		h	指定显示元素height
		用于屏幕居中处理
		*/
		$.blockUI(
			{
				message: $(e),
				//fadeIn: 500,
				fadeOut: 500,
				//timeout: 2000,
				showOverlay: false,
				centerY: 0,
				//centerX: false,
				css: {
					width:		w,
					height:		h,
					bottom:		'10px',
					left:		'',
					right:		'10px',
					top:		''

				}
			}
		);
	},
	/*
	指定显示右上角
	*/
	ShowDialogRT :function (e,w,h){
		/*显示指定元素
		e		指定显示元素ID
		w   指定显示元素width
		h	指定显示元素height
		用于屏幕居中处理
		*/
		$.blockUI(
			{
				message: $(e),
				//fadeIn: 500,
				fadeOut: 500,
				//timeout: 2000,
				showOverlay: false,
				centerY: 0,
				css: {
					width:		w,
					height:		h,
					top:		'10px',
					left:		'',
					right:		'10px',
					bottom:		'',
					_position:	'absolute'

				}
			}
		);
	},
	/* foucus 当前 文本框 的值 */
	focusTxt: function (txtem,defalt_val){
		/*
		txtem 所需清除vlaue的元素Id
		defalt_val 清除默认的value的值
		引用实例
		<input type="text" id="SearchInput" value="搜一下" />
		$.Jxq.focusTxt('#SearchInput','搜一下');
		$.Jxq.focusClass('ie_focus');
		*/
		$(txtem).val(defalt_val).focus(function(){
		var val = $(this).val();
		if(val == defalt_val){
		$(this).val('');
		}
		}).blur(function(){
		var val = $(this).val();
		if(val == ''){
		$(this).val(defalt_val);
		}
		})
	},

	focusClass: function (style_){
		if ($.browser.msie&&$("#loginForm")){
		 $("input[@type='text'], input[@type='password'], select,textarea").focus(function() {$(this).addClass(style_);})
		$("input[@type='text'], input[@type='password'],  select,textarea").blur(function() {$(this).removeClass(style_);})
      }


	},



	/* 折叠 */
	tabtoggle : function(e,o,style,style_){
		/*
		*参数说明：
		.需要折叠的div的ID
		style 隐藏起来的当前元素的样式
		style_ 展开起来的当前元素的样式
		引用实例
		<a href="javascript:void(0)" class="up" onclick="$.Jxq.tabtoggle(this,'#1','up','down')">展开</a></div>
		<div id="1" style="display:none;">
			<p>我是被隐藏的内容1，很简单可以循环使用哦</p>
		</div>
		引用 ：$.Jxq.tabtoggle(this,'#1','up','down')"
		*/
		if($(o).is(":hidden")){
			$(e).text('收缩');
			$(e).attr('title','收缩');
			$(o).slideDown("fast",function(){ $(o).show(); }); 
			$(e).addClass(style_);
			$(e).removeClass(style);
		}else{
			$(e).text('展开');
			$(e).attr('title','展开');
			$(o).slideUp("fast",function(){ $(o).hide(); }); 
			$(e).addClass(style);
			$(e).removeClass(style_);
		}

	},

	/* 折叠 留言*/
	togglemsg : function(e,o,style,style_){
		/*
		*参数说明：
		.需要折叠的div的ID
		style 隐藏起来的当前元素的样式
		style_ 展开起来的当前元素的样式
		引用实例
		<a href="javascript:void(0)" class="up" onclick="$.Jxq.tabtoggle(this,'#1','up','down')">展开</a></div>
		<div id="1" style="display:none;">
			<p>我是被隐藏的内容1，很简单可以循环使用哦</p>
		</div>
		引用 ：$.Jxq.tabtoggle(this,'#1','up','down')"
		*/
		if($(o).is(":hidden")){
			$(e).text('收起回复');
			$(e).attr('title','收起回复');
			$(o).slideDown("fast",function(){ $(o).show(); }); 
			$(e).addClass(style_);
			$(e).removeClass(style);
		}else{
			$(e).text('回复');
			$(e).attr('title','回复');
			$(o).slideUp("fast",function(){ $(o).hide(); }); 
			$(e).addClass(style);
			$(e).removeClass(style_);
		}

	},
	
	/* tab 标签*/
	tab : function (tab,tab_tag,tag_con_box,tag_con,act){
	/*
	 * tab 需要切换标签的ID
	 * tab 需要切换的标签元素
	 * tab 需要切换所有内容最外层的父元素
	 * tab 需要切换所有内容的元素
	 * act 标签切换的方式 鼠标点击（click），还是鼠标over（mouseover）,默认是点击，参数可以不写
	 引用实例
		<div class="tab-normal-tag">
			<ul id="normal">
				<li class="current"><a href="javascript:void(0)"><span>标签一</span></a></li>
				<li><a href="javascript:void(0)"><span>标签一</span></a></li>
				<li><a href="javascript:void(0)"><span>标签一</span></a></li>
			</ul>
		</div>
		<div id="normal_con" class="tab-normal-con">
			<div class="con_" style="display:;">
				我是标签一的内容
			</div>
			<div class="con_" style="display:none;">
				我是标签二的内容
			</div>
			<div class="con_" style="display:none;">
				我是标签三的内容
			</div>
		</div>
		引用   $.Jxq.tab("#normal","li","#normal_con",".con_","mouseover");
	 */
	if(!act){ act="click"};
	if(act=="click"){
	   $(tab).find(tab_tag).each(function(i){
	   $(tab).find(tab_tag).eq(i).click(
	   function(){
		$.Jxq.current(this);
		//$(tag_con_box).find(tag_con).eq(i).show().siblings(tag_con).hide();
		$(tag_con_box).find(tag_con).eq(i).show().siblings(tag_con).hide(); 
		})
	   })
	   }
	if(act=="mouseover"){
	   $(tab).find(tab_tag).each(function(i){
	   $(tab).find(tab_tag).eq(i).mouseover(
	   function(){
		$.Jxq.current(this);
		//$(tag_con_box).find(tag_con).eq(i).show().siblings(tag_con).hide();
		$(tag_con_box).find(tag_con).eq(i).show().siblings(tag_con).hide(); 
		})
	   })
	   }
	},
	/*当前元素高亮
		引用实例
		<div>
		<a onclick="$.Jxq.current(this,'current')" href="javascript:void(0)" class="">送给学生礼物</a>
		<a onclick="$.Jxq.current(this,'current')" href="javascript:void(0)" class="current">送给老师礼物</a>
		<a onclick="$.Jxq.current(this,'current')" href="javascript:void(0)" class="">送给家长礼物</a>
		</div>
	*/
	current :function (e,lightstyle){
	$(e).addClass("current").siblings().removeClass("current");
	},
	highCurrent :function (e,lightstyle){
	$(e).addClass("current").siblings().removeClass("current");
	},
	/* 表格行 高亮 
	$.Jxq.tableHover('#tableHover','hover');
	*/
	tableHover : function(e,Hover){
		/*给tbody 赋id ，标准的table结构才可以*/
		//$(e + ">tr:odd").addClass(Hover);
		//$(e + ">tr:even").addClass(Hover);
		//$(e + ">:nth-child(even)").addClass(Hover);
		//$(".tableHover tr:nth-child(even)").addClass("red");
		$(e + ">tr").hover(function(){
			//$(e + ">:nth-child(even)").addClass('red');
			$(this).addClass(Hover);
			
		}, function(){
			//$(e + ">:nth-child(even)").addClass('red');
			$(this).removeClass(Hover);
			
		});
		$(e + ">tr").click(function(){
			$.Jxq.current(this);
		});
	   },

	
	/* 2级菜单
		引用实例
        <ul id="levelmemu">
            <li><a href="#">家校圈</a>
                <ul class="submenu">
                    <li><a href="#">家校圈1</a></li>
                    <li><a href="#">家校圈2</a></li>
                </ul>
            </li>
        </ul>
		$.Jxq.levelmemu('#levelmemu');
	*/
	levelmemu : function (menu){
		$(menu+" li:has(ul.submenu)").hover(function(){
			$(this).children("ul.submenu").stop(true,true).slideDown("normal");
        },function(){
		    $(this).children("ul.submenu").slideUp("fast");
     });
	},
	
	/* 去处链接虚线
	引用实例
	$.Jxq.hideFocus();
	*/
	hideFocus :function (){
		$("a").attr("hideFocus", "true");
	},
	

	/*
	添加 代码片段
	引用实例
	<div class="htmlAddBox"></div>
	<a href="javascript:void(0)" id="AddHtml" onclick="javascript:$.Jxq.AddHtml('.htmlAddBox',6)">添加</a>
	*/
	/* 添加的html */
	AddHtml :function(em,max){
		var len=$('.addOptions').size();
		len++;
		var html=
		"<p class=\"addOptions\" id=Add" + len + ">"+
		"<input  type=\"text\" value=\"\"/>"+
		"<a href=\'javascript:void(0)\' onclick=\"javascript:$.Jxq.DelHtml("+ len +");\">删除</a>"+
		"</p>";
		if(len>max){
			alert("最对只能添加"+max+"项");
			$('#AddHtml').hide();
		}else {
			$(em).append(html);
		}
	},

	/*删除html 片段*/
	DelHtml :function (len){
		var n = "Add" + len;
		$("#"+n).remove();
		if($(".addOptions").size()==0){
			$('#AddHtml').show();
		}
	},
	/*
	div 等高
	添加 代码片段
	引用实例
	<div id="wrap">
		<div class="col" id="col1">
			1
		</div>
		<div class="col" id="col2">
			2
		</div>
		<div class="col" id="col3">
			2
		</div>
	</div>
	<script type="text/javascript">
	<!--
	$.Jxq.equalHeight($(".col"));
	//-->
</script>
	*/
	equalHeight :function (group) {
	tallest = 0;
	group.each(function() {
		thisHeight = $(this).height();
		if(thisHeight > tallest) {
			tallest = thisHeight;
		}
	});
	group.height(tallest);
	},
	/*下拉菜单 2级别菜单
	添加 代码片段
	引用实例
	<div id="dropmenu">
		<ul class="dropmenu">
			<li class="submenu">
			  <a href="#">菜单一</a>
			  <ul>
				<li><a href="#">我是子菜单</a></li>
			  </ul>
			</li>
			<li class="submenu">
				<a href="#">菜单2</a>
				<ul>
				<li><a href="#">我是子菜单</a></li>
				<li><a href="#">我是子菜单</a></li>
				<li><a href="#">我是子菜单</a></li>
			  </ul>
			</li>
			<li class="submenu"><a href="#">菜单3</a></li>
			<li class="submenu"><a href="#">菜单4</a></li>
		  </ul>
      </div>
      <script language="JavaScript">
        $.Jxq.submenu(".dropmenu li.submenu","slow")
      </script>
	*/

	submenu :function (emstyle,speed){
		$(emstyle).hover(
			function() { $('ul', this).fadeIn(speed).css('display', 'block');},
			function() { $('ul', this).fadeOut(speed).css('display', 'none'); });
	},
	/*
	Tips 效果
	添加 代码片段
	引用实例
	<ul class="flyTipMenu">
		<li>
			<a href="#">Home</a>
			<span class="flyTip">Go home!</span>
		</li>
		<li>
			<a href="#">About Us</a>
			<span class="flyTip">Get to know our team!</span>
		</li>
	</ul>
	 $.Jxq.flyTips("ul.flyTipMenu li",".flyTip","100",'0','fast');
	*/
	flyTips :function(em,flyem,left,top,speed){
		$(em).hover(function() {
		$(this).find(flyem).stop()
		.animate({left:left,top:top, opacity:1}, speed).css("display","block")
		}, function() {
			$(this).find(flyem).stop().animate({left: "0", opacity: 0},  speed)
		});
	},
	
	/*
	删除 Tips 效果
	添加 代码片段
	引用实例
	<a onclick="$.Jxq.delTips('#menu','#web_menu',-50,0,300)" href="javascript:void(0)" class="ico a-delfriend"  id="menu">
			删除好友
	</a>
	<div style="display:none" class="webmenu" id="web_menu">
		<div class="tooltips-del">
			<div class="intooltips-del">
				<span>你确认要删除好友吗？</span>
				<input type="button" class="submitBtn iorange" onclick="$.Jxq.removeTips('#menu','#web_menu')" title="确定" value="确定"/>
				<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#web_menu')" title="取消" value="取消"/>
			</div>
		</div>
	</div>
	*/
	delTips:function(ObjectID,elm,top,left,width)
	{
		var myValues = $(ObjectID).position();
		$(elm).css("display","block");
		$(elm).css("top",myValues.top+top);
		$(elm).css("left",myValues.left+left);
		$(elm).css("position","absolute");
		$(elm).css("z-index","3");
		$(elm).css("opacity","0.9");
		$(elm).css("width",width);
	},

	removeTips : function(em,o)
	{
		$(em).fadeTo("slow", 0.22, function(){$(em).remove()});
		$(o).fadeTo("slow", 0.22, function(){$(o).hide()});
	},

	hideTips :function(em)
	{
		$(em).fadeTo("slow", 0.22, function(){$(em).hide()});
	},
	objPosition:function(ObjectID,elm,top,left,width)
	{
		var myValues = $(ObjectID).position();
		$(elm).css("display","block");
		$(elm).css("top",myValues.top+top);
		$(elm).css("left",myValues.left+left);
		$(elm).css("position","absolute");
		$(elm).css("z-index","3");
		$(elm).css("opacity","0.9");
		$(elm).css("width",width);
	},

	/* 滚动效果
	添加 代码片段
	引用实例
	<div  style="height:20px;width:200px;background-color:#ffff99;overflow: hidden;margin:100px;">
		<ul id="scrollcontent">
			<li><a href="">aaa</a></li>
			<li><a href="">bbb1333333333</a></li>
			<li><a href="">ccc1111111</a></li>
			<li><a href="">ddd111111111111111111111111</a></li>
		</ul>
	</div>
	$.Jxq.marquee("#scrollcontent",1500);
	*/
	marquee :function(o,speed){
		var o;
		flag=1;
		setInterval(function(){$.Jxq.scrollul(o)},speed);
		$(o).mouseover(function(){
			 flag =0;
		})
		$(o).mouseout(function(){
			 flag =1;
		})

	},

	scrollul:function(em){
		if(flag) {
		   var obj= $(em+">li").eq(0);
		   var clones = obj.clone();
		   obj.fadeOut("slow",function(){
			 clones.appendTo($(em));
			obj.remove();
		   });
		  }
	}

 }
/*marquee
添加 代码片段
引用实例
 div id="marqueeInner" style="overflow:hidden;height:22px;width:400px; border:1px solid #dde5bc; overflow:hidden;">
	<div id="marqueeInfo">
		<ul>
			<li><a href="#">代码来自互联网，我只不过稍加整理，希望大家多提意件……2</a></li>
			<li><a href="#">完全兼容IE, FF, Opera, 其它的还未经测试……1</a></li>
		</ul>
	</div>
	<div id="marqueeOuter"></div>
</div>
<script type="text/javascript">
MarqueeBox('50');
</script>
*/

var MarqueeBox =function (speed){
	var marqueeInner=document.getElementById("marqueeInner");
	var marqueeOuter=document.getElementById("marqueeOuter");
	var marqueeInfo=document.getElementById("marqueeInfo");

	marqueeOuter.innerHTML=marqueeInfo.innerHTML;
	function Marquee(){
		if(marqueeOuter.offsetTop-marqueeInner.scrollTop<=0)
			marqueeInner.scrollTop-=marqueeInfo.offsetHeight
		else{
			marqueeInner.scrollTop++
		}
	}
	var MyMar=setInterval(Marquee,speed);
	marqueeInner.onmouseover=function() {clearInterval(MyMar)};
	marqueeInner.onmouseout=function() {MyMar=setInterval(Marquee,speed)};

}

/*
<div class="FoucsList">
	<ul id="FoucsList">
		<li><a href="#">444444444444</a></li>
		<li><a href="#">3333333333</a></li>
		<li><a href="#">222222222</a></li>
		<li><a href="#">11111111111111</a></li>
	</ul>
</div>
	marqueeOne('FoucsList','2000');
*/
var marqueeOne =function (em,time){
	var scrollnews = document.getElementById(em);
	var lis = scrollnews.getElementsByTagName('li');
	var ml = 10;
	var timer1 = setInterval(function(){
		var liHeight = lis[0].offsetHeight;
		var timer2 = setInterval(function(){
		 scrollnews.scrollTop = (++ml);
		 if(ml == liHeight){
			clearInterval(timer2);
			scrollnews.scrollLeft = 0;
			ml = 0;
			lis[0].parentNode.appendChild(lis[0]);
		 }
	  },10);
	},time);
}



