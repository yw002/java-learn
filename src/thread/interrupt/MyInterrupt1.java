package thread.interrupt;

/**
 * 退出线程的方法
 */
public class MyInterrupt1 extends Thread {
    volatile boolean flag = false; // 线程执行的退出标记

    @Override
    public void run() {
        while (!flag) {
            System.out.println("My Thread...run....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建Thread对象
        MyInterrupt1 t1 = new MyInterrupt1();
        t1.start();
        // 主线程休眠6秒
        Thread.sleep(6000);
        // 更改标记为true
        t1.flag = true;
    }
}
