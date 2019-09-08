package stackError;


/**
 * 1.7  -Xss2M
 */
public class JavaVMStackOOM {

    public void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM jso = new JavaVMStackOOM();
        jso.stackLeakByThread();
    }


}
