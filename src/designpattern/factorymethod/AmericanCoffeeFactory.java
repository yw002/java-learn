package designpattern.factorymethod;

/**
 * 具体工厂-美式工厂
 */
public class AmericanCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee makeCoffee() {
        return new AmericanCoffee();
    }
}
