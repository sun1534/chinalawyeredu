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
import com.sxit.netquality.service.BasicSetService;
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
public class HuaweiQuery extends UserQuery {
	private static Log _LOG = LogFactory.getLog(HuaweiQuery.class);

	private static final String HUAWEI_CMD_1 = "/export/home/jf/GPRS/script/huawei_query1.sh";
	private static final String HUAWEI_CMD_2 = "/export/home/jf/GPRS/script/huawei_query2.sh";
	private static final String HUAWEI_CMD_3 = "/export/home/jf/GPRS/script/huawei_query3.sh";
	private List<String> list = new ArrayList<String>();
	/**
	 * 获取华为用户状态的第一步
	 * 
	 * @param sgsnid
	 * @param msisdn
	 * @param randmo
	 * @return
	 */
	private SubscriberData queryHuaweiSubData(String sgsnid, String msisdn, String random) throws Exception {
		SubscriberData data = new SubscriberData();
		
		
		list.add("华为SGSN之状态查询第一步,获取基本数据...");
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + sgsn.getSgsnip() + " " + msisdn;
		// String params="hdcjj hdcjj*SGSN7 10.190.97.227 "+msisdn;
		String cmd = HUAWEI_CMD_1 + " " + params;

		try {
			_LOG.debug("华为状态查询第一步........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				
				if(line.indexOf(sgsn.getLoginpwd())!=-1)
					line=line.replace(sgsn.getLoginpwd(), "**********");
				if(line.indexOf(sgsn.getLoginname())!=-1)
					line=line.replace(sgsn.getLoginname(), "*******");
				
				list.add(line);
				consoleResultLines+=LINE_SEP_PRE+ line+LINE_SEP;
				System.out.println(line);
				if (line.indexOf("记录不存在") != -1) {
					System.out.println("记录不存在,直接返回..............");
					data.setMobilityState("noattach");
					break;

				} else {
					// 得到imsi;
					int idx = line.indexOf("=");
					String substrvalue = "";
					String substrkey = "";
					if (idx != -1) {
						substrvalue = line.substring(idx + 1).trim();
						substrkey = line.substring(0, idx).trim();
						// _LOG.debug(substrkey + "==>" + substrvalue);
					}
					if (substrkey.equals("IMSI")) {
						data.setImsi(substrvalue);
					} else if (substrkey.equals("IMSI")) {
						data.setImsi(substrvalue);
					} else if (substrkey.equals("IMEISV")) {
						data.setImei(substrvalue);
					} else if (substrkey.equals("HLR编号")) {
						data.setHlrAddress(substrvalue);
					} else if (substrkey.equals("用户当前所在路由区")) {
						data.setRai(substrvalue);
					} else if (substrkey.equals("用户所在小区")) {
						data.setCellcgi(substrvalue);
					} else if (substrkey.equals("用户分离标志")) {
						if (substrvalue.equals("用户分离"))// 还有一个是用户附着
							data.setMobilityState("idle");
					} else if (substrkey.equals("2G MM动态上下文状态")||substrkey.equals("3G MM动态上下文状态")) {
						if(substrkey.equals("3G MM动态上下文状态"))
							data.setIs3g(true);
						data.setMobilityState(substrvalue);
					} 	
					
					else if (substrkey.equals("MSISDN")) {
						data.setMsisdn(substrvalue);
					}else if (substrkey.equals("用户所属服务区")) {
						data.setServicearea(substrvalue);
					}
					
//					RNC标识 = 460000259 
//					用户所属服务区 = 46000a3094e53 
				}
			}
			br.close();
		} catch (Exception e) {
			_LOG.error("华为的数据第一步有误", e);
			throw e;
		}
		return data;
	}

	/**
	 * 
	 * @param sgsnid
	 * @param msisdn
	 * @param random
	 * @param subdata
	 *            要用到subdata中的imsi标志
	 * @return
	 */
	private List<SubscribedPDP> queryHuaweiSubPdps(String sgsnid, String msisdn, String random, String imsi)
			throws Exception {

		List<SubscribedPDP> resultlist = new ArrayList<SubscribedPDP>();
		list.add("华为SGSN之状态查询第二步：获取签约APN数据...");
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + sgsn.getSgsnip() + " " + imsi;
		// String params="hdcjj hdcjj*SGSN7 10.190.97.227 "+msisdn;
		String cmd = HUAWEI_CMD_2 + " " + params;
		// LGI: OP="hdcjj",PWD="hdcjj*SGSN7";
		try {
			Thread.sleep(1000L);
			_LOG.debug("华为状态查询第二步........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				if(line.indexOf(sgsn.getLoginpwd())!=-1)
					line=line.replace(sgsn.getLoginpwd(), "**********");
				if(line.indexOf(sgsn.getLoginname())!=-1)
					line=line.replace(sgsn.getLoginname(), "*******");
				list.add(line);
				consoleResultLines+=LINE_SEP_PRE+ line+LINE_SEP;
				String[] split = line.split("( |\t)+");
				// System.out.println(line+"===>"+split[0]+"<===>"+split.length);
				System.out.println(line);
				if (split != null && split.length == 9) {
					if (!split[1].equals("") && split[1].indexOf("Context") == -1) {
						SubscribedPDP pdp = new SubscribedPDP();
						resultlist.add(pdp);
						pdp.setId(split[1]);
						pdp.setAddress(split[4]);
						pdp.setSubapn(split[5]);
						if (split[7].trim().equals("1")||split[8].trim().equals("1")) {
							pdp.setIsused(true);
						}
					}
				}
				// 显示的内容如下形式
				// Context ID 框号 槽号 UE的IP地址 签约APN 是否静态地址 2G PDP个数 3G PDP个数
				//
				// 2 5 5 0.0.0.0 CMWAP 否 0 0
				// 3 5 5 0.0.0.0 CMMM 否 0 0
				// 1 5 5 0.0.0.0 CMNET 否 1 0
				// 得到imsi;
			}
			br.close();
		} catch (Exception e) {
			_LOG.error("华为第二部有误", e);
			throw e;
		}
		return resultlist;
	}

	private ActiveApn queryHuaweiActiveApn(String sgsnid, String msisdn, String random, String imsi, String contextId)
			throws Exception {

		list.add("华为SGSN之状态查询第三步：获取激活APN数据...");

		ActiveApn activeapn = new ActiveApn();
		Sgsn sgsn = BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + sgsn.getSgsnip() + " " + imsi + " "
				+ contextId;
		String cmd = HUAWEI_CMD_3 + " " + params;
		try {
			Thread.sleep(1000L);

			_LOG.debug("华为状态查询第三步........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				if(line.indexOf(sgsn.getLoginpwd())!=-1)
					line=line.replace(sgsn.getLoginpwd(), "**********");
				if(line.indexOf(sgsn.getLoginname())!=-1)
					line=line.replace(sgsn.getLoginname(), "*******");
				list.add(line);
				consoleResultLines+=LINE_SEP_PRE+ line+LINE_SEP;
//				System.out.println(line);

				int idx = line.indexOf("=");
				String substrvalue = "";
				String substrkey = "";
				if (idx != -1) {
					substrvalue = line.substring(idx + 1).trim();
					substrkey = line.substring(0, idx).trim();
				}

				if (substrkey.equals("PDP地址")) {
					activeapn.setAddressInUse(substrvalue);
				}
				if (substrkey.equals("GGSN用户面地址")) {
					activeapn.setGgsnInUse(substrvalue);
				}
				if (substrkey.equals("签约APN")) {
					activeapn.setApnRequest(substrvalue);
				}
				if (substrkey.equals("签约PDP上下文ID")) {
					activeapn.setId(substrvalue);
				}
				if (substrkey.equals("PDP类型")) {
					activeapn.setAddressNature(substrvalue);
				}

			}
			br.close();
		} catch (Exception e) {
			_LOG.error("华为第三步骤有误", e);
			throw e;
		}
		return activeapn;
	}

	/**
	 * 现在都没考虑异常的情况,要考虑异常和失败的情况的处理
	 * 
	 * @param sgsnid
	 * @param msisdn
	 * @param random
	 * @return
	 */
	public MobileApnState queryUserState(String sgsnid, String msisdn, String random) {
		MobileApnState state = new MobileApnState();
		state.setMobile(msisdn);
		state.setSgsnid(sgsnid);
		state.setStatus("noattch");
		list.clear();
		SHQueryService.QUERY_RESULT.put(msisdn +","+ random, list);// 加入到命令输出列表中,定时显示到浏览器上
		
		try {

			SubscriberData subdata = this.queryHuaweiSubData(sgsnid, msisdn, random);
			state.setSubdata(subdata);
			state.setStatus(subdata.getMobilityState());
			if (subdata.getMobilityState()!=null&&!subdata.getMobilityState().equals("noattach")) {

				List<SubscribedPDP> subpdps = this.queryHuaweiSubPdps(sgsnid, msisdn, random, subdata.getImsi());

				for (SubscribedPDP pdp : subpdps) {
					state.addSubpdp(pdp);
					if (pdp.getIsused()) {
						// 得到激活的apn的数据

						ActiveApn aa = this
								.queryHuaweiActiveApn(sgsnid, msisdn, random, subdata.getImsi(), pdp.getId());
						state.setActiveapn(aa);

					}
				}
			}
			SHQueryService.QUERY_RESULT.get(msisdn +","+ random).add("isover");

		} catch (Exception e) {
			_LOG.error("华为状态有误:", e);
			state.setHasexception(true);
			java.io.Writer sw = new java.io.StringWriter();
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
				e.printStackTrace(pw);
				pw.flush();
				pw.close();

				sw.flush();
				sw.close();
				String s = sw.toString();
				if (s.length() > 2000)
					state.setException(s.substring(0, 2000));
				else
					state.setException(sw.toString());
			} catch (Exception ee) {
				_LOG.error("关闭StringWriter有误", ee);
			}
		}
		state.toWebString();
		return state;
	}

	public static void main(String args[]) throws Exception {
		System.out.println("得到sgsn的数据信息");
		SgsnUtil.getsgsns();
		HuaweiQuery s = new HuaweiQuery();
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