package org.quickstart.javase.jdk8;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/16 16:32
 */
public class HashMapInfiniteLoopTest {

    Map<String, String> map = new HashMap<>();

    @Test
    public void hashMapTest() throws IOException {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 500; j++) {
                        map.put(Thread.currentThread().getName() + "-" + j, j + "");
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
            //            map.forEach((x,y) -> System.out.println(x));
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.in.read();
    }

}
