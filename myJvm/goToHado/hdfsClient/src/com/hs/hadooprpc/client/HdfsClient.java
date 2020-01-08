package com.hs.hadooprpc.client;

import com.hs.hadooprpc.protocol.ClientNamenodeProtocol;


import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;


public class HdfsClient {

    public static void main(String[] args) throws IOException {
        ClientNamenodeProtocol namenodeProtocol = RPC.getProxy(ClientNamenodeProtocol.class, 1L, new InetSocketAddress("localhost", 8888), new Configuration());
        String s = namenodeProtocol.getMetaData("/ang.ba.t");
        System.out.println(s);
    }
}
