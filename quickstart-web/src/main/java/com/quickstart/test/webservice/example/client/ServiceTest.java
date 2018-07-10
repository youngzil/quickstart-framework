package com.quickstart.test.webservice.example.client;

public class ServiceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ServiceHello hello = new ServiceHelloService().getServiceHelloPort();
        String name = hello.getValue("Hyan");
        System.out.println(name);
    }
}
