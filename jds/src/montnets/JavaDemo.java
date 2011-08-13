package montnets;

public class JavaDemo
{
  public static void main(String args[])
  {
    int sock = MWGateway.Connect("61.242.89.115", 8018, "20080005005", "cpsoft");    
    System.out.println("The socket is : " + String.valueOf(sock));
    if (sock < 0)
    {
      System.out.println("socket is invalid.");
      return;
    }
   // 测试单向信息
    //System.out.println("send single msg : "+String.valueOf(mgate.SendSms(sock,"13530504411","单向信息，恭喜发财,linux java")));   


    // 测试连接有效性
   // System.out.println("testing connection : " + String.valueOf(mgate.TestConn(sock)));
	
    // 查询余额
   // System.out.println("Your balance is : " + String.valueOf(mgate.QueryBalance(sock)));

    // 查询已经使用的条数
    //System.out.println("Have used : " + String.valueOf(mgate.QueryUsed(sock)));    

    // 修改密码
   //System.out.println("Change password retVal : " + String.valueOf(mgate.ChangePwd(sock, "123456", "000000")));


    // 客服网关测试内容******************************************************
    // 单条发送
    //System.out.println("CS: send one sms. retVal = " +
    //  mgate.CsSendSms(sock, "13600000000", "客服网关，单条发送，测试短信", 1) );

    // 多条发送
    System.out.println("CS: send multi sms. retVal = " +
    		MWGateway.CsSendSms(sock, "15710786193", "客服网关，多条发送，测试短信", 1) );

   // 指定客服子端口单条发送
   //System.out.println("CS: SubPort send one sms. retVal = " +
   //  mgate.CsSPSendSms(sock, "13530504411", "客服网关指定子端口发送，测试短信", 1,"77008"));

    // 接收状态报告
   // String[] arrReport = mgate.CsGetStatusReport(sock);
   // System.out.println("CS: get status report. arrReport.length is " + String.valueOf(arrReport.length));
   // for (int i = 0; i < arrReport.length; i++)
   //   System.out.println(arrReport[i]);

    // 接收上行信息
  ////  String arrMsg = mgate.CsGetSmsEx(sock);
 //    System.out.println("arrMsg.length is " + String.valueOf(arrMsg.length));
 //   for (int i = 0; i < arrMsg.length; i++)
  ///     System.out.println("接收上行信息"+arrMsg);

     String[] arrMsg = MWGateway.CsGetSms(sock);
     System.out.println("arrMsg.length is " + String.valueOf(arrMsg.length));
    for (int i = 0; i < arrMsg.length; i++)
       System.out.println("接收上行信息"+arrMsg[i]);

    // end of 客服网关*******************************************************

    // 增值网关测试内容======================================================
    // 接收上行
    // String[] arrBothMsg = mgate.VasGetSms(sock);
    // System.out.println("arrBothMsg.length is " + String.valueOf(arrBothMsg.length));
    // for (int i = 0; i < arrBothMsg.length; i++)
    //    System.out.println(arrBothMsg[i]);

    // 发送增值信息
    //   System.out.println("send vas both msg : "+String.valueOf(mgate.VasSendSms(sock,"121336","91600","00********","12145678",
    //     "13534***341","测试增值信息，恭喜发财~~",1)));   
    
    // end of 增值网关=======================================================
      
    MWGateway.Disconnect(sock);
  }
}
