package com.quickstart.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    /*public static void main(String[] args) {
    	
    	String ss = "1234567";
    	System.out.println(ss.substring(2,3));
    	Map<String,String> ids = new HashMap<String, String>();
    	ids.put("239", "239");
    	
    	for(Map.Entry<String,String> id:ids.entrySet()){
    		System.out.println(id.getValue());
    	}
    	
    	
    	System.out.println("-------------------------------------------------------");
    	if(ids!=null&&!ids.isEmpty()){//说明有错误信息
    		System.out.println("hhhhhhhhhhhhhhhhh");
    	}
    	
    	
    	if(ids==null||ids.isEmpty()){
    		System.out.println("fffffffffffffffffffffffff");
    	}
    }*/

    /*public static void main(String[] args) {
    	String testStr = "12315<Test>show me</text>";  
    	Pattern p = Pattern.compile("<Text>(.*)</Text>");  
    	Matcher m = p.matcher(testStr);  
    	while(m.find()){
    		String ss = m.group(1);
    	System.out.println(ss);  
    	}
    }*/

    public static void main(String[] args) {
        /*Pattern p = Pattern.compile("<row><column>(.*)</column></row>",Pattern.DOTALL);
        
        Matcher matcher = p.matcher("<row><column>Headertext</column></row>");
        
        if(matcher.matches()){
         System.out.println(matcher.group(1));
        }*/
        /*String a= "ab";
        String b= "abc";
        String c= a + "c";
        String d= "ab" + "c";
        
        System.out.println(b==c);
        System.out.println(d==c);
        System.out.println(b==d);
        
        System.out.println(b);
        System.out.println(b);
        System.out.println(c);*/

        /*ArrayList<String> arr = new ArrayList<String>();
        arr.add("111");
        arr.add("222");
        arr.add("333");
        arr.add("444");
        arr.add("555");
        
        for(int i=0;i<arr.size();i++){
        	if(i == 2){
        		arr.remove(i);
        	}
        	if(i == 2){
        		System.out.println(arr.get(i));
        	}
        }
        
        for(int i=0;i<arr.size();i++){
        	System.out.println(arr.get(i));
        }
        
        System.out.println(arr.size());*/

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        /*//第一种foreach循环报错
        for (Integer in : list) {
        	if (in == 3) {//in==2时，正常执行
        	list.remove(in);
        	}
        	}*/
        // 第二种iterator循环报错
        for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
            int i = iter.next();
            if (i == 3) {
                // list.remove(i);
                iter.remove();
            }
        }

        /*Iterator it=list.iterator();  
          
          while(it.hasNext()){  
          
        	it.next();  
        	it.remove();  
        	  
        	  int i = (int) it.next();
        		if (i == 3)
        		{
        //				list.remove(i);
        			it.remove();
        		}
          
        } */
        System.out.println(list.toString());

    }

}
