<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

                   <table class="tableBox" onclick="$('#errors').toggle()">
                        	<thead>
								<tr>
									<th>PDP失败情况实时总表</th>
                                 </tr>
                            </thead>
                        </table>
					<table class="tableBox" id="a"> 
                      <thead> 
                        <tr> 
                          <th ></th> 
                          <th colspan="2">当天累加值（${date1 }）</th> 
                          <th colspan="2">本时间段增加值（${date2}）</th> 
                          <th colspan="2">上次时间段增加值（${ date3}）</th> 
                        </tr> 
                      </thead> 
                      <tbody id="errors"> 
                        <tr > 
                          <td align="center" >错误类型</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#33用户设置错误 </td> 
                          <td>${errorallstat1.usercount33 }</td> 
                          <td>${errorallstat1.errorcount33 }</td> 
                          <td>${errorallstat2.usercount33 }</td> 
                          <td>${errorallstat2.errorcount33 }</td> 
                          <td>${errorallstat3.usercount33 }</td> 
                          <td>${errorallstat3.errorcount33 }</td> 
                        </tr> 
                     <!--
                        <tr> 
                          <td align="center" >华为#4328错误 </td> 
                          <td>${errorallstat1.usercount4328 }</td> 
                          <td>${errorallstat1.errorcount4328 }</td> 
                          <td>${errorallstat2.usercount4328 }</td> 
                          <td>${errorallstat2.errorcount4328 }</td> 
                          <td>${errorallstat3.usercount4328 }</td> 
                          <td>${errorallstat3.errorcount4328 }</td> 
                        </tr>
                        -->         
                        <tr> 
                          <td align="center" >华为#4329错误 </td> 
                          <td>${errorallstat1.usercount4329 }</td> 
                          <td>${errorallstat1.errorcount4329 }</td> 
                          <td>${errorallstat2.usercount4329 }</td> 
                          <td>${errorallstat2.errorcount4329 }</td> 
                          <td>${errorallstat3.usercount4329 }</td> 
                          <td>${errorallstat3.errorcount4329 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#27漫游错误 </td> 
                           <td>${errorallstat1.usercount27 }</td> 
                          <td>${errorallstat1.errorcount27 }</td> 
                          <td>${errorallstat2.usercount27 }</td> 
                          <td>${errorallstat2.errorcount27 }</td> 
                          <td>${errorallstat3.usercount27 }</td> 
                          <td>${errorallstat3.errorcount27 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#29RADUIAS错误 </td> 
                         <td>${errorallstat1.usercount29 }</td> 
                          <td>${errorallstat1.errorcount29 }</td> 
                          <td>${errorallstat2.usercount29 }</td> 
                          <td>${errorallstat2.errorcount29 }</td> 
                          <td>${errorallstat3.usercount29 }</td> 
                          <td>${errorallstat3.errorcount29 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#38网络错误 </td> 
                           <td>${errorallstat1.usercount38 }</td> 
                          <td>${errorallstat1.errorcount38 }</td> 
                          <td>${errorallstat2.usercount38 }</td> 
                          <td>${errorallstat2.errorcount38 }</td> 
                          <td>${errorallstat3.usercount38 }</td> 
                          <td>${errorallstat3.errorcount38 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >其他错误 </td> 
                          <td>${errorallstat1.usercountothers }</td> 
                          <td>${errorallstat1.errorcountothers }</td> 
                          <td>${errorallstat2.usercountothers }</td> 
                          <td>${errorallstat2.errorcountothers }</td> 
                          <td>${errorallstat3.usercountothers }</td> 
                          <td>${errorallstat3.errorcountothers }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >总量 </td> 
                          <td>${errorallstat1.usercountall }</td> 
                          <td>${errorallstat1.errorcountall }</td> 
                          <td>${errorallstat2.usercountall }</td> 
                          <td>${errorallstat2.errorcountall }</td> 
                          <td>${errorallstat3.usercountall }</td> 
                          <td>${errorallstat3.errorcountall }</td> 
                        </tr> 
                      </tbody> 
                    </table> 

 
