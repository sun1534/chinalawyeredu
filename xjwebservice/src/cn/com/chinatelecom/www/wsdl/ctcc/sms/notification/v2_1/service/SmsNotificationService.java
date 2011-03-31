/**
 * SmsNotificationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service;

public interface SmsNotificationService extends javax.xml.rpc.Service {
    public java.lang.String getSmsNotificationAddress();

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification getSmsNotification() throws javax.xml.rpc.ServiceException;

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification getSmsNotification(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
