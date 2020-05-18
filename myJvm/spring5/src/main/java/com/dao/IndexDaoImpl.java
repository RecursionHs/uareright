package com.dao;

import org.springframework.stereotype.Repository;

@Repository
public class IndexDaoImpl implements IndexDao{

    public void query(String a) {
        System.out.println("查询数据为...");
        System.out.println(a);
    }
}
