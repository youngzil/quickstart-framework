Plexus——Spring之外的IoC容器


1. Plexus引入
Plexus是什么？它是一个IoC容器，由codehaus在管理的一个开源项目。和Spring框架不同，它并不是一个完整的，拥有各种组件的大型框架，仅仅是一个纯粹的IoC容器。

本文讲解Plexus的初步使用方法。
Plexus和Maven的开发者是同一群人，可以想见Plexus和Maven的紧密关系了。由于在Maven刚刚诞生的时候，Spring还不成熟，所以Maven的开发者决定使用自己维护的IoC容器Plexus。而由于Plexus的文档比较烂，根据社区的呼

声，下一版本的Maven 3则很可能使用比较成熟的Guice框架来取代Plexus，但更换底层框架毕竟不是一件轻松的事情，所以现阶段学习了解Plexus还是很有必要的。并且Plexus目前并未停止开发，因为它的未来还未可知。除了Maven

以外，WebWork(已经与Struts合并)在底层也采用了Pleuxs。






参考
https://blog.csdn.net/songhuiqiao/article/details/49908165


