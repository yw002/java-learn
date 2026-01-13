package thread.create;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("My Thread...run...");
    }

    /**
     * 创建线程的第一种方式，继承Thread类
     */
    public static void main(String[] args) {
        // 创建Thread对象
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        // 调用start方法启动线程
        t1.start();
        t2.start();
    }
}
