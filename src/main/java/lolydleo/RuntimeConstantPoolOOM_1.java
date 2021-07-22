package lolydleo;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args：JDK6.0 : -XX:PermSize=6M -XX:MaxPermSize=6M
 *
 * @author zzm
 */
public class RuntimeConstantPoolOOM_1 {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
/**
 * 一些代码在已经非常受内存限制的环境中创建了大量临时对象和大量弱引用对象。
 * 这里和书本上不一样
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 	at java.lang.Integer.toString(Integer.java:401)
 * 	at java.lang.String.valueOf(String.java:3099)
 * 	at lolydleo.RuntimeConstantPoolOOM_1.main(RuntimeConstantPoolOOM_1.java:19)
 *
 * */
