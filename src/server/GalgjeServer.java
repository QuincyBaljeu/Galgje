package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GalgjeServer {

    private int port;
    private ServerSocket server;
    private Thread serverThread;
    private ArrayList<Thread> threads;

    public GalgjeServer(int port) {
        this.port = port;
        this.threads = new ArrayList<>();
    }

    public void start(){
        try {
            this.server = new ServerSocket(port);
            this.serverThread = new Thread(()->{
                while (true) {
                    System.out.println("Waiting for player to connect");
                    try {

                        Socket master = this.server.accept();
                        DataInputStream keyReader = new DataInputStream(master.getInputStream());
                        String key = keyReader.readUTF();
                        Socket player = this.server.accept();
                        DataInputStream in = new DataInputStream(player.getInputStream());
                        System.out.println("Client connected:" + player.getInetAddress().getHostAddress());
                        while (true){
                            String guess = in.readUTF();
                            if(key.contains(guess)){
                                System.out.println("joe joe");
                            } else {
                                System.out.println("nope nope");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("helemaal naar de touwtyfus");
                    }
                }
            });
            this.serverThread.start();
        } catch (IOException e){
            System.out.println("Could not connect");
        }


    }



}
