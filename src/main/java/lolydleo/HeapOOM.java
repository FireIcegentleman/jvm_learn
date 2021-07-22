package lolydleo;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出异常
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 堆大小为20MB，不能扩展，第三个参数用于让虚拟机再出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析
 *
 * @author zzm
 * @learner lolydleo
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        // 达到最大堆的容量限制
        while (true) {
            list.add(new OOMObject());
        }
    }
}

/**
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid13184.hprof ...
 * Heap dump file created [28315032 bytes in 0.064 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3210)
 * 	at java.util.Arrays.copyOf(Arrays.java:3181)
 * 	at java.util.ArrayList.grow(ArrayList.java:267)
 * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:241)
 * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:233)
 * 	at java.util.ArrayList.add(ArrayList.java:464)
 * 	at lolydleo.HeapOOM.main(HeapOOM.java:22)
 *
 * 	解决这个内存区域的异常，常规的处理方法时首先通过内存映像分析工具堆Dump出来的堆转储快照进行分析
 * 	1. 确认内存中导致OOM的对象是否是必要的，清楚是否出现了内存泄漏还是内存溢出
 * 	2.查看泄露对象到GC Roots的引用链
 *
 * 	IDEA使用JProfiler作为内存映像分析工具，付费的
 * */
