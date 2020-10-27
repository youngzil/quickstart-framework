Java混淆器ProGuard

http://www.oschina.net/p/proguard

https://sourceforge.net/projects/proguard/
https://github.com/facebook/proguard
https://www.guardsquare.com/en/proguard

我们通常说的proguard包括四个功能，shrinker（压缩）, optimizer（优化）,obfuscator（混淆）,preverifier（预校验）。
它能够通过移除无用代码，使用简短无意义的名称来重命名类，字段和方法。从而能够达到压缩、优化和混淆代码的目的。最终我们会获取一个较小的apk文件，并且我们这个通过ProGuard处理的apk文件更难于进行逆向工程。

ProGuard工作原理简介
　　ProGuard能够对Java类中的代码进行压缩（Shrink）,优化（Optimize）,混淆（Obfuscate）,预检（Preveirfy）。 
　　1. 压缩（Shrink）:在压缩处理这一步中，用于检测和删除没有使用的类，字段，方法和属性。 
　　2. 优化（Optimize）:在优化处理这一步中，对字节码进行优化，并且移除无用指令。 
　　3. 混淆（Obfuscate）:在混淆处理这一步中，使用a,b,c等无意义的名称，对类，字段和方法进行重命名。 
　　4. 预检（Preveirfy）:在预检这一步中，主要是在Java平台上对处理后的代码进行预检。 