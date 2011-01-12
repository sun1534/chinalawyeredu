/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.jifen.service.LxskrecsService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.Lxskrecs;
import com.changpeng.models.SysUnionparams;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class SetSKRecsRequest extends ElearningRequests {
	private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Log LOG = LogFactory.getLog(SetSKRecsRequest.class);
	private int groupid;

	public SetSKRecsRequest(int groupid) {
		this.groupid = groupid;
	}

	// 设置考勤结果,cardno和lessonid判断唯一性，如果送上来重复的数据，更新lxskrecs中的
	// timelong字段。同时判断lessons表中的刷卡的时长设限来决定积分的个数
	public String requestService(org.dom4j.Element method) {
		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		try {
			Element skrecselement = method.element("skrecs");
			Iterator iterator = skrecselement.elementIterator("skrec");

			LOG.debug("==" + iterator);

			LxskrecsService recsservice = (LxskrecsService) Globals.getBean("lxskrecsService");
			BasicService basicService = (BasicService) Globals.getBean("basicService");
			SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, groupid);
			String nianshen = "01-01";
			if (params != null)
				nianshen = params.getNianshen();
			else {
				LOG.warn("GROUPID:" + groupid + "在sys_unionparmas中的数据不存在");
			}
			JifenTime jifentime = CommonDatas.getJifenTime(0, nianshen);
			int jifenyear = jifentime.getNianshenyear();
			if (iterator != null) {
				List<Lxskrecs> lxskrecses = new ArrayList<Lxskrecs>();
				int i = 0;
				while (iterator.hasNext()) {

					try {

						Element skrec = (Element) iterator.next();
						String cardno = skrec.elementText("cardno");
						int lessonid = Integer.parseInt(skrec.elementText("lessonid").trim());
						String skdate = skrec.elementText("skdate");
						String skmode = skrec.elementText("skmode");

						Lxskrecs skrecs = new Lxskrecs();
						skrecs.setKahao(cardno);
					
						try {
							LOG.debug("刷卡时间:" + skdate);
							skrecs.setSkdate(new java.sql.Timestamp(df.parse(skdate).getTime()));
						} catch (Exception e) {
							skrecs.setSkdate(new java.sql.Timestamp(System.currentTimeMillis()));
						}
						
						if(skrecs.getSkdate().getTime()>jifentime.getStart().getTime()){
							skrecs.setJifenyear(jifenyear);
						}else
							skrecs.setJifenyear(jifenyear-1);
						
						skrecs.setSkmode(skmode);
						skrecs.setLessonid(lessonid);
						skrecs.setGroupid(groupid);
						lxskrecses.add(skrecs);

						LOG.debug("刷卡记录:" + (i++) + ":卡号:" + cardno + ",课程:" + lessonid + ",时间:" + skdate + ",刷卡:"
								+ skmode);
						// 判断这次的刷卡是否存在，如果存在的话，则更新timelong字段,根据skdate的前后时间差
					} catch (Exception ee) {
						LOG.error("数据解析有误:" + ee.getMessage());
					}
				}
				recsservice.skrecsBatch(lxskrecses);
			}

			result.append("<respcode>0</respcode>");
			result.append("<respmsg>学分上传处理成功</respmsg>");

		} catch (Exception e) {
			LOG.error("积分上传有误:" + e.getMessage());
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>上传考勤结果异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append("</response>");
		return result.toString();
	}

}
