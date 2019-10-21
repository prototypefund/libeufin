//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.18 at 06:44:54 PM CEST 
//


package tech.libeufin.messages.ebics.keyresponse;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * Datentyp für HVT-Auftragsflags.
 * 
 * <p>Java class for HVTOrderFlagsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HVTOrderFlagsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="completeOrderData" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fetchLimit" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *             &lt;totalDigits value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="fetchOffset" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *             &lt;totalDigits value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;anyAttribute namespace='urn:org:ebics:H004'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HVTOrderFlagsType")
@XmlSeeAlso({
    tech.libeufin.messages.ebics.keyresponse.HVTOrderParamsType.OrderFlags.class
})
public class HVTOrderFlagsType {

    @XmlAttribute(name = "completeOrderData", required = true)
    protected boolean completeOrderData;
    @XmlAttribute(name = "fetchLimit", required = true)
    protected BigInteger fetchLimit;
    @XmlAttribute(name = "fetchOffset", required = true)
    protected BigInteger fetchOffset;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the completeOrderData property.
     * 
     */
    public boolean isCompleteOrderData() {
        return completeOrderData;
    }

    /**
     * Sets the value of the completeOrderData property.
     * 
     */
    public void setCompleteOrderData(boolean value) {
        this.completeOrderData = value;
    }

    /**
     * Gets the value of the fetchLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFetchLimit() {
        return fetchLimit;
    }

    /**
     * Sets the value of the fetchLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFetchLimit(BigInteger value) {
        this.fetchLimit = value;
    }

    /**
     * Gets the value of the fetchOffset property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFetchOffset() {
        return fetchOffset;
    }

    /**
     * Sets the value of the fetchOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFetchOffset(BigInteger value) {
        this.fetchOffset = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
