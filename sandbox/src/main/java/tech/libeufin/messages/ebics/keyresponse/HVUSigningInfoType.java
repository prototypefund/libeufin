//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.18 at 06:44:54 PM CEST 
//


package tech.libeufin.messages.ebics.keyresponse;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Datentyp für Informationen zu den HVU-Unterschriftsmodalitäten.
 * 
 * <p>Java class for HVUSigningInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HVUSigningInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="readyToBeSigned" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NumSigRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="NumSigDone" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HVUSigningInfoType")
public class HVUSigningInfoType {

    @XmlAttribute(name = "readyToBeSigned", required = true)
    protected boolean readyToBeSigned;
    @XmlAttribute(name = "NumSigRequired", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger numSigRequired;
    @XmlAttribute(name = "NumSigDone", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numSigDone;

    /**
     * Gets the value of the readyToBeSigned property.
     * 
     */
    public boolean isReadyToBeSigned() {
        return readyToBeSigned;
    }

    /**
     * Sets the value of the readyToBeSigned property.
     * 
     */
    public void setReadyToBeSigned(boolean value) {
        this.readyToBeSigned = value;
    }

    /**
     * Gets the value of the numSigRequired property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumSigRequired() {
        return numSigRequired;
    }

    /**
     * Sets the value of the numSigRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumSigRequired(BigInteger value) {
        this.numSigRequired = value;
    }

    /**
     * Gets the value of the numSigDone property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumSigDone() {
        return numSigDone;
    }

    /**
     * Sets the value of the numSigDone property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumSigDone(BigInteger value) {
        this.numSigDone = value;
    }

}
