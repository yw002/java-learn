package thread.lock;

/**
 * synchronized 对象锁
 * 采用互斥方式让同一时刻最多只有一个线程能持有【对象锁】，其他线程想获取对象锁就会被阻塞
 * 底层：monitor实现，是个jvm级别的对象，线程获得锁需要使用对象(锁)关联monitor
 * monitor内部三个属性：Owner、EntryList、WaitSet
 * Owner是关联的获得锁的线程，只关联一个线程；
 * EntryList关联的是处于阻塞状态的线程；
 * WaitSet关联的事处于waiting状态的线程。
 */
public class TicketDemo {
    static final Object lock = new Object();
    int ticketNum = 10;

    public void getTicket() {
        synchronized (lock) {
            if (ticketNum <= 0) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + "抢到一张票，票号：" + ticketNum);
            // 非原子性操作
            ticketNum--;
        }
    }

    public static void main(String[] args) {
        TicketDemo ticketDemo = new TicketDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                ticketDemo.getTicket();
            }).start();
        }
    }
}
