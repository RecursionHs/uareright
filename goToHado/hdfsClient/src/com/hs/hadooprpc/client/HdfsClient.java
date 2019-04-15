package com.hs.hadooprpc.client;

import com.hs.hadooprpc.protocol.ClientNamenodeProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;


public class HdfsClient {

    public static void main(String[] args) throws IOException {
        ClientNamenodeProtocol namenodeProtocol = RPC.getProxy(ClientNamenodeProtocol.class, 1L, new InetSocketAddress("localhost", 8888), new Configuration());
        String s = namenodeProtocol.getMetaData("/ang.ba.t");
        System.out.println(s);
    }
}
