package DecoratorPattern;

public class Margharita extends BasePizza {
    private static int cost = 10;

    @Override
    public int getCost() {
        return cost;
    }
}
