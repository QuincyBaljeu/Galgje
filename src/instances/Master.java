package instances;

import client.GalgjeMaster;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

public class Master extends Application {
    private client.GalgjeMaster master = new GalgjeMaster("localhost", 10000);
    private ImageView imageViewMaster;
    private TextField wordProgressTextField;
    private TextField guessedLettersTextFieldMaster;


    public static void main(String[] args) {
        launch(Master.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        master.connect();
        Stage stage = new Stage();

        Image image = new Image("file:res/galgje0.png");
        imageViewMaster = new ImageView(image);

        TextField passwordTextField = new TextField();
        Button setPassword = new Button("set word");
        wordProgressTextField = new TextField();
        wordProgressTextField.setEditable(false);
        guessedLettersTextFieldMaster = new TextField();
        guessedLettersTextFieldMaster.setEditable(false);


        /**
         * sets password en sends it to server
         */

        setPassword.setOnAction(event -> {
            if (!passwordTextField.getText().isEmpty()) {
                master.enterPassword(passwordTextField.getText());

                if (passwordTextField.getText().length() > 1) {
                    passwordTextField.setEditable(false);
                }

                new Thread(longRunningTask).start();
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageViewMaster, passwordTextField, setPassword, wordProgressTextField, guessedLettersTextFieldMaster);

        HBox hBox = new HBox();

        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        imageViewMaster.setFitWidth(750);
        stage.setTitle("master");
        // stage.initOwner(parent2);
        // stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Thread for checking input from server
     */

    Task<Void> longRunningTask = new Task<Void>() {

        @Override
        protected Void call() throws Exception {
            while (true){
                String serverData = master.readServerGuiData();

                Scanner scanner = new Scanner(serverData);
                scanner.useDelimiter("#");
                imageViewMaster.setImage(new Image(scanner.next()));
                guessedLettersTextFieldMaster.setText(scanner.next());
                wordProgressTextField.setText(scanner.next());
            }
        }
    };

}

