import client.GalgjeMaster;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.border.Border;


public class Master extends Application {

    public void start(Stage stage){
        stage.setMinHeight(1000);
        stage.setMinWidth(1000);

        TextField wordToGuess = new TextField();
        Button bttn = new Button("guess");


        Scene scene = new Scene(wordToGuess);
        stage.setScene(scene);

    }
    public static void main(String[] args) {
       // GalgjeMaster master = new GalgjeMaster("localhost", 10000);
        //master.connect();

        launch(Master.class);

    }
}
