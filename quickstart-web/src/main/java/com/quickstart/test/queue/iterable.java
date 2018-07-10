package com.quickstart.test.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iterable {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new LinkedList<String>();

        Set<String> hashSet = new HashSet<String>();
        Set<String> treeSet = new TreeSet<String>();

        Map<String, String> hashMap = new HashMap<String, String>();
        Map<String, String> treeMap = new TreeMap<String, String>();
        Map<String, String> hashtable = new Hashtable<String, String>();
        Hashtable hashtable2 = new Hashtable();

        // hashtable.put(null, "null");
        // hashMap.put(null, "null");

        // hashtable2.put(null, "jjj");

        Collection<?> c = new ArrayList<String>();
        // c.add("ssss"); // 编译时错误

        LinkedList<String> c1 = new LinkedList<String>();

        String ss = "hehe";
        Integer i = 0;
        int i2 = 1;
        System.out.println(ss instanceof String);
        System.out.println(i instanceof Integer);

        int[] aa = new int[10];

        // List<String>[] lsa = new List<String>[10]; // not really allowed
        List<?>[] lsa = new List<?>[10]; // ok, array of unbounded wildcard type

        Object o = lsa;

        Object[] oa = (Object[]) o;

        List<Integer> li = new ArrayList<Integer>();

        li.add(new Integer(3));

        oa[1] = li; // unsound, but passes run time store check

        // String s = lsa[1].get(0); // run-time error - ClassCastException

        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
        // 替换第一个符合正则的数据
        System.out.println(matcher.replaceAll("Java"));

    }

}
