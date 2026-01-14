package thread.lock;

/**
 * volatile的第二个特性：防止指令重排
 * 指令重排问题复现
 * 现象：线程2会打印出 【x=1, y=0】 或者 【x=0, y=1】 的异常结果
 * 原因：线程1中的两行赋值代码，JVM会进行指令重排
 */
public class VolatileReorderTest {
    // 定义两个普通成员变量，未加volatile，允许指令重排
    private static int x = 0, y = 0;
    // 定义两个标记变量，记录赋值完成状态
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            // 每次循环重置所有变量为初始值
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            // 线程1
            Thread t1 = new Thread(() -> {
                x = 1; // 语句A：把变量x的值改成 1
                a = y; // 语句B：此时y有两种可能：0或1
            });

            // 线程2
            Thread t2 = new Thread(() -> {
                y = 1; // 语句C：把变量y的值改成 1
                b = x; // 语句D：此时x有两种可能：0或1
            });

            // 只要执行了start()，两个子线程就会被 CPU 调度，完全随机，是标准的并发执行，没有任何先后顺序。
            t1.start();
            t2.start();
            // join()是写给主线程看的，对t1和t2这两个子线程没有任何约束能力。
            t1.join();
            t2.join();

             /*
                在不发生指令重排的时候，所有可能的情况如下：
                为了方便复制和检查，这是所有的 6 种情况：
                A B C D：线程1完全执行完，线程2再执行完。（a=0,b=1）
                A C B D：线程1执行一半，线程2执行一半，线程1执行完，线程2执行完。（a=1,b=1）
                A C D B：线程1执行一半，线程2执行完，线程1执行完。（a=1,b=1）
                C A B D：线程2执行一半，线程1执行完，线程2执行完。（a=1,b=1）
                C A D B：线程2执行一半，线程1执行完，线程2执行完。（a=1,b=1）
                C D A B：线程2完全执行完，线程1再执行完。（a=1,b=0）
             */
            if (a == 0 && b == 0) {
                System.out.println("指令重排发生了！count=" + count + " , a=" + a + " , b=" + b);
                break; // 出现异常结果就停止循环
            }
        }
    }
}
