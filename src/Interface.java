import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Interface extends Application {

    int wrongAnswers = 2;

    public void wrong(){
        wrongAnswers++;
    }

    @Override
    public void start(Stage stage){
        stage.setMinWidth(1000);
        stage.setMinHeight(1000);



        Image image = new Image("file:C:/Users/qbalj/Documents/Galgje/res/galgje.png");
        ImageView imageView = new ImageView();

        imageView.setImage(image);
        imageView.setFitWidth(750);


        Button button = new Button("guess letter");

        button.setOnAction( event -> {
            imageView.setImage(new Image("file:C:/Users/qbalj/Documents/Galgje/res/galgje" + wrongAnswers +".png"));
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

    public static void main(String[] args) {
        launch(Interface.class);
    }
}
