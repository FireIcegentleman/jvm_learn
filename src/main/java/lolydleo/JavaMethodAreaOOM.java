package lolydleo;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args： -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author zzm
 * @leaner lolydleo
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            // 要设置的代理类
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            // 设置回调即MethodInterceptor的实现类
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {
    }
}

/**
 * 关于CGlib:Cglib是一个强大的、高性能的代码生成包，它广泛被许多AOP框架使用，为他们提供方法的拦截。
 * 在JVM中执行程序并不一定非要写Java代码----只要你能生成Java字节码，JVM并不关心字节码的来源
 *
 * CGLIB 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
 * CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
 * CGLIB 缺点：对于final方法，无法进行代理。
 * CGLIB代理主要通过对字节码的操作，为对象引入间接级别，以控制对象的访问。我们知道Java中有一个动态代理也是做这个事情的，那我们为什么不直接使用Java动态代理，而要使用CGLIB呢？答案是CGLIB相比于JDK动态代理更加强大，JDK动态代理虽然简单易用，
 * 但是其有一个致命缺陷是，只能对接口进行代理。如果要代理的类为一个普通类、没有接口，那么Java动态代理就没法使用了。
 *
 * 关于动态代理：
 *  对于接口的另一种使用方式
 * */

/**
 * 一个类要被垃圾收集器回收，要达成的条件是比较苛刻的。
 * JDK 8之后，永久代退出历史舞台，元空间作为替代者登场
 * */
