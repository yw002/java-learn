package thread.interrupt;

/**
 * 通过调用stop()方法来结束线程（已然弃用的方法）
 */
public class MyInterrupt2 extends Thread {
    volatile boolean flag = false; // 线程执行的退出标记

    @Override
    public void run() {
        while (!flag) {
            System.out.println("MyThread...run...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程对象
        MyInterrupt2 t1 = new MyInterrupt2();
        t1.start();

        // 主线程休眠2秒
        Thread.sleep(6000);

        // 调用stop方法强行终止线程
        t1.stop();
    }
}
