package base.package1;

public class Father {
    private String testPrivate = "private";
    String testDefault = "default";
    protected String testProtected = "protected";
    public String testPublic = "public";

    public static void main(String[] args) {
        Father father = new Father();
        // 同类都可以访问
        System.out.println(father.testPrivate);
        System.out.println(father.testDefault);
        System.out.println(father.testProtected);
        System.out.println(father.testPublic);
    }
}
