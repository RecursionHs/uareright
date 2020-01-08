package com.hs.hadooprpc.service;

import com.hs.hadooprpc.protocol.ClientNamenodeProtocol;

public class MyNameNode  implements ClientNamenodeProtocol {

    @Override
    public String getMetaData(String path) {
        return path + ": 3 - {BLK_1,BLK_2} ...";
    }
}
