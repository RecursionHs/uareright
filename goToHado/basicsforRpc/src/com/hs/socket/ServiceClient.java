package com.hs.socket;

import java.io.*;
import java.net.Socket;

public class ServiceClient {

    public static void main(String[] args) throws IOException {
        //与服务端建立连接
        Socket socket = new Socket("localhost", 8899);
        //从socket流中获取输入输出流
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(out);
        pw.println("hello");
        pw.flush();

        //拿取响应结果
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String reslut = br.readLine();
        System.out.println(reslut);

        in.close();
        out.close();
        socket.close();

    }
}
