/**
 * 
 */
package com.sxit.query.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.netquality.models.Sgsn;
import com.sxit.query.model.EricssonTraceLog;
import com.sxit.query.model.MobileTraceState;

/**
 * 
 * 执行脚本的方式获得数据
 * 
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class EricssonTrace {
	private static Log _LOG = LogFactory.getLog(EricssonTrace.class);

	private static final String ERICSSON_TRACE_CMD = "/export/home/jf/GPRS/script/ericsson_trace.sh";
	// 停止追踪的话,是一长串手机号码
	private static final String ERICSSON_TRACE_STOP_CMD = "/export/home/jf/GPRS/script/ericsson_trace_stop.sh";
	/**
	 * 得到被追踪号码的结果信息
	 */
	private static final String ERICSSON_TRACE_LOGS_CMD = "/export/home/jf/GPRS/script/ericsson_trace_logs.sh";

	/**
	 * 系统正在追踪的号码
	 */
	public static Map<String,String> TRACE_MOBILES = new HashMap<String,String>();

	/**
	 * 对分析出来的结果进行分析
	 * 
	 * @param line
	 * @return
	 */
	private EricssonTraceLog parseLine(String line) {
		EricssonTraceLog logs = new EricssonTraceLog();
		String[] split = line.split(";");
		// 2010-06-20 10:55:12 testcq Event name: activate_pdp ;
		// Event details: - ;
		// Cause Value: - ;
		// IMSI: 460028036323114 ;
		// MSISDN: 8613452595004 ;
		// NSAPI: 5 ;
		// RAI: 460-0-13058-0 ;
		// CGI: 460-0-13058-44612 ;
		// Radio Access Type: GSM
		String eventName = split[0];

		int idx = eventName.lastIndexOf(":");
		logs.setEventName(eventName.substring(idx + 1).trim());
		logs.setTheTime(eventName.substring(0, idx).replace("testcq Event name", ""));

		logs.setEventDetails(split[1].split(":")[1].trim());
		logs.setCauseValue(split[2].split(":")[1].trim());
		logs.setImsi(split[3].split(":")[1].trim());
		logs.setMsisdn(split[4].split(":")[1].trim());
		logs.setNsapi(split[5].split(":")[1].trim());
		logs.setRai(split[6].split(":")[1].trim());
		logs.setCellid(split[7].split(":")[1].trim());
		logs.setAccessType(split[8].split(":")[1].trim());

		return logs;
	}

	/**
	 * 得到爱立信号码的追踪结果。。。先要得到这个号码是否已经启动了追踪
	 * 
	 * @param sgsnid
	 * @param msisdn
	 *            这里的号码必须是单个的号码
	 * @param random
	 */
	public MobileTraceState getTraceLogs(String sgsnid, String msisdn, String random) {

		MobileTraceState trace = new MobileTraceState();
		trace.setSgsnid(sgsnid);
		trace.setMobile(msisdn);
		trace.setRandom(random);
		trace.setStatus(1);
List<String> lines=new ArrayList<String>();
SHQueryService.QUERY_RESULT.put(msisdn+","+random, lines);
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getSgsnip() + " " + sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + msisdn;
		String cmd = ERICSSON_TRACE_LOGS_CMD + " " + params;
		try {
			_LOG.debug("准备启动对号码" + msisdn + "的追踪记录查询........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
		
			int i = 0;

			List<String> list = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				i++;
//				System.out.println(line);
				lines.add(line);
//				if (line.indexOf("more /logs/er_data_log/tmp/er_data_log.* | grep ") != -1) {
//					execline = i;
//				}
//				if (i > execline&&!line.startsWith("=== ")) {
//					list.add(line);
//				}
				if(com.sxit.query.util.RauUtil.containsDatetime(line))
					list.add(line);
			}
			lines.add("isover");
			br.close();

			for (String resultLine : list) {
//				System.out.println(resultLine);
				try {
					EricssonTraceLog log = parseLine(resultLine);
					trace.addTraceLogs(log);
				} catch (Exception e) {
					_LOG.error("分析执行结果有误:"+resultLine, e);
				}
			}

			// 分析数据
		} catch (Exception e) {
			_LOG.error("爱立信的号码追踪有误", e);
			java.io.Writer sw = new java.io.StringWriter();
			trace.setHasexception(true);
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
				e.printStackTrace(pw);
				pw.flush();
				pw.close();

				sw.flush();
				sw.close();
				String s = sw.toString();
				if (s.length() > 2000)
					trace.setException(s.substring(0, 2000));
				else
					trace.setException(sw.toString());
			} catch (Exception ee) {
				_LOG.error("关闭StringWriter有误", ee);
			}
		}
		return trace;
	}

	/**
	 * 启动对号码的追踪
	 * 
	 * @param sgsnid
	 * @param msisdn
	 * @param random
	 * @return
	 */
	public MobileTraceState mobileTrac(String sgsnid, String msisdn, String random) {

		

		MobileTraceState trace = new MobileTraceState();
		trace.setSgsnid(sgsnid);
		trace.setMobile(msisdn);
		trace.setRandom(random);
		trace.setStatus(1);
		List<String> lines=new ArrayList<String>();
		SHQueryService.QUERY_RESULT.put(msisdn+","+random, lines);
		
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getSgsnip() + " " + sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + msisdn;
		String cmd = ERICSSON_TRACE_CMD + " " + params;
		try {
			_LOG.debug("准备启动对号码" + msisdn + "的追踪........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				lines.add(line);
				System.out.println(line);
			}
			lines.add("isover");
			br.close();
			TRACE_MOBILES.put(msisdn+","+random,sgsnid);
			// 分析数据
		} catch (Exception e) {
			_LOG.error("爱立信的号码追踪有误", e);
			java.io.Writer sw = new java.io.StringWriter();
			trace.setHasexception(true);
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
				e.printStackTrace(pw);
				pw.flush();
				pw.close();

				sw.flush();
				sw.close();
				String s = sw.toString();
				if (s.length() > 2000)
					trace.setException(s.substring(0, 2000));
				else
					trace.setException(sw.toString());
			} catch (Exception ee) {
				_LOG.error("关闭StringWriter有误", ee);
			}
		}
		return trace;
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
	public MobileTraceState mobileTracStop(String sgsnid, String msisdn, String random) {

		MobileTraceState trace = new MobileTraceState();
		trace.setSgsnid(sgsnid);
		trace.setMobile(msisdn);
		trace.setRandom(random);
		trace.setStatus(2);
		List<String> lines=new ArrayList<String>();
		SHQueryService.QUERY_RESULT.put(msisdn+","+random, lines);
		Sgsn sgsn = com.sxit.netquality.service.BasicSetService.ALL_SGSNS.get(sgsnid);
		String params = sgsn.getSgsnip() + " " + sgsn.getLoginname() + " " + sgsn.getLoginpwd() + " " + msisdn;
		String cmd = ERICSSON_TRACE_STOP_CMD + " " + params;
		try {
			_LOG.debug("准备启动对号码" + msisdn + "的停止追踪........");
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec(cmd);
			_LOG.debug("开始执行命令::::" + cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				i++;
				System.out.println(line);
				lines.add(line);
			}
			lines.add("isover");
			br.close();
			TRACE_MOBILES.remove(msisdn+","+random);
			_LOG.debug("对号码" + msisdn + "的停止追踪执行完毕........");
			// 分析数据
		} catch (Exception e) {
			_LOG.error("爱立信的号码追踪停止有误", e);
			java.io.Writer sw = new java.io.StringWriter();
			trace.setHasexception(true);
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(sw, true);
				e.printStackTrace(pw);
				pw.flush();
				pw.close();

				sw.flush();
				sw.close();
				String s = sw.toString();
				if (s.length() > 2000)
					trace.setException(s.substring(0, 2000));
				else
					trace.setException(sw.toString());
			} catch (Exception ee) {
				_LOG.error("关闭StringWriter有误", ee);
			}
		}
		return trace;

	}

	public static void main(String args[]) throws Exception {
		System.out.println("得到sgsn的数据信息");
		SgsnUtil.getsgsns();

		EricssonTrace trace = new EricssonTrace();

		trace.mobileTrac("SGSNCQ0" + args[0], args[1], "123456");
		int i = 0;
		while (i++ < 1) {
			Thread.sleep(5000L);
			MobileTraceState state=trace.getTraceLogs("SGSNCQ0" + args[0], args[1], "123456");
			List<EricssonTraceLog> logs=state.getTraceLogs();
			for(EricssonTraceLog log:logs){
				System.out.println(log.getTheTime()+"=>"+log.getEventName()+"=>"+log.getEventDetails()+"=>"+log.getCauseValue()+"=>"+log.getImsi()+"=>"+log.getMsisdn()+"=>"+log.getNsapi()+"=>"+log.getRai()+"=>"+log.getCellid()+"=>"+log.getAccessType());
			}
		}
		
		trace.mobileTracStop("SGSNCQ0" + args[0], args[1], "123456");
	}
}