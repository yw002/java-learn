package designpattern.chainofresponsibility;

/**
 * 抽象处理者
 */
public abstract class Handler {
    protected Handler handler;

    public void setNext(Handler handler) {
        this.handler = handler;
    }

    /**
     * 处理过程
     * 需要子类实现
     */
    public abstract void process(OrderInfo order);
}
