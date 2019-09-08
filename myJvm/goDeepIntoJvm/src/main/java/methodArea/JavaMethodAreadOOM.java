package methodArea;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodAreadOOM {

    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(obj,args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
