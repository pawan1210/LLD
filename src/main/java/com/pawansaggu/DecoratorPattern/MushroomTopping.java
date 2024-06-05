package DecoratorPattern;

public class MushroomTopping extends ToppingDecorator {
    private BasePizza basePizza;
    private static int cost = 20;

    public MushroomTopping(BasePizza basePizza) {
        this.basePizza = basePizza;
    } 

    @Override
    public int getCost() {
        return this.basePizza.getCost() + cost;
    }
}
