package com.mikasa.core.proxy;

/**
 * Created by sherlock on 2016/12/8.
 */
public class RealObject implements ProxyInterface {
    @Override
    public void print() {
        System.out.println("hello world");
    }

    @Override
    public String getName(String name) {
        System.out.println("name is:" + name);
        return name;
    }
}
