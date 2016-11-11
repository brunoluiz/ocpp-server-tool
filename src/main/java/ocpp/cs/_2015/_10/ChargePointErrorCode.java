
package ocpp.cs._2015._10;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargePointErrorCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChargePointErrorCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ConnectorLockFailure"/>
 *     &lt;enumeration value="EVCommunicationError"/>
 *     &lt;enumeration value="GroundFailure"/>
 *     &lt;enumeration value="HighTemperature"/>
 *     &lt;enumeration value="InternalError"/>
 *     &lt;enumeration value="LocalListConflict"/>
 *     &lt;enumeration value="NoError"/>
 *     &lt;enumeration value="OtherError"/>
 *     &lt;enumeration value="OverCurrentFailure"/>
 *     &lt;enumeration value="OverVoltage"/>
 *     &lt;enumeration value="PowerMeterFailure"/>
 *     &lt;enumeration value="PowerSwitchFailure"/>
 *     &lt;enumeration value="ReaderFailure"/>
 *     &lt;enumeration value="ResetFailure"/>
 *     &lt;enumeration value="UnderVoltage"/>
 *     &lt;enumeration value="WeakSignal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChargePointErrorCode")
@XmlEnum
public enum ChargePointErrorCode {

    @XmlEnumValue("ConnectorLockFailure")
    CONNECTOR_LOCK_FAILURE("ConnectorLockFailure"),
    @XmlEnumValue("EVCommunicationError")
    EV_COMMUNICATION_ERROR("EVCommunicationError"),
    @XmlEnumValue("GroundFailure")
    GROUND_FAILURE("GroundFailure"),
    @XmlEnumValue("HighTemperature")
    HIGH_TEMPERATURE("HighTemperature"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError"),
    @XmlEnumValue("LocalListConflict")
    LOCAL_LIST_CONFLICT("LocalListConflict"),
    @XmlEnumValue("NoError")
    NO_ERROR("NoError"),
    @XmlEnumValue("OtherError")
    OTHER_ERROR("OtherError"),
    @XmlEnumValue("OverCurrentFailure")
    OVER_CURRENT_FAILURE("OverCurrentFailure"),
    @XmlEnumValue("OverVoltage")
    OVER_VOLTAGE("OverVoltage"),
    @XmlEnumValue("PowerMeterFailure")
    POWER_METER_FAILURE("PowerMeterFailure"),
    @XmlEnumValue("PowerSwitchFailure")
    POWER_SWITCH_FAILURE("PowerSwitchFailure"),
    @XmlEnumValue("ReaderFailure")
    READER_FAILURE("ReaderFailure"),
    @XmlEnumValue("ResetFailure")
    RESET_FAILURE("ResetFailure"),
    @XmlEnumValue("UnderVoltage")
    UNDER_VOLTAGE("UnderVoltage"),
    @XmlEnumValue("WeakSignal")
    WEAK_SIGNAL("WeakSignal");
    private final String value;

    ChargePointErrorCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChargePointErrorCode fromValue(String v) {
        for (ChargePointErrorCode c: ChargePointErrorCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
