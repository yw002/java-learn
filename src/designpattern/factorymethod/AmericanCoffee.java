package designpattern.factorymethod;

/**
 * 具体产品
 */
public class AmericanCoffee implements Coffee {
    @Override
    public String getName() {
        return "american coffee";
    }

    @Override
    public void addMilk() {
        System.out.println("american coffee add milk");
    }

    @Override
    public void addSugar() {
        System.out.println("american coffee add sugar");
    }
}
