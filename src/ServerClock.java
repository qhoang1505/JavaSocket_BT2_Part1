import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class ServerClock {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMsg;
                while ((clientMsg = in.readLine()) != null) {
                    if (clientMsg.equals("time")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                        String time = dateFormat.format(new Date());
                        out.println(time);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
