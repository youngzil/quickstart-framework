package com.quickstart.test.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);

        for (Integer a : useCases) {
            System.out.println(a);
        }

        Collection<Integer> co = useCases;
        Iterator<Integer> it = co.iterator();
        while (it.hasNext()) {
            Integer element = it.next();

            System.out.println("E:" + element);
        }
    }

}
