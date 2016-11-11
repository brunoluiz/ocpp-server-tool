
package ocpp.cp._2015._10;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargingSchedulePeriod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargingSchedulePeriod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startPeriod" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="limit" type="{urn://Ocpp/Cp/2015/10/}DecimalOne"/>
 *         &lt;element name="numberPhases" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargingSchedulePeriod", propOrder = {
    "startPeriod",
    "limit",
    "numberPhases"
})
public class ChargingSchedulePeriod {

    protected int startPeriod;
    @XmlElement(required = true)
    protected BigDecimal limit;
    protected Integer numberPhases;

    /**
     * Gets the value of the startPeriod property.
     * 
     */
    public int getStartPeriod() {
        return startPeriod;
    }

    /**
     * Sets the value of the startPeriod property.
     * 
     */
    public void setStartPeriod(int value) {
        this.startPeriod = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLimit(BigDecimal value) {
        this.limit = value;
    }

    /**
     * Gets the value of the numberPhases property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberPhases() {
        return numberPhases;
    }

    /**
     * Sets the value of the numberPhases property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberPhases(Integer value) {
        this.numberPhases = value;
    }

}
