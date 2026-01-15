package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程的线程池
 * 用唯一的工作线程来执行任务，适用于按照顺序执行的任务（FIFO）
 */
public class NewSingleThreadPoolCase {
    static int count = 0;

    static class SingleThreadDemo implements Runnable {
        @Override
        public void run() {
            count++;
            System.out.println(Thread.currentThread().getName() + ":" + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 单个线程池，核心线程数和最大线程数都是1（没有救急线程）
        ExecutorService exec = Executors.newSingleThreadExecutor();
        // 往线程池提交10个任务
        for (int i = 0; i < 10; i++) {
            exec.execute(new SingleThreadDemo());
            Thread.sleep(5);
        }
        exec.shutdown();

    }
}
