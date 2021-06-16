import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //等待唤醒案例
        ArrayList<String> list = new ArrayList<>();
        // 创建线程对象        
        BaoZiPu bzp = new BaoZiPu(list);
        ChiHuo ch = new ChiHuo(list);
        // 开启线程
        bzp.start();
        ch.start();
    }
}