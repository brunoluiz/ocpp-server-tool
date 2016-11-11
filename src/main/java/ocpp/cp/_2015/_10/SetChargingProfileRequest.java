
package ocpp.cp._2015._10;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the SetChargingProfile.req PDU
 * 
 * <p>Java class for SetChargingProfileRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetChargingProfileRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="connectorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="csChargingProfiles" type="{urn://Ocpp/Cp/2015/10/}ChargingProfile"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetChargingProfileRequest", propOrder = {
    "connectorId",
    "csChargingProfiles"
})
public class SetChargingProfileRequest {

    protected int connectorId;
    @XmlElement(required = true)
    protected ChargingProfile csChargingProfiles;

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
     * Gets the value of the csChargingProfiles property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingProfile }
     *     
     */
    public ChargingProfile getCsChargingProfiles() {
        return csChargingProfiles;
    }

    /**
     * Sets the value of the csChargingProfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingProfile }
     *     
     */
    public void setCsChargingProfiles(ChargingProfile value) {
        this.csChargingProfiles = value;
    }

}
