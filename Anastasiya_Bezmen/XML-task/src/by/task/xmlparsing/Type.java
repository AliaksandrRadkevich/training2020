package by.task.xmlparsing;

/**
 * Type.
 * Date: 10/19/2020
 *
 * @author Anastasiya Bezmen
 */
public enum Type {
    IO_DEVICE,
    MULTIMEDIA_DEVICE;


    public static Type findByName(String typeName) {
        for (Type type : Type.values()) {
            if (typeName.equals(type.name())) {
                return type;
            }
        }
        throw new RuntimeException(String.format("Emun с именем %s не существует", typeName));
    }
}
