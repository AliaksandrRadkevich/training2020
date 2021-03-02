package by.training.domain;

import java.util.List;

public class Greenhouse {

    private List<Plant> plantList;

    public Greenhouse(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }
}