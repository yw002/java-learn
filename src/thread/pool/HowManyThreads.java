package thread.pool;

/**
 * 如何确定核心线程数
 * 高并发，执行时间短，减少线程上下文切换，
 * --- 线程数N+1
 * 并发不高，执行时间长
 * --- 计算密集型，N+1
 * --- IO密集型，2N+1
 * 并发高，执行时间长
 * --- 缓存，增加服务器，同时参考上面第二条
 */
public class HowManyThreads {
    public static void main(String[] args) {
        // 获取本机CPU的核心数N（逻辑处理器）
        int N = Runtime.getRuntime().availableProcessors();
        System.out.println("核心数：" + N);
    }
}
