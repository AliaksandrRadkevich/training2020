package by.task.xmlparsing;

/**
 * Accessory.
 * Date: 10/19/2020
 *
 * @author Anastasiya Bezmen
 */
public class Accessory {

    private Boolean peripheral;
    private int energyConsumption;
    private Boolean availabilityCooler;
    private Type Type;
    private Port port;

    public Boolean getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Boolean peripheral) {
        this.peripheral = peripheral;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Boolean getAvailabilityCooler() {
        return availabilityCooler;
    }

    public void setAvailabilityCooler(Boolean availabilityCooler) {
        this.availabilityCooler = availabilityCooler;
    }

    public by.task.xmlparsing.Type getType() {
        return Type;
    }

    public void setType(by.task.xmlparsing.Type type) {
        Type = type;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Accessory{");
        sb.append("peripheral=").append(peripheral);
        sb.append(", energyConsumption=").append(energyConsumption);
        sb.append(", availabilityCooler=").append(availabilityCooler);
        sb.append(", Type=").append(Type);
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
