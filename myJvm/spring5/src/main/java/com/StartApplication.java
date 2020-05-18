package com;


import com.config.AppConfig;
import com.dao.IndexDao;
import com.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);
        IndexDao indexDaoImpl = annotationConfigApplicationContext.getBean(IndexDao.class);
        //IndexDaoImpl indexDaoImpl = annotationConfigApplicationContext.getBean(IndexDaoImpl.class);
        indexDaoImpl.query("h");
    }
}
