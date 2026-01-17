package designpattern.base;

public class LatteCoffee implements Coffee{
    @Override
    public String getName() {
        return "latteCoffee";
    }

    @Override
    public void addMilk() {
        System.out.println("LatteCoffee addMilk");
    }

    @Override
    public void addSugar() {
        System.out.println("LatteCoffee addSugar");
    }
}
