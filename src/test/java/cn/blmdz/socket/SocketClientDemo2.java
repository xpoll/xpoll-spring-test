package cn.blmdz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientDemo2 {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

        char[] charArray = new char[3];
        System.out.println("socket begin: " + System.currentTimeMillis());
        Socket socket = new Socket("127.0.0.1", 5555);
        System.out.println("socket end: " + System.currentTimeMillis());

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
    }
}
