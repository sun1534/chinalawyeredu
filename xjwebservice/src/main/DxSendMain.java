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

	private static final int PAGE_SIZE = 1000;

	private SendService sendService = null;
	private OrderService orderservice = null;
	private Map<String, CityTemplateContent> templist = null;
	private Map<String, CityPrompt> promtlist = null;

//	private static String DEFAULT_PROMPT = "提示信息";// 默认提示信息
	private static String DEFAULT_BANNER = "【尊敬的汽车保姆用户】";// 默认的banner信息
	// 下发的违章信息,要加入这个
	private static String DEFAULT_ENDFIX = "（违章内容仅供参考，详情请前往就近交警大队查询）";

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

		// 得到订购用户今天所有的违章记录，和违章车牌表绑定
		Map<String, List<DzjcAllHistory>> todaylist = sendService.getTodayList();

		LOG.info("得到今天的违章数据完毕：" + todaylist.size());
		// 得到所有的订购用户总数,没有退订的
		int usercount = orderservice.getAllOrderUserCount();
		LOG.info("用户总数:::" + usercount);
		int smscount = 0;
		if (usercount != 0) {// 有订购用户的情况下

			int getcount = ((usercount - 1) / PAGE_SIZE) + 1;
			LOG.debug("总计处理次数:::" + getcount);
			for (int i = 0; i < getcount; i++) {
				int startIndex = i * PAGE_SIZE;
				List list = orderservice.getAllOrdreUsers(startIndex, PAGE_SIZE); // 得到这批用户并匹配是否
				int len = list == null ? 0 : list.size();
				for (int j = 0; j < len; j++) {
					String smscontent = ""; // 要下发的短信内容
					UserOrder uo = (UserOrder) list.get(j);
					if (!(uo.getAreacode() == null || uo.getAreacode().equals("")
							|| !OrderConstant.AREA_DATABASE.containsKey(uo.getAreacode()) || !OrderConstant.CITY_DATABASE
							.containsKey(uo.getAreacode()))) {
						LOG.warn(uo.getMobile() + "没有上传区域信息,忽略");
						continue;
					}
					String userkey = uo.getChepai() + "_" + uo.getChepaileixing();
					if (todaylist.containsKey(userkey)) {// 这个人有违章的情况
						List<DzjcAllHistory> histories = todaylist.get(userkey);
						// 有违章信息,看有几条,然后看是不是之前的违章信息
						int hlen = histories == null ? 0 : histories.size();
						if (hlen == 1) {// 只一条违章
							DzjcAllHistory history = histories.get(0);
							if (history.getHandleDays() == 0) {// 第一次违章
								smscontent = sendFirstWz(uo, history);
							} else {// 这条违章信息一直没处理
								smscontent = sendWzNotHandle(uo, history);
							}
						} else if (hlen > 1) {// 有多条违章，只是发送统计信息
							smscontent = sendStatic(uo, histories);
							// 下面再考虑
							// for(DzjcAllHistory history:histories){
							// if(history.getHandleDays()==0){//第一次违章
							// sendFirstWz(uo,history);
							// }else{//违章信息一直没处理
							// sendWzNotHandle(uo,history);
							// }
							// }
						}
					} else {// 这个人已经没有违章的情况了,
						smscontent = sendNoWz(uo);
					}

					if (smscontent != null && !smscontent.equals("")) {

						// 这里真的下发短信
						String result = Sms.sendSms(uo.getMobile(), smscontent, "yw","", uo.getProductid());
						LOG.debug("下发短信:" + uo.getMobile() + "," + smscontent + "," + uo.getProductid());
						// String result = "-1";
						// 将下发的短信存储到数据库里

						smscount++;
					}
				}
				list.clear();
			}
		}
		LOG.info("本次处理完成,短信下发数:" + smscount);
	}

	/**
	 * 这个人多次违章，发送统计情况之后发生公共模板信息
	 * 
	 * @param uo
	 * @param histories
	 */
	public String sendStatic(UserOrder uo, List<DzjcAllHistory> histories) {
		String s=this.getPromptContent(uo.getAreacode());
		if(!(s==null||s.equals("")))
			s="（" + s+ "）";
			
		String content = "您有" + histories.size() + "条违章信息"+s;
		return DEFAULT_BANNER + content;
	}

	/**
	 * 违章一直没处理，提醒有几条违章未处理并发送公共模板信息
	 * 
	 * @param uo
	 * @param history
	 */
	public String sendWzNotHandle(UserOrder uo, DzjcAllHistory history) {
		String s=this.getPromptContent(uo.getAreacode());
		if(!(s==null||s.equals("")))
			s="（" + s+ "）";
			
		String content = "您有1条违章信息需要处理（" + s + "）";
		return DEFAULT_BANNER + content;
	}

	/**
	 * 第一次违章的处理，发送公共模板信息并带入后缀
	 * 
	 * @param uo
	 * @param history
	 */
	public String sendFirstWz(UserOrder uo, DzjcAllHistory history) {

		UserDzjcVo vo = new UserDzjcVo(uo, history);

		CityTemplateContent template = templist.get(uo.getAreacode());
		String content = "";
		if (template != null && template.getDetailtemplate() != null) {
			content = TemplateUtil.getDetailedContent(template.getDetailtemplate(), vo);
		} else {
			content = "您的" + history.getHphm() + "于" + history.getDzjcsj() + "在" + history.getDzjcdd() + "因"
					+ history.getWzxx() + "违章。";
		}
		content = content + DxSendMain.DEFAULT_ENDFIX;
		return DEFAULT_BANNER + content;
	}

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
		if (promp != null)
			tcontent = promp.getSqlContent();
//		else
//			tcontent = DEFAULT_PROMPT;
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
