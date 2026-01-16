package thread.state;

public class TestWaitSleep {
    static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
//        illegalWait();
//        waiting();
        sleeping();
    }

    /**
     * wait()/notify()/notifyAll() 必须跟 synchronized 配合使用
     */
    private static void illegalWait() throws InterruptedException {
//        synchronized (LOCK) {
        LOCK.wait();
//        }
    }

    /**
     * wait()方法执行后会释放对象锁，允许其他线程获得对象的锁（放弃cpu，你们可以用）
     */
    private static void waiting() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println("waiting...");
                    LOCK.wait(5000L);
                    System.out.println("running...end...");
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(100);
        synchronized (LOCK) {
            System.out.println("other...");
        }
    }

    /**
     * sleep() 如果在synchronized中执行，不会释放锁🔒给其他线程（放弃cpu，谁都不能用）
     *
     * @throws InterruptedException
     */
    private static void sleeping() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println("sleeping...");
                    Thread.sleep(5000L);
                    System.out.println("running...end...");
                } catch (InterruptedException e) {
                    System.out.println("interrupted...");
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(100);
        synchronized (LOCK) {
            System.out.println("other...");
        }
    }
}
