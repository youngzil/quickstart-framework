package org.quickstart.crypto.password4j;

import com.password4j.Hash;
import com.password4j.HashingFunction;
import com.password4j.Password;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/2 00:50
 * @version v1.0
 */
public class Password4jTest {

    public static void main(String[] args) {

        // BCrypt parameters are located in a property file
        Hash bcrypt = Password.hash("my password")//
            .addPepper("pepper").withBCrypt();

        String result = bcrypt.getResult();//the hash
        String salt = bcrypt.getSalt();//the salt
        CharSequence pepper = bcrypt.getPepper();//the pepper
        HashingFunction hashingFunction = bcrypt.getHashingFunction(); //the algorithm with its parameters

        System.out.println("result=" + result);
        System.out.println("salt=" + salt);
        System.out.println("pepper=" + pepper);
        System.out.println("hashingFunction=" + hashingFunction);

        // Validating the password with BCrypt
        String hash = "$2b$10$xZKT5fvKiG113ko0PYudEeAx8ZL4n43msuHDCQ64Bh3YCDVmD80Dm";
        String userSubmittedPassword = "my password";

        // BCrypt parameters are located in a property file
        Boolean checked = Password.check(userSubmittedPassword, hash)//
            .addPepper("pepper")//
            .withBCrypt();

        System.out.println("checked=" + checked);

        hash = "$2b$10$77NIEPIsafSVOnl.KjMVR.NpIZ9ixkrVisok.NW4qagkw45Zmt8xm";
        Boolean checked2 = Password.check(userSubmittedPassword, hash)//
            .addPepper("pepper")//
            .withBCrypt();

        System.out.println("checked2=" + checked2);

        hash = result;
        Boolean checked3 = Password.check(userSubmittedPassword, hash)//
            .addPepper("pepper")//
            .withBCrypt();

        System.out.println("checked3=" + checked3);

    }
}
