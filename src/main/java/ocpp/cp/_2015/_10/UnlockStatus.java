
package ocpp.cp._2015._10;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnlockStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnlockStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unlocked"/>
 *     &lt;enumeration value="UnlockFailed"/>
 *     &lt;enumeration value="NotSupported"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnlockStatus")
@XmlEnum
public enum UnlockStatus {

    @XmlEnumValue("Unlocked")
    UNLOCKED("Unlocked"),
    @XmlEnumValue("UnlockFailed")
    UNLOCK_FAILED("UnlockFailed"),
    @XmlEnumValue("NotSupported")
    NOT_SUPPORTED("NotSupported");
    private final String value;

    UnlockStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnlockStatus fromValue(String v) {
        for (UnlockStatus c: UnlockStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
