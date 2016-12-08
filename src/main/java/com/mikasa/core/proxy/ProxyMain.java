package com.mikasa.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by sherlock on 2016/12/8.
 */
public class ProxyMain {

    public static void consumer(ProxyInterface iface) {
        iface.print();
        String name = iface.getName("mikasa");
        System.out.println("name: " + name);
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);
        System.out.println("==============================");
        // 动态代理
        ClassLoader classLoader = ProxyInterface.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { ProxyInterface.class };
        InvocationHandler handler = new MyInvocationHandler(realObject);
        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println("in ProxyMain proxy: " + proxy.getClass());
        consumer(proxy);
    }
}
