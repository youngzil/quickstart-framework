package com.quickstart.test;

import java.util.List;

public class Generic {
    // 方法一
    public static <T extends A> void get(List<T,A> list)  
	{  
	    list.get(0);  
	}

    // 方法二
    public static <T extends A> void set(List<T extends A> list, A a)  
	{  
	    list.add(a);  
	}  
	  
	//方法三  
	public static <T super B>

    void get(List<T super B> list)  
	{  
	    list.get(0);  
	}  
	  
	//方法四  
	public static <T super B>

    void set(List<T super B> list, B b)  
	{  
	    list.add(b);  
	}  

}
