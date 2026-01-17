package designpattern.abstractfactory;

public class ItalianFlavorFactory implements ProductFactory{
    @Override
    public Coffee createCoffee() {
        return new Latte();
    }

    @Override
    public Dessert createDessert() {
        return new Tiramisu();
    }
}
