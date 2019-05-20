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
            boolean playing = true;
            this.server = new ServerSocket(port);
            this.serverThread = new Thread(()->{
                while (playing) {
                    System.out.println("Waiting for player to connect");
                    try {
                        Socket master = this.server.accept();
                        Socket player = this.server.accept();

                        DataInputStream keyReader = new DataInputStream(master.getInputStream());
                        DataInputStream userGuess = new DataInputStream(player.getInputStream());

                        String keyWord = keyReader.readUTF();
                        System.out.println(keyWord);
                        String winCondition = keyWord;



                      //  DataOutputStream playerMessages = new DataOutputStream(player.getOutputStream());
                        int wrongGuesses = 0;
                        System.out.println("Keyword: " + keyWord);
                            while (true) {
                                if(winCondition.length() == 0){
                                    System.out.println("You won");
                                    System.out.println("Wrong guesses:" + wrongGuesses);
                                }
                                String guess = userGuess.readUTF();
                                System.out.println("Guess: " + guess);
                                if(guess.length() > 1){
                               //    playerMessages.writeUTF("Please enter a single character");
                                } else {
                                    if(keyWord.contains(guess)){
                                        winCondition = winCondition.replaceAll(guess, "");
                                        System.out.println("right");
                               //         playerMessages.writeUTF("You guessed right");

                                    } else {
                             //           playerMessages.writeUTF("You guessed wrong");
                                        System.out.println("wrong");
                                        wrongGuesses++;
                                    }
                                }
                            }

                    } catch (IOException e) {
                        System.out.println("Connection lost");
                    }
                }
            });
            this.serverThread.start();
        } catch (IOException e){
            System.out.println("Could not connect");
        }


    }



}
