<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>当批订单查询测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String begTime; 	//开始时间[yyyyMMddHHmmss]
	String endTime; 	//结束时间[yyyyMMddHHmmss]
	int flag = 0; 		//订单状态 0 所有 1 已支付 2 已撤销 3 部分退货 4退货处理中 5 全部退货 
	int begIndex = 0; 	//起始索引号，可为NULL,默认填0
	String begOrder; 	//起始订单号，可为NULL
	String endOrder; 	//截至订单号，可为NULL
	int sortField = 1;	//排序字段1，订单号；2，金额；3，时间
	int sortOrder = 1; 	//1：降序；2：升序
	String code; 
	String err ;
	String msg ;
	begTime = request.getParameter("begTime");
	endTime = request.getParameter("endTime");
	try{
		flag = Integer.parseInt(request.getParameter("flag"));
	}
	catch(Exception xcp){flag = 0;}
	try{
		begIndex = Integer.parseInt(request.getParameter("begIndex"));
	}
	catch(Exception xcp){begIndex = 0;}
	begOrder = request.getParameter("begOrder");
	endOrder = request.getParameter("endOrder");
	try{
		sortField = Integer.parseInt(request.getParameter("sortField"));
	}
	catch(Exception xcp){sortField = 1;}
	try{
		sortOrder = Integer.parseInt(request.getParameter("sortOrder"));
	}
	catch(Exception xcp){sortOrder = 1;}
	
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//该代码只需调用一次
	if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.queryCurOrder(begTime,endTime,flag,begIndex,begOrder,endOrder,sortField,sortOrder);
		if (rep == null){  
			err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}else{
			code = rep.getRetCode() ;//得到交易返回码
			msg = rep.getErrorMessage();
			out.print("交易返回码："+code + "<br>");
			out.print("交易错误信息："+msg + "<br>");
			if("0".equals(code)){//表示交易成功
				String num ;
				int iNum = 0;
				int index =0;
				OpResult opr = rep.getOpResult();
				String total = opr.getValueByName("totalNumber"); //得到返回记录个数
				OpResultSet oprSet = rep.getOpResultSet(); 
				iNum = oprSet.getOpresultNum();
				out.print("总交易记录数：");
				out.print(total);
				out.print("<br>本次返回记录数：");
				out.print(iNum);
				out.print("<br>------------------------<br>");
				for (index =0;index<=iNum-1;index++){
					String order 			= oprSet.getResultValueByName(index,"order");			//订单号
					String orderDate 		= oprSet.getResultValueByName(index, "orderDate");		//订单日期
					String orderTime 		= oprSet.getResultValueByName(index, "orderTime");		//订单时间
					String curType 			= oprSet.getResultValueByName(index,"curType");			//币种
					String amount 			= oprSet.getResultValueByName(index,"amount"); 			//金额
					String tranDate 		= oprSet.getResultValueByName(index,"tranDate"); 		//交易日期
					String tranTime 		= oprSet.getResultValueByName(index,"tranTime"); 		//交易时间
					String tranState 		= oprSet.getResultValueByName(index,"tranState"); 		//支付交易状态
					String orderState 		= oprSet.getResultValueByName(index, "orderState"); 	//订单状态
					String fee 				= oprSet.getResultValueByName(index, "fee"); 			//手续费
					String bankSerialNo 	= oprSet.getResultValueByName(index, "bankSerialNo"); 	//银行流水号
					String bankBatNo 		= oprSet.getResultValueByName(index, "bankBatNo"); 		//银行批次号
					String cardType 		= oprSet.getResultValueByName(index, "cardType"); 		//交易卡类型0:借记卡 1：准贷记卡 2:贷记卡
					String merchantBatNo	= oprSet.getResultValueByName(index, "merchantBatNo"); 	//商户批次号
					String merchantComment 	= oprSet.getResultValueByName(index, "merchantComment");//商户备注
					String bankComment 		= oprSet.getResultValueByName(index, "bankComment"); 	//银行备注
					out.print("订单号：" + order);
					out.print("<br>");
					out.print("订单日期：" + orderDate);
					out.print("<br>");
					out.print("订单时间：" + orderTime);
					out.print("<br>");
					out.print("币种：" + curType);
					out.print("<br>");
					out.print("金额：" + amount);
					out.print("<br>");
					out.print("交易日期：" + tranDate);
					out.print("<br>");
					out.print("交易时间：" + tranTime);
					out.print("<br>");
					out.print("支付交易状态[1:成功]：" + tranState);
					out.print("<br>");
					out.print("定单状态[0 所有 1 已支付 2 已撤销 3 部分退货 4退货处理中 5 全部退货]：" + orderState);
					out.print("<br>");
					out.print("手续费：" + fee);
					out.print("<br>");
					out.print("银行流水号：" + bankSerialNo);
					out.print("<br>");
					out.print("银行批次号：" + bankBatNo);
					out.print("<br>");
					out.print("交易卡类型[0:借记卡 1：准贷记卡 2:贷记卡]：" + cardType);
					out.print("<br>");
					out.print("商户批次号：" + merchantBatNo);
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
