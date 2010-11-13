package com.sxit.memdevice;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;

import com.sxit.memdevice.common.SystemData;

/**
 * @author 肖云亮 2010-08-31 下午03:12:13
 * 
 */
public class ProcessOptId  {
	private static final Log log = LogFactory.getLog(ProcessOptId.class);

	public ProcessOptId(){	
	}	
	public static Requests Process(org.dom4j.Element method) throws Exception{
		String optId="0";
		Requests req=null;
		for ( Iterator i = method.elementIterator(); i.hasNext(); ) { 
				 Element params = (Element) i.next();
				 log.info(params.getName());
				 if(params.getName().equals("optid"))
				 {
					 optId = params.getStringValue();
					 log.info("optId=="+ optId);
				 }				
		}
		if(SystemData.opt.containsKey(Integer.valueOf(optId))){
			req=(Requests)SystemData.opt.get(Integer.valueOf(optId)).newInstance();
		}else {
//			req=  new ErrorRequest()  ;
		}	
		return req;
	}

}