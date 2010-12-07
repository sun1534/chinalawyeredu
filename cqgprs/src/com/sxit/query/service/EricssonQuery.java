/**
 * 
 */
package com.sxit.query.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.netquality.models.Sgsn;
import com.sxit.query.model.ActiveApn;
import com.sxit.query.model.MobileApnState;
import com.sxit.query.model.SubscribedPDP;
import com.sxit.query.model.SubscriberData;

/**
 * 
 * 执行脚本的方式获得数据
 * 
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class EricssonQuery extends UserQuery {
	private static Log _LOG = LogFactory.getLog(EricssonQuery.class);

	private static final String ERICSSON_CMD = "/export/home/jf/GPRS/script/ericsson_query.sh";
	private static final String ERICSSON_CMD_2 = "/export/home/jf/GPRS/script/ericsson_query2.sh";

	private void parseLines(MobileApnState apnstate, String mobile, String sgsnid, List<String> lines, int subdataline,
			List<Integer> subapnlines, int activeapnline) throws Exception {
		int i = 0;

		if (subdataline == 0) {
			// 未附着状态,下面的不用处理了,直接返回
			apnstate.setStatus("noattach");
			return;
		}
		int endsubdataline = subapnlines.size() > 0 ? subapnlines.get(0) : lines.size(); // 第一个subapn的起始处
		// System.out.println("第一个:::" + endsubdataline);
		SubscribedPDP subpdp = null;
		int iiii=0;
		for (String line : lines) {
			i++;
			if (line.equals("isover"))
				break;

			int idx = line.indexOf(":");
			String substrvalue = "";
			String substrkey = "";
			if (idx != -1) {
				substrvalue = line.substring(idx + 1).trim();
				substrkey = line.substring(0, idx).trim();
				// _LOG.debug(substrkey + "==>" + substrvalue);
			}

			if (line.indexOf("Subscriber Data") != -1) {
				SubscriberData subdata = new SubscriberData();
				apnstate.setSubdata(subdata);
				System.out.println("尻。。。。。。。。。。。。。。。。。。。。。。。。。。");
			}
			if (line.indexOf("Subscribed PDP") != -1) {
				subpdp = new SubscribedPDP();
				apnstate.addSubpdp(subpdp);

			}
			if (line.indexOf("Active PDP") != -1) {
				ActiveApn activeapn = new ActiveApn();
				apnstate.setActiveapn(activeapn);
			}
			
			if (i >= subdataline && i < endsubdataline) {

				// Subscriber Data
				// ----------------------------------------------------------------------
				// IMSI : 460008386013215
				// Mobile Subscriber ISDN No. : 8613608364826
				// IMEI : 351246005587200
				// Roaming Status : Home
				// HLR Address : 861399600000
				// Home PLMN APN Operator Id : mnc000.mcc460.gprs
				// Subscribed Teleservices : No SMS
				// Network Access Mode : Packet/Circuit Switched
				// Radio Access Technology : GSM
				// Mobility Management State : Ready
				// Paging Proceed Flag : Set
				// Routing Area [RAI] : 460-00-13093-0
				// Cell [CGI] : 460-00-13093-26511
				// P-TMSI : 3362787912 (#C8701248)
				// MSC/VLR Address : Not Gs connected
				// Location Confirmed in HLR : true
				// Data Confirmed by HLR : true
				// Charging Characteristics :
				// Charging Characteristics Profile :

				// 这里处理的都是Subscriber Data的数据
			

				if (apnstate.getSubdata() != null) {
					if (substrkey.indexOf("IMSI") != -1) {
						apnstate.getSubdata().setImsi(substrvalue);
					} else if (substrkey.indexOf("IMEI") != -1) {
						apnstate.getSubdata().setImei(substrvalue);
					} else if (substrkey.indexOf("Mobile Subscriber ISDN No") != -1) {
						apnstate.getSubdata().setMsisdn(substrvalue);
					} else if (substrkey.indexOf("Roaming Status") != -1) {
						apnstate.getSubdata().setRoaminStatus(substrvalue);
					} else if (substrkey.indexOf("Home PLMN APN Operator Id") != -1) {
						apnstate.getSubdata().setOperatorId(substrvalue);
					} else if (substrkey.indexOf("HLR Address") != -1) {
						apnstate.getSubdata().setHlrAddress(substrvalue);
					} else if (substrkey.indexOf("Subscribed Teleservices") != -1) {
						apnstate.getSubdata().setSubscribedTeleservices(substrvalue);
					} else if (substrkey.indexOf("Mobility Management State") != -1) {
						apnstate.getSubdata().setMobilityState(substrvalue);
						apnstate.setStatus(substrvalue);
					} else if (substrkey.indexOf("Routing Area [RAI]") != -1) {
						apnstate.getSubdata().setRai(substrvalue);
					} else if (substrkey.indexOf("Cell [CGI]") != -1) {
						apnstate.getSubdata().setCellcgi(substrvalue);
					}
				} else {
					System.out.println("========不对啊,这里有错??????????????????"+(iiii++));
				}
			}

			else if (i >= endsubdataline && (activeapnline == 0 ? true : (i < activeapnline))) { // 这里有多个subpdp，处理
				// System.out.println("::::::::::::::"+i+"-->"+line);
			
				if (subpdp != null) {
					if (line.indexOf("Id") != -1) {
						subpdp.setId(substrvalue);
					} else if (substrkey.indexOf("Type") != -1) {
						subpdp.setIptype(substrvalue);
					} else if (substrkey.indexOf("Address") != -1) {
						subpdp.setAddress(substrvalue);
					} else if (substrkey.indexOf("APN") != -1) {
						subpdp.setSubapn(substrvalue);
					}
				}
				// Subscribed PDP
				// ----------------------------------------------------------------------
				// Id : 2
				// Type : IPv4
				// Address : Dynamic
				// Quality of service :
				// allocation/retention priority : level2
				// delay class : class1
				// reliability class : Unack: GTP,LLC. Ack: RLC. Protected data
				// peak throughput (octet/s) : up to 256000
				// precedence class : normal priority
				// mean throughput (octet/h) : best effort
				// traffic class : interactive
				// delivery order : yes
				// delivery of erroneous SDU : no
				// maximum SDU size (octets) : 1500
				// maximum bit rate for uplink (kbps) : 2048
				// maximum bit rate for downlink (kbps) : 2048
				// residual BER : 1E-5
				// SDU error ratio : 1E-4
				// transfer delay (ms) : 0
				// traffic handling priority : level1
				// guaranteed bit rate for uplink (kbps) : 0
				// guaranteed bit rate for downlink (kbps) : 0
				// VPLMN allowed : false
				// APN : cmmm
				// PDP Charging Characteristics :
				// PDP Charging Characteristics Profile :
			}

			else if (i >= activeapnline) { // 激活的情况
//				if (line.indexOf("Active PDP") != -1) {
//					ActiveApn activeapn = new ActiveApn();
//					apnstate.setActiveapn(activeapn);
//				} else
if(apnstate.getActiveapn()!=null){
					if (substrkey.indexOf("Id") != -1) {
					apnstate.getActiveapn().setId(substrvalue);
				} else if (substrkey.indexOf("NSAPI") != -1) {
					apnstate.getActiveapn().setNsapi(substrvalue);
				} else if (substrkey.indexOf("APN requested") != -1) {
					apnstate.getActiveapn().setApnRequest(substrvalue);
				} else if (substrkey.indexOf("Addressing nature") != -1) {
					apnstate.getActiveapn().setAddressNature(substrvalue);
				} else if (substrkey.indexOf("Address in use") != -1) {
					apnstate.getActiveapn().setAddressInUse(substrvalue);
				} else if (substrkey.indexOf("APN in use") != -1) {
					apnstate.getActiveapn().setApnInUse(substrvalue);
				} else if (substrkey.indexOf("GGSN in use") != -1) {
					apnstate.getActiveapn().setGgsnInUse(substrvalue);
				}
}
				// // Active PDP
				// //
				// ----------------------------------------------------------------------
				// // Id : 3
				// // NSAPI : 5
				// // Type requested : Information not available
				// // Address requested : Information not available
				// // APN requested : Information not available
				// // Addressing nature : Dynamic
				// // Address in use : 10.201.2.57
				// // APN in use : smjt.cq.mnc000.mcc460.gprs
				// // GGSN in use : 221.177.188.35
			}
		}
	}

	/**
	 * 
	 * @param sgsn
	 *            sgsn的编号,爱立信的只能是2-6.
	 * @param msisdn
	 *            手机号码,去掉86
	 * @param random
	 *            客户端产生的一个随机数字。根据手机号和这个随机数字做为key,存储到内存块QUERY_RESULT中
	 */
	public MobileApnState queryUserState(String sgsnid, String msisdn, String random) {

		MobileApnState apnstate = new MobileApnState();
		apnstate.setMobile(msisdn);
		apnstate.setSgsnid(sgsnid);

		List<String> list = new ArrayList<String>();
		SHQueryService.QUERY_RESULT.put(msisdn +","+ random, list);
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getSgsnip() + " " + sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + msisdn;
		String cmd = ERICSSON_CMD + " " + params;
		if(sgsnid.indexOf("2")!=-1)
			cmd = ERICSSON_CMD_2 + " " + params;
		try {
			_LOG.debug("准备启动........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int i = 0;
			int subdataline = 0;
			List<Integer> subapnlines = new ArrayList<Integer>();
			int activeapnline = 0;
			while ((line = br.readLine()) != null) {
				i++;
				list.add(line);
				consoleResultLines +=LINE_SEP_PRE+ line + LINE_SEP;
				System.out.println(line);
				if (line.indexOf("Subscriber Data") != -1) {
					subdataline = i;
				}
				if (line.indexOf("Subscribed PDP") != -1) {
					subapnlines.add(i);
				}
				if (line.indexOf("Active PDP") != -1) {
					activeapnline = i;
				}
			}
			list.add("isover");
			br.close();
			System.out.println(subdataline + "==>" + subapnlines + "==>" + activeapnline);
			// 分析数据
			this.parseLines(apnstate, msisdn, sgsnid, list, subdataline, subapnlines, activeapnline);

		} catch (Exception e) {
			_LOG.error("爱立信的数据查询有误", e);
			java.io.Writer sw = new java.io.StringWriter();
			apnstate.setHasexception(true);
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
				e.printStackTrace(pw);
				pw.flush();
				pw.close();

				sw.flush();
				sw.close();
				String s = sw.toString();
				if (s.length() > 2000)
					apnstate.setException(s.substring(0, 2000));
				else
					apnstate.setException(sw.toString());
			} catch (Exception ee) {
				_LOG.error("关闭StringWriter有误", ee);
			}
		}
		apnstate.toWebString();
		return apnstate;
	}

	public static void main(String args[]) throws Exception {
		System.out.println("得到sgsn的数据信息");
		System.out.println(	Integer.valueOf("17A01" , 16));
		SgsnUtil.getsgsns();

		UserQuery s = new EricssonQuery();
		// MobileApnState mas = s.queryEricsson("SGSNCQ0" + args[0], args[1],
		// "123456");
		MobileApnState mas = s.queryUserState("SGSNCQ0" + args[0], args[1], "123456");
		mas.toWebString();
		System.out.println("mas.getStatus()::::" + mas.getStatus());
		System.out.println("============================");
		System.out.println(mas.getWebstring());
		System.out.println("============================");

	}
}