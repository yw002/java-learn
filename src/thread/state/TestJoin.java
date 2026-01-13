package thread.state;

public class TestJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1");
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join(); // 加入线程t1，只有t1线程执行完毕后，再执行该线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join(); // 加入线程t2，只有t2线程执行完毕后，再执行该线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });
        // 启动线程，先启动哪个无所谓
        t3.start();
        t2.start();
        t1.start();
    }
}
