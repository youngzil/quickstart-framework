package org.quickstart.javase.jdk.collection.list;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/24 11:40
 */
public class CopyOnWriteArrayListTest {

    @Test
    public void testAdd() {

        CopyOnWriteArrayList aowList = new CopyOnWriteArrayList();
        aowList.add("dd");

    }
}
