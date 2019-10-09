package org.quickstart.crypto.tink;

import java.security.GeneralSecurityException;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/10/8 18:07
 */
public class TinkTest {

  public static void main(String[] args) throws GeneralSecurityException {

    // if you want to use all implementations of all primitives in Tink
    // 基于默认配置进行注册
    // 运行时必须先使用Tink注册基元，以便让Tink知道所需的实现。
    TinkConfig.register();

    // To use only implementations of the AEAD primitive:
    // AeadConfig.register();

    // 测试用的明文字符串
    String plaintext = "技术学徒";
    // 生成密钥
    // 1.加载或生成加密密钥材料（Tink术语表示的KeySet）
    // 1. 生成密钥。
    KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

    // 2.使用密钥材料获取所选的基元的实例
    // 2. 获取原语。
    Aead aead = AeadFactory.getPrimitive(keysetHandle);

    /*
     * 加密
     * 第一个参数是plaintext（明文）
     * 第二个参数是associatedData（相关数据）
     *     可以为null，相当于一个空（零长度）字节数组。
     *     同样，解密时必须提供同样的相关数据。
     */
    // 3.使用基元实例来完成加密任务
    // 3. 使用原语。
    byte[] ciphertext = aead.encrypt(plaintext.getBytes(), null);
    // 解密
    byte[] decrypted = aead.decrypt(ciphertext, null);

    System.out.println(new String(decrypted));

    // Primitive 是什么？
    // Primitive 称之为原语，Tink的所有加密都是通过原语来执行的。上面代码中返回的Aead就是原语中的一种，后面的文章我会介绍更多原语（也就是更多加密类型）。
    // Primitive 的特点？
    // 无状态（stateless），所以线程安全；
    // copy-safe（参数）；
    // 至少128位安全性（RSA除外）；

    // 什么是 associatedData（相关数据）？
    // 应该被认证但未加密的数据，具有相关数据的加密确保了该数据的真实性（即，发送者是谁）和完整性（即，数据未被篡改），而不是其保密性（参见RFC 5116）
    // 举个栗子：你要对手机号加密，那么可以将用户名作为相关数据。
    // 明文和相关数据可以有任意长度（在0..232字节范围内）

  }

}
