package thread.lock;

/**
 * 死锁诊断
 * 运行后，在控制台输入这两个命令
 * jps              (Java Process Status)
 * jstack -l [pid]  (Java Stack Trace)
 * <p>
 * 可视化工具
 * jconsole：性能监控工具，终端执行jconsole, windows在bin目录下打开jconsole.exe
 * VisualVM：故障处理工具（jdk8以上没有内置）
 */
public class DeadLock {
    static Object A = new Object();
    static Object B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "-lock A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("lock B 操作...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "-lock B");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("lock A 操作...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
