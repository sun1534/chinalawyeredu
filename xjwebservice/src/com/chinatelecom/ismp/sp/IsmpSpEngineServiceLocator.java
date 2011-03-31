/**
 * IsmpSpEngineServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.chinatelecom.ismp.sp;

public class IsmpSpEngineServiceLocator extends org.apache.axis.client.Service implements com.chinatelecom.ismp.sp.IsmpSpEngineService {

    public IsmpSpEngineServiceLocator() {
    }


    public IsmpSpEngineServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IsmpSpEngineServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IsmpSpEngine
    private java.lang.String IsmpSpEngine_address = "http://localhost:8383/WsdlProject/services/IsmpSpEngine";

    public java.lang.String getIsmpSpEngineAddress() {
        return IsmpSpEngine_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IsmpSpEngineWSDDServiceName = "IsmpSpEngine";

    public java.lang.String getIsmpSpEngineWSDDServiceName() {
        return IsmpSpEngineWSDDServiceName;
    }

    public void setIsmpSpEngineWSDDServiceName(java.lang.String name) {
        IsmpSpEngineWSDDServiceName = name;
    }

    public com.chinatelecom.ismp.sp.IsmpSpEngine getIsmpSpEngine() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IsmpSpEngine_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIsmpSpEngine(endpoint);
    }

    public com.chinatelecom.ismp.sp.IsmpSpEngine getIsmpSpEngine(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.chinatelecom.ismp.sp.IsmpSpEngineSoapBindingStub _stub = new com.chinatelecom.ismp.sp.IsmpSpEngineSoapBindingStub(portAddress, this);
            _stub.setPortName(getIsmpSpEngineWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIsmpSpEngineEndpointAddress(java.lang.String address) {
        IsmpSpEngine_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.chinatelecom.ismp.sp.IsmpSpEngine.class.isAssignableFrom(serviceEndpointInterface)) {
                com.chinatelecom.ismp.sp.IsmpSpEngineSoapBindingStub _stub = new com.chinatelecom.ismp.sp.IsmpSpEngineSoapBindingStub(new java.net.URL(IsmpSpEngine_address), this);
                _stub.setPortName(getIsmpSpEngineWSDDServiceName());
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
        if ("IsmpSpEngine".equals(inputPortName)) {
            return getIsmpSpEngine();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "IsmpSpEngineService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "IsmpSpEngine"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IsmpSpEngine".equals(portName)) {
            setIsmpSpEngineEndpointAddress(address);
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
