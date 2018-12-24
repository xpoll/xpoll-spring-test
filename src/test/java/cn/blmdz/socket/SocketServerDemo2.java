package cn.blmdz.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("accept begin: " + System.currentTimeMillis());
        
        Socket socket = serverSocket.accept();
        System.out.println("accept end: " + System.currentTimeMillis());

        OutputStream outputStream = socket.getOutputStream();
        System.out.println("send begin: " + System.currentTimeMillis());
        outputStream.write("啦啦啦啦发牢骚京东方".getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("send end: " + System.currentTimeMillis());
        socket.close();
        serverSocket.close();
    }
}
