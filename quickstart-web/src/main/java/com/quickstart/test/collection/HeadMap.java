package com.quickstart.test.collection;

import java.util.SortedMap;
import java.util.TreeMap;

public class HeadMap {

    public static void main(String[] args) {

        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("11", "heheh11");
        map.put("12", "heheh12");
        map.put("13", "heheh13");
        map.put("14", "heheh14");
        map.put("15", "heheh15");
        map.put("16", "heheh16");
        map.put("17", "heheh17");
        map.put("18", "heheh18");
        map.put("19", "heheh19");
        map.put("20", "heheh20");

        map = map.headMap("16");

        System.out.println(map.size());
        System.out.println(map);
        System.out.println(map.toString());
    }

}
