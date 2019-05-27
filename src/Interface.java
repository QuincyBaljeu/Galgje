import com.sun.javafx.scene.control.skin.CustomColorDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;

import javax.xml.soap.Text;

public class Interface extends Application {

    public void start(Stage stage){
        stage.setMinWidth(1000);
        stage.setMinHeight(1000);

        Image image = new Image("galgje2.png");
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        Button button = new Button("guess letter");
        TextField letterToGuess = new TextField();
        TextField guessedLetters = new TextField();


        HBox guessletterBox = new HBox();
        guessletterBox.getChildren().addAll(letterToGuess, button);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(guessletterBox, guessedLetters);



        HBox hBox = new HBox();
        hBox.getChildren().addAll(imageView, vBox);
        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Interface.class);
    }
}
