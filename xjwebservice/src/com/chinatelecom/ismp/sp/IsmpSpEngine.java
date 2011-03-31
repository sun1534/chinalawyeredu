/**
 * IsmpSpEngine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.chinatelecom.ismp.sp;

public interface IsmpSpEngine extends java.rmi.Remote {
    public com.chinatelecom.ismp.sp.rsp.Response orderRelationUpdateNotify(com.chinatelecom.ismp.sp.req.OrderRelationUpdateNotifyReq orderRelationUpdateNotifyReq) throws java.rmi.RemoteException;
    public com.chinatelecom.ismp.sp.rsp.Response serviceConsumeNotify(com.chinatelecom.ismp.sp.req.ServiceConsumeNotifyReq serviceConsumeNotifyReqPara) throws java.rmi.RemoteException;
    public com.chinatelecom.ismp.sp.rsp.NotifyManagementInfoRsp notifyManagementInfo(com.chinatelecom.ismp.sp.req.NotifyManagementInfoReq notifyManagementInfoReq) throws java.rmi.RemoteException;
}
