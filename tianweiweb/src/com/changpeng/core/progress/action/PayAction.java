package com.changpeng.core.progress.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import beartool.MD5;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class PayAction extends AbstractListAction {

	private int id;

	String v_mid, key, v_url, v_oid, v_amount, v_moneytype, v_md5info;
	
	String v_rcvname, v_rcvaddr, v_rcvtel, v_rcvpost, v_rcvemail, v_rcvmobile;
	String v_ordername, v_orderaddr, v_ordertel, v_orderpost, v_orderemail, v_ordermobile;
	public PayAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		CorePublish publish=(CorePublish)service.get(CorePublish.class, id);
		CoreUser user=(CoreUser)service.get(CoreUser.class, this.currentUserid);
		CoreUserDetail userdetail=(CoreUserDetail)service.get(CoreUserDetail.class, this.currentUserid);
		 // 定义必须传递的参数变量

		v_mid = "1001"; // 商户号，这里为测试商户号1001，替换为自己的商户号(老版商户号为4位或5位,新版为8位)即可
		v_url = "http://localhost:8080/chinabank/Receive.jsp"; // 商户自定义返回接收支付结果的页面
		key = "test"; 	//如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn/
						// 登陆后在上面的导航栏里可能找到“B2C”，在二级导航栏里有“MD5密钥设置”
						// 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
						// **********************************************
						// 以上三项必须修改

	
		Date currTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-" + v_mid
				+ "-hhmmss", Locale.US);
		if(publish.getOrderno()==null||publish.getOrderno().equals("")){
			v_oid = sf.format(currTime); // 推荐订单号构成格式为 年月日-商户号-小时分钟秒
			publish.setOrderno(v_oid);
			service.update(publish);
		}else{
			v_oid=publish.getOrderno();
		}
		
		
		v_amount = publish.getFee().toString(); // 订单金额
		v_moneytype = "CNY"; // 币种
		v_md5info = ""; // 对拼凑串MD5私钥加密后的值

		String text = v_amount + v_moneytype + v_oid + v_mid + v_url + key; // 拼凑加密串
		MD5 md5=new MD5();
		v_md5info = md5.getMD5ofStr(text); // 网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写

		// ************以下几项为非必填项**************
		 // 定义非必需参数

		v_rcvname = user.getUserName(); // 收货人
		v_rcvaddr = userdetail.getUserAddress(); // 收货地址
		v_rcvtel =  user.getMobile(); // 收货人电话
		v_rcvpost = userdetail.getPostcode(); // 收货人邮编
		v_rcvemail = userdetail.getEmail(); // 收货人邮件
		v_rcvmobile =  user.getMobile(); // 收货人手机号

		

		v_ordername = user.getUserName(); // 订货人姓名
		v_orderaddr = userdetail.getUserAddress(); // 订货人地址
		v_ordertel = user.getMobile(); // 订货人电话
		v_orderpost = userdetail.getPostcode(); // 订货人邮编
		v_orderemail = userdetail.getEmail(); // 订货人邮件
		v_ordermobile =  user.getMobile(); // 订货人手机号

		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getV_mid() {
		return v_mid;
	}

	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getV_url() {
		return v_url;
	}

	public void setV_url(String v_url) {
		this.v_url = v_url;
	}

	public String getV_oid() {
		return v_oid;
	}

	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}

	public String getV_amount() {
		return v_amount;
	}

	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}

	public String getV_moneytype() {
		return v_moneytype;
	}

	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}

	public String getV_md5info() {
		return v_md5info;
	}

	public void setV_md5info(String v_md5info) {
		this.v_md5info = v_md5info;
	}

	public String getV_rcvname() {
		return v_rcvname;
	}

	public void setV_rcvname(String v_rcvname) {
		this.v_rcvname = v_rcvname;
	}

	public String getV_rcvaddr() {
		return v_rcvaddr;
	}

	public void setV_rcvaddr(String v_rcvaddr) {
		this.v_rcvaddr = v_rcvaddr;
	}

	public String getV_rcvtel() {
		return v_rcvtel;
	}

	public void setV_rcvtel(String v_rcvtel) {
		this.v_rcvtel = v_rcvtel;
	}

	public String getV_rcvpost() {
		return v_rcvpost;
	}

	public void setV_rcvpost(String v_rcvpost) {
		this.v_rcvpost = v_rcvpost;
	}

	public String getV_rcvemail() {
		return v_rcvemail;
	}

	public void setV_rcvemail(String v_rcvemail) {
		this.v_rcvemail = v_rcvemail;
	}

	public String getV_rcvmobile() {
		return v_rcvmobile;
	}

	public void setV_rcvmobile(String v_rcvmobile) {
		this.v_rcvmobile = v_rcvmobile;
	}

	public String getV_ordername() {
		return v_ordername;
	}

	public void setV_ordername(String v_ordername) {
		this.v_ordername = v_ordername;
	}

	public String getV_orderaddr() {
		return v_orderaddr;
	}

	public void setV_orderaddr(String v_orderaddr) {
		this.v_orderaddr = v_orderaddr;
	}

	public String getV_ordertel() {
		return v_ordertel;
	}

	public void setV_ordertel(String v_ordertel) {
		this.v_ordertel = v_ordertel;
	}

	public String getV_orderpost() {
		return v_orderpost;
	}

	public void setV_orderpost(String v_orderpost) {
		this.v_orderpost = v_orderpost;
	}

	public String getV_orderemail() {
		return v_orderemail;
	}

	public void setV_orderemail(String v_orderemail) {
		this.v_orderemail = v_orderemail;
	}

	public String getV_ordermobile() {
		return v_ordermobile;
	}

	public void setV_ordermobile(String v_ordermobile) {
		this.v_ordermobile = v_ordermobile;
	}

}
