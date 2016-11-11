
package ocpp.cs._2015._10;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitOfMeasure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitOfMeasure">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Celsius"/>
 *     &lt;enumeration value="Fahrenheit"/>
 *     &lt;enumeration value="Wh"/>
 *     &lt;enumeration value="kWh"/>
 *     &lt;enumeration value="varh"/>
 *     &lt;enumeration value="kvarh"/>
 *     &lt;enumeration value="W"/>
 *     &lt;enumeration value="kW"/>
 *     &lt;enumeration value="VA"/>
 *     &lt;enumeration value="kVA"/>
 *     &lt;enumeration value="var"/>
 *     &lt;enumeration value="kvar"/>
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="V"/>
 *     &lt;enumeration value="K"/>
 *     &lt;enumeration value="Percent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitOfMeasure")
@XmlEnum
public enum UnitOfMeasure {

    @XmlEnumValue("Celsius")
    CELSIUS("Celsius"),
    @XmlEnumValue("Fahrenheit")
    FAHRENHEIT("Fahrenheit"),
    @XmlEnumValue("Wh")
    WH("Wh"),
    @XmlEnumValue("kWh")
    K_WH("kWh"),
    @XmlEnumValue("varh")
    VARH("varh"),
    @XmlEnumValue("kvarh")
    KVARH("kvarh"),
    W("W"),
    @XmlEnumValue("kW")
    K_W("kW"),
    VA("VA"),
    @XmlEnumValue("kVA")
    K_VA("kVA"),
    @XmlEnumValue("var")
    VAR("var"),
    @XmlEnumValue("kvar")
    KVAR("kvar"),
    A("A"),
    V("V"),
    K("K"),
    @XmlEnumValue("Percent")
    PERCENT("Percent");
    private final String value;

    UnitOfMeasure(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitOfMeasure fromValue(String v) {
        for (UnitOfMeasure c: UnitOfMeasure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
