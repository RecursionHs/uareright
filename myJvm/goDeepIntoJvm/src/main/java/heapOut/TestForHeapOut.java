package heapOut;

import java.util.ArrayList;
import java.util.List;

public class TestForHeapOut {

    static class OOMOBject{

    }

    public static void main(String[] args) {
        List<OOMOBject> list = new ArrayList<OOMOBject>();

        while(true){
            list.add(new OOMOBject());
        }
    }
}
