package com.springFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName HsSpringFactory
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/7 下午4:50
 * @Version 1.0
 */
public class HsSpringFactory {

    private Map<String,Object> beanMap = new ConcurrentHashMap<>();

    public HsSpringFactory(String xml){
        initBeanFactoryByXml(xml);
    }

    private void initBeanFactoryByXml(String xml) {
        String filePath = this.getClass().getClassLoader().getResource("/").getPath() + File.separator + xml;
        File xmlFilePath = new File(filePath);
        SAXReader reader = new SAXReader();
        //入Map 的对象
        Object object = null;
        try {
            Document document = reader.read(xmlFilePath);
            Element rootElement = document.getRootElement();
            //看有没有配置default,如果有
            Attribute aDefault = rootElement.attribute("default-autowire");
            Boolean flag = false;
            if(aDefault != null){
                flag = true;
            }
            //便利第一层
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()){
                Element element = iterator.next();
                Attribute id = element.attribute("id");
                String beanName = id.getValue();
                Attribute aClass = element.attribute("class");
                String aClassValue = aClass.getValue();
                Class<?> clazz = Class.forName(aClassValue);
                

                //看有没有Property
                for(Iterator<Element> secIterator = element.elementIterator();secIterator.hasNext();){
                    Element secElement = secIterator.next();
                    String secElementName = secElement.getName();
                    if("property".equals(secElementName)){
                        //取出ref及name,实例化及set
                        String name = secElement.attribute("name").getName();
                        String refName = secElement.attribute("ref").getName();
                        Object injectObj = beanMap.get(refName);
                        object = clazz.newInstance();
                        Field field = clazz.getDeclaredField(name);
                        field.setAccessible(true);
                        field.set(object,injectObj);
                    }else{
                        //构造注入
                        String refName = secElement.attribute("ref").getName();
                        Object injectObj = beanMap.get(refName);
                        Class<?> injectClazz = injectObj.getClass();
                        Constructor<?> constructor = clazz.getConstructor(injectClazz.getInterfaces()[0]);
                        object = constructor.newInstance(injectObj);
                    }
                }

                if(object == null){
                    if(flag){
                        if("byType".equals(aDefault.getValue())){
                            Field[] fields = clazz.getFields();
                            for (Field field : fields) {
                                //看字段的类型
                                String fieldTypeName = field.getType().getName();
                                /**
                                 * 由於是bytype 所以需要遍历map当中的所有对象
                                 * 判断对象的类型是不是和这个injectObjectClazz相同
                                 */
                                int count = 0;
                                Object injectObject = null;
                                Set<String> stringSet = beanMap.keySet();
                                for (String sKey : stringSet) {
                                    String InjectName = beanMap.get(sKey).getClass().getInterfaces()[0].getName();
                                    injectObject = beanMap.get(sKey);
                                    count++;
                                }
                                if(count > 1){
                                    throw new BeanException("找到多个相同的对象");
                                }else{
                                    object = clazz.newInstance();
                                    field.setAccessible(true);
                                    field.set(object,injectObject);
                                }
                            }
                        }
                    }
                }

                //如果没有子标签，就初始化第一节点的bean
                if(object == null){
                    object= clazz.newInstance();
                }

            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (BeanException e) {
            e.printStackTrace();
        }


    }
    public Object getBean(String beanName){
        return beanMap.get(beanName);
    }
}
