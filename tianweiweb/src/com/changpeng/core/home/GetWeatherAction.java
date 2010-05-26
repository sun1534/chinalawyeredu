/**
 * GetWeatherAction.java
 */
package com.changpeng.core.home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;

/**
 * @author 华锋 May 25, 201010:07:08 AM
 * 
 */
public class GetWeatherAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(GetWeatherAction.class);

	public GetWeatherAction() {
		this.rightCode = "PUBLIC";

	}

	private String weatherContent;
	
	private static final int TIMEOUT = 30 * 1000;
	private static final String WEATHER_URL = "http://www.thinkpage.cn/weather/weather.aspx?uid=&c=CHXX0120&l=zh-CHS&p=CMA&a=0&u=C&s=4&m=0&x=0&d=3&fc=FF0000&bgc=&bc=&ti=1&in=1&li=2";

	@Override
	protected String go() throws Exception {
		StringBuilder tempStr=new StringBuilder();

		InputStream in = null;
		BufferedReader rd = null;
		HttpURLConnection urlConn = null;
		try {
			URL url;
			url = new URL(WEATHER_URL);
			urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setRequestMethod("POST");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(TIMEOUT);
		
			urlConn.getOutputStream().flush();
			in = urlConn.getInputStream();
			rd = new BufferedReader(new InputStreamReader(in, "utf8"));
		
			String tmps;
			boolean shouldSelect=false;
			while ((tmps = rd.readLine()) != null) {
				if(tmps.indexOf("div")!=-1&&tmps.indexOf("ctl00_divForecastWeather")!=-1){
//					tempStr.append(tmps);
					tempStr.append("<div id=\"ctl00_divForecastWeather\">");
					shouldSelect=true;
				}
				else if(shouldSelect&&tmps.indexOf("</div>")!=-1){
					tempStr.append(tmps);
					shouldSelect=false;
				}
				else if(shouldSelect){
					tempStr.append(tmps);
				}			
			}			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConn != null)
				urlConn.disconnect();
		}
		this.weatherContent=tempStr.toString();
		return SUCCESS;
	}

	/**
	 * @return the weatherContent
	 */
	public String getWeatherContent() {
		return weatherContent;
	}
	
}
