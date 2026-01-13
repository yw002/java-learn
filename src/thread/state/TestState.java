package thread.state;

public class TestState {
    // 锁对象 = wait/notify 的调用对象
    public static final Object LOCK = new Object();

    public static void main(String[] args) {
//        testNewRunnableTerminated();
//        testBlocked();
        testWaiting();
//        testTimedSleepWaiting();
    }

    public static void testNewRunnableTerminated() {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 is running");
        }, "t1");
        Thread t2 = new Thread(() -> {
            System.out.println("t2 is running");
        }, "t2");
        t1.start();
        t2.start();
    }

    public static void testBlocked() {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 before blocked");
            synchronized (LOCK) {
                System.out.println("in sync");
            }
        }, "t1");
        t1.start();
        synchronized (LOCK) {
            System.out.println("running...");
        }
    }

    public static void testWaiting() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println("t1 before waiting");
                    LOCK.wait();
                    System.out.println("t1 after waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();


        // ========== 主线程休眠50ms，不要让主线程抢锁，让t2先执行到wait ==========
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        synchronized (LOCK) {
            LOCK.notify();
            System.out.println("执行了：LOCK.notify();");
        }
    }

    public static void testTimedSleepWaiting() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 is running");
        }, "t1");
        t1.start();
    }
}
