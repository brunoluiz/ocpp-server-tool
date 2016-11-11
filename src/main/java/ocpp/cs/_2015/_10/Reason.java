
package ocpp.cs._2015._10;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Reason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Reason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EmergencyStop"/>
 *     &lt;enumeration value="EVDisconnected"/>
 *     &lt;enumeration value="HardReset"/>
 *     &lt;enumeration value="Local"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="PowerLoss"/>
 *     &lt;enumeration value="Reboot"/>
 *     &lt;enumeration value="Remote"/>
 *     &lt;enumeration value="SoftReset"/>
 *     &lt;enumeration value="UnlockCommand"/>
 *     &lt;enumeration value="DeAuthorized"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Reason")
@XmlEnum
public enum Reason {

    @XmlEnumValue("EmergencyStop")
    EMERGENCY_STOP("EmergencyStop"),
    @XmlEnumValue("EVDisconnected")
    EV_DISCONNECTED("EVDisconnected"),
    @XmlEnumValue("HardReset")
    HARD_RESET("HardReset"),
    @XmlEnumValue("Local")
    LOCAL("Local"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("PowerLoss")
    POWER_LOSS("PowerLoss"),
    @XmlEnumValue("Reboot")
    REBOOT("Reboot"),
    @XmlEnumValue("Remote")
    REMOTE("Remote"),
    @XmlEnumValue("SoftReset")
    SOFT_RESET("SoftReset"),
    @XmlEnumValue("UnlockCommand")
    UNLOCK_COMMAND("UnlockCommand"),
    @XmlEnumValue("DeAuthorized")
    DE_AUTHORIZED("DeAuthorized");
    private final String value;

    Reason(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Reason fromValue(String v) {
        for (Reason c: Reason.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
