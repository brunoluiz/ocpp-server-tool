
package ocpp.cp._2015._10;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChargingSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="startSchedule" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="chargingRateUnit" type="{urn://Ocpp/Cp/2015/10/}ChargingRateUnitType"/>
 *         &lt;element name="chargingSchedulePeriod" type="{urn://Ocpp/Cp/2015/10/}ChargingSchedulePeriod" maxOccurs="unbounded"/>
 *         &lt;element name="minChargingRate" type="{urn://Ocpp/Cp/2015/10/}DecimalOne" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingSchedule", propOrder = {
    "duration",
    "startSchedule",
    "chargingRateUnit",
    "chargingSchedulePeriod",
    "minChargingRate"
})
public class ChargingSchedule {

    protected Integer duration;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startSchedule;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ChargingRateUnitType chargingRateUnit;
    @XmlElement(required = true)
    protected List<ChargingSchedulePeriod> chargingSchedulePeriod;
    protected BigDecimal minChargingRate;

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDuration(Integer value) {
        this.duration = value;
    }

    /**
     * Gets the value of the startSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartSchedule() {
        return startSchedule;
    }

    /**
     * Sets the value of the startSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartSchedule(XMLGregorianCalendar value) {
        this.startSchedule = value;
    }

    /**
     * Gets the value of the chargingRateUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ChargingRateUnitType }
     *     
     */
    public ChargingRateUnitType getChargingRateUnit() {
        return chargingRateUnit;
    }

    /**
     * Sets the value of the chargingRateUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargingRateUnitType }
     *     
     */
    public void setChargingRateUnit(ChargingRateUnitType value) {
        this.chargingRateUnit = value;
    }

    /**
     * Gets the value of the chargingSchedulePeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargingSchedulePeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargingSchedulePeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargingSchedulePeriod }
     * 
     * 
     */
    public List<ChargingSchedulePeriod> getChargingSchedulePeriod() {
        if (chargingSchedulePeriod == null) {
            chargingSchedulePeriod = new ArrayList<ChargingSchedulePeriod>();
        }
        return this.chargingSchedulePeriod;
    }

    /**
     * Gets the value of the minChargingRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinChargingRate() {
        return minChargingRate;
    }

    /**
     * Sets the value of the minChargingRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinChargingRate(BigDecimal value) {
        this.minChargingRate = value;
    }

}
