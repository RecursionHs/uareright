package com.hs.thrift;

import com.hs.thrift.impl.HelloWorldImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ToDoSomeThing {
    public static final int SERVER_PORT = 8090;

    public static void main(String[] args) {
        ToDoSomeThing doSomeThing = new ToDoSomeThing();
        doSomeThing.startServer();
    }

    public void startServer(){
        try {
            System.out.println("----------serverStart------------");
            TProcessor tprocessor =
                    new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());

            //简单的单线程服务模型
            TServerSocket tServerSocket = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(tServerSocket);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());

            TSimpleServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }


}
