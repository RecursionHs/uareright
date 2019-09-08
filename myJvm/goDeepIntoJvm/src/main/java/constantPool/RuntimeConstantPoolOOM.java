package constantPool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk1.6 -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * jdk 1.8   true false
     */
    @Test
    public void testString(){
        String str1 = new StringBuilder("测试").append("常量池").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }
}
