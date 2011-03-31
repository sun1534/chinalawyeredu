/**
 * SmsNotificationBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service;

public class SmsNotificationBindingSkeleton implements cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification, org.apache.axis.wsdl.Skeleton {
    private cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "registrationIdentifier"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "message"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/v2_1", "SmsMessage"), cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("notifySmsReception", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "notifySmsReception"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notifySmsReception") == null) {
            _myOperations.put("notifySmsReception", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notifySmsReception")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "correlator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "deliveryStatus"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/v2_1", "DeliveryInformation"), cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("notifySmsDeliveryReceipt", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/notification/v2_1/local", "notifySmsDeliveryReceipt"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notifySmsDeliveryReceipt") == null) {
            _myOperations.put("notifySmsDeliveryReceipt", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notifySmsDeliveryReceipt")).add(_oper);
    }

    public SmsNotificationBindingSkeleton() {
        this.impl = new cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service.SmsNotificationBindingImpl();
    }

    public SmsNotificationBindingSkeleton(cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification impl) {
        this.impl = impl;
    }
    public void notifySmsReception(java.lang.String registrationIdentifier, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage message) throws java.rmi.RemoteException
    {
        impl.notifySmsReception(registrationIdentifier, message);
    }

    public void notifySmsDeliveryReceipt(java.lang.String correlator, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation deliveryStatus) throws java.rmi.RemoteException
    {
        impl.notifySmsDeliveryReceipt(correlator, deliveryStatus);
    }

}
