package cn.blmdz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        char[] charArray = new char[3];
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("accept begin: " + System.currentTimeMillis());
        
        Socket socket = serverSocket.accept();
        System.out.println("accept end: " + System.currentTimeMillis());
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        System.out.println("read begin: " + System.currentTimeMillis());
        
        StringBuffer sb = new StringBuffer();
        int len = 0;
        while ((len = inputStreamReader.read(charArray)) != -1) {
            sb.append(charArray, 0, len);
        }
        System.out.println("read conetent: " + sb.toString());
        inputStreamReader.close();
        inputStream.close();
        System.out.println("read end: " + System.currentTimeMillis());
        socket.close();
        serverSocket.close();
    }
}
