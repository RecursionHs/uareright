package stackError;

public class JavaStackOOM {

    private int stackLength = 0;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaStackOOM jso = new JavaStackOOM();

        try {
            jso.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + jso.stackLength);
            throw e;
        }
    }
}
