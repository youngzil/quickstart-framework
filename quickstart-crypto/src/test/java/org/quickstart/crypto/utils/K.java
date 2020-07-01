package org.quickstart.crypto.utils;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: AI(NanJing)</p>
 * @author Yang Hua
 * @version 5.5
 */
public final class K {


  private static byte[] key = {97,105,95,110,106,95,114,100};


  /**
   * 构造
   * @throws Exception
   */
  private K() throws Exception {

  }

  /**
   * 加密
   * @param plain String
   * @throws Exception
   * @return String
   */
  public static String j(String plain) throws Exception {
    RC2 rc2 = new RC2();
    return rc2.encrypt_rc2_array_base64(plain.getBytes(),key);
  }

  /**
   * 解密
   * @param cipher String
   * @throws Exception
   * @return String
   */
  public static String k(String cipher) throws Exception {
    RC2 rc2 = new RC2();
    return rc2.decrypt_rc2_array_base64(cipher.getBytes(),key);
  }

  /**
   * 解密
   * @param cipher String
   * @throws Exception
   * @return String
   */
  public static String k_s(String cipher) throws Exception {
    String rtn = null;
    if (cipher != null && cipher.lastIndexOf("{RC2}") != -1) {
      rtn = k(cipher.substring(5));
    }
    else {
      rtn = cipher;
    }
    return rtn;
  }


  public static void main2(String[] args)  throws Exception {
    String a = K.k_s("{RC2}RcAeFXsjJHfGNA==");
    System.out.println(a);
  }


  public static void main(String[] args)  throws Exception {
//    String a = K.k_s("{RC2}XP&V,");
//    System.out.println(a);
//    String b = K.k(a);
//    System.out.println(b);
//    
//    System.out.println(K.j("adiashdsf1231hfdhf@!@@#$"));
	  System.out.println(K.k("HKH0F5PMAcL04BVz6a9kr3alPyPspnXA"));
  }
}
