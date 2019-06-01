package instances;

import client.GalgjePlayer;
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
import java.io.IOException;
import java.util.Scanner;

public class Client extends Application {

    public static void main(String[] args) {
        launch(Client.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        client.GalgjePlayer player = new GalgjePlayer("localhost", 10000);
        player.connect();
        Stage stage = new Stage();

        stage.setTitle("player");

        Image image = new Image("file:res/galgje0.png");
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        imageView.setFitWidth(750);

        TextField letterToGuessTextField = new TextField();
        TextField guessedLettersTextField = new TextField();
        guessedLettersTextField.setEditable(false);
        TextField wordProgressTextField = new TextField();
        wordProgressTextField.setEditable(false);

        Button button = new Button("guess letter");

        button.setOnAction( event -> {
            if(!letterToGuessTextField.getText().isEmpty()) {
                player.guessLetter(letterToGuessTextField.getText());
                letterToGuessTextField.clear();


                String serverData = player.readServerGuiData();
                Scanner scanner = new Scanner(serverData);
                scanner.useDelimiter("#");
                imageView.setImage(new Image(scanner.next()));
                guessedLettersTextField.setText(scanner.next());
                wordProgressTextField.setText(scanner.next());


            }
        });

        HBox guessletterBox = new HBox();
        guessletterBox.getChildren().addAll(letterToGuessTextField, button);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageView, guessletterBox, wordProgressTextField, guessedLettersTextField);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        // stage.initOwner(parent);
        // stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
