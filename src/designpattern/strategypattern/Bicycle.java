package designpattern.strategypattern;

public class Bicycle implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("使用自行车出行");
    }
}
