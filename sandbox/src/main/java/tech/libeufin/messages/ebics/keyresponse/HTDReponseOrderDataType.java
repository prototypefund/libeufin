//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.18 at 06:44:54 PM CEST 
//


package tech.libeufin.messages.ebics.keyresponse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * Datentyp für Auftragsdaten für Auftragsart HTD (Antwort: Kunden- und Teilnehmerdaten des Teilnehmers abholen).
 * 
 * <p>Java class for HTDReponseOrderDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HTDReponseOrderDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartnerInfo" type="{urn:org:ebics:H004}PartnerInfoType"/>
 *         &lt;element name="UserInfo" type="{urn:org:ebics:H004}UserInfoType"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HTDReponseOrderDataType", propOrder = {
    "partnerInfo",
    "userInfo",
    "any"
})
public class HTDReponseOrderDataType {

    @XmlElement(name = "PartnerInfo", required = true)
    protected PartnerInfoType partnerInfo;
    @XmlElement(name = "UserInfo", required = true)
    protected UserInfoType userInfo;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the partnerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerInfoType }
     *     
     */
    public PartnerInfoType getPartnerInfo() {
        return partnerInfo;
    }

    /**
     * Sets the value of the partnerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerInfoType }
     *     
     */
    public void setPartnerInfo(PartnerInfoType value) {
        this.partnerInfo = value;
    }

    /**
     * Gets the value of the userInfo property.
     * 
     * @return
     *     possible object is
     *     {@link UserInfoType }
     *     
     */
    public UserInfoType getUserInfo() {
        return userInfo;
    }

    /**
     * Sets the value of the userInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfoType }
     *     
     */
    public void setUserInfo(UserInfoType value) {
        this.userInfo = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
