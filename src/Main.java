import client.GalgjeMaster;
import client.GalgjePlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.GalgjeServer;

import javax.xml.soap.Text;

public class Main extends Application {

    private server.GalgjeServer server;
    ImageView imageViewMaster;

    @Override
    public void start(Stage stage) throws Exception {
        server = new GalgjeServer(10000);
        server.start();
        Button button = new Button("Master");
        Button button1 = new Button("player");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(button, button1);

        button1.setOnAction((event) -> {
            this.playerBox(stage);
        });

        button.setOnAction( (event) -> {
            this.masterBox(stage);
        });

        Scene scene = new Scene(hBox);

        stage.setScene(scene);

        stage.setTitle("Main Test");
        stage.show();
    }

    public  void masterBox(Stage parent2){
        client.GalgjeMaster master = new GalgjeMaster("localhost", 10000);
        master.connect();
        Stage stage = new Stage();

        Image image = new Image("file:res/galgje0.png");
        imageViewMaster = new ImageView(image);

        TextField passwordTextField = new TextField();
        Button setPassword = new Button("set word");
        TextField wordProgressTextField = new TextField();
        wordProgressTextField.setEditable(false);
        TextField guessedLettersTextField = new TextField();
        guessedLettersTextField.setEditable(false);

        setPassword.setOnAction(event -> {
            master.enterPassword(passwordTextField.getText());

            if(passwordTextField.getText().length() > 1){
                passwordTextField.setEditable(false);
            }

            guessedLettersTextField.setText(server.getGuessedLetters());
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageViewMaster, passwordTextField, setPassword, wordProgressTextField, guessedLettersTextField);

        HBox hBox= new HBox();

        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        imageViewMaster.setFitWidth(750);
        stage.setTitle("master");
       // stage.initOwner(parent2);
       // stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void playerBox (Stage parent) {
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
            player.guessLetter(letterToGuessTextField.getText());
            guessedLettersTextField.setText(server.getGuessedLetters());
            guessedLettersTextField.clear();

            imageView.setImage(new Image("file:res/galgje" + server.getWrongGuesses() + ".png"));
            imageViewMaster.setImage(new Image("file:res/galgje" + server.getWrongGuesses() + ".png"));
        });

        HBox guessletterBox = new HBox();
        guessletterBox.getChildren().addAll(letterToGuessTextField, button);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageView, guessletterBox, guessedLettersTextField, wordProgressTextField);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
       // stage.initOwner(parent);
       // stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}