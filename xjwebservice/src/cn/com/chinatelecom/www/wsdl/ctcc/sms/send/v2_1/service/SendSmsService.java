/**
 * SendSmsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service;

public interface SendSmsService extends javax.xml.rpc.Service {
    public java.lang.String getSendSmsAddress();

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms getSendSms() throws javax.xml.rpc.ServiceException;

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms getSendSms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
