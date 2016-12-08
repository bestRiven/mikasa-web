package com.mikasa.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by sherlock on 2016/12/8.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler() {}

    public MyInvocationHandler(Object target) {
        System.out.println("my proxy handler constuctor: " + target.getClass());
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("dynamic proxy name: " + proxy.getClass());
        System.out.println("method: " + method.getName());
        System.out.println("args: " + Arrays.toString(args));

        Object invokeObject = method.invoke(target, args);
        if (invokeObject != null) {
            System.out.println("invoke object: " + invokeObject.getClass());
        } else {
            System.out.println("invoke object is null");
        }
        return invokeObject;
    }
}
