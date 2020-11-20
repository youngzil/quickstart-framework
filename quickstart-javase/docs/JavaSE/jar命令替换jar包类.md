- [jar命令替换jar包类](#jar命令替换jar包类)


-------------------------------------------------------------------------
## jar命令替换jar包类


 jar uvf aiesb.jar com/yangzl/openplatform/isb/restful/security/resources/Authorization.class 
 
 
cd /Users/yangzl/workspace/gateway5/security-parent/security-zjauth/target/classes/
jar uvf /Users/yangzl/Documents/security-1.0/lib/security-zjauth-1.0.jar com/quickstart/security/zjauth/service/BaseAuthorizationService.class



Java 生态系统提供了标准的格式来分发同一个应用中的所有 Java 类。我们可以将这些类打包为 JAR（Java Archive）、WAR（Web Archive）以及 EAR（Enterprise Archive），在这些格式中包含了前端、后端以及嵌入其中的库。

---------------------------------------------------------------------------------------------------------------------