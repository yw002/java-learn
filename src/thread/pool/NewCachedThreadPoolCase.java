package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可缓存线程池
 * 阻塞队列为 SynchronousQueue，不存储元素的阻塞队列，每个插入操作都必须等待一个移出操作
 * 如果线程池长度超过处理需要（60秒存活）会灵活回收空闲线程，如果无可回收就会新建线程
 * 适合任务数比较密集，但任务执行时间短的情况
 */
public class NewCachedThreadPoolCase {
    static class CachedThreadDemo implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                // 模拟线程执行要花费的时间
                Thread.sleep(100);
                System.out.println(name + "执行完了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个可缓存线程池，没有核心线程数，最大线程数为Integer.MAX_VALUE
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new CachedThreadDemo());
            Thread.sleep(1);
        }
        cachedThreadPool.shutdown();
    }
}
