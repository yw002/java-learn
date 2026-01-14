package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的【可打断、可超时、多条件变量】锁
 */
public class ReentrantLockTest {
    // 创建锁对象
    static ReentrantLock lock = new ReentrantLock();
    // 条件1
    static Condition c1 = lock.newCondition();
    // 条件2
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        // 可打断
//        lockInterrupt();
        // 可超时
//        timeOutLock();
        // 多条件变量
        conditionTest();
    }

    /**
     * 可被打断的锁
     *
     * @throws InterruptedException
     */
    public static void lockInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // 开启可中断的锁
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("等待的过程中被打断");
                return;
            }
            try {
                System.out.println(Thread.currentThread().getName() + ",获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        // ======== 测试，给主线程锁，注释这段锁就会给到t1线程
        {
            lock.lock();
            System.out.println("主线程获得了锁");
        }

        t1.start();

        try {
            Thread.sleep(1000);
            t1.interrupt();
            System.out.println("执行打断");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 可超时的锁
     *
     * @throws InterruptedException
     */
    public static void timeOutLock() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                // 尝试获取锁，如果成功获取锁，返回true，否则返回false
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("t1-获取锁失败");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("t1线程-获得了锁");
            } finally {
                lock.unlock();
            }
        });

        // ======== 测试，给主线程锁，注释这段锁就会给到t1线程
        {
            lock.lock();
            System.out.println("主线程获得了锁");
        }

        t1.start();

        try {
//            long sleepTime = 1000; // 主线程释放锁后，还在t1的超时时间内，t1获得锁
            long sleepTime = 3000; // 主线程释放后，超过了t1的超时时间，t1无法获得锁
            Thread.sleep(sleepTime);
        } finally {
            lock.unlock();
            System.out.println("主线程释放了锁");
        }
    }

    /**
     * 多条件变量
     *
     * @throws InterruptedException
     */
    public static void conditionTest() throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                // 进入c1条件的等待
                c1.await();
                System.out.println(Thread.currentThread().getName() + ",acquire lock...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                // 进入c2条件的等待
                c2.await();
//                c1.await(); // 测试唤醒c1条件的所有线程
                System.out.println(Thread.currentThread().getName() + ",acquire lock...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        new Thread(() -> {
            lock.lock();
            // 唤醒c1条件的线程
            c1.signal();
            // 唤醒c1条件的所有线程
//            c1.signalAll();

            // 唤醒c2条件的线程
            c2.signal();
            System.out.println(Thread.currentThread().getName() + ",acquire lock...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t3").start();
    }
}
