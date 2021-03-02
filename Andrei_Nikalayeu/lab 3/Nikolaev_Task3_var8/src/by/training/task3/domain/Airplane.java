package by.training.task3.domain;

public class Airplane {
    private int id;
    private String model;
    private String origin;
    private AirplaneType airplaneType;
    private byte seats;
    private Gun gun;
    private RocketAirToAir rocketAirToAir;
    private RocketAirToGrnd rocketAirToGrnd;
    private Radar radar;
    private double length;
    private double width;
    private double height;
    private int price;

    public Airplane() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public byte getSeats() {
        return seats;
    }

    public void setSeats(byte seats) {
        this.seats = seats;
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public RocketAirToAir getRocketAirToAir() {
        return rocketAirToAir;
    }

    public void setRocketAirToAir(RocketAirToAir rocketAirToAir) {
        this.rocketAirToAir = rocketAirToAir;
    }

    public RocketAirToGrnd getRocketAirToGrnd() {
        return rocketAirToGrnd;
    }

    public void setRocketAirToGrnd(RocketAirToGrnd rocketAirToGrnd) {
        this.rocketAirToGrnd = rocketAirToGrnd;
    }

    public Radar getRadar() {
        return radar;
    }

    public void setRadar(Radar radar) {
        this.radar = radar;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airplaneType == null) ? 0 : airplaneType.hashCode());
        result = prime * result + ((gun == null) ? 0 : gun.hashCode());
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + id;
        temp = Double.doubleToLongBits(length);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + price;
        result = prime * result + ((radar == null) ? 0 : radar.hashCode());
        result = prime * result + ((rocketAirToAir == null) ? 0 : rocketAirToAir.hashCode());
        result = prime * result + ((rocketAirToGrnd == null) ? 0 : rocketAirToGrnd.hashCode());
        result = prime * result + seats;
        temp = Double.doubleToLongBits(width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Airplane other = (Airplane) obj;
        if (airplaneType != other.airplaneType)
            return false;
        if (gun == null) {
            if (other.gun != null)
                return false;
        } else if (!gun.equals(other.gun))
            return false;
        if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
            return false;
        if (id != other.id)
            return false;
        if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (price != other.price)
            return false;
        if (radar == null) {
            if (other.radar != null)
                return false;
        } else if (!radar.equals(other.radar))
            return false;
        if (rocketAirToAir == null) {
            if (other.rocketAirToAir != null)
                return false;
        } else if (!rocketAirToAir.equals(other.rocketAirToAir))
            return false;
        if (rocketAirToGrnd == null) {
            if (other.rocketAirToGrnd != null)
                return false;
        } else if (!rocketAirToGrnd.equals(other.rocketAirToGrnd))
            return false;
        if (seats != other.seats)
            return false;
        if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Airplane \n[id=" + id + 
                        ",\n model=" + model + 
                        ",\n origin=" + origin + 
                        ",\n airplaneType=" + airplaneType + 
                        ",\n seats=" + seats + 
                        ",\n gun=" + gun + 
                        ",\n Rocket air to air=" + rocketAirToAir + 
                        ",\n Rocket air to grnd=" + rocketAirToGrnd + 
                        ",\n radar=" + radar + 
                        ",\n length=" + length + 
                        ",\n width=" + width + 
                        ",\n height=" + height + 
                        ",\n price=" + price + "]";
    }
}
