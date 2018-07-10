package com.quickstart.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ParseText {

    // 逐行写入文本
    public static final void writeText(String filePath, String content) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath, true);
            String c = content + "\r\n";
            fw.write(c);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("写入失败");
            System.exit(-1);
        }
    }

    // 逐行读取文本
    public static final void readText(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            System.out.println(line);
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/ziliang.yang/桌面/ww.txt")));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("d:/ziliang.yang/桌面/ww2.txt")));

        Set<String> arr1 = new HashSet<String>();
        Set<String> arr2 = new HashSet<String>();

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (!"".equals(line)) {
                arr1.add(line);
            }
        }

        for (String line = br2.readLine(); line != null; line = br2.readLine()) {
            if (!"".equals(line)) {
                arr2.add(line);
            }
        }
        br.close();
        br2.close();

        /*for(int i=0;i<arr1.size();i++){
        	for(int j=0;j<arr2.size();j++){
        		if(-1 != arr1.get(i).indexOf(arr2.get(j))){
        			writeText("d:/ziliang.yang/桌面/ww3.txt", arr1.get(i) + "||" + arr2.get(j));
        		}
        	}
        }*/

        Iterator it1 = arr1.iterator();

        String url1 = "";
        String url2 = "";

        System.out.println("arr1.size()==" + arr1.size());
        System.out.println("arr2.size()==" + arr2.size());
        boolean flag = true;
        while (it1.hasNext()) {
            flag = true;
            url1 = (String) it1.next();
            Iterator it2 = arr2.iterator();
            while (it2.hasNext()) {
                url2 = (String) it2.next();

                if (-1 != url1.indexOf(url2)) {
                    writeText("d:/ziliang.yang/桌面/ww3.txt", url1 + "||" + url2);
                    flag = false;;
                    break;
                }
            }
            if (flag) {
                writeText("d:/ziliang.yang/桌面/ww4.txt", url1);
            }

        }

    }

}
