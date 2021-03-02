package by.training.task3.domain;

public enum AirplaneType {
    SUPPORT("support"),
    ESCORT("escort"),
    FIGHT("fight"),
    INTERCEPT("intercept"),
    SCOUT("scout");

    private String airplaneType;

    AirplaneType(String airplaneType){
        this.airplaneType = airplaneType;
    }

    public String getAirplaneType(){ 
        return airplaneType;
    }
}
