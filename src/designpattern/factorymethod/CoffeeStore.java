package designpattern.factorymethod;

/**
 * 使用工厂方法模式
 * 谁在咖啡店下单某类型咖啡，谁就提供这个类型咖啡的工厂对象
 */
public class CoffeeStore {
    public static void main(String[] args) {
        // 可以根据不同的工厂，创建不同的产品，谁下单，谁就提供工厂
        CoffeeStore coffeeStore = new CoffeeStore(new LatteCoffeeFactory());
        Coffee coffee = coffeeStore.orderCoffee();
        System.out.println(coffee.getName());
    }

    private CoffeeFactory coffeeFactory;

    public CoffeeStore(CoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee() {
        Coffee coffee = coffeeFactory.makeCoffee();
        // 添加配料
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }

}
