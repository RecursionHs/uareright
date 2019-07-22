package threadCommunication;

public class doSome {

    public static void main(String[] args) {
        MyList list = new MyList();

        ThreadA tra = new ThreadA(list);
        tra.setName("A");
        tra.start();

        ThreadB trb = new ThreadB(list);
        trb.setName("B");
        trb.start();

    }
}
