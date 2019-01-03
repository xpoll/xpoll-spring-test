package cn.blmdz.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = serverSocket.accept();
        // InputStream is = socket.getInputStream();
        // byte[] data = new byte[1024];
        // while (is.read(data) > 0) {
        // System.out.println(new String(data));
        // }
        // socket.close();
        // serverSocket.close();

        // socket.getOutputStream().write("234".getBytes());
        // socket.getOutputStream().flush();
        // socket.close();
        // serverSocket.close();

        // ObjectInputStream ois = new
        // ObjectInputStream(socket.getInputStream());
        // System.out.println(ois.readUTF());
        // ois.close();
        // socket.close();
        // serverSocket.close();

        // ObjectOutputStream oos = new
        // ObjectOutputStream(socket.getOutputStream());
        // oos.writeUTF("234");
        // oos.flush();
        // oos.close();
        // socket.close();
        // serverSocket.close();

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF("2314");
        oos.flush();
        oos.writeUTF("2314");
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println(ois.readUTF());
        System.out.println(ois.readUTF());
        oos.close();
        ois.close();
        socket.close();
        serverSocket.close();
    }
}
