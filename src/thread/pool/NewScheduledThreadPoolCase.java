package thread.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 提供了延迟和周期执行功能的ThreadPoolExecutor
 * 可以执行延迟任务的线程池，支持定时及周期性任务执行
 */
public class NewScheduledThreadPoolCase {
    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                System.out.println(name + ",开始：" + new Date());
                Thread.sleep(1000);
                System.out.println(name + ",结束：" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 按照周期执行的线程池，核心线程数为2，最大线程数为Integer.MAX_VALUE
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        System.out.println("程序开始：" + new Date());
        /*
         * schedule 提交任务到线程池中
         * 第一个参数：提交的任务
         * 第二个参数：任务执行的延迟时间
         * 第三个参数：时间单位
         */
        scheduledExecutorService.schedule(new Task(), 0, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Task(), 1, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        Thread.sleep(5000);
        // 关闭线程池
        scheduledExecutorService.shutdown();
    }
}
