package by.epam.jvd.vitebsk.task3.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*Разработать для своего варианта структуру XML документа, описать её с помощью XSD. Создать файл XML, соответствующий 
  разработанной XSD схеме. При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и 
  предельные значения, обязательно использование атрибутов и типа ID. Создать Java-класс(классы), соответствующие 
  разработанной схеме. Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией 
  из XML-файла. Для разбора использовать SAX, DOM или StAX парсер. Для сортировки объектов использовать интерфейс 
  Comparator. Произвести проверку корректности и валидности XML-документа с привлечением XSD.

Алмазный фонд.
Драгоценные и полудрагоценные камни, содержащиеся в павильоне, имеют следующие характеристики:
Name – название камня.
PreciousnessType – может быть драгоценным или полудрагоценным.
Origin – место добывания.
Visual parameters (должно быть несколько) – могут быть: цвет (зеленый, красный, желтый и т.д.), прозрачность 
(измеряется в процентах 0-100%), способы огранки (количество граней 4-15).
Value – вес камня (измеряется в каратах).*/

public class Stone implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private PreciousnessType preciousnessType;
    private String origin;
    private List<VisualParameter> parameters = new ArrayList<VisualParameter>();
    private Integer value; // stone weight (in carats)

    public Stone() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PreciousnessType getPreciousnessType() {
        return preciousnessType;
    }

    public void setPreciousnessType(PreciousnessType preciousnessType) {
        this.preciousnessType = preciousnessType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<VisualParameter> getParameters() {
        return parameters;
    }

    public String getValue() {
        return String.valueOf(value);
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    public Integer getValueInteger() {
        return value;
    }

    public void setValueInteger(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result + ((preciousnessType == null) ? 0 : preciousnessType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stone other = (Stone) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        if (preciousnessType != other.preciousnessType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Stone [id=" + id + ", name=" + name + ", preciousnessType=" + preciousnessType + ", origin=" + origin
                + ", parameters=" + parameters + ", value=" + value + "]";
    }

}
