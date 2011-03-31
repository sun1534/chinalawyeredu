/**
 * ServiceConsumeNotifyReq.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.chinatelecom.ismp.sp.req;

public class ServiceConsumeNotifyReq  implements java.io.Serializable {
    private java.lang.String featureStr;

    private java.lang.String linkID;

    private java.lang.String productID;

    private java.lang.String streamingNo;

    private java.lang.String userID;

    private int userIDType;

    public ServiceConsumeNotifyReq() {
    }

    public ServiceConsumeNotifyReq(
           java.lang.String featureStr,
           java.lang.String linkID,
           java.lang.String productID,
           java.lang.String streamingNo,
           java.lang.String userID,
           int userIDType) {
           this.featureStr = featureStr;
           this.linkID = linkID;
           this.productID = productID;
           this.streamingNo = streamingNo;
           this.userID = userID;
           this.userIDType = userIDType;
    }


    /**
     * Gets the featureStr value for this ServiceConsumeNotifyReq.
     * 
     * @return featureStr
     */
    public java.lang.String getFeatureStr() {
        return featureStr;
    }


    /**
     * Sets the featureStr value for this ServiceConsumeNotifyReq.
     * 
     * @param featureStr
     */
    public void setFeatureStr(java.lang.String featureStr) {
        this.featureStr = featureStr;
    }


    /**
     * Gets the linkID value for this ServiceConsumeNotifyReq.
     * 
     * @return linkID
     */
    public java.lang.String getLinkID() {
        return linkID;
    }


    /**
     * Sets the linkID value for this ServiceConsumeNotifyReq.
     * 
     * @param linkID
     */
    public void setLinkID(java.lang.String linkID) {
        this.linkID = linkID;
    }


    /**
     * Gets the productID value for this ServiceConsumeNotifyReq.
     * 
     * @return productID
     */
    public java.lang.String getProductID() {
        return productID;
    }


    /**
     * Sets the productID value for this ServiceConsumeNotifyReq.
     * 
     * @param productID
     */
    public void setProductID(java.lang.String productID) {
        this.productID = productID;
    }


    /**
     * Gets the streamingNo value for this ServiceConsumeNotifyReq.
     * 
     * @return streamingNo
     */
    public java.lang.String getStreamingNo() {
        return streamingNo;
    }


    /**
     * Sets the streamingNo value for this ServiceConsumeNotifyReq.
     * 
     * @param streamingNo
     */
    public void setStreamingNo(java.lang.String streamingNo) {
        this.streamingNo = streamingNo;
    }


    /**
     * Gets the userID value for this ServiceConsumeNotifyReq.
     * 
     * @return userID
     */
    public java.lang.String getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this ServiceConsumeNotifyReq.
     * 
     * @param userID
     */
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }


    /**
     * Gets the userIDType value for this ServiceConsumeNotifyReq.
     * 
     * @return userIDType
     */
    public int getUserIDType() {
        return userIDType;
    }


    /**
     * Sets the userIDType value for this ServiceConsumeNotifyReq.
     * 
     * @param userIDType
     */
    public void setUserIDType(int userIDType) {
        this.userIDType = userIDType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceConsumeNotifyReq)) return false;
        ServiceConsumeNotifyReq other = (ServiceConsumeNotifyReq) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.featureStr==null && other.getFeatureStr()==null) || 
             (this.featureStr!=null &&
              this.featureStr.equals(other.getFeatureStr()))) &&
            ((this.linkID==null && other.getLinkID()==null) || 
             (this.linkID!=null &&
              this.linkID.equals(other.getLinkID()))) &&
            ((this.productID==null && other.getProductID()==null) || 
             (this.productID!=null &&
              this.productID.equals(other.getProductID()))) &&
            ((this.streamingNo==null && other.getStreamingNo()==null) || 
             (this.streamingNo!=null &&
              this.streamingNo.equals(other.getStreamingNo()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID()))) &&
            this.userIDType == other.getUserIDType();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFeatureStr() != null) {
            _hashCode += getFeatureStr().hashCode();
        }
        if (getLinkID() != null) {
            _hashCode += getLinkID().hashCode();
        }
        if (getProductID() != null) {
            _hashCode += getProductID().hashCode();
        }
        if (getStreamingNo() != null) {
            _hashCode += getStreamingNo().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        _hashCode += getUserIDType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceConsumeNotifyReq.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "ServiceConsumeNotifyReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("featureStr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "featureStr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("linkID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "linkID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "productID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streamingNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "streamingNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "userID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIDType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://req.sp.ismp.chinatelecom.com", "userIDType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
