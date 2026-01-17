package designpattern.strategypattern;

/**
 * 策略模式
 */
public class TravelContext {
    private TravelStrategy travelStrategy;

    public TravelContext(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void selectTravel() {
        this.travelStrategy.travel();
    }

    public static void main(String[] args) {
        TravelContext travelContext = new TravelContext(new Airplain());
        travelContext.selectTravel();
    }
}
