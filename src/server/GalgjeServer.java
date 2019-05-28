package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
                System.out.println("Waiting for player to connect");

                    try {
                        /**
                         * Connect to master and read the given password
                         */
                        Socket master = this.server.accept();
                        DataInputStream passwordReader = new DataInputStream(master.getInputStream());
                        String password = passwordReader.readUTF();
                        System.out.println(password);

                        /**
                         * Connect to player
                         */

                        Socket player = this.server.accept();
                        DataInputStream guessReader = new DataInputStream(player.getInputStream());

                        while(true){
                            System.out.println(guessReader.readUTF());
                        }






                    } catch (IOException e) {
                        System.out.println("Connection lost");
                }
            });
            this.serverThread.start();
        } catch (IOException e){
            System.out.println("Could not connect");
        }


    }



}
