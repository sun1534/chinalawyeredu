/**
 * SmsSend.java
 */
package main.cdralarm;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.CMPP;
import com.sxit.cmpp.CMPPSocket;
import com.sxit.cmpp.CMPPSubmitResp;
import com.sxit.cmpp.Common;
import com.sxit.cmpp.SubmitBody;

/**
 * @author 华锋 Jul 15, 201011:19:45 AM
 * 
 */
public class SmsSend {
	private static final Log log = LogFactory.getLog(SmsSend.class);
private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void send222(String mobiles, String content)  {
		System.out.println(df.format(new Date())+"=>短信内容:::" + content + ",手机:" + CDRAlarm.MOBILES);
	}

	public static void send(String mobiles, String content) {
		System.out.println(df.format(new Date())+"=>短信内容=>" + content + ",手机:" + CDRAlarm.MOBILES);
		CMPPSocket socket = new CMPPSocket("211.139.59.3 ", 7890);

		CMPP cmpp = null;

		while (true) {
			try {
				socket.initialSock();
				cmpp = new CMPP(socket);
				// 建立和网关的connect连接
				int status = cmpp.cmppConnect("922095", "922095");
				System.out.println("和网关建立的连接返回结果:" + status);
				if (status == 0) {
					System.out.println("连接建立成功！\n");
					break;
				} else {
					log.warn("连接建立不成功,结果为:" + status + "，重新连接\n");
					Thread.sleep(5000L);
					socket.closeSock();
				}
			} catch (Exception e) {
				try {
					socket.closeSock();
					log.error("建立连接异常,休眠5秒后再次建立连接!");
					Thread.sleep(5000L);
				} catch (Exception ee) {
					log.error("关闭连接异常:" + ee.toString());
				}

				log.error(e.toString());
			}
		}
		// submit消息,封装submit包,各字段意义请查阅doc文档

		String mobiless = CDRAlarm.MOBILES;
		String[] mobilesss = mobiless.split(",");
		for (String mobile : mobilesss) {

			try {
				SubmitBody submit = new SubmitBody(); // submit.msgID = 12;
				submit.ucPkTotal = 1;
				submit.ucPkNumber = 1;
				submit.ucRegister = 1;
				submit.ucMsgLevel = 1;
				submit.sServiceId = "10658477";
				submit.ucFeeUserType = 0;
				submit.sFeeTermId = "";
				submit.ucTpPid = 0;
				submit.ucTpUdhi = 0;
				submit.ucMsgFmt = 15;
				submit.sMsgSrc = "922095";
				submit.sFeeType = "01";
				submit.sFeeCode = "0";
				submit.sValidTime = "";
				submit.sAtTime = "";
				submit.sSrcTermId = "10658477";
				// 可以支持多个发送,cmpp2.0暂时只支持一个接收号码
				submit.sDstTermId = mobile;
				// 也可以设定只有一个
				// submit.sDstTermId = "13635423870";
				submit.ucMsgContent = content.getBytes("gb2312");
				submit.reserver = ""; // cmpp.cmppSubmit(submit); //

				long begin = System.currentTimeMillis();
				// 同时获得submitresp消息
				CMPPSubmitResp resp = new CMPPSubmitResp();
				cmpp.cmppSubmit(submit, resp);

				System.out.println(mobile + "=>" + content + "=>" + resp.result + "=>" + resp.getSequenceID());

				// System.out.println("msgid=" +
				// Common.bytes8ToLong(resp.msg_Id) + "--seqid=" +
				// resp.getSequenceID()
				// + "--result=" + resp.result);
				// System.out.println("msgid=" + bytes2hex(resp.msg_Id));
				long now = System.currentTimeMillis();
				if (now - begin <= 100) {
					Thread.sleep((100 - now + begin));
					// System.out.println("发送这条信息花了" + (now - begin) + "时间,睡" +
					// (100 - now + begin) + "这么产\n\n");
				}
			} catch (Exception e) {
				System.out.println("短信发送失败:" + mobile);
				e.printStackTrace();

			}

		}

		try{
		socket.closeSock();
		System.out.println("关闭短信通道...");
		}catch(Exception e){
			
		}
		System.out.println("此次短信发送退出");
	}

	private static String bytes2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			sb.append(Common.byte2hex(b[i]) + " ");
		return sb.toString();
	}
}
