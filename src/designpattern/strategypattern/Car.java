package designpattern.strategypattern;

public class Car implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("使用汽车出行");
    }
}
