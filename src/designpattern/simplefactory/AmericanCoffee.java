package designpattern.simplefactory;

public class AmericanCoffee implements Coffee {
    @Override
    public String getName() {
        return "americanCoffee";
    }

    @Override
    public void addMilk() {
        System.out.println("AmericanCoffee addMilk...");
    }

    @Override
    public void addSugar() {
        System.out.println("AmericanCoffee addSugar...");
    }
}
