package base.package1;

// 父类（测试不同修饰符的变量/方法）
public class Parent {
    // 1. private：仅本类可见
    private String privateField = "私有字段";
    private void privateMethod() {
        System.out.println("私有方法：" + privateField);
    }

    // 2. default：本包可见
    String defaultField = "默认字段";
    void defaultMethod() {
        System.out.println("默认方法：" + defaultField);
    }

    // 3. protected：本包+子类可见
    protected String protectedField = "受保护字段";
    protected void protectedMethod() {
        System.out.println("受保护方法：" + protectedField);
    }

    // 4. public：所有位置可见
    public String publicField = "公共字段";
    public void publicMethod() {
        System.out.println("公共方法：" + publicField);
    }

    // 本类内部：所有修饰符都能访问
    public void testSelf() {
        privateMethod();   // ✅ 访问private
        defaultMethod();   // ✅ 访问default
        protectedMethod(); // ✅ 访问protected
        publicMethod();    // ✅ 访问public
    }
}

// 同包子类（测试default/protected）
class SamePackageChild {
    public void test() {
        Parent parent = new Parent();
        parent.defaultMethod();   // ✅ 同包访问default
        parent.protectedMethod(); // ✅ 同包访问protected
        parent.publicMethod();    // ✅ 同包访问public
        // parent.privateMethod(); // ❌ 无法访问private
    }
}