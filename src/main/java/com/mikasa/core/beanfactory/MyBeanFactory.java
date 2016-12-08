package com.mikasa.core.beanfactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sherlock on 2016/12/8.
 */
public class MyBeanFactory {
    //bean容器
    private Map<String, Object> contianer = new HashMap<String, Object>();

    /**
     * <p>Discription:bean工厂的初始化</p>
     * @param xml xml配置文件路径
     * @author       : lcma
     * @update       : 2016年9月20日上午9:04:41
     */
    public void init(String xml) {
        try {
            // 读取指定的配置文件
            SAXReader reader = new SAXReader();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 从class目录下获取指定的xml文件
            InputStream ins = classLoader.getResourceAsStream(xml);
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
            Element foo;
            // 遍历bean
            for (Iterator i = root.elementIterator("bean"); i.hasNext();) {
                foo = (Element) i.next();
                // 获取bean的属性id和class
                Attribute id = foo.attribute("id");
                Attribute cls = foo.attribute("class");
                // 利用Java反射机制，通过class的名称获取Class对象
                Class<?> bean = Class.forName(cls.getText());
                // 获取对应class的信息
                java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(bean);
                // 获取其属性描述
                java.beans.PropertyDescriptor pd[] = info.getPropertyDescriptors();
                // 设置值的方法
                Method mSet = null;
                // 创建一个对象
                Object obj = bean.newInstance();
                // 遍历该bean的property属性
                for (Iterator ite = foo.elementIterator("property"); ite.hasNext();) {
                    Element foo2 = (Element) ite.next();
                    // 获取该property的name属性
                    Attribute name = foo2.attribute("name");
                    String value = null;
                    // 获取该property的子元素value的值
                    for (Iterator ite1 = foo2.elementIterator("value"); ite1.hasNext();) {
                        Element node = (Element) ite1.next();
                        value = node.getText();
                        break;
                    }
                    for (int k = 0; k < pd.length; k++) {
                        if (pd[k].getName().equalsIgnoreCase(name.getText())) {
                            mSet = pd[k].getWriteMethod();
                            // 利用Java的反射机制调用对象的某个set方法，并将值设进去
                            mSet.invoke(obj, value);
                        }
                    }
                }
                // 将对象放入beanMap中，其中key为id值，value为对象
                contianer.put(id.getText(), obj);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * <p>Discription:通过bean的id在容器中获取bean对象</p>
     * @param beanName bean的唯一标识id
     * @return
     * @author       : lcma
     * @update       : 2016年9月20日上午9:05:00
     */
    public Object getBean(String beanName) {
        Object obj = contianer.get(beanName);
        return obj;
    }

    /**
     * <p>Discription:测试方法</p>
     * @param args
     * @author       : lcma
     * @update       : 2016年9月20日上午9:06:06
     */
    public static void main(String[] args) {
        //实例化BeanFactory
        MyBeanFactory factory = new MyBeanFactory();
        //调用初始化方法，传入xml路径
        factory.init("spring.xml");
        //通过bean id 获取对象
        CourseService courseService = (CourseService) factory.getBean("courseService");
        //调用对象方法
        courseService.print();
    }
}
