package com.dao;

import java.util.concurrent.BlockingQueue;

public interface IndexDao {
    void query(String str);

    BlockingQueue query(Integer a);
}
