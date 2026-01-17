package designpattern.abstractfactory;

public class AmericanFlavorFactory implements ProductFactory {
    @Override
    public Coffee createCoffee() {
        return new Americano();
    }

    @Override
    public Dessert createDessert() {
        return new MatchaMousse();
    }
}
