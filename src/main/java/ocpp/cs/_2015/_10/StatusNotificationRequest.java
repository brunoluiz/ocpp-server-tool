
package ocpp.cs._2015._10;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the StatusNotification.req PDU
 * 
 * <p>Java class for StatusNotificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusNotificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{urn://Ocpp/Cs/2015/10/}ChargePointStatus"/>
 *         &lt;element name="errorCode" type="{urn://Ocpp/Cs/2015/10/}ChargePointErrorCode"/>
 *         &lt;element name="info" type="{urn://Ocpp/Cs/2015/10/}CiString50Type" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="vendorId" type="{urn://Ocpp/Cs/2015/10/}CiString255Type" minOccurs="0"/>
 *         &lt;element name="vendorErrorCode" type="{urn://Ocpp/Cs/2015/10/}CiString50Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusNotificationRequest", propOrder = {
    "connectorId",
    "status",
    "errorCode",
    "info",
    "timestamp",
    "vendorId",
    "vendorErrorCode"
})
public class StatusNotificationRequest {

    protected int connectorId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ChargePointStatus status;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ChargePointErrorCode errorCode;
    protected String info;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    protected String vendorId;
    protected String vendorErrorCode;

    /**
     * Gets the value of the connectorId property.
     * 
     */
    public int getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the value of the connectorId property.
     * 
     */
    public void setConnectorId(int value) {
        this.connectorId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ChargePointStatus }
     *     
     */
    public ChargePointStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargePointStatus }
     *     
     */
    public void setStatus(ChargePointStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link ChargePointErrorCode }
     *     
     */
    public ChargePointErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargePointErrorCode }
     *     
     */
    public void setErrorCode(ChargePointErrorCode value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the vendorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Sets the value of the vendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorId(String value) {
        this.vendorId = value;
    }

    /**
     * Gets the value of the vendorErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorErrorCode() {
        return vendorErrorCode;
    }

    /**
     * Sets the value of the vendorErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorErrorCode(String value) {
        this.vendorErrorCode = value;
    }

}
