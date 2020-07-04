package com;


import com.config.AppConfig;
import com.dao.IndexDao;
import com.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartApplication {
    public static void main(String[] args) {

       /* AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        IndexDao indexDaoImpl = annotationConfigApplicationContext.getBean(IndexDao.class);
        //IndexDaoImpl indexDaoImpl = annotationConfigApplicationContext.getBean(IndexDaoImpl.class);
        indexDaoImpl.query("h");
        indexDaoImpl.query(12);*/


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IndexDao indexDaoImpl = (IndexDao) context.getBean("indexDaoImpl");
        indexDaoImpl.query("haha");
        indexDaoImpl.query(33);



    }
}
