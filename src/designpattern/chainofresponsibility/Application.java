package designpattern.chainofresponsibility;

/**
 * 责任链模式
 */
public class Application {
    public static void main(String[] args) {
        // 校验订单
        Handler orderValidation = new OrderValidation();
        // 补充订单信息
        Handler orderFill = new OrderFill();
        // 订单算价
        Handler orderAmountCalculate = new OrderAmountCalculate();
        // 订单落库
        Handler orderCreate = new OrderCreate();

        // 设置责任链路
        orderValidation.setNext(orderFill);
        orderFill.setNext(orderAmountCalculate);
        orderAmountCalculate.setNext(orderCreate);

        // 开始执行
        orderValidation.process(new OrderInfo());
    }
}
