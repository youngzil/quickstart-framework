package org.quickstart.crypto;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.quickstart.crypto.utils.K;
import org.quickstart.crypto.utils.MD5Util;
import org.quickstart.crypto.utils.RSAUtils;
import org.quickstart.crypto.utils.SecurityUtils;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/3 19:21
 */
public class ToolsJFrame extends JFrame {
  private ButtonGroup buttonGroup2;
  private JButton jButton2;
  private JComboBox jComboBox1;
  private JComboBox jComboBox2;

  public ToolsJFrame() {
    initComponents();
  }

  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JRadioButton jRadioButton1;
  private JRadioButton jRadioButton2;
  private JScrollPane jScrollPane1;
  private JTextArea jTextArea1;
  private JTextField jTextField1;
  private JTextField jTextField2;

  private void initComponents() {
    this.buttonGroup2 = new ButtonGroup();
    this.jPanel1 = new JPanel();
    this.jLabel6 = new JLabel();
    this.jPanel2 = new JPanel();
    this.jRadioButton1 = new JRadioButton();
    this.jRadioButton2 = new JRadioButton();
    this.jLabel1 = new JLabel();
    this.jComboBox1 = new JComboBox();
    this.jLabel2 = new JLabel();
    this.jTextField1 = new JTextField();
    this.jButton2 = new JButton();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    this.jLabel3 = new JLabel();
    this.jTextField2 = new JTextField();
    this.jComboBox2 = new JComboBox();
    this.jLabel4 = new JLabel();
    this.jLabel5 = new JLabel();

    setDefaultCloseOperation(3);

    this.jLabel6.setFont(new Font("华文行楷", 1, 18));
    this.jLabel6.setText("加解密工具");

    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup().addGap(138, 138, 138).addComponent(this.jLabel6).addContainerGap(-1, 32767)));

    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel6, -1, 29, 32767).addContainerGap()));

    this.buttonGroup2.add(this.jRadioButton1);
    this.jRadioButton1.setSelected(true);
    this.jRadioButton1.setText("加密");
    this.jRadioButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ToolsJFrame.this.jRadioButton1ActionPerformed(evt);
      }
    });

    this.buttonGroup2.add(this.jRadioButton2);
    this.jRadioButton2.setText("解密");
    this.jRadioButton2.setCursor(new Cursor(0));
    this.jRadioButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ToolsJFrame.this.jRadioButton2ActionPerformed(evt);
      }
    });

    this.jLabel1.setText("加密算法");

    this.jComboBox1.setModel(new DefaultComboBoxModel(new String[] {"AES", "RSA", "MD5", "SHA", "REDIS专用"}));
    this.jComboBox1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ToolsJFrame.this.jComboBox1ActionPerformed(evt);
      }
    });

    this.jLabel2.setText("加密秘钥");

    this.jTextField1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        ToolsJFrame.this.jTextField1MouseClicked(evt);
      }
    });

    this.jButton2.setText("加密");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        ToolsJFrame.this.jButton2ActionPerformed(evt);
      }
    });

    this.jScrollPane1.setHorizontalScrollBar(null);

    this.jTextArea1.setColumns(20);
    this.jTextArea1.setRows(5);
    this.jScrollPane1.setViewportView(this.jTextArea1);

    this.jLabel3.setText("待加密串");

    this.jTextField2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        ToolsJFrame.this.jTextField2MouseClicked(evt);
      }
    });

    this.jComboBox2.setModel(new DefaultComboBoxModel(new String[] {"公钥加密", "私钥加密"}));
    this.jComboBox2.setEnabled(false);

    this.jLabel4.setText("加密类型");

    this.jLabel5.setForeground(new Color(255, 0, 51));
    this.jLabel5.setText("(RSA加解密时可选)");

    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout
        .setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jRadioButton1).addGap(18, 18, 18).addComponent(this.jRadioButton2))
                .addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(this.jComboBox1, -2, -1, -2).addGap(38, 38, 38).addComponent(this.jLabel4)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jComboBox2, -2, -1, -2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel5))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jLabel3, -1, -1, 32767)
                        .addComponent(this.jLabel2, -1, -1, 32767))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jTextField1)
                        .addComponent(this.jTextField2, -1, 330, 32767))))
                .addGap(0, 0, 32767))
            .addGroup(jPanel2Layout.createSequentialGroup().addGap(138, 138, 138).addComponent(this.jButton2).addContainerGap(-1, 32767)));

    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup()
        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jRadioButton1).addComponent(this.jRadioButton2))
        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel2Layout
            .createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1, -2, 25, -2).addComponent(this.jComboBox1, -2, -1, -2)
            .addComponent(this.jComboBox2, -2, -1, -2).addComponent(this.jLabel4).addComponent(this.jLabel5))
        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextField1, -2, -1, -2))
        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jTextField2, -2, -1, -2))
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, 32767).addComponent(this.jButton2)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 94, -2).addContainerGap()));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767)
        .addComponent(this.jPanel2, -1, -1, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addGap(0, 0, 32767).addComponent(this.jPanel2, -2, -1, -2)));

    pack();
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    String encrypt_algorithm = this.jComboBox1.getSelectedItem().toString();

    String encrypt_key = this.jTextField1.getText();
    if ("AES".equals(encrypt_algorithm) || "RSA".equals(encrypt_algorithm)) {
      if (encrypt_key == null || encrypt_key.trim().length() == 0) {
        this.jTextField1.setText("请输入密钥");
        return;
      }
      if ("请输入密钥".equals(encrypt_key)) {
        return;
      }
    }

    boolean isEncrypt = false;
    boolean isDecrypt = false;
    if (this.jRadioButton1.isSelected()) {
      isEncrypt = true;
    } else if (this.jRadioButton2.isSelected()) {
      isDecrypt = true;
    }

    String encrypt_org_str = this.jTextField2.getText();
    if (encrypt_org_str == null || encrypt_org_str.trim().length() == 0) {
      if (isEncrypt) {
        this.jTextField2.setText("请输入待加密串");
      } else if (isDecrypt) {
        this.jTextField2.setText("请输入待解密串");
      }
      return;
    }
    if ("请输入待加密串".equals(encrypt_org_str) || "请输入待解密串".equals(encrypt_org_str)) {
      return;
    }

    try {
      String encrypt_value = "";
      if ("AES".equals(encrypt_algorithm)) {
        if (isEncrypt) {
          encrypt_value = SecurityUtils.encodeAES256HexUpper(encrypt_org_str, SecurityUtils.decodeHexUpper(encrypt_key));
        } else if (isDecrypt) {
          encrypt_value = SecurityUtils.decodeAES256HexUpper(encrypt_org_str, SecurityUtils.decodeHexUpper(encrypt_key));
        }
      } else if ("RSA".equals(encrypt_algorithm)) {
        String encrypt_type = this.jComboBox2.getSelectedItem().toString();
        if (isEncrypt) {
          if ("私钥加密".equals(encrypt_type)) {
            encrypt_value = RSAUtils.encryptByPrivateKey(encrypt_org_str, encrypt_key);
          } else if ("公钥加密".equals(encrypt_type)) {
            encrypt_value = RSAUtils.encryptByPublicKey(encrypt_org_str, encrypt_key);
          }
        } else if (isDecrypt) {
          if ("公钥解密".equals(encrypt_type)) {
            encrypt_value = RSAUtils.decryptByPublicKey(encrypt_org_str, encrypt_key);
          } else if ("私钥解密".equals(encrypt_type)) {
            encrypt_value = RSAUtils.decryptByPrivateKey(encrypt_org_str, encrypt_key);
          }
        }
      } else if ("MD5".equals(encrypt_algorithm)) {
        String encrypt_type = this.jComboBox2.getSelectedItem().toString();
        if ("一般".equals(encrypt_type)) {
          encrypt_value = MD5Util.MD5(encrypt_org_str);
        } else if ("签名".equals(encrypt_type)) {
          String[] params = encrypt_org_str.split("&");
          Map<String, String> map = new HashMap<String, String>();
          for (String s : params) {
            String[] arr = s.split("=");
            if (arr.length == 2) {
              map.put(arr[0], arr[1].trim());
            }
          }
          String[] keys = (String[]) map.keySet().toArray(new String[map.size()]);
          Arrays.sort(keys);

          StringBuilder buf = new StringBuilder(200);
          for (String key : keys) {
            if (!"sign".equalsIgnoreCase(key)) {
              buf.append((String) map.get(key));
            }
          }

          if (buf.toString().length() > 0) {
            encrypt_value = MD5Util.MD5(buf.toString());
          }
        }
      } else if ("SHA".equals(encrypt_algorithm)) {
        String[] params = encrypt_org_str.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String s : params) {
          String[] arr = s.split("=");
          if (arr.length == 2) {
            map.put(arr[0], arr[1].trim());
          }
        }
        String[] keys = (String[]) map.keySet().toArray(new String[map.size()]);
        Arrays.sort(keys);

        StringBuilder buf = new StringBuilder(200);
        buf.append(encrypt_key);
        for (String key : keys) {
          if (!"sign".equalsIgnoreCase(key)) {
            buf.append(key).append((String) map.get(key));
          }
        }
        buf.append(encrypt_key);
        System.out.println("encrypt_str:" + buf.toString());

        if (buf.toString().length() > 0) {
          encrypt_value = SecurityUtils.encodeHmacSHA256HexUpper(buf.toString(), SecurityUtils.decodeHexUpper(encrypt_key));
        }
      } else if ("REDIS专用".equals(encrypt_algorithm)) {
        if (isEncrypt) {
          encrypt_value = K.j(encrypt_org_str);
        } else if (isDecrypt) {
          encrypt_value = K.k(encrypt_org_str);
        }
      }
      this.jTextArea1.setText(encrypt_value);
    } catch (Exception e) {
      this.jTextArea1.setText(e.getMessage());
    }
  }

  private void jRadioButton1ActionPerformed(ActionEvent evt) {
    this.jLabel1.setText("加密算法");
    this.jLabel2.setText("加密秘钥");
    this.jLabel3.setText("待加密串");
    this.jLabel4.setText("加密类型");
    this.jButton2.setText("加密");

    if (this.jComboBox1.getItemCount() == 3) {
      this.jComboBox1.addItem("MD5");
      this.jComboBox1.addItem("SHA");
    }

    this.jComboBox2.removeAllItems();

    String encrypt_algorithm = this.jComboBox1.getSelectedItem().toString();
    if ("AES".equals(encrypt_algorithm) || "SHA".equals(encrypt_algorithm)) {
      this.jTextField1.setEnabled(true);
      this.jComboBox2.setEnabled(false);
      this.jComboBox2.addItem("私钥加密");
      this.jComboBox2.addItem("公钥加密");
    } else if ("RSA".equals(encrypt_algorithm)) {
      this.jTextField1.setEnabled(true);
      this.jComboBox2.setEnabled(true);
      this.jComboBox2.addItem("私钥加密");
      this.jComboBox2.addItem("公钥加密");
    } else if ("MD5".equals(encrypt_algorithm)) {
      this.jTextField1.setEnabled(false);
      this.jComboBox2.setEnabled(true);
      this.jComboBox2.addItem("签名");
      this.jComboBox2.addItem("一般");
    } else if ("REDIS专用".equals(encrypt_algorithm)) {
      this.jTextField1.setEnabled(false);
      this.jComboBox2.setEnabled(false);
    }
  }

  private void jRadioButton2ActionPerformed(ActionEvent evt) {
    this.jLabel1.setText("解密算法");
    this.jLabel2.setText("解密密钥");
    this.jLabel3.setText("待解密串");
    this.jLabel4.setText("解密类型");
    this.jButton2.setText("解密");

    if (this.jComboBox1.getItemCount() == 5) {
      this.jComboBox1.removeItem("MD5");
      this.jComboBox1.removeItem("SHA");
      this.jComboBox1.setSelectedIndex(0);
    }

    String encrypt_algorithm = this.jComboBox1.getSelectedItem().toString();
    if ("AES".equals(encrypt_algorithm)) {
      this.jComboBox2.setEnabled(false);
      this.jTextField1.setEnabled(true);
    } else if ("RSA".equals(encrypt_algorithm)) {
      this.jComboBox2.setEnabled(true);
      this.jTextField1.setEnabled(true);
    } else if ("REDIS专用".equals(encrypt_algorithm)) {
      this.jTextField1.setEnabled(false);
      this.jComboBox2.setEnabled(false);
    }
    this.jComboBox2.removeAllItems();
    this.jComboBox2.addItem("公钥解密");
    this.jComboBox2.addItem("私钥解密");
  }

  private void jComboBox1ActionPerformed(ActionEvent evt) {
    boolean isEncrypt = false;
    boolean isDecrypt = false;
    if (this.jRadioButton1.isSelected()) {
      isEncrypt = true;
    } else if (this.jRadioButton2.isSelected()) {
      isDecrypt = true;
    }

    String encrypt_algorithm = this.jComboBox1.getSelectedItem().toString();
    if ("AES".equals(encrypt_algorithm) || "SHA".equals(encrypt_algorithm)) {
      this.jComboBox2.setEnabled(false);
      this.jTextField1.setEnabled(true);
    }

    if (isEncrypt) {
      this.jLabel4.setText("加密类型");
      if ("RSA".equals(encrypt_algorithm)) {
        this.jTextField1.setEnabled(true);
        this.jComboBox2.setEnabled(true);
        this.jComboBox2.removeAllItems();
        this.jComboBox2.addItem("私钥加密");
        this.jComboBox2.addItem("公钥加密");
      } else if ("MD5".equals(encrypt_algorithm)) {
        this.jTextField1.setEnabled(false);
        this.jComboBox2.setEnabled(true);
        this.jComboBox2.removeAllItems();
        this.jComboBox2.addItem("签名");
        this.jComboBox2.addItem("一般");
      } else if ("REDIS专用".equals(encrypt_algorithm)) {
        this.jTextField1.setEnabled(false);
        this.jComboBox2.setEnabled(false);
      }
    } else if (isDecrypt) {
      this.jLabel4.setText("解密类型");

      if ("RSA".equals(encrypt_algorithm)) {
        this.jComboBox2.setEnabled(true);
        this.jComboBox2.removeAllItems();
        this.jComboBox2.addItem("公钥解密");
        this.jComboBox2.addItem("私钥解密");
      } else if ("REDIS专用".equals(encrypt_algorithm)) {
        this.jTextField1.setEnabled(false);
        this.jComboBox2.setEnabled(false);
      }
    }
  }

  private void jTextField1MouseClicked(MouseEvent evt) {
    if ("请输入密钥".equals(this.jTextField1.getText())) {
      this.jTextField1.setText("");
    }
  }

  private void jTextField2MouseClicked(MouseEvent evt) {
    if ("请输入待加密串".equals(this.jTextField2.getText()) || "请输入待解密串".equals(this.jTextField2.getText())) {
      this.jTextField2.setText("");
    }
  }

  public static void main(String[] args) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ToolsJFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(ToolsJFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(ToolsJFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(ToolsJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

    ToolsJFrame frame = new ToolsJFrame();
    frame.setVisible(true);
    frame.setResizable(false);
  }
}
