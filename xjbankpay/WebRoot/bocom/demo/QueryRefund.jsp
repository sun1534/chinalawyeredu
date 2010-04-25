<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>退款明细查询测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String begDate = request.getParameter("begDate");
	String endDate = request.getParameter("endDate");
	int refundtype = 99;
	try{
		refundtype = Integer.parseInt(request.getParameter("refundtype"));
	}
	catch(Exception xcp){refundtype = 0;}
	String order = request.getParameter("order");
	int flag = 0;
	try{
		flag = Integer.parseInt(request.getParameter("flag"));
	}
	catch(Exception xcp){flag = 0;}
	int begIndex = 0;
	try{
		begIndex = Integer.parseInt(request.getParameter("begIndex"));
	}
	catch(Exception xcp){begIndex = 0;}
	String code ;
	String err ;
	String msg ;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
	}
	else {
		BOCOMB2COPReply rep = client.queryRefund( begDate, endDate, refundtype,order,flag,begIndex) ;//退款明细查询
		if (rep == null){  
			err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}
		else{
			code = rep.getRetCode(); 		//得到交易返回码
			msg = rep.getErrorMessage();	//得到交易返回信息
			out.print("交易返回码："+code + "<br>");
			out.print("交易错误信息："+msg + "<br>");
			if("0".equals(code)){//表示交易成功
				int num ;
				String index ;
				OpResult opr = rep.getOpResult();
				String total = opr.getValueByName("totalNumber"); 	//得到返回记录个数
				OpResultSet oprSet = rep.getOpResultSet();
				int iNum = oprSet.getOpresultNum();
				int iIndex =  0;
				int iTotal =  Integer.parseInt(total);
				out.print("总交易记录数：");
				out.print(total);
				out.print("<br>本次返回记录数：");
				out.print(iNum);
				out.print("<br>------------------------<br>");
				for (iIndex=0;iIndex<=iNum-1;iIndex++) {
					String order1 			= oprSet.getResultValueByName (iIndex,"order");			//订单号
					String orderDate 		= oprSet.getResultValueByName (iIndex,"orderDate");		//订单日期
					String curType 			= oprSet.getResultValueByName (iIndex,"curType"); 		//币种
					String amount 			= oprSet.getResultValueByName (iIndex,"amount"); 		//金额
					String refundType 		= oprSet.getResultValueByName (iIndex,"refundType"); 	//退款类型
					String state  			= oprSet.getResultValueByName (iIndex,"tranState"); 	//退款交易状态
					String fee 				= oprSet.getResultValueByName (iIndex,"fee"); 			//手续费
					String merchantComment 	= oprSet.getResultValueByName(iIndex, "merchantComment");//商户备注
					String bankComment 		= oprSet.getResultValueByName(iIndex, "bankComment"); 	//银行备注
				
					out.print("订单号："+order1);
					out.print("<br>");
					out.print("订单日期："+orderDate);
					out.print("<br>");
					out.print("币种："+curType);
					out.print("<br>");
					out.print("退款金额："+amount);
					out.print("<br>");
					out.print("退款类型："+refundType);
					out.print("<br>");
					out.print("退款交易状态:"+state);
					out.print("<br>");
					out.print("手续费:"+fee);
					out.print("<br>");					
					out.print("商户备注：" + merchantComment);
					out.print("<br>");
					out.print("银行备注：" + bankComment);
					out.print("<p></p>");
				}
			}
		}
	}
%>
<p><a href="Index.htm">返回首页</a></p>
<p>&nbsp;</p>
</body>
</html>
