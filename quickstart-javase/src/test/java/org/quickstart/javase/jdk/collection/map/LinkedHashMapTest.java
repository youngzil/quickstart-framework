package org.quickstart.javase.jdk.collection.map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/24 22:18
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16, 0.75f, true);

        linkedHashMap.put("111", "111");
        linkedHashMap.put("222", "222");
        linkedHashMap.put("333", "333");
        linkedHashMap.put("444", "444");
        loopLinkedHashMap(linkedHashMap);

        linkedHashMap.get("111");
        loopLinkedHashMap(linkedHashMap);

        linkedHashMap.put("222", "2222");
        loopLinkedHashMap(linkedHashMap);

    }

    public static void loopLinkedHashMap(LinkedHashMap linkedHashMap) {
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();
    }
}
