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

public class GUI extends Application {

    int wrongAnswers = 2;

    public void wrong(){
        wrongAnswers++;
    }
    @Override
    public void start(Stage stage) throws Exception {
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

        stage.setTitle("GUI Test");
        stage.show();
    }

    public  void masterBox(Stage parent2){
        Stage stage = new Stage();

        Image image = new Image("file:res/galgje.png");
        ImageView imageView = new ImageView();

        imageView.setImage(image);


        TextField wordToGuess = new TextField();
        Button setWord = new Button("set word");

        VBox word = new VBox();
        word.getChildren().addAll(wordToGuess, setWord);

        TextField guessedLetters = new TextField();
        guessedLetters.setMinSize(200, 700);

        VBox vBox = new VBox();
        HBox hBox= new HBox();
        vBox.getChildren().addAll(imageView, word);
        hBox.getChildren().addAll(vBox, guessedLetters);

        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        imageView.setFitWidth(750);
        stage.setTitle("master");
        stage.initOwner(parent2);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void playerBox (Stage parent) {
        Stage stage = new Stage();

        stage.setTitle("player");

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
        stage.initOwner(parent);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}