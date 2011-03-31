/**
 * SendSmsBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service;

public class SendSmsBindingSkeleton implements cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms, org.apache.axis.wsdl.Skeleton {
    private cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ChargingInformation"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "message"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "SimpleReference"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSms", _params, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "sendSms"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSms") == null) {
            _myOperations.put("sendSms", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSms")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("PolicyException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("ServiceException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ChargingInformation"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "image"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "smsFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/v2_1", "SmsFormat"), cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "SimpleReference"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSmsLogo", _params, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "sendSmsLogo"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSmsLogo") == null) {
            _myOperations.put("sendSmsLogo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSmsLogo")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("PolicyException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("ServiceException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "addresses"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"), org.apache.axis.types.URI[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "senderName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "charging"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ChargingInformation"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "ringtone"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "smsFormat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/v2_1", "SmsFormat"), cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "receiptRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "SimpleReference"), cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendSmsRingtone", _params, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "sendSmsRingtone"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendSmsRingtone") == null) {
            _myOperations.put("sendSmsRingtone", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendSmsRingtone")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("PolicyException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("ServiceException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "requestIdentifier"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getSmsDeliveryStatus", _params, new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "result"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/v2_1", "DeliveryInformation"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/send/v2_1/local", "getSmsDeliveryStatus"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getSmsDeliveryStatus") == null) {
            _myOperations.put("getSmsDeliveryStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getSmsDeliveryStatus")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("PolicyException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "PolicyException"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("ServiceException");
        _fault.setQName(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _fault.setClassName("cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException");
        _fault.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "ServiceException"));
        _oper.addFault(_fault);
    }

    public SendSmsBindingSkeleton() {
        this.impl = new cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsBindingImpl();
    }

    public SendSmsBindingSkeleton(cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms impl) {
        this.impl = impl;
    }
    public java.lang.String sendSms(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, java.lang.String message, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException
    {
        java.lang.String ret = impl.sendSms(addresses, senderName, charging, message, receiptRequest);
        return ret;
    }

    public java.lang.String sendSmsLogo(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, byte[] image, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat smsFormat, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException
    {
        java.lang.String ret = impl.sendSmsLogo(addresses, senderName, charging, image, smsFormat, receiptRequest);
        return ret;
    }

    public java.lang.String sendSmsRingtone(org.apache.axis.types.URI[] addresses, java.lang.String senderName, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging, java.lang.String ringtone, cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsFormat smsFormat, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException
    {
        java.lang.String ret = impl.sendSmsRingtone(addresses, senderName, charging, ringtone, smsFormat, receiptRequest);
        return ret;
    }

    public cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation[] getSmsDeliveryStatus(java.lang.String requestIdentifier) throws java.rmi.RemoteException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.PolicyException, cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ServiceException
    {
        cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation[] ret = impl.getSmsDeliveryStatus(requestIdentifier);
        return ret;
    }

}
