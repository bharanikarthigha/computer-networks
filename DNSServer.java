import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            
            HashMap<String, String> dnsTable = new HashMap<>();
            dnsTable.put("google.com", "142.250.182.14");
            dnsTable.put("openai.com", "104.19.155.20");
            dnsTable.put("yahoo.com", "98.137.11.163");

            System.out.println("DNS Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received query for: " + domain);

                String ip = dnsTable.getOrDefault(domain, "Domain not found");

                sendBuffer = ip.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    receivePacket.getAddress(),
                    receivePacket.getPort()
                );
                serverSocket.send(sendPacket);
            }

        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}

