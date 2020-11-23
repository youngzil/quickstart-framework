package org.quickstart.javase.jdk.collection.iterator;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/23 22:49
 */
public class FailSafeExample {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> premiumPhone = new ConcurrentHashMap<String, String>();
        premiumPhone.put("Apple", "iPhone");
        premiumPhone.put("HTC", "HTC one");
        premiumPhone.put("Samsung", "S5");

        Iterator iterator = premiumPhone.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(premiumPhone.get(iterator.next()));
            premiumPhone.put("Sony", "Xperia Z");
        }

        System.out.println("premiumPhone=" + premiumPhone);

    }

}

