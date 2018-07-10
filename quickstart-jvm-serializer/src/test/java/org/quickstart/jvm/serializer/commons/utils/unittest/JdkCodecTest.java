package org.quickstart.jvm.serializer.commons.utils.unittest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;
import org.quickstart.jvm.serializer.commons.utils.CodecTest;
import org.quickstart.jvm.serializer.model.Father;

/**
 * @author xinyuzhou.zxy
 */
public class JdkCodecTest extends CodecTest {
    protected Object javaEncodeAndDecode(Object msg) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        oos.close();
        baos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        ois.close();
        bais.close();

        return obj;
    }

    @Test
    public void javaCodecTest() throws Throwable {
        Father father = new Father();
        Father fatherCopy = (Father) javaEncodeAndDecode(father);

        Assert.assertEquals(father, fatherCopy);
    }

    @Test
    public void javaCodecSizeTest() throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg1);
        System.out.println(baos.toByteArray().length);
        oos.close();
        baos.close();
    }

}
