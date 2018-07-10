/**
 * 项目名称：quickstart-jvm-serializer 
 * 文件名：JRedisSerializationUtils.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.jvm.serializer.fst;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.nustaq.serialization.FSTConfiguration;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * JRedisSerializationUtils
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 下午8:56:43
 * @since 1.0
 */
public class JRedisSerializationUtils {

    public JRedisSerializationUtils() {}

    static FSTConfiguration configuration = FSTConfiguration
            // .createDefaultConfiguration();
            .createStructConfiguration();

    public static byte[] serialize(Object obj) {
        return configuration.asByteArray(obj);
    }

    public static Object unserialize(byte[] sec) {
        return configuration.asObject(sec);
    }

    public static byte[] kryoSerizlize(Object obj) {
        Kryo kryo = new Kryo();
        byte[] buffer = new byte[2048];
        try (Output output = new Output(buffer);) {

            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {
        }
        return buffer;
    }

    static Kryo kryo = new Kryo();

    public static Object kryoUnSerizlize(byte[] src) {
        try (Input input = new Input(src);) {
            return kryo.readClassAndObject(input);
        } catch (Exception e) {
        }
        return kryo;
    }

    // jdk原生序列换方案
    public static byte[] jdkserialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object jdkdeserialize(byte[] bits) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bits); ObjectInputStream ois = new ObjectInputStream(bais);

        ) {
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
