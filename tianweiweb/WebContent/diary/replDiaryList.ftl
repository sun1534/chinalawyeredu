<#if friendPage.items?size gt 0>
	<#assign frdindex=0 >
	<@s.iterator value="friendPage.items" status="s">
		                      <#assign dest=userutil.getUserinfo(userid) />
								<li id="ml1">
									<div class="Mymsg-entry">
										<p class="img"><a href="../home/home.action?viewUserid=${sendUserid}"><img src="${resourcepath}${dest.logo}" width="61" height="61" alt="" /></a><span class="name"><a href="#">肖凯明</a></span></p>
										<div class="Mymsg-info">
											<div class="Mymsg-con">${content}</div>
											<p class="Mymsg-time">${createTime}<a id="m1" class="ico a-delmsg" href="javascript:void(0)" onclick="$.Jxq.delTips('#m1','#m1_',-50,20,220)">删除留言</a></p>
											<!-- 删除留言 -->
											<div style="display:none" class="webmenu" id="m1_">
												<div class="tooltips-del">
													<div class="intooltips-del">
														<span>确认删除该留言吗？</span>
														<input type="button" class="delBtn igreen" onclick="$.Jxq.removeTips('#ml1','#m1_')" title="确定" value="确定"/>
														<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#m1_')" title="取消" value="取消"/>
													</div>
												</div>
											</div>
											<!-- 删除留言 end -->
										</div>
										<span class="repaly"><a class="ico a-repmsgbook" title="回复" href="#">回复</a></span>
									</div>
								</li>
								</@s.iterator>
					
					<!-- 分页 -->
					<div class="formop">
						<div class="pager">
							 <#if friendPage.count!=0>
			      	         <span class="page-total">共 ${friendPage.count} 页</span>
			                  ${pageString}
			                </#if>
						</div>
					</div>
					<!-- 分页 end -->
                   <#else>
			<div class="box-grey" id="boxid">
					<h4>信息提示</h4>
					<div class="box-content">
						<p>暂无评论信息</p>
					</div>
			</div>	
			
		</#if>
		
		
	</div>
	
      <form name="replyForm" action="">
			
				<div class="pltitle"> 
				    日志评论(${friendPage.totalCount}) 
				</div>
									  <!-- 留言列表 -->
		       <div id="blue_con" class="tab-blue-con">
									 
					<div class="con_" style="display:;">
						<div class="form">
							<textarea id="commcontent"  style="width:500px;height:100px"  class="w550 h80"></textarea>
							<p class="formop">
							 <input onclick ="addComm(${diary.diaryid},${diary.diarytypeId},${viewUserid})" id="addcommbutton" type="button" value="发表评论"/>
							</p>
						</div>
					</div>
				</div>
			</form>