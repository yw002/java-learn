package designpattern.strategypattern;

public class Airplain implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("搭乘飞机✈️出行");
    }
}
