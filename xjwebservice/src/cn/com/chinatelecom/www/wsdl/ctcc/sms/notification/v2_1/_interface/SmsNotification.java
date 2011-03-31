/**
 * SmsNotification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface;

public interface SmsNotification extends java.rmi.Remote {
    public void notifySmsReception(java.lang.String registrationIdentifier, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage message) throws java.rmi.RemoteException;
    public void notifySmsDeliveryReceipt(java.lang.String correlator, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation deliveryStatus) throws java.rmi.RemoteException;
}
