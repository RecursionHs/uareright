package com.hs.ioc;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hsir
 * @Date 2020-9-14
 * @Version 1.0
 **/
public class SimpleIOC {
    private Map<String,Object> beanMap = new HashMap();

    public SimpleIOC(String xmlPath) throws Exception {
        initIocApp(xmlPath);
    }

    public void initIocApp(String xmlPath) throws Exception {
        FileInputStream in = new FileInputStream(xmlPath);
        DocumentBuilderFactory docBuildFactor = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = docBuildFactor.newDocumentBuilder();
        Document document = documentBuilder.parse(in);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if(node instanceof Element){
                Element ele = (Element)node;
                //基本属性
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                //加载class
                Class<?> beanClass = null;
                try {
                    beanClass  = Class.forName(className);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                    return;
                }

                //实例化bean
                Object bean = beanClass.getDeclaredConstructor().newInstance();
                //里面的属性
                NodeList property = ele.getElementsByTagName("property");
                for (int j = 0; j < property.getLength(); j++) {
                    Node pNode = property.item(j);
                    if(pNode instanceof Element){
                        Element propertyNode  = (Element)pNode;
                        String name = propertyNode.getAttribute("name");
                        //这里如果为Int或者其它类型呢？如果解决？
                        String value = propertyNode.getAttribute("value");

                        Field field = bean.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        //设置值
                        if(value != null && value.length() > 0){
                            field.set(bean,value);
                        }else{
                            String ref = propertyNode.getAttribute("ref");
                            if(ref == null || ref.length() == 0){
                                throw new IllegalArgumentException("ref config error");
                            }
                            //这里存在先后顺序的初始化，但是如果有引用就需要先初始化?
                            if(getBean(ref) != null){
                                field.set(bean,getBean(ref));
                            }else{
                                //initBean(bean);
                            }
                        }

                    }

                }
                registerBean(id,bean);

            }
        }

    }

    public Object getBean(String bname) {
        return beanMap.get(bname);
    }

    public void registerBean(String id,Object obj){
        beanMap.put(id,obj);
    }
}
