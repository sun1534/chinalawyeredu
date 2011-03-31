/**
 * SendSmsBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service;

import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPHeader;

public class SendSmsBindingImpl implements cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms{
    public java.lang.String sendSms(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, java.lang.String message, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException {
        
    	System.out.println("charging===>"+charging);
//    	MessageContext msgContext = MessageContext.getCurrentContext();
//    	   SOAPHeader header = msgContext.get
//    	   if(header == null){
//    	    return false;
//    	   }
    	
    	return System.currentTimeMillis()+"";
    }

    public java.lang.String sendSmsLogo(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, byte[] image, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat smsFormat, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException {
        return null;
    }

    public java.lang.String sendSmsRingtone(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, java.lang.String ringtone, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat smsFormat, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException {
        return null;
    }

    public cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation[] getSmsDeliveryStatus(java.lang.String requestIdentifier) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException {
        return null;
    }

}
