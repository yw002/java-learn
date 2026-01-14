package thread.lock;

/**
 * 原子性
 */
public class AtomicityTicket {
    int ticketNum = 10;

    /**
     * 加锁保证原子性
     * 也可以使用juc里面的lock在方法加锁
     */
    public synchronized void getTicket() {
        if (ticketNum <= 0) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + "抢到一张票，剩余：" + ticketNum);
        // 非原子性操作
        ticketNum--;
    }

    public static void main(String[] args) {
        AtomicityTicket atomicityTicket = new AtomicityTicket();
        for (int i = 0; i < 20; i++) {
            // 相当于在runnable接口的run方法中调用了 getTicket()方法
            new Thread(atomicityTicket::getTicket).start();
            // 写法2：JDK8 Lambda表达式【最通用】 ==========
//            new Thread(() -> atomicityTicket.getTicket()).start();
        }
    }
}
