package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建固定大小的线程池
 * 可控制并发数，适用于任务量已知，相对耗时的任务
 */
public class NewFixedThreadPoolCase {
    static class FixedThreadDemo implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 2; i++) {
                System.out.println(name + ":" + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个固定大小的线程池，核心线程数和最大线程数都是3（没有救急线程）
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 提交5个线程，第四个任务和第五个任务都是由核心线程执行的
        for (int i = 0; i < 5; i++) {
            executorService.submit(new FixedThreadDemo());
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
