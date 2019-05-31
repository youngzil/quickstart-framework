1、SSO
2、OpenID
3、OAuth
4、
5、






---------------------------------------------------------------------------------------------------------------------

跨应用、跨域的单点登录SSO（Single sign-on）











---------------------------------------------------------------------------------------------------------------------
https://icarusliu.iteye.com/blog/246570

OpenID 2.0实现


openid的client和server端的示例代码
https://www.ibm.com/developerworks/cn/java/j-openid2/index.html
https://www.ibm.com/developerworks/cn/cloud/library/cl-lo-openid-connect-kubernetes-authentication2/index.html


参考/Users/yangzl/git/quickstart-application-container/quickstart-jetty/src/main/java/org/quickstart/container/jetty/openid4java


OpenID官方网站：http://openid.net/
OpenID工作过程：http://openid.net/about.bml
OpenID 协议，请参考技术规范： http://openid.net/specs.bml
OpenID社区维护了这些代码库的清单：http://openid.net/wiki/index.php/Libraries
OpenID4Java项目主页：http://code.google.com/p/openid4java/wiki/ProjectHome_zh_CN
下载OpenID4Java：http://code.sxip.com/openid4java/
支持OpenID的各种代码库：http://www.openidenabled.com/
目前支持OpenID的主要站点：http://iwantmyopenid.org/bounty/sponsors


java库：openid4java 和 jopenid
OpenID的Java实现：OpenID4Java(http://www.openid4java.org)。
https://openid.net/developers/libraries/#java

https://github.com/jbufu/openid4java


---------------------------------------------------------------------------------------------------------------------
http://www.ruanyifeng.com/blog/2019/04/oauth_design.html
https://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html
https://blog.csdn.net/u013436121/article/details/23631885
https://www.zhihu.com/question/19851243
https://oauthlib.readthedocs.io/en/v3.0.1/oauth_1_versus_oauth_2.html


客户端示例
https://www.ibm.com/developerworks/cn/java/se-oauthjavapt1/index.html



OAuth 2.0
https://oauth.net/
https://oauth.net/code/java/


OAuth 2.0 是一种授权机制，主要用来颁发令牌（token）。
获取令牌的四种方式
Oauth1.0与Oauth2.0的区别：3点



在OAuth诞生前，Web安全方面的标准协议只有OpenID，不过它关注的是验证，即WHO的问题，而不是授权，即WHAT的问题。


简单说，OAuth 就是一种授权机制。数据的所有者告诉系统，同意授权第三方应用进入系统，获取这些数据。系统从而产生一个短期的进入令牌（token），用来代替密码，供第三方应用使用。

OAuth 引入了一个授权层，用来分离两种不同的角色：客户端和资源所有者。......资源所有者同意以后，资源服务器可以向客户端颁发令牌。客户端通过令牌，去请求数据。


令牌与密码
令牌（token）与密码（password）的作用是一样的，都可以进入系统，但是有三点差异。
（1）令牌是短期的，到期会自动失效，用户自己无法修改。密码一般长期有效，用户不修改，就不会发生变化。
（2）令牌可以被数据所有者撤销，会立即失效。以上例而言，屋主可以随时取消快递员的令牌。密码一般不允许被他人撤销。
（3）令牌有权限范围（scope），比如只能进小区的二号门。对于网络服务来说，只读令牌就比读写令牌更安全。密码一般是完整权限。

上面这些设计，保证了令牌既可以让第三方应用获得权限，同时又随时可控，不会危及系统安全。这就是 OAuth 2.0 的优点。
注意，只要知道了令牌，就能进入系统。系统一般不会再次确认身份，所以令牌必须保密，泄漏令牌与泄漏密码的后果是一样的。 这也是为什么令牌的有效期，一般都设置得很短的原因。



具体来说，一共分成四种授权类型（authorization grant），即四种颁发令牌的方式，适用于不同的互联网场景。
OAuth 2.0 规定了四种获得令牌的流程。你可以选择最适合自己的那一种，向第三方应用颁发令牌。下面就是这四种授权方式。
1、授权码（authorization code）方式，指的是第三方应用先申请一个授权码，然后再用该码获取令牌。
2、隐藏式（implicit）：允许直接向前端颁发令牌。这种方式没有授权码这个中间步骤，所以称为（授权码）"隐藏式"（implicit）。
3、密码式（password）：如果你高度信任某个应用，RFC 6749 也允许用户把用户名和密码，直接告诉该应用。该应用就使用你的密码，申请令牌，这种方式称为"密码式"（password）。
4、客户端凭证（client credentials）：最后一种方式是凭证式（client credentials），适用于没有前端的命令行应用，即在命令行下请求令牌。这种方式给出的令牌，是针对第三方应用的，而不是针对用户的，即有可能多个用户共享同一个令牌。
                                                                                    
更新令牌
令牌的有效期到了，如果让用户重新走一遍上面的流程，再申请一个新的令牌，很可能体验不好，而且也没有必要。OAuth 2.0 允许用户自动更新令牌。
具体方法是，B 网站颁发令牌的时候，一次性颁发两个令牌，一个用于获取数据，另一个用于获取新的令牌（refresh token 字段）。令牌到期前，用户使用 refresh token 发一个请求，去更新令牌。
                            


所谓第三方登录，实质就是 OAuth 授权。用户想要登录 A 网站，A 网站让用户提供第三方网站的数据，证明自己的身份。获取第三方网站的身份数据，就需要 OAuth 授权。

举例来说，A 网站允许 GitHub 登录，背后就是下面的流程。
1、A 网站让用户跳转到 GitHub。
2、GitHub 要求用户登录，然后询问"A 网站要求获得 xx 权限，你是否同意？"
3、用户同意，GitHub 就会重定向回 A 网站，同时发回一个授权码。
4、A 网站使用授权码，向 GitHub 请求令牌。
5、GitHub 返回令牌.
6、A 网站使用令牌，向 GitHub 请求用户数据。





Oauth1.0与Oauth2.0的区别：3点

①auth1.0与Oauth2.0是相互不兼容的，所以他们为我们提供了不同的授权方式：

2.0的用户授权过程有3步：
         A)用户到授权服务器，请求授权，然后返回授权码(AuthorizationCode)
         B)客户端由授权码到授权服务器换取访问令牌(access token)
         C)用访问令牌去访问得到授权的资源、
总结：获取授权码(Authorization Code)—>换取访问令牌（access_token）—>访问资源:

1.0的授权分4步,
          A)客户端到授权服务器请求一个授权令牌(requesttoken&secret)
         B)引导用户到授权服务器请求授权
         C)用访问令牌到授权服务器换取访问令牌(accesstoken&secret)
         D)用访问令牌去访问得到授权的资源
总结：请求授权令牌（request token&secret）—>换取访问令牌（access token&secret）—>访问资源
 

②1.0协议每个token都有一个加密，2.0则不需要。这样来看1.0似乎更加安全，但是2.0要求使用https协议，安全性也更高一筹。
 

③2.0充分考虑了客户端的各种子态，因而提供了多种途径获取访问令牌，有：授权码、

客户端私有证书、资源拥有者密码证书、刷新令牌等方式，而且验证过程更为简洁。
相比之下 1.0只有一个用户授权流程。





---------------------------------------------------------------------------------------------------------------------


