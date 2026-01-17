package designpattern.strategypattern;

public class Train implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("乘坐火车出行");
    }
}
