package designpattern.factorymethod;

/**
 * 具体产品
 */
public class LatteCoffee implements Coffee {
    @Override
    public String getName() {
        return "latte coffee";
    }

    @Override
    public void addMilk() {
        System.out.println("latte coffee add milk");
    }

    @Override
    public void addSugar() {
        System.out.println("latte coffee add sugar");
    }
}
