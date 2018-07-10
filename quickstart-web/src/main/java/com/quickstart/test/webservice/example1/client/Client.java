package com.quickstart.test.webservice.example1.client;

public class Client {

    /**
     * 通过wsimport 解析wsdl生成客户端代码调用WebService服务 wsimport -s D:\\ziliang.yang\\workspace2\\quickstart\\src -p com.yang.test.webservice.example1.client -keep
     * http://localhost:9001/Service/HelloService?wsdl
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /**
         * <service name="MyService"> 获得服务名称
         */
        MyService mywebService = new MyService();

        /**
         * <port name="HelloServicePort" binding="tns:HelloServicePortBinding">
         */
        HelloService hs = mywebService.getHelloServicePort();

        /**
         * 调用方法
         */
        System.out.println(hs.sayGoodbye("sjk", 3));

        System.out.println(hs.aliassayHello("sjk"));

    }

}
