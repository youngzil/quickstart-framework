package org.quickstart.linux.scp.example.basic.ssh;

import org.junit.Assert;

/**
 * @author Chanaka Lakmal
 */
public class SSHCommand {

    private static String userName = "chanaka";
    private static String host = "172.16.48.179";
    // private static String keyFilePath = "/home/chanaka/.ssh/id_rsa";
    private static String keyFilePath = null;
    private static String keyPassword = null;
    private static int timeOut = 60000;

    public static void main(String[] args) {
        try {
            String command = "scp /Users/lengfeng/topics.log appweb@172.16.48.179:/data/program";
            SSHManager instance = new SSHManager(userName, host, 22, keyFilePath, keyPassword, timeOut);
            instance.connect();

            String expResult = "abc.txt\n";
            String result = instance.sendCommand(command);
            instance.close();
            Assert.assertEquals(expResult, result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}