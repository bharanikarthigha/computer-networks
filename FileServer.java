import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Path to the file to send
            File file = new File("sample.txt");
            byte[] bytes = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytes, 0, bytes.length);

            OutputStream os = socket.getOutputStream();
            os.write(bytes, 0, bytes.length);
            os.flush();

            System.out.println("File sent successfully!");

            socket.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 
    

