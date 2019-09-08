package directMem;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
