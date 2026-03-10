package base.package2;

import base.package1.Parent;

// 不同包子类
public class DifferentPackageChild extends Parent {
    public void test() {
        // 1. protected：子类可访问（通过super/本类对象）
        System.out.println(super.protectedField); // ✅ 子类访问protected
        super.protectedMethod();

        // 2. public：任意位置可访问
        System.out.println(super.publicField); // ✅ 访问public
        super.publicMethod();

        // 3. default/private：无法访问
        // System.out.println(super.defaultField); // ❌ 不同包无法访问default
        // System.out.println(super.privateField); // ❌ 无法访问private

        // 注意：不能通过父类对象访问protected（核心易错点）
        Parent parent = new Parent();
        // parent.protectedMethod(); // ❌ 编译报错
    }
}

// 不同包非子类
class DifferentPackageNonChild {
    public void test() {
        Parent parent = new Parent();
        parent.publicMethod();    // ✅ 仅public可访问
//         parent.protectedMethod(); // ❌ 非子类无法访问protected
        // parent.defaultMethod();   // ❌ 不同包无法访问default
        // parent.privateMethod();   // ❌ 无法访问private
    }
}