package by.training.domain.florets;

import by.training.enums.Freshness;

public class Peony extends Flower {
    private int flowerDiameter;

    public Peony(Freshness freshness, int stemLength, int cost, int flowerDiameter) {
        super("Пион", freshness, stemLength, cost);
        this.flowerDiameter = flowerDiameter;
    }

    public int getFlowerDiameter() {
        return flowerDiameter;
    }

    public void setFlowerDiameter(int flowerDiameter) {
        this.flowerDiameter = flowerDiameter;
    }
}
