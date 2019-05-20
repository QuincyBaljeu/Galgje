package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GalgjePlayer {

    private Socket socket;
    private String host;
    private int port;
    private String name;

    public GalgjePlayer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        try{
            this.socket = new Socket(this.host, this.port);

            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream in = new DataInputStream(this.socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.println("user connected");

            while (true){
                System.out.println("Guess which letter?");
                String message = scanner.nextLine();
                out.writeUTF(message);
                System.out.println(in.readUTF());
            }
        } catch (IOException e){
            System.out.println("Could not connect with server");
        }
    }
}
