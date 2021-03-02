package by.training.domain.florets;

import by.training.enums.Freshness;

public class Carnation extends Flower {
    private int numberFlowersInBud;

    public Carnation(Freshness freshness, int stemLength, int cost, int numberFlowersInBud) {
        super("Гвоздика", freshness, stemLength, cost);
        this.numberFlowersInBud = numberFlowersInBud;
    }

    public int getNumberFlowersInBud() {
        return numberFlowersInBud;
    }

    public void setNumberFlowersInBud(int numberFlowersInBud) {
        this.numberFlowersInBud = numberFlowersInBud;
    }
}
