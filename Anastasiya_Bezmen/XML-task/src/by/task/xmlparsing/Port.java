package by.task.xmlparsing;

/**
 * Port.
 * Date: 10/19/2020
 *
 * @author Anastasiya Bezmen
 */
public enum Port {
    COM,
    USB,
    LPT;

    public static Port findByName(String portName) {
        for (Port port : Port.values()) {
            if (portName.equals(port.name())) {
                return port;
            }
        }
        throw new RuntimeException(String.format("Emun с именем %s не существует", portName));
    }
    }
