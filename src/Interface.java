import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Interface extends Application {

    public void start(Stage stage){

        stage.setMaxHeight(700);
        stage.setMaxWidth(700);
        Button button = new Button("insert dooi manneke");
        button.setMinSize(400, 400);
        Scene scene = new Scene(button);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Interface.class);
    }
}
