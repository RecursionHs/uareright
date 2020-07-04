package com.service;

import com.dao.IndexDao;
import com.dao.IndexDaoImpl;

/**
 * @ClassName IndexService
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/7 下午4:51
 * @Version 1.0
 */
public class IndexService {
    IndexDao indexDao;

    public IndexService(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    public void queryAll(){
        indexDao.query("haha");
    }

    public void setDao(IndexDaoImpl dao) {
    }
}
