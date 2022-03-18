package concurrent.simpleHttpServer;

import concurrent.threadpoolDemo.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

    //根路径
    static String basePath;
    static ServerSocket serverSocket;
    //服务监听端口
    static int port = 8080;

    /**
    *@author weiyifei
    *@description http请求处理线程类
    *@date 2022/2/28
    */
    static class HttpRequestHandler implements Runnable {

        //网络套接字
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader= null;
            PrintWriter out = null;
            InputStream in = null;
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果资源后缀是jpg或者ico，则读取资源并输出
                if(filePath.endsWith("jpg")||filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0 ;
                    while ((i=in.read())!=-1){
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: "+array.length);
                    out.println("");
                    socket.getOutputStream().write(array,0,array.length);
                }else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                }
            }catch (Exception e){

            }
        }
    }


}


