package cn.blmdz.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientDemo3 {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1", 5555);
        // socket.getOutputStream().write("234".getBytes());
        // socket.getOutputStream().flush();
        // socket.close();

        // InputStream is = socket.getInputStream();
        // byte[] data = new byte[1024];
        // while (is.read(data) > 0) {
        // System.out.println(new String(data));
        // }
        // socket.close();

        // ObjectOutputStream oos = new
        // ObjectOutputStream(socket.getOutputStream());
        // oos.writeUTF("234");
        // oos.flush();
        // oos.close();
        // socket.close();

        // ObjectInputStream ois = new
        // ObjectInputStream(socket.getInputStream());
        // System.out.println(ois.readUTF());
        // ois.close();
        // socket.close();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println(ois.readUTF());
        System.out.println(ois.readUTF());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF("3415");
        oos.flush();
        oos.writeUTF("3415");
        oos.flush();
        oos.close();
        ois.close();
        socket.close();

    }
}
