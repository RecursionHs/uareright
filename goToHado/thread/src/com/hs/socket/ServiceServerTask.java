package com.hs.socket;

import java.io.*;
import java.net.Socket;

public class ServiceServerTask implements Runnable{
        private Socket socket;
        private InputStream in = null;
        private OutputStream out = null;

        public ServiceServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //获取输入输出流
                in = socket.getInputStream();
                out = socket.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = br.readLine();

                //处理参数
                GetDataMethodImpl gm = new GetDataMethodImpl();
                String s = gm.apllyParam(line);

                //返回
                PrintWriter printWriter = new PrintWriter(out);
                printWriter.println(s);
                printWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
