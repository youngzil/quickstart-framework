package com.quickstart.test.memcached;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash {
    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    static SortedMap<Long, String> circle = new TreeMap<Long, String>();

    public static void main(String args[]) {
        List<String> list = new ArrayList<String>();
        String node1 = "node1";
        String node2 = "node2";
        String node3 = "node3";
        list.add(node1);
        list.add(node2);
        list.add(node3);
        HashFunction hf = new HashFunction();
        ConsistentHash ch = new ConsistentHash(hf, 1, list);
        System.out.println("位置1：" + ch.get("abcdefg"));
        System.out.println("位置2：" + ch.get("loadfsf"));
        System.out.println("位置3：" + ch.get("ilfcdef"));
        System.out.println("位置4：" + ch.get("mbcdefg"));
        System.out.println("位置5：" + ch.get("tbcdefg"));

        ch.add("node4");
        ch.add("node5");
        ch.add("node6");
        System.out.println("===========================");
        System.out.println("位置1：" + ch.get("abcdefg"));
        System.out.println("位置2：" + ch.get("loadfsf"));
        System.out.println("位置3：" + ch.get("ilfcdef"));
        System.out.println("位置4：" + ch.get("mbcdefg"));
        System.out.println("位置5：" + ch.get("tbcdefg"));
    }

    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<String> nodes) {

        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (String node : nodes) {
            add(node);
        }
    }

    public void add(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + ":" + i), node);
        }
    }

    public void remove(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + ":" + i));
        }
    }

    public String get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        long hash = hashFunction.hash(key);
        SortedMap<Long, String> tailMap = circle.tailMap(hash);
        hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        return circle.get(hash);
    }
}


class HashFunction {
    MessageDigest md5 = null;

    public Long hash(String key) {

        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
        }

        md5.reset();
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        long res = ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
        return res;
    }
}
