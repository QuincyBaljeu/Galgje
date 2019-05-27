package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GalgjeMaster {

    private Socket socket;
    private String host;
    private int port;
    private String name;

    public GalgjeMaster(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        try{
            this.socket = new Socket(this.host, this.port);

            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Master connected");
            System.out.println("Enter your keyword");
            String key = scanner.nextLine();
            out.writeUTF(key);
            while (true){
            }
        } catch (IOException e){
            System.out.println("Could not connect with server");
        }
    }
}
