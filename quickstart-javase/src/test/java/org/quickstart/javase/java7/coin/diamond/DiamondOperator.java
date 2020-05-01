package org.quickstart.javase.java7.coin.diamond;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondOperator {

    private Map<String, List<Object>> map;

    private void init() {
        // map = new HashMap<>();
        map = new HashMap();
    }

    public static void main(String[] args) {
        DiamondOperator test = new DiamondOperator();

        test.init();
    }
}
