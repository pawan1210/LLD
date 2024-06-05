package StrategyPattern.WithStrategyPattern;

public class Main {
    public static void main(String args[]) { 
        SportsDriveStrategy sportsDriveStrategy = new SportsDriveStrategy();
        OffRoadVehicle offRoadVehicle = new OffRoadVehicle(sportsDriveStrategy);
        SportsVehicle sportsVehicle = new SportsVehicle(sportsDriveStrategy);
        offRoadVehicle.drive();
        sportsVehicle.drive();
    }
}
