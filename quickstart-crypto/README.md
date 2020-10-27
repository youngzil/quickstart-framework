- [Tink加解密库](#Tink加解密库)
- [加密哈希函数库Password4j](#加密哈希函数库Password4j)
- [Wycheproof针对已知攻击测试加密库](#Wycheproof针对已知攻击测试加密库)
- [国密算法介绍](#国密算法介绍)



---------------------------------------------------------------------------------------------------------------------
## Tink加解密库

https://github.com/google/tink


Tink is a multi-language, cross-platform, open source library that provides cryptographic APIs that are secure, easy to use correctly, and hard(er) to misuse.
Tink是一个多语言、跨平台、开源的库，它提供了安全、易于正确使用、不易滥用的加密api。


Tink 当前提供四种加密操作。每种操作都是由特定的原语实现的：

对相关联数据经认证的加密。（原语：AEAD）。
消息认证代码（原语：MAC）。
数字签名（原语：PublicKeySign 和 PublicKeyVerify）
混合加密（原语：HybridEncrypt 和 HybridDecrypt）。




在Tink中提供了Aead、DeterministicDead、Hybrid、Mac及signature的支持。从包结构看Tink的构成

aead, (Authenticated Encryption with Associated Data) 使用关联数据进行身法认证加密
annotations
config
daead, (Deterministic Authenticated Encryption with Associated Data)使用关联数据进行确定性认证加密
hybrid, 混合型加密
integration
mac, 带秘钥的hash
proto
signature, 签名
streamingaead, 使用关联数据进行流认证加密
subtle
util



由于Tink代码是按照加密的类型分包，所以具体的使用方式也比较容易推敲

XxxTemplates，支持的加密算法模版
XxxFactory， 基元实例获取工厂
基元实例， 具体的加密等操作






参考
https://blog.csdn.net/zhaojiurong/article/details/84194693
https://www.jianshu.com/p/75919b76de93
https://www.jianshu.com/p/3d92030d18b5




---------------------------------------------------------------------------------------------------------------------
## 加密哈希函数库Password4j

Cryptographic hash function (CHFs)

Password4j is a Java utility package for hashing and checking passwords with different Cryptographic hash function (CHFs).
Password4j是一个Java实用程序包，用于使用不同的加密哈希函数（CHFs）哈希和检查密码。

password4j
https://github.com/Password4j/password4j

Password4j提供了三个主要功能。
1、散列密码：Hash the password
2、验证哈希：Verify the hash

3、更新哈希：Update the hash







加解密和加密哈希函数区别：
加密是一个双向过程，其中某些东西经过加密后变得不可读，然后再解密以再次正常使用
密码散列函数的工作方式不同，因为校验和并不意味着要使用特殊的散列密码来逆转。加密哈希函数的唯一作用是比较两段数据，例如在下载文件，存储密码以及从数据库中提取数据时。


流行的算法：Popular Hash Functions
1、Message Digest (MD)：MD2, MD4, MD5 and MD6.
2、Secure Hash Function (SHA)： SHA-0, SHA-1, SHA-2, and SHA-3
3、RIPEMD：
4、Whirlpool：


哈希函数的应用：Applications of Hash Functions
1、密码验证：Password Storage
2、数字签名校验：Data Integrity Check
    签名生成和验证：Signature Generation and Verification
3、文件校验：Verifying File and Message Integrity



密码哈希函数可以反转吗？
黑客可能会使用彩虹表找出校验和的纯文本。Rainbow表是字典，其中列出了数千，数百万甚至数十亿个校验和以及相应的纯文本值。


增强加密哈希
1、盐化会将随机数据添加到每个明文凭证中。结果：两个相同的纯文本密码现在以加密文本形式区分，因此无法检测到重复的密码。
2、键控哈希函数
  密钥哈希函数（也称为哈希消息身份验证代码或HMAC）是一种算法，该算法使用加密密钥和加密哈希函数来生成被密钥化和哈希化的消息身份验证代码。
3、自适应哈希函数
  自适应单向函数是设计为在其内部工作过程中进行迭代，以某种方式导致输出（最终）花费更长的时间来回馈输入的功能。这是自适应的，因为开发人员可以调整迭代次数。为了保护存储的密码，架构师已将自适应设计应用于哈希函数（例如PBKDF2）和加密方案（例如bcrypt）。



加密哈希函数的某些属性会影响密码存储的安全性。
不可逆或单向功能。良好的哈希值将使得很难从输出或哈希值中重建原始密码。
扩散或雪崩效应。仅更改原始密码的一位就应该将其哈希值更改为一半。换句话说，当密码稍有更改时，加密文本的输出应显着且不可预测地更改。
确定性。给定的密​​码必须始终生成相同的哈希值或加密的文本。
耐碰撞性。很难找到两个散列到相同加密文本的不同密码。
不可预测的。哈希值不能通过密码来预测。


Certain properties of cryptographic hash functions impact the security of password storage.

Non-reversibility, or one-way function. A good hash should make it very hard to reconstruct the original password from the output or hash.
Diffusion, or avalanche effect. A change in just one bit of the original password should result in change to half the bits of its hash. In other words, when a password is changed slightly, the output of enciphered text should change significantly and unpredictably.
Determinism. A given password must always generate the same hash value or enciphered text.
Collision resistance. It should be hard to find two different passwords that hash to the same enciphered text.
Non-predictable. The hash value should not be predictable from the password.




参考
https://www.synopsys.com/blogs/software-security/cryptographic-hash-functions/
https://www.tutorialspoint.com/cryptography/cryptography_hash_functions.htm
https://www.investopedia.com/news/cryptographic-hash-functions/
https://www.lifewire.com/cryptographic-hash-function-2625832
https://baike.baidu.com/item/%E5%AF%86%E7%A0%81%E6%95%A3%E5%88%97%E5%87%BD%E6%95%B0



---------------------------------------------------------------------------------------------------------------------
## Wycheproof针对已知攻击测试加密库


https://github.com/google/wycheproof

Wycheproof，这款加密套件包括了一系列安全测试，用来检测加密库（cryptographic libraries）软件是否存在各种已知漏洞。

Wycheproof项目，这是一组用于检查加密软件库是否存在已知漏洞的安全测试。我们已经开发了80多个测试用例，发现了40多个安全错误（某些测试或错误目前尚未开源，因为它们已由供应商修复）。例如，我们发现我们可以恢复广泛使用的DSA和ECDHC实现的私钥。我们还提供了现成的工具来检查Java密码体系结构提供程序，例如Bouncy Castle和OpenJDK中的默认提供程序。




---------------------------------------------------------------------------------------------------------------------

## 国密算法介绍


Java自身通过JCE和JSSE支持标准的SSL协议，但并不支持国密SSL协议。本文描述了Java使用国密JCE和国密JSSE开发一个简单的客户端程序，连接国密Web网站，发送HTTP请求，并接收HTTP应答。


国密JCE和国密JSSE。下载参 
https://www.gmssl.cn/gmssl/index.jsp?go=gmsdk
gmjce.jar和gmjsse.jar放到jre的lib/ext/目录下



注释
首先要注册国密提供者
Security.insertProviderAt(new cn.gmssl.jce.provider.GMJCE(), 1);
Security.insertProviderAt(new cn.gmssl.jsse.provider.GMJSSE(), 2);

其中要使用国密SSL来连接
String protocol = cn.gmssl.jsse.provider.GMJSSE.GMSSLv11;
String provider = cn.gmssl.jsse.provider.GMJSSE.NAME;
SSLContext ctx = SSLContext.getInstance(protocol, provider);

小结
  通过使用国密JCE和国密JSSE，Java很容易编程来使用国密SSL连接国密Web网站。www.gmssl.cn提供了全部免费的测试组件,并且支持双向国密SSL，可供学习和测试。


参考
/Users/yangzl/git/quickstart-framework/quickstart-crypto/src/test/java/org/quickstart/crypto/gmssl/SocketGet.java



参考
https://developer.aliyun.com/article/771040
https://blog.csdn.net/upset_ming/article/details/79880688
https://blog.csdn.net/upset_ming/article/details/87872381
http://gb.oversea.cnki.net/KCMS/detail/detailall.aspx?filename=1015437559.nh&dbcode=CMFD&dbname=CMFDREF
https://juejin.im/post/6844904169342500878



---------------------------------------------------------------------------------------------------------------------






