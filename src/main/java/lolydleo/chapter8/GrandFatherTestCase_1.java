package lolydleo.chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author zzm
 * @learner lolydleo
 */
public class GrandFatherTestCase_1 {

    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class,
                        "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherTestCase_1().new Son()).thinking();
    }

}
/**
 * 我以为在Son中的thinking方法调用后会得到Father，但是并没有，因为这个用法在JDK 7 Update 9 之后被作为安全问题修正了
 * 原因是必须保证findSpecial()查找方法版本时受到的访问约束。
 * */
