package org.quickstart.crypto.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.jupiter.api.Test;

public class DruidTest {

    @Test
    public void testDruidEncrypt() throws Exception {
        String password = "dchjcdhsasbhdsbjsdbjn";
        String encrypt = ConfigTools.encrypt(password);
        System.out.println("encrypt=" + encrypt);
        System.out.println("encrypt size=" + encrypt.length());
        String decrypt = ConfigTools.decrypt(encrypt);
        System.out.println("decrypt=" + decrypt);
    }

}
