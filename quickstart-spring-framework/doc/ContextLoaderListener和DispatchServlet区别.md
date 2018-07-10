ContextLoaderListener是父，全局的bean，dao、service等，不能获取子
DispatchServlet是子，局部的bean，主要是Control、viewresolver,子可以获取父，在代码中设置SetParent为ContextLoaderListener



存放在ServletContext中的key不同，获取的方式也不一样，




