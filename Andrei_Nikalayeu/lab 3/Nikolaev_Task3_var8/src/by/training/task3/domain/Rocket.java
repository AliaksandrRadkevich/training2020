package by.training.task3.domain;

public abstract class Rocket {
    private byte amount;

    public Rocket(byte amount) {
        this.amount = amount;
    }

    public byte getAmount() {
        return amount;
    }

    public void setAmount(byte amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
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
        Rocket other = (Rocket) obj;
        if (amount != other.amount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[amount=" + amount + "]";
    }
}
