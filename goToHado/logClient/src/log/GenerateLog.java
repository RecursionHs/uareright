package log;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Date;

public class GenerateLog {
    public static void main(String[] args) throws Exception{
        Logger log = LogManager.getLogger("testLog");
        int i = 0 ;
        while (true){
            log.info(new Date().toString() + "------------------------");
            i++;
            Thread.sleep(500);
            if(i > 1000000){
                break;
            }
        }

    }

}
