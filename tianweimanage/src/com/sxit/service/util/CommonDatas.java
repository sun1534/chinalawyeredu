/**
 * 
 */
package com.sxit.service.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.models.service.CoreChannel;
import com.sxit.models.service.CoreProduct;

/**
 * @author 华锋 Jul 13, 2009 9:16:43 PM
 * 
 */
public class CommonDatas {
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(CommonDatas.class);

	private static BasicService basicService = (BasicService) Globals.getBean("basicService");

	public static Map<Integer, String> PRODUCTTYPE = new LinkedHashMap<Integer, String>();
	public static Map<String, String> PRICEUNIT = new LinkedHashMap<String, String>();

	public static Map<Integer, String> ALLCHANNELS = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> ALLPRODUCTS = new LinkedHashMap<Integer, String>();

	static {

		PRODUCTTYPE.put(0, "面向家庭和企业");
		PRODUCTTYPE.put(1, "面向家庭");
		PRODUCTTYPE.put(2, "面向企业");

		PRICEUNIT.put("元/条", "元/条");
		PRICEUNIT.put("元/月", "元/月");
		PRICEUNIT.put("元/年", "元/年");

	}

	public static void getAllChannels() {
	
		try {

			List diarylist = basicService.findAll(CoreChannel.class);

			synchronized (ALLCHANNELS) {
				ALLCHANNELS.clear();
				for (int i = 0; diarylist != null && i < diarylist.size(); i++) {
					CoreChannel type = (CoreChannel) diarylist.get(i);
					ALLCHANNELS.put(type.getId(), type.getName());
				}
			}
		} catch (ServiceException e) {
			LOG.error("getAllChannels::" + e);
		}
	}
	
	public static void getAllProducts() {
		
		try {

			List diarylist = basicService.findAll(CoreProduct.class);

			synchronized (ALLPRODUCTS) {
				ALLPRODUCTS.clear();
				for (int i = 0; diarylist != null && i < diarylist.size(); i++) {
					CoreProduct type = (CoreProduct) diarylist.get(i);
					ALLPRODUCTS.put(type.getId(), type.getName());
				}
			}
		} catch (ServiceException e) {
			LOG.error("getAllChannels::" + e);
		}
	}
}
