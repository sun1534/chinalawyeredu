/**
 * SimpleReference.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.schema.ctcc.common.v2_1;

public class SimpleReference  implements java.io.Serializable {
    private org.apache.axis.types.URI endpoint;

    private java.lang.String interfaceName;

    private java.lang.String correlator;

    public SimpleReference() {
    }

    public SimpleReference(
           org.apache.axis.types.URI endpoint,
           java.lang.String interfaceName,
           java.lang.String correlator) {
           this.endpoint = endpoint;
           this.interfaceName = interfaceName;
           this.correlator = correlator;
    }


    /**
     * Gets the endpoint value for this SimpleReference.
     * 
     * @return endpoint
     */
    public org.apache.axis.types.URI getEndpoint() {
        return endpoint;
    }


    /**
     * Sets the endpoint value for this SimpleReference.
     * 
     * @param endpoint
     */
    public void setEndpoint(org.apache.axis.types.URI endpoint) {
        this.endpoint = endpoint;
    }


    /**
     * Gets the interfaceName value for this SimpleReference.
     * 
     * @return interfaceName
     */
    public java.lang.String getInterfaceName() {
        return interfaceName;
    }


    /**
     * Sets the interfaceName value for this SimpleReference.
     * 
     * @param interfaceName
     */
    public void setInterfaceName(java.lang.String interfaceName) {
        this.interfaceName = interfaceName;
    }


    /**
     * Gets the correlator value for this SimpleReference.
     * 
     * @return correlator
     */
    public java.lang.String getCorrelator() {
        return correlator;
    }


    /**
     * Sets the correlator value for this SimpleReference.
     * 
     * @param correlator
     */
    public void setCorrelator(java.lang.String correlator) {
        this.correlator = correlator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimpleReference)) return false;
        SimpleReference other = (SimpleReference) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.endpoint==null && other.getEndpoint()==null) || 
             (this.endpoint!=null &&
              this.endpoint.equals(other.getEndpoint()))) &&
            ((this.interfaceName==null && other.getInterfaceName()==null) || 
             (this.interfaceName!=null &&
              this.interfaceName.equals(other.getInterfaceName()))) &&
            ((this.correlator==null && other.getCorrelator()==null) || 
             (this.correlator!=null &&
              this.correlator.equals(other.getCorrelator())));
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
        if (getEndpoint() != null) {
            _hashCode += getEndpoint().hashCode();
        }
        if (getInterfaceName() != null) {
            _hashCode += getInterfaceName().hashCode();
        }
        if (getCorrelator() != null) {
            _hashCode += getCorrelator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SimpleReference.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "SimpleReference"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endpoint");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endpoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaceName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "interfaceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
