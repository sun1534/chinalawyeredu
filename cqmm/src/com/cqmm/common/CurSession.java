package com.cqmm.common;

import java.util.HashMap;
import java.util.Map;

import oms.dataconnection.helper.v15.QueryApList;

public class CurSession {
	public static String username;
	public static int userid;
	public static String sessionid="";
	public static Map sessionmap=new HashMap();
	
	
	public static QueryApList mQueryApList;
	public static int netposition=0;
	public static int apId;
	public static String proxyHost;
	public static int proxyPort;
}
