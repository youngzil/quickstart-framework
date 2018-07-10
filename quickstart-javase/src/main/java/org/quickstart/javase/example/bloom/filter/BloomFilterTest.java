/**
 * 项目名称：quickstart-javase 
 * 文件名：BloomFilterTest.java
 * 版本信息：
 * 日期：2018年4月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.bloom.filter;

import java.util.BitSet;

/**
 * BloomFilterTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月10日 上午9:52:11
 * @since 1.0
 * 
 *        存在的问题
 * 
 *        DEFAULT_LEN长度设置为多少合适呢？
 * 
 *        我发现result和DEFAULT_LEN有关，不应该啊，没发现原因
 * 
 */
public class BloomFilterTest {
    // 30位，表示2^2^30种字符
    static int DEFAULT_LEN = 1 << 30;
    // 要用质数
    static int[] seeds = {3, 5, 7, 11, 17, 31};
    static BitSet bitset = new BitSet(DEFAULT_LEN);
    static MyHash[] myselfHash = new MyHash[seeds.length];

    public static void main(String[] args) {
        String str = "791909235@qq.com";

        // 生成一次就够了
        for (int i = 0; i < seeds.length; i++) {
            myselfHash[i] = new MyHash(DEFAULT_LEN, seeds[i]);
        }
        bitset.clear();
        for (int i = 0; i < myselfHash.length; i++) {
            bitset.set(myselfHash[i].myHash(str), true);
        }
        boolean flag = containsStr(str);
        // System.out.println("========================");
        System.out.println(flag);

    }

    private static boolean containsStr(String str) {
        // TODO Auto-generated method stub
        if (null == str)
            return false;
        for (int i = 0; i < seeds.length; i++) {
            if (bitset.get(myselfHash[i].myHash(str)) == false)
                return false;
        }
        return true;
    }
}


class MyHash {
    int len;
    int seed;

    public MyHash(int len, int seed) {
        super();
        this.len = len;
        this.seed = seed;
    }

    public int myHash(String str) {
        int len = str.length();
        int result = 0;
        // 这的len就是str的len，不是成员变量的len
        for (int i = 0; i < len; i++) {
            // System.out.println(seed+"oooooooooooo");
            result = result * seed + str.charAt(i);
            // System.out.println(result);
            // 长度就是1<<24，如果大于这个数 感觉结果不准确
            // <0就是大于了0x7ffffff
            if (result > (1 << 30) || result < 0) {
                // System.out.println("-----"+(1<<30));
                System.out.println(result + "myHash数据越界！！！");
                break;
            }
        }
        return (len - 1) & result;
    }
}
