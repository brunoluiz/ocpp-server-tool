
package ocpp.cp._2015._10;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Defines the GetCompositeSchedule.conf PDU
 * 
 * <p>Java class for GetCompositeScheduleResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCompositeScheduleResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{urn://Ocpp/Cp/2015/10/}GetCompositeScheduleStatus"/>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="scheduleStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="chargingSchedule" type="{urn://Ocpp/Cp/2015/10/}ChargingSchedule" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCompositeScheduleResponse", propOrder = {
    "status",
    "connectorId",
    "scheduleStart",
    "chargingSchedule"
})
public class GetCompositeScheduleResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected GetCompositeScheduleStatus status;
    protected Integer connectorId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduleStart;
    protected ChargingSchedule chargingSchedule;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link GetCompositeScheduleStatus }
     *     
     */
    public GetCompositeScheduleStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCompositeScheduleStatus }
     *     
     */
    public void setStatus(GetCompositeScheduleStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the connectorId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Sets the value of the connectorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConnectorId(Integer value) {
        this.connectorId = value;
    }

    /**
     * Gets the value of the scheduleStart property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduleStart() {
        return scheduleStart;
    }

    /**
     * Sets the value of the scheduleStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduleStart(XMLGregorianCalendar value) {
        this.scheduleStart = value;
    }

    /**
     * Gets the value of the chargingSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingSchedule }
     *     
     */
    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }

    /**
     * Sets the value of the chargingSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingSchedule }
     *     
     */
    public void setChargingSchedule(ChargingSchedule value) {
        this.chargingSchedule = value;
    }

}
