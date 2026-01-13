package thread.interrupt;

public class MyInterrupt3 {

    public static void main(String[] args) throws InterruptedException {
//        testInterrupt1();
        testInterrupt2();
    }

    /**
     * 如果打断阻塞的线程（sleep，wait，join），线程会抛出InterruptedException异常
     */
    public static void testInterrupt1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1正在运行");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        Thread.sleep(500);
        t1.interrupt();
    }

    /**
     * 如果打断正常的线程，可以根据打断状态来标记是否退出线程
     */
    public static void testInterrupt2() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                System.out.println("打断状态：" + interrupted);
                if (interrupted) {
                    System.out.println("interrupted状态：" + interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();
        Thread.sleep(500);
        t2.interrupt();
    }
}
