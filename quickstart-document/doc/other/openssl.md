1、openssl
openssl可以实现：秘钥证书管理、对称加密和非对称加密。

2、https连接流程
CA 机构 + 数字证书
数字摘要 + 数字签名
证书的作用：保证Client获取到的公钥一定是Server端签发的公钥，不是什么其他人签发的
HTTPS流程：
第一阶段：非对称加解密通信：协商算法和公钥【第1-5】，数字签名方式下发数字证书
第二阶段：协商对称加解密：密钥和算法【第1-5】，数字签名+信息加密，传输信息
  第一阶段之后Client通过证书获取到Server端的公钥之后，就可以跟服务端进行非对称加解密通信来协商对称加解密的密钥，然后就可以使用对称加解密通信了
  
  
  

---------------------------------------------------------------------------------------------------------------------

HTTPS（HTTP+ TLS/SSL）
对称加密和非对称加密

CA 机构，又称为证书认证中心 (Certificate Authority) 中心
数字证书（Digital Certificate）：签发者CA、证书用途、待访问网站的公钥、待访问网站的加密算法、待访问网站的HASH算法、证书的到期时间等

数字摘要：先把内容做一次HASH，得到一个固定长度（比如128位的HASH）
数字签名（Digital Signature）：数字摘要用CA的私钥加密，就得到了数字签名


证书的作用：保证Client获取到的公钥一定是Server端签发的公钥，不是什么其他人签发的

validata crt` 我理解就是使用 CA 的公钥验证服务器端发送的证书的有效性，如颁发机构是否有效、证书中的域名和访问的域名是否一致、证书是否过期、是否被吊销等。如果证书有问题，游览器会给出警告。如果证书有效，客户端会产生一个随机字符，并使用从证书中获得的服务器端公钥加密后传输给服务器，这个信息只有服务器端的私钥才能解密，服务器端解密获得了随机字符后，便使用该随机字符对发送出去的内容进行对称加密，服务器端就可以使用相同的随机字符进行解密，这样就实现了加密通讯。

证书最重要的就是信任。HTTPS安全保证的基础就是根证书。

证书的存在的根本原因是在于认证而不是加密。所以为了加密而使用不靠谱的证书也是不安全的。HTTPS的基石就是对CA的信任。


HTTPS流程：
第一阶段：非对称加解密通信：协商算法和公钥【第1-5】，数字签名方式下发数字证书
第二阶段：协商对称加解密：密钥和算法【第1-5】，数字签名+信息加密，传输信息
  第一阶段之后Client通过证书获取到Server端的公钥之后，就可以跟服务端进行非对称加解密通信来协商对称加解密的密钥，然后就可以使用对称加解密通信了

使用对称加解密主要是提高加解密的效率
1、如下使用算法生成，不进行通信
2、通过非对称加密通信传输密钥（对称加解密的密钥）
3、通过传输随机数使用算法在Client和Server分别生成相同的密钥，然后使用密钥加密传输


1、浏览器内置CA的公钥和HASH算法，保证真实性
2、CA可以把数字证书HASH后，然后再用CA的私钥加密，就得到了数字签名，然后把数字签名和数字证书发给待访问网站
3、Client访问Server的时候，返回以上数字证书
4、浏览器接到某宝的数字证书，从第二步得到的CA公钥值，可以解密数字证书末尾的数字签名（CA私钥加密，可以用CA公钥解密，此为非对称加密），得到原始的HASHs
5、然后自己也按照证书的HASH算法，自己也计算一个HASHc，如果HASHc== HASHs，则认证通过，否则认证失败。

6、双方会运行 Diffie Hellman 算法，简称 DH算法。通俗地说：双方会协商一个master-key，这个master-key 不会在网络上传输、交换，它们独立计算出来的，其值是相同的，只有它们自己双方知道，任何第三方不会知道，俗称的天不知，地不知，你知，我知。
然后以master-key推导出 session-key，用于双方SSL数据流的加密/解密，采用对称加密，保证数据不被偷窥，加密算法一般用AES。
以master-key推导出 hash-key，用于数据完整性检查（Integrity Check Verification）的加密密钥，HASH算法一般有：MD5、SHA，通俗滴说，保证数据不被篡改。
7、然后就可以正常发送订单了，用HASH key 生成一个MAC（ Message Authentication Code），附在HTTP报文的后面，然后用session key 加密所有数据（HTTP + MAC），然后发送出去
8、服务器先用session key 解密数据，得到HTTP + MAC，然后自己用相同的算法计算自己的MAC，如果两个MAC相等，则数据没有被篡改。







https://www.openssl.org/
https://github.com/openssl/openssl

HTTPS是一种协议，等于HTTP+TLS（由于历史原因，SSL3.0之后就被TLS1.0替代了）。

openssl是一套开源工具集，主要有两个特性：
1、实现了ssl2,ssl3，TLSv1，TLSv1.1，TLSv1.2协议。
2、实现目前常用的加密算法。
没有一个非常精准的方法来判断HTTPS是否使用openssl，但是根据网站返回的server类型，可以大致估计是否使用了openssl，比如如果使用apache或者nginx，那么肯定是使用了openssl。保守估计至少70%以上的网站是使用openssl的。而windows系列的服务器包括IIS，windows server等都是使用schannel，没有使用openssl，不会受heartbleed影响。

OpenSSL 是一个开源项目，其组成主要包括一下三个组件：
    openssl：多用途的命令行工具
    libcrypto：加密算法库
    libssl：加密模块应用库，实现了ssl及tls

openssl可以实现：秘钥证书管理、对称加密和非对称加密。





OpenSSL是一个开放源代码的软件库包，应用程序可以使用这个包来进行安全通信，避免窃听，同时确认另一端连线者的身份。这个包广泛被应用在互联网的网页服务器上。

其主要库是以C语言所写成，实现了基本的加密功能，实现了SSL与TLS协议。OpenSSL可以运行在OpenVMS、 Microsoft Windows以及绝大多数类Unix操作系统上（包括Solaris，Linux，Mac OS X与各种版本的开放源代码BSD操作系统）。它也提供了一个移植版本，可以在IBM i（OS/400）上运作。




什么是openssl
https://blog.csdn.net/m0_38080126/article/details/77609226
https://www.jianshu.com/p/3665fbfc2243
http://seanlook.com/2015/01/15/openssl-certificate-encryption/
https://blog.51cto.com/fengliang/1855917
https://www.ibm.com/developerworks/cn/linux/l-openssl.html
https://segmentfault.com/a/1190000014963014


证书通信讲解
https://www.zhihu.com/question/52493697
https://www.jianshu.com/p/94d3e512953d


什么是数字证书
https://www.jianshu.com/p/42bf7c4d6ab8



