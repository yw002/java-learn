package thread.lock;

/**
 * CAS（Compare-And-Swap，比较并交换）是实现【乐观锁】的核心原子操作，其核心思想是【无锁并发】，
 * 基于 JMM（Java 内存模型）的内存可见性规则实现线程安全：
 * 1.  线程操作共享变量时，先从主内存读取变量值到自身独立的工作内存，作为【预期值（oldVal）】；
 * 2.  准备更新变量时，再次对比工作内存的预期值与主内存的当前值：
 * - 若两者一致，说明变量未被其他线程修改，直接将新值（newVal）写入主内存；
 * - 若两者不一致，说明变量已被其他线程修改，放弃本次更新并自旋重试（重新读取主内存值，重复上述流程）。
 * 注：CAS 依赖硬件层面的原子指令保证操作的不可分割性，避免了传统锁的上下文切换开销。
 */
public class CasDemo {
    // JMM中主内存提供的变量值，volatile保证内存的可见性
    private static volatile int num = 0;

    public static void main(String[] args) {
        // 开启两个线程，同时在修改一个共享变量，测试CAS的效果
        Thread t1 = new Thread(() -> {
            casAdd();
        }, "线程A");
        Thread t2 = new Thread(() -> {
            casAdd();
        }, "线程B");
        t1.start();
        t2.start();
    }

    // CAS模拟，对num+1
    private static void casAdd() {
        int newNum;
        int oldNum;
        do {
            oldNum = num;  // 线程读取主内存的值到自己的工作内存，作为预期值
            newNum = num + 1;  // 准备要更新的值
        } while (!compareAndSwap(oldNum, newNum)); // 比较替换，失败则自选

        System.out.println(Thread.currentThread().getName() + "更新成功，num=" + newNum);
    }

    // CAS的核心方法，比较并替换
    private static synchronized boolean compareAndSwap(int expectVal, int newVal) {
        if (num == expectVal) {
            num = newVal;
            return true;
        }
        return false;  // 预期值不等于主内存中共享变量中的值，说明数据被其他线程操作过，触发自旋
    }
}
