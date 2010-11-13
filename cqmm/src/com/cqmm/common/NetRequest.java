package com.cqmm.common;

import java.io.File;
import java.util.Map;

public abstract class NetRequest {
	static final int SOCKET_CONNECT_TIMEOUT = 10000;
	public final static String TYPE_HTTP_POST="HTTP_POST";
	public final static String TYPE_SOCKET="SOCKET";
	
	public static NetRequest getInstanct(String requesttype){
		if(requesttype.equals(TYPE_HTTP_POST)){
			return new HttpRequest();
		}else{
			return null;
		}
	}
	
	public abstract String post(String actionUrl, Map<String, String> params, Map<String, File> files);
	
	public abstract String post(String url,Map<String, String> params) ;
	
	public abstract String post(String url,String requeststr);
	
	public abstract String get(String url);
}
