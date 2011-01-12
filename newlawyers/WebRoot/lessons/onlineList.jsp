<#import "../common/template.html" as template >
<#escape x as (x)!"">
<@template.template myheader="" title="选课中心">
<div class="gml">当前位置：<a href="index.html">首页</a>----<a href="lessoncenter.html"><strong>选课中心</strong></a></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">选课中心</div>   
     
     <TABLE cellSpacing=0 cellPadding=0 width="88%" border=0>
        <TBODY>
       
         <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> 
            <A onclick=javascript:void(0) 
                  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=0">未分类（${notfenlei.lessoncnt}个）</A> </TD>
          </TR>
      
          <@s.iterator value="@com.changpeng.lessons.util.CommonDatas@TOP_LEVEL_TYPE">
          	 <TR>
            <TD style="PADDING-LEFT: 20px" background="" height=23><img src="../images/jiantou.gif" width="4" height="10" /> 
            <A onclick=javascript:void(0) 
                  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${typeid}">${typename}（${lessoncnt}个）</A> </TD>
          </TR>
          <@s.if test="haschild">
         
            <TR id=LM1>
          <TD><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                  <@s.iterator value="children">
                  <TR>
                    <TD style="PADDING-LEFT: 40px" height=23><img src="../images/dian_.gif" width="3" height="3" /> 
                    <A title=文件夹 
                        href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${typeid}" 
                        >${typename}</A> </TD>
                  </TR>
                  <TR>
                    <TD background="" height=3></TD>
                  </TR>
                    </@s.iterator>
                </TBODY>
            </TABLE></TD>
          </TR>
       
          </@s.if>
          </@s.iterator>
          
        
        

        </TBODY>
    </TABLE>
     
   
  </div>
  <div class="con_right2 left">
    <div class="con_right2_title"><h2>分类：<a href="#">全部</a></h2><span><a href="#">详细显示</a>&nbsp;&nbsp;<a href="#">列表显示</a></span></div>
    <div class="star">
	    <div class="startitle">
排序：<a href="#">按热门</a>&nbsp;&nbsp;<a href="#">按年份</a>&nbsp;&nbsp;<a href="#">按评分</a>&nbsp;&nbsp;&nbsp;&nbsp;显示：<a href="#">全部</a>&nbsp; <a href="#">必修</a>&nbsp; <a href="#">选修</a>&nbsp; <a href="#">免费</a>&nbsp; <a href="#">收费</a></div>

<@s.iterator value="page.items">
	<div class="t_n">
	<a href="#"><img src="../images/ico2.gif" width="150" height="125" /></a>
 <div class="list3_ul_ul">
 	       <ul>
           <h3><a href="#"><strong>${title}</strong></a></h3>
           <p class="num_con"><span><strong>8.0</strong></span>( 58人评)</p>
				<li>主讲人：<span>${teachers}</span></li>
                <li>职务：<span>人民大学法学院  教授</span></li>
                <li>提供者：<span>深圳律协  时间：2010年</span></li>	
                <li>类型：<span>民事诉讼</span></li>								
		  </ul>
        </div>
        <div class="st">
    <a href="lessonplay.html"><div class="gk"></div></a>
        <div class="oper"><a href="#">课评</a>&nbsp;<a title="" href="#" >收藏</a>&nbsp;<a href="#">选购（￥50）</a></div></div>
    </div>
</@s.iterator>
    <div class="page_01"><form action="" method="get">
		  <span><a href="#">首页</a></span>  <span><a href="#">上一页</a></span>  <span><a href="#">下一页</a></span>  <span><a href="#">尾页</a></span>  <span><a href="#">共6页</a></span>
		</form></div>
    </div>
    
  </div>
</div>
</@template.template>
</#escape>	
