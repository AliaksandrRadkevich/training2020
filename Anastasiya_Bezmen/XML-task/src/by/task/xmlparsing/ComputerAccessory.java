package by.task.xmlparsing;

/**
 * ComputerAccessory.
 * Date: 10/19/2020
 *
 * @author Anastasiya Bezmen
 */
public class ComputerAccessory {

    private String id;
    private String name;
    private String origin;
    private int price;
    private Accessory type;
    private Boolean critical;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Accessory getType() {
        return type;
    }

    public void setType(Accessory type) {
        this.type = type;
    }

    public Boolean getCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComputerAccessory{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", origin='").append(origin).append('\'');
        sb.append(", price=").append(price);
        sb.append(", type=").append(type);
        sb.append(", critical=").append(critical);
        sb.append('}');
        return sb.toString();
    }
}
