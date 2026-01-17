package designpattern.simplefactory;

/**
 * 简单工厂模式不属于23种设计模式之一
 */
public class CoffeeStore {
    public static void main(String[] args) {
        Coffee coffee = orderCoffee("american");
        System.out.println(coffee.getName());
    }

    public static Coffee orderCoffee(String type) {
        // 通过工厂获得对象，不需要知道对象的实现细节
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        Coffee coffee = factory.createCoffee(type);
        // 添加配料
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
