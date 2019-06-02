package org.quickstart.linux.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import org.apache.commons.net.telnet.TelnetClient;

public class WindowsShell {
  private TelnetClient telnet = new TelnetClient("VT220");

  InputStream in;
  PrintStream out;

  String prompt = ">";

  public WindowsShell(String ip, int port, String user, String password) {
    try {
      telnet.connect(ip, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());
      login(user, password);
    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 登录
   *
   * @param user
   * @param password
   */
  public void login(String user, String password) {
    // read()Until("login:");
    readUntil("login:");
    write(user);
    readUntil("password:");
    write(password);
    readUntil(prompt + "");
  }

  /**
   * 读取分析结果
   *
   * @param pattern
   * @return
   */
  public String readUntil(String pattern) {
    try {
      char lastChar = pattern.charAt(pattern.length() - 1);
      StringBuffer sb = new StringBuffer();
      char ch = (char) in.read();

      while (true) {
        sb.append(ch);
        if (ch == lastChar) {
          if (sb.toString().endsWith(pattern)) {
            return sb.toString();
          }
        }
        ch = (char) in.read();
//				System.out.print(ch);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 写操作
   *
   * @param value
   */
  public void write(String value) {
    try {
      out.println(value);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 向目标发送命令字符串
   *
   * @param command
   * @return
   */
  public String sendCommand(String command) {
    try {
      write(command);
      return readUntil(prompt + "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 关闭连接
   */
  public void disconnect() {
    try {
      telnet.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws UnsupportedEncodingException {
    WindowsShell ws = new WindowsShell("127.0.0.1", 23, "Administrator", "123");
//			System.out.println(ws);
    // 执行的命令
    String str = ws.sendCommand("ipconfig");
    str = new String(str.getBytes("ISO-8859-1"),"GBK");
    System.out.println(str);
    ws.disconnect();
  }

  public static void main2(String[] args) {
    try {
      TelnetClient telnetClient = new TelnetClient("vt200");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
      telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
      telnetClient.connect("127.0.0.1",23);  //建立一个连接,默认端口是23
      InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
      PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
      byte[] b = new byte[1024];
      int size;
      StringBuffer sBuffer = new StringBuffer(300);
      while(true) {     //读取Server返回来的数据，直到读到登陆标识，这个时候认为可以输入用户名
        size = inputStream.read(b);
        if(-1 != size) {
          sBuffer.append(new String(b,0,size));
          if(sBuffer.toString().trim().endsWith("login:")) {
            break;
          }
        }
      }
      System.out.println(sBuffer.toString());
      pStream.println("exit"); //写命令
      pStream.flush(); //将命令发送到telnet Server
      if(null != pStream) {
        pStream.close();
      }
      telnetClient.disconnect();
    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}

