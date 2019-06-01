package server;


import javafx.scene.image.Image;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class GalgjeServer implements Serializable {

    private int port;
    private ServerSocket server;
    private Thread serverThread;
    private ArrayList<Thread> threads;
    private static int wrongGuesses;
    private String guessProgress;
    private ArrayList<String> guessedLetters;
    private Boolean playing;
    private String serverGuiData;


    public GalgjeServer(int port) {
        this.port = port;
        this.threads = new ArrayList<>();
        wrongGuesses = 0;
        guessProgress = "";
        guessedLetters = new ArrayList<String>();
        playing = true;
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
                        Socket player = this.server.accept();
                        DataInputStream passwordReader = new DataInputStream(master.getInputStream());
                        DataOutputStream dataWriterMaster = new DataOutputStream(master.getOutputStream());
                        DataOutputStream dataWriterPlayer = new DataOutputStream(player.getOutputStream());
                        String password = passwordReader.readUTF();
                        System.out.println(password);
                        for (Character c : password.toCharArray()){
                            guessProgress += "_";
                        }

                        /**
                         * Connect to player
                         */

                        DataInputStream guessReader = new DataInputStream(player.getInputStream());

                        while(true){
                            String guessedLetter = guessReader.readUTF();
                            if(!guessedLetters.contains(guessedLetter)){
                                guessedLetters.add(guessedLetter);
                            }

                            if(password.indexOf(guessedLetter) != -1){
                                StringBuilder stringBuilder = new StringBuilder(guessProgress);
                                for(char c : password.toCharArray()){
                                 if (c == guessedLetter.charAt(0)){
                                     System.out.println("yeet");
                                     stringBuilder.setCharAt(password.indexOf(c), c);
                                     guessProgress = stringBuilder.toString();
                                    }
                                }
                                if(guessProgress.equals(password)){
                                   gameOver();
                                }
                            } else {
                                wrongGuess();
                            }
                            if (playing){
                                serverGuiData = "file:res/galgje" + this.getWrongGuesses() + ".png" +
                                        "#" + getGuessedLettersGui() +
                                        "#" + guessProgress;
                            } else {
                                serverGuiData = "file:res/galgje12.png" +
                                        "#" + getGuessedLettersGui() +
                                        "#" + guessProgress;
                            }
                            dataWriterMaster.writeUTF(serverGuiData);
                            dataWriterPlayer.writeUTF(serverGuiData);

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

    public void gameOver(){
        playing = false;
    }

    public void wrongGuess(){
        if(wrongGuesses < 11){
            wrongGuesses++;
        }
    }

    public String getGuessedLettersGui() {
        String guessedLetterString = "";
        Collections.sort(guessedLetters);

        for (String character : guessedLetters){
            guessedLetterString += character + " ";
        }
        return guessedLetterString;
    }

    public String getArraylistString(ArrayList<String> arrayList){
        String listString = "";
        for (String s : arrayList){
            listString += listString;
        }
        return listString;
    }

    public Image getProgressImage(){
        return new javafx.scene.image.Image("file:res/galgje" + getWrongGuesses() + ".png");
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



    public void setGuessedLetters(ArrayList guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

}
