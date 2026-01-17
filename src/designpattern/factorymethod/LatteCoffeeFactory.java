package designpattern.factorymethod;

/**
 * 具体工厂-拿铁工厂
 */
public class LatteCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee makeCoffee() {
        return new LatteCoffee();
    }
}
