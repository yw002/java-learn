package base.trycatchfinally;

public class TestTryCatchFinally {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    /**
     * 有异常，且捕获
     */
    public static void test1() {
        try {
            System.out.println("1. try代码块执行（有异常）");
            int a = 10 / 0; // 抛出算术异常
            System.out.println("try后续代码（不会执行）");
        } catch (ArithmeticException e) {
            System.out.println("2. catch代码块执行（捕获异常）");
        } finally {
            System.out.println("3. finally代码块执行");
        }
        System.out.println("4. 后续代码执行");

    }

    /**
     * 有异常且未被捕获
     */
    public static void test2() {
        try {
            System.out.println("1. try代码块执行（有异常）");
            int a = 10 / 0; // 算术异常
        } catch (NullPointerException e) { // 捕获的异常类型不匹配
            System.out.println("2. catch代码块执行");
        } finally {
            System.out.println("3. finally代码块执行");
        }
        System.out.println("4. 后续代码执行（不会执行）");
    }
}
