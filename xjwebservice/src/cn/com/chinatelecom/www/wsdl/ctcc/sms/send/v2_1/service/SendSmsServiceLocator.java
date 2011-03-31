/**
 * SendSmsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service;

public class SendSmsServiceLocator extends org.apache.axis.client.Service implements cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsService {

    public SendSmsServiceLocator() {
    }


    public SendSmsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendSmsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendSms
    private java.lang.String SendSms_address = "http://localhost:9080/SendSmsService/services/SendSms";

    public java.lang.String getSendSmsAddress() {
        return SendSms_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendSmsWSDDServiceName = "SendSms";

    public java.lang.String getSendSmsWSDDServiceName() {
        return SendSmsWSDDServiceName;
    }

    public void setSendSmsWSDDServiceName(java.lang.String name) {
        SendSmsWSDDServiceName = name;
    }

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms getSendSms() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendSms_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendSms(endpoint);
    }

    public cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms getSendSms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsBindingStub _stub = new cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsBindingStub(portAddress, this);
            _stub.setPortName(getSendSmsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendSmsEndpointAddress(java.lang.String address) {
        SendSms_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsBindingStub _stub = new cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsBindingStub(new java.net.URL(SendSms_address), this);
                _stub.setPortName(getSendSmsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SendSms".equals(inputPortName)) {
            return getSendSms();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/wsdl/ctcc/sms/send/v2_1/service", "SendSmsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/wsdl/ctcc/sms/send/v2_1/service", "SendSms"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendSms".equals(portName)) {
            setSendSmsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
