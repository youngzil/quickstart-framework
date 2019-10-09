1、tink  加解密库
2、wycheproof 针对已知攻击测试加密库

---------------------------------------------------------------------------------------------------------------------
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
https://github.com/google/wycheproof

Wycheproof，这款加密套件包括了一系列安全测试，用来检测加密库（cryptographic libraries）软件是否存在各种已知漏洞。

Wycheproof项目，这是一组用于检查加密软件库是否存在已知漏洞的安全测试。我们已经开发了80多个测试用例，发现了40多个安全错误（某些测试或错误目前尚未开源，因为它们已由供应商修复）。例如，我们发现我们可以恢复广泛使用的DSA和ECDHC实现的私钥。我们还提供了现成的工具来检查Java密码体系结构提供程序，例如Bouncy Castle和OpenJDK中的默认提供程序。




---------------------------------------------------------------------------------------------------------------------




