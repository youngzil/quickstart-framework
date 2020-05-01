package org.quickstart.javase.jdk;

import java.io.ObjectStreamClass;
import java.util.Calendar;

public class ClassSerialVersionUIDTest {

    public static void main(String[] args) throws Exception {
        /*Object obj = MsgFTextMessage.class.newInstance();
        Field field = MsgFTextMessage.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        System.out.println(field.getLong(obj));*/

        // create a new object stream class for Integers
        ObjectStreamClass osc = ObjectStreamClass.lookup(Integer.class);
        // get the serial for Integers
        System.out.println("" + osc.getSerialVersionUID());

        // create a new object stream class for Calendar
        ObjectStreamClass osc2 = ObjectStreamClass.lookup(Calendar.class);
        // get the serial for Calendar
        System.out.println("" + osc2.getSerialVersionUID());

    }

}
