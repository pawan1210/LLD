package StrategyPattern.WithoutStrategyPattern;

/**
 * Vehicle is the parent class and OffRoadVehicle
 * and SportsVehicle are the child classes that have
 * the same overridden implementation of drive()
 * method.
 * 
 * The solution to this is to create an interface that
 * will define a method that is being duplicated. Now the classes
 * are going to be instantiated with an instance of this class.
 * This interface will be termed as a strategy interface.
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Hello world");
    }
}
