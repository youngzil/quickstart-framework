package org.quickstart.javase.jdk.collection.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/23 22:50
 */
public class FailFastExample {

    public static void main(String[] args) {
        Map<String, String> premiumPhone = new HashMap<String, String>();
        premiumPhone.put("Apple", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "S5");

        Iterator iterator = premiumPhone.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(premiumPhone.get(iterator.next()));
            premiumPhone.put("Sony", "Xperia Z");
        }

    }

}
