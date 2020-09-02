package org.quickstart.crypto.gmssl;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/2 00:09
 * @version v1.0
 */
public class SocketGet {
    public static void main(String[] args) {
        SocketFactory fact = null;
        SSLSocket socket = null;

        String addr = "ebssec.boc.cn";
        int port = 443;
        String uri = "/";

        try {
            if (args.length > 0) {
                addr = args[0];
                port = Integer.parseInt(args[1]);
                uri = args[2];
            }

            System.out.println("\r\naddr=" + addr);
            System.out.println("port=" + port);
            System.out.println("uri=" + uri);

            // 加载国密提供者,首先要注册国密提供者
            Security.insertProviderAt(new cn.gmssl.jce.provider.GMJCE(), 1);
            Security.insertProviderAt(new cn.gmssl.jsse.provider.GMJSSE(), 2);

            fact = createSocketFactory(null, null);
            socket = (SSLSocket)fact.createSocket();
            socket.setTcpNoDelay(true);

            System.out.println("\r\nGM SSL connecting...");
            socket.connect(new InetSocketAddress(addr, port), 5000);
            socket.setTcpNoDelay(true);
            socket.startHandshake();

            System.out.println("Connected!\n");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            OutputStream out = socket.getOutputStream();

            String s = "GET " + uri + " HTTP/1.1\r\n";
            s += "Accept: */*\r\n";
            s += "User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)\r\n";
            s += "Host: " + addr + (port == 443 ? "" : ":" + port) + "\r\n";
            s += "Connection: Close\r\n";
            s += "\r\n";
            out.write(s.getBytes());
            out.flush();

            // 读取HTTP头
            while (true) {
                byte[] lineBuffer = ReadLine.read(in);
                if (lineBuffer == null || lineBuffer.length == 0) {
                    System.out.println();
                    break;
                }
                String line = new String(lineBuffer);
                System.out.println(line);
            }

            // 读取HTTP内容
            {
                byte[] buf = new byte[1024];
                while (true) {
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }
                    System.out.println(new String(buf, 0, len));
                }
            }

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    private static SSLSocketFactory createSocketFactory(KeyStore kepair, char[] pwd) throws Exception {
        X509TrustManager[] trust = {new MyTrustAllManager()};

        KeyManager[] kms = null;
        if (kepair != null) {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(kepair, pwd);
            kms = kmf.getKeyManagers();
        }

        // 使用国密SSL,其中要使用国密SSL来连接
        String protocol = cn.gmssl.jsse.provider.GMJSSE.GMSSLv11;
        String provider = cn.gmssl.jsse.provider.GMJSSE.NAME;
        SSLContext ctx = SSLContext.getInstance(protocol, provider);

        java.security.SecureRandom secureRandom = new java.security.SecureRandom();
        ctx.init(kms, trust, secureRandom);

        SSLSocketFactory factory = ctx.getSocketFactory();
        return factory;
    }
}

class MyTrustAllManager implements X509TrustManager {
    private X509Certificate[] issuers;

    public MyTrustAllManager() {
        this.issuers = new X509Certificate[0];
    }

    public X509Certificate[] getAcceptedIssuers() {
        return issuers;
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }
}

class ReadLine {
    public static final byte[] CRLF = {'\r', '\n'};
    public static final byte CR = '\r';
    public static final byte LF = '\n';

    private static final int LINE_MAX_SIZE = 16384;

    public static byte[] read(DataInputStream in) throws IOException, SocketException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream s = new DataOutputStream(baos);
        boolean previousIsCR = false;

        int len = 0;
        byte b = 0;

        try {
            b = in.readByte();
            len++;
        } catch (EOFException e) {
            return new byte[0];
        }

        while (true) {
            if (b == LF) {
                if (previousIsCR) {
                    s.flush();
                    byte[] rs = baos.toByteArray();
                    s.close();
                    return rs;
                } else {
                    s.flush();
                    byte[] rs = baos.toByteArray();
                    s.close();
                    return rs;
                }
            } else if (b == CR) {
                if (previousIsCR) {
                    s.writeByte(CR);
                }
                previousIsCR = true;
            } else {
                if (previousIsCR) {
                    s.writeByte(CR);
                }
                previousIsCR = false;
                s.write(b);
            }

            if (len > LINE_MAX_SIZE) {
                s.close();
                throw new IOException("Reach line size limit");
            }

            try {
                b = in.readByte();
                len++;
            } catch (EOFException e) {
                s.flush();
                byte[] rs = baos.toByteArray();
                s.close();
                return rs;
            }
        }
    }
}
