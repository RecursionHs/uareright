package com.hs.hadooprpc.service;

import com.hs.hadooprpc.protocol.ClientNamenodeProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class PublishServiceUtil {

    public static void main(String[] args) throws IOException {
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(8888)
                .setProtocol(ClientNamenodeProtocol.class)
                .setInstance(new MyNameNode());
        RPC.Server server = builder.build();
        server.start();
    }
}
