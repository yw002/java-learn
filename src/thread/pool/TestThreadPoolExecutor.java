package thread.pool;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的核心参数【7个】
 * 线程池的执行原理
 */
public class TestThreadPoolExecutor {
    static class MyTask implements Runnable {
        private final String name;
        private final long duration;

        public MyTask(String name) {
            this(name, 0);
        }

        public MyTask(String name, long duration) {
            this.name = name;
            this.duration = duration;
        }

        @Override
        public void run() {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] running..." + this);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyTask=" + name;
        }
    }

    public static void main(String[] args) {
        AtomicInteger c = new AtomicInteger(1);
        /* ArrayBlockingQueue 基于数组结构的有界阻塞队列 FIFO，
           1. 强制有界
           2. 底层是数组，需要提前初始化Node数组、
           3. 一把锁
         */

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);

        /* LinkedBlockingQueue 基于链表结构的有届阻塞队列 FIFO，
            1.默认无界，支持有界
            2.底层是链表，惰性添加，入队会产生新Node
            3.两把锁（头尾）
         */
//        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        /*
            了解即可：
            DelayedWorkQueue 优先级队列，每次出队任务都是当前队列中执行时间最靠前的
            SynchronousQueue 不存储元素的阻塞队列，每个插入操作必须等待一个移出操作
         */

        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                3,
                0,
                TimeUnit.MILLISECONDS,
                queue,
                r -> new Thread(r, "myThread" + c.getAndIncrement()),
                new ThreadPoolExecutor.DiscardPolicy() // 抛弃线程
//                new ThreadPoolExecutor.DiscardOldestPolicy() // 抛弃阻塞队列最前的线程，执行新线程
//                new ThreadPoolExecutor.CallerRunsPolicy()  // 用调用者所在线程执行任务【这里是main线程】
//                new ThreadPoolExecutor.AbortPolicy() // 默认策略，直接抛出异常
        );
        // 最大核心数是2，添加2个线程，不会加入到【阻塞队列】，测试一直占用核心线程，其他线程才会进入阻塞和救急
        showState(queue, threadPool);
        threadPool.submit(new MyTask("1", 3600000));
        showState(queue, threadPool);
        threadPool.submit(new MyTask("2", 3600000));
        showState(queue, threadPool);
        // 线程满了，加入阻塞队列，阻塞队列容积设计为2
        threadPool.submit(new MyTask("3"));
        showState(queue, threadPool);
        threadPool.submit(new MyTask("4"));
        showState(queue, threadPool);
        // 阻塞队列也满了，会创建一个救急线程，【核心线程+救急线程=最大线程数】,救急线程执行完当前任务，会把阻塞队列中的线程给一起执行了
        threadPool.submit(new MyTask("5", 2000));
        showState(queue, threadPool);
        // 此时最大线程是3，新加入的线程根据策略执行
        threadPool.submit(new MyTask("6"));
        showState(queue, threadPool);
    }

    private static void showState(ArrayBlockingQueue<Runnable> queue, ThreadPoolExecutor threadPool) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> tasks = new ArrayList<>();
        for (Runnable runnable : queue) {
            try {
                Field callable = FutureTask.class.getDeclaredField("callable");
                callable.setAccessible(true);
                Object adapter = callable.get(runnable);
                Class<?> clazz = Class.forName("java.util.concurrent.Executors$RunnableAdapter");
                Field task = clazz.getDeclaredField("task");
                task.setAccessible(true);
                Object o = task.get(adapter);
                tasks.add(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("pool size: " + threadPool.getPoolSize() + ", queue size: " + tasks);
    }
}
