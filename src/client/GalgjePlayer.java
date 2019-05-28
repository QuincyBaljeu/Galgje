package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GalgjePlayer extends Application {

    private Socket socket;
    private String host;
    private int port;
    private String name;

    public GalgjePlayer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    int wrongAnswers = 2;

    public void wrong(){
        wrongAnswers++;
    }


    public void start(Stage stage){
        stage.setMinWidth(1000);
        stage.setMinHeight(1000);



        Image image = new Image("file:res/galgje.png");
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        imageView.setFitWidth(750);


        Button button = new Button("guess letter");

        button.setOnAction( event -> {
            imageView.setImage(new Image("file:res/galgje" + wrongAnswers +".png"));
            wrong();
        });


        TextField letterToGuess = new TextField();
        TextField guessedLetters = new TextField();

        HBox guessletterBox = new HBox();
        guessletterBox.getChildren().addAll(letterToGuess, button);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageView, guessletterBox, guessedLetters);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
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
