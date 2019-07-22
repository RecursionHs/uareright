package threadCommunication;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private List list = new ArrayList();

    public void add(){
        list.add("一个元素");
    }

    public int size(){
        return list.size();
    }
}
