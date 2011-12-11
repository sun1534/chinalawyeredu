/**
 * DxSendMain.java
 */
package main;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import service.OrderConstant;
import service.OrderService;
import service.SendService;
import sms.Sms;

import common.Globals;

import entity.CityPrompt;
import entity.CityPromptUpdate;
import entity.CityTemplateContent;
import entity.DzjcAllHistory;
import entity.UserDzjcVo;
import entity.UserOrder;

/**
 * <pre>
 * 1、获取所有的dzjc列表
 * 2、在dzjc_all_history里面和dzjc_all表匹配，如果dzjc_all_history里面is_handled不再在dzjc_all表里，则认为已经处理了,is_handled设置为1并时间设置为当前时间
 * 3、轮询dzjc表，同时判断车牌、类型和时间是否在all_history里面并满足handled=0，如果是，那个dzjc_all_history里的handle_days加1并下发违章短信。如果不在,直接下发违章
 * 4、
 * 
 * 
 * </pre>
 * 
 * @author 刘哈哈 Apr 17, 20119:39:16 PM
 * 
 */
public class DxSendMain {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(DxSendMain.class);

	private SendService sendService = null;
	private OrderService orderservice = null;
	private Map<String, CityTemplateContent> templist = null;
	private Map<String, CityPrompt> promtlist = null;
	private CityPromptUpdate cpu;

	public DxSendMain() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DxSendMain main = new DxSendMain();
		// main.test();
		main.process();

	}

	public void process() {
		sendService = (SendService) Globals.getWebBean("sendService");
		orderservice = (OrderService) Globals.getWebBean("orderService");
		// 更新昨天违章，但是到今天已经不再违章的情况，这类短信不下发
		int s = sendService.setHasBeenHandled();
		LOG.info("更新" + s + "条违章为已处理");
		// 得到地市的短信模板信息
		templist = sendService.getTemplateList();
		LOG.info("得到短信下发模板处理完毕");
		// 得到该地市要下发的公共信息
		promtlist = sendService.getPromptList();
		LOG.info("得到地市要下发的公共信息完成");

		int weekday = DateUtil.getWeekday();
		cpu = sendService.getWeekContent(weekday);
		if (cpu != null)
			LOG.info("周" + weekday + "要发送的公共内容为:" + cpu.getContent());
		else
			LOG.warn("周" + weekday + "要发送的公共内容为空");
		// 得到订购用户今天所有的违章记录，和违章车牌表绑定
		// Map<String, List<DzjcAllHistory>> todaylist =
		// sendService.getTodayList();

		//		
		// 得到所有的订购用户总数,没有退订的
		int usercount = orderservice.getAllOrderUserCount();
		LOG.info("订购的用户总数:::" + usercount);
		int smscount = 0;
		int allcount = 0;
		if (usercount != 0) {// 有订购用户的情况下,主要是对没违章用户的处理

			int getcount = ((usercount - 1) / SendConstant.PAGESIZE) + 1;
			LOG.info("总计处理次数:::" + getcount);
			for (int i = 0; i < getcount; i++) {
				int startIndex = i * SendConstant.PAGESIZE;
				List list = orderservice.getAllOrdreUsers(startIndex, SendConstant.PAGESIZE); // 得到这批用户并匹配是否
				int len = (list == null ? 0 : list.size());

				// len=1; //测试用而已
				for (int j = 0; j < len; j++) {
					String smscontent = ""; // 要下发的短信内容
					UserOrder uo = (UserOrder) list.get(j);

					// 这里得到违章信息,一个个的来处理,而不是所有的
					if ((uo.getAreacode() == null || uo.getAreacode().equals(""))) {
						LOG.warn(uo.getMobile() + "没有上传区域信息,忽略");
						continue;
					}
					if (uo.getAreacode() != null
							&& (!OrderConstant.AREA_DATABASE.containsKey(uo.getAreacode()) && !OrderConstant.CITY_DATABASE
									.containsKey(uo.getAreacode()))) {
						LOG.warn(uo.getMobile() + "上传的区域信息不对" + uo.getAreacode() + ",忽略");
						continue;
					}
					if (uo.getChepai() == null || uo.getChepai().equals("")) {
						LOG.warn(uo.getMobile() + "没有上传车牌信息,忽略");
						continue;
					}
					if (uo.getChepaileixing() == null || uo.getChepaileixing().equals("")) {
						uo.setChepaileixing("1");
					}
					// String userkey = uo.getChepai() + "_" +
					// uo.getChepaileixing();
					List<DzjcAllHistory> histories = sendService.getTodayList(uo);
					// 这个人有违章的情况
					// 1、连续3天发送违章的详情
					// 2、第四天开始发送统计信息，如果没有新的违章，统计信息后带公告信息
					// 3、 如果有新的违章，统计信息后带违章信息
					//
					//
					// if (todaylist.containsKey(userkey)) {
					int slen = (histories == null ? 0 : histories.size());
					LOG.debug(uo.getMobile() + "的违章个数为:" + slen);
					allcount += slen;
					if (slen > 0) {
						// List<DzjcAllHistory> histories =
						// todaylist.get(userkey);
						// 有违章信息,看有几条,然后看是不是之前的违章信息
						// 每次都发详细并更新这个用户的详细发送次数

						if (uo.getSendcount() < SendConstant.DETAIL_COUNT) {
							for (DzjcAllHistory history : histories) {
								String _smscontent = sendDetailWz(uo, history, true); // 下发完整的短信
								String result = Sms.sendSms(uo.getMobile(), _smscontent, "yw", "", uo.getProductid());
								LOG.debug("下发短信:" + uo.getMobile() + "," + _smscontent + "," + uo.getProductid() + ","
										+ result);
								// String result = "-1";
								// 将下发的短信存储到数据库里
								smscount++;
							}
							// 更新
							orderservice.updateDetailCount(uo.getMobile());
						} else {// 下发统计信息，如果有新的违章的话，加入最新的那条违章

							DzjcAllHistory newhistory = null;
							for (DzjcAllHistory history : histories) {
								if (history.getHandleDays() == 0)
									newhistory = history; // 不管有多条新的，都只发一条
							}

							if (newhistory == null) {
								smscontent = sendStatic(uo, histories);
							} else {

								String content = sendDetailWz(uo, newhistory, false);
								smscontent = SendConstant.DEFAULT_BANNER + "您" + uo.getChepai() + "的车有" + slen
										+ "条违章信息待处理（" + content + "）";
							}

							// if (hlen == 1) {// 只一条违章
							// DzjcAllHistory history = histories.get(0);
							// if (history.getHandleDays() == 0) {// 第一次违章
							// smscontent = sendDetailWz(uo, history);
							// } else {// 这条违章信息一直没处理
							// smscontent = sendWzNotHandle(uo, history);
							// }
							// } else if (hlen > 1) {// 有多条违章，只是发送统计信息
							// smscontent = sendStatic(uo, histories);
							// }
						}

					} else {// 这个人已经没有违章的情况了,下发公告信息
						smscontent = sendNoWz(uo);
					}

					if (smscontent != null && !smscontent.equals("")) {

						// 这里真的下发短信
						String result = Sms.sendSms(uo.getMobile(), smscontent, "yw", "", uo.getProductid());
						LOG.debug("下发短信:" + uo.getMobile() + "," + smscontent + "," + uo.getProductid());
						// String result = "-1";
						// 将下发的短信存储到数据库里

						smscount++;
					}
				}
				list.clear();
			}
		}
		LOG.info("本次处理完成,短信下发数:" + smscount + ",违章数:" + allcount);
	}

	/**
	 * 这个人多次违章，发送统计情况之后发生公共模板信息
	 * 
	 * @param uo
	 * @param histories
	 */
	public String sendStatic(UserOrder uo, List<DzjcAllHistory> histories) {
		String s = this.getPromptContent(uo.getAreacode());
		if (!(s == null || s.equals("")))
			s = "（" + s + "）";

		String content = "您" + uo.getChepai() + "的车有" + histories.size() + "条违章信息待处理" + s;
		return SendConstant.DEFAULT_BANNER + content;
	}

	/**
	 * 违章一直没处理，提醒有几条违章未处理并发送公共模板信息
	 * 
	 * @param uo
	 * @param history
	 */
	// public String sendWzNotHandle(UserOrder uo, DzjcAllHistory history) {
	// String s = this.getPromptContent(uo.getAreacode());
	// if (!(s == null || s.equals("")))
	// s = "（" + s + "）";
	//
	// String content = "您有1条违章信息需要处理（" + s + "）";
	// return SendConstant.DEFAULT_BANNER + content;
	// }
	/**
	 * 第一次违章的处理，发送公共模板信息并带入后缀
	 * 
	 * @param uo
	 * @param history
	 */
	public String sendDetailWz(UserOrder uo, DzjcAllHistory history, boolean banner) {

		UserDzjcVo vo = new UserDzjcVo(uo, history, banner);
		CityTemplateContent template = templist.get(uo.getCityname());
		String content = "";
		if (template != null && template.getDetailtemplate() != null) {
			content = TemplateUtil.getDetailedContent(template.getDetailtemplate(), vo);
		} else {
			content = SendConstant.DEFAULT_BANNER + "您的" + history.getHphm() + "于" + history.getDzjcsj() + "在"
					+ history.getDzjcdd() + "因" + history.getWzxx() + "产生了违章。";
		}
		return content + SendConstant.DEFAULT_ENDFIX;
	}

	private static int ii = 0;

	/**
	 * uo没有违章信息，怎么处理？
	 * 
	 * <pre>
	 * 如已无违章，每周一及每周五各下发两条公共信息,根据所在的地市获取提示信息
	 * </pre>
	 * 
	 * @param uo
	 */
	public String sendNoWz(UserOrder uo) {

		int weekday = DateUtil.getWeekday();
		if (SendConstant.SEND_PROMPT_WEEKDAY.contains(weekday)) { // 周一或者周五下发

			String tcontent = getPromptContent(uo.getAreacode());
			return tcontent;
			// 加入到mt的下发日志表中
		}
		return "";
	}

	/**
	 * 根据地区得到对应的公共提示信息
	 * 
	 * @param area
	 * @return
	 */
	private String getPromptContent(String area) {
		CityPrompt promp = promtlist.get(area);
		if (promp == null)
			promp = promtlist.get(OrderConstant.AREA_DATABASE.get(area));
		String tcontent = "";
		boolean useweek=false;
		
		 //如果当周有内容并当周的内容包括了对应地市的话，就用这个数据
		if (cpu != null) {
			String city = cpu.getCity();
			if (city != null && promp!=null&&promp.getCity() != null && city.indexOf(promp.getCity()) != -1){
				tcontent = cpu.getContent();
				useweek=true;
			}
		}
		if(!useweek&&promp!=null) //如果不用当周的并对应地市有数据的话，就用对应地市的
			tcontent = promp.getSqlContent();

		return tcontent;
	}

	// public void test(){
	//
	// templist = sendService.getTemplateList();
	// LOG.info("得到短信下发模板处理完毕");
	// // 得到该地市要下发的公共信息
	// promtlist = sendService.getPromptList();
	// LOG.info("得到地市要下发的公共信息完成");
	// // java.util.Iterator<String> iterator=promtlist.keySet().iterator();
	// // while(iterator.hasNext()){
	// // String s=iterator.next();
	// // System.out.println(s+">"+promtlist.get(s).getSqlContent());
	// // }
	// UserDzjcVo vo= new UserDzjcVo();
	// java.util.Iterator<String> iterator=templist.keySet().iterator();
	// while(iterator.hasNext()){
	// String s=iterator.next();
	// CityTemplateContent ctc=templist.get(s);
	// System.out.println(TemplateUtil.getDetailedContent(ctc.getDetailtemplate(),
	// vo));
	// }
	// }
	//	

}
