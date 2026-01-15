package thread.using;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量，可以控制方法的访问线程的数量
 */
public class SemaphoreCaseDemo {
    public static void main(String[] args) {
        // 创建semaphore对象
        Semaphore semaphore = new Semaphore(3);
        // 10个线程同时运行
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 获取许可，计数-1
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end...");
                } finally {
                    // 释放许可，计数+1
                    semaphore.release();
                }
            }).start();
        }
    }
}
