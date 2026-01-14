package thread.lock;

/**
 * volatile的第一个特性：可见性
 * 可以防止被JIT优化，例如【while(!stop)】优化成【while(true)】
 * 解决方案一：加入这行VM参数关闭JIT即时编译器优化:  -Xint
 * 解决方案二：修饰stop的变量加上volatile，告诉JIT，不要对这个变量做优化
 */
public class VolatileDemo {
    //    static boolean stop = false; // 要添加VM参数，防止JIT优化
    static volatile boolean stop = false; // 加上volatile告诉JIT不要优化

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop = true;
            System.out.println(Thread.currentThread().getName() + ": modify stop to true");
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + stop);
        }, "t2").start();

        new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("stopped... c:" + i);
        }, "t3").start();
    }
}
