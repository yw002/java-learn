package thread.using;

/**
 * 1.ThreadLocal可以实现【资源对象】的线程隔离，让每个线程各用各的资源对象，避免争用引发线程安全问题
 * 2.ThreadLocal同时实现了线程内资源共享
 * 3.每个线程内有一个ThreadLocalMap类型的成员变量，用来存储资源对象
 * set、get、remove方法都是以ThreadLocal自己的实例作为key，资源对象作为value
 * 4.ThreadLocal的内存泄漏问题
 * ThreadLocalMap中key是弱引用，key会被GC释放，关联value后就变成强饮用不会被GC，建议主动remove释放
 */
public class ThreadLocalDemo {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            threadLocal.set("cat");
            print(name);
            System.out.println(name + "-after remove:" + threadLocal.get());
        }, "t1").start();

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            threadLocal.set("dog");
            print(name);
            System.out.println(name + "-after remove:" + threadLocal.get());
        }, "t2").start();
    }

    static void print(String str) {
        // 打印当前线程本地内存中本地变量的值
        System.out.println(str + ":" + threadLocal.get());
        // 清楚本地内存中的本地变量
        threadLocal.remove();
    }
}
