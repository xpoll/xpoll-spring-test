package cn.blmdz.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientDemo1 {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        
        System.out.println("socket begin: " + System.currentTimeMillis());
        Socket socket = new Socket("127.0.0.1", 5555);
        System.out.println("socket end: " + System.currentTimeMillis());
        
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("send begin: " + System.currentTimeMillis());
        outputStream.write("啦啦啦啦发牢骚京东方".getBytes());
        Thread.sleep(1000L);
        outputStream.write("啦啦啦啦发牢骚京东方".getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("send end: " + System.currentTimeMillis());
        socket.close();
    }
}
