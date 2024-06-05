package DecoratorPattern;

public class Main {
    public static void main(String args[]) {
        BasePizza basePizza = new Margharita();
        BasePizza mushroomToppingPizza = new MushroomTopping(new Margharita());
        System.out.println(basePizza.getCost());
        System.out.println(mushroomToppingPizza.getCost());
    }
}
