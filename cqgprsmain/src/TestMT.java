/*
 * NAME: com.sxit.cmpp.Test.java Company:SXIT
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.CMPP;
import com.sxit.cmpp.CMPPSocket;
import com.sxit.cmpp.CMPPSubmitResp;
import com.sxit.cmpp.Common;
import com.sxit.cmpp.SubmitBody;

/**
 * @author HuaFeng
 * @version 1.0 (2005-3-29 11:04:47)
 */
public class TestMT {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(TestMT.class);

    
    public static void main(String args[])throws Exception{
    	System.out.println("手机号:"+args[0]);
    	main.cdralarm.SmsSend.send(args[0],"GGSN02/CG03运行正常");
    }
    
    public static void main1(String args[]) throws Exception {
//System.out.println(System.getProperty("file.encoding"));
        // 初始化socket连接
        CMPPSocket socket = new CMPPSocket("218.201.8.150", 7890);

        // 将socket连接注册到CMPP api中,获得response消息的最长时间为10秒
        CMPP cmpp = null;
        // 建立和网关的connect连接
        while (true) {
            try {
                socket.initialSock();
                // 将socket连接注册到CMPP api中
//                cmpp = new CMPP(socket, 60);
                cmpp=new CMPP(socket);
                // 建立和网关的connect连接
                int status = cmpp.cmppConnect("922095", "922095");
                log.info("和网关建立的连接返回结果:" + status);

                if (status == 0) {
                    log.info("连接建立成功！\n");
                    break;
                }
                else {
                    log.warn("连接建立不成功,结果为:" + status + "，重新连接\n");
                    Thread.sleep(5000L);
                    socket.closeSock();
                }

            }
            catch (Exception e) {
                try {
                    socket.closeSock();
                    log.error("建立连接异常,休眠5秒后再次建立连接!");
                    Thread.sleep(5000L);
                }
                catch (Exception ee) {
                    log.error("关闭连接异常:" + ee.toString());
                }

                log.error(e.toString());
            }
        }

        // submit消息,封装submit包,各字段意义请查阅doc文档
        SubmitBody submit = new SubmitBody(); // submit.msgID = 12;

        submit.ucPkTotal = 1;

        submit.ucPkNumber = 1;

        submit.ucRegister = 1;

        submit.ucMsgLevel = 1;

        submit.sServiceId = "10658477";

        submit.ucFeeUserType = 1;

        submit.sFeeTermId = "13635423870";

        submit.ucTpPid = 0;

        submit.ucTpUdhi = 0;

        submit.ucMsgFmt = 15;

        submit.sMsgSrc = "922095";

        submit.sFeeType = "01";

        submit.sFeeCode = "0";

        submit.sValidTime = "";

        submit.sAtTime = "";

        submit.sSrcTermId = "10658477";

        //可以支持多个发送,cmpp2.0暂时只支持一个接收号码
        submit.sDstTermId = "13635423870";
        //也可以设定只有一个
//        submit.sDstTermId = "13635423870";

        submit.ucMsgContent = "接收短信的MSISDN号码,以英文逗号".getBytes("gb2312");

        submit.reserver = ""; // cmpp.cmppSubmit(submit); //
        // cmpp.cmppSubmit(submit); int i = 0;

        int i = 0;
        while (true) {
            long begin = System.currentTimeMillis();
            // 同时获得submitresp消息
            CMPPSubmitResp resp = new CMPPSubmitResp();
            cmpp.cmppSubmit(submit, resp);
            //			System.out.println(resp.msg_Id + "->messageid" + resp.sequenceID
            //					+ "->seqid" + resp.result + "->result");
            log.info("msgid=" + Common.bytes8ToLong(resp.msg_Id) + "--seqid=" + resp.getSequenceID() + "--result="
                    + resp.result);
            log.info("msgid=" + bytes2hex(resp.msg_Id));
            long now = System.currentTimeMillis();
            if (now - begin <= 100) {
                log.info("发送这条信息花了" + (now - begin) + "时间,睡" + (100 - now + begin) + "这么产\n\n");
                Thread.sleep(100 - now + begin);

            }
            if (i++ == 3) {
                break;
            }

        }
        log.info("--------------休息10秒");
        //        Thread.sleep(10 * 1000);
        log.info("--------------我醒了");
        // 发送链路检测包
        int iii = cmpp.cmppActiveTest();
        log.info("发送检测包成功=" + iii);
        //记住要关闭连接
        socket.closeSock();
        //        socket.closeSock();

    }

    private static String bytes2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(Common.byte2hex(b[i]) + " ");
        return sb.toString();
    }
}