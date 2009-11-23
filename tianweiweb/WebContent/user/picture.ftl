<#import "../common/home.ftl" as home>
<@home.home myheader="user/picture_h.ftl">
<style type="text/css">

form,ul,ol,h1,h2,h3,h4,h5,h6,p{margin:0;padding:0;}
input,textarea,select,button{font-size:12px;}
img{border:none;}
em{font-style:normal;}
cite,small,address{font-size:12px;font-style:normal;color:#999;}
a{color:#00f;text-decoration:underline;}

/*页面*/
#box{
z-index:19999;width:600px;margin:0 auto;left:200px;top:80px;

}
#box h1{font-size:40px;line-height:50px;height:90px;}
#box h3{font-size:16px;padding:20px 0 10px;}
code{font-size:12px;color:#333;margin:10px;display:block;}
#box ol{margin:0;padding:0;list-style-position:inside;list-style-type:decimal;}
#box ol li{margin:0 0 20px 0;line-height:18px;}
#box ol li span{color:#00f;text-decoration:underline;cursor:pointer;}
#box ol li strong{display:block;}
#box p{line-height:18px;}

/*演示内容*/
#setFace{position:relative;height:526px;width:556px;margin:10px 0;}
#imgBox_pre{position:absolute;right:-127px;top:-10px;width:100px;}
#imgBox_pre strong{display:block;font-size:12px;text-align:center;}
#imgBox_pre button{display:block;width:70px;margin:0 auto;}
#imgBox_pre div{width:48px;height:48px;margin:2px auto 20px;overflow:hidden;border:#ccc 2px solid;}
#imgBox_pre div img{margin:3px;}
#imgBox{float:left;border:#ccc 2px solid;width:200px;height:200px;}
#imgCut{border:#f90 1px dashed;width:100px;height:100px;position:absolute;top:20px;left:20px;cursor:move;}
#faceImg{opacity:0.5;filter:alpha(opacity=50);}
#data{clear:both;padding-top:20px;}
</style>

<div class="in-main">
	<div class="title-h"><h3>头像修改</h3></div>
	<div class="title-info">
		<div class="m-tag">
			<ul id="partents-mintag">
				<li><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
									<#if currentRole=1>
					<li><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>	
						</#if>
					<#if currentRole=2>
					<li><a title="企业形象" href="userimages!input.action"><span>企业形象</span></a></li>	
					<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
					</#if>
				<li><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
				<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
				<li><a title="认证管理" href="userverify.action"><span>认证管理</span></a></li>
			</ul>
		</div>
	</div>

	<div class="change_img">
		<form id="setFace" name="setFace" method="post" action="picturecut.action" style="width:600">
		    <div id="imgBox"><img  id="faceImg" src="${resourcepath}${picurl}" />
		    	<div id="imgCut"></div>
		    </div>
		    <div id="imgBox_pre">
		    	<strong>头像预览</strong>
		    	<div>
		    		<img id="faceImg_pre" src="${resourcepath}${picurl}" />
		    	</div>

		    	<button class="delBtn igreen" type="submit" style="margin-bottom:10px;">保存头像</button>
				<button class="cancerBtn " type="button" onclick="location.href='userimages!input.action'">取消</button>
		    </div>
		    <div id="data">
			    <input name="left" type="hidden" id="left"/>
			    <input name="top" type="hidden" id="top" />
			    <input name="width" type="hidden" id="width" />
			    <input name="height" type="hidden" id="height" />
			    <input name="photoname" type="hidden" id="photoname" value="${picurl}"/>
		    </div>
	 	</form>
	</div>
</div>
<script type="text/javascript">
var scale=1;
var imgH=new Number;
var imgW=new Number;
var src=$("#faceImg").attr("src");
var scale=48/96;
$("#faceImg").load(function(){
  imgH=$("#faceImg").height();
  imgW=$("#faceImg").width();
  $("#imgBox").width(imgW).height(imgH);
  $("#setFace").width(560).height(imgH+4);
  $("#imgCut").css("background","url("+src+") -20px -20px no-repeat");
  $("#faceImg_pre").height(imgH*scale);
  $("#imgBox_pre div").scrollTop(20*scale).scrollLeft(20*scale);
});
$("#imgCut").draggable({
  containment:$("#faceImg"),
  drag:function(){
    var temp_top=$(this).offset().top-$("#faceImg").offset().top;
	var temp_left=$(this).offset().left-$("#faceImg").offset().left;
    scale=48/$(this).height();
    $("#faceImg_pre").height(imgH*scale);
	$(this).css("background","url("+src+") -"+(temp_left+1)+"px -"+(temp_top+1)+"px no-repeat");
    $("#imgBox_pre div").scrollTop(temp_top*scale).scrollLeft(temp_left*scale);
  },
  stop:function(){
    $("#width").val($(this).width());
	$("#height").val($(this).height());
	$("#left").val(($(this).offset().left-$("#faceImg").offset().left));
	$("#top").val(($(this).offset().top-$("#faceImg").offset().top));
  }
});
$("#imgCut").resizable({
  containment:$("#faceImg"),
  handles:"all",
  knobHandles:true,
  aspectRatio:true,
  minWidth:96,
  minHeight:96,
  resize:function(){
    var temp_top=$(this).offset().top-$("#faceImg").offset().top;
	var temp_left=$(this).offset().left-$("#faceImg").offset().left;
    scale=48/$(this).height();
    $("#faceImg_pre").height(imgH*scale);
	$(this).css("background","url("+src+") -"+(temp_left+1)+"px -"+(temp_top+1)+"px no-repeat");
    $("#imgBox_pre div").scrollTop(temp_top*scale).scrollLeft(temp_left*scale);
  },
  stop:function(e,ui){
    $("#width").val($(this).width());
	$("#height").val($(this).height());
	$("#left").val(($(this).offset().left-$("#faceImg").offset().left));
	$("#top").val(($(this).offset().top-$("#faceImg").offset().top));
  }
});
</script>
</@home.home>