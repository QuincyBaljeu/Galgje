package server;

import java.awt.*;
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
    int wrongGuesses;
    private String guessProgress;
    private String guessedLetters;

    public GalgjeServer(int port) {
        this.port = port;
        this.threads = new ArrayList<>();
        wrongGuesses = 0;
        guessProgress = "";
        guessedLetters = "";
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
                        for (Character c : password.toCharArray()){
                            guessProgress += "_ ";
                        }

                        /**
                         * Connect to player
                         */

                        Socket player = this.server.accept();
                        DataInputStream guessReader = new DataInputStream(player.getInputStream());

                        while(true){
                            String guessedLetter = guessReader.readUTF();
                            System.out.println(guessedLetter);
                            System.out.println(guessedLetters);
                            

                            if(password.indexOf(guessedLetter) != -1){
                                StringBuilder stringBuilder = new StringBuilder(guessProgress);
                                stringBuilder.setCharAt(password.indexOf(guessedLetter), guessedLetter.charAt(0));
                            } else {
                                wrongGuess();
                            }
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

    public void wrongGuess(){
        if(wrongGuesses < 13){
            wrongGuesses++;
        }
        System.out.println(guessProgress);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public Thread getServerThread() {
        return serverThread;
    }

    public void setServerThread(Thread serverThread) {
        this.serverThread = serverThread;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public String getGuessProgress() {
        return guessProgress;
    }

    public void setGuessProgress(String guessProgress) {
        this.guessProgress = guessProgress;
    }

    public String getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(String guessedLetters) {
        this.guessedLetters = guessedLetters;
    }
}
