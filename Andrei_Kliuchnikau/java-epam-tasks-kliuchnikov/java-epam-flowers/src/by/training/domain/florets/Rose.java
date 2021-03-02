package by.training.domain.florets;

import by.training.enums.Freshness;

public class Rose extends Flower {
    private boolean hasThorns;

    public Rose(Freshness freshness, int stemLength, int cost, boolean hasThorns) {
        super("Роза", freshness, stemLength, cost);
        this.hasThorns = hasThorns;
    }

    public boolean isHasThorns() {
        return hasThorns;
    }

    public void setHasThorns(boolean hasThorns) {
        this.hasThorns = hasThorns;
    }
}
