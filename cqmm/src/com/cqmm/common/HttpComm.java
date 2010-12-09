package com.cqmm.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import oms.dataconnection.helper.httpget.DataHttp;
import oms.dataconnection.helper.v15.ConnectByAp;
import oms.dataconnection.helper.v15.QueryApList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.NodeList;

import com.cqmm.bean.Command;
import com.cqmm.bean.Device;

import android.content.Context;
import android.util.Log;

/**
 * Created by IntelliJ IDEA. User: zhyy Date: 2010-9-1 Time: 15:52:15
 */
public class HttpComm {
	public static final int HTTPCOMM_ANP_NOT_FIND = 1;
	public static final int HTTPCOMM_OK = 0;
	public static final int HTTPCOMM_START_APN_FAIL = 2;

	public static String serverUrl = "http://218.201.8.150:8000/serverprocess";
	private static HttpURLConnection hc = null;
	private static OutputStream os = null;
	private static InputStream is = null;
	private static String proxyHost = null;
	private static int proxyPort = 80;

	private static Context saveContext;
	private static String apnName;

	private static QueryApList mQueryApList = null;
	// helper object for AP connecting
	private static ConnectByAp mConnectHelper = null;

	// private static ConnectivityManager mConnMgr=null;

	public static String getProxyHost() {
		return proxyHost;
	}

	public static int getProxyPort() {
		return proxyPort;
	}

	private static void executeOpen(String hostUrl, String sendMessage)
			throws IOException {
		Log.v("cqmm-request-",sendMessage);
		startApConnnet(saveContext, apnName);

		URL url = new URL(hostUrl);

		// ---------------------------------------------------------
		if ((CurSession.proxyHost != null) && (CurSession.proxyHost.length() > 0)) {
			Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress(CurSession.proxyHost, CurSession.proxyPort));
			hc = (HttpURLConnection) url.openConnection(proxy);
		} else
			hc = (HttpURLConnection) url.openConnection();
		// ---------------------------------------------------------

		if (hc == null)
			throw new IOException("url.openConnection fail!");

		hc.setDoInput(true);
		hc.setConnectTimeout(10000);
		hc.setRequestProperty("accept", "*/*");
		if (sendMessage == null) {
			hc.setRequestMethod("GET");
			hc.connect();
		} else {
			hc.setDoOutput(true);
			hc.setRequestMethod("POST");
			hc.connect();
			os = hc.getOutputStream();
			os.write(sendMessage.getBytes("UTF-8"));
			os.flush();
			os.close();
			os = null;
		}

		if (HttpURLConnection.HTTP_OK != hc.getResponseCode())
			throw new IOException("Http respone code error!");

		is = hc.getInputStream();
	}
//	private static void executeOpen(String hostUrl, String sendMessage)
//		throws IOException {
//		Log.v("cqmm-request-",sendMessage);
//		
//		CqmmDataHttp dataHttp = new CqmmDataHttp(hostUrl  
//                , "*/*", "OPhone 1.5 ", CurSession.proxyHost, CurSession.proxyPort){  
//		        // 自定义新数据接受到的网络通知，用于数据传输状态的获取  
//		        @Override  
//		        protected void onDataReceived(int len_read, int size, int total) {  
//		                // show data receiving status  
//		                super.onDataReceived(len_read, size, total);  
//		        }  
//		};  
//		
//		// 然后，发送请求网络，并接收返回数据  
//		try {  
////			dataHttp.=sendMessage.getBytes();
//		        dataHttp.connect(sendMessage);  
//		} catch (MalformedURLException e) {  
//		        e.printStackTrace();  
//		} catch (IOException e) {  
//		        e.printStackTrace();  
//		}  
//		
//		// 读取返回数据  
//		// 调用下面的方法可以实现压缩编码的解压和字符编码的自动识别  
//		String page = dataHttp.getResponseString();
//		Log.v("cqmm-request", page);
//
//	}
	
	
	
	private static String executeGet(String hostUrl, String sendMessage)
			throws IOException {
		executeOpen(hostUrl, sendMessage);

		int contentLength = (int) hc.getContentLength();

		if (contentLength <= 0) {
			throw new IOException("Content length is missing");
		}

		byte[] data = new byte[contentLength];
		int readedCount = 0;
		do {
			int length = is.read(data);
			if (length > 0)
				readedCount += length;
			else
				break;
		} while (readedCount < contentLength);

		return new String(data, 0, readedCount, "UTF-8");

	}

	/*
	 * public static void stopApConnect(Context context,String apType){
	 * 
	 * mConnMgr = (ConnectivityManager)
	 * context.getSystemService(Context.CONNECTIVITY_SERVICE); NetworkInfo[]
	 * netInfos = mConnMgr.getAllNetworkInfo();
	 * 
	 * for (NetworkInfo netInfo : netInfos) { if (TextUtils.equals(apType,
	 * netInfo.getApType()) && netInfo.isConnected()) {
	 * 
	 * break; } }
	 * 
	 * mConnMgr.stopUsingNetworkFeature(ConnectivityManager.TYPE_MOBILE,
	 * apType); }
	 */
	public static void startApConnnet(Context context, String apnname)
			throws IOException {
		
		if(context== null)return;

		saveContext = context;
		apnName = apnname;
		// the query result of system configured AP list
		if (mQueryApList == null)
			mQueryApList = new QueryApList(context);
		// helper object for AP connecting
		if (mConnectHelper == null)
			mConnectHelper = new ConnectByAp(context);

		String[] apnListTitle = mQueryApList.getApValue();
		int position = -1;

		for (int i = 0; i < apnListTitle.length; i++) {
			if (apnListTitle[i].equalsIgnoreCase(apnName)) {
				position = i;
				break;
			}
		}

		if (position < 0)
			throw new IOException("APN NOT EXIST!");

		// int apId = mQueryApList.getApId(position);

		// AP type
		String apType = mQueryApList.getApType(position);
		// AP configured proxy host
		proxyHost = mQueryApList.getApProxy(position);
		// AP configured proxy port
		proxyPort = mQueryApList.getApProxyPort(position);

		if (proxyPort <= 0)
			proxyPort = 80;

		if (!mConnectHelper.connect(apType, 10000))
			throw new IOException("START APN CONNECT FAIL!");

	}

	public static void close() {
		if (hc == null)
			return;
		try {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			hc.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hc = null;
	}

	public static boolean isOpen() {
		return hc != null;
	}

	static public boolean downloadCmds() throws Exception {
		if (isOpen())
			return false;
		try {
			boolean result = false;

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			executeOpen("http://218.201.8.150:8000/serverprocess", null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
//			result=baos.toString();
			Log.v("mmmmmmmmmmm-----------",baos.toString());
//			Document dom = builder.parse(is);
//			Element root = dom.getDocumentElement();
//			NodeList items = root.getElementsByTagName("cmdnode");
//			if (items.getLength() > 0) {
//				result = CmdManager.loadXml(items.item(0));
//			}

			close();
			return result;
		} catch (Exception e) {
			close();
			throw e;

		}
	}

//	static public boolean downloadHosts() throws Exception {
//		if (isOpen())
//			return false;
//		try {
//			boolean result = false;
//
//			DocumentBuilderFactory factory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			executeOpen(serverUrl + "GetHosts", null);
//
//			Document dom = builder.parse(is);
//			Element root = dom.getDocumentElement();
//			NodeList items = root.getElementsByTagName("hosts");
//			if (items.getLength() > 0)
//				result = HostManager.loadXml(items.item(0));
//
//			close();
//			return result;
//		} catch (Exception e) {
//			close();
//			throw e;
//
//		}
//	}

//	static public RemoteCmdResult executeCmd(RemoteCmdItem cmd)
//			throws Exception {
//
//		if (isOpen())
//			return null;
//
//		try {
//			RemoteCmdResult result = null;
//			RemoteCmdResult remoteCmdResult = new RemoteCmdResult();
//			DocumentBuilderFactory factory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			executeOpen(serverUrl + "CmdExcute", cmd.toXmlString());
//
//			Document dom = builder.parse(is);
//			Element root = dom.getDocumentElement();
//			// NodeList items = root.getElementsByTagName("cmdresult");
//			// if(items.getLength()>0)
//			if (remoteCmdResult.loadXml(root))
//				result = remoteCmdResult;
//
//			close();
//			return result;
//
//		} catch (Exception e) {
//			close();
//			throw e;
//
//		}
//
//	}

	static public String downloadFile(String fileName) throws Exception {

		try {
			String result = executeGet(serverUrl + "CmdResult/" + fileName,
					null);
			close();
			return result;

		} catch (Exception e) {
			close();
			throw e;

		}

	}

	
	public static String Login(String username,String password)  {
		Map<String,String> params=new HashMap<String,String>();
		params.put("optype", "login");
		params.put("username", username.trim());
		params.put("password", password.trim());
		if (isOpen())
			return "网络连接错误";
		try {
			String result = "";

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			executeOpen(serverUrl, getreqstr(params));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
			result=baos.toString();
			Log.v("cqmm-response-",baos.toString());
			
//			Document dom = builder.parse(is);
//			Element root = dom.getDocumentElement();
//			NodeList items = root.getElementsByTagName("cmdnode");
//			if (items.getLength() > 0) {
//				result = CmdManager.loadXml(items.item(0));
//			}

			close();
			return result;
		} catch (Exception e) {
			close();
			return "ERROR";

		}
	}
	
	public static List<Device> getDevice()  {
		
		List<Device> deviceList=new ArrayList<Device>();
		
		try{
			Map<String,String> params=new HashMap<String,String>();
			params.put("optype", "getdevice");
			params.put("userid", Integer.toString(CurSession.userid));
			
			executeOpen(serverUrl, getreqstr(params));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
			String res=baos.toString();
			
			
		
			Document doc=DocumentHelper.parseText(res);
			List<Element> list=doc.getRootElement().elements();
			for(Element element:list){
				Device device=new Device();
				device.setId(Integer.parseInt(element.elementText("deviceid")));
				device.setDevicename(element.elementText("devicename"));
				deviceList.add(device);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return deviceList;
	}
	
	public static String login_device(int deviceid,String username,String password){
		
		String result="";
		
		try{
			Map<String,String> params=new HashMap<String,String>();
			params.put("optype", "login_device");
			params.put("deviceid", Integer.toString(deviceid));
			params.put("username", username.trim());
			params.put("password", password.trim());
			
			executeOpen(serverUrl, getreqstr(params));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
			result=baos.toString();
			Log.v("cqmm-login_device-response-", result);
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static List<Command> getCommand(int deviceid){
		List<Command> commandList=new ArrayList<Command>();
		try{
			Map<String,String> params=new HashMap<String,String>();
			params.put("optype", "getcommand");
			params.put("userid", Integer.toString(CurSession.userid));
			params.put("deviceid", Integer.toString(deviceid));
			
			executeOpen(serverUrl, getreqstr(params));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
			String res=baos.toString();
		
		
			Document doc=DocumentHelper.parseText(res);
			List<Element> list=doc.getRootElement().elements();
			for(Element element:list){
				Command command=new Command();
				command.setId(Integer.parseInt(element.elementText("commandid")));
				command.setCommandname(element.elementText("commandname"));
				command.setCommandtype(Integer.parseInt(element.elementText("commandtype")));
				command.setDeviceid(deviceid);
				commandList.add(command);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return commandList;
	}
	
	public static String execmd(int deviceid,int commandid){
		String result="";
		try{
			Map<String,String> params=new HashMap<String,String>();
			params.put("optype", "execommand");
			params.put("userid", Integer.toString(CurSession.userid));
			params.put("deviceid", Integer.toString(deviceid));
			params.put("commandid", Integer.toString(commandid));
			executeOpen(serverUrl, getreqstr(params));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] tmpb = new byte[1024];
			int len;
			while ((len = is.read(tmpb)) != -1) {
				baos.write(tmpb, 0, len);
			}
			result=baos.toString();
			Log.v("cqmm-execmd-response", result);
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private static String getreqstr(Map<String, String> params){
		Element xmlInfo = DocumentHelper.createElement("request");
		for(Map.Entry<String,String> d:params.entrySet()){
			xmlInfo.addElement(d.getKey()).addText(d.getValue());
		}
		return xmlInfo.asXML();
	}
}
