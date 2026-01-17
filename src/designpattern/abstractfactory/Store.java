package designpattern.abstractfactory;

/**
 * 1.定义两个抽象产品：咖啡和甜点
 * 2.分别实现4个具体产品：提拉米苏、拿铁咖啡、抹茶慕斯、美式咖啡
 * 3.创建抽象的产品工厂：产品工厂
 * 4.为不同的产品族（风味）实现具体的风味工厂：意大利风味工厂、美式风味工厂
 */
public class Store {
    public static void main(String[] args) {
        // 想要一份意大利风味的下午茶
        ProductFactory italianFactory = new ItalianFlavorFactory();
        Coffee c1 = italianFactory.createCoffee();
        Dessert d1 = italianFactory.createDessert();
        System.out.println(c1.getName());
        d1.show();

        System.out.println("------------");

        // 想要一份美式风味的下午茶
        ProductFactory americanFactory = new AmericanFlavorFactory();
        Coffee c2 = americanFactory.createCoffee();
        Dessert d2 = americanFactory.createDessert();
        System.out.println(c2.getName());
        d2.show();
    }
}
