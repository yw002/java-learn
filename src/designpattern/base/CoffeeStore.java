package designpattern.base;

/**
 * 没有使用任何设计模式的例子
 */
public class CoffeeStore {
    public static void main(String[] args) {
        Coffee coffee = orderCoffee("latte");
        System.out.println(coffee.getName());
    }

    public static Coffee orderCoffee(String type) {
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        }
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
