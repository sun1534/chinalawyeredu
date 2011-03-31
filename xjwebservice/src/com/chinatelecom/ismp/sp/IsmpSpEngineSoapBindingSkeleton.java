/**
 * IsmpSpEngineSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.chinatelecom.ismp.sp;

public class IsmpSpEngineSoapBindingSkeleton implements com.chinatelecom.ismp.sp.IsmpSpEngine, org.apache.axis.wsdl.Skeleton {
    private com.chinatelecom.ismp.sp.IsmpSpEngine impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "orderRelationUpdateNotifyReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "OrderRelationUpdateNotifyReq"), com.chinatelecom.ismp.sp.req.OrderRelationUpdateNotifyReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("orderRelationUpdateNotify", _params, new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "orderRelationUpdateNotifyReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://rsp.sp.ismp.chinatelecom.com", "Response"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "orderRelationUpdateNotify"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("orderRelationUpdateNotify") == null) {
            _myOperations.put("orderRelationUpdateNotify", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("orderRelationUpdateNotify")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "serviceConsumeNotifyReqPara"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "ServiceConsumeNotifyReq"), com.chinatelecom.ismp.sp.req.ServiceConsumeNotifyReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("serviceConsumeNotify", _params, new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "serviceConsumeNotifyReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://rsp.sp.ismp.chinatelecom.com", "Response"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "serviceConsumeNotify"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("serviceConsumeNotify") == null) {
            _myOperations.put("serviceConsumeNotify", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("serviceConsumeNotify")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "notifyManagementInfoReq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "NotifyManagementInfoReq"), com.chinatelecom.ismp.sp.req.NotifyManagementInfoReq.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("notifyManagementInfo", _params, new javax.xml.namespace.QName("http://sp.ismp.chinatelecom.com", "notifyManagementInfoReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://rsp.sp.ismp.chinatelecom.com", "NotifyManagementInfoRsp"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "notifyManagementInfo"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("notifyManagementInfo") == null) {
            _myOperations.put("notifyManagementInfo", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("notifyManagementInfo")).add(_oper);
    }

    public IsmpSpEngineSoapBindingSkeleton() {
        this.impl = new com.chinatelecom.ismp.sp.IsmpSpEngineSoapBindingImpl();
    }

    public IsmpSpEngineSoapBindingSkeleton(com.chinatelecom.ismp.sp.IsmpSpEngine impl) {
        this.impl = impl;
    }
    public com.chinatelecom.ismp.sp.rsp.Response orderRelationUpdateNotify(com.chinatelecom.ismp.sp.req.OrderRelationUpdateNotifyReq orderRelationUpdateNotifyReq) throws java.rmi.RemoteException
    {
        com.chinatelecom.ismp.sp.rsp.Response ret = impl.orderRelationUpdateNotify(orderRelationUpdateNotifyReq);
        return ret;
    }

    public com.chinatelecom.ismp.sp.rsp.Response serviceConsumeNotify(com.chinatelecom.ismp.sp.req.ServiceConsumeNotifyReq serviceConsumeNotifyReqPara) throws java.rmi.RemoteException
    {
        com.chinatelecom.ismp.sp.rsp.Response ret = impl.serviceConsumeNotify(serviceConsumeNotifyReqPara);
        return ret;
    }

    public com.chinatelecom.ismp.sp.rsp.NotifyManagementInfoRsp notifyManagementInfo(com.chinatelecom.ismp.sp.req.NotifyManagementInfoReq notifyManagementInfoReq) throws java.rmi.RemoteException
    {
        com.chinatelecom.ismp.sp.rsp.NotifyManagementInfoRsp ret = impl.notifyManagementInfo(notifyManagementInfoReq);
        return ret;
    }

}
