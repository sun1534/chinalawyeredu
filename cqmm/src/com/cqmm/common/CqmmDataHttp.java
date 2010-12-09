package com.cqmm.common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.http.HttpStatus;

import android.util.Log;
import oms.dataconnection.helper.httpget.DataHttp;

public class CqmmDataHttp extends DataHttp {

	public CqmmDataHttp(String url, String accept, String user_agent) {
		super(url, accept, user_agent);
		this.url = url;
		this.accept = accept;
		this.user_agent = user_agent;
	}
	public CqmmDataHttp(String url, String accept, String user_agent, String proxy_host,
			int proxy_port) {
		this(url, accept, user_agent);
		
		this.proxy_host = proxy_host;
		this.proxy_port = proxy_port;
	}

	/**
	 * have the connect, and get the response
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public final void connect(String msg) throws MalformedURLException, IOException {
		URL url = new URL(this.url);

		// get Connection, with or without proxy
		if ((proxy_host != null) && (proxy_host.length() > 0)) {
			if (proxy_port <= 0)
				proxy_port = CMWAP_PROXY_PORT;
			Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress(proxy_host, proxy_port));
			connection = (HttpURLConnection) url.openConnection(proxy);
		} else
			connection = (HttpURLConnection) url.openConnection();

		if (connection == null)
			throw new IOException("URLConnection instance is null");

		Log.d(TAG, "Open connection");
		// timeout setting
		connection.setConnectTimeout(TIMEOUT_CONNECT);
		connection.setReadTimeout(TIMEOUT_READ);

		// prepare Headers for HTTP-request
		setRequestHeader();

		// connect
		connection.connect();
		connection.setDoInput(true);
		connection.setConnectTimeout(10000);
		connection.setRequestProperty("accept", "*/*");
		if (msg == null) {
			connection.setRequestMethod("GET");
			connection.connect();
		} else {
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.connect();
			OutputStream os = connection.getOutputStream();
			os.write(msg.getBytes("UTF-8"));
			os.flush();
			os.close();
			os = null;
		}
		int resp_code = connection.getResponseCode();
		if (resp_code <= 0)
			throw new java.io.IOException();
		else if (resp_code != HttpStatus.SC_OK)
			throw new java.io.FileNotFoundException();

		// read Headers from HTTP-response
		getResponseHeader();

		// read data from HTTP-BODY
		getResponseBody();
	}

}
