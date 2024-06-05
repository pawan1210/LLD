package StrategyPattern.WithStrategyPattern;

public class SportsDriveStrategy implements DriveStrategy {
    public void drive() {
        System.out.println("Start driving in sports mode");
    }
}
