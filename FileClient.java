import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            byte[] bytes = new byte[1024];
            InputStream is = socket.getInputStream();

            FileOutputStream fos = new FileOutputStream("received.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int bytesRead;
            while ((bytesRead = is.read(bytes)) != -1) {
                bos.write(bytes, 0, bytesRead);
            }

            bos.close();
            System.out.println("File received and saved as 'received.txt'");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

